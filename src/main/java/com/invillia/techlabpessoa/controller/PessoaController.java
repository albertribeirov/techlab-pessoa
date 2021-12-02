package com.invillia.techlabpessoa.controller;

import com.invillia.techlabpessoa.model.Pessoa;
import com.invillia.techlabpessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
@CrossOrigin("localhost:3000")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> getPessoa() {
        return pessoaService.findAll();
    }

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoaForm) {
        Pessoa user = pessoaService.save(pessoaForm);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<Pessoa> updatePessoa(@PathParam("id") Long id, @RequestBody Pessoa pessoaForm) {
        Optional<Pessoa> userFromDb = pessoaService.findById(id);

        if (userFromDb.isPresent()) {
            Pessoa pessoa = pessoaService.save(userFromDb.get());
            return ResponseEntity.ok(pessoa);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Object> deletePessoa(@PathParam("id") Long id) {
        Optional<Pessoa> userFromDb = pessoaService.findById(id);

        if (userFromDb.isPresent()) {
            pessoaService.delete(userFromDb.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
