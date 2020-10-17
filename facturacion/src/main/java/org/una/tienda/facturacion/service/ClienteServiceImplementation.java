/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tienda.facturacion.dto.ClienteDTO;
import org.una.tienda.facturacion.entities.Cliente;
import org.una.tienda.facturacion.exceptions.ClienteException;
import org.una.tienda.facturacion.repository.IClienteRepository;
import org.una.tienda.facturacion.utils.MapperUtils;
import org.una.tienda.facturacion.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class ClienteServiceImplementation implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<ClienteDTO> findById(Long id) {
        return ServiceConvertionHelper.OptionalOneToOptionalDto(clienteRepository.findById(id), ClienteDTO.class);
    }

    @Override
    @Transactional
    public ClienteDTO create(ClienteDTO cliente) throws ClienteException{
        if(cliente.getEmail() == null || cliente.getTelefono() == null || cliente.getDireccion() == null)
            throw new ClienteException("Los clientes deben tener direccion, correo y telefono para poder registrarlos");
        Cliente client = MapperUtils.EntityFromDto(cliente, Cliente.class);
        client = clienteRepository.save(client);
        return ServiceConvertionHelper.OneToDto(client, ClienteDTO.class);
    }

    @Override
    @Transactional
    public Optional<ClienteDTO> update(ClienteDTO cliente, Long id) throws ClienteException{
        Optional<Cliente> clienteModified = clienteRepository.findById(id);
        if (clienteModified.isPresent()) {
            if(!clienteModified.get().getEstado())
                throw new ClienteException("No se pueden editar datos inactivos");
            Cliente client = MapperUtils.EntityFromDto(cliente, Cliente.class);
            client = clienteRepository.save(client);
            return ServiceConvertionHelper.oneToOptionalDto(client, ClienteDTO.class);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

}
