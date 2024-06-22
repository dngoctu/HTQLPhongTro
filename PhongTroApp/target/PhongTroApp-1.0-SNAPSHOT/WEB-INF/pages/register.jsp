<%-- 
    Document   : register
    Created on : May 18, 2024, 9:30:10 PM
    Author     : DikamonTu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="text-center mt-2">Đăng kí tài khoản người dùng</h2>
<form id="registrationForm" enctype="multipart/form-data" action="${pageContext.request.contextPath}/api/taikhoan/" method="post">
    <div class="mb-3 mt-3">
        <label class="form-label" for="username" >Tên đăng nhập:</label>
        <input class="form-control" type="text" id="username" name="username" required>
    </div>
    <div>
        <label class="form-label" for="password">Mật khẩu:</label>
        <input class="form-control" type="password" id="password" name="password" required>
    </div>
    <div>
        <label class="form-label" for="confirm">Xác nhận mật khẩu:</label>
        <input class="form-control" type="password" id="confirm" name="confirm" required>
    </div>
    <div>
        <label class="form-label" for="vaitro">Vai trò:</label>
        <select class="form-select" aria-label="Default select example" id="vaitro" name="vaitro">
            <c:forEach items="${vaitro}" var="vt">
                <c:choose>
                    <c:when test="${vt == 'ROLE_CHUTRO'}">
                       <option value="${vt}" id="${vt}">Chủ trọ</option>
                    </c:when>    
                    <c:when test="${vt == 'ROLE_NGUOITHUE'}">
                        <option value="${vt}" id="${vt}">Người thuê</option>
                    </c:when>   
                    <c:otherwise>
                        <option value="${vt}" id="${vt}">ADMIN</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </div>
    <div>
        <label class="form-label" for="email">Email: </label>
        <input class="form-control" type="email" id="email" name="email" required>
    </div>
    <div>
        <label class="form-label" for="file">Upload ảnh:</label>
        <input class="form-control" type="file" id="file" name="file">
    </div>
    <button class="btn btn-primary mt-2" type="submit">Đăng kí tài khoản</button>
</div>
<div class="alert alert-danger" id="alert-password" hidden></div>
</form>

<script>
    document.getElementById('registrationForm').addEventListener('submit', function (event) {
        var password = document.getElementById('password').value;
        var confirm = document.getElementById('confirm').value;
        var alert_password = document.getElementById('alert-password');

        if (password !== confirm) {
            event.preventDefault();
            alert_password.innerText = 'Mật khẩu và xác nhận mật khẩu không khớp.';
            alert_password.removeAttribute("hidden");
        }
    });
</script>