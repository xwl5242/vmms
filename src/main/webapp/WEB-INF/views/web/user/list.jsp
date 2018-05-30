<%@ page contentType="text/html;charset=UTF-8" %>
<%
String path = request.getContextPath().toString();
%>
<body>
	<myCss>
		<link rel="stylesheet" href="<%=path %>/static/admui/plugins/datatables-bootstrap/dataTables.bootstrap.css">
		<link rel="stylesheet" href="<%=path %>/static/admui/plugins/datatables-responsive/dataTables.responsive.css">
		<link rel="stylesheet" href="<%=path %>/static/admui/plugins/highlight/default.css">
		<link rel="stylesheet" href="<%=path %>/static/admui/plugins/highlight/github-gist.css">
		<link rel="stylesheet" href="<%=path %>/static/admui/plugins/highlight/highlight.css">
		<link rel="stylesheet" href="<%=path %>/static/admui/css/examples/tables/data-tables/examples.css">
		<link rel="stylesheet" href="<%=path %>/static/admui/plugins/bootstrap-select/bootstrap-select.css">
		<link rel="stylesheet" href="<%=path %>/static/admui/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
	</myCss>
	<div class="page animation-fade page-index" title="用户管理">
		<div class="page page-full animation-fade page-data-tables">
		    <div class="page-main">
		        <div class="page-content" id="DTConent">
		        	<div class="panel">
		        		<div class="panel-heading">
					    	<h3 class="panel-title">&nbsp;</h3>
					    </div>
		        		<div class="panel-body">
		        			<form class="form-horizontal" id="userSearForm">
		        				<div class="row">
			                        <div class="form-group col-sm-3">
			                            <label class="col-sm-3 control-label">用户名：</label>
			                            <div class="col-sm-9">
			                                <input type="text" class="form-control" name="userName" placeholder="用名" autocomplete="off">
			                            </div>
			                        </div>
			                        <div class="form-group  col-sm-3">
			                            <label class="col-sm-3 control-label">登录名：</label>
			                            <div class="col-sm-9">
			                                <input type="text" class="form-control" name="userCode" placeholder="登录名" autocomplete="off">
			                            </div>
			                        </div>
			                        <div class="form-group col-sm-6">
			                            <label class="col-sm-2 control-label">登录时间：</label>
		                                <div class="input-daterange col-sm-6">
		                                    <div class="input-group">
		                                        <span class="input-group-addon">
		                                            <i class="icon wb-calendar" aria-hidden="true"></i>
		                                        </span>
		                                        <input type="text" data-plugin="datetimepicker" class="form-control" name="start">
		                                        <span class="input-group-addon"> 至 </span>
		                                        <input type="text" data-plugin="datetimepicker" class="form-control" name="end">
		                                    </div>
		                                </div>
		                        	</div>
		                        </div>
		                        <div class="row">
			                        <div class="form-group col-sm-3">
			                            <label class="col-sm-3 control-label">电话：</label>
			                            <div class="col-sm-9">
			                                <input type="tel" class="form-control" name="phone" placeholder="电话" autocomplete="off">
			                            </div>
			                        </div>
			                        <div class="form-group  col-sm-3">
			                            <label class="col-sm-3 control-label">邮箱：</label>
			                            <div class="col-sm-9">
			                                <input type="email" class="form-control" name="mail" placeholder="邮箱" autocomplete="off">
			                            </div>
			                        </div>
			                        <div class="form-group col-sm-3">
			                            <label class="col-sm-3 control-label">使用状态：</label>
			                            <div class="col-sm-9">
			                                <select data-plugin="selectpicker">
			                                    <option value="0">禁用</option>
			                                    <option value="1">启用</option>
			                                </select>
			                            </div>
			                        </div>
			                        <div class="form-group col-sm-3">
			                            <label class="col-sm-3 control-label">是否删除：</label>
			                            <div class="col-sm-9">
			                                <select data-plugin="selectpicker">
			                                    <option value="0">否</option>
			                                    <option value="1">是</option>
			                                </select>
			                            </div>
			                        </div>
		                        </div>
		                        <div class="row">
		                        	<div class="form-group col-sm-4"></div>
		                        	<div class="form-group col-sm-4"></div>
			                        <div class="form-group col-sm-4">
			                            <div style="float: right;padding: 0px 12px;">
			                                <button type="button" class="btn btn-primary">查询</button>
			                                <button type="reset" class="btn btn-default btn-outline">重置</button>
			                            </div>
			                        </div>
		                        </div>
		                    </form>
		        		</div>
		        	</div>
					<div class="panel panel-bordered">
					    <div class="panel-heading">
					    	<h3 class="panel-title">用户列表</h3>
					    </div>
					    <div class="panel-body">
					        <table id="userTable" class="table table-bordered table-hover dataTable table-striped width-full text-nowrap">
					        	<thead>
					            <tr>
					                <th>用户名</th>
					                <th>登录名称</th>
					                <th>密码</th>
					                <th>登录次数</th>
					                <th>上次登录时间</th>
					                <th>电话</th>
					                <th>邮箱</th>
					                <th>使用状态</th>
					                <th>是否删除</th>
					            </tr>
					            </thead>
					        </table>
					    </div>
					</div>
		        </div>
		    </div>
		</div>
	</div>
	<script>
	$('#userTable').DataTable($.po('dataTable', {
		"ajax":{
			"url":"<%=path%>/user/list",
			"type":"PATCH"
		},
		"columns":[
		    {data: "userName"},
		    {data: "userCode"},
		    {data: "password"},
		    {data: "loginTotal"},
		    {data: "lastLoginTime"},
		    {data: "phone"},
		    {data: "mail"},
		    {data: "useStatus",render:function(data, type, row, meta){
		    	return data==0?'禁用':'启用';
		    }},
		    {data: "isDel",render:function(data, type, row, meta){
		    	return data==0?'否':'是';
		    }}
		]
	}));
	</script>
</body>