package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Route;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SignalNeighborQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.signalNeighbor pattern,
 * to be used in conjunction with {@link SignalNeighborMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see SignalNeighborMatcher
 * @see SignalNeighborProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class SignalNeighborMatch extends BasePatternMatch {
  private Route fR1;
  
  private static List<String> parameterNames = makeImmutableList("R1");
  
  private SignalNeighborMatch(final Route pR1) {
    this.fR1 = pR1;
    
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("R1".equals(parameterName)) return this.fR1;
    return null;
    
  }
  
  public Route getR1() {
    return this.fR1;
    
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("R1".equals(parameterName) ) {
    	this.fR1 = (Concept.Route) newValue;
    	return true;
    }
    return false;
    
  }
  
  public void setR1(final Route pR1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fR1 = pR1;
    
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.signalNeighbor";
    
  }
  
  @Override
  public List<String> parameterNames() {
    return SignalNeighborMatch.parameterNames;
    
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fR1};
    
  }
  
  @Override
  public SignalNeighborMatch toImmutable() {
    return isMutable() ? newMatch(fR1) : this;
    
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"R1\"=" + prettyPrintValue(fR1));
    return result.toString();
    
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fR1 == null) ? 0 : fR1.hashCode());
    return result;
    
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof SignalNeighborMatch)) { // this should be infrequent
    	if (obj == null)
    		return false;
    	if (!(obj instanceof IPatternMatch))
    		return false;
    	IPatternMatch otherSig  = (IPatternMatch) obj;
    	if (!specification().equals(otherSig.specification()))
    		return false;
    	return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    SignalNeighborMatch other = (SignalNeighborMatch) obj;
    if (fR1 == null) {if (other.fR1 != null) return false;}
    else if (!fR1.equals(other.fR1)) return false;
    return true;
  }
  
  @Override
  public SignalNeighborQuerySpecification specification() {
    try {
    	return SignalNeighborQuerySpecification.instance();
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
  public static SignalNeighborMatch newEmptyMatch() {
    return new Mutable(null);
    
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pR1 the fixed value of pattern parameter R1, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static SignalNeighborMatch newMutableMatch(final Route pR1) {
    return new Mutable(pR1);
    
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pR1 the fixed value of pattern parameter R1, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static SignalNeighborMatch newMatch(final Route pR1) {
    return new Immutable(pR1);
    
  }
  
  private static final class Mutable extends SignalNeighborMatch {
    Mutable(final Route pR1) {
      super(pR1);
      
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends SignalNeighborMatch {
    Immutable(final Route pR1) {
      super(pR1);
      
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
