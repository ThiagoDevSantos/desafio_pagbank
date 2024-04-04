package nexmuv.atividade_5.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nexmuv.atividade_5.model.Veiculo;
import nexmuv.atividade_5.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
@Api(tags = "Veiculo Controller", description = "API para operações relacionadas a veículos")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;


    @PostMapping
    @ApiOperation("Cadastra um novo veículo")
    public ResponseEntity<?> cadastrarVeiculo(@RequestBody Veiculo veiculo) {
        try {
            Veiculo novoVeiculo = veiculoService.cadastrarVeiculo(veiculo);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
        } catch (IllegalArgumentException e) {
            String mensagemErro = "Erro ao cadastrar veículo: " + e.getMessage();
            return ResponseEntity.badRequest().body(mensagemErro);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualiza um veículo existente")
    public ResponseEntity<?> atualizarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        Veiculo veiculoAtualizado;
        try {
            veiculoAtualizado = veiculoService.atualizarVeiculo(id, veiculo);
        } catch (IllegalArgumentException e) {
            String mensagemErro = "Erro ao atualizar veículo: " + e.getMessage();
            return ResponseEntity.badRequest().body(mensagemErro);
        }

        return ResponseEntity.ok(veiculoAtualizado);
    }


    @DeleteMapping("/{id}")
    @ApiOperation("Exclui um veículo existente")
    public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id) {
        veiculoService.excluirVeiculo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @ApiOperation("Filtra veículos de acordo com os parâmetros especificados")
    public ResponseEntity<List<Veiculo>> filtrarVeiculos(
            @RequestParam(required = false) Boolean naoVendidos,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) String fabricante,
            @RequestParam(required = false) String cor,
            @RequestParam(required = false) Boolean ultimaSemana) {
        List<Veiculo> veiculosFiltrados = veiculoService.filtrarVeiculos(naoVendidos, ano, fabricante, cor, ultimaSemana);
        return ResponseEntity.ok(veiculosFiltrados);
    }

    @GetMapping("/{id}")
    @ApiOperation("Busca um veículo pelo seu ID")
    public ResponseEntity<Veiculo> buscarVeiculoPorId(@PathVariable Long id) {
        Veiculo veiculo = veiculoService.buscarVeiculoPorId(id);
        if (veiculo != null) {
            return ResponseEntity.ok(veiculo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @ApiOperation("Atualiza parcialmente um veículo existente")
    public ResponseEntity<?> atualizarParcialmenteVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculoPatch) {
        Veiculo veiculoAtualizado;
        try {
            veiculoAtualizado = veiculoService.atualizarParcialmenteVeiculo(id, veiculoPatch);
        } catch (IllegalArgumentException e) {
            String mensagemErro = "Erro ao atualizar veículo: " + e.getMessage();
            return ResponseEntity.badRequest().body(mensagemErro);
        }

        return ResponseEntity.ok(veiculoAtualizado);
    }
}
