import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import APIs, { authApi, endpoints } from "../../configs/APIs";
import { Button, Form, Image, Spinner } from "react-bootstrap";

const CapNhatPhongTro = () => {
    const { id } = useParams();
    const nav = useNavigate();
    const [loadingPhong, setLoadingPhong] = useState(false);
    const [loadingSubmit, setLoadingSubmit] = useState(false);
    const [quanhuyen, setQuanHuyen] = useState([]);
    const [hinhAnhTro, setHinhAnhTro] = useState([]);
    const [formData, setFormData] = useState({
        diaChiPhong: '',
        gia: '',
        soNguoi: '',
        conTrong: '',
        idQuan: '',
        idchuTro: ''
    });

    const loadPhong = async() =>{
        setLoadingPhong(true);
        try {
            let  res = await APIs.get(`${endpoints['phongtro']}${id}/`)
            setFormData(res.data);

            let resQuanHuyen = await APIs.get(`${endpoints['quanhuyen']}`)
            setQuanHuyen(resQuanHuyen.data);

            let resHinhAnh = await APIs.get(`${endpoints['hinhanhtro']}`)
            setHinhAnhTro(resHinhAnh.data);
        } catch (error) {
            console.error(error);
        } finally{
            setLoadingPhong(false);
        }
    }

    const handleChange = (evt, field) => {
        setFormData(current => {
            return { ...current, [field]: evt.target.value }
        })
    }

    const handleSubmit = async(e) => {
        e.preventDefault();
        setLoadingSubmit(true);
        try {
            let res =  await authApi().patch(`${endpoints['phongtro']}${id}/`, formData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            if(res.status === 200){
                nav("/phongtro")
            }
        } catch (error) {
            console.error(error);
        } finally{
            setLoadingSubmit(false);
        }
    }

    useEffect(() => {
        loadPhong();
    }, []);

    return (<>
        {loadingPhong ? <div className="d-flex justify-content-center"><Spinner animation="border" variant="primary" /></div> : <>
            <h1 className="container text-center">Chỉnh sửa thông tin phòng trọ</h1>
        <Form className="container" onSubmit={handleSubmit}>
            <Form.Group className="mb-3">
                <Form.Label>Địa chỉ phòng</Form.Label>
                <Form.Control type="text" value={formData.diaChiPhong} onChange={(e) => handleChange(e, "diaChiPhong")} placeholder="Địa chỉ phòng ..." required />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Quận/Huyện</Form.Label>
                <Form.Select aria-label="Default select example" onChange={(e) => handleChange(e, "idQuan")}>
                    {quanhuyen.map(q =>(
                        (q.id === formData.idQuan.id) ? 
                        <option key={q.id} value={q.id} selected>{q.ten}, {q.idthanhPho.ten}</option>
                        :
                        <option key={q.id} value={q.id}>{q.ten}, {q.idthanhPho.ten}</option>
                        
                    ))}
                </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Giá</Form.Label>
                <Form.Control type="number"value={formData.gia} onChange={(e) => handleChange(e, "gia")} placeholder="Giá ..." />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Số người</Form.Label>
                <Form.Control type="number" value={formData.soNguoi} onChange={(e) => handleChange(e, "soNguoi")} placeholder="Số người ..." />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Tình trạng</Form.Label>
                <Form.Select aria-label="Default select example" onChange={(e) => handleChange(e, "conTrong")}>
                    {(formData.conTrong === 0) ?<>
                    <option value="0" selected>Còn trống</option>
                    <option value="1" >Đã thuê</option>
                    </>:<>
                    <option value="0">Còn trống</option>
                    <option value="1" selected>Đã thuê</option>
                    </>}
                </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Ảnh</Form.Label>
                <div className="d-flex flex-row mt-2">
                {hinhAnhTro.map(h => (
                    h.idphongTro.id == id && <Image key={h.id} className="rounded mx-auto d-block" style={{width: 120 + 'px'}} src={h.hinhAnh} />
                ))}
                </div>
            </Form.Group>
            <Form.Group className="mb-3">
                {loadingSubmit ? <Spinner animation="border" variant="primary" /> : <Button variant="info" type="submit">Cập nhật</Button>}
            </Form.Group>
        </Form></>}
        </>
    )
}

export default CapNhatPhongTro;