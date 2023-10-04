package br.edu.ifms.manytoone.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.ifms.manytoone.modelo.Produto;
import br.edu.ifms.manytoone.servico.ProdutoServico;

@Controller
public class MainControle {
	@Autowired
	private ProdutoServico produtoServico;
	
	@GetMapping("/")
	public String index(Model model) {
		List<Produto> produtos = produtoServico.listar();
		model.addAttribute("produtos", produtos);
		
		return "index";
	}
}
