package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Sensor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ConnectingSensorsQuerySpecification;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.connectingSensors pattern,
 * to be used in conjunction with {@link ConnectingSensorsMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see ConnectingSensorsMatcher
 * @see ConnectingSensorsProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class ConnectingSensorsMatch extends BasePatternMatch {
  private Sensor fSen1;
  
  private Sensor fSen2;
  
  private static List<String> parameterNames = makeImmutableList("Sen1", "Sen2");
  
  private ConnectingSensorsMatch(final Sensor pSen1, final Sensor pSen2) {
    this.fSen1 = pSen1;
    this.fSen2 = pSen2;
    
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("Sen1".equals(parameterName)) return this.fSen1;
    if ("Sen2".equals(parameterName)) return this.fSen2;
    return null;
    
  }
  
  public Sensor getSen1() {
    return this.fSen1;
    
  }
  
  public Sensor getSen2() {
    return this.fSen2;
    
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("Sen1".equals(parameterName) ) {
    	this.fSen1 = (Concept.Sensor) newValue;
    	return true;
    }
    if ("Sen2".equals(parameterName) ) {
    	this.fSen2 = (Concept.Sensor) newValue;
    	return true;
    }
    return false;
    
  }
  
  public void setSen1(final Sensor pSen1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSen1 = pSen1;
    
  }
  
  public void setSen2(final Sensor pSen2) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSen2 = pSen2;
    
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.connectingSensors";
    
  }
  
  @Override
  public List<String> parameterNames() {
    return ConnectingSensorsMatch.parameterNames;
    
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSen1, fSen2};
    
  }
  
  @Override
  public ConnectingSensorsMatch toImmutable() {
    return isMutable() ? newMatch(fSen1, fSen2) : this;
    
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"Sen1\"=" + prettyPrintValue(fSen1) + ", ");
    result.append("\"Sen2\"=" + prettyPrintValue(fSen2));
    return result.toString();
    
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSen1 == null) ? 0 : fSen1.hashCode());
    result = prime * result + ((fSen2 == null) ? 0 : fSen2.hashCode());
    return result;
    
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof ConnectingSensorsMatch)) { // this should be infrequent
    	if (obj == null)
    		return false;
    	if (!(obj instanceof IPatternMatch))
    		return false;
    	IPatternMatch otherSig  = (IPatternMatch) obj;
    	if (!specification().equals(otherSig.specification()))
    		return false;
    	return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    ConnectingSensorsMatch other = (ConnectingSensorsMatch) obj;
    if (fSen1 == null) {if (other.fSen1 != null) return false;}
    else if (!fSen1.equals(other.fSen1)) return false;
    if (fSen2 == null) {if (other.fSen2 != null) return false;}
    else if (!fSen2.equals(other.fSen2)) return false;
    return true;
  }
  
  @Override
  public ConnectingSensorsQuerySpecification specification() {
    try {
    	return ConnectingSensorsQuerySpecification.instance();
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
  public static ConnectingSensorsMatch newEmptyMatch() {
    return new Mutable(null, null);
    
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSen1 the fixed value of pattern parameter Sen1, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static ConnectingSensorsMatch newMutableMatch(final Sensor pSen1, final Sensor pSen2) {
    return new Mutable(pSen1, pSen2);
    
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSen1 the fixed value of pattern parameter Sen1, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static ConnectingSensorsMatch newMatch(final Sensor pSen1, final Sensor pSen2) {
    return new Immutable(pSen1, pSen2);
    
  }
  
  private static final class Mutable extends ConnectingSensorsMatch {
    Mutable(final Sensor pSen1, final Sensor pSen2) {
      super(pSen1, pSen2);
      
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends ConnectingSensorsMatch {
    Immutable(final Sensor pSen1, final Sensor pSen2) {
      super(pSen1, pSen2);
      
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
