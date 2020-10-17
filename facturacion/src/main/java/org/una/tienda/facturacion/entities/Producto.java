/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "ut_productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Producto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name= "descripcion")
    private String descripcion;
    
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
    
    @Column(name= "impuesto")
    private Double impuesto;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto") 
    private List<ProductoExistencia> existencias = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productos") 
    private List<ProductoPrecio> precios = new ArrayList<>();
    
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
