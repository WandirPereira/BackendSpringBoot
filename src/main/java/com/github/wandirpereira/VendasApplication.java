package com.github.wandirpereira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @RestController
public class VendasApplication {

//    @Bean
//    public CommandLineRunner commandLineRunner(@Autowired Clientes clientes){
//        return args -> {
//            Cliente c = new Cliente(null,  "Fulano");
//            clientes.save(c);
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
