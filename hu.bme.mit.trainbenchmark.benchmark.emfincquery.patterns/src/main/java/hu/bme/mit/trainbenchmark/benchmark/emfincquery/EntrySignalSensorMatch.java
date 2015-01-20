package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Route;
import Concept.Sensor;
import Concept.Signal;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.EntrySignalSensorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySignalSensor pattern,
 * to be used in conjunction with {@link EntrySignalSensorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see EntrySignalSensorMatcher
 * @see EntrySignalSensorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class EntrySignalSensorMatch extends BasePatternMatch {
  private Signal fSig;
  
  private Route fR2;
  
  private Sensor fSen2;
  
  private static List<String> parameterNames = makeImmutableList("Sig", "R2", "Sen2");
  
  private EntrySignalSensorMatch(final Signal pSig, final Route pR2, final Sensor pSen2) {
    this.fSig = pSig;
    this.fR2 = pR2;
    this.fSen2 = pSen2;
    
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("Sig".equals(parameterName)) return this.fSig;
    if ("R2".equals(parameterName)) return this.fR2;
    if ("Sen2".equals(parameterName)) return this.fSen2;
    return null;
    
  }
  
  public Signal getSig() {
    return this.fSig;
    
  }
  
  public Route getR2() {
    return this.fR2;
    
  }
  
  public Sensor getSen2() {
    return this.fSen2;
    
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("Sig".equals(parameterName) ) {
    	this.fSig = (Concept.Signal) newValue;
    	return true;
    }
    if ("R2".equals(parameterName) ) {
    	this.fR2 = (Concept.Route) newValue;
    	return true;
    }
    if ("Sen2".equals(parameterName) ) {
    	this.fSen2 = (Concept.Sensor) newValue;
    	return true;
    }
    return false;
    
  }
  
  public void setSig(final Signal pSig) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSig = pSig;
    
  }
  
  public void setR2(final Route pR2) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fR2 = pR2;
    
  }
  
  public void setSen2(final Sensor pSen2) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSen2 = pSen2;
    
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySignalSensor";
    
  }
  
  @Override
  public List<String> parameterNames() {
    return EntrySignalSensorMatch.parameterNames;
    
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSig, fR2, fSen2};
    
  }
  
  @Override
  public EntrySignalSensorMatch toImmutable() {
    return isMutable() ? newMatch(fSig, fR2, fSen2) : this;
    
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"Sig\"=" + prettyPrintValue(fSig) + ", ");
    result.append("\"R2\"=" + prettyPrintValue(fR2) + ", ");
    result.append("\"Sen2\"=" + prettyPrintValue(fSen2));
    return result.toString();
    
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSig == null) ? 0 : fSig.hashCode());
    result = prime * result + ((fR2 == null) ? 0 : fR2.hashCode());
    result = prime * result + ((fSen2 == null) ? 0 : fSen2.hashCode());
    return result;
    
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof EntrySignalSensorMatch)) { // this should be infrequent
    	if (obj == null)
    		return false;
    	if (!(obj instanceof IPatternMatch))
    		return false;
    	IPatternMatch otherSig  = (IPatternMatch) obj;
    	if (!specification().equals(otherSig.specification()))
    		return false;
    	return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    EntrySignalSensorMatch other = (EntrySignalSensorMatch) obj;
    if (fSig == null) {if (other.fSig != null) return false;}
    else if (!fSig.equals(other.fSig)) return false;
    if (fR2 == null) {if (other.fR2 != null) return false;}
    else if (!fR2.equals(other.fR2)) return false;
    if (fSen2 == null) {if (other.fSen2 != null) return false;}
    else if (!fSen2.equals(other.fSen2)) return false;
    return true;
  }
  
  @Override
  public EntrySignalSensorQuerySpecification specification() {
    try {
    	return EntrySignalSensorQuerySpecification.instance();
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
  public static EntrySignalSensorMatch newEmptyMatch() {
    return new Mutable(null, null, null);
    
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @param pR2 the fixed value of pattern parameter R2, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static EntrySignalSensorMatch newMutableMatch(final Signal pSig, final Route pR2, final Sensor pSen2) {
    return new Mutable(pSig, pR2, pSen2);
    
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @param pR2 the fixed value of pattern parameter R2, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static EntrySignalSensorMatch newMatch(final Signal pSig, final Route pR2, final Sensor pSen2) {
    return new Immutable(pSig, pR2, pSen2);
    
  }
  
  private static final class Mutable extends EntrySignalSensorMatch {
    Mutable(final Signal pSig, final Route pR2, final Sensor pSen2) {
      super(pSig, pR2, pSen2);
      
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends EntrySignalSensorMatch {
    Immutable(final Signal pSig, final Route pR2, final Sensor pSen2) {
      super(pSig, pR2, pSen2);
      
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
