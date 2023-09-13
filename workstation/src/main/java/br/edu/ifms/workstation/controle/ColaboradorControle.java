package br.edu.ifms.workstation.controle;

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

import br.edu.ifms.workstation.modelo.Colaborador;
import br.edu.ifms.workstation.servico.ColaboradorServico;
import jakarta.validation.Valid;

@Controller
public class ColaboradorControle {
	@Autowired
	private ColaboradorServico colaboradorServico;
	
	@GetMapping("")
	public String listarColaboradores(Model model) {
		List<Colaborador> colaboradores = colaboradorServico.buscarTodosColaboradores();
		model.addAttribute("listaColaboradores", colaboradores);
		return "/lista-colaboradores";
	}
	
	@GetMapping("/novo")
	public String novoColaborador(Model model) {
		Colaborador colaborador = new Colaborador();
		model.addAttribute("objetoColaborador", colaborador);
		return "/novo-colaborador";
	}
	
	@PostMapping("/gravar")
	public String gravarColaborador(@ModelAttribute("objetoColaborador") @Valid Colaborador colaborador,
			BindingResult erros) {
		
		if(erros.hasErrors()) {
			return "/novo-colaborador";
		}
		
		colaboradorServico.gravar(colaborador);
		return "redirect:/";
	}
	
	@GetMapping("/apagar/{id}")
	public String apagarColaborador(@PathVariable("id") long id) {
		//try {
		
		colaboradorServico.apagarColaborador(id);
		
		//} catch (EstudanteNotFoundException e) {
		//}
		return "redirect:/";
	}
	
	@GetMapping("/editar/{id}")
	public String formColaborador(@PathVariable("id") long id, Model model) {
		//try {
			Colaborador colaborador = colaboradorServico.buscarColaboradorPorId(id);
			model.addAttribute("objetoColaborador", colaborador);
		//} catch (EstudanteNotFoundException e) {
			//
			//
		//}
		return "/editar-colaborador";
	}
	
	@PostMapping("/editar/{id}")
	public String editarColaborador(
			@PathVariable("id") long id, 
			@ModelAttribute("objetoColaborador") 
			@Valid Colaborador colaborador,
			BindingResult erros) {
		
		if(erros.hasErrors()) {
			colaborador.setId(id);
			return "/editar-colaborador";
		}
		colaboradorServico.editar(colaborador);
		return "redirect:/";
	}
	
	@PostMapping("/buscar")
	public String buscarColaboradorPorNome(@Param("nome") String nome, Model model) {
		if(nome == null) {
			return "redirect:/";
		}
		List<Colaborador> colaboradores = colaboradorServico.buscarColaboradorPorNome(nome);
		model.addAttribute("listaColaboradores", colaboradores);
		return "/lista-colaboradores";
	}
}
