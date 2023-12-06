package br.com.productsapi.exceptions;

public class ProdutoForaDeEstoqueException extends RuntimeException{
    public ProdutoForaDeEstoqueException(String message){
        super(message);

    }
}
