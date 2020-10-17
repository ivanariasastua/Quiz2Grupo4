/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tienda.facturacion.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author cordo
 */
@Controller
public class TipoCambioController {
    
    @RequestMapping("/tipo-cambio/a-dolares/")
    public @ResponseBody double conversionDolaresAColones() { 
        double conversionDolaresAColones= 610;
        return conversionDolaresAColones; 
    } 
    
}

    
    