package ovd.modelo;

// Autor: Julián David Oviedo
// Asignatura: Programación Avanzada - Grupo 302
// Universidad Distrital Francisco José de Caldas - 2026

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase DTO que representa un Aspirante universitario.
 * Hereda los datos personales de Persona y agrega la fecha de registro
 * y el programa académico de interés.
 */
public class Aspirante extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Fecha en que el aspirante realizó su registro */
    private LocalDate fechaRegistro;

    /** Programa académico al que aspira ingresar */
    private ProgramaAcademico programaAcademico;

    public Aspirante() {
        super();
        this.programaAcademico = new ProgramaAcademico();
    }

    public Aspirante(long idPersona, String nombres, String apellidos,
                     String correo, String telefono,
                     LocalDate fechaRegistro, ProgramaAcademico programaAcademico) {
        super(idPersona, nombres, apellidos, correo, telefono);
        this.fechaRegistro      = fechaRegistro;
        this.programaAcademico  = programaAcademico;
    }

    // ─── Getters y Setters ────────────────────────────────────────────────────

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public ProgramaAcademico getProgramaAcademico() {
        return programaAcademico;
    }

    public void setProgramaAcademico(ProgramaAcademico programaAcademico) {
        this.programaAcademico = programaAcademico;
    }

    // ─── equals / hashCode / toString ────────────────────────────────────────

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(fechaRegistro, programaAcademico);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        Aspirante other = (Aspirante) obj;
        return Objects.equals(fechaRegistro,     other.fechaRegistro)
                && Objects.equals(programaAcademico, other.programaAcademico);
    }

    @Override
    public String toString() {
        return "Aspirante [id=" + getIdPersona()
                + ", nombres=" + getNombres()
                + ", apellidos=" + getApellidos()
                + ", correo=" + getCorreo()
                + ", telefono=" + getTelefono()
                + ", programa=" + programaAcademico
                + ", fechaRegistro=" + fechaRegistro + "]";
    }
}
