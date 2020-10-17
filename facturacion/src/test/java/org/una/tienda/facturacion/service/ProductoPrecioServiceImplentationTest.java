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
import org.una.tienda.facturacion.dto.ProductoPrecioDTO;

/**
 *
 * @author cordo
 */
@SpringBootTest
public class ProductoPrecioServiceImplentationTest {

    @Autowired
    private IProductoPrecioService productoService;

    ProductoPrecioDTO productoEjemplo;

    @BeforeEach
    public void setup() {
        productoEjemplo = new ProductoPrecioDTO(Long.valueOf("0"), 10, 5, true, new Date(), new Date(), 15000, null);
    }

    @Test
    public void sePuedeCrearUnProductoPrecioCorrectamente() {
        productoEjemplo = productoService.create(productoEjemplo);
        Optional<ProductoPrecioDTO> productoEncontrado = productoService.findById(productoEjemplo.getId());
        if (productoEncontrado.isPresent()) {
            ProductoPrecioDTO producto = productoEncontrado.get();
            assertEquals(productoEjemplo.getId(), producto.getId());
        } else {
            fail("No se encontro la información en la BD");
        }
    }

    @Test
    public void sePuedeModificarUnProductoPrecioCorrectamente() {
        Assertions.assertDoesNotThrow(() -> {
            productoEjemplo = productoService.create(productoEjemplo);
            Optional<ProductoPrecioDTO> productoEncontrado = productoService.findById(productoEjemplo.getId());
            if (productoEncontrado.isPresent()) {
                ProductoPrecioDTO producto = productoEncontrado.get();
                producto.setEstado(Boolean.FALSE);
                Optional<ProductoPrecioDTO> productoModified = productoService.update(producto, producto.getId());
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
    public void sePuedeEliminarUProductoPrecioCorrectamente() {
        productoEjemplo = productoService.create(productoEjemplo);
        Optional<ProductoPrecioDTO> productoEncontrado = productoService.findById(productoEjemplo.getId());
        if (productoEncontrado.isPresent()) {
            ProductoPrecioDTO producto = productoEncontrado.get();
            productoService.delete(producto.getId());
            if (productoService.findById(producto.getId()) != null) {
                fail("El precio del producto no pudo ser eliminado");
            } else {
                productoEjemplo = null;
            }
        } else {
            fail("No se encontro la informacion en base de datos");
        }
    }

    @AfterEach
    public void tearDown() {
        if (productoEjemplo != null) {
            productoService.delete(productoEjemplo.getId());
            productoEjemplo = null;
        }

    }
}
