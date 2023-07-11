package com.luxs.copinx.copinx.Controller;

import com.luxs.copinx.copinx.service.Agua.Agua;
import com.luxs.copinx.copinx.service.Exceptions.aguaInvalidaException;
import com.luxs.copinx.copinx.service.GerenciadorAgua;
import com.luxs.copinx.copinx.service.GerenciadorReview;
import com.luxs.copinx.copinx.service.arquivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class aguaController {

    GerenciadorAgua gerenciador;

    @Autowired
    public aguaController(GerenciadorAgua gerenciador){
        this.gerenciador = gerenciador;
    }

    @GetMapping("/api/povoar")
    public String teste(){
        try {
            gerenciador.adicionarAgua(new Agua("Indaiá","Água Indaiá"));
            gerenciador.adicionarAgua(new Agua("Minalba", "Água Minalba"));
            gerenciador.adicionarAgua(new Agua("Crystal", "Água Crystal"));
            gerenciador.adicionarAgua(new Agua("Luiz", "Água da casa de Luiz"));
            gerenciador.adicionarAgua(new Agua("Lucas", "Água da casa de Lucas"));
            gerenciador.adicionarAgua(new Agua("Cauê", "Água da casa de Cauê"));
            gerenciador.adicionarAgua(new Agua("Quintuplos", "Água da casa dos Quintuplos"));
            gerenciador.adicionarAgua(new Agua("Amy e Mill", "Água da casa de Amy e Mill"));
            gerenciador.adicionarAgua(new Agua("Lua", "Água da casa de Lua"));
            gerenciador.adicionarAgua(new Agua("Isa", "Água da casa de Isa"));
            gerenciador.adicionarAgua(new Agua("Kla", "Água da casa de Kla"));
            gerenciador.adicionarAgua(new Agua("Karol", "Água da casa de Karol"));
            gerenciador.adicionarAgua(new Agua("Sofia", "Água da casa de Sofia"));
            gerenciador.adicionarAgua(new Agua("Campus I", "Água da UFPB - Campus I"));
            gerenciador.adicionarAgua(new Agua("Campus IV", "Água da UFPB - Campus IV"));
            gerenciador.adicionarAgua(new Agua("Bougainville", "Água do Bungas"));
        } catch (aguaInvalidaException e) {
            return e.getMessage();
        }
        return "povoado com sucesso";

    }

    @GetMapping("/api/aguas")
    public String listarAguas(){
        return gerenciador.toString();
    }

    @PostMapping("/api/agua/adicionar")
    public String adicionar(@RequestParam("nome") String nome, @RequestParam("descricao") String descricao){
        try {
            gerenciador.adicionarAgua(nome, descricao);
            arquivo.salvar(arquivo.GERENCIADOR_AGUA, gerenciador);
            return "Água adicionada com sucesso";
        } catch (aguaInvalidaException e) {
            return e.getMessage();
        }
    }
    @PostMapping("/api/agua/remover")
    public String remover(@RequestParam("nome") String nome){
        try {
            gerenciador.removerAgua(gerenciador.getAgua(nome));
            arquivo.salvar(arquivo.GERENCIADOR_AGUA, gerenciador);
            return "Água adicionada com sucesso";
        } catch (aguaInvalidaException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/api/{agua}/getReviews")
    public String getReviews(@PathVariable("agua") String agua){
        GerenciadorReview r = new GerenciadorReview();
        try {
            r.setReviews(gerenciador.getAgua(agua).getReviews());
            return r.toString();
        } catch (aguaInvalidaException e) {
            return "redirect:/waters";
        }
    }

}
