package com.vr.backend.domain.mapper;


import com.vr.backend.domain.entity.Cartao;
import com.vr.backend.domain.model.dto.CartaoDTO;

import java.math.BigDecimal;

public class CartaoMapper {
    public static Cartao to(CartaoDTO dto) {
        return Cartao.builder()
                .numero(dto.getNumeroCartao())
                .senha(dto.getSenha())
                .saldo(BigDecimal.valueOf(500.00))
                .build();
    }

    public static CartaoDTO toDTO(Cartao cartao) {
        return CartaoDTO.builder()
                .numeroCartao(cartao.getNumero())
                .senha(cartao.getSenha())
                .build();
    }
}
