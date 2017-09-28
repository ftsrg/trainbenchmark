/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/ConnectedSegmentsInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.util.ConnectedSegmentsInjectQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.viatra.connectedSegmentsInject pattern,
 * to be used in conjunction with {@link ConnectedSegmentsInjectMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see ConnectedSegmentsInjectMatcher
 * @see ConnectedSegmentsInjectProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class ConnectedSegmentsInjectMatch extends BasePatternMatch {
  private Sensor fSensor;
  
  private Segment fSegment1;
  
  private Segment fSegment3;
  
  private static List<String> parameterNames = makeImmutableList("sensor", "segment1", "segment3");
  
  private ConnectedSegmentsInjectMatch(final Sensor pSensor, final Segment pSegment1, final Segment pSegment3) {
    this.fSensor = pSensor;
    this.fSegment1 = pSegment1;
    this.fSegment3 = pSegment3;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("sensor".equals(parameterName)) return this.fSensor;
    if ("segment1".equals(parameterName)) return this.fSegment1;
    if ("segment3".equals(parameterName)) return this.fSegment3;
    return null;
  }
  
  public Sensor getSensor() {
    return this.fSensor;
  }
  
  public Segment getSegment1() {
    return this.fSegment1;
  }
  
  public Segment getSegment3() {
    return this.fSegment3;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("sensor".equals(parameterName) ) {
        this.fSensor = (Sensor) newValue;
        return true;
    }
    if ("segment1".equals(parameterName) ) {
        this.fSegment1 = (Segment) newValue;
        return true;
    }
    if ("segment3".equals(parameterName) ) {
        this.fSegment3 = (Segment) newValue;
        return true;
    }
    return false;
  }
  
  public void setSensor(final Sensor pSensor) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSensor = pSensor;
  }
  
  public void setSegment1(final Segment pSegment1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSegment1 = pSegment1;
  }
  
  public void setSegment3(final Segment pSegment3) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSegment3 = pSegment3;
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.viatra.connectedSegmentsInject";
  }
  
  @Override
  public List<String> parameterNames() {
    return ConnectedSegmentsInjectMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSensor, fSegment1, fSegment3};
  }
  
  @Override
  public ConnectedSegmentsInjectMatch toImmutable() {
    return isMutable() ? newMatch(fSensor, fSegment1, fSegment3) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"sensor\"=" + prettyPrintValue(fSensor) + ", ");
    
    result.append("\"segment1\"=" + prettyPrintValue(fSegment1) + ", ");
    
    result.append("\"segment3\"=" + prettyPrintValue(fSegment3)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSensor == null) ? 0 : fSensor.hashCode());
    result = prime * result + ((fSegment1 == null) ? 0 : fSegment1.hashCode());
    result = prime * result + ((fSegment3 == null) ? 0 : fSegment3.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
        return true;
    if (!(obj instanceof ConnectedSegmentsInjectMatch)) { // this should be infrequent
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof IPatternMatch)) {
            return false;
        }
        IPatternMatch otherSig  = (IPatternMatch) obj;
        if (!specification().equals(otherSig.specification()))
            return false;
        return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    ConnectedSegmentsInjectMatch other = (ConnectedSegmentsInjectMatch) obj;
    if (fSensor == null) {if (other.fSensor != null) return false;}
    else if (!fSensor.equals(other.fSensor)) return false;
    if (fSegment1 == null) {if (other.fSegment1 != null) return false;}
    else if (!fSegment1.equals(other.fSegment1)) return false;
    if (fSegment3 == null) {if (other.fSegment3 != null) return false;}
    else if (!fSegment3.equals(other.fSegment3)) return false;
    return true;
  }
  
  @Override
  public ConnectedSegmentsInjectQuerySpecification specification() {
    try {
        return ConnectedSegmentsInjectQuerySpecification.instance();
    } catch (ViatraQueryException ex) {
         // This cannot happen, as the match object can only be instantiated if the query specification exists
         throw new IllegalStateException (ex);
    }
  }
  
  /**
   * Returns an empty, mutable match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @return the empty match.
   * 
   */
  public static ConnectedSegmentsInjectMatch newEmptyMatch() {
    return new Mutable(null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static ConnectedSegmentsInjectMatch newMutableMatch(final Sensor pSensor, final Segment pSegment1, final Segment pSegment3) {
    return new Mutable(pSensor, pSegment1, pSegment3);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static ConnectedSegmentsInjectMatch newMatch(final Sensor pSensor, final Segment pSegment1, final Segment pSegment3) {
    return new Immutable(pSensor, pSegment1, pSegment3);
  }
  
  private static final class Mutable extends ConnectedSegmentsInjectMatch {
    Mutable(final Sensor pSensor, final Segment pSegment1, final Segment pSegment3) {
      super(pSensor, pSegment1, pSegment3);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends ConnectedSegmentsInjectMatch {
    Immutable(final Sensor pSensor, final Segment pSegment1, final Segment pSegment3) {
      super(pSensor, pSegment1, pSegment3);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
