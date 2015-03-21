package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSetMatcher;
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
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.Inequality;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.ConstantValue;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeBinary;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.psystem.queries.QueryInitializationException;

/**
 * A pattern-specific query specification that can instantiate SwitchSetMatcher in a type-safe way.
 * 
 * @see SwitchSetMatcher
 * @see SwitchSetMatch
 * 
 */
@SuppressWarnings("all")
public final class SwitchSetQuerySpecification extends BaseGeneratedEMFQuerySpecification<SwitchSetMatcher> {
  private SwitchSetQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static SwitchSetQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected SwitchSetMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return SwitchSetMatcher.on(engine);
  }
  
  @Override
  public SwitchSetMatch newEmptyMatch() {
    return SwitchSetMatch.newEmptyMatch();
  }
  
  @Override
  public SwitchSetMatch newMatch(final Object... parameters) {
    return SwitchSetMatch.newMatch((hu.bme.mit.trainbenchmark.railway.SwitchPosition) parameters[0]);
  }
  
  private static class LazyHolder {
    private final static SwitchSetQuerySpecification INSTANCE = make();
    
    public static SwitchSetQuerySpecification make() {
      return new SwitchSetQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static SwitchSetQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.switchSet";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("switchPosition");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("switchPosition", "hu.bme.mit.trainbenchmark.railway.SwitchPosition"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      {
      	PBody body = new PBody(this);
      	PVariable var_switchPosition = body.getOrCreateVariableByName("switchPosition");
      	PVariable var_route = body.getOrCreateVariableByName("route");
      	PVariable var_semaphore = body.getOrCreateVariableByName("semaphore");
      	PVariable var_sw = body.getOrCreateVariableByName("sw");
      	PVariable var__virtual_3_ = body.getOrCreateVariableByName(".virtual{3}");
      	PVariable var_swPP = body.getOrCreateVariableByName("swPP");
      	PVariable var_swCP = body.getOrCreateVariableByName("swCP");
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_switchPosition, "switchPosition")
      	));
      	new TypeBinary(body, CONTEXT, var_route, var_semaphore, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "entry"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Route.entry");
      	new TypeBinary(body, CONTEXT, var_route, var_switchPosition, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "follows"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Route.follows");
      	new TypeBinary(body, CONTEXT, var_switchPosition, var_sw, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition", "switch"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/SwitchPosition.switch");
      	new ConstantValue(body, var__virtual_3_, getEnumLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Signal", "GO").getInstance());
      	new TypeBinary(body, CONTEXT, var_semaphore, var__virtual_3_, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Semaphore", "signal"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Semaphore.signal");
      	new TypeBinary(body, CONTEXT, var_switchPosition, var_swPP, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition", "position"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/SwitchPosition.position");
      	new TypeBinary(body, CONTEXT, var_sw, var_swCP, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Switch", "currentPosition"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Switch.currentPosition");
      	new Inequality(body, var_swPP, var_swCP);
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
