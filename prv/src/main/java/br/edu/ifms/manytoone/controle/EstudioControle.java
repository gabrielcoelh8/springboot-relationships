package br.edu.ifms.manytoone.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifms.manytoone.modelo.Estudio;
import br.edu.ifms.manytoone.modelo.Jogo;
import br.edu.ifms.manytoone.servico.EstudioServico;
import br.edu.ifms.manytoone.servico.JogoServico;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/estudio")
public class EstudioControle {
	@Autowired
	private JogoServico jogoServico;
	
	@Autowired
	private EstudioServico estudioServico;
	
	@GetMapping("/gravar/{id}")
    public String gravarEstudio(@PathVariable("id") long id, RedirectAttributes attributes,
    		Model model) {
		Jogo jogo = jogoServico.buscarJogoPorId(id);
		model.addAttribute("objetoJogo", jogo);
		
		Estudio estudio = new Estudio();
		model.addAttribute("estudio", estudio);
		
		return "/cadastro-estudio";
    }
	
	@PostMapping("/gravar/{id}")
	public String editarJogo(
			@PathVariable("id") long id,
			@ModelAttribute("estudio") 
			@Valid Estudio estudio, 
			BindingResult erros) {
		
		if (erros.hasErrors()) {
	        return "/cadastro-estudio";
	    }
		
		estudioServico.gravar(estudio);
		Jogo jogo = jogoServico.buscarJogoPorId(id);
		jogo.setEstudio(estudio);
		
		return "redirect:/listar";
	}
}
