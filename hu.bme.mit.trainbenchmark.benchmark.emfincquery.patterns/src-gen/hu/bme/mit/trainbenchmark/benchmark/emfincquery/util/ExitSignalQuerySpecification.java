package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ExitSignalMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ExitSignalMatcher;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.psystem.PBody;
import org.eclipse.incquery.runtime.matchers.psystem.PVariable;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeBinary;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.psystem.queries.QueryInitializationException;

/**
 * A pattern-specific query specification that can instantiate ExitSignalMatcher in a type-safe way.
 * 
 * @see ExitSignalMatcher
 * @see ExitSignalMatch
 * 
 */
@SuppressWarnings("all")
public final class ExitSignalQuerySpecification extends BaseGeneratedEMFQuerySpecification<ExitSignalMatcher> {
  private ExitSignalQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static ExitSignalQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected ExitSignalMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return ExitSignalMatcher.on(engine);
  }
  
  @Override
  public ExitSignalMatch newEmptyMatch() {
    return ExitSignalMatch.newEmptyMatch();
  }
  
  @Override
  public ExitSignalMatch newMatch(final Object... parameters) {
    return ExitSignalMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Route) parameters[0], (hu.bme.mit.trainbenchmark.railway.Signal) parameters[1]);
  }
  
  private static class LazyHolder {
    private final static ExitSignalQuerySpecification INSTANCE = make();
    
    public static ExitSignalQuerySpecification make() {
      return new ExitSignalQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static ExitSignalQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.exitSignal";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("route","signal");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("route", "hu.bme.mit.trainbenchmark.railway.Route"),new PParameter("signal", "hu.bme.mit.trainbenchmark.railway.Signal"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      {
      	PBody body = new PBody(this);
      	PVariable var_route = body.getOrCreateVariableByName("route");
      	PVariable var_signal = body.getOrCreateVariableByName("signal");
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_route, "route"),
      				
      		new ExportedParameter(body, var_signal, "signal")
      	));
      	new TypeBinary(body, CONTEXT, var_route, var_signal, getFeatureLiteral("http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl", "Route", "Route_exit"), "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl/Route.Route_exit");
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
