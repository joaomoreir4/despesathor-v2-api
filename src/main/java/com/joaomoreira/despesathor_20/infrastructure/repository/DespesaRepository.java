package com.joaomoreira.despesathor_20.infrastructure.repository;
import com.joaomoreira.despesathor_20.infrastructure.entitys.Categoria;
import com.joaomoreira.despesathor_20.infrastructure.entitys.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    @Query("SELECT d FROM Despesa d WHERE " +
            "(:ano IS NULL OR YEAR(d.data) = :ano) AND " +
            "(:mes IS NULL OR MONTH(d.data) = :mes) AND " +
            "(:categoria IS NULL OR d.categoria = :categoria)"
    )
    List<Despesa> pesquisar(
            @Param("ano") Integer ano,
            @Param("mes") Integer mes,
            @Param("categoria") Categoria categoria
    );
}
