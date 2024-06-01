import { useContext, useState } from "react";
import { Alert, Button, Container, Form, Spinner } from "react-bootstrap";
import cookie from "react-cookies";
import { useNavigate } from "react-router-dom";
import { MyDispatchContext } from "../../configs/Contexts";
import APIs, { authApi, endpoints } from "../../configs/APIs";

const Login = () => {
    const [err, setErr] = useState(null);
    const [loading, setLoading] = useState(false);
    const dispatch = useContext(MyDispatchContext);
    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    const nav = useNavigate();

    const login = (evt) => {
        evt.preventDefault();

        const process = async () => {
            try {
                setLoading(true);
                let res = await APIs.post(endpoints['login'], {
                    "username": username,
                    "password": password
                });
                cookie.save("token", res.data);

                let u = await authApi().get(endpoints['current-user']);
                cookie.save("user", u.data);

                dispatch({
                    "type": "login",
                    "payload": u.data
                });
                nav("/");
            } catch (error) {
                console.error(error);
                setErr("Sai tên đăng nhập hoặc mật khẩu. Vui lòng kiểm tra lại !")
            } finally{
                setLoading(false);
            }
        }

        process();
    }

    return (
        <Container>
            <h1 className="text-center text-info">ĐĂNG NHẬP NGƯỜI DÙNG</h1>

            <Form onSubmit={login}>
                <Form.Group className="mb-3" controlId="username">
                    <Form.Label>Tên đăng nhập</Form.Label>
                    <Form.Control value={username} onChange={e => setUsername(e.target.value)} type="text" placeholder="Tên đăng nhập" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="password">
                    <Form.Label>Mật khẩu</Form.Label>
                    <Form.Control value={password} onChange={e => setPassword(e.target.value)} type="password" placeholder="Mật khẩu" />
                </Form.Group>
                {err && <Alert variant="danger">{err}</Alert>}
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                    {loading ? <Spinner animation="border" variant="primary" /> : <Button variant="info" type="submit">Đăng nhập</Button>}
                </Form.Group>
            </Form>
        </Container>
    )
}

export default Login;