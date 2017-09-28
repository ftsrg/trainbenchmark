/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/PosLengthInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.util.PosLengthInjectQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Segment;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.viatra.posLengthInject pattern,
 * to be used in conjunction with {@link PosLengthInjectMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see PosLengthInjectMatcher
 * @see PosLengthInjectProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class PosLengthInjectMatch extends BasePatternMatch {
  private Segment fSegment;
  
  private static List<String> parameterNames = makeImmutableList("segment");
  
  private PosLengthInjectMatch(final Segment pSegment) {
    this.fSegment = pSegment;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("segment".equals(parameterName)) return this.fSegment;
    return null;
  }
  
  public Segment getSegment() {
    return this.fSegment;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("segment".equals(parameterName) ) {
        this.fSegment = (Segment) newValue;
        return true;
    }
    return false;
  }
  
  public void setSegment(final Segment pSegment) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSegment = pSegment;
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.viatra.posLengthInject";
  }
  
  @Override
  public List<String> parameterNames() {
    return PosLengthInjectMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSegment};
  }
  
  @Override
  public PosLengthInjectMatch toImmutable() {
    return isMutable() ? newMatch(fSegment) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"segment\"=" + prettyPrintValue(fSegment)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSegment == null) ? 0 : fSegment.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
        return true;
    if (!(obj instanceof PosLengthInjectMatch)) { // this should be infrequent
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
    PosLengthInjectMatch other = (PosLengthInjectMatch) obj;
    if (fSegment == null) {if (other.fSegment != null) return false;}
    else if (!fSegment.equals(other.fSegment)) return false;
    return true;
  }
  
  @Override
  public PosLengthInjectQuerySpecification specification() {
    try {
        return PosLengthInjectQuerySpecification.instance();
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
  public static PosLengthInjectMatch newEmptyMatch() {
    return new Mutable(null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSegment the fixed value of pattern parameter segment, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static PosLengthInjectMatch newMutableMatch(final Segment pSegment) {
    return new Mutable(pSegment);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSegment the fixed value of pattern parameter segment, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static PosLengthInjectMatch newMatch(final Segment pSegment) {
    return new Immutable(pSegment);
  }
  
  private static final class Mutable extends PosLengthInjectMatch {
    Mutable(final Segment pSegment) {
      super(pSegment);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends PosLengthInjectMatch {
    Immutable(final Segment pSegment) {
      super(pSegment);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
