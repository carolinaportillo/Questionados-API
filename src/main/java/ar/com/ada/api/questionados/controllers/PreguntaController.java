package ar.com.ada.api.questionados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.models.request.InfoPreguntaNueva;
import ar.com.ada.api.questionados.models.response.GenericResponse;
import ar.com.ada.api.questionados.services.PreguntaService;

@RestController
public class PreguntaController {

    @Autowired
    private PreguntaService service;

    @GetMapping("/preguntas")
    public ResponseEntity<List<Pregunta>> traerPreguntas(){

        return ResponseEntity.ok(service.traerPreguntas());
    }

    @GetMapping("/preguntas/{id}")
    public ResponseEntity <Pregunta> buscarPreguntaPorId(@PathVariable Integer id){

        return ResponseEntity.ok(service.buscarPreguntaPorId(id));
    }

    @PostMapping ("/preguntas")
    public ResponseEntity<?> crearPregunta(@RequestBody InfoPreguntaNueva preguntaNueva){

        GenericResponse respuesta = new GenericResponse();

        Pregunta pregunta = service.crearPregunta(preguntaNueva.enunciado, preguntaNueva.categoriaId, preguntaNueva.opciones);
        
        respuesta.isOk = true;
        respuesta.id = pregunta.getPreguntaId();
        respuesta.message = "La pregunta fue creada con exito";

        return ResponseEntity.ok(respuesta);

    }
    

    // DELETE /preguntas/{id} -> elimina pregunta
    @DeleteMapping("/preguntas/{id}")
    public ResponseEntity<GenericResponse> eliminarPregunta(@PathVariable Integer id) {

        GenericResponse respuesta = new GenericResponse();

        service.eliminarPreguntaPorId(id);

        respuesta.isOk = true;
        respuesta.message = "La pregunta fue eliminada correctamente.";

        return ResponseEntity.ok(respuesta);
    }
    
}
