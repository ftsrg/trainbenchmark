package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.viatra.MisalignedSwitchMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.MisalignedSwitchMatcher;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Inequality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate MisalignedSwitchMatcher in a type-safe way.
 * 
 * @see MisalignedSwitchMatcher
 * @see MisalignedSwitchMatch
 * 
 */
@SuppressWarnings("all")
public final class MisalignedSwitchQuerySpecification extends BaseGeneratedEMFQuerySpecification<MisalignedSwitchMatcher> {
  private MisalignedSwitchQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static MisalignedSwitchQuerySpecification instance() throws ViatraQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected MisalignedSwitchMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return MisalignedSwitchMatcher.on(engine);
  }
  
  @Override
  public MisalignedSwitchMatch newEmptyMatch() {
    return MisalignedSwitchMatch.newEmptyMatch();
  }
  
  @Override
  public MisalignedSwitchMatch newMatch(final Object... parameters) {
    return MisalignedSwitchMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Route) parameters[0], (hu.bme.mit.trainbenchmark.railway.SwitchPosition) parameters[1], (hu.bme.mit.trainbenchmark.railway.Switch) parameters[2]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link MisalignedSwitchQuerySpecification} to be created 
   * 	<b>not</b> at the class load time of the outer class, 
   * 	but rather at the first call to {@link MisalignedSwitchQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static MisalignedSwitchQuerySpecification INSTANCE = new MisalignedSwitchQuerySpecification();
    
    /**
     * Statically initializes the query specification <b>after</b> the field {@link #INSTANCE} is assigned.
     * This initialization order is required to support indirect recursion.
     * 
     * <p> The static initializer is defined using a helper field to work around limitations of the code generator.
     * 
     */
    private final static Object STATIC_INITIALIZER = ensureInitialized();
    
    public static Object ensureInitialized() {
      INSTANCE.ensureInitializedInternalSneaky();
      return null;					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static MisalignedSwitchQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.viatra.misalignedSwitch";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("route","swP","sw");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(
      			 new PParameter("route", "hu.bme.mit.trainbenchmark.railway.Route", null),
      			 new PParameter("swP", "hu.bme.mit.trainbenchmark.railway.SwitchPosition", null),
      			 new PParameter("sw", "hu.bme.mit.trainbenchmark.railway.Switch", null)
      			);
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      	{
      		PBody body = new PBody(this);
      		PVariable var_route = body.getOrCreateVariableByName("route");
      		PVariable var_swP = body.getOrCreateVariableByName("swP");
      		PVariable var_sw = body.getOrCreateVariableByName("sw");
      		PVariable var_position = body.getOrCreateVariableByName("position");
      		PVariable var_currentPosition = body.getOrCreateVariableByName("currentPosition");
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_route, "route"),
      		   new ExportedParameter(body, var_swP, "swP"),
      		   new ExportedParameter(body, var_sw, "sw")
      		));
      		// 	Route.follows(route, swP)
      		new TypeConstraint(body, new FlatTuple(var_route), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_route, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "follows")));
      		new Equality(body, var__virtual_0_, var_swP);
      		// 	SwitchPosition.target(swP, sw)
      		new TypeConstraint(body, new FlatTuple(var_swP), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_swP, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition", "target")));
      		new Equality(body, var__virtual_1_, var_sw);
      		// 	SwitchPosition.position(swP, position)
      		new TypeConstraint(body, new FlatTuple(var_swP), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition")));
      		PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
      		new TypeConstraint(body, new FlatTuple(var_swP, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition", "position")));
      		new Equality(body, var__virtual_2_, var_position);
      		// 	Switch.currentPosition(sw, currentPosition)
      		new TypeConstraint(body, new FlatTuple(var_sw), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Switch")));
      		PVariable var__virtual_3_ = body.getOrCreateVariableByName(".virtual{3}");
      		new TypeConstraint(body, new FlatTuple(var_sw, var__virtual_3_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Switch", "currentPosition")));
      		new Equality(body, var__virtual_3_, var_currentPosition);
      		// 	position != currentPosition
      		new Inequality(body, var_position, var_currentPosition);
      		bodies.add(body);
      	}
      	// to silence compiler error
      	if (false) throw new ViatraQueryException("Never", "happens");
      } catch (ViatraQueryException ex) {
      	throw processDependencyException(ex);
      }
      return bodies;
    }
  }
}
