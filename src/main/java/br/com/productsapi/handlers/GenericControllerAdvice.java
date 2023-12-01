package br.com.productsapi.handlers;

import br.com.productsapi.exceptions.ProdutoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericControllerAdvice {
    @ExceptionHandler({ProdutoNaoEncontradoException.class})
    public ResponseEntity<String> produtoNaoEncontradoExceptionHandler(final ProdutoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
    }
}
