import axios from 'axios';

const API_URL = 'http://localhost:8080/empresas';

export const registrarEmpresa = (datos) => axios.post(API_URL, datos);
export const obtenerEmpresas = () => axios.get(API_URL);
export const eliminarEmpresa = (id) => axios.delete(`${API_URL}/${id}`);
export const actualizarEmpresa = (id, datos) => axios.put(`${API_URL}/${id}`, datos);
export const obtenerEmpresaPorId = (id) => axios.get(`${API_URL}/${id}`);
