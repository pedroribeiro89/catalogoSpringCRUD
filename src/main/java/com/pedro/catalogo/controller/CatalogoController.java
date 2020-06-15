package com.pedro.catalogo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.pedro.catalogo.model.Musica;
import com.pedro.catalogo.service.CatalogoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CatalogoController {
    @Autowired
    CatalogoService catalogoService;

    @RequestMapping(value="/musicas", method = RequestMethod.GET)
    public ModelAndView getMusicas() {
        ModelAndView mv = new ModelAndView("musicas");
        List<Musica> musicas = catalogoService.findAll();
        mv.addObject("musicas", musicas);
        return mv;
    }

    @RequestMapping(value="/musicas/{id}", method = RequestMethod.GET)
    public ModelAndView getMusicasDetalhes(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("musicaDetalhe");
        Musica musica = catalogoService.findById(id);
        mv.addObject("musica", musica);
        return mv;
    }

    @RequestMapping(value="/addMusica", method = RequestMethod.GET)
    public String getMusicaForm() {
        return "musicaForm";
    }

    @RequestMapping(value="/addMusica", method = RequestMethod.POST)
    public String salvarMusica(@Valid Musica musica, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("menssagem", "Campos obrigatorios nao preechidos");
            return "redirect:/addMusica";
        }
        musica.setData(LocalDate.now());
        catalogoService.save(musica);
        return "redirect:/musicas";
    }

    @RequestMapping(value="/updateMusica/{id}", method = RequestMethod.GET)
    public ModelAndView getFilledMusicaForm(@PathVariable("id") long id,  RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("updateMusicaForm");
        Musica musica = catalogoService.findById(id);
        mv.addObject("musica", musica);
        return mv;
    }

    @RequestMapping(value="/updateMusica/{id}", method = RequestMethod.POST)
    public String alterarMusica(@PathVariable("id") long id, @Valid Musica musica, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            musica.setId(id);
            attributes.addFlashAttribute("menssagem", "Campos obrigatorios nao preechidos");
            return "redirect:/updateMusicaForm";
        }
        catalogoService.update(musica);
        return "redirect:/musicas";
    }

    @RequestMapping(value="/deleteMusica/{id}", method = RequestMethod.GET)
    public String deletarMusica(@PathVariable("id") long id) {
        catalogoService.delete(id);
    
        return "redirect:/musicas";
    }
}