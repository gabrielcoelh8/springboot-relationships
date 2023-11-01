package com.fourcatsdev.entitycrud.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourcatsdev.entitycrud.modelo.Curso;
import com.fourcatsdev.entitycrud.repositorio.CursoRepositorio;

@Service
public class CursoServico {
	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	public Curso criarEstudante(Curso curso) {
		return cursoRepositorio.save(curso);
	}
}
