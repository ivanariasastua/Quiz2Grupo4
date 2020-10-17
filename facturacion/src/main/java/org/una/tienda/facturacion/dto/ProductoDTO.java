/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Ivan Josué Arias Astua
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoDTO {
    
    private Long id;
    private String descripcion;
    private Boolean estado;
    private Date fechaModificacion;
    private Date fechaRegistro;
    private Double impuesto;
    @JsonManagedReference
    private List<ProductoExistenciaDTO> existencias = new ArrayList<>();
    @JsonManagedReference
    private List<ProductoPrecioDTO> precios = new ArrayList<>();
}
