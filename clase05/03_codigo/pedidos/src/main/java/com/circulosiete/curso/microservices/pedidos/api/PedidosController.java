package com.circulosiete.curso.microservices.pedidos.api;

import com.circulosiete.curso.microservices.pedidos.modelo.Pedido;
import com.circulosiete.curso.microservices.pedidos.service.Pagina;
import com.circulosiete.curso.microservices.pedidos.service.PedidoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidosController {
  private final PedidoService pedidoService;

  public PedidosController(PedidoService pedidoService) {
    this.pedidoService = pedidoService;
  }

  @GetMapping
  public Map index() {
    return new HashMap<>();
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public ClientePedido add(@RequestBody PedidoBean bean) {
    Pedido crear = pedidoService.crear(bean);
    crear.setPagado(false);

    return ClientePedido.builder()
      .deuda(pedidoService.totalAdeudo(bean.getId()))
      .pedido(crear.to())
      .build();
  }

  @DeleteMapping
  @ResponseStatus(NO_CONTENT)
  public void delete(String pedidoId) {
    pedidoService.delete(pedidoId);
  }

  @GetMapping("/client/{id}")
  public Pagina<PedidoBean> ordersByClient(@PathVariable("id") String clientId, Pageable page) {

    Page<Pedido> pedidos = pedidoService.ordersByClientId(clientId, page);
    Page<PedidoBean> map = pedidos.map(pedido -> PedidoBean.builder()
      .clienteId(pedido.getClienteId())
      .createdAt(pedido.getCreatedAt())
      .id(pedido.getId())
      .importe(pedido.getImporte())
      .lastModifiedAt(pedido.getLastModifiedAt())
      .pagado(pedido.getPagado())
      .version(pedido.getVersion())
      .build());

    List<PedidoBean> content = map.getContent();

    Pagina p = new Pagina();
    p.setContent(content);
    p.setHasContent(pedidos.hasContent());
    p.setNumber(pedidos.getNumber());
    p.setNumberOfElements(pedidos.getNumberOfElements());


    return p;

  }
}

