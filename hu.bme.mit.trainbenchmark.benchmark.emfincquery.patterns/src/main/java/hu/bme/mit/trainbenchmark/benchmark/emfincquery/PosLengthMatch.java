package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Segment;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.PosLengthQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.posLength pattern,
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
  private Segment fSource;
  
  private Integer fTarget;
  
  private static List<String> parameterNames = makeImmutableList("Source", "Target");
  
  private PosLengthMatch(final Segment pSource, final Integer pTarget) {
    this.fSource = pSource;
    this.fTarget = pTarget;
    
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("Source".equals(parameterName)) return this.fSource;
    if ("Target".equals(parameterName)) return this.fTarget;
    return null;
    
  }
  
  public Segment getSource() {
    return this.fSource;
    
  }
  
  public Integer getTarget() {
    return this.fTarget;
    
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("Source".equals(parameterName) ) {
    	this.fSource = (Concept.Segment) newValue;
    	return true;
    }
    if ("Target".equals(parameterName) ) {
    	this.fTarget = (java.lang.Integer) newValue;
    	return true;
    }
    return false;
    
  }
  
  public void setSource(final Segment pSource) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSource = pSource;
    
  }
  
  public void setTarget(final Integer pTarget) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTarget = pTarget;
    
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.posLength";
    
  }
  
  @Override
  public List<String> parameterNames() {
    return PosLengthMatch.parameterNames;
    
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSource, fTarget};
    
  }
  
  @Override
  public PosLengthMatch toImmutable() {
    return isMutable() ? newMatch(fSource, fTarget) : this;
    
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"Source\"=" + prettyPrintValue(fSource) + ", ");
    result.append("\"Target\"=" + prettyPrintValue(fTarget));
    return result.toString();
    
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSource == null) ? 0 : fSource.hashCode());
    result = prime * result + ((fTarget == null) ? 0 : fTarget.hashCode());
    return result;
    
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof PosLengthMatch)) { // this should be infrequent
    	if (obj == null)
    		return false;
    	if (!(obj instanceof IPatternMatch))
    		return false;
    	IPatternMatch otherSig  = (IPatternMatch) obj;
    	if (!specification().equals(otherSig.specification()))
    		return false;
    	return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    PosLengthMatch other = (PosLengthMatch) obj;
    if (fSource == null) {if (other.fSource != null) return false;}
    else if (!fSource.equals(other.fSource)) return false;
    if (fTarget == null) {if (other.fTarget != null) return false;}
    else if (!fTarget.equals(other.fTarget)) return false;
    return true;
  }
  
  @Override
  public PosLengthQuerySpecification specification() {
    try {
    	return PosLengthQuerySpecification.instance();
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
  public static PosLengthMatch newEmptyMatch() {
    return new Mutable(null, null);
    
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSource the fixed value of pattern parameter Source, or null if not bound.
   * @param pTarget the fixed value of pattern parameter Target, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static PosLengthMatch newMutableMatch(final Segment pSource, final Integer pTarget) {
    return new Mutable(pSource, pTarget);
    
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSource the fixed value of pattern parameter Source, or null if not bound.
   * @param pTarget the fixed value of pattern parameter Target, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static PosLengthMatch newMatch(final Segment pSource, final Integer pTarget) {
    return new Immutable(pSource, pTarget);
    
  }
  
  private static final class Mutable extends PosLengthMatch {
    Mutable(final Segment pSource, final Integer pTarget) {
      super(pSource, pTarget);
      
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends PosLengthMatch {
    Immutable(final Segment pSource, final Integer pTarget) {
      super(pSource, pTarget);
      
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
