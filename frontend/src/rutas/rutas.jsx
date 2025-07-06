import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import FormularioEmpresa from "../componentes/FormularioEmpresa";
import ListaEmpresas from "../componentes/ListaEmpresas";
import PositionedMenu from "../componentes/Navegacion";


export default function Rutas() {
  return (
    <BrowserRouter>
      <PositionedMenu />
       
      <Routes>
        <Route path="/" element={<FormularioEmpresa />} />
        <Route path="/lista" element={<ListaEmpresas />} />
        <Route path="/menu" element={<PositionedMenu />} />
        <Route path="/editar/:id" element={<FormularioEmpresa />} />
      </Routes>
    </BrowserRouter>
  );
}
