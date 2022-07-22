package com.github.wandirpereira.rest.controller;


import com.github.wandirpereira.domain.entity.Pedido;
import com.github.wandirpereira.rest.dto.PedidoDTO;
import com.github.wandirpereira.service.impl.PedidoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus
    public  Integer save (@RequestBody PedidoDTO dto){
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();
    }

}
