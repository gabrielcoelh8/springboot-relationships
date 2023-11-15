package com.fourcatsdev.entitycrud.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fourcatsdev.entitycrud.modelo.Matricula;

public interface MatriculaRepositorio extends JpaRepository<Matricula, Long> {
	
	@Query("select m from Matricula m where m.estudante.id=:idEstudante")
	public List<Matricula> buscarMatriculaPorId(Long idEstudante);
}
