<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath().toString();
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 移动设备 viewport -->
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no,minimal-ui">
<meta name="renderer" content="webkit"><!-- 360浏览器默认使用Webkit内核 -->
<meta http-equiv="Cache-Control" content="no-siteapp"><!-- 禁止百度SiteAPP转码 -->
<link rel="icon" type="image/png" href="<%=path %>/static/admui/images/favicon.png"><!-- Chrome浏览器添加桌面快捷方式（安卓） -->
<meta name="mobile-web-app-capable" content="yes">
<link rel="icon" sizes="192x192" href="<%=path %>/static/admui/images/apple-touch-icon.png"><!-- Safari浏览器添加到主屏幕（IOS） -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title" content="Admui">
<link rel="apple-touch-icon-precomposed" href="<%=path %>/static/admui/images/apple-touch-icon.png"><!-- Win8标题栏及ICON图标 -->
<meta name="msapplication-TileColor" content="#62a8ea">