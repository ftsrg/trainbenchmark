package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySignalMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySignalMatcher;
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
 * A pattern-specific query specification that can instantiate EntrySignalMatcher in a type-safe way.
 * 
 * @see EntrySignalMatcher
 * @see EntrySignalMatch
 * 
 */
@SuppressWarnings("all")
public final class EntrySignalQuerySpecification extends BaseGeneratedQuerySpecification<EntrySignalMatcher> {
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static EntrySignalQuerySpecification instance() throws IncQueryException {
    return LazyHolder.INSTANCE;
    
  }
  
  @Override
  protected EntrySignalMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return EntrySignalMatcher.on(engine);
  }
  
  @Override
  public String getFullyQualifiedName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySignal";
    
  }
  
  @Override
  public List<String> getParameterNames() {
    return Arrays.asList("R","Sig");
  }
  
  @Override
  public List<PParameter> getParameters() {
    return Arrays.asList(new PParameter("R", "Concept.Route"),new PParameter("Sig", "Concept.Signal"));
  }
  
  @Override
  public EntrySignalMatch newEmptyMatch() {
    return EntrySignalMatch.newEmptyMatch();
  }
  
  @Override
  public EntrySignalMatch newMatch(final Object... parameters) {
    return EntrySignalMatch.newMatch((Concept.Route) parameters[0], (Concept.Signal) parameters[1]);
  }
  
  @Override
  public Set<PBody> doGetContainedBodies() throws IncQueryException {
    Set<PBody> bodies = Sets.newLinkedHashSet();
    {
      PBody body = new PBody(this);
      PVariable var_R = body.getOrCreateVariableByName("R");
      PVariable var_Sig = body.getOrCreateVariableByName("Sig");
      body.setExportedParameters(Arrays.<ExportedParameter>asList(
        new ExportedParameter(body, var_R, "R"), 
        new ExportedParameter(body, var_Sig, "Sig")
      ));
      
      
      new TypeBinary(body, CONTEXT, var_R, var_Sig, getFeatureLiteral("http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl", "Route", "Route_entry"), "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl/Route.Route_entry");
      bodies.add(body);
    }
    return bodies;
  }
  
  private static class LazyHolder {
    private final static EntrySignalQuerySpecification INSTANCE = make();
    
    public static EntrySignalQuerySpecification make() {
      return new EntrySignalQuerySpecification();					
      
    }
  }
}
