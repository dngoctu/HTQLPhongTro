import { useParams } from "react-router-dom";
import APIs, { authApi, endpoints } from "../../configs/APIs";
import { useContext, useEffect, useState } from "react";
import { Alert, Button, Container, Form, Image, Modal, Spinner, Table } from "react-bootstrap";
import { MyUserContext } from "../../configs/Contexts";
import Moment from "react-moment";
import 'moment/locale/vi';
import { RiUserFollowFill, RiUserUnfollowFill } from "react-icons/ri";

const TinDetails = () => {
    const { id } = useParams();
    const [tin, setTin] = useState(null);
    const [commentList, setCommentList] = useState(null);
    const [loadingComment, setLoadingComment] = useState(false);
    const [myComment, setMyComment] = useState("");
    const user = useContext(MyUserContext);
    const [err, setErr] = useState(null);
    const [loading, setLoading] = useState(false);

    const [loadingDelete, setLoadingDelete] = useState(false);
    const [selectedCommentId, setSelectedCommentId] = useState(null);
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = (commentId) => {
        setSelectedCommentId(commentId);
        setShow(true);
    };
    const [follow, setFollow] = useState([]);
    const [loadingFollow, setLoadingFollow] = useState(false);

    const loadTin = async () => {
        try {
            let res = await APIs.get(endpoints['tindetails'](id));
            setTin(res.data);
        } catch (error) {
            console.error(error);
        }
    }

    const loadFollow = async () => {
        try {
            setLoadingFollow(true);
            let res = await APIs.get(endpoints['follow']);
            setFollow(res.data);
        } catch (error) {
            console.error(error);
        } finally {
            setLoadingFollow(false);
        }
    }

    const loadComment = async () => {
        setLoadingComment(true);
        try {
            let res = await APIs.get(endpoints['tincomment'](id));
            setCommentList(res.data);
        } catch (error) {
            console.error(error);
        } finally {
            setLoadingComment(false);
        }
    }

    const postComment = async (evt) => {
        evt.preventDefault();

        try {
            setLoading(true);
            let res = await authApi().post(endpoints['comment'], {
                "noiDung": myComment,
                "idtaiKhoan": user.id,
                "idTin": id
            });
            if (res.status === 201) {
                await loadComment();
            }
            else
                setErr("Có lỗi xảy ra!")
        } catch (error) {
            console.error(error);
        } finally {
            setLoading(false);
        }
    }

    const deleteComment = async (idCommentDel) => {
        try {
            setLoadingDelete(true);
            let res = await authApi().delete(`${endpoints['comment']}${idCommentDel}/`)
            if (res.status === 204) {
                handleClose();
                await loadComment();
            }
        } catch (error) {
            console.error(error);
        } finally {
            setLoadingDelete(false);

        }
    }

    const postFollow = async (idchuTro) => {
        try {
            setLoadingFollow(true);
            let idnguoiThue = 0;
            let resNguoiThue = await APIs.get(endpoints['nguoithue'])
            resNguoiThue.data.map(n => {
                if (n.idtaiKhoan.id === user.id)
                    idnguoiThue = n.id;
            })

            let resFollow = await authApi().post(endpoints['follow'], {
                "idchuTro": idchuTro,
                "idnguoiThue": idnguoiThue
            });
            if (resFollow.status === 201)
                await loadFollow();
        } catch (error) {
            console.error(error);
        } finally {
            setLoadingFollow(false);
        }
    }

    const deleteFollow = async (idFollow) => {
        try {
            setLoadingFollow(true);
            let res = await authApi().delete(`${endpoints['follow']}${idFollow}/`)
            if (res.status === 204) {
                await loadFollow();
            }
        } catch (error) {
            console.error(error);
        } finally {
            setLoadingFollow(false);
        }
    }
    const currentFollow = () =>follow.find(f =>
        f.idnguoiThue.idtaiKhoan.id === user.id && f.idchuTro.id === tin.idchuTro.id
    );

    useEffect(() => {
        loadTin();
    }, []);

    useEffect(() => {
        loadComment();
    }, []);

    useEffect(() => {
        loadFollow();
    }, []);
    const timeFormat = (time) => {
        const moment = require('moment');
        const specificDate = moment(time);
        return specificDate.format('HH:mm:ss DD-MM-YYYY');
    }

    return (
        <><Container>
            {tin === null ?<div className="d-flex justify-content-center">
                <Spinner animation="border" variant="primary" className="container mt-2"/></div> : <>
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th>Người đăng</th>
                            <th>Nội dung</th>
                            <th>Thời gian đăng</th>
                            <th>Loại</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            {tin.idchuTro !== null && tin.idchuTro !== "" ?
                                <td><Image width="50" src={tin.idchuTro.idtaiKhoan.avatar} roundedCircle />
                                    {tin.idchuTro.ho} {tin.idchuTro.ten}
                                    {user !== null && user.vaiTro === "ROLE_NGUOITHUE" && <>
                                        {tin.idchuTro !== "" && tin.idchuTro !== null && user !== null && currentFollow() ? (loadingFollow ? (
                                                <Spinner animation="border" variant="primary" />
                                            ) : (
                                                <Button variant="success" className="m-2" onClick={() => deleteFollow(currentFollow().id)}>
                                                   {loadingFollow ? <Spinner animation="border" variant="primary" /> : <> Hủy theo dõi <RiUserUnfollowFill /></>} 
                                                </Button>
                                            )
                                        ) : (loadingFollow ? (
                                                <Spinner animation="border" variant="primary" />
                                            ) : (
                                                <Button variant="success" className="m-2" onClick={() => postFollow(tin.idchuTro.id)}>
                                                {loadingFollow ? <Spinner animation="border" variant="primary" /> : <>Theo dõi <RiUserFollowFill /></>} 
                                             </Button>
                                            )   
                                        )}

                                    </>}
                                </td> :
                                <td><Image width="50" src={tin.idnguoiThue.idtaiKhoan.avatar} roundedCircle />
                                    {tin.idnguoiThue.ho} {tin.idnguoiThue.ten}</td>}
                            <td dangerouslySetInnerHTML={{ __html: tin.noiDung }}></td>
                            <td>{timeFormat(tin.thoiGian)}</td>
                            <td>{tin.loaiTin}</td>
                        </tr>
                    </tbody>
                </Table>
                <Form onSubmit={postComment}>
                    <Form.Group className="mb-3" controlId="binhluan">
                        <Form.Label>Bình luận</Form.Label>
                        <Form.Control as="textarea" rows={3} onChange={e => setMyComment(e.target.value)} />
                    </Form.Group>
                    {loading ? <Spinner animation="border" variant="primary" /> : <>
                        {user !== null && <Button type="submit" className="m-1">Bình luận</Button>}
                    </>}
                    {err && <Alert variant="danger">{err}</Alert>}
                </Form>
                <div className="mt-1 text-center text-success"><strong>Danh sách bình luận</strong></div>
                <Table striped bordered hover className="mt-3">
                    {commentList !== null && <>
                        <thead>
                            <tr>
                                <th>Người đăng</th>
                                <th>Nội dung</th>
                                <th>Thời gian đăng</th>
                                <th></th>
                            </tr>
                        </thead>
                        {loadingComment && <Spinner animation="border" variant="primary" />}
                        {commentList.map(c => (
                            <tbody>
                                <tr>
                                    <td><Image width="30" src={c.idtaiKhoan.avatar} roundedCircle />
                                        {c.idtaiKhoan.username}
                                    </td>
                                    <td>{c.noiDung}</td>
                                    <td><Moment locale="vi" fromNow>{c.thoiGian}</Moment></td>
                                    <td>
                                        {user !== null && c.idtaiKhoan.id === user.id && <>
                                            <Button variant="danger" className="m-1" onClick={() => handleShow(c.id)}>Xóa</Button>
                                            <Modal show={show} onHide={handleClose} key={user.id}>
                                                <Modal.Header closeButton>
                                                    <Modal.Title></Modal.Title>
                                                </Modal.Header>
                                                <Modal.Body>Bạn chắc chắn xóa comment này ? </Modal.Body>
                                                <Modal.Footer>
                                                    <Button variant="secondary" onClick={handleClose}>
                                                        Không
                                                    </Button>
                                                    {loadingDelete ? <Spinner animation="border" variant="primary" /> :
                                                        <Button variant="primary" onClick={() => deleteComment(selectedCommentId)}>
                                                            OK
                                                        </Button>}
                                                </Modal.Footer>
                                            </Modal>
                                        </>}

                                    </td>
                                </tr>
                            </tbody>
                        ))}
                    </>}
                </Table>
            </>}
        </Container>

        </>
    );
}

export default TinDetails;