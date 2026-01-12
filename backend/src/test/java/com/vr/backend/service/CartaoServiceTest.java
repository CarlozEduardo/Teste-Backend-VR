package com.vr.backend.service;

import com.vr.backend.domain.entity.Cartao;
import com.vr.backend.domain.model.dto.CartaoDTO;
import com.vr.backend.exception.CartaoJaExisteException;
import com.vr.backend.exception.CartaoNaoEncontradoException;
import com.vr.backend.repository.CartaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartaoServiceTest {

    @InjectMocks
    private CartaoService service;

    @Mock
    private CartaoRepository repository;

    @Test
    void deveCriarCartao() {
        CartaoDTO dto = new CartaoDTO("123", "1234");

        when(repository.findByNumero("123"))
                .thenReturn(Optional.empty());

        service.criar(dto);

        verify(repository).save(any(Cartao.class));
    }

    @Test
    void deveLancarExcecaoQuandoCartaoJaExiste() {
        CartaoDTO dto = new CartaoDTO("123", "1234");

        when(repository.findByNumero("123"))
                .thenReturn(Optional.of(new Cartao()));

        assertThrows(
                CartaoJaExisteException.class,
                () -> service.criar(dto)
        );

        verify(repository, never()).save(any());
    }

    @Test
    void deveObterSaldoDoCartao() {
        Cartao cartao = Cartao.builder()
                .numero("123")
                .saldo(new BigDecimal("500.00"))
                .build();

        when(repository.findByNumero("123"))
                .thenReturn(Optional.of(cartao));

        BigDecimal saldo = service.obterSaldo("123");

        assertEquals(new BigDecimal("500.00"), saldo);
    }

    @Test
    void deveLancarExcecaoQuandoCartaoNaoExiste() {
        when(repository.findByNumero("999"))
                .thenReturn(Optional.empty());

        assertThrows(
                CartaoNaoEncontradoException.class,
                () -> service.obterSaldo("999")
        );
    }

    @Test
    void deveBuscarCartaoComSucesso() {
        Cartao cartao = new Cartao();
        cartao.setNumero("123");

        when(repository.findByNumero("123"))
                .thenReturn(Optional.of(cartao));

        Cartao resultado =
                service.buscarCartao("123", new RuntimeException());

        assertEquals("123", resultado.getNumero());
    }

    @Test
    void deveLancarExcecaoCustomizadaQuandoBuscarCartaoNaoExiste() {
        when(repository.findByNumero("999"))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                new RuntimeException("Erro customizado");

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> service.buscarCartao("999", exception)
        );

        assertEquals("Erro customizado", ex.getMessage());
    }
}
