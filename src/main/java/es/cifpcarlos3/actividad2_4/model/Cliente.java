package es.cifpcarlos3.actividad2_4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private int id;
    private String dni;
    private String nombre;
    private String telefono;
    private String email;
}
