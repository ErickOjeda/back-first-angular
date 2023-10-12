package app.controller;

import java.util.List;

import app.exception.Excecao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.dto.PessoaDTO;
import app.service.PessoaService;

@RestController
@RequestMapping("/api/pessoa")
@CrossOrigin(origins = "http://localhost:4200")
public class PessoaController {

	private static final String ERROR = "Error : ";

	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	private ResponseEntity<List<PessoaDTO>> listAll(){
		try {		
			List<PessoaDTO> lista = pessoaService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping
	private ResponseEntity<PessoaDTO> save(@RequestBody PessoaDTO pessoaDTO){
		try {		
			PessoaDTO pessoaSalva = pessoaService.save(pessoaDTO);
			return new ResponseEntity<>(pessoaSalva, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}") // Adjust the endpoint path to "/pessoa/{id}"
	private ResponseEntity<PessoaDTO> editPessoa(@PathVariable("id") final Long id, @RequestBody PessoaDTO pessoaDTO){
		try {
			PessoaDTO pessoaSalva = pessoaService.edit(id, pessoaDTO); // Call the appropriate service method for editing a Pessoa
			return new ResponseEntity<>(pessoaSalva, HttpStatus.OK);
		} catch (Exception e){
			throw new Excecao(ERROR + e.getMessage());
		}
	}
	
	@GetMapping("erro")
	private ResponseEntity<List<PessoaDTO>> exemploErro(){
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

}
