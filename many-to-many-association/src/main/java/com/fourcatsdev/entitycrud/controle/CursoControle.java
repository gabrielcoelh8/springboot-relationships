package com.fourcatsdev.entitycrud.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fourcatsdev.entitycrud.modelo.Curso;
import com.fourcatsdev.entitycrud.modelo.Estudante;
import com.fourcatsdev.entitycrud.servico.CursoServico;

import jakarta.validation.Valid;

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
	
	@PostMapping("/gravar")
	public String gravarCurso(@ModelAttribute("novoCurso") @Valid Curso curso,
			BindingResult erros,
			RedirectAttributes attributes) {
		if(erros.hasErrors()) {
			return "/novo-curso";
		}
		cursoServico.salvar(curso);
		attributes.addFlashAttribute("mensagem", "Curso salvo com sucesso!");
        return "redirect:/curso/novo";
    }
}
