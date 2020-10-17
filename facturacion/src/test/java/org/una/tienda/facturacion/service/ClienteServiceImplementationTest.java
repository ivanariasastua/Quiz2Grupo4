/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.service;

import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.una.tienda.facturacion.dto.ClienteDTO;

/**
 *
 * @author cordo
 */
@SpringBootTest
public class ClienteServiceImplementationTest {

    @Autowired
    private IClienteService clienteService;

    ClienteDTO clienteEjemplo;

    @BeforeEach
    public void setup() {
        clienteEjemplo = new ClienteDTO(Long.valueOf("0"), "", "", true, new Date(), new Date(), "", "", null);
    }

    @Test
    public void sePuedeCrearUnClienteCorrectamente() {
        clienteEjemplo = clienteService.create(clienteEjemplo);
        Optional<ClienteDTO> clienteEncontrado = clienteService.findById(clienteEjemplo.getId());
        if (clienteEncontrado.isPresent()) {
            ClienteDTO cliente = clienteEncontrado.get();
            assertEquals(clienteEjemplo.getId(), cliente.getId());
        } else {
            fail("No se encontro la información en la BD");
        }
    }

    @Test
    public void sePuedeModificarUnaFacturaCorrectamente() {
        clienteEjemplo = clienteService.create(clienteEjemplo);
        Optional<ClienteDTO> clienteEncontrado = clienteService.findById(clienteEjemplo.getId());
        if (clienteEncontrado.isPresent()) {
            ClienteDTO cliente = clienteEncontrado.get();
            cliente.setEstado(Boolean.FALSE);
            Optional<ClienteDTO> clienteModified = clienteService.update(cliente, cliente.getId());
            if (clienteModified.isPresent()) {
                if (clienteModified.get().equals(clienteEjemplo)) {
                    fail("La modificacion fallo");
                }
            } else {
                fail("Fallo en el intento de modificar");
            }
        } else {
            fail("No se encontro la informacion en base de datos");
        }
    }

    @Test
    public void sePuedeEliminarUnaFacturaCorrectaMente() {
        clienteEjemplo = clienteService.create(clienteEjemplo);
        Optional<ClienteDTO> clienteEncontrado = clienteService.findById(clienteEjemplo.getId());
        if (clienteEncontrado.isPresent()) {
            ClienteDTO cliente = clienteEncontrado.get();
            clienteService.delete(cliente.getId());
            if (clienteService.findById(cliente.getId()) != null) {
                fail("El cliente no pudo ser eliminada");
            } else {
                clienteEjemplo = null;
            }
        } else {
            fail("No se encontro la informacion en base de datos");
        }
    }

    @AfterEach
    public void tearDown() {
        if (clienteEjemplo != null) {
            clienteService.delete(clienteEjemplo.getId());
            clienteEjemplo = null;
        }

    }

}
