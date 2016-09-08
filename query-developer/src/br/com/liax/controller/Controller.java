package br.com.liax.controller;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;

import br.com.liax.exception.ErroSintaxeException;
import br.com.liax.exception.UpdateSemWhereException;
import br.com.liax.integridadedados.ExecutarUpdate;
import br.com.liax.integridadedados.GerarPlanilha;
import br.com.liax.integridadedados.LinhasAfetadas;
import br.com.liax.integridadedados.LogComandos;
import br.com.liax.integridadedados.Rollback;
import br.com.liax.utils.EscreverArquivo;
import br.com.liax.utils.Utils;

public class Controller {

	private Connection c;

	public Controller(Connection c) {
		this.c = c;
	}

	public long getLinhasAfetadas(String update) throws ErroSintaxeException, SQLException, UpdateSemWhereException {
		return LinhasAfetadas.getNumLinhasAfetadas(c, update);
	}

	public void fazerUpdate(String update, String nrChamado, String usuario, String filePath, String fileName,String conteudo,
			String rollback, String planilha) throws FileNotFoundException, UnsupportedEncodingException,
			ErroSintaxeException, SQLException, UpdateSemWhereException {
		
		update = Utils.tratarQuery(update);
		System.out.println(update);
		GerarPlanilha.gerarPlanilhas(update, c, filePath + "\\", planilha+".csv");
		LogComandos.inserirLog(c, nrChamado, usuario, update);
		Rollback.gerarRollback(c, update, filePath + "\\", rollback + ".sql");
		EscreverArquivo.escrever(conteudo, filePath +"\\", fileName+".sql");
		ExecutarUpdate.executar(c,update);
	}
}
