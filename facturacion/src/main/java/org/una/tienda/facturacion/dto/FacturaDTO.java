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
public class FacturaDTO {
    
    private Long id;
    private Integer caja;
    private Double descuentoGeneral;
    private Boolean estado;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaModificacion;
    private ClienteDTO cliente;
    @JsonManagedReference
    private List<FacturaDetalleDTO> detalle = new ArrayList<>();
}
