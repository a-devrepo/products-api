package br.com.productsapi.exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException{
    public ProdutoNaoEncontradoException(String message){
        super(message);

    }
}
