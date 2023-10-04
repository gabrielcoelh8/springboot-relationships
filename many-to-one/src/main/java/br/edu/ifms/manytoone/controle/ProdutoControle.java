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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifms.manytoone.excecao.ProdutoNotFoundException;
import br.edu.ifms.manytoone.modelo.Categoria;
import br.edu.ifms.manytoone.modelo.Produto;
import br.edu.ifms.manytoone.servico.CategoriaServico;
import br.edu.ifms.manytoone.servico.ProdutoServico;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/produto")
public class ProdutoControle {
	@Autowired
	private ProdutoServico produtoServico;
	
	@Autowired
	private CategoriaServico categoriaServico;
	
	@GetMapping("/novo")
	public String novoProduto(Model model) {
		Produto produto = new Produto();
		model.addAttribute("produto", produto);
		
		List<Categoria> categorias = categoriaServico.listar();
		model.addAttribute("categorias", categorias);
		
		return "new-produto";
	}
	
	@PostMapping("/salvar")
	public String gravarProduto(@ModelAttribute("produto") @Valid Produto produto, BindingResult erros, RedirectAttributes att) {
		if(erros.hasErrors()) {
			return "new-produto";
		} 
		
		produtoServico.gravar(produto);
		att.addFlashAttribute("mensagem", "Salvo com sucesso!");
		return "redirect:/produto/novo";
	}
	
	@GetMapping("/editar/{id}")
    public String editarForm(@PathVariable("id") long id, RedirectAttributes attributes,
    		Model model) {	
		try {
			Produto produto = produtoServico.buscarProdutoPorId(id);
			model.addAttribute("objetoProduto", produto);
			List<Categoria> categorias = categoriaServico.listar();
			model.addAttribute("categorias", categorias);
			
			return "/edit-produto";
		} catch (ProdutoNotFoundException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
        return "redirect:/";
    }
	
	@PostMapping("/editar/{id}")
	public String editarProduto(
			@PathVariable("id") long id, 
			@ModelAttribute("objetoProduto") 
			@Valid Produto produto, 
			BindingResult erros) {
		
		if (erros.hasErrors()) {
			produto.setId(id);
	        return "/edit-produto";
	    }
		
		produtoServico.alterar(produto);
		return "redirect:/";
	}
	
	@GetMapping("/apagar/{id}")
    public String apagarProduto(@PathVariable("id") long id, RedirectAttributes attributes) {	
		try {
			produtoServico.apagar(id);
		} catch (ProdutoNotFoundException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
        return "redirect:/";
    }

}
