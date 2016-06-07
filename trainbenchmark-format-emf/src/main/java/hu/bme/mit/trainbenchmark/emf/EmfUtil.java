package hu.bme.mit.trainbenchmark.emf;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class EmfUtil {
	
	public static void registerExtension() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(EmfConstants.RAILWAY_EXTENSION, new XMIResourceFactoryImpl());
	}
	
}
