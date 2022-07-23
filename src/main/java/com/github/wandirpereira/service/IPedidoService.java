package com.github.wandirpereira.service;

import com.github.wandirpereira.domain.entity.Pedido;
import com.github.wandirpereira.domain.enums.StatusPedido;
import com.github.wandirpereira.rest.dto.PedidoDTO;

import java.util.Optional;

public interface IPedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);

}
