package co.com.pruebatecnica.registro_empresas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmpresaDTO {

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    @NotBlank(message = "El NIT es obligatorio.")
    private String nit;

    private String direccion;

    @Pattern(regexp = "^[0-9\\-\\+]{7,15}$", message = "Formato de teléfono inválido.")
    private String telefono;
}
