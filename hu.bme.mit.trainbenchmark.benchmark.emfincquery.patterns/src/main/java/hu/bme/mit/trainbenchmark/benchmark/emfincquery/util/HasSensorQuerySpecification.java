package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.HasSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.HasSensorMatcher;
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
 * A pattern-specific query specification that can instantiate HasSensorMatcher in a type-safe way.
 * 
 * @see HasSensorMatcher
 * @see HasSensorMatch
 * 
 */
@SuppressWarnings("all")
public final class HasSensorQuerySpecification extends BaseGeneratedQuerySpecification<HasSensorMatcher> {
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static HasSensorQuerySpecification instance() throws IncQueryException {
    return LazyHolder.INSTANCE;
    
  }
  
  @Override
  protected HasSensorMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return HasSensorMatcher.on(engine);
  }
  
  @Override
  public String getFullyQualifiedName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.hasSensor";
    
  }
  
  @Override
  public List<String> getParameterNames() {
    return Arrays.asList("Trackelement");
  }
  
  @Override
  public List<PParameter> getParameters() {
    return Arrays.asList(new PParameter("Trackelement", "Concept.Trackelement"));
  }
  
  @Override
  public HasSensorMatch newEmptyMatch() {
    return HasSensorMatch.newEmptyMatch();
  }
  
  @Override
  public HasSensorMatch newMatch(final Object... parameters) {
    return HasSensorMatch.newMatch((Concept.Trackelement) parameters[0]);
  }
  
  @Override
  public Set<PBody> doGetContainedBodies() throws IncQueryException {
    Set<PBody> bodies = Sets.newLinkedHashSet();
    {
      PBody body = new PBody(this);
      PVariable var_Trackelement = body.getOrCreateVariableByName("Trackelement");
      PVariable var_Target = body.getOrCreateVariableByName("Target");
      body.setExportedParameters(Arrays.<ExportedParameter>asList(
        new ExportedParameter(body, var_Trackelement, "Trackelement")
      ));
      
      new TypeBinary(body, CONTEXT, var_Trackelement, var_Target, getFeatureLiteral("http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl", "Trackelement", "TrackElement_sensor"), "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl/Trackelement.TrackElement_sensor");
      bodies.add(body);
    }
    return bodies;
  }
  
  private static class LazyHolder {
    private final static HasSensorQuerySpecification INSTANCE = make();
    
    public static HasSensorQuerySpecification make() {
      return new HasSensorQuerySpecification();					
      
    }
  }
}
