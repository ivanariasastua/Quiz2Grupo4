/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.exceptions;

/**
 *
 * @author Ivan Josué Arias Astua
 */
public class ProductoConDescuentoMayorAlPermitidoException extends Exception{
    
    private String mensaje;

    public ProductoConDescuentoMayorAlPermitidoException(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
    
    
}
