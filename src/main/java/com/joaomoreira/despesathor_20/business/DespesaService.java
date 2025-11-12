package com.joaomoreira.despesathor_20.business;

import com.joaomoreira.despesathor_20.infrastructure.entitys.Categoria;
import com.joaomoreira.despesathor_20.infrastructure.entitys.Despesa;
import com.joaomoreira.despesathor_20.infrastructure.repository.DespesaRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DespesaService {

    private final DespesaRepository repository;

    public DespesaService(DespesaRepository repository) {
        this.repository = repository;
    }

    public void salvarDespesa(Despesa despesa){
        repository.saveAndFlush(despesa);
    }

    public Despesa buscarDespesaPorId(Long id){
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Despesa não encontrada!")
        );
    }

    public List<Despesa> pesquisarDespesas(
            Integer ano, Integer mes, Categoria categoria
    ) {
        return repository.pesquisar(ano, mes, categoria);
    }

    public ResumoDTO resumir(Integer ano, Integer mes, Categoria categoria){
        return repository.resumir(ano, mes, categoria);
    }

    public void deletarDespesaPorId(Long id){
        Despesa despesaParaDeletar = this.buscarDespesaPorId(id);
        repository.delete(despesaParaDeletar);
    }

    public void atualizarDespesaPorId(Long id, Despesa despesa){
        Despesa despesaEntity = buscarDespesaPorId(id);
        Despesa despesaAtualizada = Despesa.builder()
                .descricao(despesa.getDescricao() != null ? despesa.getDescricao() :
                        despesaEntity.getDescricao())
                .valor(despesa.getValor() != null ? despesa.getValor() :
                        despesaEntity.getValor())
                .categoria(despesa.getCategoria() != null ? despesa.getCategoria() :
                        despesaEntity.getCategoria())
                .data(despesa.getData() != null ? despesa.getData() :
                        despesaEntity.getData())
                .id(despesaEntity.getId())
                .build();
        repository.saveAndFlush(despesaAtualizada);
    }

    public List<Despesa> listarTodas(){
        return repository.findAll();
    }
}
