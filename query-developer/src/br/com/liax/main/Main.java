package br.com.liax.main;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;

import br.com.liax.connection.ConexaoManutencao;
import br.com.liax.controller.Controller;
import br.com.liax.exception.ErroSintaxeException;
import br.com.liax.exception.UpdateSemWhereException;
import br.com.liax.integridadedados.GerarPlanilha;
import br.com.liax.integridadedados.LinhasAfetadas;
import br.com.liax.integridadedados.LogComandos;
import br.com.liax.integridadedados.Rollback;
import br.com.liax.utils.Utils;
import br.com.liax.view.FrameDeveloper;

public class Main {
	public static void main(String[] args) throws SQLException {
		Connection c = ConexaoManutencao.getConexao();
		Controller controller = new Controller(c);
		FrameDeveloper window = new FrameDeveloper(controller);
		/*
		 * 
		 update    tbod_associado set id_tipo_movto =  'E' where cd_associado = '90000000' and cd_empresa = '000850';
		 update    tbod_associado set id_tipo_movto =  'A' where cd_associado = '90000000' and cd_empresa = '000850';
		 update    tbod_associado set cd_empresa =  '905126' where cd_associado = '90000000' and cd_empresa = '000850';
		 update tbod_associado set cd_empresa = '500000';
		 update tbod_associado set cd_empresa = '500000' where cd_empresa = '000850';
		 * */ 
		 
	}
}
