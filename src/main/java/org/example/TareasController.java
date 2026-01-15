package org.example;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tareas")
public class TareasController {

    private final TareasService tareasService;

    public TareasController(TareasService tareasService) {
        this.tareasService = tareasService;
    }

    @GetMapping
    public List<Tarea> listarTareas() {
        return tareasService.listar();
    }

    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Long id) {
        tareasService.eliminar(id);
    }

    @GetMapping("/{id}")
    public String verTarea(@PathVariable Long id){
        return tareasService.verTarea(id);
    }

    @PostMapping
    public Tarea crearTarea(@Valid @RequestBody Tarea tarea) {
        return tareasService.crearTarea(tarea.getTitulo());
    }

    @PutMapping
    public Tarea modificarTarea(@Valid@RequestBody Tarea tarea){
        return tareasService.modificarTarea(tarea);
    }

}
