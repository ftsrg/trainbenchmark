package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Switch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SwitchSensorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.switchSensor pattern,
 * to be used in conjunction with {@link SwitchSensorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see SwitchSensorMatcher
 * @see SwitchSensorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class SwitchSensorMatch extends BasePatternMatch {
  private Switch fIndividual;
  
  private static List<String> parameterNames = makeImmutableList("Individual");
  
  private SwitchSensorMatch(final Switch pIndividual) {
    this.fIndividual = pIndividual;
    
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("Individual".equals(parameterName)) return this.fIndividual;
    return null;
    
  }
  
  public Switch getIndividual() {
    return this.fIndividual;
    
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("Individual".equals(parameterName) ) {
    	this.fIndividual = (Concept.Switch) newValue;
    	return true;
    }
    return false;
    
  }
  
  public void setIndividual(final Switch pIndividual) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fIndividual = pIndividual;
    
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.switchSensor";
    
  }
  
  @Override
  public List<String> parameterNames() {
    return SwitchSensorMatch.parameterNames;
    
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fIndividual};
    
  }
  
  @Override
  public SwitchSensorMatch toImmutable() {
    return isMutable() ? newMatch(fIndividual) : this;
    
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"Individual\"=" + prettyPrintValue(fIndividual));
    return result.toString();
    
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fIndividual == null) ? 0 : fIndividual.hashCode());
    return result;
    
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof SwitchSensorMatch)) { // this should be infrequent
    	if (obj == null)
    		return false;
    	if (!(obj instanceof IPatternMatch))
    		return false;
    	IPatternMatch otherSig  = (IPatternMatch) obj;
    	if (!specification().equals(otherSig.specification()))
    		return false;
    	return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    SwitchSensorMatch other = (SwitchSensorMatch) obj;
    if (fIndividual == null) {if (other.fIndividual != null) return false;}
    else if (!fIndividual.equals(other.fIndividual)) return false;
    return true;
  }
  
  @Override
  public SwitchSensorQuerySpecification specification() {
    try {
    	return SwitchSensorQuerySpecification.instance();
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
  public static SwitchSensorMatch newEmptyMatch() {
    return new Mutable(null);
    
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pIndividual the fixed value of pattern parameter Individual, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static SwitchSensorMatch newMutableMatch(final Switch pIndividual) {
    return new Mutable(pIndividual);
    
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pIndividual the fixed value of pattern parameter Individual, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static SwitchSensorMatch newMatch(final Switch pIndividual) {
    return new Immutable(pIndividual);
    
  }
  
  private static final class Mutable extends SwitchSensorMatch {
    Mutable(final Switch pIndividual) {
      super(pIndividual);
      
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends SwitchSensorMatch {
    Immutable(final Switch pIndividual) {
      super(pIndividual);
      
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
