package com.fourcatsdev.entitycrud.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourcatsdev.entitycrud.modelo.Matricula;

public interface MatriculaRepositorio extends JpaRepository<Matricula, Long> {

}
