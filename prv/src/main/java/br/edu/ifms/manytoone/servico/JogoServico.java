package br.edu.ifms.manytoone.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.manytoone.modelo.Jogo;
import br.edu.ifms.manytoone.repositorio.JogoRepositorio;

@Service
public class JogoServico {
	@Autowired
	private JogoRepositorio jogoRepositorio;
	
	public Jogo gravar(Jogo jogo) {
		return jogoRepositorio.save(jogo);
	}
	
	public List<Jogo> listar() {
		return jogoRepositorio.findAll();
	}
	
	public Jogo buscarJogoPorId(Long id) {
		Optional<Jogo> opt = jogoRepositorio.findById(id);
		
		if (opt.isPresent()) {
			return opt.get();
		} 

		return null;
	}
	
	public void apagar(Long id) {
		Jogo produto = buscarJogoPorId(id);
		jogoRepositorio.delete(produto);
	}
}
