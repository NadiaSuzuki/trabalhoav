package com.gestaofinanceirapessoal.resources;

import com.gestaofinanceirapessoal.domains.MovimentoFinanceiro;
import com.gestaofinanceirapessoal.domains.dtos.MovimentoFinanceiroDTO;
import com.gestaofinanceirapessoal.services.MovimentoFinanceiroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/movimentos")
public class MovimentoFinanceiroResource {

    @Autowired
    private MovimentoFinanceiroService movimentoService;

    @GetMapping
    public ResponseEntity<List<MovimentoFinanceiroDTO>> findAll() {
        return ResponseEntity.ok().body(movimentoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovimentoFinanceiroDTO> findById(@PathVariable Long id) {
        MovimentoFinanceiro obj = movimentoService.findById(id);
        return ResponseEntity.ok().body(new MovimentoFinanceiroDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody MovimentoFinanceiroDTO objDto) {
        MovimentoFinanceiro movimento = movimentoService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(movimento.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MovimentoFinanceiroDTO> update(@PathVariable Long id, @Valid @RequestBody MovimentoFinanceiroDTO objDto) {
        MovimentoFinanceiro obj = movimentoService.update(id, objDto);
        return ResponseEntity.ok().body(new MovimentoFinanceiroDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movimentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/periodo")
    public ResponseEntity<List<MovimentoFinanceiroDTO>> findByPeriodo(
            @RequestParam(value = "inicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date inicio,
            @RequestParam(value = "fim") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fim) {
        List<MovimentoFinanceiroDTO> list = movimentoService.findByPeriodo(inicio, fim);
        return ResponseEntity.ok().body(list);
    }
}
