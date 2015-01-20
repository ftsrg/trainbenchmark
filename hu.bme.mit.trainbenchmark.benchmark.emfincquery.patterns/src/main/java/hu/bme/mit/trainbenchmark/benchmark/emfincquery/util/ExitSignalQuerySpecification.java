package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ExitSignalMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ExitSignalMatcher;
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
 * A pattern-specific query specification that can instantiate ExitSignalMatcher in a type-safe way.
 * 
 * @see ExitSignalMatcher
 * @see ExitSignalMatch
 * 
 */
@SuppressWarnings("all")
public final class ExitSignalQuerySpecification extends BaseGeneratedQuerySpecification<ExitSignalMatcher> {
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static ExitSignalQuerySpecification instance() throws IncQueryException {
    return LazyHolder.INSTANCE;
    
  }
  
  @Override
  protected ExitSignalMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return ExitSignalMatcher.on(engine);
  }
  
  @Override
  public String getFullyQualifiedName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.exitSignal";
    
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
  public ExitSignalMatch newEmptyMatch() {
    return ExitSignalMatch.newEmptyMatch();
  }
  
  @Override
  public ExitSignalMatch newMatch(final Object... parameters) {
    return ExitSignalMatch.newMatch((Concept.Route) parameters[0], (Concept.Signal) parameters[1]);
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
      
      
      new TypeBinary(body, CONTEXT, var_R, var_Sig, getFeatureLiteral("http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl", "Route", "Route_exit"), "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl/Route.Route_exit");
      bodies.add(body);
    }
    return bodies;
  }
  
  private static class LazyHolder {
    private final static ExitSignalQuerySpecification INSTANCE = make();
    
    public static ExitSignalQuerySpecification make() {
      return new ExitSignalQuerySpecification();					
      
    }
  }
}
