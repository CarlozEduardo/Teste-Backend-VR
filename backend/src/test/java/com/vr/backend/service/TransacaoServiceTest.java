package com.vr.backend.service;

import com.vr.backend.domain.entity.Cartao;
import com.vr.backend.domain.model.MotivoErroTransacao;
import com.vr.backend.domain.model.dto.CartaoDTO;
import com.vr.backend.domain.model.dto.TransacaoDTO;
import com.vr.backend.exception.TransacaoNaoAutorizadaException;
import com.vr.backend.repository.CartaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @Mock
    private CartaoRepository repository;

    @Mock
    private CartaoService cartaoService;

    @InjectMocks
    private TransacaoService transacaoService;

    @Test
    void deveRealizarTransacaoComSucesso() {
        Cartao cartao = new Cartao();
        cartao.setSenha("1234");
        cartao.setSaldo(BigDecimal.valueOf(100));

        TransacaoDTO dto = new TransacaoDTO(
                "123",
                "1234",
                BigDecimal.valueOf(50)
        );

        when(cartaoService.buscarCartao(anyString(), any()))
                .thenReturn(cartao);

        transacaoService.realizarTransacao(dto);

        assertEquals(BigDecimal.valueOf(50), cartao.getSaldo());
    }

    @Test
    void deveLancarExcecaoQuandoCartaoInexistente() {
        TransacaoDTO dto = new TransacaoDTO(
                "123",
                "1234",
                BigDecimal.valueOf(50)
        );

        when(cartaoService.buscarCartao(anyString(), any()))
                .thenThrow(
                        new TransacaoNaoAutorizadaException(
                                MotivoErroTransacao.CARTAO_INEXISTENTE
                        )
                );

        TransacaoNaoAutorizadaException ex =
                assertThrows(TransacaoNaoAutorizadaException.class,
                        () -> transacaoService.realizarTransacao(dto));

        assertEquals(MotivoErroTransacao.CARTAO_INEXISTENTE, ex.getMotivo());
    }

    @Test
    void deveLancarExcecaoQuandoSenhaInvalida() {
        Cartao cartao = new Cartao();
        cartao.setSenha("1234");
        cartao.setSaldo(BigDecimal.valueOf(100));

        TransacaoDTO dto = new TransacaoDTO(
                "123",
                "0000",
                BigDecimal.valueOf(50)
        );

        when(cartaoService.buscarCartao(anyString(), any()))
                .thenReturn(cartao);

        TransacaoNaoAutorizadaException ex =
                assertThrows(TransacaoNaoAutorizadaException.class,
                        () -> transacaoService.realizarTransacao(dto));

        assertEquals(MotivoErroTransacao.SENHA_INVALIDA, ex.getMotivo());
    }

    @Test
    void deveLancarExcecaoQuandoSaldoInsuficiente() {
        Cartao cartao = new Cartao();
        cartao.setSenha("1234");
        cartao.setSaldo(BigDecimal.valueOf(30));

        TransacaoDTO dto = new TransacaoDTO(
                "123",
                "1234",
                BigDecimal.valueOf(50)
        );

        when(cartaoService.buscarCartao(anyString(), any()))
                .thenReturn(cartao);

        TransacaoNaoAutorizadaException ex =
                assertThrows(TransacaoNaoAutorizadaException.class,
                        () -> transacaoService.realizarTransacao(dto));

        assertEquals(MotivoErroTransacao.SALDO_INSUFICIENTE, ex.getMotivo());
    }

}
