<%@ page contentType="text/html;charset=UTF-8" %>
<%
	String path = request.getContextPath().toString();
%>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="zh-cn">

<head>
    <title>主页</title>
    <jsp:include page="commons/head.jsp"></jsp:include>
    <script>
        Breakpoints();
    </script>
</head>

<body class="site-contabs-open site-menubar-unfold ">

<nav class="site-navbar navbar navbar-default navbar-fixed-top navbar-inverse " role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle hamburger hamburger-close navbar-toggle-left hided" data-toggle="menubar">
            <span class="sr-only">切换菜单</span> <span class="hamburger-bar"></span>
        </button>
        <button type="button" class="navbar-toggle collapsed" data-target="#admui-navbarCollapse" data-toggle="collapse">
            <i class="icon wb-more-horizontal" aria-hidden="true"></i>
        </button>
        <div class="navbar-brand navbar-brand-center site-gridmenu-toggle" data-toggle="gridmenu">
            <img class="navbar-brand-logo visible-lg visible-xs navbar-logo" src="<%=path%>/static/admui/images/logo-white.svg" title="Admui">
            <img class="navbar-brand-logo hidden-xs hidden-lg navbar-logo-mini" src="<%=path%>/static/admui/images/logo-white-min.svg" title="Admui">
        </div>
    </div>
    <div class="navbar-container container-fluid">
        <div class="collapse navbar-collapse navbar-collapse-toolbar" id="admui-navbarCollapse">
            <ul class="nav navbar-toolbar navbar-left">
                <li class="hidden-float">
                    <a data-toggle="menubar" class="hidden-float" href="javascript:;" role="button" id="admui-toggleMenubar">
                        <i class="icon hamburger hamburger-arrow-left"> 
                      		<span class="sr-only">切换目录</span>
                            <span class="hamburger-bar"></span> 
                        </i>
                    </a>
                </li>
                <li class="navbar-menu nav-tabs-horizontal nav-tabs-animate is-load" id="admui-navMenu">
                    <ul class="nav navbar-toolbar nav-tabs" role="tablist">
                        <!-- 顶部菜单 -->
                        <li role="presentation" class="active">
                            <a data-toggle="tab" href="#admui-navTabsItem-1" aria-controls="admui-navTabsItem-1" role="tab" aria-expanded="false">
                                <i class="icon wb-library"></i> <span>UI 示例</span>
                            </a>
                        </li>
                        <li role="presentation" class="">
                            <a data-toggle="tab" href="#admui-navTabsItem-2" aria-controls="admui-navTabsItem-2" role="tab" aria-expanded="false">
                                <i class="icon wb-desktop"></i> <span>页面示例</span>
                            </a>
                        </li>
                        <li role="presentation" class="">
                            <a data-toggle="tab" href="#admui-navTabsItem-3" aria-controls="admui-navTabsItem-3" role="tab" aria-expanded="false">
                                <i class="icon wb-table"></i> <span>表格示例</span>
                            </a>
                        </li>
                        <li role="presentation" class="">
                            <a data-toggle="tab" href="#admui-navTabsItem-4" aria-controls="admui-navTabsItem-4" role="tab" aria-expanded="false">
                                <i class="icon wb-order"></i> <span>表单示例</span>
                            </a>
                        </li>
                        <li role="presentation" class="">
                            <a data-toggle="tab" href="#admui-navTabsItem-5" aria-controls="admui-navTabsItem-5" role="tab" aria-expanded="false">
                                <i class="icon wb-pie-chart"></i> <span>统计图表</span>
                            </a>
                        </li>
                        <li role="presentation" class="">
                            <a data-toggle="tab" href="#admui-navTabsItem-6" aria-controls="admui-navTabsItem-6" role="tab" aria-expanded="false">
                                <i class="icon fa-bars"></i> <span>菜单示例</span>
                            </a>
                        </li>
                        <li role="presentation" class="">
                            <a data-toggle="tab" href="#admui-navTabsItem-7" aria-controls="admui-navTabsItem-7" role="tab" aria-expanded="false">
                                <i class="icon wb-settings"></i> <span>系统管理</span>
                            </a>
                        </li>
                        <li class="dropdown" id="admui-navbarSubMenu">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#" data-animation="slide-bottom" aria-expanded="true" role="button">
                                <i class="icon wb-more-vertical"></i>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li class="no-menu" role="presentation">
                                    <a href="/sitemap.html" target="_blank" role="menuitem" data-pjax>
                                        <i class="icon wb-list-numbered"></i><span>网站地图</span>
                                    </a>
                                </li>
                                <li class="no-menu" role="presentation">
                                    <a href="/system/menu.html" target="_blank" role="menuitem" data-pjax>
                                        <i class="icon wb-wrench"></i><span>菜单管理</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-toolbar navbar-right navbar-toolbar-right">
                <li class="hidden-xs" id="admui-navbarDisplay" data-toggle="tooltip" data-placement="bottom" title="设置主题与布局等">
                    <a class="icon wb-layout" href="/system/settings/display.html" target="_blank" data-pjax>
                        <span class="sr-only">主题与布局</span>
                    </a>
                </li>
                <li class="hidden-xs" id="admui-QRcode" data-toggle="tooltip" data-placement="bottom" title="在手机上预览本页">
                    <a class="icon wb-mobile" href="#" data-toggle="modal" data-target="#admui-mobileView">
                        <span class="sr-only">在手机上预览</span></a>
                </li>
                <li class="hidden-xs" id="admui-demo-app" data-toggle="tooltip" data-placement="bottom" title="下载桌面版">
                    <a class="icon wb-download dropdown-toggle" href="#" data-toggle="dropdown" aria-expanded="false" role="button">
                        <span class="sr-only">下载桌面版</span></a>
                    <ul class="dropdown-menu dropdown-menu-success bullet dropdown-menu-right" aria-labelledby="demoApp" role="menu">
                        <li role="presentation">
                            <a href="http://dl.admui.com/app/win/admui-demo.zip" role="menuitem"><i class="icon fa-windows"></i> Windows 版</a>
                        </li>
                        <li role="presentation">
                            <a href="http://dl.admui.com/app/mac/admui-demo.zip" role="menuitem"><i class="icon fa-apple"></i> MacOS 版</a>
                        </li>
                    </ul>
                </li>
                <li class="open-kf" id="admui-service" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="在线咨询">
                    <a class="icon wb-user" href="#" role="button">
                        <span class="sr-only">在线咨询</span></a>
                </li>
                <li class="hidden-xs" id="admui-navbarFullscreen" data-toggle="tooltip" data-placement="bottom" title="全屏">
                    <a class="icon icon-fullscreen" data-toggle="fullscreen" href="#" role="button">
                        <span class="sr-only">全屏</span>
                    </a>
                </li>
                <li>
                    <a class="icon fa-sign-out" id="admui-signOut" data-ctx="" data-user="9" href="/system/logout" role="button">
                    	<span class="sr-only">退出</span>
                   	</a>
                </li>

            </ul>
        </div>
    </div>
</nav>
<nav class="site-menubar site-menubar-dark">
    <div class="site-menubar-body">
        <div class="tab-content height-full" id="admui-navTabs">
        	<!-- 一级菜单 -->
            <div class="tab-pane animation-fade height-full active" id="admui-navTabsItem-1" role="tabpanel">
                <div>
                    <ul class="site-menu">
                    	<!-- 二级菜单 -->
                    	<li class="site-menu-category">UI 示例</li>
                    	<!-- 三级菜单 -->
                        <li class="site-menu-item has-sub ">
                            <a href="javascript:;"><i class="site-menu-icon fa-laptop" aria-hidden="true"></i><span class="site-menu-title">布局</span><span class="site-menu-arrow"></span></a>
                            <ul class="site-menu-sub">
                            	<!-- 四级菜单 -->
<!--                                	<li class="site-menu-item has-sub "> -->
<!--                                     <a href="javascript:;"> -->
<!--                                     	<span class="site-menu-title">栅格</span><span class="site-menu-arrow"></span></a> -->
<!--                                     <ul class="site-menu-sub"> -->
<!--                                 	五级菜单 -->
<!--                                 		<li class="site-menu-item "> -->
<!--                                             <a data-pjax href="grids.html" target="_blank"> -->
<!--                                                 <span class="site-menu-title">基本栅格</span> -->
<!-- 											</a> -->
<!--                                         </li> -->
<!--                                 		<li class="site-menu-item "> -->
<!--                                             <a data-pjax href="/components/layouts/layout-grid.html" target="_blank"> -->
<!--                                                 <span class="site-menu-title">布局栅格</span> -->
<!-- 											</a> -->
<!--                                         </li> -->
<!--                                 	五级菜单 -->
<!--                                 	</ul> -->
<!--                                	</li> -->
                                <li class="site-menu-item ">
                                    <a data-pjax href="/components/layouts/two-columns.html" target="_blank">
                                    	<span class="site-menu-title">两栏式布局</span>
                                    </a>
                                </li>
                                <!-- 四级菜单 -->
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>
<nav class="site-contabs" id="admui-siteConTabs">
    <button type="button" class="btn btn-icon btn-default pull-left hide">
        <i class="icon fa-angle-double-left"></i>
    </button>
    <div class="contabs-scroll pull-left">
        <ul class="nav con-tabs">
            <li class="active">
                <a data-pjax href="/home.html" rel="contents"><span>首页</span></a>
            </li>
        </ul>
    </div>
    <div class="btn-group pull-right">
        <button type="button" class="btn btn-icon btn-default hide">
            <i class="icon fa-angle-double-right"></i>
        </button>
        <button type="button" class="btn btn-default dropdown-toggle btn-outline" data-toggle="dropdown" aria-expanded="false">
            <span class="caret"></span> <span class="sr-only">切换菜单</span>
        </button>
        <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="conTabsDropdown" role="menu">
            <li class="reload-page" role="presentation">
                <a href="javascript:;" role="menuitem"><i class="icon wb-reload"></i> 刷新当前</a>
            </li>
            <li class="close-other" role="presentation">
                <a href="javascript:;" role="menuitem"><i class="icon wb-close"></i> 关闭其他</a>
            </li>
            <li class="close-all" role="presentation">
                <a href="javascript:;" role="menuitem"><i class="icon wb-power"></i> 关闭所有</a>
            </li>
        </ul>
    </div>
</nav>

<main class="site-page">
    <div class="page-container" id="admui-pageContent">
    	<div class="page animation-fade page-index">
            <div class="page-content">
   			 随后设计
    		</div>
    	</div>
    </div>
</main>

<footer class="site-footer">
    <div class="site-footer-legal">北京序时科技 &copy;
<!--         <a href="http://www.admui.com" target="_blank">admui.com</a> -->
    </div>
    <div class="site-footer-right">
        当前版本：v1.1.0
<!--         <a class="margin-left-5" data-toggle="tooltip" title="升级" href="http://www.admui.com/buy" target="_blank"> -->
<!--             <i class="icon fa-cloud-upload"></i> -->
<!--         </a> -->
    </div>
</footer>

<div class="modal fade" id="admui-mobileView" aria-hidden="true" aria-labelledby="admui-mobileView" role="dialog" tabindex="-1">
    <div class="modal-dialog modal-sidebar modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="关闭">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">在手机上预览本页</h4>
            </div>
            <div class="modal-body">
                <p class="thumbnail">
                    <img class="img-responsive img-bordered" id="admui-mobileViewQRcode" src="">
                </p>
                <p class="margin-top-20 font-size-12">
                    <i class="icon wb-bell" aria-hidden="true"></i> 您可以用手机APP(如微信、支付宝等)的扫码功能扫码下面的二维码，在手机上查看本页显示效果。</p>
            </div>
        </div>
    </div>
</div>

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

</body>
</html>