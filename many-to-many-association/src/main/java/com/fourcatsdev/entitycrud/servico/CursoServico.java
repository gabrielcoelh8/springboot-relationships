package com.fourcatsdev.entitycrud.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourcatsdev.entitycrud.modelo.Curso;
import com.fourcatsdev.entitycrud.repositorio.CursoRepositorio;

@Service
public class CursoServico {
	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	public Curso salvar(Curso curso) {
		return cursoRepositorio.save(curso);
	}
	
	public List<Curso> todosCursos() {
		return cursoRepositorio.findAll();
	}
	
	public List<Curso> todosCursosPorId(Iterable<Long> ids) {
		return cursoRepositorio.findAllById(ids);
	}
	
	public List<Curso> findAllCursosEstudanteMatriculado(Long estudanteId) {
		return cursoRepositorio.findAllCursosEstudanteMatriculado(estudanteId);
	}
}
