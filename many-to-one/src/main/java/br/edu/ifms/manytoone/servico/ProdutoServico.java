package br.edu.ifms.manytoone.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.manytoone.excecao.ProdutoNotFoundException;
import br.edu.ifms.manytoone.modelo.Categoria;
import br.edu.ifms.manytoone.modelo.Produto;
import br.edu.ifms.manytoone.repositorio.ProdutoRepositorio;

@Service
public class ProdutoServico {
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	public Produto gravar(Produto produto) {
		return produtoRepositorio.save(produto);
	}
	
	public List<Produto> listar() {
		return produtoRepositorio.findAll();
	}
	
	public Produto buscarProdutoPorId(Long id) throws ProdutoNotFoundException {
		Optional<Produto> opt = produtoRepositorio.findById(id);
		
		if (opt.isPresent()) {
			return opt.get();
		} 
		else {
			new ProdutoNotFoundException("Produto com id : " + id + " não existe");
		}
		
		return null;
	}
	
	public Produto alterar(Produto produto) {
		return produtoRepositorio.save(produto);
	}
	
	public void apagar(Long id) throws ProdutoNotFoundException {
		Produto produto = buscarProdutoPorId(id);
		produtoRepositorio.delete(produto);
	}


}
