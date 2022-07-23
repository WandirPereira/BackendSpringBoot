package com.github.wandirpereira.rest.controller;

import com.github.wandirpereira.domain.entity.Produto;
import com.github.wandirpereira.service.impl.ProdutoService;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto save( @RequestBody @Valid Produto produto ){
        return produtoService.salvar(produto);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update( @PathVariable Integer id,
                        @RequestBody @Valid Produto produto ){
        produtoService.atualizar(id, produto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id){
        produtoService.deletar(id);
    }

    @GetMapping("{id}")
    public Produto getById(@PathVariable Integer id){
        return produtoService.buscarProdutoById(id);
    }

    @GetMapping
    public List<Produto> find(Produto filtro ){
        return produtoService.pesquisar(filtro);
    }
}
