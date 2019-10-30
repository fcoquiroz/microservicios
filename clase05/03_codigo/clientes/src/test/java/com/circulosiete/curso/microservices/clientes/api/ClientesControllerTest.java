package com.circulosiete.curso.microservices.clientes.api;

import com.circulosiete.curso.microservices.clientes.ClientesServiceApplication;
import com.circulosiete.curso.microservices.clientes.modelo.Cliente;
import com.circulosiete.curso.microservices.clientes.modelo.ClienteBean;
import com.circulosiete.curso.microservices.clientes.service.ClientesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.math.BigDecimal.ONE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ClientesServiceApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "build/snippets")
@Slf4j
public class ClientesControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ClientesService service;
  @Autowired
  private ObjectMapper objectMapper;
  private List<FieldDescriptor> PERSON_RESPONSE_DESCRIPTORS;
  private List<FieldDescriptor> PERSON_REQUEST_DESCRIPTORS;
  private ClienteBean clienteBean;

  @Before
  public void init() {
    PERSON_RESPONSE_DESCRIPTORS = Stream.of(
      fieldWithPath("id")
        .type(STRING)
        .description("El identificador del cliente"),
      fieldWithPath("version")
        .type(NUMBER)
        .description("la version del registro del cliente"),
      fieldWithPath("createdAt")
        .type(NULL)
        .description("fecha de creacion"),
      fieldWithPath("lastModifiedAt")
        .type(NULL)
        .description("Fecha de modificacion"),
      fieldWithPath("firstName")
        .type(STRING)
        .description("Nombre del cliente"),
      fieldWithPath("lastName")
        .type(STRING)
        .description("Nombre del cliente"),
      fieldWithPath("taxId")
        .type(STRING)
        .description("Identificdor fiscal (RFC en México)"),
      fieldWithPath("email")
        .type(STRING)
        .description("Email del cliente"),
      fieldWithPath("creditThreshold")
        .type(NUMBER)
        .description("Limite de crédito"),
      fieldWithPath("pedidosPendientes")
        .type(NUMBER)
        .description("pedisos"),
      fieldWithPath("verified")
        .type(BOOLEAN)
        .description("Si el cliente esta verificado"))
      .collect(Collectors.toList());

    PERSON_REQUEST_DESCRIPTORS = Stream.of(
      fieldWithPath("id")
        .type(NULL)
        .description("El identificador del cliente"),
      fieldWithPath("createdAt")
        .type(NULL)
        .description("fecha de creacion"),
      fieldWithPath("version")
        .type(NULL)
        .description("la version del registro del cliente"),
      fieldWithPath("lastModifiedAt")
        .type(NULL)
        .description("Fecha de modificacion"),
      fieldWithPath("firstName")
        .type(STRING)
        .description("Nombre del cliente"),
      fieldWithPath("lastName")
        .type(STRING)
        .description("Nombre del cliente"),
      fieldWithPath("email")
        .type(STRING)
        .description("Email del cliente"),
      fieldWithPath("taxId")
        .type(STRING)
        .description("Identificdor fiscal (RFC en México)"),
      fieldWithPath("creditThreshold")
        .type(NUMBER)
        .description("Limite de crédito"),
      fieldWithPath("pedidosPendientes")
        .type(NUMBER)
        .description("pedisos"),
      fieldWithPath("verified")
        .type(BOOLEAN)
        .description("Si el cliente esta verificado")
    ).collect(Collectors.toList());

    clienteBean = ClienteBean.builder()
      .verified(true)
      .taxId("XXXX-000000-XXX")
      .lastName("N")
      .firstName("Juan")
      .email("juan@foo.com")
      .creditThreshold(BigDecimal.valueOf(50000))
      .pedidosPendientes(0)
      .build();
  }


  @Test
  public void shouldReturnTheCreditLimit() throws Exception {
    mockMvc.perform(get("/v1/clientes/_creditLimit"))
      .andDo(print())
      .andExpect(
        status().isOk())
      .andExpect(
        jsonPath("limiteCredito").value(service.getLimite()))
      .andDo(document("v1-cliente/limiteCredito"));
  }

  @Test
  public void shouldCreateCliente() throws Exception {

    String json = objectMapper.writeValueAsString(clienteBean);

    ResultActions result = mockMvc.perform(
      post("/v1/clientes")
        .contentType(APPLICATION_JSON)
        .content(json)
    );

    result
      .andExpect(
        status().isCreated())
      .andExpect(
        jsonPath("id").isNotEmpty())
      .andExpect(
        jsonPath("firstName").value(clienteBean.getFirstName()))
      .andExpect(
        jsonPath("email").value(clienteBean.getEmail()))
      .andDo(document("v1-cliente/create",
        preprocessRequest(prettyPrint()),
        preprocessResponse(prettyPrint()),
        requestFields(PERSON_REQUEST_DESCRIPTORS),
        responseFields(PERSON_RESPONSE_DESCRIPTORS)
      ));
  }

  @Test
  public void shouldFailWhenAClientExceedCreditLimit() throws Exception {
    BigDecimal exceededLimit = service.getLimite().add(ONE);
    clienteBean.setCreditThreshold(exceededLimit);
    String json = objectMapper.writeValueAsString(clienteBean);
    clienteBean.setCreditThreshold(BigDecimal.valueOf(50000));

    ResultActions result = mockMvc.perform(
      post("/v1/clientes")
        .contentType(APPLICATION_JSON)
        .content(json)
    );

    result
      .andExpect(
        status().isUnprocessableEntity())
      .andExpect(
        jsonPath("creditThreshold").value(service.getLimite()))
      .andExpect(
        jsonPath("message").isNotEmpty())
      .andExpect(
        jsonPath("requestedCredit").value(exceededLimit))
      .andDo(document("v1-cliente/createWithErrorExceedCreditLimit",
        preprocessRequest(prettyPrint()),
        preprocessResponse(prettyPrint()),
        requestFields(PERSON_REQUEST_DESCRIPTORS)
      ));
  }

  @Test
  public void shouldGetaCliente() throws Exception {
    clienteBean.setEmail("dhgfsdjhg@ff.com");
    log.info("Intentando crear el cliente {}", clienteBean);
    Cliente cliente = service.createCliente(clienteBean);
    log.info("Se creo al cliente {}", cliente);

    mockMvc.perform(get("/v1/clientes/" + cliente.getId()))
      .andDo(print())
      .andExpect(
        status().isOk())
      .andExpect(
        jsonPath("id").value(cliente.getId()))
      .andDo(document("v1-cliente/get"));
  }

  @Test
  public void shouldFailWhenFindingAMissingClient() throws Exception {
    String missingId = "fjhgfjhdsg";
    mockMvc.perform(get("/v1/clientes/" + missingId))
      .andDo(print())
      .andExpect(
        status().isNotFound())
      .andExpect(
        jsonPath("id").value(missingId))
      .andDo(document("v1-cliente/getNotFound"));
  }

  @Test
  public void shouldDeleteClient() throws Exception {
    clienteBean.setEmail("aborrarlo@ff.com");
    Cliente cliente = service.createCliente(clienteBean);
    log.info("Se creo al cliente {}", cliente);

    mockMvc.perform(delete("/v1/clientes/" + cliente.getId()))
      .andDo(print())
      .andExpect(
        status().isNoContent())
      .andDo(document("v1-cliente/deleteClient"));
  }

  @Test
  public void shouldFailWhenDeleteAMissingClient() throws Exception {
    String missingId = "fjhgfjhdsg";

    mockMvc.perform(delete("/v1/clientes/" + missingId))
      .andDo(print())
      .andExpect(
        status().isNotFound())

      .andDo(document("v1-cliente/deleteClient-fail-not-found"));
  }
}
