package es.cifpcarlos3.actividad2_3.dao;

import es.cifpcarlos3.actividad2_3.model.Continente;

public interface ContinenteDAO {
    Continente obtenerContinentePorNombre(String nombre);
    void insertarContinente(Continente continente);
    void eliminarPorCodigo(String codigo);
}
