/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tienda.facturacion.entities.Cliente;

/**
 *
 * @author cordo
 */
public interface IClienteRepository extends JpaRepository<Cliente, Long>{
    
    public List<Cliente> findByNombre(String nombre);
    public List<Cliente> findByEstado(Boolean estado);
    public List<Cliente> findByDireccion(String direccion);
}
