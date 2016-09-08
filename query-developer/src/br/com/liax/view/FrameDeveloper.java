package br.com.liax.view;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JToolBar;

import br.com.liax.controller.Controller;
import br.com.liax.exception.ErroSintaxeException;
import br.com.liax.exception.UpdateSemWhereException;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.TextArea;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FrameDeveloper {

	private Controller controller;
	private JFrame frame;
	private JTextField tfChamado;
	private JTextField tfUsuario;
	private JTextField tfDiretorio;
	private JTextField tfNomePlanilha;
	private JTextField tfNomeArquivoRollback;
	private JTextField tfNomeArquivo;
	private TextArea textArea;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameDeveloper window = new FrameDeveloper();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the application.
	 */
	public FrameDeveloper() {
		initialize();
	}

	public FrameDeveloper(Controller controller) {
		this.controller = controller;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		frame = new JFrame();
		frame.setBounds(100, 100, width, height - 200);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);

		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mnArquivo.add(mntmAbrir);

		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mnArquivo.add(mntmSalvar);

		JMenuItem mntmSalvarComo = new JMenuItem("Salvar como");
		mnArquivo.add(mntmSalvarComo);

		JMenu mnMais = new JMenu("Mais");
		menuBar.add(mnMais);

		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mnMais.add(mntmSobre);
		frame.getContentPane().setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, frame.getWidth(), 40);
		toolBar.setBorderPainted(false);
		toolBar.setEnabled(false);
		frame.getContentPane().add(toolBar);

		JButton btnExecutar = new JButton("Executar Update");
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executaUpdate();
			}
		});
		btnExecutar.setIcon(new ImageIcon("C:\\Users\\vitor\\Desktop\\play-icon.png"));
		toolBar.add(btnExecutar);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 55, 350, frame.getHeight());
		frame.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Chamado", null, panel, null);
		panel.setLayout(null);

		JLabel lblChamado = new JLabel("Chamado: ");
		lblChamado.setBounds(12, 41, 300, 30);
		panel.add(lblChamado);

		tfChamado = new JTextField();
		tfChamado.setBounds(12, 84, 300, 22);
		panel.add(tfChamado);
		tfChamado.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(12, 109, 300, 30);
		panel.add(lblUsuario);

		tfUsuario = new JTextField();
		tfUsuario.setBounds(12, 152, 300, 22);
		panel.add(tfUsuario);
		tfUsuario.setColumns(10);

		JLabel lblDiretorio = new JLabel("Diretorio:");
		lblDiretorio.setBounds(12, 426, 300, 30);
		panel.add(lblDiretorio);

		tfDiretorio = new JTextField();
		tfDiretorio.setBounds(12, 469, 300, 22);
		panel.add(tfDiretorio);
		tfDiretorio.setColumns(10);
		tfDiretorio.setEditable(false);

		JButton btnAlterarDiretorio = new JButton("Alterar Diretorio");
		btnAlterarDiretorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String diretorio = FileChooser.actionPerformed(e);
				tfDiretorio.setText(diretorio);
			}
		});
		btnAlterarDiretorio.setBounds(138, 516, 174, 25);
		panel.add(btnAlterarDiretorio);

		JLabel lblNomeDaPlanilha = new JLabel("Nome da Planilha:");
		lblNomeDaPlanilha.setBounds(12, 277, 300, 30);
		panel.add(lblNomeDaPlanilha);

		tfNomePlanilha = new JTextField();
		tfNomePlanilha.setBounds(12, 320, 300, 22);
		panel.add(tfNomePlanilha);
		tfNomePlanilha.setColumns(10);

		JLabel lblNomeDoArquivo = new JLabel("Nome do arquivo de Rollback:");
		lblNomeDoArquivo.setBounds(12, 355, 207, 16);
		panel.add(lblNomeDoArquivo);

		tfNomeArquivoRollback = new JTextField();
		tfNomeArquivoRollback.setBounds(12, 391, 300, 22);
		panel.add(tfNomeArquivoRollback);
		tfNomeArquivoRollback.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\vitor\\Desktop\\liax-logo.png"));
		lblNewLabel.setBounds(-14, 741, 370, 122);
		panel.add(lblNewLabel);

		JLabel lblNomeArquivo = new JLabel("Nome Arquivo: ");
		lblNomeArquivo.setBounds(12, 187, 300, 30);
		panel.add(lblNomeArquivo);

		tfNomeArquivo = new JTextField();
		tfNomeArquivo.setBounds(12, 230, 300, 22);
		panel.add(tfNomeArquivo);
		tfNomeArquivo.setColumns(10);

		textArea = new TextArea();
		textArea.setBounds(456, 82, 1421, 853);
		frame.getContentPane().add(textArea);

	}

	/*
	 * 
	 * private JTextField tfChamado; private JTextField tfUsuario; private
	 * JTextField tfDiretorio; private JTextField tfNomePlanilha; private
	 * JTextField tfNomeArquivoRollback; private JTextField tfNomeArquivo;
	 **/
	private boolean validaCampos() {
		if (tfChamado.getText().replaceAll(" ", "").isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Campo Chamado na aba \"Chamado\" não deve estar nulo!");
			return false;
		}
		if (tfUsuario.getText().replaceAll(" ", "").isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Campo Usuario na aba \"Chamado\" não deve estar nulo!");
			return false;
		}
		if (tfDiretorio.getText().replaceAll(" ", "").isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Campo Diretorio na aba \"Chamado\" não deve estar nulo!");
			return false;
		}
		if (tfNomePlanilha.getText().replaceAll(" ", "").isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Campo Nome da Planilha na aba \"Chamado\" não deve estar nulo!");
			return false;
		}
		if (tfNomeArquivoRollback.getText().replaceAll(" ", "").isEmpty()) {
			JOptionPane.showMessageDialog(frame,
					"Campo Nome do arquivo de Rollback na aba \"Chamado\" não deve estar nulo!");
			return false;
		}
		if (tfNomeArquivo.getText().replaceAll(" ", "").isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Campo Nome do arquivo na aba \"Chamado\" não deve estar nulo!");
			return false;
		}
		return true;
	}

	private void executaUpdate() {
		if (validaCampos()) {
			String conteudo = textArea.getText();
			String updates[] = conteudo.split(";");
			for (int i=0; i<updates.length; i++) {
				String update = updates[i];
				try {
					if (!update.replaceAll(" ", "").isEmpty()) {
						long linhasAfetadas = controller.getLinhasAfetadas(update);
						Object[] options = { "Sim, desejo", "Não, Obrigado!", "Não, sou estagiario!" };
						int prosseguir = JOptionPane.showOptionDialog(frame,
								linhasAfetadas + " linha(s) afetada(s), deseja prossegir?", "Confirmacao",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
								options[2]);
						if (prosseguir == 0) {
							String nrChamado = tfChamado.getText();
							String usuario = tfUsuario.getText();
							String filePath = tfDiretorio.getText();
							String fileName = tfNomeArquivo.getText();
							String conteudoTextArea = textArea.getText();
							String rollback = tfNomeArquivoRollback.getText();
							String planilha = tfNomePlanilha.getText();
							if (updates.length>1){
								rollback = rollback + "_" + (i+1);
								planilha = planilha + "_" + (i+1);
							}

							controller.fazerUpdate(update, nrChamado, usuario, filePath, fileName, conteudoTextArea,
									rollback, planilha);
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Query vazia!");
					}

				} catch (ErroSintaxeException e) {
					JOptionPane.showMessageDialog(frame, "Erro ao tentar reconhecer a sintaxe", "Erro na sintaxe",
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} catch (UpdateSemWhereException e) {
					JOptionPane.showMessageDialog(frame, "CUIDADO! Não posso permir um Update sem where!!!",
							"UPDATE SEM WHERE", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(frame, "Erro com o SQL", "sql Exception", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(frame, "Erro FileNotFoundException", "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					JOptionPane.showMessageDialog(frame, "Erro UnsupportedEncodingException", "UnsupportedEncodingException", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				

			}
		}
	}
}
