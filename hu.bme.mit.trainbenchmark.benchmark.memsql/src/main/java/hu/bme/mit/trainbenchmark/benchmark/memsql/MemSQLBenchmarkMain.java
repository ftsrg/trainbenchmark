package hu.bme.mit.trainbenchmark.benchmark.memsql;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

public class MemSQLBenchmarkMain {
	public static void main(String[] args) throws IOException, ParseException {
		MemSQLBenchmarkLogic benchmarkLogic = new MemSQLBenchmarkLogic(args);
		benchmarkLogic.runBenchmark();

		// Connection connection = null;
		// try {
		// connection = DriverManager.getConnection(
		// "jdbc:mysql://127.0.0.1:3306/memsql", "root", "");
		// Statement stmt = connection.createStatement();
		// stmt.execute("DROP DATABASE IF EXISTS MemSQL_tutorial2");
		// stmt.execute("CREATE DATABASE MemSQL_tutorial2");
		// stmt.execute("use MemSQL_tutorial2");
		// stmt.execute("CREATE TABLE t(id INT PRIMARY KEY AUTO_INCREMENT, v VARCHAR(10) NOT NULL)");
		// stmt.execute("INSERT INTO t (v) VALUES ('hello'), ('goodbye')");
		// ResultSet rs = stmt.executeQuery("SELECT * FROM t");
		// while (rs.next()) {
		// System.out.println("(('" + rs.getInt("id") + "', '"
		// + rs.getString("v") + "'),)");
		// }
		// } catch (SQLException e) {
		// throw new IOException(e);
		// }

	}
}
