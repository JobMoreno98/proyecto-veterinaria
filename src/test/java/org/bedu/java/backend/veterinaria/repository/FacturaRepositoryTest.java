package org.bedu.java.backend.veterinaria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.List;

import org.bedu.java.backend.veterinaria.model.Factura;
import org.bedu.java.backend.veterinaria.model.Propietario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class FacturaRepositoryTest {

   @Autowired
   private FacturaRepository repository;

   @Autowired
   private TestEntityManager manager;

   @Autowired
   private PropietarioRepository propietarioRepository;

   @AfterEach
   public void setup() {
      repository.deleteAll();
   }

   @Test
   @DisplayName("Repository should be injected")
   void smokeTest() {
      assertNotNull(repository);
   }
   /*
    * @Test
    * 
    * @DisplayName("FindAll")
    * void findAllTest() {
    * Date fecha = Date.valueOf("2023-12-12");
    * Factura factura = new Factura();
    * Factura factura2 = new Factura();
    * 
    * Propietario propietario = new Propietario();
    * propietario.setId(1L);
    * propietario.setApellidoMaterno("Martinez");
    * propietario.setApellidoPaterno("Moreno");
    * propietario.setDireccion("Paseo");
    * propietario.setCelular("33333");
    * propietario.setCorreo("a@a.com");
    * propietario.setFechaNacimiento(fecha);
    * propietario.setOcupacion("empleado");
    * 
    * 
    * when(propietarioRepository.save(any(Propietario.class))).thenReturn(
    * propietario);
    * 
    * factura.setId(1L);
    * 
    * factura.setFechaEmision(fecha);
    * factura.setIva(1);
    * factura.setRazonSocial("qwerty");
    * factura.setRfcCliente("1234567891234");
    * factura.setSubtotal(150);
    * factura.setTotal(1500);
    * factura.setPropietario(propietario);
    * 
    * factura2.setId(2L);
    * 
    * factura2.setFechaEmision(fecha);
    * factura2.setIva(1);
    * factura2.setRazonSocial("qwerty");
    * factura2.setRfcCliente("1234567891234");
    * factura2.setSubtotal(150);
    * factura2.setTotal(1500);
    * factura2.setPropietario(propietario);
    * 
    * manager.persist(factura);
    * manager.persist(factura2);
    * 
    * List<Factura> result = repository.findAll();
    * assertEquals(2, result.size());
    * }
    * 
    * 
    * @Test
    * 
    * @DisplayName("Repository should filter by Id")
    * void findByIdtest(){
    * Factura factura = new Factura();
    * Factura factura2 = new Factura();
    * 
    * Date fecha = Date.valueOf("2023-12-12");
    * factura.setId(1L);
    * 
    * factura.setFechaEmision(fecha);
    * factura.setIva(1);
    * factura.setRazonSocial("qwerty");
    * factura.setRfcCliente("ytrewq");
    * factura.setSubtotal(150);
    * factura.setTotal(1500);
    * factura.setPropietario(null);
    * 
    * factura2.setId(2L);
    * 
    * factura2.setFechaEmision(fecha);
    * factura2.setIva(1);
    * factura2.setRazonSocial("qwerty");
    * factura2.setRfcCliente("ytrewq");
    * factura2.setSubtotal(150);
    * factura2.setTotal(1500);
    * factura2.setPropietario(null);
    * 
    * 
    * manager.persist(factura);
    * manager.persist(factura2);
    * 
    * Optional<Factura> result = repository.findById(1L);
    * Factura fResult = result.get();
    * 
    * assertEquals(fResult.getId(),1L);
    * 
    * }
    */
}