package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SignalNeighborQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.SignalNeighbor pattern,
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
  private Route fRoute1;
  
  private static List<String> parameterNames = makeImmutableList("route1");
  
  private SignalNeighborMatch(final Route pRoute1) {
    this.fRoute1 = pRoute1;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("route1".equals(parameterName)) return this.fRoute1;
    return null;
  }
  
  public Route getRoute1() {
    return this.fRoute1;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("route1".equals(parameterName) ) {
    	this.fRoute1 = (hu.bme.mit.trainbenchmark.railway.Route) newValue;
    	return true;
    }
    return false;
  }
  
  public void setRoute1(final Route pRoute1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fRoute1 = pRoute1;
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.SignalNeighbor";
  }
  
  @Override
  public List<String> parameterNames() {
    return SignalNeighborMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fRoute1};
  }
  
  @Override
  public SignalNeighborMatch toImmutable() {
    return isMutable() ? newMatch(fRoute1) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"route1\"=" + prettyPrintValue(fRoute1)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fRoute1 == null) ? 0 : fRoute1.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof SignalNeighborMatch)) { // this should be infrequent
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
    SignalNeighborMatch other = (SignalNeighborMatch) obj;
    if (fRoute1 == null) {if (other.fRoute1 != null) return false;}
    else if (!fRoute1.equals(other.fRoute1)) return false;
    return true;
  }
  
  @Override
  public SignalNeighborQuerySpecification specification() {
    try {
    	return SignalNeighborQuerySpecification.instance();
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
  public static SignalNeighborMatch newEmptyMatch() {
    return new Mutable(null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static SignalNeighborMatch newMutableMatch(final Route pRoute1) {
    return new Mutable(pRoute1);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static SignalNeighborMatch newMatch(final Route pRoute1) {
    return new Immutable(pRoute1);
  }
  
  private static final class Mutable extends SignalNeighborMatch {
    Mutable(final Route pRoute1) {
      super(pRoute1);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends SignalNeighborMatch {
    Immutable(final Route pRoute1) {
      super(pRoute1);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
