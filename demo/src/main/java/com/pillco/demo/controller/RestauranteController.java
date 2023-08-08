package com.pillco.demo.controller;
import com.pillco.demo.model.Restaurante;
import com.pillco.demo.service.RestauranteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ristorante")
public class RestauranteController {

    @Autowired
    RestauranteServiceImpl ristoranteService;

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Restaurante> eliminarRestaurante(@PathVariable Long id) {
        ristoranteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Restaurante> CrearRistorante(
            @RequestBody Restaurante p) {
        return new ResponseEntity<>(ristoranteService.save(p),
                HttpStatus.CREATED);
    }

    
    
    
     @GetMapping("/todas")
    public ResponseEntity<List<Restaurante>> listarRestaurantes() {
        return new ResponseEntity<>(ristoranteService.findByAll(),
                HttpStatus.OK);
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Restaurante> actualizarRestaurante(@PathVariable Long id, @RequestBody Restaurante p) {
        Restaurante restaurante = ristoranteService.findById(id);
        if (restaurante == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                restaurante.setNombre(p.getNombre());
                restaurante.setTelefono(p.getTelefono());
                restaurante.setCorreo(p.getCorreo());
                restaurante.setDireccion(p.getDireccion());
                restaurante.setClienteLista(p.getClienteLista());
                restaurante.setCartaheredao(p.getCartaheredao());

                return new ResponseEntity<>(ristoranteService.save(p), HttpStatus.OK);
            } catch (DataAccessException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    


}
