<%-- 
    Document   : chutrodetails
    Created on : May 26, 2024, 7:47:30 PM
    Author     : DikamonTu
--%>

<%-- 
    Document   : nguoithueadd
    Created on : May 18, 2024, 10:15:39 AM
    Author     : DikamonTu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center">Quản lí chủ trọ</h1>

<c:url value="/chutro/chutrodetails" var="action" />
<form:form method="post" action="${action}" modelAttribute="chutro" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="ho"  placeholder="Họ" path="ho" />
        <label for="ho">Họ</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="ten" placeholder="Tên" path="ten" />
        <label for="ten">Tên</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="sdt"  placeholder="Số điện thoại" path="sdt" />
        <label for="sdt">Số điện thoại</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="diaChi"  placeholder="Địa chỉ" path="diaChi" />
        <label for="diaChi">Địa chỉ</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="idtaiKhoan"  path="idtaiKhoan.id">
            <c:forEach items="${taiKhoan}" var="tk">
                <c:choose>
                    <c:when test="${tk.id==chutro.idtaiKhoan.id}">
                        <option value="${tk.id}" selected>${tk.username}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${tk.id}">${tk.username}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="idtaiKhoan.id" class="form-label">Tài khoản</label>
    </div>
    <div class="form-floating">
        <button class="btn btn-info mt-1" type="submit">
            <c:choose>
                <c:when test="${chutro.id > 0}"> Cập nhật </c:when>
                <c:otherwise> Thêm </c:otherwise>
            </c:choose>
        </button>
        <form:hidden path="id" />
    </div>
</form:form>

