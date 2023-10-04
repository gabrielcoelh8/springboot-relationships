package br.edu.ifms.manytoone.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.manytoone.modelo.Categoria;
import br.edu.ifms.manytoone.repositorio.CategoriaRepositorio;

@Service
public class CategoriaServico {
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	
	public Categoria gravar(Categoria categoria) {
		return categoriaRepositorio.save(categoria);
	}
	
	public List<Categoria> listar() {
		return categoriaRepositorio.findAll();
	}
}
