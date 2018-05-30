<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title><sitemesh:write property="title"/></title>
	<jsp:include page="/WEB-INF/views/web/commons/head.jsp"></jsp:include>		
	<sitemesh:write property="head"/>
</head>
<body class="site-contabs-open site-menubar-unfold">
<jsp:include page="/WEB-INF/views/web/commons/menu.jsp"></jsp:include>
	<main class="site-page">
	    <div class="page-container" id="admui-pageContent">
			<sitemesh:write property="myCss"/>
			<sitemesh:write property="body"/>
		</div>
	    <div class="page-loading vertical-align text-center">
	        <div class="page-loader loader-default loader vertical-align-middle" data-type="default"></div>
	    </div>
	</main>
	<jsp:include page="/WEB-INF/views/web/commons/footer.jsp"></jsp:include>
</body>
</html>