package br.com.liax.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class EscreverArquivo {
	public static void escrever(String conteudo, String filePath, String  fileName){
		Writer w = null;
		try {			
            File statText = new File(filePath+fileName);
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            w = new BufferedWriter(osw);
        	w.write(conteudo);       
            w.close();
        } catch (IOException e) {	
        	e.printStackTrace();
        }
	}
}
