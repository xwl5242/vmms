<%@ page contentType="text/html;charset=UTF-8" %>
<%
	String path = request.getContextPath().toString();
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>后台系统</title>
	<jsp:include page="commons/meta.jsp"></jsp:include>
    <!-- 自定义 -->
    <link rel="stylesheet" href="<%=path %>/static/admui/css/login.css">
    <!-- 插件 -->
    <link rel="stylesheet" href="<%=path %>/static/admui/plugins/animsition/animsition.css">
    <!-- 图标 -->
    <link rel="stylesheet" href="<%=path %>/static/admui/fonts/web-icons/web-icons.css">
    <link rel="stylesheet" href="<%=path %>/static/admui/fonts/font-awesome/font-awesome.css">
    <!-- 插件 -->
    <link rel="stylesheet" href="<%=path %>/static/admui/plugins/bootstrap-select/bootstrap-select.css">
    <link rel="stylesheet" href="<%=path %>/static/admui/plugins/formvalidation/formValidation.css">
</head>

<body class="page-login layout-full page-dark">
<div class="page height-full">
    <div class="page-content height-full">
        <div class="page-brand-info vertical-align animation-slide-left hidden-xs">
            <div class="page-brand vertical-align-middle">
                <div class="brand">
<%--                     <img class="brand-img" src="<%=path %>/static/admui/images/logo-white.svg" height="50" alt="Admui"> --%>
                </div>
                <h2 class="hidden-sm">通用后台管理系统</h2>
                <ul class="list-icons hidden-sm">
                    <li>
                        <i class="wb-check" aria-hidden="true"></i> 一个基于最新 Web
                        技术的企业级通用管理系统快速开发框架，可以帮助企业极大的提高工作效率，节省开发成本，提升品牌形象。
                    </li>
                    <li><i class="wb-check" aria-hidden="true"></i> 快速开发各种MIS系统，如CMS、OA、CRM、ERP、POS等。</li>
                    <li><i class="wb-check" aria-hidden="true"></i> 涵盖了大量的常用组件和基础功能，最大程度上帮助企业节省时间成本和费用开支。
                    </li>
                </ul>
            </div>
        </div>
        <div class="page-login-main animation-fade">
        	
            <div class="vertical-align">
                <div class="vertical-align-middle">
                    <div class="brand visible-xs text-center">
                        <img class="brand-img" src="<%=path %>/static/admui/images/logo.svg" height="50" alt="Admui">
                    </div>
                    <h3 class="hidden-xs">登录 </h3>
                    <p class="hidden-xs">后台管理系统</p>
                    <form action="<%=path %>/loginIn" class="login-form" method="post" id="loginForm">
                        <div class="form-group">
                            <label class="sr-only" for="username">用户名</label>
                            <input type="text" class="form-control" id="userCode" name="userCode" placeholder="请输入用户名">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="password">密码</label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="password">验证码</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="validCode" placeholder="请输入验证码">
                                <a class="input-group-addon padding-0 reload-vify" href="javascript:;">
                                    <img src="<%=path%>/captcha" height="40">
                                </a>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <div class="checkbox-custom checkbox-inline checkbox-primary pull-left">
                                <input type="checkbox" id="remember" name="remember">
                                <label for="remember">自动登录</label>
                            </div>
                            <div class="pull-right">
<!--                             	<a href="http://www.admui.com/?sendUrl=http%3A%2F%2Fdemo.admui.com%2Flogin#register" target="_blank">注册账号</a> -->
<!--                             	· -->
	                            <a class="collapsed" data-toggle="collapse" href="#forgetPassword" aria-expanded="false" aria-controls="forgetPassword">
	                                找回密码
	                            </a>
                            </div>
                        </div>
                        <div class="collapse" id="forgetPassword" aria-expanded="true">
                            <div class="alert alert-warning alert-dismissible" role="alert">
                                请练习系统管理人员找回密码
                            </div>
                        </div>
                        <div class="collapse" id="loginError" aria-expanded="true">
                            <div class="alert alert-warning alert-dismissible" role="alert">
                               	${message}
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block margin-top-30">立即登录</button>
                    </form>
                </div>
            </div>
            <footer class="page-copyright">
                <p>北京序时科技&copy;
        			<a href="" target="_blank">bj.xushi.com</a>
                </p>
            </footer>
        </div>
    </div>
</div>

<!-- JS -->
<script src="<%=path %>/static/admui/plugins/jquery/jquery.min.js"></script>
<script src="<%=path %>/static/admui/plugins/bootstrap/bootstrap.min.js"></script>
<script src="<%=path %>/static/admui/plugins/bootstrap-select/bootstrap-select.min.js"></script>
<script src="<%=path %>/static/admui/plugins/formvalidation/formValidation.min.js" data-name="formValidation"></script>
<script src="<%=path %>/static/admui/plugins/formvalidation/framework/bootstrap.min.js" data-deps="formValidation"></script>
<script src="<%=path %>/static/admui/js/login.js"></script>
<script>
	$(function(){
		var message = '${message}';
		if(message){
			$("#loginError").show();
		}
	});
</script>
</body>

</html>
