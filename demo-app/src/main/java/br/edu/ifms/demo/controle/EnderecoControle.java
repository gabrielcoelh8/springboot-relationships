package br.edu.ifms.demo.controle;

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

import br.edu.ifms.demo.excecao.EstudanteNotFoundException;
import br.edu.ifms.demo.modelo.Endereco;
import br.edu.ifms.demo.modelo.Estudante;
import br.edu.ifms.demo.servico.EnderecoServico;
import br.edu.ifms.demo.servico.EstudanteServico;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/endereco")
public class EnderecoControle {
	@Autowired
	private EstudanteServico estudanteServico;
	
	@Autowired
	private EnderecoServico enderecoServico;
	
	@GetMapping("/buscar-endereco/{id}")
	public String novoEndereco(@PathVariable("id") long id, Model model) {
		String pagina = "";
		try {
			Estudante estudante = estudanteServico.buscarEstudantePorId(id);
			if (estudante.getEndereco() == null) {
				Endereco endereco = new Endereco();
				endereco.setEstudante(estudante);
				model.addAttribute("item", endereco);
				pagina = "/novo-endereco";
			} else {
				model.addAttribute("item", estudante.getEndereco());
				pagina = "/alterar-endereco";
			}

		} catch (EstudanteNotFoundException e) {
			System.out.println(e.getMessage());
			return "redirect:/";
		}
		return pagina;
	}
	
	@PostMapping("/gravar-endereco/{idEstudante}")
	public String gravarEndereco(@PathVariable("idEstudante") long idEstudante,
			@ModelAttribute("item") @Valid Endereco endereco, BindingResult result, 
			RedirectAttributes attributes) {
		try {
			Estudante estudante = estudanteServico.buscarEstudantePorId(idEstudante);
			endereco.setEstudante(estudante);
		} catch (EstudanteNotFoundException e) {
			e.printStackTrace();
		}

		if (result.hasErrors()) {
			return "/novo-endereco";
		}
		
		enderecoServico.criarEndereco(endereco);
		attributes.addFlashAttribute("mensagem", "Gravado com sucesso");
		return "redirect:/";
	}

	@PostMapping("/alterar-endereco/{idEstudante}/{idEndereco}")
	public String alterarEndereco(@PathVariable("idEstudante") long idEstudante,
			@PathVariable("idEndereco") long idEndereco, @ModelAttribute("item") @Valid Endereco endereco,
			BindingResult result, RedirectAttributes attributes) {
		try {
			Estudante estudante = estudanteServico.buscarEstudantePorId(idEstudante);
			endereco.setEstudante(estudante);
			endereco.setId(idEndereco);			
		} catch (EstudanteNotFoundException e) {
			e.printStackTrace();
		}

		if (result.hasErrors()) {			
			return "/alterar-endereco";
		}
		enderecoServico.alterarEndereco(endereco);
		attributes.addFlashAttribute("mensagem", "Gravado com sucesso");
		return "redirect:/";
	}
}
