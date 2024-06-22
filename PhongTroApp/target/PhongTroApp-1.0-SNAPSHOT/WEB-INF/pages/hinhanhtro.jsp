<%-- 
    Document   : hinhanhtro
    Created on : Jun 20, 2024, 9:40:43 PM
    Author     : DikamonTu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center">Quản lí hình ảnh</h1>

<form method="post" action="${pageContext.request.contextPath}/hinhanhtro" enctype="multipart/form-data">
    <div>
        <label class="form-label" for="files">Upload ảnh:</label>
        <input class="form-control" type="file" id="files" name="files" multiple="true" />
        
    </div>
    <div class="mb-3 mt-3">
        <label for="idphongTro" class="form-label">Phòng</label>
        <select class="form-select" id="idphongTro" name="idphongTro">
            <c:forEach items="${phongtro}" var="p">    
                <option value="${p.id}" id="${p.id}" selected>Phòng: ${p.diaChiPhong}, ${p.idQuan.ten}, ${p.idQuan.idthanhPho.ten}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-floating">
        <button class="btn btn-info mt-1" type="submit">
               Thêm
        </button>
    </div>

</form>