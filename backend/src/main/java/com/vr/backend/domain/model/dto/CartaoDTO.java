package com.vr.backend.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CartaoDTO {
    private String numeroCartao;
    private String senha;
}
