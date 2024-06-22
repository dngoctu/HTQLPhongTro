<%-- 
    Document   : index
    Created on : Apr 15, 2024, 7:56:42 PM
    Author     : DikamonTu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="vi" scope="session"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-success">DANH SÁCH PHÒNG TRỌ</h1>

<div class="container" >
    <form>
        <select class="form-select  m-1" id="quanId" name="quanId">
            <option value="" selected>Địa điểm</option>
            <c:forEach items="${quanHuyen}" var="q">    
                <option value="${q.id}" id="${q.id}">${q.ten} - ${q.idthanhPho.ten}</option>
            </c:forEach>
        </select>
        <input class="form-control me-2 m-1" name="fromGia" type="text" placeholder="Nhập giá từ ...">
        <input class="form-control me-2  m-1" name="toGia" type="text" placeholder="đến ...">
        <select class="form-select  m-1" id="conTrong" name="conTrong">
            <option value="" selected>Tình trạng</option>
            <option value="0" id="0">Còn trống</option>
            <option value="1" id="1">Đã thuê</option>
        </select>
        <button class="m-1 btn btn-primary" type="submit">Tìm</button>
    </form>
</div >

<c:choose>
<c:when test="${pageContext.request.userPrincipal.name != null}">
<a href="<c:url value="/phongtro" />" class="btn btn-success mb-1">Thêm phòng</a>
<a href="<c:url value="/hinhanhtro" />" class="btn btn-success mb-1">Thêm ảnh</a>
</c:when>
</c:choose>
<table class="table table-striped">
    <tr>
        <th>ID</th>
        <th>Địa chỉ</th>
        <th>Ảnh phòng</th>
        <th>Giá</th>
        <th>Số người</th>
        <th>Tình trạng</th>
        <th></th>
    </tr>
    <c:forEach items="${phongtro}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.diaChiPhong}, ${p.idQuan.ten}, ${p.idQuan.idthanhPho.ten}</td>
            <td>
                <c:forEach items="${hinhanhtro}" var="ha">
                    <c:choose>
                    <c:when test="${ha.idphongTro.id==p.id}">
                        <img class="img-fluid" src="${ha.hinhAnh}" width="100" alt="${ha.hinhAnh.toString()}">
                    </c:when>
                </c:choose>
                </c:forEach>
            </td>
            <td> <fmt:formatNumber value="${p.gia}" type="currency" currencySymbol="VNĐ" minFractionDigits="0" maxFractionDigits="0"/></td>
            <td>${p.soNguoi}</td>
            <td>
                <c:choose>
                    <c:when test="${p.conTrong == 0}">Phòng trống</c:when>
                    <c:when test="${p.conTrong == 1}">Đã thuê</c:when>
                </c:choose>
                </td>
            <td>
                <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name != null}">
                <c:url value="/phongtro/${p.id}" var="url" />
                <c:url value="/api/phongtro/${p.id}/" var="urlDelete" />
                <button class="btn btn-danger m-1" data-bs-toggle="modal" data-bs-target="#modal${p.id}">Xóa</button>
                <!-- The Modal -->
            <div class="modal" id="modal${p.id}">
                <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                    <div class="modal-content">
                        <!-- Modal body -->
                        <div class="modal-body text-danger">
                            Bạn có chắc chắn muốn xóa phòng trọ
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Không</button>
                            <button type="button" class="btn btn-success" data-bs-dismiss="modal"
                                    onclick="deletePhongTro('${urlDelete}')">Ok</button>
                        </div>
                    </div>
                </div>
            </div>
                <a href="${url}" class="btn btn-info m-1">Cập nhật</a>
            </td>
            </c:when>
            </c:choose>
        </tr>
    </c:forEach>
</table>

<ul class="pagination mt-1">
        <c:forEach begin="1" end="${(phongtro.size() / pageSize) + 1}" var="pageNum">
            <c:url value="/?page=${pageNum}" var="urlPage" />
        <li class="page-item"><a class="page-link" href="${urlPage}">${pageNum}</a></li>
        </c:forEach>
</ul>

<script>
    function deletePhongTro(url) {
        fetch(url, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Xóa thất bại!");

        });
    }
</script>