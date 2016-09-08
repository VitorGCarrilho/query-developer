package br.com.liax.view;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class FileChooser  {
	public static String actionPerformed(ActionEvent e){
        JFileChooser chooser = new JFileChooser();
        chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int res = chooser.showOpenDialog(null);
        
        if(res == JFileChooser.APPROVE_OPTION){
            File arquivo = chooser.getSelectedFile();
            return arquivo.getPath();
        }
        else{
            JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado.");
            return null;
        }
    }
}