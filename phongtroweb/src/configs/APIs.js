import axios from "axios";

const BASE_URL = 'http://localhost:8080/PhongTroApp/';

export const endpoints = {
    'tin': '/api/tin/',
}

export default axios.create({
    baseURL: BASE_URL
});