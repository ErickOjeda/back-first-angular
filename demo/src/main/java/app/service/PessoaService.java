package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import app.entity.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dto.PessoaDTO;
import app.entity.Pessoa;
import app.repository.PessoaRepository;
import org.springframework.util.Assert;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<PessoaDTO> listAll(){
		List<Pessoa> lista = pessoaRepository.findAll();
		List<PessoaDTO> listaDTO = new ArrayList<>();

		for(int i=0; i<lista.size(); i++) 
			listaDTO.add(this.toPessoaDTO(lista.get(i)));

		return listaDTO;
	}
	
	public PessoaDTO save(PessoaDTO pessoaDTO){
		Pessoa pessoa = this.toPessoa(pessoaDTO);

		Pessoa pessoasalva = pessoaRepository.save(pessoa);

		return this.toPessoaDTO(pessoasalva);
	}


	public PessoaDTO edit(Long id, PessoaDTO pessoaDTO) {
		Optional<Pessoa> pessoaBanco = pessoaRepository.findById(id);

		Assert.isTrue(pessoaBanco.isPresent(), "Registro não encontrado");
		Assert.isTrue(pessoaDTO.getId().equals(id), "Id fornecido na URL não condiz com o corpo da requisição");

		Pessoa pessoa = this.toPessoa(pessoaDTO); // Implement a conversion method if necessary
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);

		return this.toPessoaDTO(pessoaSalva); // Implement a conversion method if necessary
	}

	public void delete(Long id){

		final Pessoa pessoaBanco = pessoaRepository.findById(id).orElse(null);

		Assert.isTrue(pessoaBanco != null, "Registro não encontrado");

		pessoaRepository.delete(pessoaBanco);

	}

	private PessoaDTO toPessoaDTO(Pessoa pessoa) {
		PessoaDTO pessoaDTO = new PessoaDTO();
		pessoaDTO.setId(pessoa.getId());
		pessoaDTO.setNome(pessoa.getNome());
		pessoaDTO.setIdade(pessoa.getIdade());
		return pessoaDTO;
	}
	
	private Pessoa toPessoa(PessoaDTO pessoaDTO) {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(pessoaDTO.getId());
		pessoa.setNome(pessoaDTO.getNome());
		pessoa.setIdade(pessoaDTO.getIdade());
		return pessoa;
	}

}
