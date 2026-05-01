package ovd.bean;

// Autor: Julián David Oviedo
// Asignatura: Programación Avanzada - Grupo 302
// Universidad Distrital Francisco José de Caldas - 2026

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import ovd.modelo.Aspirante;
import ovd.modelo.AspiranteDAO;
import ovd.modelo.ProgramaAcademico;
import ovd.modelo.ProgramaAcademicoDAO;

/**
 * Bean administrado por JSF que controla la lógica de la interfaz de usuario.
 * Gestiona el formulario de registro de aspirantes y la vista del listado administrador.
 * Alcance de sesión para mantener los datos mientras el usuario navega.
 */
@Named("aspiranteBean")
@SessionScoped
public class AspiranteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /** DTO del aspirante en proceso de registro */
    private Aspirante dto = new Aspirante();

    /** Índice seleccionado del programa académico en el menú desplegable */
    private int indiceProgramaSeleccionado = -1;

    /** Mensaje de resultado tras el intento de registro */
    private String mensajeResultado = "";

    /** Indica si el último registro fue exitoso */
    private boolean registroExitoso = false;

    public AspiranteBean() {
        // Cargar programas académicos al iniciar el bean
        ProgramaAcademicoDAO.cargarProgramas();
    }

    // ─── Getters y Setters ────────────────────────────────────────────────────

    public Aspirante getDto() {
        return dto;
    }

    public void setDto(Aspirante dto) {
        this.dto = dto;
    }

    public int getIndiceProgramaSeleccionado() {
        return indiceProgramaSeleccionado;
    }

    public void setIndiceProgramaSeleccionado(int indiceProgramaSeleccionado) {
        this.indiceProgramaSeleccionado = indiceProgramaSeleccionado;
    }

    public ArrayList<Aspirante> getListaAspirantes() {
        return AspiranteDAO.listaAspirantes;
    }

    public ArrayList<ProgramaAcademico> getListaProgramas() {
        return ProgramaAcademicoDAO.listaProgramas;
    }

    public String getMensajeResultado() {
        return mensajeResultado;
    }

    public boolean isRegistroExitoso() {
        return registroExitoso;
    }

    public int getTotalRegistros() {
        return AspiranteDAO.totalRegistros();
    }

    // ─── Acciones ─────────────────────────────────────────────────────────────

    /**
     * Acción que registra un nuevo aspirante tras validar los datos del formulario.
     * Verifica que el número de identificación no esté duplicado, asigna el programa
     * académico seleccionado y guarda el registro con la fecha actual.
     */
    public void registrar() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        registroExitoso = false;
        mensajeResultado = "";

        // Validar que el ID sea positivo
        if (dto.getIdPersona() <= 0) {
            ctx.addMessage("formRegistro:idPersona",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Identificación inválida", "Ingrese un número de identificación válido (mayor que 0)."));
            return;
        }

        // Validar identificación duplicada
        if (AspiranteDAO.existeId(dto.getIdPersona())) {
            ctx.addMessage("formRegistro:idPersona",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "ID duplicado", "Ya existe un aspirante registrado con ese número de identificación."));
            return;
        }

        // Validar que se haya seleccionado un programa
        if (indiceProgramaSeleccionado < 0
                || indiceProgramaSeleccionado >= ProgramaAcademicoDAO.listaProgramas.size()) {
            ctx.addMessage("formRegistro:programaAcademico",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Programa requerido", "Debe seleccionar un programa académico."));
            return;
        }

        // Asignar programa y fecha
        ProgramaAcademico programa = ProgramaAcademicoDAO.listaProgramas.get(indiceProgramaSeleccionado);
        dto.setProgramaAcademico(programa);
        dto.setFechaRegistro(LocalDate.now());

        // Guardar en DAO
        AspiranteDAO.agregar(dto);
        System.out.println("[OviedoWebUD] Aspirante registrado: " + dto.toString());

        registroExitoso = true;
        mensajeResultado = "¡Registro exitoso! Bienvenido(a), " + dto.getNombres() + " " + dto.getApellidos() + ".";

        // Reiniciar formulario
        dto = new Aspirante();
        indiceProgramaSeleccionado = -1;
    }

    /**
     * Navega a la vista de administrador con el listado de aspirantes.
     *
     * @return nombre de la vista destino para la navegación JSF
     */
    public String irAdmin() {
        return "admin?faces-redirect=true";
    }

    /**
     * Navega de regreso al formulario de registro.
     *
     * @return nombre de la vista destino para la navegación JSF
     */
    public String irRegistro() {
        mensajeResultado = "";
        registroExitoso = false;
        return "registro?faces-redirect=true";
    }

    /**
     * Limpiar el formulario de registro.
     */
    public void limpiarFormulario() {
        dto = new Aspirante();
        indiceProgramaSeleccionado = -1;
        mensajeResultado = "";
        registroExitoso = false;
    }
}
