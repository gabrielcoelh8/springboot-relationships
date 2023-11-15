package com.fourcatsdev.entitycrud.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fourcatsdev.entitycrud.modelo.Curso;

public interface CursoRepositorio extends JpaRepository<Curso, Long> {
	
	@Query(value = "SELECT curso.id, curso.nome "
			+ "	  FROM curso "
			+ "	JOIN matricula "
			+ "	  ON curso.id = matricula.curso_id "
			+ "	JOIN estudante "
			+ "	  ON matricula.estudante_id = estudante.id "
			+ "	  and estudante.id=:estudanteId",  nativeQuery = true)
	List<Curso> findAllCursosEstudanteMatriculado(Long estudanteId);
}
