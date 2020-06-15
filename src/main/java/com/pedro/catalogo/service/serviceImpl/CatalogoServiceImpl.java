package com.pedro.catalogo.service.serviceImpl;

import java.util.List;

import com.pedro.catalogo.model.Musica;
import com.pedro.catalogo.repository.CatalogoRepository;
import com.pedro.catalogo.service.CatalogoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogoServiceImpl implements CatalogoService {

    @Autowired
    CatalogoRepository catalogoRepository;

    @Override
    public List<Musica> findAll() {
        return catalogoRepository.findAll();
    }

    @Override
    public Musica findById(Long id) {
        return catalogoRepository.findById(id).get();
    }

    @Override
    public Musica save(Musica musica) {
        return catalogoRepository.save(musica);
    }

    @Override
    public Musica update(Musica updatedMusica) {
        Musica musica = catalogoRepository.getOne(updatedMusica.getId());
        musica.setTitulo(updatedMusica.getTitulo());
        musica.setAutor(updatedMusica.getAutor());
        musica.setLetra(updatedMusica.getLetra());
        return catalogoRepository.save(musica);
    }

    @Override
    public void delete(long id) {
        catalogoRepository.deleteById(id);
    }
    
}