package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SwitchSetQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.switchSet pattern,
 * to be used in conjunction with {@link SwitchSetMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see SwitchSetMatcher
 * @see SwitchSetProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class SwitchSetMatch extends BasePatternMatch {
  private Semaphore fSemaphore;
  
  private Route fRoute;
  
  private SwitchPosition fSwitchPosition;
  
  private Switch fSw;
  
  private static List<String> parameterNames = makeImmutableList("semaphore", "route", "switchPosition", "sw");
  
  private SwitchSetMatch(final Semaphore pSemaphore, final Route pRoute, final SwitchPosition pSwitchPosition, final Switch pSw) {
    this.fSemaphore = pSemaphore;
    this.fRoute = pRoute;
    this.fSwitchPosition = pSwitchPosition;
    this.fSw = pSw;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("semaphore".equals(parameterName)) return this.fSemaphore;
    if ("route".equals(parameterName)) return this.fRoute;
    if ("switchPosition".equals(parameterName)) return this.fSwitchPosition;
    if ("sw".equals(parameterName)) return this.fSw;
    return null;
  }
  
  public Semaphore getSemaphore() {
    return this.fSemaphore;
  }
  
  public Route getRoute() {
    return this.fRoute;
  }
  
  public SwitchPosition getSwitchPosition() {
    return this.fSwitchPosition;
  }
  
  public Switch getSw() {
    return this.fSw;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("semaphore".equals(parameterName) ) {
    	this.fSemaphore = (hu.bme.mit.trainbenchmark.railway.Semaphore) newValue;
    	return true;
    }
    if ("route".equals(parameterName) ) {
    	this.fRoute = (hu.bme.mit.trainbenchmark.railway.Route) newValue;
    	return true;
    }
    if ("switchPosition".equals(parameterName) ) {
    	this.fSwitchPosition = (hu.bme.mit.trainbenchmark.railway.SwitchPosition) newValue;
    	return true;
    }
    if ("sw".equals(parameterName) ) {
    	this.fSw = (hu.bme.mit.trainbenchmark.railway.Switch) newValue;
    	return true;
    }
    return false;
  }
  
  public void setSemaphore(final Semaphore pSemaphore) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSemaphore = pSemaphore;
  }
  
  public void setRoute(final Route pRoute) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fRoute = pRoute;
  }
  
  public void setSwitchPosition(final SwitchPosition pSwitchPosition) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSwitchPosition = pSwitchPosition;
  }
  
  public void setSw(final Switch pSw) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSw = pSw;
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.switchSet";
  }
  
  @Override
  public List<String> parameterNames() {
    return SwitchSetMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSemaphore, fRoute, fSwitchPosition, fSw};
  }
  
  @Override
  public SwitchSetMatch toImmutable() {
    return isMutable() ? newMatch(fSemaphore, fRoute, fSwitchPosition, fSw) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"semaphore\"=" + prettyPrintValue(fSemaphore) + ", ");
    
    result.append("\"route\"=" + prettyPrintValue(fRoute) + ", ");
    
    result.append("\"switchPosition\"=" + prettyPrintValue(fSwitchPosition) + ", ");
    
    result.append("\"sw\"=" + prettyPrintValue(fSw)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSemaphore == null) ? 0 : fSemaphore.hashCode());
    result = prime * result + ((fRoute == null) ? 0 : fRoute.hashCode());
    result = prime * result + ((fSwitchPosition == null) ? 0 : fSwitchPosition.hashCode());
    result = prime * result + ((fSw == null) ? 0 : fSw.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof SwitchSetMatch)) { // this should be infrequent
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
    SwitchSetMatch other = (SwitchSetMatch) obj;
    if (fSemaphore == null) {if (other.fSemaphore != null) return false;}
    else if (!fSemaphore.equals(other.fSemaphore)) return false;
    if (fRoute == null) {if (other.fRoute != null) return false;}
    else if (!fRoute.equals(other.fRoute)) return false;
    if (fSwitchPosition == null) {if (other.fSwitchPosition != null) return false;}
    else if (!fSwitchPosition.equals(other.fSwitchPosition)) return false;
    if (fSw == null) {if (other.fSw != null) return false;}
    else if (!fSw.equals(other.fSw)) return false;
    return true;
  }
  
  @Override
  public SwitchSetQuerySpecification specification() {
    try {
    	return SwitchSetQuerySpecification.instance();
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
  public static SwitchSetMatch newEmptyMatch() {
    return new Mutable(null, null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSwitchPosition the fixed value of pattern parameter switchPosition, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static SwitchSetMatch newMutableMatch(final Semaphore pSemaphore, final Route pRoute, final SwitchPosition pSwitchPosition, final Switch pSw) {
    return new Mutable(pSemaphore, pRoute, pSwitchPosition, pSw);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSwitchPosition the fixed value of pattern parameter switchPosition, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static SwitchSetMatch newMatch(final Semaphore pSemaphore, final Route pRoute, final SwitchPosition pSwitchPosition, final Switch pSw) {
    return new Immutable(pSemaphore, pRoute, pSwitchPosition, pSw);
  }
  
  private static final class Mutable extends SwitchSetMatch {
    Mutable(final Semaphore pSemaphore, final Route pRoute, final SwitchPosition pSwitchPosition, final Switch pSw) {
      super(pSemaphore, pRoute, pSwitchPosition, pSw);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends SwitchSetMatch {
    Immutable(final Semaphore pSemaphore, final Route pRoute, final SwitchPosition pSwitchPosition, final Switch pSw) {
      super(pSemaphore, pRoute, pSwitchPosition, pSw);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
