package ar.com.ada.api.questionados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.questionados.entities.Categoria;
import ar.com.ada.api.questionados.models.response.GenericResponse;
import ar.com.ada.api.questionados.services.CategoriaService;

@RestController
public class CategoriaController {
    @Autowired
    private CategoriaService service;

    //GET /categorias
    @GetMapping("/categorias") //hacer el mapping
    public ResponseEntity<List<Categoria>> traerCategorias() { //return Response Entity
        return ResponseEntity.ok(service.traerCategorias()); //return entity con el valor esperado
    }

    //GET Categoría por Id
    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> traerCategoriaPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarCategoria(id));
    }

    @PostMapping(value = "/categorias")
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) {
        GenericResponse r = new GenericResponse();

        if (service.crearCategoria(categoria)) {
            r.id = categoria.getCategoriaId();
            r.isOk = true;
            r.message = "Categoria creada con exito";
            return ResponseEntity.ok(r);
        } else {
            r.isOk = false;
            r.message = "Esta categoria ya esta creada";
   
         return ResponseEntity.badRequest().body(r);
        }

    }
    
}
