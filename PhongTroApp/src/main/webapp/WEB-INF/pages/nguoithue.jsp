<%-- 
    Document   : nguoithue
    Created on : May 17, 2024, 10:49:14 AM
    Author     : DikamonTu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center">Danh sách người thuê</h1>
<div class="container" >
    <form>
        <input class="form-control me-2" name="ten" type="text" placeholder="Tìm tên người thuê ...">
        <button class="mt-1 btn btn-primary" type="submit">Tìm</button>
    </form>
</div >
<div class="container mt-2" >
    <button data-bs-toggle="collapse" data-bs-target="#demo" type="button" class="btn btn-warning" >Thêm người thuê</button>

    <div id="demo" class="collapse">
        <c:url value="/nguoithue" var="action" />
        <form method="post" action="${action}" modelAttribute="nguoithue" enctype="multipart/form-data">
            <errors path="*" element="div" cssClass="alert alert-danger" />
            <div class="form-floating mb-3 mt-3">
                <input type="text" class="form-control" path="ho" id="ho" placeholder="Nhập họ..." name="ho" />
                <label for="ho">Họ: </label>
            </div>
            <div class="form-floating mb-3 mt-3">
                <input type="text" class="form-control" path="ten" id="ten" placeholder="Nhập tên..." name="ten" />
                <label for="ten">Tên: </label>
            </div>
            <div class="form-floating mb-3 mt-3">
                <input type="text" class="form-control" path="sdt" id="sdt" placeholder="Nhập số điện thoại..." name="sdt" />
                <label for="sdt">SĐT: </label>
            </div>
            <div class="form-floating mb-3 mt-3">
                <input type="text" class="form-control" path="diachi" id="diachi" placeholder="Nhập địa chỉ..." name="diachi" />
                <label for="diachi">Địa chỉ: </label>
            </div>
        </form>
    </div>
</div>
<c:forEach items="${nguoithue}" var="n">
    <div class="container mt-3">
        <div class="mt-4 p-2 bg-success text-white rounded">
            <p>Họ tên: ${n.ho} ${n.ten}, Số điện thoại: ${n.sdt}</p> 
            <p>Địa chỉ: ${n.diaChi}</p>
            <p>Tài khoản: ${n.idtaiKhoan.username}</p>
            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#modal${n.id}">
                Xóa
            </button>
            <c:url value="/api/nguoithue/${n.id}/" var="urlDelete" />
            <!-- The Modal -->
            <div class="modal" id="modal${n.id}">
                <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                    <div class="modal-content">
                        <!-- Modal body -->
                        <div class="modal-body text-danger">
                            Bạn có chắc chắn muốn xóa người dùng: ${n.ho} ${n.ten}
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Không</button>
                            <button type="button" class="btn btn-success" data-bs-dismiss="modal"
                                    onclick="deleteNguoiThue('${urlDelete}')">Ok</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>

<ul class="pagination mt-1">
    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
        <c:forEach begin="1" end="${(nguoithue.size() / pageSize) + 1}" var="pageNum">
            <c:url value="/nguoithue/?page=${pageNum}" var="urlPage" />
        <li class="page-item"><a class="page-link" href="${urlPage}">${pageNum}</a></li>
        </c:forEach>
    <li class="page-item"><a class="page-link" href="#">Next</a></li>
</ul>

<script>
    function deleteNguoiThue(url) {
        fetch(url, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Something wrong!");

        });
    }
</script>
