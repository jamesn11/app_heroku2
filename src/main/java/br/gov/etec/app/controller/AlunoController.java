package br.gov.etec.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.etec.app.dtos.AlunoDto;
import br.gov.etec.app.entity.Aluno;
import br.gov.etec.app.repository.AlunoRepository;

@RestController
public class AlunoController {

	@Autowired
	private AlunoRepository repository;
	
	@PostMapping
	@RequestMapping("/cadastroAluno")
	public void cadastrarAluno(@RequestBody AlunoDto dto) {		
		Aluno aluno = this.transformarDtoEntity(dto);		
		repository.save(aluno);
	}
	/// 
	//@PostMapping
	//@RequestMapping("/logar")
	//public void alunoLogin(@RequestBody AlunoDto alunodto) {		
	//	return repository.findByEmailAndSenha(alunodto.getEmail(), alunodto.getSenha());
	//}
	///

	@PostMapping
	@RequestMapping("/logar")
	public Aluno alunoLogin(@RequestBody AlunoDto alunodto) {		
		return repository.findByEmailAndSenha(alunodto.getEmail(), alunodto.getSenha());
	}
	
	//Mapping
	private Aluno transformarDtoEntity(AlunoDto dto) {
		Aluno a = new Aluno();
		a.setNome(dto.getNome());
		a.setRg(dto.getRg());
		a.setCpf(dto.getCpf());
		a.setEmail(dto.getEmail());
		a.setId_curso(dto.getId_curso());
		a.setData_nasc(dto.getdata_nasc());
		a.setSenha(dto.getSenha());
		return a;
	}
}