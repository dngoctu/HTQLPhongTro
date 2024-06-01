import { Button, Form, Spinner } from "react-bootstrap";
import React, { useContext, useEffect, useState } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import APIs, { authApi, endpoints } from "../../configs/APIs";
import { MyUserContext } from "../../configs/Contexts";
import { useNavigate } from "react-router-dom";

const ThemTin = () =>{
    const [noiDung, setNoiDung] = useState("");
    const [loaiTin, setLoaiTin] = useState("Ở ghép");
    const user = useContext(MyUserContext);
    const nav = useNavigate();
    const [loading, setLoading] = useState(false);

    const dangTin = async(evt) =>{
        evt.preventDefault();
        try {
            setLoading(true);
            let resNguoiDung;
            let idtaiKhoan= 0;
            if(user.vaiTro === "ROLE_NGUOITHUE"){
                resNguoiDung = await APIs.get(endpoints['nguoithue']);
            }
            else if(user.vaiTro === "ROLE_CHUTRO"){
                resNguoiDung = await APIs.get(endpoints['chutro']);
            }
           
            for (let nguoiDung of resNguoiDung.data){
                if(user.id === nguoiDung.idtaiKhoan.id){
                    idtaiKhoan = nguoiDung.id;
                    break;
                }
            }
            
            let data = {
                noiDung: noiDung,
                loaiTin: loaiTin
            };
            if (user.vaiTro === "ROLE_NGUOITHUE") {
                data.idnguoiThue = idtaiKhoan;
            } else {
                data.idchuTro = idtaiKhoan;
            }
            let res = await authApi().post(endpoints['tin'], data, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            if(res.status === 201){
                nav("/")
            }
        } catch (error) {
            console.error(error);
        } finally{
            setLoading(false);
        }
    };

    return(
    <>
    <h1 className="container text-center">Đăng tin</h1>
        <Form className="container" onSubmit={dangTin}>
        <Form.Group className="mb-3" controlId="noidung">
            <Form.Label>Nội dung bài viết</Form.Label>
            <ReactQuill 
            style={{ height: '100px' }}
                    placeholder="Nhập nội dung ..."
                    value={noiDung}
                    onChange={e=> setNoiDung(e)}
                    modules={ThemTin.modules}
                    formats={ThemTin.formats}
                    theme="snow"
            />
        </Form.Group>
        <Form.Group className="mt-5" controlId="loaitin">
            <Form.Label>Loại tin</Form.Label>
            <Form.Select aria-label="Default select example" onChange={e=>setLoaiTin(e.target.value)}>
                <option value="Ở ghép" >Ở ghép</option>
                <option value="Phòng mới">Phòng mới</option>
                </Form.Select>
        </Form.Group>
        {loading ?<Spinner animation="border" variant="primary" /> :<Button type="submit" className="mt-3">Đăng tin</Button> }
        </Form>
    </>
    );
}

ThemTin.modules = {
    toolbar: [
        [{ 'header': '1'}, {'header': '2'}, { 'font': [] }],
        [{size: []}],
        ['bold', 'italic', 'underline', 'strike', 'blockquote'],
        [{'list': 'ordered'}, {'list': 'bullet'}, 
         {'indent': '-1'}, {'indent': '+1'}],
        ['link', 'image', 'video'],
        ['clean']                                         
    ],
};



ThemTin.formats = [
    'header', 'font', 'size',
    'bold', 'italic', 'underline', 'strike', 'blockquote',
    'list', 'bullet', 'indent',
    'link', 'image', 'video'
];

export default ThemTin;