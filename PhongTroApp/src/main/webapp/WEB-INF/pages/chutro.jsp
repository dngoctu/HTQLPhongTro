<%-- 
    Document   : chutro
    Created on : May 17, 2024, 7:48:11 PM
    Author     : DikamonTu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center">Danh sách chủ trọ</h1>
<div class="container" >
    <form>
        <input class="form-control me-2" name="ten" type="text" placeholder="Tìm tên người thuê ...">
        <button class="mt-1 btn btn-primary" type="submit">Tìm</button>
    </form>
</div >
<div class="container mt-2" >
    <button data-bs-toggle="collapse" data-bs-target="#demo" type="button" class="btn btn-warning" >Thêm chủ trọ</button>

    <div id="demo" class="collapse">
        Lorem ipsum dolor text....
    </div>
</div>
<c:forEach items="${chutro}" var="c">
    <div class="container mt-3">
        <div class="mt-4 p-2 bg-success text-white rounded">
            <p>Họ tên: ${c.ho} ${c.ten}, Số điện thoại: ${c.sdt}</p> 
            <p>Địa chỉ: ${c.diaChi}</p>
            <p>Tài khoản: ${c.idtaiKhoan.username}</p>
            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#modal${c.id}">
                Xóa
            </button>
            <c:url value="/api/chutro/${c.id}/" var="urlDelete" />
            <!-- The Modal -->
            <div class="modal" id="modal${c.id}">
                <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                    <div class="modal-content">
                        <!-- Modal Header -->
                        <div class="modal-header"> 
                            <h4 class="modal-title text-body">Cảnh báo !</h4>
                        </div>
                        <!-- Modal body -->
                        <div class="modal-body text-danger">
                            Bạn có chắc chắn muốn xóa chủ trọ: ${c.ho} ${c.ten}
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Không</button>
                            <button type="button" class="btn btn-success" data-bs-dismiss="modal"
                                   onclick="deleteChuTro('${urlDelete}')" >Ok</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>

<ul class="pagination mt-1">
    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
        <c:forEach begin="1" end="${(chutro.size() / pageSize) + 1}" var="pageNum">
            <c:url value="/nguoithue/?page=${pageNum}" var="urlPage" />
        <li class="page-item"><a class="page-link" href="${urlPage}">${pageNum}</a></li>
        </c:forEach>
    <li class="page-item"><a class="page-link" href="#">Next</a></li>
</ul>

<script>
function deleteChuTro(url) {
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
