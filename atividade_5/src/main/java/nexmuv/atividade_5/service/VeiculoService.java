package nexmuv.atividade_5.service;

import lombok.AllArgsConstructor;
import nexmuv.atividade_5.model.Veiculo;
import nexmuv.atividade_5.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    private static final Set<String> MARCAS_VALIDAS = Set.of("Toyota", "Honda", "Ford", "Chevrolet", "Fiat","Jeep", "Mitsubishi ");


    public Veiculo cadastrarVeiculo(Veiculo veiculo) {
        if (!marcaValida(veiculo.getMarca())) {
            throw new IllegalArgumentException("Marca de veículo inválida: " + veiculo.getMarca());
        }
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizarVeiculo(Long id, Veiculo veiculo) {
        if (!marcaValida(veiculo.getMarca())) {
            throw new IllegalArgumentException("Marca de veículo inválida: " + veiculo.getMarca());
        }
        if (veiculoRepository.existsById(id)) {
            veiculo.setId(id);
            return veiculoRepository.save(veiculo);
        }
        return null;
    }

    public void excluirVeiculo(Long id) {
        veiculoRepository.deleteById(id);
    }

    public Veiculo buscarVeiculoPorId(Long id) {
        return veiculoRepository.findById(id).orElse(null);
    }


    public List<Veiculo> filtrarVeiculos(Boolean naoVendidos, Integer ano, String fabricante, String cor, Boolean ultimaSemana) {
        return veiculoRepository.findAll()
                .stream()
                .filter(veiculo ->
                        (naoVendidos == null || (!naoVendidos || !veiculo.isVendido())) &&
                                (ano == null || veiculo.getAnoFabricacao() == ano) &&
                                (fabricante == null || veiculo.getMarca().equalsIgnoreCase(fabricante)) &&
                                (cor == null || veiculo.getCor().equalsIgnoreCase(cor)) &&
                                (ultimaSemana == null || (!ultimaSemana || veiculo.getDataCadastro().isAfter(LocalDate.now().minusWeeks(1))))
                )
                .collect(Collectors.toList());
    }

    public Veiculo atualizarParcialmenteVeiculo(Long id, Veiculo veiculoPatch) {
        Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);
        return optionalVeiculo.map(veiculo -> {
            if (!marcaValida(veiculoPatch.getMarca())) {
                throw new IllegalArgumentException("Marca de veículo inválida: " + veiculoPatch.getMarca());
            }
            atualizarCampoSeNaoNulo(veiculoPatch.getMarca(), veiculo::setMarca);
            atualizarCampoSeNaoNulo(veiculoPatch.getVeiculo(), veiculo::setVeiculo);
            atualizarCampoSeNaoNulo(veiculoPatch.getAnoFabricacao(), veiculo::setAnoFabricacao);
            atualizarCampoSeNaoNulo(veiculoPatch.getCor(), veiculo::setCor);
            atualizarCampoSeNaoNulo(veiculoPatch.isVendido(), veiculo::setVendido);
            veiculo.setDataAtualizacao(LocalDate.now()); // Atualiza a data de atualização
            return veiculoRepository.save(veiculo);
        }).orElse(null);
    }

    private <T> void atualizarCampoSeNaoNulo(T valorNovo, Consumer<T> atualizarCampo) {
        if (valorNovo != null) {
            atualizarCampo.accept(valorNovo);
        }
    }

    private boolean marcaValida(String marca) {
        return MARCAS_VALIDAS.contains(marca);
    }


}
