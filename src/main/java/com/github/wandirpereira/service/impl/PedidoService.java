package com.github.wandirpereira.service.impl;

import com.github.wandirpereira.domain.entity.Pedido;
import com.github.wandirpereira.domain.enums.StatusPedido;
import com.github.wandirpereira.rest.dto.PedidoDTO;
import com.github.wandirpereira.service.IPedidoService;

import java.util.Optional;

public class PedidoService implements IPedidoService {
    @Override
    public Pedido salvar(PedidoDTO dto) {
        return null;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return Optional.empty();
    }

    @Override
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {

    }
}
