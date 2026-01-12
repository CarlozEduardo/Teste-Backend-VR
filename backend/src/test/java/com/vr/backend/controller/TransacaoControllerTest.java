package com.vr.backend.controller;

import com.vr.backend.domain.model.dto.TransacaoDTO;
import com.vr.backend.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransacaoControllerTest {

    @InjectMocks
    private TransacaoController controller;

    @Mock
    private TransacaoService service;

    @Test
    void deveRealizarTransacaoComSucesso() {
        TransacaoDTO dto =
                new TransacaoDTO("123", "1234", new BigDecimal("10"));

        ResponseEntity<String> response =
                controller.realizarTransacao(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("OK", response.getBody());

        verify(service).realizarTransacao(dto);
    }
}
