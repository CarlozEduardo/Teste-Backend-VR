package com.vr.backend.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class TransacaoDTO {
    private String numeroCartao;
    private String senhaCartao;
    private BigDecimal valor;
}
