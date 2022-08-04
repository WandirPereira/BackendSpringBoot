package com.github.wandirpereira.rest.controller;

import com.github.wandirpereira.domain.entity.Cliente;
import com.github.wandirpereira.service.impl.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/clientes")
@Api("API Clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController( ClienteService clienteService ) {
        this.clienteService = clienteService;
    }

    @GetMapping("{id}")
    @ApiOperation("Obter detalhes de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontrado!"),
            @ApiResponse(code = 404, message = "Cliente não encontrado para o ID informado!"),
    })
    public Cliente getClienteById( @PathVariable Integer id ){
        return clienteService.buscarClienteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salva um novo cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente salvo com sucesso!"),
            @ApiResponse(code = 400, message = "Erro de validação!"),
    })
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
