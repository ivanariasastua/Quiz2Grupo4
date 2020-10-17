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
import org.una.tienda.facturacion.dto.ProductoPrecioDTO;
import org.una.tienda.facturacion.entities.ProductoPrecio;
import org.una.tienda.facturacion.repository.IProductoPrecioRepository;
import org.una.tienda.facturacion.utils.MapperUtils;
import org.una.tienda.facturacion.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class ProductoPrecioServiceImplementation implements IProductoPrecioService {

    @Autowired
    private IProductoPrecioRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductoPrecioDTO> findById(Long id) {
        return ServiceConvertionHelper.OptionalOneToOptionalDto(productoRepository.findById(id), ProductoPrecioDTO.class);
    }

    @Override
    @Transactional
    public ProductoPrecioDTO create(ProductoPrecioDTO producto) {
        ProductoPrecio productoPrecio = MapperUtils.EntityFromDto(producto, ProductoPrecio.class);
        productoPrecio = productoRepository.save(productoPrecio);
        return ServiceConvertionHelper.OneToDto(productoPrecio, ProductoPrecioDTO.class);
    }

    @Override
    @Transactional
    public Optional<ProductoPrecioDTO> update(ProductoPrecioDTO producto, Long id) {
        if (productoRepository.findById(id).isPresent()) {
            ProductoPrecio productoPrecio = MapperUtils.EntityFromDto(producto, ProductoPrecio.class);
            productoPrecio = productoRepository.save(productoPrecio);
            return ServiceConvertionHelper.oneToOptionalDto(productoPrecio, ProductoPrecioDTO.class);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

}
