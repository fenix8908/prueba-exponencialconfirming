import { useState } from "react";
import { registrarEmpresa } from "../servicios/empresaServicio";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

export default function FormularioEmpresa() {
  const [formulario, setFormulario] = useState({
    nombre: "",
    nit: "",
    direccion: "",
    telefono: "",
  });
  const [mensaje, setMensaje] = useState("");
  const navegacion = useNavigate();

  const manejarCambio = (e) => {
    const { name, value } = e.target;
    setFormulario({ ...formulario, [name]: value });
  };

  const manejarEnvio = async (e) => {
    e.preventDefault();
    try {
      await registrarEmpresa(formulario);
      setMensaje("Empresa registrada con éxito.");
      setFormulario({ nombre: "", nit: "", direccion: "", telefono: "" });
      Swal.fire({
        title: "Éxito",
        text: "Empresa registrada correctamente.",
        icon: "success",
        confirmButtonText: "Aceptar",
      }).then(() => {
        navegacion("/lista");
      });
    } catch (error) {
        Swal.fire({
        title: "Error",
        text: error.response?.data?.error || "Error al registrar la empresa.",
        icon: "error",
        confirmButtonText: "Aceptar",
      });
    }
  };

  return (
    <div>
      <h2>Registrar Empresa</h2>
      {mensaje && <p>{mensaje}</p>}
      <form onSubmit={manejarEnvio} className="container mt-4 border p-4 shadow">
        <div className="row mb-3">
          <div className="col-md-6">
            <label htmlFor="nombre" className="form-label">
              Nombre
            </label>
            <input
              id="nombre"
              className="form-control"
              name="nombre"
              placeholder="Ingrese su nombre"
              value={formulario.nombre}
              onChange={manejarCambio}
              required
            />
          </div>
          <div className="col-md-6">
            <label htmlFor="nit" className="form-label">
              NIT
            </label>
            <input
              id="nit"
              className="form-control"
              name="nit"
              placeholder="Ingrese el NIT"
              value={formulario.nit}
              onChange={manejarCambio}
              required
            />
          </div>
        </div>

        <div className="row mb-3">
          <div className="col-md-6">
            <label htmlFor="direccion" className="form-label">
              Dirección
            </label>
            <input
              id="direccion"
              className="form-control"
              name="direccion"
              placeholder="Ingrese la dirección"
              value={formulario.direccion}
              onChange={manejarCambio}
            />
          </div>
          <div className="col-md-6">
            <label htmlFor="telefono" className="form-label">
              Teléfono
            </label>
            <input
              id="telefono"
              className="form-control"
              name="telefono"
              placeholder="Ingrese el teléfono"
              value={formulario.telefono}
              onChange={manejarCambio}
              pattern="^[0-9+\-]{7,15}$"
              title="El teléfono debe tener entre 7 y 15 caracteres numéricos"
            />
          </div>
        </div>

        <div className="row">
          <div className="col-12 text-center">
            <button type="submit" className="btn btn-primary">
              Registrar
            </button>
          </div>
        </div>
      </form>
    </div>
  );
}
