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
import org.una.tienda.facturacion.dto.FacturaDTO;
import org.una.tienda.facturacion.entities.Factura;
import org.una.tienda.facturacion.exceptions.FacturaException;
import org.una.tienda.facturacion.repository.IFacturaRepository;
import org.una.tienda.facturacion.utils.MapperUtils;
import org.una.tienda.facturacion.utils.ServiceConvertionHelper;

/**
 *
 * @author Ivan Josué Arias Astua
 */
@Service
public class FacturaServiceImplementation implements IFacturaService{

    @Autowired
    private IFacturaRepository facturaRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<FacturaDTO> findById(Long id) {
        return ServiceConvertionHelper.OptionalOneToOptionalDto(facturaRepository.findById(id), FacturaDTO.class);
    }

    @Override
    @Transactional
    public FacturaDTO create(FacturaDTO factura) {
        Factura fac = MapperUtils.EntityFromDto(factura, Factura.class);
        fac = facturaRepository.save(fac);
        return ServiceConvertionHelper.OneToDto(fac, FacturaDTO.class);
    }

    @Override
    @Transactional
    public Optional<FacturaDTO> update(FacturaDTO factura, Long id) throws FacturaException{
        Optional<Factura> facturaModified = facturaRepository.findById(id);
        if(facturaModified.isPresent()){
            if(!facturaModified.get().getEstado())
                throw new FacturaException("No se pueden modificar los datos inactivos");
            Factura fac = MapperUtils.EntityFromDto(factura, Factura.class);
            fac = facturaRepository.save(fac);
            return ServiceConvertionHelper.oneToOptionalDto(fac, FacturaDTO.class);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        facturaRepository.deleteById(id);
    }
    
}
