package ovd.modelo;

// Autor: Julián David Oviedo
// Asignatura: Programación Avanzada - Grupo 302
// Universidad Distrital Francisco José de Caldas - 2026

import java.util.ArrayList;

/**
 * Clase DAO (Data Access Object) para los Aspirantes universitarios.
 * Contiene una lista estática pública que persiste los registros durante la
 * ejecución de la aplicación, simulando una capa de acceso a datos en memoria.
 */
public class AspiranteDAO {

    /** Lista estática pública que almacena todos los aspirantes registrados */
    public static ArrayList<Aspirante> listaAspirantes = new ArrayList<>();

    /**
     * Agrega un nuevo aspirante a la lista de registros.
     *
     * @param aspirante el aspirante a registrar
     */
    public static void agregar(Aspirante aspirante) {
        listaAspirantes.add(aspirante);
    }

    /**
     * Verifica si ya existe un aspirante registrado con el mismo número de identificación.
     *
     * @param idPersona el número de identificación a verificar
     * @return true si ya existe, false en caso contrario
     */
    public static boolean existeId(long idPersona) {
        for (Aspirante a : listaAspirantes) {
            if (a.getIdPersona() == idPersona) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene el total de aspirantes registrados.
     *
     * @return número de aspirantes en la lista
     */
    public static int totalRegistros() {
        return listaAspirantes.size();
    }
}
