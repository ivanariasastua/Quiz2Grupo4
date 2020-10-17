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
import org.una.tienda.facturacion.dto.ProductoExistenciaDTO;
import org.una.tienda.facturacion.entities.ProductoExistencia;
import org.una.tienda.facturacion.exceptions.ProductoExistenciaException;
import org.una.tienda.facturacion.repository.IProductoExistenciaRepository;
import org.una.tienda.facturacion.utils.MapperUtils;
import org.una.tienda.facturacion.utils.ServiceConvertionHelper;

/**
 *
 * @author Dios
 */
@Service
public class ProductoExistenciaServiceImplementation implements IProductoExistenciaService{

    @Autowired
    private IProductoExistenciaRepository productoExistenciaRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductoExistenciaDTO> findById(Long id) {
        return ServiceConvertionHelper.OptionalOneToOptionalDto(productoExistenciaRepository.findById(id), ProductoExistenciaDTO.class);
    }

    @Override
    @Transactional
    public ProductoExistenciaDTO create(ProductoExistenciaDTO producto) {
        ProductoExistencia entidad = MapperUtils.EntityFromDto(producto, ProductoExistencia.class);
        entidad = productoExistenciaRepository.save(entidad);
        return ServiceConvertionHelper.OneToDto(entidad, ProductoExistenciaDTO.class);
    }

    @Override
    @Transactional
    public Optional<ProductoExistenciaDTO> update(ProductoExistenciaDTO producto, Long id) throws ProductoExistenciaException{
        Optional<ProductoExistencia> productoExistenciaModified = productoExistenciaRepository.findById(id);
        if (productoExistenciaModified.isPresent()) {
            if(!productoExistenciaModified.get().isEstado())
                throw new ProductoExistenciaException("No se pueden modificar los datos inactivos");
            ProductoExistencia entidad = MapperUtils.EntityFromDto(producto, ProductoExistencia.class);
            entidad = productoExistenciaRepository.save(entidad);
            return ServiceConvertionHelper.oneToOptionalDto(entidad, ProductoExistenciaDTO.class);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productoExistenciaRepository.deleteById(id);
    }
    
    
}
