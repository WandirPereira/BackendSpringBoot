package com.github.wandirpereira.rest.controller;

import com.github.wandirpereira.domain.entity.Cliente;
import com.github.wandirpereira.domain.repository.Clientes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
//@RequestMapping("/api/clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClientById(@PathVariable Integer id){
        Optional<Cliente> cliente = clientes.findById(id);
        if(cliente.isPresent()){
//            HttpHeaders headers = new HttpHeaders();
//            headers.put("Authorization","token");
//            ResponseEntity<Cliente> responseEntity = new ResponseEntity<>(
//                    cliente.get(),
//                    headers,
//                    HttpStatus.OK
//            );
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity save( @RequestBody Cliente cliente ){
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity delete ( @PathVariable Integer id ){
        Optional<Cliente> cliente = clientes.findById(id);
        if(cliente.isPresent()) {
            clientes.delete( cliente.get() );
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity update ( @PathVariable Integer id, @RequestBody Cliente cliente ){
        return clientes
        .findById(id)
        .map( clienteExistente -> {
            cliente.setId(clienteExistente.getId());
            clientes.save(cliente);
            return ResponseEntity.noContent().build();
        }).orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PutMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity find( Cliente filtro ){

    }

}

