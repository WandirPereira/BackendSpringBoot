package com.github.wandirpereira.domain.repository;

import com.github.wandirpereira.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedido extends JpaRepository<ItemPedido, Integer> {

}
