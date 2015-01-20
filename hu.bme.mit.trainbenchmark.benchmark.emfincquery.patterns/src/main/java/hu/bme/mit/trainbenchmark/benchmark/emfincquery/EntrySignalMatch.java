package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Route;
import Concept.Signal;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.EntrySignalQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySignal pattern,
 * to be used in conjunction with {@link EntrySignalMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see EntrySignalMatcher
 * @see EntrySignalProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class EntrySignalMatch extends BasePatternMatch {
  private Route fR;
  
  private Signal fSig;
  
  private static List<String> parameterNames = makeImmutableList("R", "Sig");
  
  private EntrySignalMatch(final Route pR, final Signal pSig) {
    this.fR = pR;
    this.fSig = pSig;
    
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("R".equals(parameterName)) return this.fR;
    if ("Sig".equals(parameterName)) return this.fSig;
    return null;
    
  }
  
  public Route getR() {
    return this.fR;
    
  }
  
  public Signal getSig() {
    return this.fSig;
    
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("R".equals(parameterName) ) {
    	this.fR = (Concept.Route) newValue;
    	return true;
    }
    if ("Sig".equals(parameterName) ) {
    	this.fSig = (Concept.Signal) newValue;
    	return true;
    }
    return false;
    
  }
  
  public void setR(final Route pR) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fR = pR;
    
  }
  
  public void setSig(final Signal pSig) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSig = pSig;
    
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySignal";
    
  }
  
  @Override
  public List<String> parameterNames() {
    return EntrySignalMatch.parameterNames;
    
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fR, fSig};
    
  }
  
  @Override
  public EntrySignalMatch toImmutable() {
    return isMutable() ? newMatch(fR, fSig) : this;
    
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"R\"=" + prettyPrintValue(fR) + ", ");
    result.append("\"Sig\"=" + prettyPrintValue(fSig));
    return result.toString();
    
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fR == null) ? 0 : fR.hashCode());
    result = prime * result + ((fSig == null) ? 0 : fSig.hashCode());
    return result;
    
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof EntrySignalMatch)) { // this should be infrequent
    	if (obj == null)
    		return false;
    	if (!(obj instanceof IPatternMatch))
    		return false;
    	IPatternMatch otherSig  = (IPatternMatch) obj;
    	if (!specification().equals(otherSig.specification()))
    		return false;
    	return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    EntrySignalMatch other = (EntrySignalMatch) obj;
    if (fR == null) {if (other.fR != null) return false;}
    else if (!fR.equals(other.fR)) return false;
    if (fSig == null) {if (other.fSig != null) return false;}
    else if (!fSig.equals(other.fSig)) return false;
    return true;
  }
  
  @Override
  public EntrySignalQuerySpecification specification() {
    try {
    	return EntrySignalQuerySpecification.instance();
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
  public static EntrySignalMatch newEmptyMatch() {
    return new Mutable(null, null);
    
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static EntrySignalMatch newMutableMatch(final Route pR, final Signal pSig) {
    return new Mutable(pR, pSig);
    
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static EntrySignalMatch newMatch(final Route pR, final Signal pSig) {
    return new Immutable(pR, pSig);
    
  }
  
  private static final class Mutable extends EntrySignalMatch {
    Mutable(final Route pR, final Signal pSig) {
      super(pR, pSig);
      
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends EntrySignalMatch {
    Immutable(final Route pR, final Signal pSig) {
      super(pR, pSig);
      
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
