package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SwitchSetQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.switchSet pattern,
 * to be used in conjunction with {@link SwitchSetMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see SwitchSetMatcher
 * @see SwitchSetProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class SwitchSetMatch extends BasePatternMatch {
  private SwitchPosition fSwitchPosition;
  
  private static List<String> parameterNames = makeImmutableList("switchPosition");
  
  private SwitchSetMatch(final SwitchPosition pSwitchPosition) {
    this.fSwitchPosition = pSwitchPosition;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("switchPosition".equals(parameterName)) return this.fSwitchPosition;
    return null;
  }
  
  public SwitchPosition getSwitchPosition() {
    return this.fSwitchPosition;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("switchPosition".equals(parameterName) ) {
    	this.fSwitchPosition = (hu.bme.mit.trainbenchmark.railway.SwitchPosition) newValue;
    	return true;
    }
    return false;
  }
  
  public void setSwitchPosition(final SwitchPosition pSwitchPosition) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSwitchPosition = pSwitchPosition;
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.switchSet";
  }
  
  @Override
  public List<String> parameterNames() {
    return SwitchSetMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSwitchPosition};
  }
  
  @Override
  public SwitchSetMatch toImmutable() {
    return isMutable() ? newMatch(fSwitchPosition) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"switchPosition\"=" + prettyPrintValue(fSwitchPosition)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSwitchPosition == null) ? 0 : fSwitchPosition.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof SwitchSetMatch)) { // this should be infrequent
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
    SwitchSetMatch other = (SwitchSetMatch) obj;
    if (fSwitchPosition == null) {if (other.fSwitchPosition != null) return false;}
    else if (!fSwitchPosition.equals(other.fSwitchPosition)) return false;
    return true;
  }
  
  @Override
  public SwitchSetQuerySpecification specification() {
    try {
    	return SwitchSetQuerySpecification.instance();
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
  public static SwitchSetMatch newEmptyMatch() {
    return new Mutable(null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSwitchPosition the fixed value of pattern parameter switchPosition, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static SwitchSetMatch newMutableMatch(final SwitchPosition pSwitchPosition) {
    return new Mutable(pSwitchPosition);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSwitchPosition the fixed value of pattern parameter switchPosition, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static SwitchSetMatch newMatch(final SwitchPosition pSwitchPosition) {
    return new Immutable(pSwitchPosition);
  }
  
  private static final class Mutable extends SwitchSetMatch {
    Mutable(final SwitchPosition pSwitchPosition) {
      super(pSwitchPosition);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends SwitchSetMatch {
    Immutable(final SwitchPosition pSwitchPosition) {
      super(pSwitchPosition);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
