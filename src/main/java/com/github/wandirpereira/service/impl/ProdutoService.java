package com.github.wandirpereira.service.impl;

import com.github.wandirpereira.domain.entity.Produto;
import com.github.wandirpereira.domain.repository.ProdutosRepository;
import com.github.wandirpereira.service.IProdutoService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class ProdutoService implements IProdutoService {

    private final ProdutosRepository produtosRepository;

      public ProdutoService(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    @Override
    public Produto buscarProdutoById(Integer id) {
        return produtosRepository
                .findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto não encontrado."));
    }

    @Override
    public Produto salvar(Produto produto) {
        return produtosRepository.save(produto);
    }

    @Override
    public void deletar(Integer id) {
        produtosRepository
                .findById(id)
                .map( p -> {
                    produtosRepository.delete(p);
                    return Void.TYPE;
                }).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado."));
    }

    @Override
    public void atualizar(Integer id, Produto produto) {
        produtosRepository
                .findById(id)
                .map( p -> {
                    produto.setId(p.getId());
                    produtosRepository.save(produto);
                    return produto;
                }).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado."));
    }

    @Override
    public List<Produto> pesquisar(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        return produtosRepository.findAll(example);
    }
}
