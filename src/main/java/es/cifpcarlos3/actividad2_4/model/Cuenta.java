package es.cifpcarlos3.actividad2_4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {
    private int id;
    private String numero;
    private Cliente cliente;
    private BigDecimal saldo;
}
