package com.lovable.envio_service.controller;


import com.lovable.envio_service.model.Envio;
import com.lovable.envio_service.service.EnvioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    // Crear Envío
    @PostMapping
    public ResponseEntity<Envio> crearEnvio(@RequestBody Envio envio) {
        Envio nuevoEnvio = envioService.crearEnvio(envio);
        return ResponseEntity.ok(nuevoEnvio);
    }

    // Obtener Envío por ID
    @GetMapping("/{idEnvio}")
    public ResponseEntity<Envio> obtenerEnvio(@PathVariable Long idEnvio) {
        Envio envio = envioService.obtenerEnvio(idEnvio);
        return ResponseEntity.ok(envio);
    }

    // Listar todos los envíos
    @GetMapping
    public ResponseEntity<List<Envio>> listarEnvios() {
        return ResponseEntity.ok(envioService.listarEnvios());
    }

    // Actualizar Envío
    @PutMapping("/{idEnvio}")
    public ResponseEntity<Envio> actualizarEnvio(@PathVariable Long idEnvio,
                                                 @RequestBody Envio envioActualizado) {
        Envio envio = envioService.actualizarEnvio(idEnvio, envioActualizado);
        return ResponseEntity.ok(envio);
    }

    // Eliminar Envío
    @DeleteMapping("/{idEnvio}")
    public ResponseEntity<Void> eliminarEnvio(@PathVariable Long idEnvio) {
        envioService.eliminarEnvio(idEnvio);
        return ResponseEntity.noContent().build();
    }
}
