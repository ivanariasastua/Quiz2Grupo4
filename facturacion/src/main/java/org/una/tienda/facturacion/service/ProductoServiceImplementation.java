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
import org.una.tienda.facturacion.dto.ProductoDTO;
import org.una.tienda.facturacion.entities.Producto;
import org.una.tienda.facturacion.repository.IProductoRepository;
import org.una.tienda.facturacion.utils.MapperUtils;
import org.una.tienda.facturacion.utils.ServiceConvertionHelper;

@Service
public class ProductoServiceImplementation implements IProductoService{

    @Autowired
    private IProductoRepository productoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductoDTO> findById(Long id) {
        return ServiceConvertionHelper.OptionalOneToOptionalDto(productoRepository.findById(id), ProductoDTO.class);
    }

    @Override
    @Transactional
    public ProductoDTO create(ProductoDTO producto) {
        Producto entidad = MapperUtils.EntityFromDto(producto, Producto.class);
        entidad = productoRepository.save(entidad);
        return ServiceConvertionHelper.OneToDto(entidad, ProductoDTO.class);
    }

    @Override
    @Transactional
    public Optional<ProductoDTO> update(ProductoDTO producto, Long id) {
        if (productoRepository.findById(id).isPresent()) {
            Producto entidad = MapperUtils.EntityFromDto(producto, Producto.class);
            entidad = productoRepository.save(entidad);
            return ServiceConvertionHelper.oneToOptionalDto(entidad, ProductoDTO.class);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productoRepository.deleteById(id);
    }
    
}
