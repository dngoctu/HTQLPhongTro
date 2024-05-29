import { useEffect, useState } from "react";
import { Card, Button, Col, Row, Spinner, Form } from "react-bootstrap";
import APIs, { endpoints } from "../../configs/APIs";
import { useNavigate, useSearchParams } from "react-router-dom";

const Home = () => {
    const [tin, setTin] = useState(null);
    const [loading, setLoading] = useState(false);
    const [q, ] = useSearchParams();
    const [page, setPage] = useState(1);
    const [loaiTin, setLoaiTin] = useState("");
    const [chuTro, setChuTro] = useState("");
    const [nguoiThue, setNguoiThue] = useState("");
    const nav = useNavigate();

    const loadTins = async () => {
        setLoading(true);
        try {
            let url = `${endpoints['tin']}?page=${page}`;

            let lt = q.get('loaitin');
            if(lt){
                url = `${url}&loaitin=${lt}`;
                setPage(1);
            } 

            let ct = q.get('chutro');
            if(ct){
                url = `${url}&chutro=${ct}`;
                setPage(1);
            } 

            let nt = q.get('nguoithue');
            if(nt){
                url = `${url}&nguoithue=${nt}`;
                setPage(1);
            } 

            let res = await APIs.get(url);
            
            if(page === 1)
                setTin(res.data);
            else if(page > 1)
                setTin(current => {
                    return [...current, ...res.data];
                })
        } catch (error) {
            console.error(error);
        } finally{
            setLoading(false);
        }
    }

    const timeFormat = (time) => {
        const moment = require('moment');
        const specificDate = moment(time);
        return specificDate.format('HH:mm:ss DD-MM-YYYY');
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
        {loading && <Spinner animation="border" variant="primary" />}
        <Form inline onSubmit={submit} className="container mt-1">
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
                <Col>
                    <Button type="submit">Tìm</Button>
                </Col>
                </Row>
                </Form>
            <Row >
                <Col md={5} xs={17} className='mt-2 container-fluid'>
                    {tin === null ? <Spinner animation="border" variant="primary" /> : <>
                    {tin.map(t=> (
                        <Card className='mt-1' key={t.id}>
                            <Card.Body>
                                {(t.idchuTro !== null && t.idchuTro !== "") ? 
                                <Card.Title>Người đăng (chủ trọ): {t.idchuTro.ho} {t.idchuTro.ten} <p>Thời gian: {timeFormat(t.thoiGian)}</p></Card.Title> 
                                :<>
                                <Card.Title>Người đăng (Người thuê): {t.idnguoiThue.ho} {t.idnguoiThue.ten} <p>Thời gian: {timeFormat(t.thoiGian)}</p></Card.Title>
                                </>}
                                <Card.Text>{t.noiDung}</Card.Text>
                                <Card.Text>Loại: {t.loaiTin}</Card.Text>
                                <Button variant="danger" className="m-1" >Xem chi tiết</Button>
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