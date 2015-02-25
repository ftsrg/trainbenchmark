package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.RouteDefinitionQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.routeDefinition pattern,
 * to be used in conjunction with {@link RouteDefinitionMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see RouteDefinitionMatcher
 * @see RouteDefinitionProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class RouteDefinitionMatch extends BasePatternMatch {
  private Route fRoute;
  
  private Sensor fSensor;
  
  private static List<String> parameterNames = makeImmutableList("route", "sensor");
  
  private RouteDefinitionMatch(final Route pRoute, final Sensor pSensor) {
    this.fRoute = pRoute;
    this.fSensor = pSensor;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("route".equals(parameterName)) return this.fRoute;
    if ("sensor".equals(parameterName)) return this.fSensor;
    return null;
  }
  
  public Route getRoute() {
    return this.fRoute;
  }
  
  public Sensor getSensor() {
    return this.fSensor;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("route".equals(parameterName) ) {
    	this.fRoute = (hu.bme.mit.trainbenchmark.railway.Route) newValue;
    	return true;
    }
    if ("sensor".equals(parameterName) ) {
    	this.fSensor = (hu.bme.mit.trainbenchmark.railway.Sensor) newValue;
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
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.routeDefinition";
  }
  
  @Override
  public List<String> parameterNames() {
    return RouteDefinitionMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fRoute, fSensor};
  }
  
  @Override
  public RouteDefinitionMatch toImmutable() {
    return isMutable() ? newMatch(fRoute, fSensor) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"route\"=" + prettyPrintValue(fRoute) + ", ");
    
    result.append("\"sensor\"=" + prettyPrintValue(fSensor)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fRoute == null) ? 0 : fRoute.hashCode());
    result = prime * result + ((fSensor == null) ? 0 : fSensor.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof RouteDefinitionMatch)) { // this should be infrequent
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
    RouteDefinitionMatch other = (RouteDefinitionMatch) obj;
    if (fRoute == null) {if (other.fRoute != null) return false;}
    else if (!fRoute.equals(other.fRoute)) return false;
    if (fSensor == null) {if (other.fSensor != null) return false;}
    else if (!fSensor.equals(other.fSensor)) return false;
    return true;
  }
  
  @Override
  public RouteDefinitionQuerySpecification specification() {
    try {
    	return RouteDefinitionQuerySpecification.instance();
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
  public static RouteDefinitionMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static RouteDefinitionMatch newMutableMatch(final Route pRoute, final Sensor pSensor) {
    return new Mutable(pRoute, pSensor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static RouteDefinitionMatch newMatch(final Route pRoute, final Sensor pSensor) {
    return new Immutable(pRoute, pSensor);
  }
  
  private static final class Mutable extends RouteDefinitionMatch {
    Mutable(final Route pRoute, final Sensor pSensor) {
      super(pRoute, pSensor);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends RouteDefinitionMatch {
    Immutable(final Route pRoute, final Sensor pSensor) {
      super(pRoute, pSensor);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
