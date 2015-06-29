///*******************************************************************************
// * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
// * All rights reserved. This program and the accompanying materials
// * are made available under the terms of the Eclipse Public License v1.0
// * which accompanies this distribution, and is available at
// * http://www.eclipse.org/legal/epl-v10.html
// *
// * Contributors:
// *   Benedek Izso - initial API and implementation
// *   Gabor Szarnyas - initial API and implementation
// *******************************************************************************/
//package hu.bme.mit.trainbenchmark.benchmark.util;
//
//import hu.bme.mit.trainbenchmark.constants.Query;
//
//import java.io.File;
//import java.io.IOException;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//
//public class ResultSerializer {
//
//	public static void serializeToJSON(final BenchmarkResult br) throws IOException {
//		final ObjectMapper mapper = new ObjectMapper();
//		// to enable standard indentation ("pretty-printing"):
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
//		// to allow serialization of "empty" POJOs (no properties to serialize)
//		// (without this setting, an exception is thrown in those cases)
//		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//		mapper.setVisibilityChecker(mapper.getVisibilityChecker().with(JsonAutoDetect.Visibility.NONE));
//
//		final String scenario = br.getScenario();
//		final Query query = br.getQuery();
//		final String tool = br.getTool();
//		final int size = br.getSize();
//		final int runIndex = br.getRunIndex();
//		final String relativeFilePath = "results/" + tool + "-" + scenario + "-" + query.toString() + "-Size" + size + "-Index" + runIndex
//				+ ".json";
//		mapper.writeValue(new File(br.getBenchmarkConfig().getWorkspacePath() + relativeFilePath), br);
//
//		System.out.println("Create JSON file: " + relativeFilePath);
//	}
//}
