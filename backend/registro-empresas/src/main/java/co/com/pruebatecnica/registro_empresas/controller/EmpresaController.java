package co.com.pruebatecnica.registro_empresas.controller;

import co.com.pruebatecnica.registro_empresas.dto.EmpresaDTO;
import co.com.pruebatecnica.registro_empresas.dto.MensajeRespuesta;
import co.com.pruebatecnica.registro_empresas.entity.Empresa;
import co.com.pruebatecnica.registro_empresas.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/empresas")
@CrossOrigin
public class EmpresaController {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> registrarEmpresa(@Valid @RequestBody EmpresaDTO dto) {
        try {
            MensajeRespuesta respuesta = empresaService.registrarEmpresa(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<EmpresaDTO>> listarEmpresas() {
        return ResponseEntity.ok(empresaService.listarEmpresas());
    }
}
