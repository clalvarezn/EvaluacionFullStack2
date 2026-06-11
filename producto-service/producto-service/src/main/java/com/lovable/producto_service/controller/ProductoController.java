package com.lovable.producto_service.controller;

import com.lovable.producto_service.dto.ProductoRequestDTO;
import com.lovable.producto_service.dto.ProductoResponseDTO;
import com.lovable.producto_service.service.ProductoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoServiceImpl productoService;

    // 1. Obtener todos (Retorna lista de preoducto)
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> lista(){
        List<ProductoResponseDTO> productos = productoService.obtenerTodos();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    // 2. Crear producto
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> guardar(@Valid @RequestBody ProductoRequestDTO request) {
        ProductoResponseDTO productoNuevo = productoService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    // 3. Buscar por ID
    @GetMapping("/{idProducto}")
    public ResponseEntity<ProductoResponseDTO> buscar(@PathVariable Integer idProducto){
        try{
            ProductoResponseDTO producto = productoService.obtenerPorId(idProducto);
            return ResponseEntity.ok(producto);
        } catch (Exception e ){
            return ResponseEntity.notFound().build();
        }
    }

    // 4. Actualizar
    @PutMapping("/{idProducto}")
    public ResponseEntity<ProductoResponseDTO> actualizar(@PathVariable Integer idProducto, @Valid @RequestBody ProductoRequestDTO request) {
        try {
            ProductoResponseDTO productoActualizado = productoService.actualizar(idProducto, request);
            return ResponseEntity.ok(productoActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. Eliminar (Se mantiene igual pero cambia el estado a FALSO)
    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idProducto) {
        try {
            productoService.desactivarProducto(idProducto);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 6. Actualizar (Se mantiene igual del stock)
    @PatchMapping("/{idProducto}/stock")
    public ResponseEntity<?> modificarStock(
            @PathVariable Integer idProducto,
            @RequestParam Integer cantidad) {
        try {
            productoService.actualizarStock(idProducto, cantidad);
            return ResponseEntity.ok().body("Stock actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
