package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ExitSignalQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Signal;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.exitSignal pattern,
 * to be used in conjunction with {@link ExitSignalMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see ExitSignalMatcher
 * @see ExitSignalProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class ExitSignalMatch extends BasePatternMatch {
  private Route fRoute;
  
  private Signal fSignal;
  
  private static List<String> parameterNames = makeImmutableList("route", "signal");
  
  private ExitSignalMatch(final Route pRoute, final Signal pSignal) {
    this.fRoute = pRoute;
    this.fSignal = pSignal;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("route".equals(parameterName)) return this.fRoute;
    if ("signal".equals(parameterName)) return this.fSignal;
    return null;
  }
  
  public Route getRoute() {
    return this.fRoute;
  }
  
  public Signal getSignal() {
    return this.fSignal;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("route".equals(parameterName) ) {
    	this.fRoute = (hu.bme.mit.trainbenchmark.railway.Route) newValue;
    	return true;
    }
    if ("signal".equals(parameterName) ) {
    	this.fSignal = (hu.bme.mit.trainbenchmark.railway.Signal) newValue;
    	return true;
    }
    return false;
  }
  
  public void setRoute(final Route pRoute) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fRoute = pRoute;
  }
  
  public void setSignal(final Signal pSignal) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSignal = pSignal;
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.exitSignal";
  }
  
  @Override
  public List<String> parameterNames() {
    return ExitSignalMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fRoute, fSignal};
  }
  
  @Override
  public ExitSignalMatch toImmutable() {
    return isMutable() ? newMatch(fRoute, fSignal) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"route\"=" + prettyPrintValue(fRoute) + ", ");
    
    result.append("\"signal\"=" + prettyPrintValue(fSignal)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fRoute == null) ? 0 : fRoute.hashCode());
    result = prime * result + ((fSignal == null) ? 0 : fSignal.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof ExitSignalMatch)) { // this should be infrequent
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
    ExitSignalMatch other = (ExitSignalMatch) obj;
    if (fRoute == null) {if (other.fRoute != null) return false;}
    else if (!fRoute.equals(other.fRoute)) return false;
    if (fSignal == null) {if (other.fSignal != null) return false;}
    else if (!fSignal.equals(other.fSignal)) return false;
    return true;
  }
  
  @Override
  public ExitSignalQuerySpecification specification() {
    try {
    	return ExitSignalQuerySpecification.instance();
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
  public static ExitSignalMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSignal the fixed value of pattern parameter signal, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static ExitSignalMatch newMutableMatch(final Route pRoute, final Signal pSignal) {
    return new Mutable(pRoute, pSignal);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSignal the fixed value of pattern parameter signal, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static ExitSignalMatch newMatch(final Route pRoute, final Signal pSignal) {
    return new Immutable(pRoute, pSignal);
  }
  
  private static final class Mutable extends ExitSignalMatch {
    Mutable(final Route pRoute, final Signal pSignal) {
      super(pRoute, pSignal);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends ExitSignalMatch {
    Immutable(final Route pRoute, final Signal pSignal) {
      super(pRoute, pSignal);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
