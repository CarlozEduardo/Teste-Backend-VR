package com.vr.backend.service;

import com.vr.backend.domain.entity.Cartao;
import com.vr.backend.domain.mapper.CartaoMapper;
import com.vr.backend.domain.model.dto.CartaoDTO;
import com.vr.backend.exception.CartaoJaExisteException;
import com.vr.backend.exception.CartaoNaoEncontradoException;
import com.vr.backend.repository.CartaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@Transactional
public class CartaoService {

    @Autowired
    private CartaoRepository repository;

    public CartaoDTO criar(CartaoDTO dto) {
        buscarCartao(dto.getNumeroCartao(), new CartaoJaExisteException(dto));
        repository.save(CartaoMapper.to(dto));
        return dto;
    }

    public BigDecimal obterSaldo(String numeroCartao) {
        Cartao cartao = repository.findByNumero(numeroCartao)
                .orElseThrow(() -> new CartaoNaoEncontradoException());
        return cartao.getSaldo();
    }

    public Cartao buscarCartao(String numeroCartao, RuntimeException exception) {
        return repository.findByNumero(numeroCartao)
                .orElseThrow(() -> exception);
    }
}
