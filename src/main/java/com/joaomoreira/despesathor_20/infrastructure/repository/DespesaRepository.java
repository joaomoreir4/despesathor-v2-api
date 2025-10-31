package com.joaomoreira.despesathor_20.infrastructure.repository;

import com.joaomoreira.despesathor_20.infrastructure.entitys.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}
