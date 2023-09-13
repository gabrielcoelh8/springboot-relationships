package br.edu.ifms.workstation.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.workstation.modelo.Colaborador;
import br.edu.ifms.workstation.repositorio.ColaboradorRepositorio;

@Service
public class ColaboradorServico {
	@Autowired
	private ColaboradorRepositorio colaboradorRep;
	
	public Colaborador gravar(Colaborador colaborador) {
		return colaboradorRep.save(colaborador);
	}
	
	public List<Colaborador> buscarTodosColaboradores() {
		return colaboradorRep.findAll();
	}
	
	public Colaborador buscarColaboradorPorId(long id)  {
		Optional<Colaborador> opt = colaboradorRep.findById(id);
		return opt.get();
		
		/*if(opt.isPresent()) {
			return opt.get();
		} else {
			throw new EstudanteNotFoundException("Estudante com id " + id + " não existe.");
		}*/
	}
	
	public void apagarColaborador(long id) {
		Colaborador colaborador = buscarColaboradorPorId(id);
		colaboradorRep.delete(colaborador);
	}
	
	public Colaborador editar(Colaborador colaborador) {
		return colaboradorRep.save(colaborador);
	}
	
	public List<Colaborador> buscarColaboradorPorNome(String nome) {
		return colaboradorRep.findByNomeContainingIgnoreCase(nome);
	}
}
