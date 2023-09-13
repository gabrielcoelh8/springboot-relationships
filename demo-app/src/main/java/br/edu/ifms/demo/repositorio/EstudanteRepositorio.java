package br.edu.ifms.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifms.demo.modelo.Estudante;

public interface EstudanteRepositorio extends JpaRepository<Estudante, Long> {
	List<Estudante> findByNomeContainingIgnoreCase(String nome);
}