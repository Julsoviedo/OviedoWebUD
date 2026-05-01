package ovd.modelo;

// Autor: Julián David Oviedo
// Asignatura: Programación Avanzada - Grupo 302
// Universidad Distrital Francisco José de Caldas - 2026

import java.util.ArrayList;

/**
 * Clase DAO (Data Access Object) para las Simulaciones de CDT.
 * Almacena un historial de simulaciones en memoria usando una lista estática.
 *
 * Lógica de cálculo CDT:
 *   Fórmula de interés compuesto:
 *     VF = Capital × (1 + TEA)^(plazo_dias / 365)
 *   Donde TEA = tasa efectiva anual expresada en decimal.
 */
public class SimulacionCDTDAO {

    /** Lista estática pública que almacena el historial de simulaciones */
    public static ArrayList<SimulacionCDT> historialSimulaciones = new ArrayList<>();

    /**
     * Ejecuta los cálculos financieros del CDT y completa el objeto SimulacionCDT.
     * Aplica la fórmula de interés compuesto con base 365 días.
     *
     * @param sim objeto SimulacionCDT con los parámetros de entrada ya establecidos
     * @return el mismo objeto con los resultados calculados
     */
    public static SimulacionCDT calcular(SimulacionCDT sim) {

        // 1. Convertir el plazo a días
        int plazoEnDias = convertirADias(sim.getPlazo(), sim.getTipoPlazo());
        sim.setPlazoEnDias(plazoEnDias);

        // 2. Convertir tasa EA de porcentaje a decimal
        double teaDecimal = sim.getTasaEA() / 100.0;

        // 3. Calcular tasa nominal mensual (para mostrar como dato adicional)
        //    TNM = ((1 + TEA)^(1/12) - 1) * 100
        double tnm = (Math.pow(1 + teaDecimal, 1.0 / 12.0) - 1) * 100;
        sim.setTasaNominalMensual(tnm);

        // 4. Calcular el Valor Final usando interés compuesto
        //    VF = Capital × (1 + TEA)^(días / 365)
        double vf = sim.getCapitalInicial() * Math.pow(1 + teaDecimal, (double) plazoEnDias / 365.0);
        sim.setValorFinal(vf);

        // 5. Calcular intereses generados
        double intereses = vf - sim.getCapitalInicial();
        sim.setInteresesGenerados(intereses);

        return sim;
    }

    /**
     * Guarda una simulación ya calculada en el historial.
     *
     * @param sim simulación a guardar
     */
    public static void guardar(SimulacionCDT sim) {
        historialSimulaciones.add(sim);
    }

    /**
     * Convierte el plazo a días según el tipo indicado.
     *
     * @param plazo     cantidad numérica del plazo
     * @param tipoPlazo "dias", "meses" o "anios"
     * @return plazo expresado en días
     */
    private static int convertirADias(int plazo, String tipoPlazo) {
        if (tipoPlazo == null) return plazo;
        return switch (tipoPlazo.toLowerCase()) {
            case "meses" -> plazo * 30;
            case "anios" -> plazo * 365;
            default      -> plazo; // "dias"
        };
    }

    /**
     * Obtiene el total de simulaciones realizadas.
     *
     * @return número de simulaciones en el historial
     */
    public static int totalSimulaciones() {
        return historialSimulaciones.size();
    }
}
