package com.fourcatsdev.entitycrud.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourcatsdev.entitycrud.modelo.Curso;

public interface CursoRepositorio extends JpaRepository<Curso, Long> {

}
