/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SemaphoreNeighbor.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.util.SemaphoreNeighborQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;
import java.util.Arrays;
import java.util.List;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.viatra.semaphoreNeighbor pattern,
 * to be used in conjunction with {@link SemaphoreNeighborMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see SemaphoreNeighborMatcher
 * @see SemaphoreNeighborProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class SemaphoreNeighborMatch extends BasePatternMatch {
  private Semaphore fSemaphore;
  
  private Route fRoute1;
  
  private Route fRoute2;
  
  private Sensor fSensor1;
  
  private Sensor fSensor2;
  
  private TrackElement fTe1;
  
  private TrackElement fTe2;
  
  private static List<String> parameterNames = makeImmutableList("semaphore", "route1", "route2", "sensor1", "sensor2", "te1", "te2");
  
  private SemaphoreNeighborMatch(final Semaphore pSemaphore, final Route pRoute1, final Route pRoute2, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe1, final TrackElement pTe2) {
    this.fSemaphore = pSemaphore;
    this.fRoute1 = pRoute1;
    this.fRoute2 = pRoute2;
    this.fSensor1 = pSensor1;
    this.fSensor2 = pSensor2;
    this.fTe1 = pTe1;
    this.fTe2 = pTe2;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("semaphore".equals(parameterName)) return this.fSemaphore;
    if ("route1".equals(parameterName)) return this.fRoute1;
    if ("route2".equals(parameterName)) return this.fRoute2;
    if ("sensor1".equals(parameterName)) return this.fSensor1;
    if ("sensor2".equals(parameterName)) return this.fSensor2;
    if ("te1".equals(parameterName)) return this.fTe1;
    if ("te2".equals(parameterName)) return this.fTe2;
    return null;
  }
  
  public Semaphore getSemaphore() {
    return this.fSemaphore;
  }
  
  public Route getRoute1() {
    return this.fRoute1;
  }
  
  public Route getRoute2() {
    return this.fRoute2;
  }
  
  public Sensor getSensor1() {
    return this.fSensor1;
  }
  
  public Sensor getSensor2() {
    return this.fSensor2;
  }
  
  public TrackElement getTe1() {
    return this.fTe1;
  }
  
  public TrackElement getTe2() {
    return this.fTe2;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("semaphore".equals(parameterName) ) {
        this.fSemaphore = (Semaphore) newValue;
        return true;
    }
    if ("route1".equals(parameterName) ) {
        this.fRoute1 = (Route) newValue;
        return true;
    }
    if ("route2".equals(parameterName) ) {
        this.fRoute2 = (Route) newValue;
        return true;
    }
    if ("sensor1".equals(parameterName) ) {
        this.fSensor1 = (Sensor) newValue;
        return true;
    }
    if ("sensor2".equals(parameterName) ) {
        this.fSensor2 = (Sensor) newValue;
        return true;
    }
    if ("te1".equals(parameterName) ) {
        this.fTe1 = (TrackElement) newValue;
        return true;
    }
    if ("te2".equals(parameterName) ) {
        this.fTe2 = (TrackElement) newValue;
        return true;
    }
    return false;
  }
  
  public void setSemaphore(final Semaphore pSemaphore) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSemaphore = pSemaphore;
  }
  
  public void setRoute1(final Route pRoute1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fRoute1 = pRoute1;
  }
  
  public void setRoute2(final Route pRoute2) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fRoute2 = pRoute2;
  }
  
  public void setSensor1(final Sensor pSensor1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSensor1 = pSensor1;
  }
  
  public void setSensor2(final Sensor pSensor2) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSensor2 = pSensor2;
  }
  
  public void setTe1(final TrackElement pTe1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTe1 = pTe1;
  }
  
  public void setTe2(final TrackElement pTe2) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fTe2 = pTe2;
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.viatra.semaphoreNeighbor";
  }
  
  @Override
  public List<String> parameterNames() {
    return SemaphoreNeighborMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSemaphore, fRoute1, fRoute2, fSensor1, fSensor2, fTe1, fTe2};
  }
  
  @Override
  public SemaphoreNeighborMatch toImmutable() {
    return isMutable() ? newMatch(fSemaphore, fRoute1, fRoute2, fSensor1, fSensor2, fTe1, fTe2) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"semaphore\"=" + prettyPrintValue(fSemaphore) + ", ");
    
    result.append("\"route1\"=" + prettyPrintValue(fRoute1) + ", ");
    
    result.append("\"route2\"=" + prettyPrintValue(fRoute2) + ", ");
    
    result.append("\"sensor1\"=" + prettyPrintValue(fSensor1) + ", ");
    
    result.append("\"sensor2\"=" + prettyPrintValue(fSensor2) + ", ");
    
    result.append("\"te1\"=" + prettyPrintValue(fTe1) + ", ");
    
    result.append("\"te2\"=" + prettyPrintValue(fTe2)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSemaphore == null) ? 0 : fSemaphore.hashCode());
    result = prime * result + ((fRoute1 == null) ? 0 : fRoute1.hashCode());
    result = prime * result + ((fRoute2 == null) ? 0 : fRoute2.hashCode());
    result = prime * result + ((fSensor1 == null) ? 0 : fSensor1.hashCode());
    result = prime * result + ((fSensor2 == null) ? 0 : fSensor2.hashCode());
    result = prime * result + ((fTe1 == null) ? 0 : fTe1.hashCode());
    result = prime * result + ((fTe2 == null) ? 0 : fTe2.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
        return true;
    if (!(obj instanceof SemaphoreNeighborMatch)) { // this should be infrequent
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
    SemaphoreNeighborMatch other = (SemaphoreNeighborMatch) obj;
    if (fSemaphore == null) {if (other.fSemaphore != null) return false;}
    else if (!fSemaphore.equals(other.fSemaphore)) return false;
    if (fRoute1 == null) {if (other.fRoute1 != null) return false;}
    else if (!fRoute1.equals(other.fRoute1)) return false;
    if (fRoute2 == null) {if (other.fRoute2 != null) return false;}
    else if (!fRoute2.equals(other.fRoute2)) return false;
    if (fSensor1 == null) {if (other.fSensor1 != null) return false;}
    else if (!fSensor1.equals(other.fSensor1)) return false;
    if (fSensor2 == null) {if (other.fSensor2 != null) return false;}
    else if (!fSensor2.equals(other.fSensor2)) return false;
    if (fTe1 == null) {if (other.fTe1 != null) return false;}
    else if (!fTe1.equals(other.fTe1)) return false;
    if (fTe2 == null) {if (other.fTe2 != null) return false;}
    else if (!fTe2.equals(other.fTe2)) return false;
    return true;
  }
  
  @Override
  public SemaphoreNeighborQuerySpecification specification() {
    try {
        return SemaphoreNeighborQuerySpecification.instance();
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
  public static SemaphoreNeighborMatch newEmptyMatch() {
    return new Mutable(null, null, null, null, null, null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static SemaphoreNeighborMatch newMutableMatch(final Semaphore pSemaphore, final Route pRoute1, final Route pRoute2, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe1, final TrackElement pTe2) {
    return new Mutable(pSemaphore, pRoute1, pRoute2, pSensor1, pSensor2, pTe1, pTe2);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static SemaphoreNeighborMatch newMatch(final Semaphore pSemaphore, final Route pRoute1, final Route pRoute2, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe1, final TrackElement pTe2) {
    return new Immutable(pSemaphore, pRoute1, pRoute2, pSensor1, pSensor2, pTe1, pTe2);
  }
  
  private static final class Mutable extends SemaphoreNeighborMatch {
    Mutable(final Semaphore pSemaphore, final Route pRoute1, final Route pRoute2, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe1, final TrackElement pTe2) {
      super(pSemaphore, pRoute1, pRoute2, pSensor1, pSensor2, pTe1, pTe2);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends SemaphoreNeighborMatch {
    Immutable(final Semaphore pSemaphore, final Route pRoute1, final Route pRoute2, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe1, final TrackElement pTe2) {
      super(pSemaphore, pRoute1, pRoute2, pSensor1, pSensor2, pTe1, pTe2);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
