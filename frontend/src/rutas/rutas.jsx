import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import FormularioEmpresa from "../componentes/FormularioEmpresa";
import ListaEmpresas from "../componentes/ListaEmpresas";


export default function Rutas() {
  return (
    <BrowserRouter>
      <nav>
        <Link to="/">Registrar</Link>       
      </nav>
      <nav>
        <Link to="/lista">Lista de Empresas</Link>
      </nav>
      <Routes>
        <Route path="/" element={<FormularioEmpresa />} />
        <Route path="/lista" element={<ListaEmpresas />} />
      </Routes>
    </BrowserRouter>
  );
}
