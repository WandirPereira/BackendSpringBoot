package com.github.wandirpereira.service;

import com.github.wandirpereira.domain.entity.Cliente;

import java.util.List;

public interface IClienteService {

    Cliente buscarClienteById(Integer id );
    Cliente salvar( Cliente cliente );
    void deletar( Integer id );
    void atualizar(Integer id, Cliente cliente );
    List<Cliente> pesquisar(Cliente filtro );

}
