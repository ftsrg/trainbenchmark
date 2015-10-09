package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSetMatcher;
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
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.ConstantValue;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.incquery.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate SwitchSetMatcher in a type-safe way.
 * 
 * @see SwitchSetMatcher
 * @see SwitchSetMatch
 * 
 */
@SuppressWarnings("all")
public final class SwitchSetQuerySpecification extends BaseGeneratedEMFQuerySpecification<SwitchSetMatcher> {
  private SwitchSetQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static SwitchSetQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected SwitchSetMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return SwitchSetMatcher.on(engine);
  }
  
  @Override
  public SwitchSetMatch newEmptyMatch() {
    return SwitchSetMatch.newEmptyMatch();
  }
  
  @Override
  public SwitchSetMatch newMatch(final Object... parameters) {
    return SwitchSetMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Semaphore) parameters[0], (hu.bme.mit.trainbenchmark.railway.Route) parameters[1], (hu.bme.mit.trainbenchmark.railway.SwitchPosition) parameters[2], (hu.bme.mit.trainbenchmark.railway.Switch) parameters[3]);
  }
  
  private static class LazyHolder {
    private final static SwitchSetQuerySpecification INSTANCE = make();
    
    public static SwitchSetQuerySpecification make() {
      return new SwitchSetQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static SwitchSetQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.switchSet";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("semaphore","route","swP","sw");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("semaphore", "hu.bme.mit.trainbenchmark.railway.Semaphore"),new PParameter("route", "hu.bme.mit.trainbenchmark.railway.Route"),new PParameter("swP", "hu.bme.mit.trainbenchmark.railway.SwitchPosition"),new PParameter("sw", "hu.bme.mit.trainbenchmark.railway.Switch"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      	{
      		PBody body = new PBody(this);
      		PVariable var_semaphore = body.getOrCreateVariableByName("semaphore");
      		PVariable var_route = body.getOrCreateVariableByName("route");
      		PVariable var_swP = body.getOrCreateVariableByName("swP");
      		PVariable var_sw = body.getOrCreateVariableByName("sw");
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
      		PVariable var__virtual_3_ = body.getOrCreateVariableByName(".virtual{3}");
      		PVariable var__virtual_4_ = body.getOrCreateVariableByName(".virtual{4}");
      		PVariable var_swpPosition = body.getOrCreateVariableByName("swpPosition");
      		PVariable var__virtual_5_ = body.getOrCreateVariableByName(".virtual{5}");
      		PVariable var_swCurrentPosition = body.getOrCreateVariableByName("swCurrentPosition");
      		PVariable var__virtual_6_ = body.getOrCreateVariableByName(".virtual{6}");
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      			new ExportedParameter(body, var_semaphore, "semaphore"),
      			
      			new ExportedParameter(body, var_route, "route"),
      			
      			new ExportedParameter(body, var_swP, "swP"),
      			
      			new ExportedParameter(body, var_sw, "sw")
      		));
      		new TypeConstraint(body, new FlatTuple(var_route), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route")));
      		new TypeConstraint(body, new FlatTuple(var_route, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "entry")));
      		new Equality(body, var__virtual_0_, var_semaphore);
      		new TypeConstraint(body, new FlatTuple(var_route), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route")));
      		new TypeConstraint(body, new FlatTuple(var_route, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "follows")));
      		new Equality(body, var__virtual_1_, var_swP);
      		new TypeConstraint(body, new FlatTuple(var_swP), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition")));
      		new TypeConstraint(body, new FlatTuple(var_swP, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition", "switch")));
      		new Equality(body, var__virtual_2_, var_sw);
      		new ConstantValue(body, var__virtual_3_, getEnumLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Signal", "GO").getInstance());
      		new TypeConstraint(body, new FlatTuple(var_semaphore), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Semaphore")));
      		new TypeConstraint(body, new FlatTuple(var_semaphore, var__virtual_4_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Semaphore", "signal")));
      		new Equality(body, var__virtual_4_, var__virtual_3_);
      		new TypeConstraint(body, new FlatTuple(var_swP), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition")));
      		new TypeConstraint(body, new FlatTuple(var_swP, var__virtual_5_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition", "position")));
      		new Equality(body, var__virtual_5_, var_swpPosition);
      		new TypeConstraint(body, new FlatTuple(var_sw), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Switch")));
      		new TypeConstraint(body, new FlatTuple(var_sw, var__virtual_6_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Switch", "currentPosition")));
      		new Equality(body, var__virtual_6_, var_swCurrentPosition);
      		new Inequality(body, var_swpPosition, var_swCurrentPosition);
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
