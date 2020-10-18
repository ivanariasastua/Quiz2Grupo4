/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tienda.facturacion.dto.FacturaDetalleDTO;
import org.una.tienda.facturacion.dto.ProductoExistenciaDTO;
import org.una.tienda.facturacion.dto.ProductoPrecioDTO;
import org.una.tienda.facturacion.entities.FacturaDetalle;
import org.una.tienda.facturacion.exceptions.FacturaDetalleException;
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
    public FacturaDetalleDTO create(FacturaDetalleDTO factura) throws FacturaDetalleException{
        if(factura.getFactura().getCliente() == null || !factura.getFactura().getCliente().isEstado())
            throw new FacturaDetalleException("No se puede registrar un detalle si el cliente no es valido");
            
        if(factura.getCantidad() == 0)
            throw new FacturaDetalleException("No se puede registrar un detalle si no hay una cantidad de producto valida");
        
        if(factura.getProducto() == null)
            throw new FacturaDetalleException("No se puede registrar un detalle de factura sin producto");
        
        List<ProductoExistenciaDTO> existencias = factura.getProducto().getExistencias();
        if(existencias.isEmpty())
            throw new FacturaDetalleException("No se puede registrar el detalle de factura, el producto no posee existencias");
        
        ProductoExistenciaDTO ultimaExistencia;
        if(existencias.size() == 1)
            ultimaExistencia = existencias.get(0);
        else{
            ultimaExistencia = existencias.get(0);
            for(int i = 1; i < existencias.size(); i++){
                if(existencias.get(i).getId() > ultimaExistencia.getId())
                    ultimaExistencia = existencias.get(i);
            }
        }
        
        if(ultimaExistencia.getCantidad() == 0)
            throw new FacturaDetalleException("No se puede registrar el detalle de factura, el producto no tiene existecias");
        
        List<ProductoPrecioDTO> precios = factura.getProducto().getPrecios();
        if(precios.isEmpty())
            throw new FacturaDetalleException("No se puede registrar el detalle de factura, el producto no tiene precios registrados");
        ProductoPrecioDTO ultimo;
        
        if(precios.size() == 1)
            ultimo = precios.get(0);
        else{
            ultimo = precios.get(0);
            for(int i = 1; i < precios.size(); i++){
                if(precios.get(i).getId() > ultimo.getId())
                    ultimo = precios.get(i);
            }
        }
        
        if(factura.getDescuentoFinal() > ultimo.getDescuentoMaximo())
            throw new FacturaDetalleException("El descuento final del detalle de factura es mayor al descuento máximo permitido por el producto");
        
        FacturaDetalle facturaDetalle = MapperUtils.EntityFromDto(factura, FacturaDetalle.class);
        facturaDetalle = facturaDetalleRepository.save(facturaDetalle);
        return ServiceConvertionHelper.OneToDto(facturaDetalle, FacturaDetalleDTO.class);
    }

    @Override
    @Transactional
    public Optional<FacturaDetalleDTO> update(FacturaDetalleDTO factura, Long id) throws FacturaDetalleException{
        Optional<FacturaDetalle> facturaDetalleModified = facturaDetalleRepository.findById(id);
        if(facturaDetalleModified.isPresent()){
            if(!facturaDetalleModified.get().getEstado())
                throw new FacturaDetalleException("No se puede modificar un dato inactivo");
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
