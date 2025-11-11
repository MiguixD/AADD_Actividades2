package es.cifpcarlos3.actividad2_4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento {
    private int id;
    private Cuenta cuenta;
    private String concepto;
    private float importe;
    private Date fecha;
}
