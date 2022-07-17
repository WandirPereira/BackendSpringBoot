package com.github.wandirpereira.domain.repository;

import com.github.wandirpereira.domain.entity.Cliente;
import com.github.wandirpereira.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

}
