package com.vr.backend.exception;

import com.vr.backend.domain.model.MotivoErroTransacao;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
public class TransacaoNaoAutorizadaException extends RuntimeException {
    private final MotivoErroTransacao motivo;

    public TransacaoNaoAutorizadaException(MotivoErroTransacao motivo) {
        super("Transação não autorizada");
        this.motivo = motivo;
    }
}
