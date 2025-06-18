import { useEffect, useState } from "react";
import { obtenerEmpresas } from "../servicios/empresaServicio";
import "bootstrap/dist/css/bootstrap.min.css";


export default function ListaEmpresas() {
  const [empresas, setEmpresas] = useState([]);

  useEffect(() => {
    obtenerEmpresas().then(res => setEmpresas(res.data));
  }, []);

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
          </tr>
        </thead>
        <tbody>
          {empresas.map((empresa) => (
            <tr key={empresa.id}>
              <td>{empresa.nombre}</td>
              <td>{empresa.nit}</td>
              <td>{empresa.direccion}</td>
              <td>{empresa.telefono}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
