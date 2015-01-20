package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.HeadMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.HeadMatcher;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedQuerySpecification;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.psystem.PBody;
import org.eclipse.incquery.runtime.matchers.psystem.PVariable;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeBinary;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;

/**
 * A pattern-specific query specification that can instantiate HeadMatcher in a type-safe way.
 * 
 * @see HeadMatcher
 * @see HeadMatch
 * 
 */
@SuppressWarnings("all")
public final class HeadQuerySpecification extends BaseGeneratedQuerySpecification<HeadMatcher> {
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static HeadQuerySpecification instance() throws IncQueryException {
    return LazyHolder.INSTANCE;
    
  }
  
  @Override
  protected HeadMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return HeadMatcher.on(engine);
  }
  
  @Override
  public String getFullyQualifiedName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.head";
    
  }
  
  @Override
  public List<String> getParameterNames() {
    return Arrays.asList("Sen","R");
  }
  
  @Override
  public List<PParameter> getParameters() {
    return Arrays.asList(new PParameter("Sen", "Concept.Sensor"),new PParameter("R", "Concept.Route"));
  }
  
  @Override
  public HeadMatch newEmptyMatch() {
    return HeadMatch.newEmptyMatch();
  }
  
  @Override
  public HeadMatch newMatch(final Object... parameters) {
    return HeadMatch.newMatch((Concept.Sensor) parameters[0], (Concept.Route) parameters[1]);
  }
  
  @Override
  public Set<PBody> doGetContainedBodies() throws IncQueryException {
    Set<PBody> bodies = Sets.newLinkedHashSet();
    {
      PBody body = new PBody(this);
      PVariable var_Sen = body.getOrCreateVariableByName("Sen");
      PVariable var_R = body.getOrCreateVariableByName("R");
      body.setExportedParameters(Arrays.<ExportedParameter>asList(
        new ExportedParameter(body, var_Sen, "Sen"), 
        new ExportedParameter(body, var_R, "R")
      ));
      
      
      new TypeBinary(body, CONTEXT, var_R, var_Sen, getFeatureLiteral("http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl", "Route", "Route_routeDefinition"), "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl/Route.Route_routeDefinition");
      bodies.add(body);
    }
    return bodies;
  }
  
  private static class LazyHolder {
    private final static HeadQuerySpecification INSTANCE = make();
    
    public static HeadQuerySpecification make() {
      return new HeadQuerySpecification();					
      
    }
  }
}
