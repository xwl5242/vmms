<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath().toString();
%>
<meta charset="utf-8">
<meta HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-store, must-revalidate"> 
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

<!-- 样式 -->
<link rel="stylesheet" href="<%=path %>/static/admui/themes/classic/global/css/bootstarp.css">
<link rel="stylesheet" href="<%=path %>/static/admui/themes/classic/base/css/site.css" id="siteStyle">

<!-- 图标 CSS-->
<link rel="stylesheet" href="<%=path %>/static/admui/fonts/font-awesome/font-awesome.css">
<link rel="stylesheet" href="<%=path %>/static/admui/fonts/web-icons/web-icons.css">

<!-- 插件 CSS -->
<link rel="stylesheet" href="<%=path %>/static/admui/plugins/animsition/animsition.css">
<link rel="stylesheet" href="<%=path %>/static/admui/plugins/toastr/toastr.css">
<link rel="stylesheet" href="<%=path %>/static/admui/css/demo.css">

<!-- 插件 -->
<script src="<%=path %>/static/admui/plugins/jquery/jquery.min.js"></script>
<script src="<%=path %>/static/admui/plugins/bootstrap/bootstrap.min.js"></script>
<script src="<%=path %>/static/admui/plugins/modernizr/modernizr.min.js"></script>
<script src="<%=path %>/static/admui/plugins/breakpoints/breakpoints.min.js"></script>
<script src="<%=path %>/static/admui/plugins/artTemplate/template.min.js"></script>
<script src="<%=path %>/static/admui/plugins/toastr/toastr.min.js"></script>
<script src="<%=path %>/static/admui/plugins/bootbox/bootbox.min.js"></script>

<!-- 核心  -->
<script src="<%=path %>/static/admui/themes/classic/global/js/core.js"></script>
<script src="<%=path %>/static/admui/themes/classic/base/js/site.js"></script>
<script src="<%=path %>/static/admui/themes/classic/global/js/configs/site-configs.js"></script>
<script src="<%=path %>/static/admui/themes/classic/global/js/components.js"></script>

<!-- 布局 -->
<script src="<%=path%>/static/admui/themes/classic/base/js/sections/menu.js"></script>
<script src="<%=path%>/static/admui/themes/classic/base/js/sections/media-menu.js"></script>
<script src="<%=path%>/static/admui/themes/classic/base/js/sections/content-tabs.js"></script>

<!-- 插件 -->
<script src="<%=path%>/static/admui/plugins/jquery-pjax/jquery.pjax.min.js"></script>
<script src="<%=path%>/static/admui/themes/classic/global/js/plugins/responsive-tabs.js"></script>
<script src="<%=path%>/static/admui/plugins/ashoverscroll/jquery-asHoverScroll.min.js"></script>
<script src="<%=path%>/static/admui/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="<%=path%>/static/admui/plugins/screenfull/screenfull.min.js"></script>

<!-- dataTables -->
<script src="<%=path %>/static/admui/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="<%=path %>/static/admui/plugins/datatables-bootstrap/dataTables.bootstrap.min.js"></script>
<script src="<%=path %>/static/admui/plugins/datatables-responsive/dataTables.responsive.min.js"></script>
<script src="<%=path %>/static/admui/plugins/highlight/highlight.pack.min.js"></script>
<script src="<%=path %>/static/admui/themes/classic/base/js/app.js"></script>
<script src="<%=path %>/static/admui/js/examples/tables/data-tables/common.js"></script>

<!-- select -->
<script src="<%=path %>/static/admui/plugins/bootstrap-select/bootstrap-select.min.js"></script>
<!-- datetimepicker -->
<script src="<%=path %>/static/admui/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/static/admui/plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>


<script src="<%=path %>/static/admui/plugins/switchery/switchery.min.js"></script>
<script>
	//用户样式的运用
	var userTheme = '${userTheme}';
	//设置本地缓存对象
	localStorage.setItem("admui.base.skinTools",userTheme);
	//替换样式文件的引用
	var cssHref = $("#siteStyle").attr("href");//原样式
	cssHref = cssHref.substring(0,cssHref.lastIndexOf("/"));//处理样式路径
	cssHref = cssHref.substring(0,cssHref.lastIndexOf("/"))+"/skins/"+JSON.parse(userTheme).themeColor+".css";//处理样式路径
	$("#siteStyle").attr('href',cssHref);//替换样式路径
</script>
