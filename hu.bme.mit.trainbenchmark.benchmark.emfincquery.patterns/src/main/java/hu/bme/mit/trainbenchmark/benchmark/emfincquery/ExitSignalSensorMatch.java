package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Route;
import Concept.Sensor;
import Concept.Signal;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ExitSignalSensorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.exitSignalSensor pattern,
 * to be used in conjunction with {@link ExitSignalSensorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see ExitSignalSensorMatcher
 * @see ExitSignalSensorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class ExitSignalSensorMatch extends BasePatternMatch {
  private Signal fSig;
  
  private Route fR1;
  
  private Sensor fSen1;
  
  private static List<String> parameterNames = makeImmutableList("Sig", "R1", "Sen1");
  
  private ExitSignalSensorMatch(final Signal pSig, final Route pR1, final Sensor pSen1) {
    this.fSig = pSig;
    this.fR1 = pR1;
    this.fSen1 = pSen1;
    
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("Sig".equals(parameterName)) return this.fSig;
    if ("R1".equals(parameterName)) return this.fR1;
    if ("Sen1".equals(parameterName)) return this.fSen1;
    return null;
    
  }
  
  public Signal getSig() {
    return this.fSig;
    
  }
  
  public Route getR1() {
    return this.fR1;
    
  }
  
  public Sensor getSen1() {
    return this.fSen1;
    
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("Sig".equals(parameterName) ) {
    	this.fSig = (Concept.Signal) newValue;
    	return true;
    }
    if ("R1".equals(parameterName) ) {
    	this.fR1 = (Concept.Route) newValue;
    	return true;
    }
    if ("Sen1".equals(parameterName) ) {
    	this.fSen1 = (Concept.Sensor) newValue;
    	return true;
    }
    return false;
    
  }
  
  public void setSig(final Signal pSig) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSig = pSig;
    
  }
  
  public void setR1(final Route pR1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fR1 = pR1;
    
  }
  
  public void setSen1(final Sensor pSen1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSen1 = pSen1;
    
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.exitSignalSensor";
    
  }
  
  @Override
  public List<String> parameterNames() {
    return ExitSignalSensorMatch.parameterNames;
    
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSig, fR1, fSen1};
    
  }
  
  @Override
  public ExitSignalSensorMatch toImmutable() {
    return isMutable() ? newMatch(fSig, fR1, fSen1) : this;
    
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"Sig\"=" + prettyPrintValue(fSig) + ", ");
    result.append("\"R1\"=" + prettyPrintValue(fR1) + ", ");
    result.append("\"Sen1\"=" + prettyPrintValue(fSen1));
    return result.toString();
    
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSig == null) ? 0 : fSig.hashCode());
    result = prime * result + ((fR1 == null) ? 0 : fR1.hashCode());
    result = prime * result + ((fSen1 == null) ? 0 : fSen1.hashCode());
    return result;
    
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof ExitSignalSensorMatch)) { // this should be infrequent
    	if (obj == null)
    		return false;
    	if (!(obj instanceof IPatternMatch))
    		return false;
    	IPatternMatch otherSig  = (IPatternMatch) obj;
    	if (!specification().equals(otherSig.specification()))
    		return false;
    	return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    ExitSignalSensorMatch other = (ExitSignalSensorMatch) obj;
    if (fSig == null) {if (other.fSig != null) return false;}
    else if (!fSig.equals(other.fSig)) return false;
    if (fR1 == null) {if (other.fR1 != null) return false;}
    else if (!fR1.equals(other.fR1)) return false;
    if (fSen1 == null) {if (other.fSen1 != null) return false;}
    else if (!fSen1.equals(other.fSen1)) return false;
    return true;
  }
  
  @Override
  public ExitSignalSensorQuerySpecification specification() {
    try {
    	return ExitSignalSensorQuerySpecification.instance();
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
  public static ExitSignalSensorMatch newEmptyMatch() {
    return new Mutable(null, null, null);
    
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @param pR1 the fixed value of pattern parameter R1, or null if not bound.
   * @param pSen1 the fixed value of pattern parameter Sen1, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static ExitSignalSensorMatch newMutableMatch(final Signal pSig, final Route pR1, final Sensor pSen1) {
    return new Mutable(pSig, pR1, pSen1);
    
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @param pR1 the fixed value of pattern parameter R1, or null if not bound.
   * @param pSen1 the fixed value of pattern parameter Sen1, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static ExitSignalSensorMatch newMatch(final Signal pSig, final Route pR1, final Sensor pSen1) {
    return new Immutable(pSig, pR1, pSen1);
    
  }
  
  private static final class Mutable extends ExitSignalSensorMatch {
    Mutable(final Signal pSig, final Route pR1, final Sensor pSen1) {
      super(pSig, pR1, pSen1);
      
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends ExitSignalSensorMatch {
    Immutable(final Signal pSig, final Route pR1, final Sensor pSen1) {
      super(pSig, pR1, pSen1);
      
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
