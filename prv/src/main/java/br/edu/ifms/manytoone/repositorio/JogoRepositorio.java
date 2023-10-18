package br.edu.ifms.manytoone.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifms.manytoone.modelo.Jogo;

public interface JogoRepositorio extends JpaRepository<Jogo, Long> {

}
