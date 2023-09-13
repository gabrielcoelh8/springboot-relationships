package br.edu.ifms.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifms.demo.modelo.Endereco;

public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {

}
