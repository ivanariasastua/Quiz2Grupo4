/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Ivan Josué Arias Astua
 */
@Entity
@Table(name = "ut_Productos_Precios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductosPrecios implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "descuento_maximo")
    private Double descuentoMaximo;
    
    @Column(name = "descuento_promocional")
    private Double descuentoPromocional;
    
    @Column(columnDefinition="TINYINT")
    private Boolean estado;
    
    @Column(name = "fecha_modificacion", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaModificacion;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;
    
    @Column(name = "precio_colones")
    private Double precioColones;
    
    @ManyToOne 
    @JoinColumn(name="productos_id")
    private Productos productos;
    
    @PrePersist
    public void prePersist(){
        estado=true;
        fechaRegistro = new Date();
        fechaModificacion = new Date();
    }
    
    @PreUpdate
    public void preUpdate() {
        fechaModificacion = new Date();
    }
}
