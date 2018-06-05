<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath().toString();
%>
<!DOCTYPE html>
<html class="no-js css-menubar height-full" lang="zh-cn">
<head>
    <title>请重新登录</title>
	<jsp:include page="../commons/meta.jsp"></jsp:include>
    <!-- 自定义 -->
    <link rel="stylesheet" href="<%=path %>/static/admui/css/locked.css">
    <!-- 插件 -->
    <link rel="stylesheet" href="<%=path %>/static/admui/plugins/animsition/animsition.css">
    <!-- 图标 -->
    <link rel="stylesheet" href="<%=path %>/static/admui/fonts/web-icons/web-icons.css">
</head>

<body class="page-locked layout-full page-dark">
	<div class=" page animation-fade vertical-align text-center animsition-fade height-full">
	    <div class="page-content vertical-align-middle">
	        <div class="avatar avatar-lg">
	            <img src="<%=path %>/static/admui/images/avatar.svg" alt="${userCode}">
	        </div>
	        <h4 class="locked-user">${userCode}</h4>
	        <form action="<%=path %>/loginIn" method="post" role="form">
	            <div class="input-group">
	            	<input type="hidden" name="userCode" value="${userCode }">
	            	<input type="hidden" name="needCaptcha" value="false">
	                <input type="password" class="form-control last" name="password" placeholder="请输入您的密码继续登录">
	                <span class="input-group-btn">
	                    <button type="submit" class="btn btn-primary">
	                        <i class="icon wb-unlock" aria-hidden="true"></i>
	                        <span class="sr-only">登录</span>
	                    </button>
	                </span>
	            </div>
	        </form>
	    </div>
	</div>
</body>
</html>