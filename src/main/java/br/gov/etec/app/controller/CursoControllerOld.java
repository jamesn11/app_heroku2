package br.gov.etec.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.etec.app.dtos.CadastroCursoDtoOld;
import br.gov.etec.app.entity.CursoOld;
import br.gov.etec.app.repository.CursoReposityOld;
import br.gov.etec.app.response.Response;

@RestController
@RequestMapping("/api-curso")
public class CursoControllerOld {

	private Map<Integer, CursoOld> curso;
	
	private static final Logger log = LoggerFactory.getLogger(CursoControllerOld.class);
	
	@Autowired
	CursoReposityOld repository;

	@RequestMapping("/listacursos")
	ResponseEntity<List<CursoOld>> listaCurso() {
		log.info("Consulta de cursos");
		
		curso = new HashMap<Integer, CursoOld>();

		List<CursoOld> retorno = repository.findAll();
		
		for (CursoOld c : retorno) {
			
			curso.put(c.getId().intValue(), c);
		}
		
		
		
		return new ResponseEntity<List<CursoOld>>(new ArrayList<CursoOld>(curso.values()), HttpStatus.OK);
	}

	@PostMapping
	@RequestMapping(value = "/cadastrar")
	public ResponseEntity<Response> cadastrar(@Validated @RequestBody List<CadastroCursoDtoOld> listaCurso, BindingResult result){
	Response<CadastroCursoDtoOld> response  = new Response<CadastroCursoDtoOld>();
		for (CadastroCursoDtoOld cadastroCursoDto : listaCurso) {
			log.info("Cadastrando o curso : {}", cadastroCursoDto.toString());
			CursoOld curso = this.converterDtoParaCurso(cadastroCursoDto);
			repository.save(curso);
		}
		
		response.setData(listaCurso.get(0));
		return ResponseEntity.ok(response);
	}
	
	private CursoOld converterDtoParaCurso(CadastroCursoDtoOld cadastroCursoDto) {
		CursoOld curso = new CursoOld();
		curso.setNome(cadastroCursoDto.getNome());
		curso.setDescricao(cadastroCursoDto.getDescricao());
		return curso;
	}

}
