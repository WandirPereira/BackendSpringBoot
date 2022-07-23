package com.github.wandirpereira.rest.controller;

import com.github.wandirpereira.domain.entity.Cliente;
import com.github.wandirpereira.service.impl.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController( ClienteService clienteService ) {
        this.clienteService = clienteService;
    }

    @GetMapping("{id}")
    public Cliente getClienteById( @PathVariable Integer id ){
        return clienteService.buscarClienteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save( @RequestBody Cliente cliente ){
        return clienteService.salvar(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ){
        clienteService.deletar(id);

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id,
                        @RequestBody Cliente cliente ){
        clienteService.atualizar(id, cliente);
    }

    @GetMapping
    public List<Cliente> find(Cliente filtro ){
        return clienteService.pesquisar(filtro);
    }

}
