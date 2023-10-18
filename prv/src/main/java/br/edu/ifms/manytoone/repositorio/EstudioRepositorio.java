package br.edu.ifms.manytoone.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifms.manytoone.modelo.Estudio;


public interface EstudioRepositorio extends JpaRepository<Estudio, Long>{

}
