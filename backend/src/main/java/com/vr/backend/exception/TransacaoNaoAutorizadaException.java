package com.vr.backend.exception;

import com.vr.backend.domain.model.MotivoErroTransacao;
import lombok.Data;

@Data
public class TransacaoNaoAutorizadaException extends RuntimeException {
    private final MotivoErroTransacao motivo;

    public TransacaoNaoAutorizadaException(MotivoErroTransacao motivo) {
        super("Transação não autorizada");
        this.motivo = motivo;
    }
}
