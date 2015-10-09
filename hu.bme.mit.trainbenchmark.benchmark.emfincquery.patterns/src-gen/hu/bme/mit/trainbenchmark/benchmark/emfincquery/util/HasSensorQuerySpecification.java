package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.HasSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.HasSensorMatcher;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.incquery.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.incquery.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.psystem.PBody;
import org.eclipse.incquery.runtime.matchers.psystem.PVariable;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.incquery.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate HasSensorMatcher in a type-safe way.
 * 
 * @see HasSensorMatcher
 * @see HasSensorMatch
 * 
 */
@SuppressWarnings("all")
public final class HasSensorQuerySpecification extends BaseGeneratedEMFQuerySpecification<HasSensorMatcher> {
  private HasSensorQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static HasSensorQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected HasSensorMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return HasSensorMatcher.on(engine);
  }
  
  @Override
  public HasSensorMatch newEmptyMatch() {
    return HasSensorMatch.newEmptyMatch();
  }
  
  @Override
  public HasSensorMatch newMatch(final Object... parameters) {
    return HasSensorMatch.newMatch((hu.bme.mit.trainbenchmark.railway.TrackElement) parameters[0]);
  }
  
  private static class LazyHolder {
    private final static HasSensorQuerySpecification INSTANCE = make();
    
    public static HasSensorQuerySpecification make() {
      return new HasSensorQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static HasSensorQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.hasSensor";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("sw");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("sw", "hu.bme.mit.trainbenchmark.railway.TrackElement"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      	{
      		PBody body = new PBody(this);
      		PVariable var_sw = body.getOrCreateVariableByName("sw");
      		PVariable var___0_ = body.getOrCreateVariableByName("_<0>");
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      			new ExportedParameter(body, var_sw, "sw")
      		));
      		new TypeConstraint(body, new FlatTuple(var_sw), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement")));
      		new TypeConstraint(body, new FlatTuple(var_sw, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "sensor")));
      		new Equality(body, var__virtual_0_, var___0_);
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
