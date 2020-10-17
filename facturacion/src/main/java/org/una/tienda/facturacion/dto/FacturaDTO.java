/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.dto;

import java.util.Date;
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
public class FacturaDTO {
    
    private Long id;
    private Integer caja;
    private Double descuentoGeneral;
    private Boolean estado;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private ClientesDTO cliente;
}
