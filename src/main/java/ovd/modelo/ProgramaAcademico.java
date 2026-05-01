package ovd.modelo;

// Autor: Julián David Oviedo
// Asignatura: Programación Avanzada - Grupo 302
// Universidad Distrital Francisco José de Caldas - 2026

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase DTO que representa un Programa Académico ofertado por la universidad.
 * Contiene el código identificador y el nombre del programa.
 */
public class ProgramaAcademico implements Serializable {

    private static final long serialVersionUID = 1L;

    private int codigo;
    private String nombre;

    public ProgramaAcademico() {
    }

    public ProgramaAcademico(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    // ─── Getters y Setters ────────────────────────────────────────────────────

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // ─── equals / hashCode / toString ────────────────────────────────────────

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProgramaAcademico other = (ProgramaAcademico) obj;
        return codigo == other.codigo && Objects.equals(nombre, other.nombre);
    }

    @Override
    public String toString() {
        return "ProgramaAcademico [codigo=" + codigo + ", nombre=" + nombre + "]";
    }
}
