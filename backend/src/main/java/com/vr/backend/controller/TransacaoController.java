package com.vr.backend.controller;

import com.vr.backend.domain.model.dto.TransacaoDTO;
import com.vr.backend.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping
    public ResponseEntity<String> realizarTransacao(@RequestBody TransacaoDTO dto) {
        service.realizarTransacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }
}
