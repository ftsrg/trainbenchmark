package hu.bme.mit.trainbenchmark.benchmark.jena.match.schedule;

import hu.bme.mit.trainbenchmark.benchmark.jena.match.JenaMatch;
import hu.bme.mit.trainbenchmark.benchmark.matches.schedule.ScheduleCountMatch;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Resource;

public class JenaStationsPathMatch extends JenaMatch implements ScheduleCountMatch {

	public JenaStationsPathMatch(QuerySolution qs) {
		super(qs);
	}

	@Override
	public Object getCount() {
		return null;
	}

	@Override
	public Resource[] toArray() {
		return null;
	}

}
