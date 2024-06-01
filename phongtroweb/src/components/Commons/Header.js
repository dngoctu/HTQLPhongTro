import { useContext } from "react";
import { Container, Image, Nav, Navbar} from "react-bootstrap";
import { CiLogin } from "react-icons/ci";
import { Link } from "react-router-dom";
import { MyDispatchContext, MyUserContext } from "../../configs/Contexts";

const Header = () => {
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);

    return (
        <Navbar expand="lg" className="bg-info">
            <Container>
                <Navbar.Brand><Link to="/" className="nav-link">Phòng trọ web</Link></Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Link to="/" className="nav-link">Trang chủ</Link>                        
                    </Nav>
                </Navbar.Collapse>
                {user === null ? <>
                <Link to="/login" className="nav-link me-1 text-success m-1"> Đăng nhập </Link>
                <Link to="/register" className="nav-link me-1 m-1"> Đăng ký </Link>
                </> : <>
                <Link to="/taikhoan" className="nav-link text-success m-1"> 
                    <Image src={user.avatar} width="40" className="rounded-circle" /> {user.username}
                </Link>
                <Link to="/" onClick={() => dispatch({"type": "logout"})} className="nav-link text-danger m-1"> Đăng xuất <CiLogin /></Link>
                </>}
            
            </Container>
        </Navbar>
    );
}

export default Header;