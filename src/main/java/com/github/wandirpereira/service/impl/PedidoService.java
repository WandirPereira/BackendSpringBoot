package com.github.wandirpereira.service.impl;

import com.github.wandirpereira.domain.entity.Cliente;
import com.github.wandirpereira.domain.entity.ItemPedido;
import com.github.wandirpereira.domain.entity.Pedido;
import com.github.wandirpereira.domain.entity.Produto;
import com.github.wandirpereira.domain.enums.StatusPedido;
import com.github.wandirpereira.domain.repository.ClientesRepository;
import com.github.wandirpereira.domain.repository.ItensPedidoRepository;
import com.github.wandirpereira.domain.repository.PedidosRepository;
import com.github.wandirpereira.domain.repository.ProdutosRepository;
import com.github.wandirpereira.exception.RegraNegocioException;
import com.github.wandirpereira.rest.dto.ItemPedidoDTO;
import com.github.wandirpereira.rest.dto.PedidoDTO;
import com.github.wandirpereira.service.IPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService implements IPedidoService {

    private final PedidosRepository pedidosRepository;
    private final ClientesRepository clientesRepository;
    private final ProdutosRepository produtosRepository;

    private final ItensPedidoRepository itensPedidoRepository;

//    public PedidoService(PedidosRepository pedidosRepository, ClientesRepository clientesRepository, ProdutosRepository produtosRepository) {
//        this.pedidosRepository = pedidosRepository;
//        this.clientesRepository = clientesRepository;
//        this.produtosRepository = produtosRepository;
//    }

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto){
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente).orElseThrow(() -> new RegraNegocioException("Código de cliente inválido!"));
        Pedido pedido = new Pedido();
        pedido.setTotal((dto.getTotal()));
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
        pedidosRepository.save(pedido);
        itensPedidoRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);
        return pedido;
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens){
        if(itens.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens!");
        }
        return itens
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException("Código de produto inválido: " + idProduto)
                            );
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return Optional.empty();
    }

    @Override
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {

    }
}
