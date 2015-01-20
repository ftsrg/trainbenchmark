package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Trackelement;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.TrackelementConnectedQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.trackelementConnected pattern,
 * to be used in conjunction with {@link TrackelementConnectedMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see TrackelementConnectedMatcher
 * @see TrackelementConnectedProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class TrackelementConnectedMatch extends BasePatternMatch {
  private Trackelement fTe1;
  
  private Trackelement fTe2;
  
  private static List<String> parameterNames = makeImmutableList("Te1", "Te2");
  
  private TrackelementConnectedMatch(final Trackelement pTe1, final Trackelement pTe2) {
    this.fTe1 = pTe1;
    this.fTe2 = pTe2;
    
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("Te1".equals(parameterName)) return this.fTe1;
    if ("Te2".equals(parameterName)) return this.fTe2;
    return null;
    
  }
  
  public Trackelement getTe1() {
    return this.fTe1;
    
  }
  
  public Trackelement getTe2() {
    return this.fTe2;
    
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("Te1".equals(parameterName) ) {
    	this.fTe1 = (Concept.Trackelement) newValue;
    	return true;
    }
    if ("Te2".equals(parameterName) ) {
    	this.fTe2 = (Concept.Trackelement) newValue;
    	return true;
    }
    return false;
    
  }
  
  public void setTe1(final Trackelement pTe1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTe1 = pTe1;
    
  }
  
  public void setTe2(final Trackelement pTe2) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTe2 = pTe2;
    
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.trackelementConnected";
    
  }
  
  @Override
  public List<String> parameterNames() {
    return TrackelementConnectedMatch.parameterNames;
    
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fTe1, fTe2};
    
  }
  
  @Override
  public TrackelementConnectedMatch toImmutable() {
    return isMutable() ? newMatch(fTe1, fTe2) : this;
    
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"Te1\"=" + prettyPrintValue(fTe1) + ", ");
    result.append("\"Te2\"=" + prettyPrintValue(fTe2));
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
    if (!(obj instanceof TrackelementConnectedMatch)) { // this should be infrequent
    	if (obj == null)
    		return false;
    	if (!(obj instanceof IPatternMatch))
    		return false;
    	IPatternMatch otherSig  = (IPatternMatch) obj;
    	if (!specification().equals(otherSig.specification()))
    		return false;
    	return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    TrackelementConnectedMatch other = (TrackelementConnectedMatch) obj;
    if (fTe1 == null) {if (other.fTe1 != null) return false;}
    else if (!fTe1.equals(other.fTe1)) return false;
    if (fTe2 == null) {if (other.fTe2 != null) return false;}
    else if (!fTe2.equals(other.fTe2)) return false;
    return true;
  }
  
  @Override
  public TrackelementConnectedQuerySpecification specification() {
    try {
    	return TrackelementConnectedQuerySpecification.instance();
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
  public static TrackelementConnectedMatch newEmptyMatch() {
    return new Mutable(null, null);
    
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pTe1 the fixed value of pattern parameter Te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter Te2, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static TrackelementConnectedMatch newMutableMatch(final Trackelement pTe1, final Trackelement pTe2) {
    return new Mutable(pTe1, pTe2);
    
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pTe1 the fixed value of pattern parameter Te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter Te2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static TrackelementConnectedMatch newMatch(final Trackelement pTe1, final Trackelement pTe2) {
    return new Immutable(pTe1, pTe2);
    
  }
  
  private static final class Mutable extends TrackelementConnectedMatch {
    Mutable(final Trackelement pTe1, final Trackelement pTe2) {
      super(pTe1, pTe2);
      
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends TrackelementConnectedMatch {
    Immutable(final Trackelement pTe1, final Trackelement pTe2) {
      super(pTe1, pTe2);
      
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
