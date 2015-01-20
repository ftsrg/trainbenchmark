package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SensorTrackelementMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SensorTrackelementMatcher;
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
 * A pattern-specific query specification that can instantiate SensorTrackelementMatcher in a type-safe way.
 * 
 * @see SensorTrackelementMatcher
 * @see SensorTrackelementMatch
 * 
 */
@SuppressWarnings("all")
public final class SensorTrackelementQuerySpecification extends BaseGeneratedQuerySpecification<SensorTrackelementMatcher> {
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static SensorTrackelementQuerySpecification instance() throws IncQueryException {
    return LazyHolder.INSTANCE;
    
  }
  
  @Override
  protected SensorTrackelementMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return SensorTrackelementMatcher.on(engine);
  }
  
  @Override
  public String getFullyQualifiedName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.sensorTrackelement";
    
  }
  
  @Override
  public List<String> getParameterNames() {
    return Arrays.asList("Sen","Te");
  }
  
  @Override
  public List<PParameter> getParameters() {
    return Arrays.asList(new PParameter("Sen", "Concept.Sensor"),new PParameter("Te", "Concept.Trackelement"));
  }
  
  @Override
  public SensorTrackelementMatch newEmptyMatch() {
    return SensorTrackelementMatch.newEmptyMatch();
  }
  
  @Override
  public SensorTrackelementMatch newMatch(final Object... parameters) {
    return SensorTrackelementMatch.newMatch((Concept.Sensor) parameters[0], (Concept.Trackelement) parameters[1]);
  }
  
  @Override
  public Set<PBody> doGetContainedBodies() throws IncQueryException {
    Set<PBody> bodies = Sets.newLinkedHashSet();
    {
      PBody body = new PBody(this);
      PVariable var_Sen = body.getOrCreateVariableByName("Sen");
      PVariable var_Te = body.getOrCreateVariableByName("Te");
      body.setExportedParameters(Arrays.<ExportedParameter>asList(
        new ExportedParameter(body, var_Sen, "Sen"), 
        new ExportedParameter(body, var_Te, "Te")
      ));
      
      
      new TypeBinary(body, CONTEXT, var_Sen, var_Te, getFeatureLiteral("http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl", "Sensor", "Sensor_trackElement"), "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl/Sensor.Sensor_trackElement");
      bodies.add(body);
    }
    return bodies;
  }
  
  private static class LazyHolder {
    private final static SensorTrackelementQuerySpecification INSTANCE = make();
    
    public static SensorTrackelementQuerySpecification make() {
      return new SensorTrackelementQuerySpecification();					
      
    }
  }
}
