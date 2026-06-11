package com.lovable.producto_service.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name="producto")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Producto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idProducto;

    @NotBlank(message = "Debe ingresar el nombre del producto")
    @Column(nullable = false)
    private String nombreProducto;

    @NotBlank(message = "Debe ingresar la descripción")
    @Column(nullable = false)
    private String descripcion;

    @NotBlank(message = "Debe ingresar la talla")
    @Column(nullable = false)
    private String talla;

    @NotBlank(message = "Debe ingresar el color")
    @Column(nullable = false)
    private String color;

    @NotNull(message = "Debe ingresar el precio del producto")
    @Min(value = 1, message = "El precio debe ser mayor a 0")
    @Column(nullable = false)
    private Integer precio;

    @NotNull(message = "Debe ingresar la dirección de la imagen")
    @Column(nullable = false)
    private String imagen;

    @Column(nullable = false)
    private Boolean estadoProducto;

    @NotNull(message = "Debe ingresar el stock actual")
    @PositiveOrZero(message = "El stock no puede ser un número negativo")
    @Column(nullable = false)
    private Integer stockActual;

}
