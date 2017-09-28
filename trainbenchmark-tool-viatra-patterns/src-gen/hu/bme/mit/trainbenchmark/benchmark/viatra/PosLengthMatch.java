/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/PosLength.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.util.PosLengthQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Segment;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.viatra.posLength pattern,
 * to be used in conjunction with {@link PosLengthMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see PosLengthMatcher
 * @see PosLengthProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class PosLengthMatch extends BasePatternMatch {
  private Segment fSegment;
  
  private static List<String> parameterNames = makeImmutableList("segment");
  
  private PosLengthMatch(final Segment pSegment) {
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
    return "hu.bme.mit.trainbenchmark.benchmark.viatra.posLength";
  }
  
  @Override
  public List<String> parameterNames() {
    return PosLengthMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSegment};
  }
  
  @Override
  public PosLengthMatch toImmutable() {
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
    if (!(obj instanceof PosLengthMatch)) { // this should be infrequent
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
    PosLengthMatch other = (PosLengthMatch) obj;
    if (fSegment == null) {if (other.fSegment != null) return false;}
    else if (!fSegment.equals(other.fSegment)) return false;
    return true;
  }
  
  @Override
  public PosLengthQuerySpecification specification() {
    try {
        return PosLengthQuerySpecification.instance();
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
  public static PosLengthMatch newEmptyMatch() {
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
  public static PosLengthMatch newMutableMatch(final Segment pSegment) {
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
  public static PosLengthMatch newMatch(final Segment pSegment) {
    return new Immutable(pSegment);
  }
  
  private static final class Mutable extends PosLengthMatch {
    Mutable(final Segment pSegment) {
      super(pSegment);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends PosLengthMatch {
    Immutable(final Segment pSegment) {
      super(pSegment);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
