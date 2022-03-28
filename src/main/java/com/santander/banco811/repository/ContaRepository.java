package com.santander.banco811.repository;

import com.santander.banco811.model.Conta;
import com.santander.banco811.model.TipoConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {
    List<Conta> findBySaldoLessThan(BigDecimal saldo);
    List<Conta> findBySaldoLessThanEqual(BigDecimal saldo);
    List<Conta> findBySaldoGreaterThan(BigDecimal saldo);
    List<Conta> findBySaldoBetween(BigDecimal saldoInicial, BigDecimal saldoFinal);
    List<Conta> findBySaldoIn(List<BigDecimal> saldos);

    List<Conta> findByUsuario_cpf(String cpf);

    Boolean existsByTipoConta(TipoConta tipoConta);

    @Query("select c from Conta c " +
            "where (c.tipoConta = :tipoConta and c.usuario.cpf = :cpf) " +
            "or (c.tipoConta = :tipoConta or c.saldo = :saldo)")
    List<Conta> findByTipoContaAndAgenciaOrTipoContaAndSaldo(
            @Param("tipoConta") TipoConta tipoConta,
            @Param("cpf") String cpf,
            @Param("saldo") BigDecimal saldo
    );

//    @Query(value = "select * from Conta c " +
//            "where (c.tipo_conta = :tipoConta AND " +
//            "c.data_criacao >= :dataCriaca) " +
//            "OR c.saldo = :saldo", nativeQuery = true)
//    List<Conta> findByDataCriacaoAndTipoContaOrSaldo(
//            @Param("dataCriacao") LocalDateTime dataCriacao,
//            @Param("tipoConta") LocalDateTime tipoConta,
//            @Param("saldo") BigDecimal saldo
//            );
}
