package hu.bme.mit.trainbenchmark.sql.match;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLPosLengthMatch extends SQLMatch {

	public SQLPosLengthMatch(final ResultSet rs) throws SQLException {
		match = new Long[] { rs.getLong(VAR_SEGMENT) };
	}

	public Long getSegment() {
		return match[0];
	}

}
