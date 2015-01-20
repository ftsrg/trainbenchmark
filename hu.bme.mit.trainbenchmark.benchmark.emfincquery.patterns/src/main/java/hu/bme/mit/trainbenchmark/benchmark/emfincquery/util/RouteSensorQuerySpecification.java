package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.HeadQuerySpecification;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedQuerySpecification;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.psystem.PBody;
import org.eclipse.incquery.runtime.matchers.psystem.PVariable;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.NegativePatternCall;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeBinary;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate RouteSensorMatcher in a type-safe way.
 * 
 * @see RouteSensorMatcher
 * @see RouteSensorMatch
 * 
 */
@SuppressWarnings("all")
public final class RouteSensorQuerySpecification extends BaseGeneratedQuerySpecification<RouteSensorMatcher> {
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static RouteSensorQuerySpecification instance() throws IncQueryException {
    return LazyHolder.INSTANCE;
    
  }
  
  @Override
  protected RouteSensorMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return RouteSensorMatcher.on(engine);
  }
  
  @Override
  public String getFullyQualifiedName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.routeSensor";
    
  }
  
  @Override
  public List<String> getParameterNames() {
    return Arrays.asList("Sen");
  }
  
  @Override
  public List<PParameter> getParameters() {
    return Arrays.asList(new PParameter("Sen", "Concept.Sensor"));
  }
  
  @Override
  public RouteSensorMatch newEmptyMatch() {
    return RouteSensorMatch.newEmptyMatch();
  }
  
  @Override
  public RouteSensorMatch newMatch(final Object... parameters) {
    return RouteSensorMatch.newMatch((Concept.Sensor) parameters[0]);
  }
  
  @Override
  public Set<PBody> doGetContainedBodies() throws IncQueryException {
    Set<PBody> bodies = Sets.newLinkedHashSet();
    {
      PBody body = new PBody(this);
      PVariable var_Sen = body.getOrCreateVariableByName("Sen");
      PVariable var_R = body.getOrCreateVariableByName("R");
      PVariable var_Sp = body.getOrCreateVariableByName("Sp");
      PVariable var_Sw = body.getOrCreateVariableByName("Sw");
      body.setExportedParameters(Arrays.<ExportedParameter>asList(
        new ExportedParameter(body, var_Sen, "Sen")
      ));
      
      new TypeBinary(body, CONTEXT, var_R, var_Sp, getFeatureLiteral("http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl", "Route", "Route_switchPosition"), "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl/Route.Route_switchPosition");
      new TypeBinary(body, CONTEXT, var_Sp, var_Sw, getFeatureLiteral("http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl", "SwitchPosition", "SwitchPosition_switch"), "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl/SwitchPosition.SwitchPosition_switch");
      new TypeBinary(body, CONTEXT, var_Sw, var_Sen, getFeatureLiteral("http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl", "Trackelement", "TrackElement_sensor"), "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl/Trackelement.TrackElement_sensor");
      new NegativePatternCall(body, new FlatTuple(var_Sen, var_R), HeadQuerySpecification.instance().instance());
      bodies.add(body);
    }
    return bodies;
  }
  
  private static class LazyHolder {
    private final static RouteSensorQuerySpecification INSTANCE = make();
    
    public static RouteSensorQuerySpecification make() {
      return new RouteSensorQuerySpecification();					
      
    }
  }
}
