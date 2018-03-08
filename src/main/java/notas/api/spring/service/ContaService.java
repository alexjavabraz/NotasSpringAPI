package notas.api.spring.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import notas.api.domain.Conta;
import notas.api.spring.repo.ContaRepository;

@Service
public class ContaService {
	
	
	@Autowired
	private ContaRepository contaRepo;
	
	public Conta consultarPorId(Long id) {
		return contaRepo.findById(id);
	}
	
	@Transactional
	public Conta salvar(Conta c) {
		return contaRepo.save(c);
	}

}
