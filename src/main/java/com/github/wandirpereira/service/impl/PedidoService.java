package com.github.wandirpereira.service.impl;

import com.github.wandirpereira.domain.entity.Cliente;
import com.github.wandirpereira.domain.entity.ItemPedido;
import com.github.wandirpereira.domain.entity.Pedido;
import com.github.wandirpereira.domain.entity.Produto;
import com.github.wandirpereira.domain.enums.StatusPedido;
import com.github.wandirpereira.domain.repository.ItemPedidoRepository;
import com.github.wandirpereira.exception.PedidoNaoEncontradoException;
import com.github.wandirpereira.exception.RegraNegocioException;
import com.github.wandirpereira.rest.dto.ItemPedidoDTO;
import com.github.wandirpereira.rest.dto.PedidoDTO;
import com.github.wandirpereira.service.IPedidoService;
import com.github.wandirpereira.domain.repository.ClientesRepository;
import com.github.wandirpereira.domain.repository.PedidosRepository;
import com.github.wandirpereira.domain.repository.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
    private final ItemPedidoRepository repositoryItemPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto ) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItens());
        pedidosRepository.save(pedido);
        repositoryItemPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return Optional.ofNullable(pedidosRepository
                .findByIdFetchItens(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException()));
    }

    @Override
    @Transactional
    public void atualizaStatus( Integer id, StatusPedido statusPedido ) {
        pedidosRepository
                .findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidosRepository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException() );
    }


    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
       }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }


    @Override
    public List<Pedido> pesquisar(Pedido filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        return pedidosRepository.findAll(example);
    }
}
