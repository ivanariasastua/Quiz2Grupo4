/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.service;

import java.util.Optional;
import org.una.tienda.facturacion.dto.ProductoDTO;
import org.una.tienda.facturacion.exceptions.ProductoException;


public interface IProductoService {
    
    public Optional<ProductoDTO> findById(Long id);
    
    public ProductoDTO create(ProductoDTO producto);

    public Optional<ProductoDTO> update(ProductoDTO producto, Long id) throws ProductoException;
    
    public void delete(Long id);
}
