package com.joaomoreira.despesathor_20.controller;

import com.joaomoreira.despesathor_20.business.DespesaService;
import com.joaomoreira.despesathor_20.infrastructure.entitys.Despesa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/despesas")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService despesaService;

    @PostMapping
    public ResponseEntity<Void> salvarDespesa(@RequestBody Despesa despesa){
        despesaService.salvarDespesa(despesa);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Despesa> buscarDespesaPorId(@PathVariable Long id){
        return ResponseEntity.ok(despesaService.buscarDespesaPorId(id));
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
