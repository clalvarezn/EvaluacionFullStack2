package com.lovable.pedido_service.client;

import com.lovable.pedido_service.client.dto.UsuarioResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UsuarioClient {

    private final WebClient webClient;

    //informacion del servicio usuario
    @Value("${microservices.usuario.base-url}")
    private String usuarioBaseUrl;

    public UsuarioClient(WebClient webClient) {
        this.webClient = webClient;
    }

    //Metodo que obtiene informacion del servicio usuario
    public UsuarioResponse obtenerUsuarioPorId(Integer id) {
        return webClient.get()
                .uri(usuarioBaseUrl + "/api/v1/usuarios/" + id)
                .retrieve()
                .bodyToMono(UsuarioResponse.class)
                .block();
    }

    public boolean existeUsuario(Integer id) {
        UsuarioResponse usuario = obtenerUsuarioPorId(id);
        return usuario != null;
    }

}
