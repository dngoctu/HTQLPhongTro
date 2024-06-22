import { useContext, useEffect, useState } from "react";
import APIs, { authApi, endpoints } from "../../configs/APIs";
import { Link, useNavigate, useSearchParams } from "react-router-dom";
import { Button, Card, Col, Form, Image, Modal, Row, Spinner } from "react-bootstrap";
import BackToTop from "../Commons/BackToTop";
import { MyUserContext } from "../../configs/Contexts";
import Moment from "react-moment";
import 'moment/locale/vi';

const PhongTro = () => {
    const [phongTro, setPhongTro] = useState([]);
    const [loadingPhongTro, setLoadingPhongTro] = useState(false);
    const [page, setPage] = useState(1);
    const [q,] = useSearchParams();
    const [searchQuanHuyen, setSearchQuanHuyen] = useState("");
    const [fromGia, setFromGia] = useState("");
    const [toGia, setToGia] = useState("");
    const [conTrong, setConTrong] = useState(0);
    const [soNguoi, setSoNguoi] = useState("");
    const nav = useNavigate();
    const [loadingQuanHuyen, setLoadingQuanHuyen] = useState(false);
    const [quanHuyen, setQuanHuyen] = useState([]);
    const user = useContext(MyUserContext);
    const [hinhAnh, setHinhAnh] = useState([]);
    const [loadingDelete, setLoadingDelete] = useState(false);

    const [selectedPhongTroId, setSelectedPhongTroId] = useState(null);
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = (phongTroId) => {
        setSelectedPhongTroId(phongTroId);
        setShow(true);
    };

    const loadPhongTro = async () => {
        setLoadingPhongTro(true);
        try {
            let url = `${endpoints['phongtro']}?page=${page}`;

            let sqh = q.get('quanId');
            if (sqh) {
                url = `${url}&quanId=${sqh}`;
                setPage(1);
            }

            let fg = q.get('fromGia');
            if (fg) {
                url = `${url}&fromGia=${fg}`;
                setPage(1);
            }

            let tg = q.get('toGia');
            if (tg) {
                url = `${url}&toGia=${tg}`;
                setPage(1);
            }

            let tt = q.get('conTrong');
            if (tt) {
                url = `${url}&conTrong=${tt}`;
                setPage(1);
            }

            let sl = q.get('soNguoi');
            if (sl) {
                url = `${url}&soNguoi=${sl}`;
                setPage(1);
            }

            let res = await APIs.get(url);

            if (page === 1)
                setPhongTro(res.data);
            else if (page > 1)
                setPhongTro(current => {
                    return [...current, ...res.data];
                })

            let resHinhAnh = await APIs.get(endpoints['hinhanhtro']);
            setHinhAnh(resHinhAnh.data);

        } catch (error) {
            console.error(error);
        } finally {
            setLoadingPhongTro(false);
        }
    }

    const loadQuanHuyen = async () => {
        setLoadingQuanHuyen(true);
        try {
            let url = `${endpoints['quanhuyen']}`;
            let res = await APIs.get(url);
            setQuanHuyen(res.data);
        } catch (error) {
            console.error(error);
        } finally {
            setLoadingQuanHuyen(false);
        }
    }

    const loadMore = () => {
        if (!loadingPhongTro)
            setPage(page + 1);
    }

    const deletePhongTro = async (idPhongTroDel) => {
        setLoadingDelete(true);
        try {
            let res = await authApi().delete(`${endpoints['phongtro']}${idPhongTroDel}/`)
            if (res.status === 204) {
                handleClose();
                loadPhongTro();
            }
        } catch (error) {
            console.info(error);
        } finally {
            setLoadingDelete(false);
        }
    }

    const timeFormat = (time) => {
        const moment = require('moment');
        const specificDate = moment(time);
        return specificDate.format('HH:mm:ss DD-MM-YYYY');
    }

    useEffect(() => {
        loadPhongTro();
        loadQuanHuyen();
    }, [q, page]);

    const submit = (event) => {
        event.preventDefault();
        nav(`/phongtro/?quanId=${searchQuanHuyen}&fromGia=${fromGia}&toGia=${toGia}&conTrong=${conTrong}&soNguoi=${soNguoi}`);
    }

    return (
        <>
            <Form inline onSubmit={submit} className="container mt-1">
                <Row>
                    <Col>
                        <Form.Label>Quận/Huyện</Form.Label>
                        <Form.Select aria-label="Default select example" value={searchQuanHuyen} onChange={e => setSearchQuanHuyen(e.target.value)}>
                            <option value="">Tất cả</option>
                            {loadingQuanHuyen ? <Spinner animation="border" variant="primary" />
                                : <>
                                    {quanHuyen.map(q => (
                                        <option key={q.id} value={q.id}>{q.ten} - {q.idthanhPho.ten}</option>
                                    ))}
                                </>}
                        </Form.Select>
                        <Form.Control value={fromGia} onChange={e => setFromGia(e.target.value)}
                            type="text"
                            placeholder="Giá từ ..."
                            className="mr-sm-2 m-1"
                        />
                        <Form.Control value={toGia} onChange={e => setToGia(e.target.value)}
                            type="text"
                            placeholder="Đến ..."
                            className="mr-sm-2 m-1"
                        />
                        <Form.Label>Tình trạng</Form.Label>
                        <Form.Select aria-label="Default select example" value={conTrong} onChange={e => setConTrong(e.target.value)}>
                            <option value="">Tất cả</option>
                            <option key="0" value="0">Còn trống</option>
                            <option key="1" value="1">Đã thuê</option>
                        </Form.Select>
                        <Form.Control value={soNguoi} onChange={e => setSoNguoi(e.target.value)}
                            type="number"
                            placeholder="Số người ..."
                            className="m-1"
                        />
                    </Col>
                    <Col></Col>
                    <Col>
                        {(user !== null && user.vaiTro === "ROLE_CHUTRO") && <Link to="/themphongtro" className="nav-link"><Button className="m-1" variant="warning">Thêm phòng</Button></Link>}
                    </Col>
                </Row>
                <Button type="submit" className="m-1">Tìm kiếm</Button>
            </Form>
            {loadingPhongTro && <>
                <div className="d-flex justify-content-center"><Spinner animation="border" variant="primary" className="container mt-2" /></div>
            </>}
            {phongTro.map(p =>
                <Card className="w-100 container mt-2 border-3 border-secondary">
                    <Card.Title className="d-flex flex-row mt-2">
                        {hinhAnh.map(h =>
                            (h.idphongTro.id === p.id) && <Image key={h.id} className="rounded mx-auto d-block" style={{ width: 120 + 'px' }} src={h.hinhAnh} />
                        )}
                    </Card.Title>
                    <Card.Body>
                        <Card.Text>Địa chỉ: {p.diaChiPhong}, {p.idQuan.ten}, {p.idQuan.idthanhPho.ten}</Card.Text>
                        <Card.Text>Giá: {p.gia.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}</Card.Text>
                        <Card.Text>Số người: {p.soNguoi}</Card.Text>
                        <Card.Text className="text-primary">Tình trạng: {p.conTrong === 0 ? "Còn trống" : "Đã thuê"}</Card.Text>
                        <Card.Text>Chủ trọ: {p.idchuTro.ho} {p.idchuTro.ten} ({p.idchuTro.idtaiKhoan.username})</Card.Text>
                        <Card.Text className="text-danger">Số điện thoại: {p.idchuTro.sdt}</Card.Text>
                        <Card.Text>
                            {p.ngayDang !== null && p.ngayDang !== "" ? (
                                <>
                                    Thời gian: {timeFormat(p.ngayDang)} (<Moment locale="vi" fromNow>{p.ngayDang}</Moment>)
                                </>
                            ) : (
                                <>
                                    Thời gian: {timeFormat(p.ngayCapNhat)} (<Moment locale="vi" fromNow>{p.ngayCapNhat}</Moment>)
                                </>
                            )}
                        </Card.Text>{user !== null && user.id === p.idchuTro.idtaiKhoan.id && <>
                            <Link to={`/phongtro/${p.id}`}>
                                <div class="d-flex justify-content-center"><Button className="m-1">Cập nhật</Button></div>
                            </Link>
                            <div class="d-flex justify-content-center"><Button variant="danger" className="m-1" onClick={() => handleShow(p.id)}>Xóa</Button></div>
                        </>}
                        <Modal show={show} onHide={handleClose} key={p.id}>
                            <Modal.Header closeButton>
                                <Modal.Title></Modal.Title>
                            </Modal.Header>
                            <Modal.Body>Bạn chắc chắn xóa phòng trọ này ? </Modal.Body>
                            <Modal.Footer>
                                <Button variant="secondary" onClick={handleClose}>
                                    Không
                                </Button>
                                {loadingDelete ? <Spinner animation="border" variant="primary" /> :
                                    <Button variant="primary" onClick={() => deletePhongTro(selectedPhongTroId)}>
                                        OK
                                    </Button>}
                            </Modal.Footer>
                        </Modal>

                    </Card.Body>
                </Card>
            )}

            <div className="text-center mt-2">
                {(loadingPhongTro && page > 1) ? <Spinner animation="border" variant="primary" /> : <>
                    <Button variant="success" onClick={loadMore}>Xem thêm...</Button>
                </>}
            </div>
            <BackToTop />
        </>
    );
}

export default PhongTro;