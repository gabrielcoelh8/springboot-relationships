package br.edu.ifms.demo.controle;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifms.demo.excecao.EstudanteNotFoundException;
import br.edu.ifms.demo.modelo.Estudante;
import br.edu.ifms.demo.servico.EstudanteServico;
import jakarta.validation.Valid;

@Controller
public class EstudanteControle {
	@Autowired
	private EstudanteServico estudanteServico;
	
	@GetMapping("")
	public String listarEstudantes(Model model) {
		List<Estudante> estudantes = estudanteServico.buscarTodosEstudantes();
		model.addAttribute("listaEstudantes", estudantes);
		return "/lista-estudantes";
	}
	
	@GetMapping("/novo")
	public String novoEstudante(Model model) {
		Estudante estudante = new Estudante();
		model.addAttribute("objetoEstudante", estudante);
		return "/novo-estudante";
	}
	
	@PostMapping("/gravar")
	public String gravarEstudante(@ModelAttribute("objetoEstudante") @Valid Estudante estudante,
			BindingResult erros) {
		
		if(erros.hasErrors()) {
			return "/novo-estudante";
		}
		
		estudanteServico.gravar(estudante);
		return "redirect:/";
	}
	
	@GetMapping("/apagar/{id}")
	public String apagarEstudante(@PathVariable("id") long id) {
		try {
			estudanteServico.apagarEstudante(id);
		} catch (EstudanteNotFoundException e) {
			
		}
		return "redirect:/";
	}
	
	@GetMapping("/editar/{id}")
	public String formEstudante(@PathVariable("id") long id, Model model) {
		try {
			Estudante estudante = estudanteServico.buscarEstudantePorId(id);
			model.addAttribute("objetoEstudante", estudante);
		} catch (EstudanteNotFoundException e) {
			//
			//
		}
		return "/editar-estudante";
	}
	
	@PostMapping("/editar/{id}")
	public String editarEstudante(
			@PathVariable("id") long id, 
			@ModelAttribute("objetoEstudante") 
			@Valid Estudante estudante,
			BindingResult erros) {
		
		if(erros.hasErrors()) {
			estudante.setId(id);
			return "/editar-estudante";
		}
		estudanteServico.editar(estudante);
		return "redirect:/";
	}
	
	@PostMapping("/buscar")
	public String buscarEstudantePorNome(@Param("nome") String nome, Model model) {
		if(nome == null) {
			return "redirect:/";
		}
		List<Estudante> estudantes = estudanteServico.buscarEstudantePorNome(nome);
		model.addAttribute("listaEstudantes", estudantes);
		return "/lista-estudantes";
	}
}
