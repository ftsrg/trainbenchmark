package hu.bme.mit.trainbenchmark.benchmark.ingraph.queries;

import hu.bme.mit.ire.ChangeListener;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;

import java.util.HashSet;

/**
 * Created by wafle on 11/20/2016.
 */
public class IngraphChangeListener extends ChangeListener{
    HashSet<IngraphMatch> results = new HashSet<>();
    private RailwayQuery query;

    IngraphChangeListener(final RailwayQuery query) {
        this.query = query;
    }

    @Override
    public void listener(List<Map<Object, Object>> positive, List<Map<Object, Object>> negative) {
        Iterator<Map<Object, Object>> positiveIterator = positive.iterator();
        while (positiveIterator.hasNext()) {
            final Map<Object, Object> qs = positiveIterator.next();
            final IngraphMatch match = IngraphMatch.createMatch(query, qs);
            results.add(match);
        }

        Iterator<Map<Object, Object>> negativeIterator = negative.iterator();
        while (negativeIterator.hasNext()) {
            final Map<Object, Object> qs = negativeIterator.next();
            final IngraphMatch match = IngraphMatch.createMatch(query, qs);
            results.remove(match);
        }
    }
}
