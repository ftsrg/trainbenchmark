package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Route;
import Concept.Sensor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.RDefinitionQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.rDefinition pattern,
 * to be used in conjunction with {@link RDefinitionMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see RDefinitionMatcher
 * @see RDefinitionProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class RDefinitionMatch extends BasePatternMatch {
  private Route fR;
  
  private Sensor fSen;
  
  private static List<String> parameterNames = makeImmutableList("R", "Sen");
  
  private RDefinitionMatch(final Route pR, final Sensor pSen) {
    this.fR = pR;
    this.fSen = pSen;
    
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("R".equals(parameterName)) return this.fR;
    if ("Sen".equals(parameterName)) return this.fSen;
    return null;
    
  }
  
  public Route getR() {
    return this.fR;
    
  }
  
  public Sensor getSen() {
    return this.fSen;
    
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("R".equals(parameterName) ) {
    	this.fR = (Concept.Route) newValue;
    	return true;
    }
    if ("Sen".equals(parameterName) ) {
    	this.fSen = (Concept.Sensor) newValue;
    	return true;
    }
    return false;
    
  }
  
  public void setR(final Route pR) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fR = pR;
    
  }
  
  public void setSen(final Sensor pSen) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSen = pSen;
    
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.rDefinition";
    
  }
  
  @Override
  public List<String> parameterNames() {
    return RDefinitionMatch.parameterNames;
    
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fR, fSen};
    
  }
  
  @Override
  public RDefinitionMatch toImmutable() {
    return isMutable() ? newMatch(fR, fSen) : this;
    
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"R\"=" + prettyPrintValue(fR) + ", ");
    result.append("\"Sen\"=" + prettyPrintValue(fSen));
    return result.toString();
    
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fR == null) ? 0 : fR.hashCode());
    result = prime * result + ((fSen == null) ? 0 : fSen.hashCode());
    return result;
    
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof RDefinitionMatch)) { // this should be infrequent
    	if (obj == null)
    		return false;
    	if (!(obj instanceof IPatternMatch))
    		return false;
    	IPatternMatch otherSig  = (IPatternMatch) obj;
    	if (!specification().equals(otherSig.specification()))
    		return false;
    	return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    RDefinitionMatch other = (RDefinitionMatch) obj;
    if (fR == null) {if (other.fR != null) return false;}
    else if (!fR.equals(other.fR)) return false;
    if (fSen == null) {if (other.fSen != null) return false;}
    else if (!fSen.equals(other.fSen)) return false;
    return true;
  }
  
  @Override
  public RDefinitionQuerySpecification specification() {
    try {
    	return RDefinitionQuerySpecification.instance();
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
  public static RDefinitionMatch newEmptyMatch() {
    return new Mutable(null, null);
    
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static RDefinitionMatch newMutableMatch(final Route pR, final Sensor pSen) {
    return new Mutable(pR, pSen);
    
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static RDefinitionMatch newMatch(final Route pR, final Sensor pSen) {
    return new Immutable(pR, pSen);
    
  }
  
  private static final class Mutable extends RDefinitionMatch {
    Mutable(final Route pR, final Sensor pSen) {
      super(pR, pSen);
      
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends RDefinitionMatch {
    Immutable(final Route pR, final Sensor pSen) {
      super(pR, pSen);
      
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
