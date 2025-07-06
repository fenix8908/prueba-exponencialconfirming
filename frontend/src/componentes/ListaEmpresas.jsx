import { useEffect, useState } from "react";
import { eliminarEmpresa, obtenerEmpresas } from "../servicios/empresaServicio";
import "bootstrap/dist/css/bootstrap.min.css";
import { Button } from "@mui/material";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";

export default function ListaEmpresas() {
  const [empresas, setEmpresas] = useState([]);
  const navegacion = useNavigate();

  useEffect(() => {
    obtenerEmpresas().then((res) => setEmpresas(res.data));
  }, []);
  // Función para eliminar una empresa
  const eliminarEmpresaPorId = async (id) => {
    try {
      // Confirmación antes de eliminar
      const confirmacion = await Swal.fire({
        title: "¿Estás seguro?",
        text: "Esta acción no se puede deshacer.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: "Cancelar",
      });
      if (!confirmacion.isConfirmed) {
        return; // Si el usuario cancela, no hacer nada 
      }
      // Llamar al servicio para eliminar la empresa
      await eliminarEmpresa(id);
      Swal.fire({
        title: "Exito",
        text: "Empresa eliminada correctamente.",
        icon: "success",
        confirmButtonText: "Aceptar",
      }).then(() => {
        navegacion("/lista");
        // Actualizar la lista de empresas después de eliminar
        setEmpresas(empresas.filter((empresa) => empresa.id !== id));
      });
    } catch (error) {
      Swal.fire({
        title: "Error",
        text: error.response?.data?.error || "Error al eliminar la empresa.",
        icon: "error",
        confirmButtonText: "Aceptar",
      });
    }
  }

    return (
      <div>
        <h2>Empresas Registradas</h2>
        <table className="table table-hover table-bordered">
          <thead className="thead-dark">
            <tr className="text-center">
              <th>Nombre</th>
              <th>NIT</th>
              <th>Dirección</th>
              <th>Teléfono</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {empresas.map((empresa) => (
              <tr key={empresa.id}>
                <td>{empresa.nombre}</td>
                <td>{empresa.nit}</td>
                <td>{empresa.direccion}</td>
                <td>{empresa.telefono}</td>
                <td className="text-center">
                  <button className="btn btn-primary me-2" onClick={()=>navegacion(`/editar/${empresa.id}`)} >Editar</button>
                  <Button variant="outlined" color="error" onClick={()=>eliminarEmpresaPorId(empresa.id)}>
                    Eliminar
                  </Button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  };
