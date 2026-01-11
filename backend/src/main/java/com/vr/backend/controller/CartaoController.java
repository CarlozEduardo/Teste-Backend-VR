package com.vr.backend.controller;

import com.vr.backend.domain.model.dto.CartaoDTO;
import com.vr.backend.service.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/cartoes")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<CartaoDTO> criar(@RequestBody CartaoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cartaoService.criar(dto));
    }

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<BigDecimal> obterSaldo(
            @PathVariable String numeroCartao) {
        return ResponseEntity.ok(cartaoService.obterSaldo(numeroCartao));
    }
}