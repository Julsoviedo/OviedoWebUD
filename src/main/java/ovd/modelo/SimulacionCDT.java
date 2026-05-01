package ovd.modelo;

// Autor: Julián David Oviedo
// Asignatura: Programación Avanzada - Grupo 302
// Universidad Distrital Francisco José de Caldas - 2026

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase DTO que representa una simulación de CDT
 * (Certificado de Depósito a Término).
 * Almacena los parámetros ingresados por el usuario y los resultados calculados.
 */
public class SimulacionCDT implements Serializable {

    private static final long serialVersionUID = 1L;

    // ── Parámetros de entrada ──────────────────────────────────────────────

    /** Capital inicial invertido en el CDT */
    private double capitalInicial;

    /** Tasa de interés Efectiva Anual (EA) en porcentaje, ej: 12.5 para 12.5% EA */
    private double tasaEA;

    /** Plazo de inversión en la unidad indicada por tipoPlazo */
    private int plazo;

    /**
     * Tipo de plazo: "dias", "meses" o "anios"
     * Define la unidad de tiempo del campo plazo
     */
    private String tipoPlazo;

    // ── Resultados calculados ──────────────────────────────────────────────

    /** Intereses generados al final del período */
    private double interesesGenerados;

    /** Valor total al vencimiento (capital + intereses) */
    private double valorFinal;

    /** Tasa nominal mensual calculada */
    private double tasaNominalMensual;

    /** Plazo convertido a días para el cálculo */
    private int plazoEnDias;

    /** Fecha en que se realizó la simulación */
    private LocalDate fechaSimulacion;

    public SimulacionCDT() {
        this.fechaSimulacion = LocalDate.now();
    }

    public SimulacionCDT(double capitalInicial, double tasaEA, int plazo, String tipoPlazo) {
        this.capitalInicial  = capitalInicial;
        this.tasaEA          = tasaEA;
        this.plazo           = plazo;
        this.tipoPlazo       = tipoPlazo;
        this.fechaSimulacion = LocalDate.now();
    }

    // ── Getters y Setters ──────────────────────────────────────────────────

    public double getCapitalInicial()       { return capitalInicial; }
    public void setCapitalInicial(double v) { this.capitalInicial = v; }

    public double getTasaEA()               { return tasaEA; }
    public void setTasaEA(double v)         { this.tasaEA = v; }

    public int getPlazo()                   { return plazo; }
    public void setPlazo(int v)             { this.plazo = v; }

    public String getTipoPlazo()            { return tipoPlazo; }
    public void setTipoPlazo(String v)      { this.tipoPlazo = v; }

    public double getInteresesGenerados()       { return interesesGenerados; }
    public void setInteresesGenerados(double v) { this.interesesGenerados = v; }

    public double getValorFinal()               { return valorFinal; }
    public void setValorFinal(double v)         { this.valorFinal = v; }

    public double getTasaNominalMensual()       { return tasaNominalMensual; }
    public void setTasaNominalMensual(double v) { this.tasaNominalMensual = v; }

    public int getPlazoEnDias()                 { return plazoEnDias; }
    public void setPlazoEnDias(int v)           { this.plazoEnDias = v; }

    public LocalDate getFechaSimulacion()           { return fechaSimulacion; }
    public void setFechaSimulacion(LocalDate v)     { this.fechaSimulacion = v; }

    // ── equals / hashCode / toString ──────────────────────────────────────

    @Override
    public int hashCode() {
        return Objects.hash(capitalInicial, tasaEA, plazo, tipoPlazo, fechaSimulacion);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SimulacionCDT other = (SimulacionCDT) obj;
        return Double.compare(capitalInicial, other.capitalInicial) == 0
                && Double.compare(tasaEA, other.tasaEA) == 0
                && plazo == other.plazo
                && Objects.equals(tipoPlazo, other.tipoPlazo)
                && Objects.equals(fechaSimulacion, other.fechaSimulacion);
    }

    @Override
    public String toString() {
        return "SimulacionCDT [capital=" + capitalInicial
                + ", tasaEA=" + tasaEA
                + ", plazo=" + plazo + " " + tipoPlazo
                + ", valorFinal=" + valorFinal + "]";
    }
}
