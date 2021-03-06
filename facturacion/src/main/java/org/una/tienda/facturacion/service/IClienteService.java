/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.service;

import java.util.Optional;
import org.una.tienda.facturacion.dto.ClienteDTO;
import org.una.tienda.facturacion.exceptions.ClienteException;

/**
 *
 * @author cordo
 */
public interface IClienteService {
    
    public Optional<ClienteDTO> findById(Long id);
    
    public ClienteDTO create(ClienteDTO cliente) throws ClienteException;

    public Optional<ClienteDTO> update(ClienteDTO cliente, Long id) throws ClienteException;
    
    public void delete(Long id);
    
}
