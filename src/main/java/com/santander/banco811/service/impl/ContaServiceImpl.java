package com.santander.banco811.service.impl;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.model.Conta;
import com.santander.banco811.repository.ContaRepository;
import com.santander.banco811.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    ContaRepository contaRepository;

    @Override
    public List<Conta> getAll(Integer numero) {

        if (numero != null) {
            return contaRepository.findByNumero(numero);
        } else {
            return contaRepository.findAll();
        }
    }

    @Override
    public ContaResponse create(ContaRequest contaRequest) {
        Conta conta = new Conta(contaRequest);
        contaRepository.save(conta);

        return new ContaResponse(conta);
    }

    @Override
    public Conta getById(Integer id) {
        return contaRepository.findById(id).orElseThrow();
    }

    @Override
    public Conta update(ContaRequest contaRequest, Integer id) {
        Conta conta = contaRepository.findById(id).orElseThrow();

        conta.setNumero(contaRequest.getNumero());
        conta.setAgencia(contaRequest.getAgencia());
        conta.setTipoConta(contaRequest.getTipoConta());
        conta.setUsuario(contaRequest.getUsuario());

        return contaRepository.save(conta);
    }

    @Override
    public void delete(Integer id) {
        var conta = contaRepository.findById(id).orElseThrow();

        contaRepository.delete(conta);
    }
}
