/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.service;

import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.una.tienda.facturacion.dto.ClienteDTO;
import org.una.tienda.facturacion.dto.FacturaDTO;

/**
 *
 * @author Ivan Josué Arias Astua
 */
@SpringBootTest
public class FacturaServiceImplementationTest {
    
    @Autowired
    private IFacturaService facturaService;
    
    @Autowired
    private IClienteService clienteService;

    FacturaDTO facturaEjemplo;
    ClienteDTO cliente;
    
    @BeforeEach
    public void setup(){
        cliente = new ClienteDTO(Long.valueOf("0"), "Desconocida", "sumail@correo.com", true, new Date(), new Date(), "Cliente Prueba", "12345678", null);
        cliente = clienteService.create(cliente);
        facturaEjemplo = new FacturaDTO(Long.valueOf("0"), 1, 0.5, true, new Date(),new Date(), cliente);
    }
    
    @Test
    public void sePuedeCrearUnaFacturaCorrectamente(){
        facturaEjemplo = facturaService.create(facturaEjemplo);
        Optional<FacturaDTO> facturaEncontrada = facturaService.findById(facturaEjemplo.getId());
        if(facturaEncontrada.isPresent()){
            FacturaDTO factura = facturaEncontrada.get();
            Assertions.assertEquals(facturaEjemplo.getId(), factura.getId());
        }else{
            fail("No se encontro la informacion en base de datos");
        }
    }
    
    @Test 
    public void sePuedeModificarUnaFacturaCorrectamente(){
        facturaEjemplo = facturaService.create(facturaEjemplo);
        Optional<FacturaDTO> facturaEncontrada = facturaService.findById(facturaEjemplo.getId());
        if(facturaEncontrada.isPresent()){
            FacturaDTO factura = facturaEncontrada.get();
            factura.setEstado(Boolean.FALSE);
            Optional<FacturaDTO> facturaModified = facturaService.update(factura, factura.getId());
            if(facturaModified.isPresent()){
                if(facturaModified.get().equals(facturaEjemplo)){
                    fail("La modificacion fallo");
                }
            }else{
                fail("Fallo en el intento de modificar");
            }
        }else{
            fail("No se encontro la informacion en base de datos");
        }
    }
    
    @Test
    public void sePuedeEliminarUnaFacturaCorrectaMente(){
        facturaEjemplo = facturaService.create(facturaEjemplo);
        Optional<FacturaDTO> facturaEncontrada = facturaService.findById(facturaEjemplo.getId());
        if(facturaEncontrada.isPresent()){
            FacturaDTO factura = facturaEncontrada.get();
            facturaService.delete(factura.getId());
            if(facturaService.findById(factura.getId()) != null){
                fail("La factura no pudo ser eliminada");
            }else{
                facturaEjemplo = null;
            }
        }else{
            fail("No se encontro la informacion en base de datos");
        }
    }
    
    @AfterEach
    public void tearDown(){
        if(facturaEjemplo != null){
            facturaService.delete(facturaEjemplo.getId());
            facturaEjemplo = null;
        }
    }
}
