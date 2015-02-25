package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SensorTrackElementQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.sensorTrackElement pattern,
 * to be used in conjunction with {@link SensorTrackElementMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see SensorTrackElementMatcher
 * @see SensorTrackElementProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class SensorTrackElementMatch extends BasePatternMatch {
  private Sensor fSen;
  
  private TrackElement fTe;
  
  private static List<String> parameterNames = makeImmutableList("sen", "te");
  
  private SensorTrackElementMatch(final Sensor pSen, final TrackElement pTe) {
    this.fSen = pSen;
    this.fTe = pTe;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("sen".equals(parameterName)) return this.fSen;
    if ("te".equals(parameterName)) return this.fTe;
    return null;
  }
  
  public Sensor getSen() {
    return this.fSen;
  }
  
  public TrackElement getTe() {
    return this.fTe;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("sen".equals(parameterName) ) {
    	this.fSen = (hu.bme.mit.trainbenchmark.railway.Sensor) newValue;
    	return true;
    }
    if ("te".equals(parameterName) ) {
    	this.fTe = (hu.bme.mit.trainbenchmark.railway.TrackElement) newValue;
    	return true;
    }
    return false;
  }
  
  public void setSen(final Sensor pSen) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSen = pSen;
  }
  
  public void setTe(final TrackElement pTe) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTe = pTe;
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.sensorTrackElement";
  }
  
  @Override
  public List<String> parameterNames() {
    return SensorTrackElementMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSen, fTe};
  }
  
  @Override
  public SensorTrackElementMatch toImmutable() {
    return isMutable() ? newMatch(fSen, fTe) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"sen\"=" + prettyPrintValue(fSen) + ", ");
    
    result.append("\"te\"=" + prettyPrintValue(fTe)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSen == null) ? 0 : fSen.hashCode());
    result = prime * result + ((fTe == null) ? 0 : fTe.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof SensorTrackElementMatch)) { // this should be infrequent
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
    SensorTrackElementMatch other = (SensorTrackElementMatch) obj;
    if (fSen == null) {if (other.fSen != null) return false;}
    else if (!fSen.equals(other.fSen)) return false;
    if (fTe == null) {if (other.fTe != null) return false;}
    else if (!fTe.equals(other.fTe)) return false;
    return true;
  }
  
  @Override
  public SensorTrackElementQuerySpecification specification() {
    try {
    	return SensorTrackElementQuerySpecification.instance();
    } catch (IncQueryException ex) {
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
  public static SensorTrackElementMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSen the fixed value of pattern parameter sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter te, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static SensorTrackElementMatch newMutableMatch(final Sensor pSen, final TrackElement pTe) {
    return new Mutable(pSen, pTe);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSen the fixed value of pattern parameter sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter te, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static SensorTrackElementMatch newMatch(final Sensor pSen, final TrackElement pTe) {
    return new Immutable(pSen, pTe);
  }
  
  private static final class Mutable extends SensorTrackElementMatch {
    Mutable(final Sensor pSen, final TrackElement pTe) {
      super(pSen, pTe);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends SensorTrackElementMatch {
    Immutable(final Sensor pSen, final TrackElement pTe) {
      super(pSen, pTe);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
