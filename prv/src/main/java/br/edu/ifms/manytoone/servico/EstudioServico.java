package br.edu.ifms.manytoone.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifms.manytoone.modelo.Estudio;
import br.edu.ifms.manytoone.repositorio.EstudioRepositorio;

@Service
public class EstudioServico {
	@Autowired
	private EstudioRepositorio estudioRepositorio;
	
	public Estudio gravar(Estudio estudio) {
		return estudioRepositorio.save(estudio);
	}
	
	public List<Estudio> listar() {
		return estudioRepositorio.findAll();
	}
}
