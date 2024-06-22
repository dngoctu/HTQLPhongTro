<%-- 
    Document   : phongtro
    Created on : Jun 20, 2024, 2:37:43 PM
    Author     : DikamonTu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center">Quản lí phòng trọ</h1>
<c:url value="/phongtro" var="action" />

<form:form method="post" action="${action}" modelAttribute="phongtro" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="diaChiPhong"  placeholder="Địa chỉ phòng" path="diaChiPhong" />
        <label for="diaChiPhong">Địa chỉ phòng</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="idQuan"  path="idQuan.id">
            <c:forEach items="${quanHuyen}" var="q">    
                <option value="${q.id}" selected>${q.ten} ${q.idthanhPho.ten}</option>
            </c:forEach>
        </form:select>
        <label for="idtaiKhoan.id" class="form-label">Quận</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="gia" placeholder="Giá" path="gia" />
        <label for="gia">Giá</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="soNguoi"  placeholder="Số người" path="soNguoi" />
        <label for="soNguoi">Số người</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="conTrong"  path="conTrong"> 
            <option value="1">Đã thuê</option>
            <option value="0" selected>Còn trống</option>                 
        </form:select>
        <label for="conTrong" class="form-label">Tình trạng</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="idchuTro"  path="idchuTro.id"> 
            <c:forEach items="${chuTro}" var="c">    
                <c:choose>
                    <c:when test="${c.id==phongtro.idchuTro.id}">
                        <option value="${c.id}" selected>${c.ho} ${c.ten}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.ho} ${c.ten}</option>
                    </c:otherwise>
                </c:choose> 
            </c:forEach>
        </form:select>
        <label for="conTrong" class="form-label">Chủ trọ</label>
    </div>
    <div class="form-floating">
        <button class="btn btn-info mt-1" type="submit">
            <c:choose>
                <c:when test="${phongtro.id > 0}"> Cập nhật </c:when>
                <c:otherwise> Thêm </c:otherwise>
            </c:choose>
        </button>
        <form:hidden path="id" />
    </div>
</form:form>
