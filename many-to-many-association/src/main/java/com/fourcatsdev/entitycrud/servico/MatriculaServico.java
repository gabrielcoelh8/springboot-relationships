package com.fourcatsdev.entitycrud.servico;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourcatsdev.entitycrud.modelo.Curso;
import com.fourcatsdev.entitycrud.modelo.Estudante;
import com.fourcatsdev.entitycrud.modelo.Matricula;
import com.fourcatsdev.entitycrud.repositorio.MatriculaRepositorio;

@Service
public class MatriculaServico {
	
	@Autowired
	private MatriculaRepositorio matriculaRepositorio;
	
	public void criarMatricula(Estudante estudante, List<Curso> cursos) {
		List<Matricula> matriculas = matriculaRepositorio.buscarMatriculaPorId(estudante.getId());
		apagarMatriculas(matriculas);
		
		for(Curso c: cursos) {
			Matricula matricula = new Matricula();
			matricula.setEstudante(estudante);
			matricula.setCurso(c);
			matricula.setData(LocalDateTime.now());
			matriculaRepositorio.save(matricula);
		}
	}
	
	public void apagarMatriculas(List<Matricula> matriculas) {
		matriculaRepositorio.deleteAll(matriculas);
	}
}
