package com.finalproject.ventas_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CarritoDTO {

    private Long idCarrito;
    private Double total;
    private List<Long> codigosProductos;

}
