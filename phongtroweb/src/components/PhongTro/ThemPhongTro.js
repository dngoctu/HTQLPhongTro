import { useContext, useEffect, useState } from "react";
import { MyUserContext } from "../../configs/Contexts";
import { useNavigate } from "react-router-dom";
import APIs, { authApi, endpoints } from "../../configs/APIs";
import { Button, Form, Spinner } from "react-bootstrap";

const ThemPhongTro = ({ phongTroId }) => {
    const user = useContext(MyUserContext);
    const nav = useNavigate();
    const [loading, setLoading] = useState(false);
    const [loadingSubmit, setLoadingSubmit] = useState(false);
    const [quanhuyen, setQuanHuyen] = useState([]);
    const [files, setFiles] = useState([]);
    const [formPhongTro, setFormPhongTro] = useState({
        diaChiPhong: '',
        gia: '',
        soNguoi: '',
        conTrong: 0,
        idQuan: '',
        idchuTro: ''
    });

    const loadPage = async () => {
        setLoading(true);
        try {
            let resQuanHuyen = await APIs.get(`${endpoints['quanhuyen']}`);
            setQuanHuyen(resQuanHuyen.data);

            let resChuTro = await APIs.get(`${endpoints['chutro']}`);
            let idct;
            resChuTro.data.map(c => {
                if(c.idtaiKhoan.id === user.id){
                    idct = c.id;
                }
            })
            setFormPhongTro(current => {
                return { ...current, "idchuTro": idct }
            })
        } catch (error) {
            console.error(error);
        } finally {
            setLoading(false);
        }
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        setLoadingSubmit(true);
        try {
            let resPhongTro = await authApi().post(endpoints['phongtro'], formPhongTro, {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            if (resPhongTro.status === 201) {
                const formHinhAnh = new FormData();
                formHinhAnh.append('idphongTro', resPhongTro.data.id);
                for (let i = 0; i < files.length; i++) {
                    formHinhAnh.append('files', files[i]);
                }

                console.info(files.length, formHinhAnh.get("files"));

                try {
                    let response = await authApi().post(endpoints['hinhanhtro'], formHinhAnh, {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    });
                    if(response.status === 201){
                        nav("/phongtro");
                    }
                } catch (error) {
                    console.error(error);
                }
            }
        } catch (error) {
            console.error(error);
        } finally {
            setLoadingSubmit(false);
        }
    }

    const handleChange = (evt, field) => {
        setFormPhongTro(current => {
            return { ...current, [field]: evt.target.value }
        })
    }

    const handleFileChange = (event) => {
        setFiles(event.target.files);
    };

    useEffect(() => {
        loadPage();
    }, []);

    return (
        <>

            <h1 className="container text-center">Đăng phòng trọ</h1>
            <Form className="container" onSubmit={handleSubmit}>
                <Form.Group className="mb-3">
                    <Form.Label>Địa chỉ phòng</Form.Label>
                    <Form.Control type="text" value={formPhongTro.diaChiPhong} onChange={(e) => handleChange(e, "diaChiPhong")} placeholder="Địa chỉ phòng ..." required />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Quận/Huyện</Form.Label>
                    <Form.Select aria-label="Default select example" onChange={(e) => handleChange(e, "idQuan")}>
                        {quanhuyen.map(q => (<option key={q.id} value={q.id} selected>{q.ten}, {q.idthanhPho.ten}</option>))}
                    </Form.Select>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Giá</Form.Label>
                    <Form.Control type="number" value={formPhongTro.gia} onChange={(e) => handleChange(e, "gia")} placeholder="Giá ..." />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Số người</Form.Label>
                    <Form.Control type="number" value={formPhongTro.soNguoi} onChange={(e) => handleChange(e, "soNguoi")} placeholder="Số người ..." />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Ảnh</Form.Label>
                    <Form.Control type="file" id="files" name="files" multiple onChange={(e) => handleFileChange(e)} required />
                </Form.Group>
                <Form.Group className="mb-3">
                    {loadingSubmit ? <Spinner animation="border" variant="primary" /> : <Button variant="info" type="submit">Đăng</Button>}
                </Form.Group>
            </Form></>
    );
}

export default ThemPhongTro;