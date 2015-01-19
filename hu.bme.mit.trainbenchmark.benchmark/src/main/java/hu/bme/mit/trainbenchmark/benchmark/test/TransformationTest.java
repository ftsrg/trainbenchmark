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

package hu.bme.mit.trainbenchmark.benchmark.test;

import static org.junit.Assert.assertEquals;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.GenericBenchmarkLogic;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.hamcrest.Matchers;
import org.junit.Assert;

public abstract class TransformationTest extends TrainBenchmarkTest {

	protected void testTransformation(String queryName, String scenario, int expectedResultSize1, int expectedResultSize2)
			throws ParseException, IOException {
		GenericBenchmarkLogic bl = bi.initializeBenchmark(queryName, scenario);
		testTransformation(bl, expectedResultSize1, expectedResultSize2);
	}

	protected void testTransformation(GenericBenchmarkLogic bl, int expectedResultSize1, int expectedResultSize2) throws IOException {
		BenchmarkCase testCase = bl.getTestCase();

		try {
			testCase.init(bl.getBc());
			testCase.load();
			testCase.check();
			assertEquals(expectedResultSize1, testCase.getResultSize());
			((TransformationBenchmarkCase) testCase).modify();
			testCase.check();
			if (expectedResultSize2 > expectedResultSize1) {
				Assert.assertThat(testCase.getResultSize(), Matchers.greaterThanOrEqualTo(expectedResultSize1));
			} else {
				assertEquals(expectedResultSize2, testCase.getResultSize());
			}
		} finally {
			testCase.destroy();
		}
	}

}
