package br.com.liax.integridadedados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExecutarUpdate {
	public static void executar(Connection c, String update) throws SQLException{
		PreparedStatement stmt = c.prepareStatement(update);
		stmt.executeUpdate();
	}
}
