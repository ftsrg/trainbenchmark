package hu.bme.mit.trainbenchmark.benchmark.fourstore.driver;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryResultHandler;
import org.openrdf.query.QueryResultHandlerException;
import org.openrdf.query.TupleQueryResultHandlerException;

public class BindingSetCollector implements QueryResultHandler {

	protected Collection<BindingSet> bindingSets = new LinkedList<BindingSet>();

	@Override
	public void handleBoolean(final boolean value) throws QueryResultHandlerException {
	}

	@Override
	public void handleLinks(final List<String> linkUrls) throws QueryResultHandlerException {
	}

	@Override
	public void startQueryResult(final List<String> bindingNames) throws TupleQueryResultHandlerException {
	}

	@Override
	public void endQueryResult() throws TupleQueryResultHandlerException {
	}

	@Override
	public void handleSolution(final BindingSet bindingSet) throws TupleQueryResultHandlerException {
		bindingSets.add(bindingSet);
	}

	public Collection<BindingSet> getBindingSets() {
		return bindingSets;
	}
}
