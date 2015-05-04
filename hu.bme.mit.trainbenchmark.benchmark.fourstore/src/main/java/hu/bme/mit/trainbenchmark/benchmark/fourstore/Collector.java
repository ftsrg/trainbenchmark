package hu.bme.mit.trainbenchmark.benchmark.fourstore;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryResultHandler;
import org.openrdf.query.QueryResultHandlerException;
import org.openrdf.query.TupleQueryResultHandlerException;

public class Collector implements QueryResultHandler {

	Collection<BindingSet> matches = new LinkedList<BindingSet>();

	public void endQueryResult() throws TupleQueryResultHandlerException {
		// TODO Auto-generated method stub

	}

	public void handleBoolean(final boolean arg0) throws QueryResultHandlerException {
		// TODO Auto-generated method stub

	}

	public void handleLinks(final List<String> arg0) throws QueryResultHandlerException {
		// TODO Auto-generated method stub

	}

	public void handleSolution(final BindingSet arg0) throws TupleQueryResultHandlerException {
		matches.add(arg0);
	}

	public void startQueryResult(final List<String> arg0) throws TupleQueryResultHandlerException {
		// TODO Auto-generated method stub

	}

	public Collection<BindingSet> getMatches() {
		return matches;
	}

}
