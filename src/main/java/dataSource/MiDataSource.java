package dataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MiDataSource {
	private static DataSource dataSource = null;
	static {
		try {
			Context ctx = (Context) (new InitialContext().lookup("java:comp/env/"));
			dataSource = (DataSource) ctx.lookup("jdbc/MySQLDS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	private MiDataSource() {
	}
}
