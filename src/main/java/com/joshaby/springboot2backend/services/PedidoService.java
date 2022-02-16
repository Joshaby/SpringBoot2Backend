package com.joshaby.springboot2backend.services;

import com.joshaby.springboot2backend.entities.ItemPedido;
import com.joshaby.springboot2backend.entities.PagamentoComBoleto;
import com.joshaby.springboot2backend.entities.Pedido;
import com.joshaby.springboot2backend.entities.enums.EstadoPagamentoEnum;
import com.joshaby.springboot2backend.repositories.ItemPedidoRepository;
import com.joshaby.springboot2backend.repositories.PagamentoRepository;
import com.joshaby.springboot2backend.repositories.PedidoRepository;
import com.joshaby.springboot2backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Pedido find(Integer id) {
        Optional<Pedido> pedido = repository.findById(id);
        return pedido.orElseThrow(
                () -> new ObjectNotFoundException(
                        String.format("Objeto %d não encontrado! Tipo %s", id, Pedido.class.getName())));
    }

    @Transactional
    public Pedido insert(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
        pedido.getPagamento().setEstadoPagamento(EstadoPagamentoEnum.PEDENTE);
        pedido.getPagamento().setPedido(pedido);
        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagamento = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.generateDueDate(pagamento, pedido.getInstante());
        }
        pedido = repository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());
        for (ItemPedido item : pedido.getItens()) {
            item.setDesconto(0.0);
            item.setProduto(produtoService.find(item.getProduto().getId()));
            item.setPreco(item.getProduto().getPreco());
            item.setPedido(pedido);
        }
        itemPedidoRepository.saveAll(pedido.getItens());
        return pedido;
    }
}
