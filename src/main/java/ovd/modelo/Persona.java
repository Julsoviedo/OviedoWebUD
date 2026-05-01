package ovd.modelo;

// Autor: Julián David Oviedo
// Asignatura: Programación Avanzada - Grupo 302
// Universidad Distrital Francisco José de Caldas - 2026

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase DTO base que representa los datos personales de una persona.
 * Sirve como superclase para las entidades que requieran información básica de contacto.
 */
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    private long idPersona;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;

    public Persona() {
    }

    public Persona(long idPersona, String nombres, String apellidos,
                   String correo, String telefono) {
        this.idPersona = idPersona;
        this.nombres   = nombres;
        this.apellidos = apellidos;
        this.correo    = correo;
        this.telefono  = telefono;
    }

    // ─── Getters y Setters ────────────────────────────────────────────────────

    public long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(long idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // ─── equals / hashCode ────────────────────────────────────────────────────

    @Override
    public int hashCode() {
        return Objects.hash(idPersona, nombres, apellidos, correo, telefono);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Persona other = (Persona) obj;
        return idPersona == other.idPersona
                && Objects.equals(nombres,   other.nombres)
                && Objects.equals(apellidos, other.apellidos)
                && Objects.equals(correo,    other.correo)
                && Objects.equals(telefono,  other.telefono);
    }
}
