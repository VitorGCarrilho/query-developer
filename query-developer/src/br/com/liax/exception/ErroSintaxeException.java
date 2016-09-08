package br.com.liax.exception;

public class ErroSintaxeException extends Exception {

	private static final long serialVersionUID = 4015392177854189543L;
	
	public ErroSintaxeException(String mensagem){
		super(mensagem);
	}
	public ErroSintaxeException(){
		super();
	}
	
}
