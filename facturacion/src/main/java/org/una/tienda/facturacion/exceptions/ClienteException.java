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
public class ClienteException extends Exception{
    
    public static final long serialVersionUID = 700L;
    
    public ClienteException(String mensaje){
        super(mensaje);
    }
}
