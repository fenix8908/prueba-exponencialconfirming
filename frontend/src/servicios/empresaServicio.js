import axios from 'axios';

const API_URL = 'http://localhost:8080/empresas';

export const registrarEmpresa = (datos) => axios.post(API_URL, datos);
export const obtenerEmpresas = () => axios.get(API_URL);
