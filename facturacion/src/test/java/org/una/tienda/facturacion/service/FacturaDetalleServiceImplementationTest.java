/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.service;

import java.util.ArrayList;
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
import org.una.tienda.facturacion.dto.FacturaDetalleDTO;
import org.una.tienda.facturacion.dto.ProductoDTO;
import org.una.tienda.facturacion.dto.ProductoExistenciaDTO;
import org.una.tienda.facturacion.dto.ProductoPrecioDTO;
import org.una.tienda.facturacion.exceptions.ClienteException;

/**
 *
 * @author Ivan Josué Arias Astua
 */
@SpringBootTest
public class FacturaDetalleServiceImplementationTest {
    
    @Autowired
    private IFacturaDetalleService facturaDetalleService;
    
    @Autowired
    private IClienteService clienteService;
    
    @Autowired
    private IFacturaService facturaService;
    
    @Autowired
    private IProductoService productoService;
    
    @Autowired
    private IProductoPrecioService productoPrecioService;
    
    @Autowired
    private IProductoExistenciaService productoExistenciaService;
    
    FacturaDetalleDTO facturaDetalleEjemplo;
    FacturaDetalleDTO facturaDetallePruebaConExtraDescuento;
    FacturaDTO factura;
    ProductoDTO producto;
    ProductoPrecioDTO productoPrecio;
    ProductoExistenciaDTO productExistencia;
    ClienteDTO cliente;
    
    @BeforeEach
    public void setup() throws ClienteException{
        cliente = new ClienteDTO(0L, "Desconocida", "sumail@correo.com", true, new Date(), new Date(), "Cliente Prueba", "12345678", null);
        cliente = clienteService.create(cliente);
        factura = new FacturaDTO(0L, 1, 0.05, Boolean.TRUE, new Date(), new Date(), cliente, null);
        factura = facturaService.create(factura);
        producto = new ProductoDTO(0L, "Este es un producto cualquiera", Boolean.TRUE, new Date(), new Date(), 0.10, null, null);
        producto = productoService.create(producto);
        productoPrecio = new ProductoPrecioDTO(0L, 0.07, 0.1, true, new Date(), new Date(), 14300, producto);
        productoPrecio = productoPrecioService.create(productoPrecio);
        productExistencia = new ProductoExistenciaDTO(0L, 50.0, Boolean.TRUE, new Date(), new Date(), producto);
        productExistencia = productoExistenciaService.create(productExistencia);
        producto.setExistencias(new ArrayList<>());
        producto.getExistencias().add(productExistencia);
        producto.setPrecios(new ArrayList<>());
        producto.getPrecios().add(productoPrecio);
        facturaDetalleEjemplo = new FacturaDetalleDTO(Long.valueOf("0"), 15000.0, 0.07, Boolean.TRUE, new Date(), new Date(), factura, producto);
    }
    
    @Test
    public void sePuedeCrearUnaFacturaDetalleCorrectamente() {
        Assertions.assertDoesNotThrow(() -> {
            facturaDetalleEjemplo = facturaDetalleService.create(facturaDetalleEjemplo);
            Optional<FacturaDetalleDTO> facturaDetalleEncontrada = facturaDetalleService.findById(facturaDetalleEjemplo.getId());
            if(facturaDetalleEncontrada.isPresent()){
                FacturaDetalleDTO facturaDetalle = facturaDetalleEncontrada.get();
                Assertions.assertEquals(facturaDetalleEjemplo.getId(), facturaDetalle.getId());
            }else{
                fail("No se encontro la informacion en base de datos");
            }
        });
    }
    
    @Test 
    public void sePuedeModificarUnaFacturaDetalleCorrectamente()  {
        Assertions.assertDoesNotThrow(() -> {
            facturaDetalleEjemplo = facturaDetalleService.create(facturaDetalleEjemplo);
            Optional<FacturaDetalleDTO> facturaDetalleEncontrada = facturaDetalleService.findById(facturaDetalleEjemplo.getId());
            if(facturaDetalleEncontrada.isPresent()){
                FacturaDetalleDTO facturaDetalle = facturaDetalleEncontrada.get();
                facturaDetalle.setEstado(Boolean.FALSE);
                Optional<FacturaDetalleDTO> facturaDetallerModified = facturaDetalleService.update(facturaDetalle, facturaDetalle.getId());
                if(facturaDetallerModified.isPresent()){
                    if(facturaDetallerModified.get().equals(facturaDetalleEjemplo)){
                        fail("La modificacion fallo");
                    }
                }else{
                    fail("Fallo en el intento de modificar");
                }
            }else{
                fail("No se encontro la informacion en base de datos");
            }
        });
    }
    
    @Test
    public void sePuedeEliminarUnaFacturaDetalleCorrectaMente()  {
        Assertions.assertDoesNotThrow(() -> {
            facturaDetalleEjemplo = facturaDetalleService.create(facturaDetalleEjemplo);
            Optional<FacturaDetalleDTO> facturaDetalleEncontrada = facturaDetalleService.findById(facturaDetalleEjemplo.getId());
            if(facturaDetalleEncontrada.isPresent()){
                FacturaDetalleDTO facturaDetalle = facturaDetalleEncontrada.get();
                facturaDetalleService.delete(facturaDetalle.getId());
                if(facturaDetalleService.findById(facturaDetalle.getId()) != null){
                    fail("La facturaDetalle no pudo ser eliminada");
                }else{
                    facturaDetalleEjemplo = null;
                }
            }else{
                fail("No se encontro la informacion en base de datos");
            }
        });
    }
   
    @AfterEach
    public void tearDown(){
        if(facturaDetalleEjemplo != null){
            facturaDetalleService.delete(facturaDetalleEjemplo.getId());
            facturaDetalleEjemplo = null;
        }
        cliente = null;
        producto = null;
        productoPrecio = null;
        productExistencia = null;
    }
}