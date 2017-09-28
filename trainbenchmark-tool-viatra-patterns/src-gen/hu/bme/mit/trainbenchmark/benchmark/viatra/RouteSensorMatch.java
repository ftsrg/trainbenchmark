/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/RouteSensor.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.util.RouteSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.viatra.routeSensor pattern,
 * to be used in conjunction with {@link RouteSensorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see RouteSensorMatcher
 * @see RouteSensorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class RouteSensorMatch extends BasePatternMatch {
  private Route fRoute;
  
  private Sensor fSensor;
  
  private SwitchPosition fSwP;
  
  private Switch fSw;
  
  private static List<String> parameterNames = makeImmutableList("route", "sensor", "swP", "sw");
  
  private RouteSensorMatch(final Route pRoute, final Sensor pSensor, final SwitchPosition pSwP, final Switch pSw) {
    this.fRoute = pRoute;
    this.fSensor = pSensor;
    this.fSwP = pSwP;
    this.fSw = pSw;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("route".equals(parameterName)) return this.fRoute;
    if ("sensor".equals(parameterName)) return this.fSensor;
    if ("swP".equals(parameterName)) return this.fSwP;
    if ("sw".equals(parameterName)) return this.fSw;
    return null;
  }
  
  public Route getRoute() {
    return this.fRoute;
  }
  
  public Sensor getSensor() {
    return this.fSensor;
  }
  
  public SwitchPosition getSwP() {
    return this.fSwP;
  }
  
  public Switch getSw() {
    return this.fSw;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("route".equals(parameterName) ) {
        this.fRoute = (Route) newValue;
        return true;
    }
    if ("sensor".equals(parameterName) ) {
        this.fSensor = (Sensor) newValue;
        return true;
    }
    if ("swP".equals(parameterName) ) {
        this.fSwP = (SwitchPosition) newValue;
        return true;
    }
    if ("sw".equals(parameterName) ) {
        this.fSw = (Switch) newValue;
        return true;
    }
    return false;
  }
  
  public void setRoute(final Route pRoute) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fRoute = pRoute;
  }
  
  public void setSensor(final Sensor pSensor) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSensor = pSensor;
  }
  
  public void setSwP(final SwitchPosition pSwP) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSwP = pSwP;
  }
  
  public void setSw(final Switch pSw) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSw = pSw;
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.viatra.routeSensor";
  }
  
  @Override
  public List<String> parameterNames() {
    return RouteSensorMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fRoute, fSensor, fSwP, fSw};
  }
  
  @Override
  public RouteSensorMatch toImmutable() {
    return isMutable() ? newMatch(fRoute, fSensor, fSwP, fSw) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"route\"=" + prettyPrintValue(fRoute) + ", ");
    
    result.append("\"sensor\"=" + prettyPrintValue(fSensor) + ", ");
    
    result.append("\"swP\"=" + prettyPrintValue(fSwP) + ", ");
    
    result.append("\"sw\"=" + prettyPrintValue(fSw)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fRoute == null) ? 0 : fRoute.hashCode());
    result = prime * result + ((fSensor == null) ? 0 : fSensor.hashCode());
    result = prime * result + ((fSwP == null) ? 0 : fSwP.hashCode());
    result = prime * result + ((fSw == null) ? 0 : fSw.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
        return true;
    if (!(obj instanceof RouteSensorMatch)) { // this should be infrequent
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
    RouteSensorMatch other = (RouteSensorMatch) obj;
    if (fRoute == null) {if (other.fRoute != null) return false;}
    else if (!fRoute.equals(other.fRoute)) return false;
    if (fSensor == null) {if (other.fSensor != null) return false;}
    else if (!fSensor.equals(other.fSensor)) return false;
    if (fSwP == null) {if (other.fSwP != null) return false;}
    else if (!fSwP.equals(other.fSwP)) return false;
    if (fSw == null) {if (other.fSw != null) return false;}
    else if (!fSw.equals(other.fSw)) return false;
    return true;
  }
  
  @Override
  public RouteSensorQuerySpecification specification() {
    try {
        return RouteSensorQuerySpecification.instance();
    } catch (ViatraQueryException ex) {
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
  public static RouteSensorMatch newEmptyMatch() {
    return new Mutable(null, null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSwP the fixed value of pattern parameter swP, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static RouteSensorMatch newMutableMatch(final Route pRoute, final Sensor pSensor, final SwitchPosition pSwP, final Switch pSw) {
    return new Mutable(pRoute, pSensor, pSwP, pSw);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSwP the fixed value of pattern parameter swP, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static RouteSensorMatch newMatch(final Route pRoute, final Sensor pSensor, final SwitchPosition pSwP, final Switch pSw) {
    return new Immutable(pRoute, pSensor, pSwP, pSw);
  }
  
  private static final class Mutable extends RouteSensorMatch {
    Mutable(final Route pRoute, final Sensor pSensor, final SwitchPosition pSwP, final Switch pSw) {
      super(pRoute, pSensor, pSwP, pSw);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends RouteSensorMatch {
    Immutable(final Route pRoute, final Sensor pSensor, final SwitchPosition pSwP, final Switch pSw) {
      super(pRoute, pSensor, pSwP, pSw);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
