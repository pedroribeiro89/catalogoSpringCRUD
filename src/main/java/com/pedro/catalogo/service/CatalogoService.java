package com.pedro.catalogo.service;

import java.util.List;

import com.pedro.catalogo.model.Musica;

public interface CatalogoService {
    List<Musica> findAll();
    Musica findById(Long id);
    Musica save(Musica musica);
    Musica update(Musica musica);
    void delete(long musica);
}