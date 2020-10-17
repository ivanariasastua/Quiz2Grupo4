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
import org.una.tienda.facturacion.dto.FacturaDTO;
import org.una.tienda.facturacion.dto.ProductoDTO;
import org.una.tienda.facturacion.dto.ProductoExistenciaDTO;

@SpringBootTest
public class ProductoExistenciaServiceImplementationTest {

    @Autowired
    private IProductoExistenciaService productoExistenciaService;
    
    @Autowired
    private IProductoService productoService;

    ProductoExistenciaDTO productoExistenciaEjemplo;
    ProductoDTO producto;
    
    @BeforeEach
    public void setup(){
        producto = new ProductoDTO(Long.valueOf("0"), "Sin descripción", true, new Date(), new Date(), 0.16);
        producto = productoService.create(producto);
        productoExistenciaEjemplo = new ProductoExistenciaDTO(Long.valueOf("0"),0.0001, true, new Date(), new Date(), producto);
    }
    
    @Test
    public void sePuedeCrearUnaExistenciaCorrectamente(){
        productoExistenciaEjemplo = productoExistenciaService.create(productoExistenciaEjemplo);
        Optional<ProductoExistenciaDTO> productoExistenciaEncontrada = productoExistenciaService.findById(productoExistenciaEjemplo.getId());
        if(productoExistenciaEncontrada.isPresent()){
            ProductoExistenciaDTO productoExistencia = productoExistenciaEncontrada.get();
            Assertions.assertEquals(productoExistenciaEjemplo.getId(), productoExistencia.getId());
        }else{
            fail("No se encontro la informacion en base de datos");
        }
    }
    
    @Test 
    public void sePuedeModificarUnaExistenciaCorrectamente(){
        productoExistenciaEjemplo = productoExistenciaService.create(productoExistenciaEjemplo);
        Optional<ProductoExistenciaDTO> productoExistenciaEncontrada = productoExistenciaService.findById(productoExistenciaEjemplo.getId());
        if(productoExistenciaEncontrada.isPresent()){
            ProductoExistenciaDTO productoExistencia = productoExistenciaEncontrada.get();
            productoExistencia.setEstado(Boolean.FALSE);
            Optional<ProductoExistenciaDTO> productoExistenciaModified = productoExistenciaService.update(productoExistencia, productoExistencia.getId());
            if(productoExistenciaModified.isPresent()){
                if(productoExistenciaModified.get().equals(productoExistenciaEjemplo)){
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
    public void sePuedeEliminarUnaExistenciaCorrectaMente(){
        productoExistenciaEjemplo = productoExistenciaService.create(productoExistenciaEjemplo);
        Optional<ProductoExistenciaDTO> productoExistenciaEncontrada = productoExistenciaService.findById(productoExistenciaEjemplo.getId());
        if(productoExistenciaEncontrada.isPresent()){
            ProductoExistenciaDTO productoExistencia = productoExistenciaEncontrada.get();
            productoExistenciaService.delete(productoExistencia.getId());
            if(productoExistenciaService.findById(productoExistencia.getId()) != null){
                fail("La existencia del producto no pudo ser eliminada");
            }else{
                productoExistenciaEjemplo = null;
            }
        }else{
            fail("No se encontro la informacion en base de datos");
        }
    }
    
    @AfterEach
    public void tearDown(){
        if(productoExistenciaEjemplo != null){
            productoExistenciaService.delete(productoExistenciaEjemplo.getId());
            productoExistenciaEjemplo = null;
        }
    }
}
