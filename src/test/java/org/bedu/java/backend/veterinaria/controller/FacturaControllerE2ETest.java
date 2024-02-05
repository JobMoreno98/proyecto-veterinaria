package org.bedu.java.backend.veterinaria.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.bedu.java.backend.veterinaria.dto.factura.FacturaDTO;
import org.bedu.java.backend.veterinaria.model.Factura;
import org.bedu.java.backend.veterinaria.model.Propietario;
import org.bedu.java.backend.veterinaria.repository.FacturaRepository;
import org.bedu.java.backend.veterinaria.repository.PropietarioRepository;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class FacturaControllerE2ETest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FacturaRepository repository;

    @Autowired
    private PropietarioRepository propietarioRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @AfterEach
    public void setup() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("GET /facturas regresa una lista vacia ")
    void emptyListTest() throws Exception {
        // Realiza una peticion de tipo GET /facturas
        MvcResult result = mockMvc.perform(get("/facturas")).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        assertEquals("[]", content);
    }

    @Test
    @DisplayName("GET /facturas retorna una lista de facturas")
    void findAll() throws Exception {
        Factura factura = new Factura();
        Date fecha = Date.valueOf("2023-12-12");
        factura.setId(1L);
        factura.setFechaEmision(fecha);
        factura.setIva(1);
        factura.setRazonSocial("qwerty");
        factura.setRfcCliente("qwerty");
        factura.setSubtotal(150);
        factura.setTotal(1500);
        factura.setPropietario(null);

        repository.save(factura);

        MvcResult result = mockMvc.perform(get("/facturas"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        // Creamos una referencia del tipo al que se va a convertir el JSON
        TypeReference<List<FacturaDTO>> listTypeReference = new TypeReference<List<FacturaDTO>>() {
        };

        // Convertimos el JSON a un objeto de Java
        List<FacturaDTO> response = mapper.readValue(content, listTypeReference);

        // Hacemos las verificaciones basadas en los objetos
        assertEquals(1, response.size());
        assertEquals(factura.getId(), response.get(0).getId());
    }

    @Test
    @DisplayName("POST /facturas registro de una factura con algun parametro faltante ")
    void postMissingParameterRequestBodyTest() throws Exception {
        MvcResult result = mockMvc
                .perform(post("/facturas").contentType("application/json").content("{\"rfcCliente\":\"qwerty\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        String resultContent = "{\"code\":\"ERR_VALID\",\"message\":\"Hubo un error al procesar los datos de entrada";

        assertThat(content, containsString(resultContent));
    }

    @Test
    @DisplayName("POST  /facturas registro de una factura de manera exitosa")
    void postTest() throws Exception {

        Propietario propietario = new Propietario();
        propietario.setId(1L);
        propietario.setApellidoMaterno("Moreno");
        propietario.setApellidoPaterno("Martinez");
        propietario.setCelular("3333333");
        propietario.setCorreo("a@a.com");
        propietario.setDireccion("Paseo");
        propietario.setFechaNacimiento(Date.valueOf("2023-12-12"));
        propietario.setNombre("Job");
        propietario.setOcupacion("Empleado");

        Propietario dto = propietarioRepository.save(propietario);

        String content = "{\"fechaEmision\":\"2023-12-12\",\"subtotal\": 1500.0,\"iva\": 0.16,\"rfcCliente\": \"RFC del Clien\",\"razonSocial\": \"Razón Social del Cliente2\",\"propietario\": { \"id\":1}}";

        MvcResult result = mockMvc.perform(post("/facturas").contentType("application/json")
                .content(content))
                .andExpect(status().isCreated())
                .andReturn();
    }

}
