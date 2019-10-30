package com.circulosiete.curso.microservices.clientes.service;

import com.circulosiete.curso.microservices.clientes.modelo.ClienteBean;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClientesServiceTests {

  private static final BigDecimal LIMITE = BigDecimal.valueOf(100);

  @Mock
  MeterRegistry meterRegistry;

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Test
  public void testLimitCredit() {

    ClientesService service = new ClientesService(null, null, null, meterRegistry, LIMITE, false);

    assertTrue(service.isCreditLimitValid(LIMITE.subtract(ONE)));
    assertTrue(service.isCreditLimitValid(LIMITE));
    assertFalse(service.isCreditLimitValid(LIMITE.add(ONE)));
  }

  @Test(expected = CreditLimitException.class)
  public void testLimitCreditCreateClient() {
    ClientesService service = new ClientesService(null, null, null, meterRegistry, LIMITE, false);
    service.createCliente(ClienteBean.builder().creditThreshold(LIMITE.add(ONE)).build());
  }

}
