package com.github.wandirpereira.service.impl;

import com.github.wandirpereira.domain.entity.Cliente;
import com.github.wandirpereira.domain.repository.ClientesRepository;
import com.github.wandirpereira.service.IClienteService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class ClienteService implements IClienteService {

    private final ClientesRepository clientesRepository;

    public ClienteService(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @Override
    public Cliente buscarClienteById(Integer id) {
          return clientesRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Cliente não encontrado"));
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        return clientesRepository.save(cliente);
    }

    @Override
    public void deletar(Integer id) {
        clientesRepository.findById(id)
                .map( cliente -> {
                    clientesRepository.delete(cliente );
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado") );
    }

    @Override
    public void update(Integer id, Cliente cliente) {
        clientesRepository
                .findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientesRepository.save(cliente);
                    return cliente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado") );
    }

    @Override
    public List<Cliente> find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        return clientesRepository.findAll(example);
    }
}
