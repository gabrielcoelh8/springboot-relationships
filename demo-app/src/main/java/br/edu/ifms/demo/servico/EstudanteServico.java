package br.edu.ifms.demo.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.demo.excecao.EstudanteNotFoundException;
import br.edu.ifms.demo.modelo.Estudante;
import br.edu.ifms.demo.repositorio.EstudanteRepositorio;

@Service
public class EstudanteServico {
	@Autowired
	private EstudanteRepositorio estudanteRepositorio;
	
	public Estudante gravar(Estudante estudante) {
		return estudanteRepositorio.save(estudante);
	}
	
	public List<Estudante> buscarTodosEstudantes() {
		return estudanteRepositorio.findAll();
	}
	
	public Estudante buscarEstudantePorId(long id) throws EstudanteNotFoundException {
		Optional<Estudante> opt = estudanteRepositorio.findById(id);
		
		if(opt.isPresent()) {
			return opt.get();
		} else {
			throw new EstudanteNotFoundException("Estudante com id " + id + " não existe.");
		}
	}
	
	public void apagarEstudante(long id) throws EstudanteNotFoundException {
		Estudante estudante = buscarEstudantePorId(id);
		estudanteRepositorio.delete(estudante);
	}
	
	public Estudante editar(Estudante estudante) {
		return estudanteRepositorio.save(estudante);
	}
	
	public List<Estudante> buscarEstudantePorNome(String nome) {
		return estudanteRepositorio.findByNomeContainingIgnoreCase(nome);
	}
}
