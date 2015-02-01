package hu.bme.mit.trainbenchmark.benchmark.virtuoso.benchmarkcases.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.virtuoso.util.SesameData;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.benchmark.virtuoso.benchmarkcases.PosLength;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

public class PosLengthUser extends PosLength implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		final long nElemToModify = Util.calcModify(vbc, vbc.getModificationConstant(),bmr);
		bmr.addModifyParams(nElemToModify);
		
		// modify
		final long start = System.nanoTime();
		final ValueFactory f = myRepository.getValueFactory();
		final URI segmentOC = f.createURI(RDFConstants.BASE_PREFIX + ModelConstants.SEGMENT);
		RepositoryResult<Statement> segmentsIter;

		try {
			segmentsIter = con.getStatements(null, RDF.TYPE, segmentOC, true);

			final List<Resource> segments = new ArrayList<Resource>();
			for (final Statement s : segmentsIter.asList()) {
				segments.add(s.getSubject());
			}

			final List<Resource> segmentsToModify = Transformation.pickRandom(nElemToModify, segments);
			
			final List<SesameData> itemsToRemove = new ArrayList<SesameData>();
			for (final Resource segment : segmentsToModify) {
				final URI segmentLength = f.createURI(RDFConstants.BASE_PREFIX + ModelConstants.SEGMENT_LENGTH);
				final SesameData jd = new SesameData();

				final RepositoryResult<Statement> statementsToRemove = con.getStatements(segment, segmentLength, null, true);
				jd.setStatements(statementsToRemove.asList());

				jd.setResource(segment);
				jd.setUri(segmentLength);
				jd.setLiteral(f.createLiteral(-1));

				itemsToRemove.add(jd);
			}

			// edit
			final long startEdit = System.nanoTime();
			for (final SesameData jd : itemsToRemove) {
				con.remove(jd.getStatements());
				con.add(jd.getResource(), jd.getUri(), jd.getLiteral());
			}
			con.commit();
			final long end = System.nanoTime();
			bmr.addEditTime(end - startEdit);
			bmr.addModificationTime(end - start);
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

}
