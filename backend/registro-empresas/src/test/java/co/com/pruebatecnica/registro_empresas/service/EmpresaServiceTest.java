package co.com.pruebatecnica.registro_empresas.service;

import co.com.pruebatecnica.registro_empresas.dto.EmpresaDTO;
import co.com.pruebatecnica.registro_empresas.dto.MensajeRespuesta;
import co.com.pruebatecnica.registro_empresas.entity.Empresa;
import co.com.pruebatecnica.registro_empresas.repository.EmpresaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpresaServiceTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaService empresaService;

    private EmpresaDTO empresaDTO;
    private Empresa empresa;

    @BeforeEach
    void setUp() {
        empresaDTO = new EmpresaDTO();
        empresaDTO.setNombre("Empresa Test");
        empresaDTO.setNit("123456789");
        empresaDTO.setDireccion("Calle 123");
        empresaDTO.setTelefono("555-1234");

        empresa = new Empresa();
        empresa.setNombre("Empresa Test");
        empresa.setNit("123456789");
        empresa.setDireccion("Calle 123");
        empresa.setTelefono("555-1234");
    }

    @Test
    void registrarEmpresa_Exitoso() {
        // Arrange
        when(empresaRepository.existsByNit(empresaDTO.getNit())).thenReturn(false);
        when(empresaRepository.save(any(Empresa.class))).thenReturn(empresa);

        // Act
        MensajeRespuesta respuesta = empresaService.registrarEmpresa(empresaDTO);

        // Assert
        assertNotNull(respuesta);
        assertEquals("Empresa registrada exitosamente", respuesta.getMensaje());
    }

    @Test
    void registrarEmpresa_NitExistente_LanzaExcepcion() {
        // Arrange
        when(empresaRepository.existsByNit(empresaDTO.getNit())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            empresaService.registrarEmpresa(empresaDTO);
        });

        assertEquals("El NIT ya está registrado.", exception.getMessage());
    }

    @Test
    void listarEmpresas_RetornaListaEmpresas() {
        // Arrange
        Empresa empresa2 = new Empresa();
        empresa2.setNombre("Otra Empresa");
        empresa2.setNit("987654321");
        empresa2.setDireccion("Avenida 456");
        empresa2.setTelefono("555-5678");

        when(empresaRepository.findAll()).thenReturn(Arrays.asList(empresa, empresa2));

        // Act
        List<EmpresaDTO> resultado = empresaService.listarEmpresas();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        EmpresaDTO dto1 = resultado.get(0);
        assertEquals(empresa.getNombre(), dto1.getNombre());
        assertEquals(empresa.getNit(), dto1.getNit());
        assertEquals(empresa.getDireccion(), dto1.getDireccion());
        assertEquals(empresa.getTelefono(), dto1.getTelefono());
        verify(empresaRepository).findAll();
    }

    @Test
    void listarEmpresas_ListaVacia_RetornaListaVacia() {
        // Arrange
        when(empresaRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<EmpresaDTO> resultado = empresaService.listarEmpresas();

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void convertirADTO_ConvierteCorrectamente() {
        // Act
        EmpresaDTO dto = empresaService.convertirADTO(empresa);

        // Assert
        assertNotNull(dto);
        assertEquals(empresa.getNombre(), dto.getNombre());
        assertEquals(empresa.getNit(), dto.getNit());
        assertEquals(empresa.getDireccion(), dto.getDireccion());
        assertEquals(empresa.getTelefono(), dto.getTelefono());
    }

}