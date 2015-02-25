package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.TrackElementConnectedQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.TrackElement;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.trackElementConnected pattern,
 * to be used in conjunction with {@link TrackElementConnectedMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see TrackElementConnectedMatcher
 * @see TrackElementConnectedProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class TrackElementConnectedMatch extends BasePatternMatch {
  private TrackElement fTe1;
  
  private TrackElement fTe2;
  
  private static List<String> parameterNames = makeImmutableList("te1", "te2");
  
  private TrackElementConnectedMatch(final TrackElement pTe1, final TrackElement pTe2) {
    this.fTe1 = pTe1;
    this.fTe2 = pTe2;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("te1".equals(parameterName)) return this.fTe1;
    if ("te2".equals(parameterName)) return this.fTe2;
    return null;
  }
  
  public TrackElement getTe1() {
    return this.fTe1;
  }
  
  public TrackElement getTe2() {
    return this.fTe2;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("te1".equals(parameterName) ) {
    	this.fTe1 = (hu.bme.mit.trainbenchmark.railway.TrackElement) newValue;
    	return true;
    }
    if ("te2".equals(parameterName) ) {
    	this.fTe2 = (hu.bme.mit.trainbenchmark.railway.TrackElement) newValue;
    	return true;
    }
    return false;
  }
  
  public void setTe1(final TrackElement pTe1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTe1 = pTe1;
  }
  
  public void setTe2(final TrackElement pTe2) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTe2 = pTe2;
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.trackElementConnected";
  }
  
  @Override
  public List<String> parameterNames() {
    return TrackElementConnectedMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fTe1, fTe2};
  }
  
  @Override
  public TrackElementConnectedMatch toImmutable() {
    return isMutable() ? newMatch(fTe1, fTe2) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"te1\"=" + prettyPrintValue(fTe1) + ", ");
    
    result.append("\"te2\"=" + prettyPrintValue(fTe2)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fTe1 == null) ? 0 : fTe1.hashCode());
    result = prime * result + ((fTe2 == null) ? 0 : fTe2.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof TrackElementConnectedMatch)) { // this should be infrequent
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
    TrackElementConnectedMatch other = (TrackElementConnectedMatch) obj;
    if (fTe1 == null) {if (other.fTe1 != null) return false;}
    else if (!fTe1.equals(other.fTe1)) return false;
    if (fTe2 == null) {if (other.fTe2 != null) return false;}
    else if (!fTe2.equals(other.fTe2)) return false;
    return true;
  }
  
  @Override
  public TrackElementConnectedQuerySpecification specification() {
    try {
    	return TrackElementConnectedQuerySpecification.instance();
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
  public static TrackElementConnectedMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static TrackElementConnectedMatch newMutableMatch(final TrackElement pTe1, final TrackElement pTe2) {
    return new Mutable(pTe1, pTe2);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static TrackElementConnectedMatch newMatch(final TrackElement pTe1, final TrackElement pTe2) {
    return new Immutable(pTe1, pTe2);
  }
  
  private static final class Mutable extends TrackElementConnectedMatch {
    Mutable(final TrackElement pTe1, final TrackElement pTe2) {
      super(pTe1, pTe2);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends TrackElementConnectedMatch {
    Immutable(final TrackElement pTe1, final TrackElement pTe2) {
      super(pTe1, pTe2);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
