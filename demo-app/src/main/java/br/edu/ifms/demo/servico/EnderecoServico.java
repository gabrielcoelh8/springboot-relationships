package br.edu.ifms.demo.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.demo.modelo.Endereco;
import br.edu.ifms.demo.repositorio.EnderecoRepositorio;

@Service
public class EnderecoServico {
	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	
	public Endereco criarEndereco(Endereco endereco) {
		return enderecoRepositorio.save(endereco);
	}
	
	public Endereco alterarEndereco(Endereco endereco) {
		return enderecoRepositorio.save(endereco);
	}
}
