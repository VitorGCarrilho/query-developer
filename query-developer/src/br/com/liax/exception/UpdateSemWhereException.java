package br.com.liax.exception;

public class UpdateSemWhereException extends Exception{

	private static final long serialVersionUID = 2788518924249888979L;
	
	public UpdateSemWhereException(){
		super();
	}
	public UpdateSemWhereException(String mensagem){
		super(mensagem);
	}
	
}
