package com.github.wandirpereira.domain.repository;

import com.github.wandirpereira.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos  extends JpaRepository<Produto, Integer> {

}
