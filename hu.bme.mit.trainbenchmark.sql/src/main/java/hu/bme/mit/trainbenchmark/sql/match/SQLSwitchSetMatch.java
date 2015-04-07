package hu.bme.mit.trainbenchmark.sql.match;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SWP;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLSwitchSetMatch extends SQLMatch {

	public SQLSwitchSetMatch(final ResultSet rs) throws SQLException {
		match = new Long[] { rs.getLong(VAR_SEMAPHORE), rs.getLong(VAR_ROUTE), rs.getLong(VAR_SWP), rs.getLong(VAR_SW) };
	}

	public Long getSemaphore() {
		return match[0];
	}

	public Long getRoute() {
		return match[1];
	}

	public Long getSwitchPosition() {
		return match[2];
	}

	public Long getSw() {
		return match[3];
	}

}
