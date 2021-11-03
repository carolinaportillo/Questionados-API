package ar.com.ada.api.questionados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.questionados.entities.Pregunta;
import ar.com.ada.api.questionados.models.request.RespuestaAVerificar;
import ar.com.ada.api.questionados.models.response.PreguntaAResolver;
import ar.com.ada.api.questionados.models.response.RespuestaVerificada;
import ar.com.ada.api.questionados.services.QuestionadosService;

@RestController
public class QuestionadosController {

    @Autowired
    QuestionadosService service;
    //Obtener siguiente pregunta

    
    //GET /questionados/next
    @GetMapping("/questionados/next")
    public ResponseEntity<PreguntaAResolver> traerPreguntaRandom() {

        Pregunta pregunta = service.traerPreguntaRandom();

        PreguntaAResolver preguntaAResolver = PreguntaAResolver.convertirDesde(pregunta);

        return ResponseEntity.ok(preguntaAResolver);

        //return ResponseEntity.ok(service.traerPreguntaRandom());

    }

    //verificar si una respuesta de una pregunta es valida
    @PostMapping("/questionados/verificaciones")
    public ResponseEntity<RespuestaVerificada> verificarRespuesta(@RequestBody RespuestaAVerificar respuestaAVerificar){

        RespuestaVerificada respuestaVerificada = new RespuestaVerificada();
        
        if (service.verificarRespuesta(respuestaAVerificar.preguntaId, respuestaAVerificar.respuestaId)) {
            respuestaVerificada.esCorrecta = true;
        } else {
            respuestaVerificada.esCorrecta = false;
        }

        return ResponseEntity.ok(respuestaVerificada);

    }
    
}
