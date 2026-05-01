package ovd.modelo;

// Autor: Julián David Oviedo
// Asignatura: Programación Avanzada - Grupo 302
// Universidad Distrital Francisco José de Caldas - 2026

import java.util.ArrayList;

/**
 * Clase DAO (Data Access Object) para los Programas Académicos.
 * Contiene una lista estática que simula la persistencia de datos en memoria.
 * Los programas académicos ofertados se cargan una única vez al iniciar la aplicación.
 */
public class ProgramaAcademicoDAO {

    /** Lista estática pública que almacena los programas académicos ofertados */
    public static ArrayList<ProgramaAcademico> listaProgramas = new ArrayList<>();

    /**
     * Carga los programas académicos disponibles si la lista aún está vacía.
     * Incluye los 6 programas definidos por la universidad para este sistema.
     */
    public static void cargarProgramas() {
        if (listaProgramas.isEmpty()) {
            listaProgramas.add(new ProgramaAcademico(10, "Ingeniería de Sistemas"));
            listaProgramas.add(new ProgramaAcademico(20, "Ingeniería Industrial"));
            listaProgramas.add(new ProgramaAcademico(30, "Matemática"));
            listaProgramas.add(new ProgramaAcademico(40, "Administración de Empresas"));
            listaProgramas.add(new ProgramaAcademico(50, "Derecho"));
            listaProgramas.add(new ProgramaAcademico(60, "Ingeniería Telemática"));
        }
    }
}
