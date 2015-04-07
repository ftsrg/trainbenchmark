package hu.bme.mit.trainbenchmark.sql.match;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_TE1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_TE2;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLSemaphoreNeighborMatch extends SQLMatch {

	public SQLSemaphoreNeighborMatch(final ResultSet rs) throws SQLException {
		match = new Long[] { rs.getLong(VAR_SEMAPHORE), rs.getLong(VAR_ROUTE1), rs.getLong(VAR_ROUTE2), rs.getLong(VAR_SENSOR1),
				rs.getLong(VAR_SENSOR2), rs.getLong(VAR_TE1), rs.getLong(VAR_TE2), };
	}

	public Long getSemaphore() {
		return match[0];
	}

	public Long getRoute1() {
		return match[1];
	}

	public Long getRoute2() {
		return match[2];
	}

	public Long getSensor1() {
		return match[3];
	}

	public Long getSensor2() {
		return match[4];
	}

	public Long getTe1() {
		return match[5];
	}

	public Long getTe2() {
		return match[6];
	}
}
