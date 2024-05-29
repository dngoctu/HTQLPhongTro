import { Container, Nav, Navbar} from "react-bootstrap";
import { CiLogin } from "react-icons/ci";
import { Link } from "react-router-dom";

const Header = () => {
    return (
        <Navbar expand="lg" className="bg-info">
            <Container>
                <Navbar.Brand href="#home">Phòng trọ web</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Link to="/" className="nav-link">Trang chủ</Link>
                        <Link to="/" className="nav-link">Thông tin tài khoản</Link>
                        
                    </Nav>
                </Navbar.Collapse>
            <Link to="/" className="nav-link me-1"> Đăng nhập </Link>
            <Link to="/" className="nav-link"> Đăng xuất <CiLogin /></Link>
            </Container>
        </Navbar>
    );
}

export default Header;