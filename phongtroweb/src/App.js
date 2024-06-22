import 'bootstrap/dist/css/bootstrap.min.css';

import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/Commons/Header";
import Footer from "./components/Commons/Footer";
import Home from "./components/Tin/Home";
import ThemTin from './components/Tin/ThemTin';
import TinDetails from './components/Tin/TinDetails';
import Login from './components/TaiKhoan/Login';
import Register from './components/TaiKhoan/Register';
import { MyDispatchContext, MyUserContext } from './configs/Contexts';
import { useReducer } from 'react';
import TaiKhoanReducer from './configs/TaiKhoanReducer';
import cookie from "react-cookies";
import TaiKhoanDetails from './components/TaiKhoan/TaiKhoanDetails';
import PhongTro from './components/PhongTro/PhongTro';
import ThemPhongTro from './components/PhongTro/ThemPhongTro';
import CapNhatPhongTro from './components/PhongTro/CapNhatPhongTro';

const App = () => {
  const [user, dispatch] = useReducer(TaiKhoanReducer, cookie.load("user")||null);

  return (
    <BrowserRouter>
      <MyUserContext.Provider value={user}>
        <MyDispatchContext.Provider value={dispatch}>
          <Header />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/tin" element={<ThemTin />} />
            <Route path="/tin/:id" element={<TinDetails />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/taikhoan" element={<TaiKhoanDetails />} />
            <Route path="/phongtro" element={<PhongTro />} />
            <Route path="/themphongtro" element={<ThemPhongTro />} />
            <Route path="/phongtro/:id" element={<CapNhatPhongTro />} />

          </Routes>
          <Footer />
        </MyDispatchContext.Provider>
      </MyUserContext.Provider>
    </BrowserRouter>
  );
}

export default App;