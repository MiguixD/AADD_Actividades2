package es.cifpcarlos3.actividad2_2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pais {
    private int id;
    private String nombre;
    private String capital;
    private Continente continente;
}
