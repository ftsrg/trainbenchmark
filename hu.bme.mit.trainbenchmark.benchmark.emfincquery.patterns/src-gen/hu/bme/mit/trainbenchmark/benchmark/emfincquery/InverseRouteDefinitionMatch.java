/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.InverseRouteDefinitionQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.inverseRouteDefinition pattern,
 * to be used in conjunction with {@link InverseRouteDefinitionMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see InverseRouteDefinitionMatcher
 * @see InverseRouteDefinitionProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class InverseRouteDefinitionMatch extends BasePatternMatch {
  private Sensor fSensor;
  
  private Route fRoute;
  
  private static List<String> parameterNames = makeImmutableList("sensor", "route");
  
  private InverseRouteDefinitionMatch(final Sensor pSensor, final Route pRoute) {
    this.fSensor = pSensor;
    this.fRoute = pRoute;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("sensor".equals(parameterName)) return this.fSensor;
    if ("route".equals(parameterName)) return this.fRoute;
    return null;
  }
  
  public Sensor getSensor() {
    return this.fSensor;
  }
  
  public Route getRoute() {
    return this.fRoute;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("sensor".equals(parameterName) ) {
    	this.fSensor = (hu.bme.mit.trainbenchmark.railway.Sensor) newValue;
    	return true;
    }
    if ("route".equals(parameterName) ) {
    	this.fRoute = (hu.bme.mit.trainbenchmark.railway.Route) newValue;
    	return true;
    }
    return false;
  }
  
  public void setSensor(final Sensor pSensor) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSensor = pSensor;
  }
  
  public void setRoute(final Route pRoute) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fRoute = pRoute;
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.inverseRouteDefinition";
  }
  
  @Override
  public List<String> parameterNames() {
    return InverseRouteDefinitionMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSensor, fRoute};
  }
  
  @Override
  public InverseRouteDefinitionMatch toImmutable() {
    return isMutable() ? newMatch(fSensor, fRoute) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"sensor\"=" + prettyPrintValue(fSensor) + ", ");
    
    result.append("\"route\"=" + prettyPrintValue(fRoute)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSensor == null) ? 0 : fSensor.hashCode());
    result = prime * result + ((fRoute == null) ? 0 : fRoute.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof InverseRouteDefinitionMatch)) { // this should be infrequent
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
    InverseRouteDefinitionMatch other = (InverseRouteDefinitionMatch) obj;
    if (fSensor == null) {if (other.fSensor != null) return false;}
    else if (!fSensor.equals(other.fSensor)) return false;
    if (fRoute == null) {if (other.fRoute != null) return false;}
    else if (!fRoute.equals(other.fRoute)) return false;
    return true;
  }
  
  @Override
  public InverseRouteDefinitionQuerySpecification specification() {
    try {
    	return InverseRouteDefinitionQuerySpecification.instance();
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
  public static InverseRouteDefinitionMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static InverseRouteDefinitionMatch newMutableMatch(final Sensor pSensor, final Route pRoute) {
    return new Mutable(pSensor, pRoute);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static InverseRouteDefinitionMatch newMatch(final Sensor pSensor, final Route pRoute) {
    return new Immutable(pSensor, pRoute);
  }
  
  private static final class Mutable extends InverseRouteDefinitionMatch {
    Mutable(final Sensor pSensor, final Route pRoute) {
      super(pSensor, pRoute);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends InverseRouteDefinitionMatch {
    Immutable(final Sensor pSensor, final Route pRoute) {
      super(pSensor, pRoute);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
