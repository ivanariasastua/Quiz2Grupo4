/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/tipo-cambio")
public class TipoCambioController {
    
    @GetMapping("/a-dolares/{valorEnColones}")
    public String conversionDolaresAColones(@PathVariable("valorEnColones") String valorEnColones){
        Double valorColones = Double.parseDouble(valorEnColones);
        return String.valueOf( valorColones / 610);
    }
}

    
    