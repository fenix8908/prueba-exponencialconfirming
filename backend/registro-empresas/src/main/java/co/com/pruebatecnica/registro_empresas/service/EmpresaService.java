package co.com.pruebatecnica.registro_empresas.service;

import co.com.pruebatecnica.registro_empresas.dto.EmpresaDTO;
import co.com.pruebatecnica.registro_empresas.dto.MensajeRespuesta;
import co.com.pruebatecnica.registro_empresas.entity.Empresa;
import co.com.pruebatecnica.registro_empresas.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public MensajeRespuesta registrarEmpresa(EmpresaDTO empresaDTO) {
        if (empresaRepository.existsByNit(empresaDTO.getNit())) {
            throw new IllegalArgumentException("El NIT ya está registrado.");
        }
        Empresa empresa = new Empresa();
        empresa.setNombre(empresaDTO.getNombre());
        empresa.setNit(empresaDTO.getNit());
        empresa.setDireccion(empresaDTO.getDireccion());
        empresa.setTelefono(empresaDTO.getTelefono());

        empresaRepository.save(empresa);
        return new MensajeRespuesta("Empresa registrada exitosamente");
    }

    public List<EmpresaDTO> listarEmpresas() {
        return empresaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    EmpresaDTO convertirADTO(Empresa empresa) {
        EmpresaDTO dto = new EmpresaDTO();
        dto.setNombre(empresa.getNombre());
        dto.setNit(empresa.getNit());
        dto.setDireccion(empresa.getDireccion());
        dto.setTelefono(empresa.getTelefono());
        return dto;
    }
}
