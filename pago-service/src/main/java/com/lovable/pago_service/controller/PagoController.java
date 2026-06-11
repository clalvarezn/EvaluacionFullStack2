package com.lovable.pago_service.controller;

import com.lovable.pago_service.model.Pago;
import com.lovable.pago_service.service.PagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    //Crear pago
    @PostMapping
    public ResponseEntity<Pago> procesarPago(@RequestBody Pago pago) {

        Pago pagoProcesado = pagoService.procesarPago(pago);

        return ResponseEntity.status(201)
                .header("X-Service-Name", "pago-service")
                .body(pagoProcesado);

    }

    // Muestra pagos existentes
    @GetMapping
    public ResponseEntity<List<Pago>> obtenerPagos() {
        List<Pago> pagos = pagoService.obtenerPagos();
        if (pagos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pagos);
    }

    //Muestra pagos por IdPago
    @GetMapping("/{idPago}")
    public ResponseEntity<Pago> obtenerPagoPorId(@PathVariable Integer idPago) {

        Pago pago = pagoService.obtenerPagoPorId(idPago);

        return ResponseEntity.ok(pago);
    }

}
