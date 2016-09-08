package br.com.liax.integridadedados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.liax.exception.ErroSintaxeException;
import br.com.liax.exception.UpdateSemWhereException;
import br.com.liax.utils.Utils;

public class LinhasAfetadas {
	
	public static long getNumLinhasAfetadas(Connection c, String update) throws ErroSintaxeException, SQLException, UpdateSemWhereException{
		long numLinhasAfetadas = 0;
		update = Utils.tratarQuery(update);
		String query = getQuery(update);			
		PreparedStatement stmt = c.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();		
		if(rs.next()){
			numLinhasAfetadas = rs.getLong("numLinhasAfetadas");
		}
		return numLinhasAfetadas;
	}
	private static String getQuery(String update) throws ErroSintaxeException, UpdateSemWhereException{
		String where = Utils.gerarWhere(update);
		String from = Utils.gerarFromTable(update);				
		String query = "SELECT COUNT(*) as numLinhasAfetadas "+ from + where;		
		return query;
	}
}
