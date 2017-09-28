/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SwitchMonitored.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.util.HasSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.TrackElement;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.viatra.hasSensor pattern,
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
  private TrackElement fSw;
  
  private static List<String> parameterNames = makeImmutableList("sw");
  
  private HasSensorMatch(final TrackElement pSw) {
    this.fSw = pSw;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("sw".equals(parameterName)) return this.fSw;
    return null;
  }
  
  public TrackElement getSw() {
    return this.fSw;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("sw".equals(parameterName) ) {
        this.fSw = (TrackElement) newValue;
        return true;
    }
    return false;
  }
  
  public void setSw(final TrackElement pSw) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSw = pSw;
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.viatra.hasSensor";
  }
  
  @Override
  public List<String> parameterNames() {
    return HasSensorMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSw};
  }
  
  @Override
  public HasSensorMatch toImmutable() {
    return isMutable() ? newMatch(fSw) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"sw\"=" + prettyPrintValue(fSw)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSw == null) ? 0 : fSw.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
        return true;
    if (!(obj instanceof HasSensorMatch)) { // this should be infrequent
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
    HasSensorMatch other = (HasSensorMatch) obj;
    if (fSw == null) {if (other.fSw != null) return false;}
    else if (!fSw.equals(other.fSw)) return false;
    return true;
  }
  
  @Override
  public HasSensorQuerySpecification specification() {
    try {
        return HasSensorQuerySpecification.instance();
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
  public static HasSensorMatch newEmptyMatch() {
    return new Mutable(null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static HasSensorMatch newMutableMatch(final TrackElement pSw) {
    return new Mutable(pSw);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static HasSensorMatch newMatch(final TrackElement pSw) {
    return new Immutable(pSw);
  }
  
  private static final class Mutable extends HasSensorMatch {
    Mutable(final TrackElement pSw) {
      super(pSw);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends HasSensorMatch {
    Immutable(final TrackElement pSw) {
      super(pSw);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
