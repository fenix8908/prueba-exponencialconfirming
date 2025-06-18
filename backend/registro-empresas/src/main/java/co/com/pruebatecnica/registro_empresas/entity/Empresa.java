package co.com.pruebatecnica.registro_empresas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "empresas", uniqueConstraints = {@UniqueConstraint(columnNames = "nit")})
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(unique = false)
    private String nombre;

    @NotBlank(message = "El NIT es obligatorio")
    @Column(unique = true)
    private String nit;

    private String direccion;

    @Pattern(regexp = "^[0-9\\-\\+]{7,15}$", message = "Formato de teléfono inválido.")
    private String telefono;
}
