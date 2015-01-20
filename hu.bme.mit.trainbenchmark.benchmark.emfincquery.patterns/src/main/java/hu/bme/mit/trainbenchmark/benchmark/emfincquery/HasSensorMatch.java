package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Trackelement;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.HasSensorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.hasSensor pattern,
 * to be used in conjunction with {@link HasSensorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see HasSensorMatcher
 * @see HasSensorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class HasSensorMatch extends BasePatternMatch {
  private Trackelement fTrackelement;
  
  private static List<String> parameterNames = makeImmutableList("Trackelement");
  
  private HasSensorMatch(final Trackelement pTrackelement) {
    this.fTrackelement = pTrackelement;
    
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("Trackelement".equals(parameterName)) return this.fTrackelement;
    return null;
    
  }
  
  public Trackelement getTrackelement() {
    return this.fTrackelement;
    
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("Trackelement".equals(parameterName) ) {
    	this.fTrackelement = (Concept.Trackelement) newValue;
    	return true;
    }
    return false;
    
  }
  
  public void setTrackelement(final Trackelement pTrackelement) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTrackelement = pTrackelement;
    
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.hasSensor";
    
  }
  
  @Override
  public List<String> parameterNames() {
    return HasSensorMatch.parameterNames;
    
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fTrackelement};
    
  }
  
  @Override
  public HasSensorMatch toImmutable() {
    return isMutable() ? newMatch(fTrackelement) : this;
    
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"Trackelement\"=" + prettyPrintValue(fTrackelement));
    return result.toString();
    
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fTrackelement == null) ? 0 : fTrackelement.hashCode());
    return result;
    
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof HasSensorMatch)) { // this should be infrequent
    	if (obj == null)
    		return false;
    	if (!(obj instanceof IPatternMatch))
    		return false;
    	IPatternMatch otherSig  = (IPatternMatch) obj;
    	if (!specification().equals(otherSig.specification()))
    		return false;
    	return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    HasSensorMatch other = (HasSensorMatch) obj;
    if (fTrackelement == null) {if (other.fTrackelement != null) return false;}
    else if (!fTrackelement.equals(other.fTrackelement)) return false;
    return true;
  }
  
  @Override
  public HasSensorQuerySpecification specification() {
    try {
    	return HasSensorQuerySpecification.instance();
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
  public static HasSensorMatch newEmptyMatch() {
    return new Mutable(null);
    
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pTrackelement the fixed value of pattern parameter Trackelement, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static HasSensorMatch newMutableMatch(final Trackelement pTrackelement) {
    return new Mutable(pTrackelement);
    
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pTrackelement the fixed value of pattern parameter Trackelement, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static HasSensorMatch newMatch(final Trackelement pTrackelement) {
    return new Immutable(pTrackelement);
    
  }
  
  private static final class Mutable extends HasSensorMatch {
    Mutable(final Trackelement pTrackelement) {
      super(pTrackelement);
      
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends HasSensorMatch {
    Immutable(final Trackelement pTrackelement) {
      super(pTrackelement);
      
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
