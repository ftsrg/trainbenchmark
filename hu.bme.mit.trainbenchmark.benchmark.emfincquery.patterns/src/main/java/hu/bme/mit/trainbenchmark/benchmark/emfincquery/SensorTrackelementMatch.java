package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Sensor;
import Concept.Trackelement;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SensorTrackelementQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.sensorTrackelement pattern,
 * to be used in conjunction with {@link SensorTrackelementMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see SensorTrackelementMatcher
 * @see SensorTrackelementProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class SensorTrackelementMatch extends BasePatternMatch {
  private Sensor fSen;
  
  private Trackelement fTe;
  
  private static List<String> parameterNames = makeImmutableList("Sen", "Te");
  
  private SensorTrackelementMatch(final Sensor pSen, final Trackelement pTe) {
    this.fSen = pSen;
    this.fTe = pTe;
    
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("Sen".equals(parameterName)) return this.fSen;
    if ("Te".equals(parameterName)) return this.fTe;
    return null;
    
  }
  
  public Sensor getSen() {
    return this.fSen;
    
  }
  
  public Trackelement getTe() {
    return this.fTe;
    
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("Sen".equals(parameterName) ) {
    	this.fSen = (Concept.Sensor) newValue;
    	return true;
    }
    if ("Te".equals(parameterName) ) {
    	this.fTe = (Concept.Trackelement) newValue;
    	return true;
    }
    return false;
    
  }
  
  public void setSen(final Sensor pSen) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSen = pSen;
    
  }
  
  public void setTe(final Trackelement pTe) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTe = pTe;
    
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.sensorTrackelement";
    
  }
  
  @Override
  public List<String> parameterNames() {
    return SensorTrackelementMatch.parameterNames;
    
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSen, fTe};
    
  }
  
  @Override
  public SensorTrackelementMatch toImmutable() {
    return isMutable() ? newMatch(fSen, fTe) : this;
    
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"Sen\"=" + prettyPrintValue(fSen) + ", ");
    result.append("\"Te\"=" + prettyPrintValue(fTe));
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
    if (!(obj instanceof SensorTrackelementMatch)) { // this should be infrequent
    	if (obj == null)
    		return false;
    	if (!(obj instanceof IPatternMatch))
    		return false;
    	IPatternMatch otherSig  = (IPatternMatch) obj;
    	if (!specification().equals(otherSig.specification()))
    		return false;
    	return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    SensorTrackelementMatch other = (SensorTrackelementMatch) obj;
    if (fSen == null) {if (other.fSen != null) return false;}
    else if (!fSen.equals(other.fSen)) return false;
    if (fTe == null) {if (other.fTe != null) return false;}
    else if (!fTe.equals(other.fTe)) return false;
    return true;
  }
  
  @Override
  public SensorTrackelementQuerySpecification specification() {
    try {
    	return SensorTrackelementQuerySpecification.instance();
    } catch (IncQueryException ex) {
     	// This cannot happen, as the match object can only be instantiated if the query specification exists
     	throw new IllegalStateException	(ex);
    }
    
  }
  
  /**
   * Returns an empty, mutable match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @return the empty match.
   * 
   */
  public static SensorTrackelementMatch newEmptyMatch() {
    return new Mutable(null, null);
    
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter Te, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static SensorTrackelementMatch newMutableMatch(final Sensor pSen, final Trackelement pTe) {
    return new Mutable(pSen, pTe);
    
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter Te, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static SensorTrackelementMatch newMatch(final Sensor pSen, final Trackelement pTe) {
    return new Immutable(pSen, pTe);
    
  }
  
  private static final class Mutable extends SensorTrackelementMatch {
    Mutable(final Sensor pSen, final Trackelement pTe) {
      super(pSen, pTe);
      
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends SensorTrackelementMatch {
    Immutable(final Sensor pSen, final Trackelement pTe) {
      super(pSen, pTe);
      
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
