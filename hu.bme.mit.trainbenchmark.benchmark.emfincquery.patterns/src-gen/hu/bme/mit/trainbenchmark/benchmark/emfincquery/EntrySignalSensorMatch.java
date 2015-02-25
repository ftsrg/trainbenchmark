package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.EntrySignalSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.Signal;
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
  private Signal fSignal;
  
  private Route fRoute2;
  
  private Sensor fSensor2;
  
  private static List<String> parameterNames = makeImmutableList("signal", "route2", "sensor2");
  
  private EntrySignalSensorMatch(final Signal pSignal, final Route pRoute2, final Sensor pSensor2) {
    this.fSignal = pSignal;
    this.fRoute2 = pRoute2;
    this.fSensor2 = pSensor2;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("signal".equals(parameterName)) return this.fSignal;
    if ("route2".equals(parameterName)) return this.fRoute2;
    if ("sensor2".equals(parameterName)) return this.fSensor2;
    return null;
  }
  
  public Signal getSignal() {
    return this.fSignal;
  }
  
  public Route getRoute2() {
    return this.fRoute2;
  }
  
  public Sensor getSensor2() {
    return this.fSensor2;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("signal".equals(parameterName) ) {
    	this.fSignal = (hu.bme.mit.trainbenchmark.railway.Signal) newValue;
    	return true;
    }
    if ("route2".equals(parameterName) ) {
    	this.fRoute2 = (hu.bme.mit.trainbenchmark.railway.Route) newValue;
    	return true;
    }
    if ("sensor2".equals(parameterName) ) {
    	this.fSensor2 = (hu.bme.mit.trainbenchmark.railway.Sensor) newValue;
    	return true;
    }
    return false;
  }
  
  public void setSignal(final Signal pSignal) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSignal = pSignal;
  }
  
  public void setRoute2(final Route pRoute2) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fRoute2 = pRoute2;
  }
  
  public void setSensor2(final Sensor pSensor2) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSensor2 = pSensor2;
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
    return new Object[]{fSignal, fRoute2, fSensor2};
  }
  
  @Override
  public EntrySignalSensorMatch toImmutable() {
    return isMutable() ? newMatch(fSignal, fRoute2, fSensor2) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"signal\"=" + prettyPrintValue(fSignal) + ", ");
    
    result.append("\"route2\"=" + prettyPrintValue(fRoute2) + ", ");
    
    result.append("\"sensor2\"=" + prettyPrintValue(fSensor2)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSignal == null) ? 0 : fSignal.hashCode());
    result = prime * result + ((fRoute2 == null) ? 0 : fRoute2.hashCode());
    result = prime * result + ((fSensor2 == null) ? 0 : fSensor2.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof EntrySignalSensorMatch)) { // this should be infrequent
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
    EntrySignalSensorMatch other = (EntrySignalSensorMatch) obj;
    if (fSignal == null) {if (other.fSignal != null) return false;}
    else if (!fSignal.equals(other.fSignal)) return false;
    if (fRoute2 == null) {if (other.fRoute2 != null) return false;}
    else if (!fRoute2.equals(other.fRoute2)) return false;
    if (fSensor2 == null) {if (other.fSensor2 != null) return false;}
    else if (!fSensor2.equals(other.fSensor2)) return false;
    return true;
  }
  
  @Override
  public EntrySignalSensorQuerySpecification specification() {
    try {
    	return EntrySignalSensorQuerySpecification.instance();
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
  public static EntrySignalSensorMatch newEmptyMatch() {
    return new Mutable(null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSignal the fixed value of pattern parameter signal, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static EntrySignalSensorMatch newMutableMatch(final Signal pSignal, final Route pRoute2, final Sensor pSensor2) {
    return new Mutable(pSignal, pRoute2, pSensor2);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSignal the fixed value of pattern parameter signal, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static EntrySignalSensorMatch newMatch(final Signal pSignal, final Route pRoute2, final Sensor pSensor2) {
    return new Immutable(pSignal, pRoute2, pSensor2);
  }
  
  private static final class Mutable extends EntrySignalSensorMatch {
    Mutable(final Signal pSignal, final Route pRoute2, final Sensor pSensor2) {
      super(pSignal, pRoute2, pSensor2);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends EntrySignalSensorMatch {
    Immutable(final Signal pSignal, final Route pRoute2, final Sensor pSensor2) {
      super(pSignal, pRoute2, pSensor2);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
