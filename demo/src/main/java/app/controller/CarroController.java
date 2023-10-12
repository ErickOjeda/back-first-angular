package app.controller;

import java.util.List;

import app.dto.CarroDTO;
import app.exception.Excecao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.dto.CarroDTO;
import app.service.CarroService;

@RestController
@RequestMapping("/api/carro")
@CrossOrigin(origins = "http://localhost:4200")
public class CarroController {

    private static final String ERROR = "Error : ";

    @Autowired
    private CarroService carroService;

    @GetMapping
    private ResponseEntity<List<CarroDTO>> listAll(){
        try {
            List<CarroDTO> lista = carroService.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e){
            throw new Excecao(ERROR + e.getMessage());
        }
    }

    @PostMapping
    private ResponseEntity<CarroDTO> save(@RequestBody CarroDTO carroDTO){
        try {
            CarroDTO carroSalvo = carroService.save(carroDTO);
            return new ResponseEntity<>(carroSalvo, HttpStatus.OK);
        } catch (Exception e){
            throw new Excecao(ERROR + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<CarroDTO> edit(@PathVariable("id") final Long id, @RequestBody CarroDTO carroDTO){
        try {
            CarroDTO livroSalvo = carroService.edit(id, carroDTO);
            return new ResponseEntity<>(livroSalvo, HttpStatus.OK);
        } catch (Exception e){
            throw new Excecao(ERROR + e.getMessage());
        }
    }

    @GetMapping("erro")
    private ResponseEntity<List<CarroDTO>> exemploErro(){
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
