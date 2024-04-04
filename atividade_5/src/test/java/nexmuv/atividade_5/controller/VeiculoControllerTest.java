package nexmuv.atividade_5.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nexmuv.atividade_5.model.Veiculo;
import nexmuv.atividade_5.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VeiculoControllerTest {

    @Mock
    private VeiculoService veiculoService;

    @InjectMocks
    private VeiculoController veiculoController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void deveriaCriarNovoVeiculo() {
        // Given
        Veiculo veiculo = createMockVeiculo();
        when(veiculoService.cadastrarVeiculo(any(Veiculo.class))).thenReturn(veiculo);

        // When
        ResponseEntity<?> responseEntity = veiculoController.cadastrarVeiculo(veiculo);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo(veiculo);
        verify(veiculoService, times(1)).cadastrarVeiculo(any(Veiculo.class));
    }

    @Test
    void deveriaAtualizarVeiculo() {
        // Given
        Long id = 1L;
        Veiculo veiculo = createMockVeiculoWithId(id);
        when(veiculoService.atualizarVeiculo(eq(id), any(Veiculo.class))).thenReturn(veiculo);

        // When
        ResponseEntity<?> responseEntity = veiculoController.atualizarVeiculo(id, veiculo);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(veiculo);
        verify(veiculoService, times(1)).atualizarVeiculo(eq(id), any(Veiculo.class));
    }

    @Test
    void deveriaExcluirVeiculo() {
        // Given
        Long id = 1L;

        // When
        ResponseEntity<Void> responseEntity = veiculoController.excluirVeiculo(id);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(veiculoService, times(1)).excluirVeiculo(eq(id));
    }

    @Test
    void deveriaFiltrarVeiculo() {
        // Given
        List<Veiculo> veiculos = createMockVeiculosList();
        when(veiculoService.filtrarVeiculos(any(), any(), any(), any(), any())).thenReturn(veiculos);

        // When
        ResponseEntity<List<Veiculo>> responseEntity = veiculoController.filtrarVeiculos(null, null, null, null, null);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(veiculos);
        verify(veiculoService, times(1)).filtrarVeiculos(any(), any(), any(), any(), any());
    }

    @Test
    void deveriaMostrarVeiculoPorId() {
        // Given
        Long id = 1L;
        Veiculo veiculo = createMockVeiculoWithId(id);
        when(veiculoService.buscarVeiculoPorId(eq(id))).thenReturn(veiculo);

        // When
        ResponseEntity<Veiculo> responseEntity = veiculoController.buscarVeiculoPorId(id);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(veiculo);
        verify(veiculoService, times(1)).buscarVeiculoPorId(eq(id));
    }

    @Test
    void deveriaAtualizarParcialmenteVeiculo() {
        // Given
        Long id = 1L;
        Veiculo veiculo = createMockVeiculoWithId(id);
        when(veiculoService.atualizarParcialmenteVeiculo(eq(id), any(Veiculo.class))).thenReturn(veiculo);

        // When
        ResponseEntity<?> responseEntity = veiculoController.atualizarParcialmenteVeiculo(id, veiculo);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(veiculo);
        verify(veiculoService, times(1)).atualizarParcialmenteVeiculo(eq(id), any(Veiculo.class));
    }

    private Veiculo createMockVeiculo() {
        return new Veiculo(null, "Toyota", "Corolla", 2022, "Prata", false);
    }

    private Veiculo createMockVeiculoWithId(Long id) {
        return new Veiculo(id, "Toyota", "Corolla", 2022, "Prata", false);
    }

    private List<Veiculo> createMockVeiculosList() {
        List<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(createMockVeiculoWithId(1L));
        veiculos.add(createMockVeiculoWithId(2L));
        return veiculos;
    }
}
