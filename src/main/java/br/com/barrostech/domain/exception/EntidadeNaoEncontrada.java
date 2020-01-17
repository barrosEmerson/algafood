package br.com.barrostech.domain.exception;

public class EntidadeNaoEncontrada extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontrada(String message) {
		super(message);
	}

}
