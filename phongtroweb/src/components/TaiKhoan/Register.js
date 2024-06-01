import { useRef, useState } from "react";
import { Alert, Button, Container, Form, Spinner } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import APIs, { endpoints } from "../../configs/APIs";

const Register = () => {
    const [user, setUser] = useState({
        "username": "",
        "password": "",
        "vaitro": "ROLE_NGUOITHUE",
        "ho": "",
        "ten": "",
        "diaChi": "",
        "sdt": "",
        "confirmPass": ""
    });
    const [err, setErr] = useState(null);
    const [loading, setLoading] = useState(false);
    const avatar = useRef();
    const nav = useNavigate();

    const register = (evt) => {
        evt.preventDefault();

        const process = async () => {
            let formTaiKhoan = new FormData();
            let formNguoiDung = new FormData();

            for (let field in user) {
                if (field !== "confirmPass") {
                    if (field === "username" || field === "password" || field === "vaitro") {
                        formTaiKhoan.append(field, user[field]);
                    } else {
                        formNguoiDung.append(field, user[field]);
                    }
                }
            }
            formTaiKhoan.append("file", avatar.current.files[0]);
            try {
                setLoading(true);
                let resTaiKhoan = await APIs.post(endpoints['register'], formTaiKhoan);
                console.info(formNguoiDung);
                if (resTaiKhoan.status === 201) {
                    let resNguoiDung;
                    formNguoiDung.append("idtaiKhoan", resTaiKhoan.data.id); 
                    try {
                        if (user.vaitro === "ROLE_NGUOITHUE") {
                            resNguoiDung = await APIs.post(endpoints['nguoithue'], formNguoiDung);
                        } else {
                            resNguoiDung = await APIs.post(endpoints['chutro'], formNguoiDung);
                        }

                        if (resNguoiDung.status === 201) {
                            nav("/login");
                        } else {
                            setErr("Đăng ký người dùng thất bại!");
                        }
                    } catch (error) {
                        setErr("Đăng ký người dùng thất bại!");
                    }
                } else {
                    setErr("Đăng ký tài khoản thất bại!");
                }
            } catch (error) {
                setErr("Hệ thống bị lỗi!");
                console.error(error);
            } finally {
                setLoading(false);
            }
        }

        if (user.password === user.confirmPass)
            process();
        else {
            setErr("Mật khẩu KHÔNG khớp!");
        }
    }

    const change = (evt, field) => {
        setUser(current => {
            return { ...current, [field]: evt.target.value }
        })
    }

    return (
    <Container>
        <h1 className="text-center text-info mt-2">ĐĂNG KÝ NGƯỜI DÙNG</h1>

        {err && <Alert variant="danger">{err}</Alert>}

        <Form onSubmit={register}>
            <Form.Group className="mb-3">
                <Form.Label>Tên</Form.Label>
                <Form.Control type="text" onChange={(e) => change(e, "ten")} placeholder="Tên" required />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Họ và chữ lót</Form.Label>
                <Form.Control type="text" onChange={(e) => change(e, "ho")} placeholder="Họ và chữ lót" required />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Địa chỉ</Form.Label>
                <Form.Control type="text" onChange={(e) => change(e, "diaChi")} placeholder="Địa chỉ" />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Điện thoại</Form.Label>
                <Form.Control type="tel" onChange={(e) => change(e, "sdt")} placeholder="Điện thoại" />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Tên đăng nhập</Form.Label>
                <Form.Control value={user.username} onChange={(e) => change(e, "username")} type="text" placeholder="Tên đăng nhập" required />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Mật khẩu</Form.Label>
                <Form.Control value={user.password} onChange={(e) => change(e, "password")} type="password" placeholder="Mật khẩu" required />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Xác nhận mật khẩu</Form.Label>
                <Form.Control value={user.confirmPass} onChange={(e) => change(e, "confirmPass")} type="password" placeholder="Xác nhận mật khẩu" required />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Vai trò</Form.Label>
                <Form.Select aria-label="Default select example" value={user.vaitro} onChange={(e) => change(e, "vaitro")}>
                    <option value="ROLE_CHUTRO">Chủ trọ</option>
                    <option value="ROLE_NGUOITHUE">Người thuê</option>
                </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Ảnh đại diện</Form.Label>
                <Form.Control type="file" ref={avatar} />
            </Form.Group>
            <Form.Group className="mb-3">
                {loading ? <Spinner animation="border" variant="primary" /> : <Button variant="info" type="submit">Đăng ký</Button>}
            </Form.Group>
        </Form>
    </Container>
    );
}

export default Register;