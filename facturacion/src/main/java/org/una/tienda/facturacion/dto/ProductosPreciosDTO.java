/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.dto;

import java.util.Date;
import javax.json.bind.annotation.JsonbDateFormat;
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
public class ProductosPreciosDTO {
    
    private Long id;
    private double descuentoMaximo;
    private double descuentoPromocional;
    private boolean estado;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaModificacion;
    private double precioColones;
    private ProductosDTO productos;
}
