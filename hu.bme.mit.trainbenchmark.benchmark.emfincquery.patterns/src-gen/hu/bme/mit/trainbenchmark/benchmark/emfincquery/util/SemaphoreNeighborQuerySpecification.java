package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SemaphoreNeighborMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.EntrySemaphoreQuerySpecification;
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
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.Inequality;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.NegativePatternCall;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.incquery.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate SemaphoreNeighborMatcher in a type-safe way.
 * 
 * @see SemaphoreNeighborMatcher
 * @see SemaphoreNeighborMatch
 * 
 */
@SuppressWarnings("all")
public final class SemaphoreNeighborQuerySpecification extends BaseGeneratedEMFQuerySpecification<SemaphoreNeighborMatcher> {
  private SemaphoreNeighborQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static SemaphoreNeighborQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected SemaphoreNeighborMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return SemaphoreNeighborMatcher.on(engine);
  }
  
  @Override
  public SemaphoreNeighborMatch newEmptyMatch() {
    return SemaphoreNeighborMatch.newEmptyMatch();
  }
  
  @Override
  public SemaphoreNeighborMatch newMatch(final Object... parameters) {
    return SemaphoreNeighborMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Semaphore) parameters[0], (hu.bme.mit.trainbenchmark.railway.Route) parameters[1], (hu.bme.mit.trainbenchmark.railway.Route) parameters[2], (hu.bme.mit.trainbenchmark.railway.Sensor) parameters[3], (hu.bme.mit.trainbenchmark.railway.Sensor) parameters[4], (hu.bme.mit.trainbenchmark.railway.TrackElement) parameters[5], (hu.bme.mit.trainbenchmark.railway.TrackElement) parameters[6]);
  }
  
  private static class LazyHolder {
    private final static SemaphoreNeighborQuerySpecification INSTANCE = make();
    
    public static SemaphoreNeighborQuerySpecification make() {
      return new SemaphoreNeighborQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static SemaphoreNeighborQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.semaphoreNeighbor";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("semaphore","route1","route2","sensor1","sensor2","te1","te2");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("semaphore", "hu.bme.mit.trainbenchmark.railway.Semaphore"),new PParameter("route1", "hu.bme.mit.trainbenchmark.railway.Route"),new PParameter("route2", "hu.bme.mit.trainbenchmark.railway.Route"),new PParameter("sensor1", "hu.bme.mit.trainbenchmark.railway.Sensor"),new PParameter("sensor2", "hu.bme.mit.trainbenchmark.railway.Sensor"),new PParameter("te1", "hu.bme.mit.trainbenchmark.railway.TrackElement"),new PParameter("te2", "hu.bme.mit.trainbenchmark.railway.TrackElement"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      {
      	PBody body = new PBody(this);
      	PVariable var_semaphore = body.getOrCreateVariableByName("semaphore");
      	PVariable var_route1 = body.getOrCreateVariableByName("route1");
      	PVariable var_route2 = body.getOrCreateVariableByName("route2");
      	PVariable var_sensor1 = body.getOrCreateVariableByName("sensor1");
      	PVariable var_sensor2 = body.getOrCreateVariableByName("sensor2");
      	PVariable var_te1 = body.getOrCreateVariableByName("te1");
      	PVariable var_te2 = body.getOrCreateVariableByName("te2");
      	PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      	PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      	PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
      	PVariable var__virtual_3_ = body.getOrCreateVariableByName(".virtual{3}");
      	PVariable var__virtual_4_ = body.getOrCreateVariableByName(".virtual{4}");
      	PVariable var__virtual_5_ = body.getOrCreateVariableByName(".virtual{5}");
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_semaphore, "semaphore"),
      				
      		new ExportedParameter(body, var_route1, "route1"),
      				
      		new ExportedParameter(body, var_route2, "route2"),
      				
      		new ExportedParameter(body, var_sensor1, "sensor1"),
      				
      		new ExportedParameter(body, var_sensor2, "sensor2"),
      				
      		new ExportedParameter(body, var_te1, "te1"),
      				
      		new ExportedParameter(body, var_te2, "te2")
      	));
      	new TypeConstraint(body, new FlatTuple(var_route1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route")));
      	new TypeConstraint(body, new FlatTuple(var_route1, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "exit")));
      	new Equality(body, var__virtual_0_, var_semaphore);
      	new TypeConstraint(body, new FlatTuple(var_route1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route")));
      	new TypeConstraint(body, new FlatTuple(var_route1, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "definedBy")));
      	new Equality(body, var__virtual_1_, var_sensor1);
      	new TypeConstraint(body, new FlatTuple(var_te1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement")));
      	new TypeConstraint(body, new FlatTuple(var_te1, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "sensor")));
      	new Equality(body, var__virtual_2_, var_sensor1);
      	new TypeConstraint(body, new FlatTuple(var_te1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement")));
      	new TypeConstraint(body, new FlatTuple(var_te1, var__virtual_3_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo")));
      	new Equality(body, var__virtual_3_, var_te2);
      	new TypeConstraint(body, new FlatTuple(var_te2), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement")));
      	new TypeConstraint(body, new FlatTuple(var_te2, var__virtual_4_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "sensor")));
      	new Equality(body, var__virtual_4_, var_sensor2);
      	new TypeConstraint(body, new FlatTuple(var_route2), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route")));
      	new TypeConstraint(body, new FlatTuple(var_route2, var__virtual_5_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "definedBy")));
      	new Equality(body, var__virtual_5_, var_sensor2);
      	new NegativePatternCall(body, new FlatTuple(var_route2, var_semaphore), EntrySemaphoreQuerySpecification.instance().getInternalQueryRepresentation());
      	new Inequality(body, var_route1, var_route2);
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
