package br.edu.ifms.manytoone.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifms.manytoone.modelo.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
	
}
