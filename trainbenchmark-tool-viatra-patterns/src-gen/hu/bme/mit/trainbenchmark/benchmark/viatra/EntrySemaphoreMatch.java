/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SemaphoreNeighbor.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.util.EntrySemaphoreQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.viatra.entrySemaphore pattern,
 * to be used in conjunction with {@link EntrySemaphoreMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see EntrySemaphoreMatcher
 * @see EntrySemaphoreProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class EntrySemaphoreMatch extends BasePatternMatch {
  private Route fRoute;
  
  private Semaphore fSemaphore;
  
  private static List<String> parameterNames = makeImmutableList("route", "semaphore");
  
  private EntrySemaphoreMatch(final Route pRoute, final Semaphore pSemaphore) {
    this.fRoute = pRoute;
    this.fSemaphore = pSemaphore;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("route".equals(parameterName)) return this.fRoute;
    if ("semaphore".equals(parameterName)) return this.fSemaphore;
    return null;
  }
  
  public Route getRoute() {
    return this.fRoute;
  }
  
  public Semaphore getSemaphore() {
    return this.fSemaphore;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("route".equals(parameterName) ) {
        this.fRoute = (Route) newValue;
        return true;
    }
    if ("semaphore".equals(parameterName) ) {
        this.fSemaphore = (Semaphore) newValue;
        return true;
    }
    return false;
  }
  
  public void setRoute(final Route pRoute) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fRoute = pRoute;
  }
  
  public void setSemaphore(final Semaphore pSemaphore) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSemaphore = pSemaphore;
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.viatra.entrySemaphore";
  }
  
  @Override
  public List<String> parameterNames() {
    return EntrySemaphoreMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fRoute, fSemaphore};
  }
  
  @Override
  public EntrySemaphoreMatch toImmutable() {
    return isMutable() ? newMatch(fRoute, fSemaphore) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"route\"=" + prettyPrintValue(fRoute) + ", ");
    
    result.append("\"semaphore\"=" + prettyPrintValue(fSemaphore)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fRoute == null) ? 0 : fRoute.hashCode());
    result = prime * result + ((fSemaphore == null) ? 0 : fSemaphore.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
        return true;
    if (!(obj instanceof EntrySemaphoreMatch)) { // this should be infrequent
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
    EntrySemaphoreMatch other = (EntrySemaphoreMatch) obj;
    if (fRoute == null) {if (other.fRoute != null) return false;}
    else if (!fRoute.equals(other.fRoute)) return false;
    if (fSemaphore == null) {if (other.fSemaphore != null) return false;}
    else if (!fSemaphore.equals(other.fSemaphore)) return false;
    return true;
  }
  
  @Override
  public EntrySemaphoreQuerySpecification specification() {
    try {
        return EntrySemaphoreQuerySpecification.instance();
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
  public static EntrySemaphoreMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static EntrySemaphoreMatch newMutableMatch(final Route pRoute, final Semaphore pSemaphore) {
    return new Mutable(pRoute, pSemaphore);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static EntrySemaphoreMatch newMatch(final Route pRoute, final Semaphore pSemaphore) {
    return new Immutable(pRoute, pSemaphore);
  }
  
  private static final class Mutable extends EntrySemaphoreMatch {
    Mutable(final Route pRoute, final Semaphore pSemaphore) {
      super(pRoute, pSemaphore);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends EntrySemaphoreMatch {
    Immutable(final Route pRoute, final Semaphore pSemaphore) {
      super(pRoute, pSemaphore);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
