package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Route;
import Concept.Sensor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.HeadQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.head pattern,
 * to be used in conjunction with {@link HeadMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see HeadMatcher
 * @see HeadProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class HeadMatch extends BasePatternMatch {
  private Sensor fSen;
  
  private Route fR;
  
  private static List<String> parameterNames = makeImmutableList("Sen", "R");
  
  private HeadMatch(final Sensor pSen, final Route pR) {
    this.fSen = pSen;
    this.fR = pR;
    
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("Sen".equals(parameterName)) return this.fSen;
    if ("R".equals(parameterName)) return this.fR;
    return null;
    
  }
  
  public Sensor getSen() {
    return this.fSen;
    
  }
  
  public Route getR() {
    return this.fR;
    
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("Sen".equals(parameterName) ) {
    	this.fSen = (Concept.Sensor) newValue;
    	return true;
    }
    if ("R".equals(parameterName) ) {
    	this.fR = (Concept.Route) newValue;
    	return true;
    }
    return false;
    
  }
  
  public void setSen(final Sensor pSen) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSen = pSen;
    
  }
  
  public void setR(final Route pR) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fR = pR;
    
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.head";
    
  }
  
  @Override
  public List<String> parameterNames() {
    return HeadMatch.parameterNames;
    
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSen, fR};
    
  }
  
  @Override
  public HeadMatch toImmutable() {
    return isMutable() ? newMatch(fSen, fR) : this;
    
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"Sen\"=" + prettyPrintValue(fSen) + ", ");
    result.append("\"R\"=" + prettyPrintValue(fR));
    return result.toString();
    
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSen == null) ? 0 : fSen.hashCode());
    result = prime * result + ((fR == null) ? 0 : fR.hashCode());
    return result;
    
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof HeadMatch)) { // this should be infrequent
    	if (obj == null)
    		return false;
    	if (!(obj instanceof IPatternMatch))
    		return false;
    	IPatternMatch otherSig  = (IPatternMatch) obj;
    	if (!specification().equals(otherSig.specification()))
    		return false;
    	return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    HeadMatch other = (HeadMatch) obj;
    if (fSen == null) {if (other.fSen != null) return false;}
    else if (!fSen.equals(other.fSen)) return false;
    if (fR == null) {if (other.fR != null) return false;}
    else if (!fR.equals(other.fR)) return false;
    return true;
  }
  
  @Override
  public HeadQuerySpecification specification() {
    try {
    	return HeadQuerySpecification.instance();
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
  public static HeadMatch newEmptyMatch() {
    return new Mutable(null, null);
    
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static HeadMatch newMutableMatch(final Sensor pSen, final Route pR) {
    return new Mutable(pSen, pR);
    
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static HeadMatch newMatch(final Sensor pSen, final Route pR) {
    return new Immutable(pSen, pR);
    
  }
  
  private static final class Mutable extends HeadMatch {
    Mutable(final Sensor pSen, final Route pR) {
      super(pSen, pR);
      
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends HeadMatch {
    Immutable(final Sensor pSen, final Route pR) {
      super(pSen, pR);
      
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
