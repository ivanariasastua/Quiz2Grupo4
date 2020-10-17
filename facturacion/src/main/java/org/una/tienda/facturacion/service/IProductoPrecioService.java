/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.service;

import java.util.Optional;
import org.una.tienda.facturacion.dto.ProductoPrecioDTO;
import org.una.tienda.facturacion.exceptions.ProductoPrecioException;

/**
 *
 * @author cordo
 */
public interface IProductoPrecioService {
    
    public Optional<ProductoPrecioDTO> findById(Long id);
    
    public ProductoPrecioDTO create(ProductoPrecioDTO producto);

    public Optional<ProductoPrecioDTO> update(ProductoPrecioDTO producto, Long id) throws ProductoPrecioException;
    
    public void delete(Long id);
    
}
