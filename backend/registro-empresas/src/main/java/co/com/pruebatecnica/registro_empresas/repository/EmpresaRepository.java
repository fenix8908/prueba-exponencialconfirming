package co.com.pruebatecnica.registro_empresas.repository;

import co.com.pruebatecnica.registro_empresas.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpresaRepository extends JpaRepository<Empresa,Long> {

    boolean existsByNit(String nit);
}
