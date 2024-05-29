<%-- 
    Document   : header
    Created on : May 11, 2024, 8:26:53 AM
    Author     : DikamonTu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/PhongTroApp/">Hệ thống quản lý phòng trọ </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/" />">Trang chủ</a>
                </li>
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name == null}">
                        <li>
                            <a class="nav-link text-success" href="<c:url value="/login" />">Đăng nhập</a>
                        </li>
                    </c:when>
                    <c:when test="${pageContext.request.userPrincipal.getAuthorities()[0] == 'ROLE_ADMIN' }">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/nguoithue" />">Người thuê</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/chutro" />">Chủ trọ</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-warning" href="<c:url value="/register" />">Tạo tài khoản</a>
                        </li>
                        <li>
                            <a class="nav-link text-success">Xin chào ${pageContext.request.userPrincipal.name} !</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-info" href="<c:url value="/stats" />">Thống kê</a>
                        </li>
                        <li>
                            <a class="nav-link text-danger" href="<c:url value="/logout" />">Đăng xuất</a>
                        </li>
                    </c:when>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <li>
                            <a class="nav-link text-danger" href="<c:url value="/logout" />">Đăng xuất</a>
                        </li>
                    </c:when>
                        
            </c:choose>
            </ul>
            <form action="<c:url value="/" />" class="d-flex">
                <input class="form-control me-2" name="kw" type="text" placeholder="Nhập tên...">
                <button class="btn btn-primary" type="submit">Tìm</button>
            </form>
        </div>
    </div>
</nav>
