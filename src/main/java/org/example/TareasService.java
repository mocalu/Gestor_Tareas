package org.example;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TareasService {

    private List<Tarea> tareas = new ArrayList<>();

    public TareasService() {
        tareas.add(new Tarea(1L, "Aprender Spring Boot"));
        tareas.add(new Tarea(2L, "Practicar Git"));
    }

    public List<Tarea> listar() {
        return tareas;
    }

    public void eliminar(Long id) {
        tareas.removeIf(tarea -> tarea.getId().equals(id));
    }

    public String verTarea(Long id){
        for(Tarea tarea : tareas){
            if(tarea.getId().equals(id)){
                return tarea.getTitulo();
            }
        }
        throw new TareaNoEncontradaException(id);
    }

    public Tarea crearTarea(String titulo) {
        Long nuevoId = tareas.isEmpty() ? 1L : tareas.get(tareas.size() - 1).getId() + 1;
        Tarea nueva = new Tarea(nuevoId, titulo);
        tareas.add(nueva);
        return nueva;
    }

    public Tarea modificarTarea(Tarea tareaModif){
        for(Tarea tarea : tareas){
            if(tarea.getId().equals(tareaModif.getId())){
                tarea.setTitulo(tareaModif.getTitulo());
                return tarea;
            }
        }
        throw new TareaNoEncontradaException(tareaModif.getId());
    }

}
