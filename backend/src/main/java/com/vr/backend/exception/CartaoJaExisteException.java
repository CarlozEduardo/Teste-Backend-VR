package com.vr.backend.exception;

import com.vr.backend.domain.model.dto.CartaoDTO;
import lombok.Data;

@Data
public class CartaoJaExisteException extends RuntimeException {
    private final CartaoDTO dto;

    public CartaoJaExisteException(CartaoDTO dto) {
        super("Cartão já existe");
        this.dto = dto;
    }
}
