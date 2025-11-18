package es.cifpcarlos3.actividad2_4.dao;

import es.cifpcarlos3.actividad2_4.model.Cuenta;

import java.util.List;

public interface CuentaDAO {
    List<Cuenta> obtenerCuentas();
    int obtenerMaxIdCuenta();
    void insertarCuenta();
    void actualizarSaldoCuenta();
    Cuenta obtenerCuentaPorId(int id);
    void transferenciaEntreCuentas();
    List<Cuenta> obtenerCuentasPorIdCliente(int idCliente);
}
