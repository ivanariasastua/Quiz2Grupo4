/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.service;

import java.util.Optional;
import org.una.tienda.facturacion.dto.ProductoExistenciaDTO;


public interface IProductoExistenciaService {
    
    public Optional<ProductoExistenciaDTO> findById(Long id);
    
    public ProductoExistenciaDTO create(ProductoExistenciaDTO producto);

    public Optional<ProductoExistenciaDTO> update(ProductoExistenciaDTO producto, Long id);
    
    public void delete(Long id);
}
