import { useContext, useEffect, useState } from "react";
import { Container, Image, ListGroup, Spinner } from "react-bootstrap";
import { MyUserContext } from "../../configs/Contexts";
import APIs, { endpoints } from "../../configs/APIs";
import moment from 'moment';

const TaiKhoanDetails = () => {
    const user = useContext(MyUserContext);
    const [info, setInfo] = useState(null); 

    const loadInfo = async () =>{
        try {
            let res;
            if (user.vaiTro === "ROLE_NGUOITHUE") {
                res = await APIs.get(endpoints['nguoithue']);
            } else {
                res = await APIs.get(endpoints['chutro']);
            }

            const matchedInfo = res.data.find(t => user.id === t.idtaiKhoan.id);
            setInfo(matchedInfo); 
        } catch (error) {
            console.error(error);
        }
    }

    useEffect(() => {
        loadInfo();
    }, []);

    return (
        <Container>
            {info == null ?<div className="d-flex justify-content-center">
                <Spinner animation="border" variant="primary" className="container mt-2"/></div>:<>
                <ListGroup>
            <Image src={user.avatar} style={{ display: 'block', margin: '0 auto 10px auto', width: "20%" }} 
                 className="rounded-circle" />
                <ListGroup.Item>Tên đăng nhập: {user.username}</ListGroup.Item>
                <ListGroup.Item>Vai trò: {user.vaiTro === "ROLE_NGUOITHUE" ? <b>Người thuê</b> : <b>Chủ trọ</b>}</ListGroup.Item>
                <ListGroup.Item>Email: {user.email}</ListGroup.Item>
                <ListGroup.Item>Họ tên: {info.ho} {info.ten}</ListGroup.Item>
                <ListGroup.Item>Số điện thoại: {info.sdt}</ListGroup.Item>
                <ListGroup.Item>Địa chỉ: {info.diaChi}</ListGroup.Item>
                <ListGroup.Item>Ngày tham gia: {moment(info.ngayTao).format('DD/MM/YYYY')}</ListGroup.Item>
            </ListGroup></>}
        </Container>
    );
}

export default TaiKhoanDetails;
