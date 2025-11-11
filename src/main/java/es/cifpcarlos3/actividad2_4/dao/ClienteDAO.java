package es.cifpcarlos3.actividad2_4.dao;

import es.cifpcarlos3.actividad2_4.model.Cliente;

import java.util.List;

public interface ClienteDAO {
    List<Cliente> obtenerClientes();
    Cliente obtenerClientePorId(int id);
}
