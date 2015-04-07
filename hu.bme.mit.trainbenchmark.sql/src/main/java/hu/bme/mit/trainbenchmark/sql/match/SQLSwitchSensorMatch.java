package hu.bme.mit.trainbenchmark.sql.match;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLSwitchSensorMatch extends SQLMatch {

	public SQLSwitchSensorMatch(final ResultSet rs) throws SQLException {
		match = new Long[] { rs.getLong(VAR_SW) };
	}

	public Long getSw() {
		return match[0];
	}

}
