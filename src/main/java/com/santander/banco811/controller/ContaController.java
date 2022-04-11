package com.santander.banco811.controller;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.model.Conta;
import com.santander.banco811.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    ContaService contaService;

    @GetMapping
    public List<Conta> getAll(@RequestParam(required = false) Integer numero) {
        return contaService.getAll(numero);
    }

    @PostMapping
    public ContaResponse create(@RequestBody ContaRequest contaRequest) {
        return contaService.create(contaRequest);
    }

    @GetMapping("/{id}")
    public Conta getById(@PathVariable Integer id) {
        return contaService.getById(id);
    }

    @PutMapping("/{id}")
    public Conta update(@PathVariable Integer id, @RequestBody ContaRequest contaRequest) {
        return contaService.update(contaRequest, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        contaService.delete(id);
    }
}
