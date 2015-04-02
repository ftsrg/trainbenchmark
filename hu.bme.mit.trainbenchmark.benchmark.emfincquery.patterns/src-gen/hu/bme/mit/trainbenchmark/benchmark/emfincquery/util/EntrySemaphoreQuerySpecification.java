package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySemaphoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySemaphoreMatcher;
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
 * A pattern-specific query specification that can instantiate EntrySemaphoreMatcher in a type-safe way.
 * 
 * @see EntrySemaphoreMatcher
 * @see EntrySemaphoreMatch
 * 
 */
@SuppressWarnings("all")
public final class EntrySemaphoreQuerySpecification extends BaseGeneratedEMFQuerySpecification<EntrySemaphoreMatcher> {
  private EntrySemaphoreQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static EntrySemaphoreQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected EntrySemaphoreMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return EntrySemaphoreMatcher.on(engine);
  }
  
  @Override
  public EntrySemaphoreMatch newEmptyMatch() {
    return EntrySemaphoreMatch.newEmptyMatch();
  }
  
  @Override
  public EntrySemaphoreMatch newMatch(final Object... parameters) {
    return EntrySemaphoreMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Route) parameters[0], (hu.bme.mit.trainbenchmark.railway.Semaphore) parameters[1]);
  }
  
  private static class LazyHolder {
    private final static EntrySemaphoreQuerySpecification INSTANCE = make();
    
    public static EntrySemaphoreQuerySpecification make() {
      return new EntrySemaphoreQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static EntrySemaphoreQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySemaphore";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("route","semaphore");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("route", "hu.bme.mit.trainbenchmark.railway.Route"),new PParameter("semaphore", "hu.bme.mit.trainbenchmark.railway.Semaphore"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      {
      	PBody body = new PBody(this);
      	PVariable var_route = body.getOrCreateVariableByName("route");
      	PVariable var_semaphore = body.getOrCreateVariableByName("semaphore");
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_route, "route"),
      				
      		new ExportedParameter(body, var_semaphore, "semaphore")
      	));
      	new TypeBinary(body, CONTEXT, var_route, var_semaphore, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "entry"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Route.entry");
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
