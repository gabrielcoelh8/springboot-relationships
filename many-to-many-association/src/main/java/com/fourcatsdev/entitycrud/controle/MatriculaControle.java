package com.fourcatsdev.entitycrud.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fourcatsdev.entitycrud.excecao.EstudanteNotFoundException;
import com.fourcatsdev.entitycrud.modelo.Curso;
import com.fourcatsdev.entitycrud.modelo.Estudante;
import com.fourcatsdev.entitycrud.servico.CursoServico;
import com.fourcatsdev.entitycrud.servico.EstudanteServico;
import com.fourcatsdev.entitycrud.servico.MatriculaServico;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/matricula")
public class MatriculaControle {
	@Autowired
	private EstudanteServico estudanteServico;
	@Autowired
	private CursoServico cursoServico;
	@Autowired
	private MatriculaServico matriculaServico;
	
	@GetMapping("/matricular/{id}")
    public String matricularEstudante(@PathVariable("id") long id, RedirectAttributes attributes, Model model) {	
		try {
			Estudante estudante = estudanteServico.buscarEstudantePorId(id);
			model.addAttribute("estudante", estudante);
			
			List<Curso> cursos = cursoServico.todosCursos();
			model.addAttribute("cursos", cursos);
			
			List<Curso> cursosEstudante = cursoServico.findAllCursosEstudanteMatriculado(id);
			model.addAttribute("cursosEstudante", cursosEstudante);
			
			return "/matricula";
		} catch (EstudanteNotFoundException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
        return "redirect:/";
    }
	
	@PostMapping("/criar-matricula/{id}")
	public String criarMatricula(@PathVariable("id") long id, 
								@RequestParam(value = "idCursos", required=false) long[] idsCursos) {
		try {
			Estudante estudante = estudanteServico.buscarEstudantePorId(id);
			List<Long> ids = new ArrayList<Long>();
			
			if (idsCursos != null) {
				// transforma em uma lista de longs
				ids = LongStream.of(idsCursos)
								.boxed()
								.collect(Collectors.toList());
			}
			List<Curso> cursos = cursoServico.todosCursosPorId(ids);
			matriculaServico.criarMatricula(estudante, cursos);
		} catch (EstudanteNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
}
