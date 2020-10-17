/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.tienda.facturacion.entities.Factura;

/**
 *
 * @author Ivan Josué Arias Astua
 */
public interface IFacturasRepository extends JpaRepository<Factura, Long>{
    
    public List<Factura> findByCaja(Integer caja);
    public List<Factura> findByDescuentoGeneral(Double descuentoGeneral);
    public List<Factura> findByEstado(Boolean estado);
//    @Query("SELECT f FROM Factura f WHERE f.cliente.id = :id")
//    public List<Factura> findByCliente(@Param("id") Long id);
    
}
