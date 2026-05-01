package ovd.bean;

// Autor: Julián David Oviedo
// Asignatura: Programación Avanzada - Grupo 302
// Universidad Distrital Francisco José de Caldas - 2026

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import ovd.modelo.SimulacionCDT;
import ovd.modelo.SimulacionCDTDAO;

/**
 * Bean administrado por JSF que controla la lógica de la interfaz
 * del Simulador de CDT (Certificado de Depósito a Término).
 * Alcance de sesión para conservar los datos durante la navegación.
 */
@Named("cdtBean")
@SessionScoped
public class CDTBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /** DTO del CDT en proceso de simulación */
    private SimulacionCDT dto = new SimulacionCDT();

    /** Última simulación calculada (para mostrar resultados) */
    private SimulacionCDT ultimaSimulacion = null;

    /** Indica si hay resultados disponibles para mostrar */
    private boolean resultadoDisponible = false;

    // ── Getters y Setters ──────────────────────────────────────────────────

    public SimulacionCDT getDto()               { return dto; }
    public void setDto(SimulacionCDT dto)       { this.dto = dto; }

    public SimulacionCDT getUltimaSimulacion()  { return ultimaSimulacion; }
    public boolean isResultadoDisponible()      { return resultadoDisponible; }

    public ArrayList<SimulacionCDT> getHistorial() {
        return SimulacionCDTDAO.historialSimulaciones;
    }

    public int getTotalSimulaciones() {
        return SimulacionCDTDAO.totalSimulaciones();
    }

    // ── Valores formateados como pesos colombianos ─────────────────────────

    private final NumberFormat fmtPesos = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
    private final NumberFormat fmtPct   = NumberFormat.getNumberInstance();

    {
        fmtPct.setMaximumFractionDigits(4);
        fmtPct.setMinimumFractionDigits(4);
    }

    public String getCapitalFormateado() {
        return (ultimaSimulacion != null) ? fmtPesos.format(ultimaSimulacion.getCapitalInicial()) : "";
    }

    public String getInteresesFormateados() {
        return (ultimaSimulacion != null) ? fmtPesos.format(ultimaSimulacion.getInteresesGenerados()) : "";
    }

    public String getValorFinalFormateado() {
        return (ultimaSimulacion != null) ? fmtPesos.format(ultimaSimulacion.getValorFinal()) : "";
    }

    public String getTasaNominalFormateada() {
        return (ultimaSimulacion != null) ? fmtPct.format(ultimaSimulacion.getTasaNominalMensual()) + "%" : "";
    }

    public String getNombreTipoPlazo() {
        if (ultimaSimulacion == null) return "";
        return switch (ultimaSimulacion.getTipoPlazo()) {
            case "meses" -> "mes(es)";
            case "anios" -> "año(s)";
            default      -> "día(s)";
        };
    }

    // ── Acciones ───────────────────────────────────────────────────────────

    /**
     * Ejecuta la simulación del CDT con los datos del formulario.
     * Valida entradas, calcula resultados y redirige a la vista de resultados.
     *
     * @return nombre de la vista de resultados para la navegación JSF
     */
    public String simular() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        resultadoDisponible = false;

        // Validar capital
        if (dto.getCapitalInicial() <= 0) {
            ctx.addMessage("formCDT:capital",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Capital inválido", "El capital debe ser mayor a $0."));
            return null;
        }

        // Validar capital mínimo de CDT en Colombia
        if (dto.getCapitalInicial() < 100000) {
            ctx.addMessage("formCDT:capital",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Capital insuficiente", "El capital mínimo para un CDT es $100,000 COP."));
            return null;
        }

        // Validar tasa
        if (dto.getTasaEA() <= 0 || dto.getTasaEA() > 100) {
            ctx.addMessage("formCDT:tasaEA",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Tasa inválida", "La tasa EA debe estar entre 0.01% y 100%."));
            return null;
        }

        // Validar plazo
        if (dto.getPlazo() <= 0) {
            ctx.addMessage("formCDT:plazo",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Plazo inválido", "El plazo debe ser un número entero mayor a 0."));
            return null;
        }

        // Validar plazo mínimo CDT (30 días)
        String tipo = dto.getTipoPlazo();
        boolean plazoCortoDias   = "dias".equals(tipo)  && dto.getPlazo() < 30;
        boolean plazoCortoPlazo  = "meses".equals(tipo) && dto.getPlazo() < 1;
        if (plazoCortoDias || plazoCortoPlazo) {
            ctx.addMessage("formCDT:plazo",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Plazo mínimo", "El plazo mínimo de un CDT es 30 días o 1 mes."));
            return null;
        }

        // Calcular y guardar
        SimulacionCDTDAO.calcular(dto);
        SimulacionCDTDAO.guardar(dto);
        ultimaSimulacion    = dto;
        resultadoDisponible = true;

        System.out.println("[OviedoWebUD] " + dto.toString());

        // Preparar nuevo DTO para la siguiente simulación
        dto = new SimulacionCDT();

        return "resultadoCDT?faces-redirect=true";
    }

    /** Navega al formulario del simulador */
    public String irSimulador() {
        return "simuladorCDT?faces-redirect=true";
    }

    /** Navega al inicio */
    public String irInicio() {
        return "index?faces-redirect=true";
    }

    /** Limpia el formulario */
    public void limpiar() {
        dto = new SimulacionCDT();
        resultadoDisponible = false;
    }
}
