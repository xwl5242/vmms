<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath().toString();
%>
<myCss>
	<link rel="stylesheet" href="<%=path %>/static/admui/css/system/settings/display.css">
	<link rel="stylesheet" href="<%=path %>/static/admui/fonts/material-design/material-design.css">
	<link rel="stylesheet" href="<%=path %>/static/admui/plugins/bootstrap-select/bootstrap-select.css">
	<link rel="stylesheet" href="<%=path %>/static/admui/plugins/switchery/switchery.css">
</myCss>
<div class="page animation-fade page-display" title="系统设置">
    <div class="page-content">
        <form id="displayForm" class="form-horizontal padding-vertical-30">
        	<input type="hidden" id="id">
            <div class="form-group">
                <label class="col-sm-2 control-label">导航条颜色：</label>
                <div class="col-sm-10" id="skintoolsNavbar">
                    <ul class="list-unstyled list-inline color-radio">
                        <li>
                            <div class="radio-custom radio-primary">
                                <input type="radio" checked name="navbar" value=""> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-brown">
                                <input type="radio"  name="navbar" value="bg-brown-600"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-cyan">
                                <input type="radio"  name="navbar" value="bg-cyan-600"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-green">
                                <input type="radio"  name="navbar" value="bg-green-600"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-grey">
                                <input type="radio"  name="navbar" value="bg-grey-600"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-indigo">
                                <input type="radio"  name="navbar" value="bg-indigo-600"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-orange">
                                <input type="radio"  name="navbar" value="bg-orange-600"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-pink">
                                <input type="radio"  name="navbar" value="bg-pink-600"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-purple">
                                <input type="radio"  name="navbar" value="bg-purple-600"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-red">
                                <input type="radio"  name="navbar" value="bg-red-600"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-teal">
                                <input type="radio"  name="navbar" value="bg-teal-600"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-yellow">
                                <input type="radio"  name="navbar" value="bg-yellow-700"> <label></label>
                            </div>
                        </li>
                    </ul>
                    <div class="checkbox-custom checkbox-primary margin-top-10">
                        <input type="checkbox" checked id="navbarDisplay" name="navbarInverse" value="navbar-inverse">
                        <label for="navbarDisplay">通栏显示</label>
                    </div>
                </div>
            </div>
            <hr>
            <div class="form-group">
                <label class="col-sm-2 control-label">菜单主题：</label>
                <div class="col-sm-10">
                    <select data-plugin="selectpicker" id="skintoolsSidebar" name="sidebar">
                        <option value="site-menubar-dark" selected>深色主题</option>
                        <option value="site-menubar-light" >浅色主题</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">菜单显示：</label>
                <div class="col-sm-10">
                    <div class="radio-inline radio-custom radio-primary">
                        <input type="radio" id="menuUnfold" checked="checked" name="menuDisplay" value="site-menubar-unfold">
                        <label for="menuUnfold">默认展开</label>
                    </div>
                    <div class="radio-inline radio-custom radio-primary">
                        <input type="radio" id="menuFold"  name="menuDisplay" value="site-menubar-fold">
                        <label for="menuFold">默认收起</label>
                    </div>
                    <div class="margin-top-10 hidden" id="menuFoldSetting">
                        <span>鼠标经过菜单时显示：</span>
                        <div class="btn-group btn-group-xs" data-toggle="buttons">
                            <label class="btn  btn-outline btn-dark active" for="textIconKeep">
                                <input type="radio" id="textIconKeep" autocomplete="off" hidden="hidden" checked name="menuTxtIcon" value="site-menubar-keep"> 文字
                            </label> <label class="btn btn-outline btn-dark" for="textIconAlt">
                            <input type="radio" id="textIconAlt" autocomplete="off" hidden="hidden"  name="menuTxtIcon" value="site-menubar-fold-alt"> 图标
                        </label>
                        </div>
                    </div>
                    <span class="help-block">仅在可视区域宽度大于768px生效</span>
                </div>
            </div>
            <hr>
            <div class="form-group">
                <label class="col-sm-2 control-label">主题颜色：</label>
                <div class="col-sm-10" id="skintoolsPrimary">
                    <ul class="list-unstyled list-inline color-radio">
                        <li>
                            <div class="radio-custom radio-primary">
                                <input type="radio" checked name="themeColor" value="primary"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-brown">
                                <input type="radio"  name="themeColor" value="brown"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-cyan">
                                <input type="radio"  name="themeColor" value="cyan"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-green">
                                <input type="radio"  name="themeColor" value="green"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-grey">
                                <input type="radio"  name="themeColor" value="grey"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-indigo">
                                <input type="radio"  name="themeColor" value="indigo"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-orange">
                                <input type="radio"  name="themeColor" value="orange"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-pink">
                                <input type="radio"  name="themeColor" value="pink"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-purple">
                                <input type="radio"  name="themeColor" value="purple"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-red">
                                <input type="radio"  name="themeColor" value="red"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-teal">
                                <input type="radio"  name="themeColor" value="teal"> <label></label>
                            </div>
                        </li>
                        <li>
                            <div class="radio-custom radio-yellow">
                                <input type="radio"  name="themeColor" value="yellow"> <label></label>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <hr>
            <div class="form-group">
                <label class="col-sm-2 control-label">Tab 页签：</label>
                <div class="col-sm-10">
                    <div class="radio-inline radio-custom radio-primary">
                        <input type="radio" id="tabDisplayShow" checked name="tabFlag" value="site-contabs-open">
                        <label for="tabDisplayShow">开启</label>
                    </div>
                    <div class="radio-inline radio-custom radio-primary">
                        <input type="radio" id="tabDisplayHide"  name="tabFlag" value="">
                        <label for="tabDisplayHide">关闭</label>
                    </div>
                    <span class="help-block">Tab 页签必须保存以后才能看到效果</span>
                </div>
            </div>
            <hr>
            <div class="form-group">
                <div class="col-sm-10 col-sm-offset-2 margin-top-20">
                    <button type="button" class="btn btn-primary" name="save" value="true">保存</button>
                    <button type="button" class="btn btn-outline btn-default" name="reset" value="reset" id="skintoolsReset">恢复默认
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="<%=path %>/static/admui/js/system/settings/display.js"></script>
<script>
	//保存主题样式操作
	$("button[name='save']").click(function(){
		$.post('<%=path %>/user/updateTheme',$("#displayForm").serialize(),function(result){
			console.log(result);
		},'json');
	});
</script>