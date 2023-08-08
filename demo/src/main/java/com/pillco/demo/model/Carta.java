package com.pillco.demo.model;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "carta")
@Data
public class Carta {

    @Id
    private Long idCarta;
     private String categoria;
    private String nombre;
   private List<Plato> platoLista;
    private int cantidad;
    
}
