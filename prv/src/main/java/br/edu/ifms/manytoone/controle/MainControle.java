package br.edu.ifms.manytoone.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifms.manytoone.modelo.Estudio;
import br.edu.ifms.manytoone.modelo.Jogo;
import br.edu.ifms.manytoone.servico.EstudioServico;
import br.edu.ifms.manytoone.servico.JogoServico;
import jakarta.validation.Valid;

@Controller
public class MainControle {
	@Autowired
	private JogoServico jogoServico;
	
	@Autowired
	private EstudioServico estudioServico;
	
	@GetMapping("/")
	public String novoProduto(Model model) {
		Jogo jogo = new Jogo();
		model.addAttribute("jogo", jogo);
		
		List<Estudio> estudios = estudioServico.listar();
		model.addAttribute("estudios", estudios);
		
		return "index";
	}
	
	@GetMapping("/listar")
	public String index(Model model) {
		List<Jogo> jogos = jogoServico.listar();
		model.addAttribute("jogos", jogos);
		
		return "listar-jogos";
	}
	
	@PostMapping("/gravar")
	public String gravarJogo(@ModelAttribute("jogo") @Valid Jogo jogo, BindingResult erros, RedirectAttributes att) {
		if(erros.hasErrors()) {
			return "redirect:/";
		} 
		
		jogoServico.gravar(jogo);
		att.addFlashAttribute("mensagem", "Salvo com sucesso!");
		return "redirect:/listar";
	}
	
	@GetMapping("/apagar/{id}")
    public String apagarJogo(@PathVariable("id") long id, RedirectAttributes attributes) {	
		jogoServico.apagar(id);
		
        return "redirect:/listar";
    }
}
