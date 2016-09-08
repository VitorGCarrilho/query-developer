package br.com.liax.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.liax.exception.ErroSintaxeException;
import br.com.liax.exception.UpdateSemWhereException;

public class Utils {
	public static String tratarQuery(String codigo){
		codigo = codigo.replaceAll("\t"," ");
		String aux = null;
		aux = codigo.replaceAll("  "," ");
		while(aux != codigo){
			codigo = codigo.replaceAll("  "," ");
			aux = codigo.replaceAll("  "," ");
		}
		return codigo;		
	}	
	public static void validaQuery(String update) throws ErroSintaxeException, UpdateSemWhereException{
		String aux[] = update.split(" ");
		int countWhere = 0;
		for (String token : aux){
			if ("where".equals(token))
				countWhere += 1;
		}
		if (countWhere >  1){
			throw new ErroSintaxeException("Erro de sintaxe, a query contem mais de um 'Where'");
		} else if (countWhere == 0){
			throw new UpdateSemWhereException("Erro, Update sem where nao pode ser executado");
		}
		
	}	
	public static String gerarWhere(String update) throws ErroSintaxeException, UpdateSemWhereException {
		validaQuery(update);
		String aux[] = update.toUpperCase().split("WHERE");
		return " WHERE " + aux[1];
	}
	public static String gerarFromTable(String update){
		String aux[] = update.split(" ");
		return " FROM "+aux[1]+" ";
	}
	
	
	public static String gerarQuery(String update) throws ErroSintaxeException, UpdateSemWhereException{
		String where = gerarWhere(update);
		String from = gerarFromTable(update);
		String campo = "";
		List<String> campos = getTodosCampos(update);
		for (int i = 0; i<campos.size();i++){
			campo = campo + (String) campos.get(i);
			if ( i+1 < campos.size()) {
				campo += ", ";
			}
		}
		String query = "SELECT "+ campo + from +where;
		return query;
	}
	
	public static List<String> getTodosCampos(String update){
		List<String> campos = new ArrayList<String>();
		update = Utils.tratarQuery(update.toUpperCase());
		String alteracoes = update.substring(update.indexOf("SET") + "SET".length(), update.indexOf("WHERE"));
		String alteracao[] = alteracoes.split(",");
		
		String clausulas = update.substring(update.indexOf("WHERE") + "WHERE".length(), update.length());
		String clausula[] = clausulas.split("AND");
		
		for (String alt : alteracao){
			String token[] = alt.split("=");
			campos.add(token[0]);
		}
		
		for (String cla : clausula){
			String campo = cla.substring(0,encontarFimCampo(cla));
			campos.add(campo);
		}
		return campos;
	}
	public static List<String> getCamposRollback(String update){
		List<String> campos = new ArrayList<String>();
		update = Utils.tratarQuery(update.toUpperCase());
		String alteracoes = update.substring(update.indexOf("SET") + "SET".length(), update.indexOf("WHERE"));
		String alteracao[] = alteracoes.split(",");		
		for (String alt : alteracao){
			String token[] = alt.split("=");
			campos.add(token[0]);
		}		
		return campos;
	}
	private static int encontarFimCampo(String clausula){
		char caracteres[] = clausula.toCharArray();
		for (int i = 0; i<caracteres.length;i++){
			if ((caracteres[i] == ' ' && i != 0)|| caracteres[i] == '=' ||
					caracteres[i] == '<' || caracteres[i] == '!')
				return i;
		}
		return caracteres.length;
	}
}
