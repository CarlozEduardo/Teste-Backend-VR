package com.vr.backend.service;

import com.vr.backend.domain.entity.Cartao;
import com.vr.backend.domain.model.MotivoErroTransacao;
import com.vr.backend.domain.model.dto.TransacaoDTO;
import com.vr.backend.exception.TransacaoNaoAutorizadaException;
import com.vr.backend.repository.CartaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class TransacaoService {

    @Autowired
    private CartaoRepository repository;

    @Transactional
    public void realizarTransacao(TransacaoDTO dto) {

        Cartao cartao = repository.findByNumero(dto.getNumeroCartao())
                .orElseThrow(() ->
                        new TransacaoNaoAutorizadaException(
                                MotivoErroTransacao.CARTAO_INEXISTENTE
                        )
                );

        validarSenha(cartao, dto.getSenhaCartao());
        validarSaldo(cartao, dto.getValor());

        cartao.debitarSaldo(dto.getValor());
    }

    private void validarSenha(Cartao cartao, String senha) {
        Optional.of(cartao)
                .filter(c -> c.getSenha().equals(senha))
                .orElseThrow(() ->
                        new TransacaoNaoAutorizadaException(
                                MotivoErroTransacao.SENHA_INVALIDA
                        )
                );
    }

    private void validarSaldo(Cartao cartao, BigDecimal valor) {
        Optional.of(cartao)
                .filter(c -> c.getSaldo().compareTo(valor) >= 0)
                .orElseThrow(() ->
                        new TransacaoNaoAutorizadaException(
                                MotivoErroTransacao.SALDO_INSUFICIENTE
                        )
                );
    }
}
