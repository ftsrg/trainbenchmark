package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.TrackelementConnectedMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.TrackelementConnectedMatcher;
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
 * A pattern-specific query specification that can instantiate TrackelementConnectedMatcher in a type-safe way.
 * 
 * @see TrackelementConnectedMatcher
 * @see TrackelementConnectedMatch
 * 
 */
@SuppressWarnings("all")
public final class TrackelementConnectedQuerySpecification extends BaseGeneratedQuerySpecification<TrackelementConnectedMatcher> {
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static TrackelementConnectedQuerySpecification instance() throws IncQueryException {
    return LazyHolder.INSTANCE;
    
  }
  
  @Override
  protected TrackelementConnectedMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return TrackelementConnectedMatcher.on(engine);
  }
  
  @Override
  public String getFullyQualifiedName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.trackelementConnected";
    
  }
  
  @Override
  public List<String> getParameterNames() {
    return Arrays.asList("Te1","Te2");
  }
  
  @Override
  public List<PParameter> getParameters() {
    return Arrays.asList(new PParameter("Te1", "Concept.Trackelement"),new PParameter("Te2", "Concept.Trackelement"));
  }
  
  @Override
  public TrackelementConnectedMatch newEmptyMatch() {
    return TrackelementConnectedMatch.newEmptyMatch();
  }
  
  @Override
  public TrackelementConnectedMatch newMatch(final Object... parameters) {
    return TrackelementConnectedMatch.newMatch((Concept.Trackelement) parameters[0], (Concept.Trackelement) parameters[1]);
  }
  
  @Override
  public Set<PBody> doGetContainedBodies() throws IncQueryException {
    Set<PBody> bodies = Sets.newLinkedHashSet();
    {
      PBody body = new PBody(this);
      PVariable var_Te1 = body.getOrCreateVariableByName("Te1");
      PVariable var_Te2 = body.getOrCreateVariableByName("Te2");
      body.setExportedParameters(Arrays.<ExportedParameter>asList(
        new ExportedParameter(body, var_Te1, "Te1"), 
        new ExportedParameter(body, var_Te2, "Te2")
      ));
      
      
      new TypeBinary(body, CONTEXT, var_Te1, var_Te2, getFeatureLiteral("http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl", "Trackelement", "TrackElement_connectsTo"), "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl/Trackelement.TrackElement_connectsTo");
      bodies.add(body);
    }
    return bodies;
  }
  
  private static class LazyHolder {
    private final static TrackelementConnectedQuerySpecification INSTANCE = make();
    
    public static TrackelementConnectedQuerySpecification make() {
      return new TrackelementConnectedQuerySpecification();					
      
    }
  }
}
