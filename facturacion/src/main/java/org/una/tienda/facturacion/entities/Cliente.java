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
@Table(name = "ut_clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cliente implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 100)
    private String direccion;
    
    @Column(length = 100)
    private String email;
    
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
    
    @Column(length = 100)
    private String nombre;
    
    @Column(length = 8)
    private String telefono;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente") 
    private List<Factura> facturas = new ArrayList<>();
    
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
