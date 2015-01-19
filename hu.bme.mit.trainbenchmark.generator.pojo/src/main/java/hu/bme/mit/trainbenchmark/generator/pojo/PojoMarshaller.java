/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.generator.pojo;

import com.google.common.base.Strings;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import hu.bme.mit.trainbenchmark.pojo.Graph;
import hu.bme.mit.trainbenchmark.pojo.Route;
import hu.bme.mit.trainbenchmark.pojo.Segment;
import hu.bme.mit.trainbenchmark.pojo.Sensor;
import hu.bme.mit.trainbenchmark.pojo.Switch;
import hu.bme.mit.trainbenchmark.pojo.SwitchPosition;
import hu.bme.mit.trainbenchmark.pojo.SwitchStateKind;
import hu.bme.mit.trainbenchmark.pojo.TrackElement;

import java.io.File;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PojoMarshaller {
	private final ObjectStore objectStore = new ObjectStore();
	private final XStream xStream = new XStream(new DomDriver());

	public PojoMarshaller() {
		xStream.setMode(XStream.ID_REFERENCES);
		xStream.registerConverter(new TrackElementConverter(objectStore));
		xStream.registerConverter(new SwitchConverter(objectStore));
		xStream.registerConverter(new SegmentConverter(objectStore));
		xStream.registerConverter(new SwitchPositionConverter(objectStore));
		xStream.alias("graph", Graph.class);
		xStream.alias("trackElement", TrackElement.class);
		xStream.alias("switch", Switch.class);
		xStream.alias("segment", Segment.class);
		xStream.alias("sensor", Sensor.class);
		xStream.alias("switchPosition", SwitchPosition.class);
		xStream.alias("route", Route.class);
	}

	public String toXML(Graph graph) {
		return xStream.toXML(graph);
	}

	public void toXML(Writer writer, Graph graph) {
		xStream.toXML(graph, writer);
	}

	public Graph fromXML(File file) {
		Graph graph = (Graph) xStream.fromXML(file);
		objectStore.checkEndUnmarshall();
		return graph;
	}

	public Graph fromXML(URL url) {
		Graph graph = (Graph) xStream.fromXML(url);
		objectStore.checkEndUnmarshall();
		return graph;
	}

	public Graph fromXML(String string) {
		Graph graph = (Graph) xStream.fromXML(string);
		objectStore.checkEndUnmarshall();
		return graph;
	}

	public static class TrackElementConverter extends AbstractXStreamConverter {
		public TrackElementConverter(ObjectStore store) {
			this(store, TrackElement.class);
		}

		protected TrackElementConverter(ObjectStore store, Class type) {
			super(store, type);
		}

		@Override
		public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
			TrackElement trackElement = (TrackElement) value;
			writeAttribute(writer, "ref-id", "" + trackElement.getId());
			writeObjectList(writer, context, "sensors", "sensor", trackElement.getSensors());
			writeString(writer, "connectsTo", toIds(trackElement.getConnectsTo()));
		}

		@Override
		public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
			return unmarshal(new TrackElement(), reader, context);
		}

		public Object unmarshal(final TrackElement trackElement, HierarchicalStreamReader reader, final UnmarshallingContext context) {
			final long id = Long.parseLong(reader.getAttribute("ref-id"));
			trackElement.setId(id);
			readNodes(reader, new AbstractXStreamConverter.NodeReader() {
				public void onNode(HierarchicalStreamReader reader, String name, String value) {
					if ("sensors".equals(name)) {
						List<Sensor> sensors = readObjectList(reader, context, Sensor.class);
						trackElement.setSensors(sensors);
					} else if ("connectsTo".equals(name)) {
						if (Strings.isNullOrEmpty(value)) {
							return;
						}
						String[] connects = value.split(",");
						for (int i = 0; i < connects.length; i++) {
							long connectId = Long.parseLong(connects[i]);
							TrackElement connected = store.get(connectId);
							trackElement.addConnectsTo(connected);
							if (connected == null) {
								final int j = i;
								store.listen(connectId, new Listener<TrackElement>() {
									@Override
									public void onObject(TrackElement object) {
										trackElement.getConnectsTo().set(j, object);
									}
								});
							}
						}
					}
				}
			});
			store.store(id, trackElement);
			return trackElement;
		}

		public String toIds(List<TrackElement> trackElements) {
			if (trackElements == null || trackElements.isEmpty()) {
				return "";
			}
			StringBuilder sb = new StringBuilder();
			Iterator<TrackElement> i = trackElements.iterator();
			sb.append(i.next().getId());
			while (i.hasNext()) {
				sb.append(",").append(i.next().getId());
			}
			return sb.toString();
		}
	}

	public static class SwitchConverter extends TrackElementConverter {
		public SwitchConverter(ObjectStore store) {
			super(store, Switch.class);
		}

		@Override
		public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
			super.marshal(value, writer, context);
			Switch switchObj = (Switch) value;
			writeObject(writer, context, "actualState", switchObj.getActualState());
			writeObjectList(writer, context, "switchPositions", "switchPosition", switchObj.getSwitchPositions());
		}

		@Override
		public Object unmarshal(HierarchicalStreamReader reader, final UnmarshallingContext context) {
			final Switch switchObj = new Switch();
			unmarshal(switchObj, reader, context);
			readNodes(reader, new AbstractXStreamConverter.NodeReader() {
				public void onNode(HierarchicalStreamReader reader, String name, String value) {
					if ("actualState".equals(name)) {
						switchObj.setActualState(SwitchStateKind.valueOf(value));
					} else if ("switchPositions".equals(name)) {
						List<SwitchPosition> switchPositions = readObjectList(reader, context, SwitchPosition.class);
						switchObj.setSwitchPositions(switchPositions);
					}
				}
			});
			return switchObj;
		}
	}

	public static class SegmentConverter extends TrackElementConverter {
		public SegmentConverter(ObjectStore store) {
			super(store, Segment.class);
		}

		@Override
		public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
			writeAttribute(writer, "length", "" + ((Segment) value).getLength());
			super.marshal(value, writer, context);
		}

		@Override
		public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
			final Segment segment = new Segment();
			unmarshal(segment, reader, context);
			int length = Integer.parseInt(reader.getAttribute("length"));
			segment.setLength(length);
			return segment;
		}
	}

	public static class SwitchPositionConverter extends AbstractXStreamConverter {
		public SwitchPositionConverter(ObjectStore store) {
			super(store, SwitchPosition.class);
		}

		@Override
		public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
			SwitchPosition switchPosition = ((SwitchPosition) value);
			writeObject(writer, context, "switch", switchPosition.getSwitch());
			writeObject(writer, context, "switchState", switchPosition.getSwitchState());
			writeObject(writer, context, "route", switchPosition.getRoute());
		}

		@Override
		public Object unmarshal(final HierarchicalStreamReader reader, final UnmarshallingContext context) {
			final SwitchPosition switchPosition = new SwitchPosition();
			readNodes(reader, new AbstractXStreamConverter.NodeReader() {
				public void onNode(HierarchicalStreamReader reader, String name, String value) {
					if ("switch".equals(name)) {
						switchPosition.setSwitch(readObject(reader, context, Switch.class));
					} else if ("switchState".equals(name)) {
						switchPosition.setSwitchState(SwitchStateKind.valueOf(value));
					} else if ("route".equals(name)) {
						switchPosition.setSwitch(readObject(reader, context, Switch.class));
					}
				}
			});
			return switchPosition;
		}
	}

	public static abstract class AbstractXStreamConverter implements Converter {
		private final Class type;
		protected final ObjectStore store;

		protected AbstractXStreamConverter(ObjectStore store, Class type) {
			this.type = type;
			this.store = store;
		}

		public boolean canConvert(Class clazz) {
			return type == clazz;
		}

		protected void writeAttribute(HierarchicalStreamWriter writer, String name, String value) {
			if (value != null) {
				writer.addAttribute(name, value);
			}
		}

		protected void writeString(HierarchicalStreamWriter writer, String name, String value) {
			if (value != null) {
				writer.startNode(name);
				writer.setValue(value);
				writer.endNode();
			}
		}

		protected void writeObject(HierarchicalStreamWriter writer, MarshallingContext context, String name, Object value) {
			if (value != null) {
				writer.startNode(name);
				context.convertAnother(value);
				writer.endNode();
			}
		}

		protected void writeList(HierarchicalStreamWriter writer, String listName, String itemName, Iterable<String> list) {
			if (list != null) {
				java.util.Iterator<String> i = list.iterator();
				if (i.hasNext()) {
					writer.startNode(listName);
					while (i.hasNext()) {
						writer.startNode(itemName);
						writer.setValue(i.next());
						writer.endNode();
					}
					writer.endNode();
				}

			}
		}

		protected void writeObjectList(HierarchicalStreamWriter writer, MarshallingContext context, String listName, String itemName,
				Iterable<?> list) {
			if (list != null) {
				java.util.Iterator<? extends Object> i = list.iterator();
				if (i.hasNext()) {
					writer.startNode(listName);
					while (i.hasNext()) {
						writeObject(writer, context, itemName, i.next());
					}
					writer.endNode();
				}
			}
		}

		protected void writePropertyMap(HierarchicalStreamWriter writer, MarshallingContext context, String mapName, Map<String, String> map) {
			if (map != null && !map.isEmpty()) {
				writer.startNode(mapName);
				for (Map.Entry<String, String> entry : map.entrySet()) {
					writer.startNode("property");
					writer.addAttribute("key", entry.getKey());
					writer.addAttribute("value", entry.getValue());
					writer.endNode();
				}
				writer.endNode();
			}
		}

		protected void readNodes(HierarchicalStreamReader reader, NodeReader nodeReader) {
			while (reader.hasMoreChildren()) {
				reader.moveDown();
				nodeReader.onNode(reader, reader.getNodeName(), reader.getValue());
				reader.moveUp();
			}
		}

		protected List<String> readList(HierarchicalStreamReader reader) {
			List<String> list = new ArrayList<String>();
			while (reader.hasMoreChildren()) {
				reader.moveDown();
				list.add(reader.getValue());
				reader.moveUp();
			}
			return list;
		}

		protected <T> T readObject(HierarchicalStreamReader reader, UnmarshallingContext context, Class<? extends T> clazz) {
			return (T) context.convertAnother(reader.getValue(), clazz);
		}

		protected <T> List<T> readObjectList(HierarchicalStreamReader reader, UnmarshallingContext context, Class<? extends T> clazz) {
			List<T> list = new ArrayList<T>();
			while (reader.hasMoreChildren()) {
				reader.moveDown();
				list.add((T) context.convertAnother(reader.getValue(), clazz));
				reader.moveUp();
			}
			return list;
		}

		protected Map<String, String> readPropertyMap(HierarchicalStreamReader reader, UnmarshallingContext context) {
			Map<String, String> map = new HashMap<String, String>();
			while (reader.hasMoreChildren()) {
				reader.moveDown();
				map.put(reader.getAttribute("key"), reader.getAttribute("value"));
				reader.moveUp();
			}
			return map;
		}

		public interface NodeReader {
			void onNode(HierarchicalStreamReader reader, String name, String value);
		}
	}

	private static class ObjectStore {
		private Map<Long, Object> store = new HashMap<>();
		private Map<Long, List<Listener<?>>> listenersById = new HashMap<>();

		public <T> T get(long id) {
			return (T) store.get(id);
		}

		public void listen(long id, Listener listener) {
			List<Listener<?>> listeners = listenersById.get(id);
			if (listeners == null) {
				listeners = new ArrayList<>();
				listenersById.put(id, listeners);
			}
			listeners.add(listener);
		}

		public void store(long id, Object object) {
			store.put(id, object);
			List<Listener<?>> listeners = listenersById.remove(id);
			if (listeners != null) {
				for (Listener listener : listeners) {
					listener.onObject(object);
				}
			}
		}

		public void checkEndUnmarshall() {
			if (!listenersById.isEmpty()) {
				throw new RuntimeException("Unresolved ids: " + listenersById.keySet());
			}
		}
	}

	private interface Listener<T> {
		void onObject(T obj);
	}
}
