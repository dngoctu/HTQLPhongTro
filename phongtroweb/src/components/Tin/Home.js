import { useContext, useEffect, useState } from "react";
import { Card, Button, Col, Row, Spinner, Form, Modal } from "react-bootstrap";
import APIs, { authApi, endpoints } from "../../configs/APIs";
import { Link, useNavigate, useSearchParams } from "react-router-dom";
import { MyUserContext } from "../../configs/Contexts";

const Home = () => {
    const [tin, setTin] = useState(null);
    const [loading, setLoading] = useState(false);
    const [q,] = useSearchParams();
    const [page, setPage] = useState(1);
    const [loaiTin, setLoaiTin] = useState("");
    const [chuTro, setChuTro] = useState("");
    const [nguoiThue, setNguoiThue] = useState("");
    const nav = useNavigate();
    const user = useContext(MyUserContext);
    const [loadingDelete, setLoadingDelete] = useState(false);

    const [selectedTinId, setSelectedTinId] = useState(null);
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = (tinId) => {
        setSelectedTinId(tinId);
        setShow(true);
    };

    const loadTins = async () => {
        setLoading(true);
        try {
            let url = `${endpoints['tin']}?page=${page}`;

            let lt = q.get('loaitin');
            if (lt) {
                url = `${url}&loaitin=${lt}`;
                setPage(1);
            }

            let ct = q.get('chutro');
            if (ct) {
                url = `${url}&chutro=${ct}`;
                setPage(1);
            }

            let nt = q.get('nguoithue');
            if (nt) {
                url = `${url}&nguoithue=${nt}`;
                setPage(1);
            }

            let res = await APIs.get(url);

            if (page === 1)
                setTin(res.data);
            else if (page > 1)
                setTin(current => {
                    return [...current, ...res.data];
                })
        } catch (error) {
            console.error(error);
        } finally {
            setLoading(false);
        }
    }

    const timeFormat = (time) => {
        const moment = require('moment');
        const specificDate = moment(time);
        return specificDate.format('HH:mm:ss DD-MM-YYYY');
    }

    const deleteTin = async (idTinDel) => {
        setLoadingDelete(true);
        try {
            let res = await authApi().delete(`${endpoints['tin']}${idTinDel}/`)
            console.info(res)
            if (res.status === 204) {
                handleClose();
                loadTins();
        }
        } catch (error) {
            console.info(error);
        } finally{
            setLoadingDelete(false);
        }
    }

    useEffect(() => {
        loadTins();
    }, [q, page]);

    const loadMore = () => {
        if (!loading)
            setPage(page + 1);
    }

    const submit = (event) => {
        event.preventDefault();
        nav(`/?loaitin=${loaiTin}&chutro=${chuTro}&nguoithue=${nguoiThue}`);
    }

    return (
        <>
            <Form inline onSubmit={submit} className="container mt-1">
                {loading && <Spinner animation="border" variant="primary" />}
                <Row>
                    <Col>
                        <Form.Control value={loaiTin} onChange={e => setLoaiTin(e.target.value)}
                            type="text"
                            placeholder="Loại tin ..."
                            className="mr-sm-2 mt-1"
                        />
                        <Form.Control value={chuTro} onChange={e => setChuTro(e.target.value)}
                            type="text"
                            placeholder="Chủ trọ ..."
                            className="mr-sm-2 mt-1"
                        />
                        <Form.Control value={nguoiThue} onChange={e => setNguoiThue(e.target.value)}
                            type="text"
                            placeholder="Người thuê ..."
                            className="mr-sm-2 mt-1"
                        />
                    </Col>
                    <Col></Col>
                    <Col>
                        {user !== null && <Link to="/tin" className="nav-link"><Button className="m-1" variant="warning">Đăng bài</Button></Link>}
                    </Col>
                </Row>
                <Button type="submit" className="mt-1">Tìm kiếm</Button>
            </Form>
            <Row>
                <Col md={5} xs={17} className='mt-2 container-fluid'>
                    {tin === null ? <Spinner animation="border" variant="primary" /> : <>
                        {tin.map(t => (
                            <Card className='mt-1' key={t.id}>
                                <Card.Body>
                                    {(t.idchuTro !== null && t.idchuTro !== "") ?
                                        <Card.Title>Người đăng: {t.idchuTro.ho} {t.idchuTro.ten} ({t.idchuTro.idtaiKhoan.username}) <p>Thời gian: {timeFormat(t.thoiGian)}</p></Card.Title>
                                        : <>
                                            <Card.Title>Người đăng: {t.idnguoiThue.ho} {t.idnguoiThue.ten} ({t.idnguoiThue.idtaiKhoan.username}) <p>Thời gian: {timeFormat(t.thoiGian)}</p></Card.Title>
                                        </>}
                                    <Card.Text><td dangerouslySetInnerHTML={{ __html: t.noiDung }}></td></Card.Text>
                                    <Card.Text>Loại: {t.loaiTin}</Card.Text>
                                    <Link to={`/tin/${t.id}`} className="nav-link">
                                        <Button variant="success" className="m-1">Xem chi tiết</Button></Link>
                                    {t.idnguoiThue !== null ? <>
                                        {user !== null && t.idnguoiThue.idtaiKhoan.id === user.id &&
                                            <Button variant="danger" className="m-1" onClick={() => handleShow(t.id)}>Xóa</Button>}
                                    </> : <>
                                        {user !== null && t.idchuTro.idtaiKhoan.id === user.id &&
                                            <Button variant="danger" className="m-1" onClick={() => handleShow(t.id)}>Xóa</Button>}</>}
                                    <Modal show={show} onHide={handleClose} key={t.id}>
                                        <Modal.Header closeButton>
                                            <Modal.Title></Modal.Title>
                                        </Modal.Header>
                                        <Modal.Body>Bạn chắc chắn xóa tin này ? </Modal.Body>
                                        <Modal.Footer>
                                            <Button variant="secondary" onClick={handleClose}>
                                                Không
                                            </Button>
                                           {loadingDelete ?<Spinner animation="border" variant="primary" /> : 
                                           <Button variant="primary" onClick={() => deleteTin(selectedTinId)}>
                                                OK
                                            </Button>}
                                        </Modal.Footer>
                                    </Modal>
                                </Card.Body>
                            </Card>
                        ))}
                    </>}
                </Col>
            </Row>
            {loading && page > 1 && <Spinner animation="border" variant="primary" />}
            <div className="text-center mt-2">
                <Button variant="success" onClick={loadMore}>Xem thêm...</Button>
            </div>
        </>
    );
}

export default Home;