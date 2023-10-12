package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import app.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dto.LivroDTO;
import app.entity.Livro;
import org.springframework.util.Assert;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<LivroDTO> listAll(){
        List<Livro> lista = livroRepository.findAll();
        List<LivroDTO> listaDTO = new ArrayList<>();

        for(int i=0; i<lista.size(); i++)
            listaDTO.add(this.toLivroDTO(lista.get(i)));

        return listaDTO;
    }

    public LivroDTO save(LivroDTO livroDTO){
        Livro livro = this.toLivro(livroDTO);

        Livro livrosalvo = livroRepository.save(livro);

        return this.toLivroDTO(livrosalvo);
    }

    public LivroDTO edit(Long id, LivroDTO livroDTO){

        Optional<Livro> livroBanco = livroRepository.findById(id);

        Assert.isTrue(livroBanco.isPresent(), "Registro não encontrado");
        Assert.isTrue(livroDTO.getId().equals(id), "Id fornecido na URL não condiz com o corpo da requisição");

        Livro livro = this.toLivro(livroDTO);
        Livro livrosalvo = livroRepository.save(livro);

        return this.toLivroDTO(livrosalvo);

    }

    private LivroDTO toLivroDTO(Livro livro) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(livro.getId());
        livroDTO.setNome(livro.getNome());
        livroDTO.setAutor(livro.getAutor());
        return livroDTO;
    }

    private Livro toLivro(LivroDTO livroDTO) {
        Livro livro = new Livro();
        livro.setId(livroDTO.getId());
        livro.setAutor(livroDTO.getAutor());
        livro.setNome(livroDTO.getNome());
        return livro;
    }
}
