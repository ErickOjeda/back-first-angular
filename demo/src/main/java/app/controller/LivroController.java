package app.controller;

import java.util.List;

import app.exception.Excecao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.dto.LivroDTO;
import app.service.LivroService;

@RestController
@RequestMapping("/api/livro")
@CrossOrigin(origins = "http://localhost:4200, https://www.getpostman.com")
public class LivroController {

    private static final String ERROR = "Error : ";

    @Autowired
    private LivroService livroService;

    @GetMapping
    private ResponseEntity<List<LivroDTO>> listAll(){
        try {
            List<LivroDTO> lista = livroService.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e){
            throw new Excecao(ERROR + e.getMessage());
        }
    }

    @PostMapping
    private ResponseEntity<LivroDTO> save(@RequestBody LivroDTO livroDTO){
        try {
            LivroDTO livroSalvo = livroService.save(livroDTO);
            return new ResponseEntity<>(livroSalvo, HttpStatus.OK);
        } catch (Exception e){
            throw new Excecao(ERROR + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<LivroDTO> edit(@PathVariable("id") final Long id, @RequestBody LivroDTO livroDTO){
        try {
            LivroDTO livroSalvo = livroService.edit(id, livroDTO);
            return new ResponseEntity<>(livroSalvo, HttpStatus.OK);
        } catch (Exception e){
            throw new Excecao(ERROR + e.getMessage());
        }
    }

    @GetMapping("erro")
    private ResponseEntity<List<LivroDTO>> exemploErro(){
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
