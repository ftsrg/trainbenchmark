package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchMonitoredMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.HasSensorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.incquery.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.psystem.PBody;
import org.eclipse.incquery.runtime.matchers.psystem.PVariable;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.NegativePatternCall;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.incquery.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate SwitchMonitoredMatcher in a type-safe way.
 * 
 * @see SwitchMonitoredMatcher
 * @see SwitchMonitoredMatch
 * 
 */
@SuppressWarnings("all")
public final class SwitchMonitoredQuerySpecification extends BaseGeneratedEMFQuerySpecification<SwitchMonitoredMatcher> {
  private SwitchMonitoredQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static SwitchMonitoredQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected SwitchMonitoredMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return SwitchMonitoredMatcher.on(engine);
  }
  
  @Override
  public SwitchMonitoredMatch newEmptyMatch() {
    return SwitchMonitoredMatch.newEmptyMatch();
  }
  
  @Override
  public SwitchMonitoredMatch newMatch(final Object... parameters) {
    return SwitchMonitoredMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Switch) parameters[0]);
  }
  
  private static class LazyHolder {
    private final static SwitchMonitoredQuerySpecification INSTANCE = make();
    
    public static SwitchMonitoredQuerySpecification make() {
      return new SwitchMonitoredQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static SwitchMonitoredQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchMonitored";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("sw");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("sw", "hu.bme.mit.trainbenchmark.railway.Switch"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      	{
      		PBody body = new PBody(this);
      		PVariable var_sw = body.getOrCreateVariableByName("sw");
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_sw, "sw")
      		));
      		// 	Switch(sw)
      		new TypeConstraint(body, new FlatTuple(var_sw), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Switch")));
      		// 	neg find hasSensor(sw)
      		new NegativePatternCall(body, new FlatTuple(var_sw), HasSensorQuerySpecification.instance().getInternalQueryRepresentation());
      		bodies.add(body);
      	}
      	// to silence compiler error
      	if (false) throw new IncQueryException("Never", "happens");
      } catch (IncQueryException ex) {
      	throw processDependencyException(ex);
      }
      return bodies;
    }
  }
}
