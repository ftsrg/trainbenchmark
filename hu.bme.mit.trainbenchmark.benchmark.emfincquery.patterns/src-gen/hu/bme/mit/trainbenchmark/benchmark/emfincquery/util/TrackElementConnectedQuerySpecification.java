package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.TrackElementConnectedMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.TrackElementConnectedMatcher;
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
 * A pattern-specific query specification that can instantiate TrackElementConnectedMatcher in a type-safe way.
 * 
 * @see TrackElementConnectedMatcher
 * @see TrackElementConnectedMatch
 * 
 */
@SuppressWarnings("all")
public final class TrackElementConnectedQuerySpecification extends BaseGeneratedEMFQuerySpecification<TrackElementConnectedMatcher> {
  private TrackElementConnectedQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static TrackElementConnectedQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected TrackElementConnectedMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return TrackElementConnectedMatcher.on(engine);
  }
  
  @Override
  public TrackElementConnectedMatch newEmptyMatch() {
    return TrackElementConnectedMatch.newEmptyMatch();
  }
  
  @Override
  public TrackElementConnectedMatch newMatch(final Object... parameters) {
    return TrackElementConnectedMatch.newMatch((hu.bme.mit.trainbenchmark.railway.TrackElement) parameters[0], (hu.bme.mit.trainbenchmark.railway.TrackElement) parameters[1]);
  }
  
  private static class LazyHolder {
    private final static TrackElementConnectedQuerySpecification INSTANCE = make();
    
    public static TrackElementConnectedQuerySpecification make() {
      return new TrackElementConnectedQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static TrackElementConnectedQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.trackElementConnected";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("te1","te2");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("te1", "hu.bme.mit.trainbenchmark.railway.TrackElement"),new PParameter("te2", "hu.bme.mit.trainbenchmark.railway.TrackElement"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      {
      	PBody body = new PBody(this);
      	PVariable var_te1 = body.getOrCreateVariableByName("te1");
      	PVariable var_te2 = body.getOrCreateVariableByName("te2");
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_te1, "te1"),
      				
      		new ExportedParameter(body, var_te2, "te2")
      	));
      	new TypeBinary(body, CONTEXT, var_te1, var_te2, getFeatureLiteral("http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl", "TrackElement", "TrackElement_connectsTo"), "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl/TrackElement.TrackElement_connectsTo");
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
