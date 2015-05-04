package hu.bme.mit.trainbenchmark.benchmark.fourstore;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResultHandlerException;
import org.openrdf.query.resultio.QueryResultParseException;
import org.openrdf.query.resultio.text.tsv.SPARQLResultsTSVParser;
import org.openrdf.query.resultio.text.tsv.SPARQLResultsTSVParserFactory;

public class Main {

	public static void main(final String[] args) throws TupleQueryResultHandlerException, QueryResultParseException, IOException {
		final SPARQLResultsTSVParserFactory f = new SPARQLResultsTSVParserFactory();

		final SPARQLResultsTSVParser parser = new SPARQLResultsTSVParser();
		final InputStream is = new FileInputStream("/home/szarnyasg/results.tsv");
		final Collector collector = new Collector();
		parser.setQueryResultHandler(collector);
		parser.parse(is);

		final Collection<BindingSet> matches = collector.getMatches();
		for (final BindingSet match : matches) {
			System.out.println(match);
		}

		// SesameMatch accepts BindingSets

	}
}
