/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/ConnectedSegments.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.util.ConnectedSegmentsQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.viatra.connectedSegments pattern,
 * to be used in conjunction with {@link ConnectedSegmentsMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see ConnectedSegmentsMatcher
 * @see ConnectedSegmentsProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class ConnectedSegmentsMatch extends BasePatternMatch {
  private Sensor fSensor;
  
  private Segment fSegment1;
  
  private Segment fSegment2;
  
  private Segment fSegment3;
  
  private Segment fSegment4;
  
  private Segment fSegment5;
  
  private Segment fSegment6;
  
  private static List<String> parameterNames = makeImmutableList("sensor", "segment1", "segment2", "segment3", "segment4", "segment5", "segment6");
  
  private ConnectedSegmentsMatch(final Sensor pSensor, final Segment pSegment1, final Segment pSegment2, final Segment pSegment3, final Segment pSegment4, final Segment pSegment5, final Segment pSegment6) {
    this.fSensor = pSensor;
    this.fSegment1 = pSegment1;
    this.fSegment2 = pSegment2;
    this.fSegment3 = pSegment3;
    this.fSegment4 = pSegment4;
    this.fSegment5 = pSegment5;
    this.fSegment6 = pSegment6;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("sensor".equals(parameterName)) return this.fSensor;
    if ("segment1".equals(parameterName)) return this.fSegment1;
    if ("segment2".equals(parameterName)) return this.fSegment2;
    if ("segment3".equals(parameterName)) return this.fSegment3;
    if ("segment4".equals(parameterName)) return this.fSegment4;
    if ("segment5".equals(parameterName)) return this.fSegment5;
    if ("segment6".equals(parameterName)) return this.fSegment6;
    return null;
  }
  
  public Sensor getSensor() {
    return this.fSensor;
  }
  
  public Segment getSegment1() {
    return this.fSegment1;
  }
  
  public Segment getSegment2() {
    return this.fSegment2;
  }
  
  public Segment getSegment3() {
    return this.fSegment3;
  }
  
  public Segment getSegment4() {
    return this.fSegment4;
  }
  
  public Segment getSegment5() {
    return this.fSegment5;
  }
  
  public Segment getSegment6() {
    return this.fSegment6;
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
    if ("segment2".equals(parameterName) ) {
        this.fSegment2 = (Segment) newValue;
        return true;
    }
    if ("segment3".equals(parameterName) ) {
        this.fSegment3 = (Segment) newValue;
        return true;
    }
    if ("segment4".equals(parameterName) ) {
        this.fSegment4 = (Segment) newValue;
        return true;
    }
    if ("segment5".equals(parameterName) ) {
        this.fSegment5 = (Segment) newValue;
        return true;
    }
    if ("segment6".equals(parameterName) ) {
        this.fSegment6 = (Segment) newValue;
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
  
  public void setSegment2(final Segment pSegment2) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSegment2 = pSegment2;
  }
  
  public void setSegment3(final Segment pSegment3) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSegment3 = pSegment3;
  }
  
  public void setSegment4(final Segment pSegment4) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSegment4 = pSegment4;
  }
  
  public void setSegment5(final Segment pSegment5) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSegment5 = pSegment5;
  }
  
  public void setSegment6(final Segment pSegment6) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSegment6 = pSegment6;
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.viatra.connectedSegments";
  }
  
  @Override
  public List<String> parameterNames() {
    return ConnectedSegmentsMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSensor, fSegment1, fSegment2, fSegment3, fSegment4, fSegment5, fSegment6};
  }
  
  @Override
  public ConnectedSegmentsMatch toImmutable() {
    return isMutable() ? newMatch(fSensor, fSegment1, fSegment2, fSegment3, fSegment4, fSegment5, fSegment6) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"sensor\"=" + prettyPrintValue(fSensor) + ", ");
    
    result.append("\"segment1\"=" + prettyPrintValue(fSegment1) + ", ");
    
    result.append("\"segment2\"=" + prettyPrintValue(fSegment2) + ", ");
    
    result.append("\"segment3\"=" + prettyPrintValue(fSegment3) + ", ");
    
    result.append("\"segment4\"=" + prettyPrintValue(fSegment4) + ", ");
    
    result.append("\"segment5\"=" + prettyPrintValue(fSegment5) + ", ");
    
    result.append("\"segment6\"=" + prettyPrintValue(fSegment6)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSensor == null) ? 0 : fSensor.hashCode());
    result = prime * result + ((fSegment1 == null) ? 0 : fSegment1.hashCode());
    result = prime * result + ((fSegment2 == null) ? 0 : fSegment2.hashCode());
    result = prime * result + ((fSegment3 == null) ? 0 : fSegment3.hashCode());
    result = prime * result + ((fSegment4 == null) ? 0 : fSegment4.hashCode());
    result = prime * result + ((fSegment5 == null) ? 0 : fSegment5.hashCode());
    result = prime * result + ((fSegment6 == null) ? 0 : fSegment6.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
        return true;
    if (!(obj instanceof ConnectedSegmentsMatch)) { // this should be infrequent
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
    ConnectedSegmentsMatch other = (ConnectedSegmentsMatch) obj;
    if (fSensor == null) {if (other.fSensor != null) return false;}
    else if (!fSensor.equals(other.fSensor)) return false;
    if (fSegment1 == null) {if (other.fSegment1 != null) return false;}
    else if (!fSegment1.equals(other.fSegment1)) return false;
    if (fSegment2 == null) {if (other.fSegment2 != null) return false;}
    else if (!fSegment2.equals(other.fSegment2)) return false;
    if (fSegment3 == null) {if (other.fSegment3 != null) return false;}
    else if (!fSegment3.equals(other.fSegment3)) return false;
    if (fSegment4 == null) {if (other.fSegment4 != null) return false;}
    else if (!fSegment4.equals(other.fSegment4)) return false;
    if (fSegment5 == null) {if (other.fSegment5 != null) return false;}
    else if (!fSegment5.equals(other.fSegment5)) return false;
    if (fSegment6 == null) {if (other.fSegment6 != null) return false;}
    else if (!fSegment6.equals(other.fSegment6)) return false;
    return true;
  }
  
  @Override
  public ConnectedSegmentsQuerySpecification specification() {
    try {
        return ConnectedSegmentsQuerySpecification.instance();
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
  public static ConnectedSegmentsMatch newEmptyMatch() {
    return new Mutable(null, null, null, null, null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment2 the fixed value of pattern parameter segment2, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @param pSegment4 the fixed value of pattern parameter segment4, or null if not bound.
   * @param pSegment5 the fixed value of pattern parameter segment5, or null if not bound.
   * @param pSegment6 the fixed value of pattern parameter segment6, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static ConnectedSegmentsMatch newMutableMatch(final Sensor pSensor, final Segment pSegment1, final Segment pSegment2, final Segment pSegment3, final Segment pSegment4, final Segment pSegment5, final Segment pSegment6) {
    return new Mutable(pSensor, pSegment1, pSegment2, pSegment3, pSegment4, pSegment5, pSegment6);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment2 the fixed value of pattern parameter segment2, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @param pSegment4 the fixed value of pattern parameter segment4, or null if not bound.
   * @param pSegment5 the fixed value of pattern parameter segment5, or null if not bound.
   * @param pSegment6 the fixed value of pattern parameter segment6, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static ConnectedSegmentsMatch newMatch(final Sensor pSensor, final Segment pSegment1, final Segment pSegment2, final Segment pSegment3, final Segment pSegment4, final Segment pSegment5, final Segment pSegment6) {
    return new Immutable(pSensor, pSegment1, pSegment2, pSegment3, pSegment4, pSegment5, pSegment6);
  }
  
  private static final class Mutable extends ConnectedSegmentsMatch {
    Mutable(final Sensor pSensor, final Segment pSegment1, final Segment pSegment2, final Segment pSegment3, final Segment pSegment4, final Segment pSegment5, final Segment pSegment6) {
      super(pSensor, pSegment1, pSegment2, pSegment3, pSegment4, pSegment5, pSegment6);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends ConnectedSegmentsMatch {
    Immutable(final Sensor pSensor, final Segment pSegment1, final Segment pSegment2, final Segment pSegment3, final Segment pSegment4, final Segment pSegment5, final Segment pSegment6) {
      super(pSensor, pSegment1, pSegment2, pSegment3, pSegment4, pSegment5, pSegment6);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
