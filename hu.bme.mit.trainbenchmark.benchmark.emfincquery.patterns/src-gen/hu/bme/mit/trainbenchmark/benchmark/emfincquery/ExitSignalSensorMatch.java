package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ExitSignalSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.Signal;
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
  private Signal fSignal;
  
  private Route fRoute1;
  
  private Sensor fSensor1;
  
  private static List<String> parameterNames = makeImmutableList("signal", "route1", "sensor1");
  
  private ExitSignalSensorMatch(final Signal pSignal, final Route pRoute1, final Sensor pSensor1) {
    this.fSignal = pSignal;
    this.fRoute1 = pRoute1;
    this.fSensor1 = pSensor1;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("signal".equals(parameterName)) return this.fSignal;
    if ("route1".equals(parameterName)) return this.fRoute1;
    if ("sensor1".equals(parameterName)) return this.fSensor1;
    return null;
  }
  
  public Signal getSignal() {
    return this.fSignal;
  }
  
  public Route getRoute1() {
    return this.fRoute1;
  }
  
  public Sensor getSensor1() {
    return this.fSensor1;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("signal".equals(parameterName) ) {
    	this.fSignal = (hu.bme.mit.trainbenchmark.railway.Signal) newValue;
    	return true;
    }
    if ("route1".equals(parameterName) ) {
    	this.fRoute1 = (hu.bme.mit.trainbenchmark.railway.Route) newValue;
    	return true;
    }
    if ("sensor1".equals(parameterName) ) {
    	this.fSensor1 = (hu.bme.mit.trainbenchmark.railway.Sensor) newValue;
    	return true;
    }
    return false;
  }
  
  public void setSignal(final Signal pSignal) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSignal = pSignal;
  }
  
  public void setRoute1(final Route pRoute1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fRoute1 = pRoute1;
  }
  
  public void setSensor1(final Sensor pSensor1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSensor1 = pSensor1;
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
    return new Object[]{fSignal, fRoute1, fSensor1};
  }
  
  @Override
  public ExitSignalSensorMatch toImmutable() {
    return isMutable() ? newMatch(fSignal, fRoute1, fSensor1) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"signal\"=" + prettyPrintValue(fSignal) + ", ");
    
    result.append("\"route1\"=" + prettyPrintValue(fRoute1) + ", ");
    
    result.append("\"sensor1\"=" + prettyPrintValue(fSensor1)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSignal == null) ? 0 : fSignal.hashCode());
    result = prime * result + ((fRoute1 == null) ? 0 : fRoute1.hashCode());
    result = prime * result + ((fSensor1 == null) ? 0 : fSensor1.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof ExitSignalSensorMatch)) { // this should be infrequent
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
    ExitSignalSensorMatch other = (ExitSignalSensorMatch) obj;
    if (fSignal == null) {if (other.fSignal != null) return false;}
    else if (!fSignal.equals(other.fSignal)) return false;
    if (fRoute1 == null) {if (other.fRoute1 != null) return false;}
    else if (!fRoute1.equals(other.fRoute1)) return false;
    if (fSensor1 == null) {if (other.fSensor1 != null) return false;}
    else if (!fSensor1.equals(other.fSensor1)) return false;
    return true;
  }
  
  @Override
  public ExitSignalSensorQuerySpecification specification() {
    try {
    	return ExitSignalSensorQuerySpecification.instance();
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
  public static ExitSignalSensorMatch newEmptyMatch() {
    return new Mutable(null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSignal the fixed value of pattern parameter signal, or null if not bound.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static ExitSignalSensorMatch newMutableMatch(final Signal pSignal, final Route pRoute1, final Sensor pSensor1) {
    return new Mutable(pSignal, pRoute1, pSensor1);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSignal the fixed value of pattern parameter signal, or null if not bound.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static ExitSignalSensorMatch newMatch(final Signal pSignal, final Route pRoute1, final Sensor pSensor1) {
    return new Immutable(pSignal, pRoute1, pSensor1);
  }
  
  private static final class Mutable extends ExitSignalSensorMatch {
    Mutable(final Signal pSignal, final Route pRoute1, final Sensor pSensor1) {
      super(pSignal, pRoute1, pSensor1);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends ExitSignalSensorMatch {
    Immutable(final Signal pSignal, final Route pRoute1, final Sensor pSensor1) {
      super(pSignal, pRoute1, pSensor1);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
