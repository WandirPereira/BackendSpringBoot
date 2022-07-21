package com.github.wandirpereira.domain.repository;

import com.github.wandirpereira.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto, Integer> {

}
