package br.edu.ifms.manytoone.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifms.manytoone.modelo.Categoria;
import br.edu.ifms.manytoone.servico.CategoriaServico;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/categoria")
public class CategoriaControle {
	
	@Autowired
	private CategoriaServico categoriaServico;
	
	@GetMapping("/novo")
	public String novaCategoria(Model model) {
		Categoria categoria = new Categoria();
		model.addAttribute("categoria", categoria);
		return "new-categoria";
	}
	
	@PostMapping("/salvar")
	public String gravarCategoria(@ModelAttribute("categoria") @Valid Categoria categoria, BindingResult erros, RedirectAttributes att) {
		if(erros.hasErrors()) {
			return "new-categoria";
		} 
		
		categoriaServico.gravar(categoria);
		att.addFlashAttribute("mensagem", "Salvo com sucesso!");
		return "redirect:/categoria/novo";
	}

}
