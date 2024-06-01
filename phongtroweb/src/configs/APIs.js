import axios from "axios";
import cookie from "react-cookies";

const BASE_URL = 'http://localhost:8080/PhongTroApp/';

export const endpoints = {
    "tin": "/api/tin/",
    "tindetails": (tinId) => `/api/tin/${tinId}/`,
    "tincomment": (tinId) => `/api/tin/${tinId}/comment/`,
    "login": "/api/login/",
    "current-user": "/api/current-taikhoan/",
    "register": "/api/taikhoan/",
    "nguoithue": "/api/nguoithue/",
    "chutro": "/api/chutro/",
    "comment": "/api/comment/",
}

export const authApi = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            "Authorization":  `${cookie.load("token")}`
        }
    })
}

export default axios.create({
    baseURL: BASE_URL
});