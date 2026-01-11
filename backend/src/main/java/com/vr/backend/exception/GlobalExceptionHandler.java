package com.vr.backend.exception;

import com.vr.backend.domain.model.MotivoErroTransacao;
import com.vr.backend.domain.model.dto.CartaoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CartaoJaExisteException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public CartaoDTO handleCartaoJaExisteException(CartaoJaExisteException ex) {
        return ex.getDto();
    }

    @ExceptionHandler(TransacaoNaoAutorizadaException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public MotivoErroTransacao handleTransacaoNaoAutorizadaException(TransacaoNaoAutorizadaException ex) {
        return ex.getMotivo();
    }

    @ExceptionHandler(CartaoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleCartaoNaoEncontradoException() {
    }
}
