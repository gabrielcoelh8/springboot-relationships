package br.edu.ifms.manytoone.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProdutoControle {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
}
