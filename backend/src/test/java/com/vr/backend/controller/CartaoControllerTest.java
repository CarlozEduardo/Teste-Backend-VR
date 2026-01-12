package com.vr.backend.controller;

import com.vr.backend.domain.model.dto.CartaoDTO;
import com.vr.backend.service.CartaoService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartaoControllerTest {

    @InjectMocks
    private CartaoController controller;

    @Mock
    private CartaoService cartaoService;

    @Test
    void deveCriarCartaoComSucesso() {
        CartaoDTO dto = new CartaoDTO("123", "1234");

        when(cartaoService.criar(dto)).thenReturn(dto);

        ResponseEntity<CartaoDTO> response = controller.criar(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("123", response.getBody().getNumeroCartao());
        assertEquals("1234", response.getBody().getSenha());

        verify(cartaoService).criar(dto);
    }

    @Test
    void deveObterSaldoComSucesso() {
        when(cartaoService.obterSaldo("123"))
                .thenReturn(new BigDecimal("500.00"));

        ResponseEntity<BigDecimal> response =
                controller.obterSaldo("123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new BigDecimal("500.00"), response.getBody());

        verify(cartaoService).obterSaldo("123");
    }
}