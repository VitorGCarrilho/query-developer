package br.com.liax.integridadedados;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.liax.exception.ErroSintaxeException;
import br.com.liax.exception.UpdateSemWhereException;
import br.com.liax.utils.EscreverArquivo;
import br.com.liax.utils.Utils;

public class Rollback {
	public static void gerarRollback(Connection c, String update, String filePath, String fileName) throws ErroSintaxeException, UpdateSemWhereException, SQLException{
		List<String> rollbacks = new ArrayList<String>();
		String aux[] = update.split(" ");
		String query = Utils.gerarQuery(update);
		List<String> campos = Utils.getCamposRollback(update);
		String where = Utils.gerarWhere(update);
		PreparedStatement stmt = c.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		 while(rs.next()){
			 String rollback = "UPDATE " + aux[1].toUpperCase() + " SET ";
         	for(int i=0; i<campos.size();i++){
         		String campo = campos.get(i);
         		rollback = rollback + campo + " = '" + rs.getString(campo.replaceAll(" ","")) +"' ";
         		if(i!=campos.size()-1) {
         			rollback = rollback + ", ";
         		}
         	}
         	rollback = rollback + where;
         	rollbacks.add(rollback);
         }
		 StringBuilder sb = new StringBuilder();
		 for(int i=0; i<rollbacks.size();i++){
     		sb.append(rollbacks.get(i)+";\n");
     	}   
		 EscreverArquivo.escrever(sb.toString(), filePath, fileName);
	}
}
