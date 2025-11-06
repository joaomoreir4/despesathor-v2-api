package com.joaomoreira.despesathor_20.controller;

import com.joaomoreira.despesathor_20.business.DespesaService;
import com.joaomoreira.despesathor_20.infrastructure.entitys.Despesa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/despesas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DespesaController {

    private final DespesaService despesaService;

    @PostMapping
    public ResponseEntity<Void> salvarDespesa(@RequestBody Despesa despesa){
        despesaService.salvarDespesa(despesa);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Despesa>> listarTodasAsDespesas(){
        List<Despesa> todasAsDespesas = despesaService.listarTodas();
        return ResponseEntity.ok(todasAsDespesas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDespesaPorId(@PathVariable Long id){
        despesaService.deletarDespesaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarDespesaPorId(@PathVariable Long id,
                                                      @RequestBody Despesa despesa){
        despesaService.atualizarDespesaPorId(id, despesa);
        return ResponseEntity.ok().build();
    }
}
