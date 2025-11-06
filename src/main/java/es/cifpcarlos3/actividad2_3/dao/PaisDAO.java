package es.cifpcarlos3.actividad2_3.dao;

import es.cifpcarlos3.actividad2_3.model.Continente;
import es.cifpcarlos3.actividad2_3.model.Pais;

import java.util.List;

public interface PaisDAO {
    List<Pais> obtenerPaisesPorContinenteConCapitalComenzandoPor(Continente continente, String capital);
    void actualizarCapitalPorId(int id, String capital);
    void eliminarPorCodigoContinente(String codigoContinente);
}
