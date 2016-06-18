package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import org.eclipse.viatra.query.runtime.api.IMatchProcessor;

import hu.bme.mit.trainbenchmark.benchmark.viatra.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.viatra.ConnectedSegments pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class ConnectedSegmentsProcessor implements IMatchProcessor<ConnectedSegmentsMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSensor the value of pattern parameter sensor in the currently processed match
   * @param pSegment1 the value of pattern parameter segment1 in the currently processed match
   * @param pSegment2 the value of pattern parameter segment2 in the currently processed match
   * @param pSegment3 the value of pattern parameter segment3 in the currently processed match
   * @param pSegment4 the value of pattern parameter segment4 in the currently processed match
   * @param pSegment5 the value of pattern parameter segment5 in the currently processed match
   * @param pSegment6 the value of pattern parameter segment6 in the currently processed match
   * 
   */
  public abstract void process(final Sensor pSensor, final Segment pSegment1, final Segment pSegment2, final Segment pSegment3, final Segment pSegment4, final Segment pSegment5, final Segment pSegment6);
  
  @Override
  public void process(final ConnectedSegmentsMatch match) {
    process(match.getSensor(), match.getSegment1(), match.getSegment2(), match.getSegment3(), match.getSegment4(), match.getSegment5(), match.getSegment6());
  }
}
