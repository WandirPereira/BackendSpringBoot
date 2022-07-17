package com.github.wandirpereira;

import com.github.wandirpereira.domain.entity.Cliente;
import com.github.wandirpereira.domain.entity.Pedido;
import com.github.wandirpereira.domain.repository.Clientes;
import com.github.wandirpereira.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
// @RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos
    ) {
        return args -> {
            System.out.println("Salvando clientes");
            clientes.save(new Cliente("Douglas"));
            clientes.save(new Cliente("Wandir"));
            Cliente fulano = new Cliente("Fulano");
            clientes.save(fulano);

            Pedido p = new Pedido();
            p.setCliente(fulano);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);

            System.out.println("Pedidos cliente");
            pedidos.findByCliente(fulano).forEach(System.out::println);
//            Cliente cliente = clientes.findClienteFetchPedidos(fulano.getId());
//            System.out.println(cliente);
//            System.out.println(cliente.getPedidos());

//            System.out.println("Existe cliente");
//            boolean existe = clientes.existsByNome("Wandir");
//            System.out.println("Existe um cliente com o nome Wandir? " + existe);
//
//            System.out.println("Query clientes");
//            List<Cliente> result = clientes.encontrarPorNome("Wandir");
//            result.forEach(System.out::println);
//
//            System.out.println("Listando todos clientes");
//            List<Cliente> todosClientes = clientes.findAll();
//            todosClientes.forEach(System.out::println);
//
//            System.out.println("Atualizando clientes");
//            todosClientes.forEach(c -> {
//                c.setNome(c.getNome() + " atualizado.");
//                clientes.save(c);
//            });
//            todosClientes = clientes.findAll();
//            todosClientes.forEach(System.out::println);
//
//            System.out.println("Buscando clientes");
//            clientes.findByNomeLike("Wan").forEach(System.out::println);

            // todosClientes = clientes.obterTodos();
            // todosClientes.forEach(System.out::println);

//            System.out.println("Deletando clientes");
//            clientes.findAll().forEach(c -> {
//                clientes.delete(c);
//            });
//
//            todosClientes = clientes.findAll();
//            if (todosClientes.isEmpty()) {
//                System.out.println("Nenhum cliente encontrado!");
//            } else {
//                todosClientes.forEach(System.out::println);
//            }

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
