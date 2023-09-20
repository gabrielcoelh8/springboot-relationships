package br.edu.ifms.manytoone.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifms.manytoone.modelo.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

}
