/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.drools;

import java.util.HashSet;
import java.util.Set;

import org.kie.api.runtime.rule.Row;
import org.kie.api.runtime.rule.ViewChangedEventListener;

public class ResultListener<T> implements ViewChangedEventListener {
	String resultVariable;

	private Set<T> matching;
	int added, removed, updated;

	public ResultListener(String resultVariable) {
		super();
		matching = new HashSet<T>();
		this.resultVariable = resultVariable;
		added = removed = updated = 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void rowDeleted(Row row) {
		T result = (T) row.get(resultVariable);
		matching.remove(result);
		removed++;
	}

	@Override
	public void rowInserted(Row row) {
		@SuppressWarnings("unchecked")
		T result = (T) row.get(resultVariable);
		matching.add(result);
		added++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void rowUpdated(Row row) {
		matching.add((T) row.get(resultVariable));
		updated++;
	}

	public Set<T> getMatching() {
		return matching;
	}

	public int getAdded() {
		return added;
	}

	public int getRemoved() {
		return removed;
	}

	public int getUpdated() {
		return updated;
	}

	public int getNumberOfChanges() {
		return added + removed + updated;
	}

}
