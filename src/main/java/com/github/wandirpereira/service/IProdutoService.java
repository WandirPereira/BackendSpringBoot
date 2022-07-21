package com.github.wandirpereira.service;

import com.github.wandirpereira.domain.entity.Produto;

import java.util.List;

public interface IProdutoService {
    Produto buscarProdutoById(Integer id );
    Produto salvar( Produto produto );
    void deletar( Integer id );
    void atualizar(Integer id, Produto produto);
    List<Produto> pesquisar(Produto filtro );

}
