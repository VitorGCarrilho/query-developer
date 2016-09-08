package br.com.liax.integridadedados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.liax.exception.ErroSintaxeException;
import br.com.liax.exception.UpdateSemWhereException;
import br.com.liax.utils.Utils;

public class GerarPlanilha {
	public static void gerarPlanilhas(String update, Connection c, String filePath, String fileName ) throws ErroSintaxeException, SQLException, FileNotFoundException, UnsupportedEncodingException, UpdateSemWhereException{
		String query = Utils.gerarQuery(update);
		List<String> campos = Utils.getTodosCampos(update);
		String cabecalho = getCabecalho(campos);
		
		
		PreparedStatement stmt = c.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		Writer w = null;
		try {			
            File statText = new File(filePath+fileName);
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            w = new BufferedWriter(osw);
            
            w.write(cabecalho+"\n");
            while(rs.next()){
            	String linha = "";
            	for(int i=0; i<campos.size();i++){
            		linha = linha + rs.getString(campos.get(i).replaceAll(" ","")) + ";";
            	}
            	w.write(linha+"\n");
            }
            
            
            w.close();
            
        } catch (IOException e) {	
        	e.printStackTrace();
        }
	}

	private static String getCabecalho(List<String> campos){
		String cabecalho = "";
		for (int i=0; i<campos.size(); i++){
			cabecalho = cabecalho + campos.get(i) + ";";
		}		
		return cabecalho;
	}
}
