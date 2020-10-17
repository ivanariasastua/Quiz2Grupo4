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
import org.una.tienda.facturacion.dto.FacturaDetalleDTO;
import org.una.tienda.facturacion.entities.FacturaDetalle;
import org.una.tienda.facturacion.repository.IFacturaDetalleRepository;
import org.una.tienda.facturacion.utils.MapperUtils;
import org.una.tienda.facturacion.utils.ServiceConvertionHelper;

/**
 *
 * @author Ivan Josué Arias Astua
 */
@Service
public class FacturaDetalleServiceImplementation implements IFacturaDetalleService{

    @Autowired
    private IFacturaDetalleRepository facturaDetalleRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<FacturaDetalleDTO> findById(Long id) {
        return ServiceConvertionHelper.OptionalOneToOptionalDto(facturaDetalleRepository.findById(id), FacturaDetalleDTO.class);
    }

    @Override
    @Transactional
    public FacturaDetalleDTO create(FacturaDetalleDTO factura) {
        FacturaDetalle facturaDetalle = MapperUtils.EntityFromDto(factura, FacturaDetalle.class);
        facturaDetalle = facturaDetalleRepository.save(facturaDetalle);
        return ServiceConvertionHelper.OneToDto(facturaDetalle, FacturaDetalleDTO.class);
    }

    @Override
    @Transactional
    public Optional<FacturaDetalleDTO> update(FacturaDetalleDTO factura, Long id) {
        if(facturaDetalleRepository.findById(id).isPresent()){
            FacturaDetalle facturaDetalle = MapperUtils.EntityFromDto(factura, FacturaDetalle.class);
            facturaDetalle = facturaDetalleRepository.save(facturaDetalle);
            return ServiceConvertionHelper.oneToOptionalDto(facturaDetalle, FacturaDetalleDTO.class);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        facturaDetalleRepository.deleteById(id);
    }
    
}
