package ar.com.ada.api.questionados.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.questionados.entities.Categoria;
import ar.com.ada.api.questionados.repos.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repo;

    public List<Categoria> traerCategorias(){
        return repo.findAll();
    }

    public Categoria buscarCategoria(Integer categoriaId){

        Optional<Categoria> resultado = repo.findById(categoriaId);
        Categoria categoria = null;

        if (resultado.isPresent())
            categoria = resultado.get();

        return categoria;

    }

    public Categoria buscarCategoriaV2(Integer categoriaId){

        Categoria categoria = repo.findById(categoriaId.intValue());

        return categoria;

    }

    public boolean crearCategoria(Categoria categoria){

        if (existe(categoria.getNombre())){

            return false;
        }
        repo.save(categoria);

        return true;
    }

    public boolean existe(String nombre) {
        Categoria categoria = repo.findByNombre(nombre);
        return categoria != null;
    }

    public boolean existeV2(String nombre) {
        return repo.existsByNombre(nombre);
    }
}
