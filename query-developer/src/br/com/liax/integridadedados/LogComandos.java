package br.com.liax.integridadedados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogComandos {
	public static void inserirLog(Connection c,String nrChamado, String cdUsuario, String update) throws SQLException{
		PreparedStatement stmt;
		String insert = "insert into lxtmp_log_comandos_rodados (nr_chamado, dt_comando_rodado, cd_usuario, ds_comando)"
				+ " values ( ?, sysdate() ,? ,? )";
		stmt = c.prepareStatement(insert);
		stmt.setString(1, nrChamado);
		stmt.setString(2, cdUsuario);
		stmt.setString(3, update);
		
		stmt.executeUpdate();
	}
}
