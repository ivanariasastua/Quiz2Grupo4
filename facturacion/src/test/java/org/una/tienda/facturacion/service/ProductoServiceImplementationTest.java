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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.una.tienda.facturacion.dto.ProductoDTO;

@SpringBootTest
public class ProductoServiceImplementationTest {
    
    @Autowired
    IProductoService productoService;
    
    ProductoDTO productoEjemplo;
    
    @BeforeEach
    public void setup() {
        productoEjemplo = new ProductoDTO(Long.valueOf("0"), "Sin descripción", true, new Date(), new Date(), 0.16, null, null);
    }
    
    @Test
    public void sePuedeCrearUnProductoCorrectamente(){
        productoEjemplo = productoService.create(productoEjemplo);
        Optional<ProductoDTO> productoEncontrado = productoService.findById(productoEjemplo.getId());
        if (productoEncontrado.isPresent()) {
            ProductoDTO producto = productoEncontrado.get();
            assertEquals(productoEjemplo.getId(), producto.getId());
        } else {
            fail("No se encontro la información en la BD");
        }
    }
    
    @Test
    public void sePuedeModificarUnProductoCorrectamente(){
        Assertions.assertDoesNotThrow(() -> {
            productoEjemplo = productoService.create(productoEjemplo);
            Optional<ProductoDTO> productoEncontrado = productoService.findById(productoEjemplo.getId());
            if (productoEncontrado.isPresent()) {
                ProductoDTO producto = productoEncontrado.get();
                producto.setEstado(Boolean.FALSE);
                Optional<ProductoDTO> productoModified = productoService.update(producto, producto.getId());
                if (productoModified.isPresent()) {
                    if (productoModified.get().equals(productoEjemplo)) {
                        fail("La modificacion fallo");
                    }
                } else {
                    fail("Fallo en el intento de modificar");
                }
            } else {
                fail("No se encontro la informacion en base de datos");
            }
        });
    }
    
    @Test
    public void sePuedeEliminarUnProductoCorrectaMente(){
        productoEjemplo = productoService.create(productoEjemplo);
        Optional<ProductoDTO> productoEncontrado = productoService.findById(productoEjemplo.getId());
        if(productoEncontrado.isPresent()){
            ProductoDTO producto = productoEncontrado.get();
            productoService.delete(producto.getId());
            if(productoService.findById(producto.getId()) != null){
                fail("El producto no pudo ser eliminada");
            }else{
                productoEjemplo = null;
            }
        }else{
            fail("No se encontro la informacion en base de datos");
        }
    }
    
    @AfterEach
    public void tearDown(){
        if(productoEjemplo != null){
            productoService.delete(productoEjemplo.getId());
            productoEjemplo = null;
        }
    }
}
