/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.service;

import java.util.Optional;
import org.una.tienda.facturacion.dto.FacturaDetalleDTO;
import org.una.tienda.facturacion.exceptions.FacturaDetalleException;

/**
 *
 * @author Ivan Josué Arias Astua
 */
public interface IFacturaDetalleService {
    
    public Optional<FacturaDetalleDTO> findById(Long id);
    
    public FacturaDetalleDTO create(FacturaDetalleDTO factura) throws FacturaDetalleException;

    public Optional<FacturaDetalleDTO> update(FacturaDetalleDTO factura, Long id) throws FacturaDetalleException;

    public void delete(Long id);
    
}
