package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TareasController {

    @GetMapping("/tareas")
    public List<String> listarTareas() {
        // Devuelve una lista de ejemplo
        return List.of("Practica Spring Boot", "Hacer mini proyecto", "Subir a GitHub");
    }

}
