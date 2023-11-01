package com.fourcatsdev.entitycrud.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourcatsdev.entitycrud.modelo.Curso;
import com.fourcatsdev.entitycrud.servico.CursoServico;

@Controller
@RequestMapping("/curso")
public class CursoControle {
	
	@Autowired
	private CursoServico cursoServico;
	
	@GetMapping("/novo")
    public String novoCurso(Model model) {	
		Curso curso = new Curso();
		model.addAttribute("novoCurso", curso);
        return "/novo-curso";
    }
}
