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
@Table(name = "ut_productos_existentes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoExistencia implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name= "cantidad")
    private Double cantidad;
    
    @Column(columnDefinition="TINYINT")
    private boolean estado;
    
    @Column(name = "fecha_modificacion")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    
    
    @Column(name = "fecha_registro")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    
    @ManyToOne 
    @JoinColumn(name="productos_id")
    Producto producto;
    
    @PrePersist
    public void PrePersist(){
        this.estado = true;
        this.fechaRegistro = new Date();
        this.fechaModificacion = new Date();
    }
    
    @PreUpdate
    public void PreUpdate(){
        this.fechaModificacion = new Date();
    }
}
