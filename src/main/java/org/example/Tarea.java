package org.example;

import jakarta.validation.constraints.NotBlank;

public class Tarea {

    private Long id;
    @NotBlank(message = "El título no puede estar vacío")
    private String titulo;

    public Tarea(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
