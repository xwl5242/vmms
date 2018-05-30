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
	</myCss>
	<div class="page animation-fade page-index" title="用户管理">
		<div class="page page-full animation-fade page-data-tables">
		    <div class="page-main">
		        <div class="page-content" id="DTConent">
		        	<div class="panel">
		        		<div class="panel-body">
		        			<form class="form-horizontal">
		        				<div class="row">
			                        <div class="form-group col-sm-4">
			                            <label class="col-sm-3 control-label">您的姓名：</label>
			                            <div class="col-sm-9">
			                                <input type="text" class="form-control" name="name" placeholder="姓名" autocomplete="off">
			                            </div>
			                        </div>
			                        <div class="form-group  col-sm-4">
			                            <label class="col-sm-3 control-label">您的电话：</label>
			                            <div class="col-sm-9">
			                                <input type="text" class="form-control" name="name" placeholder="姓名" autocomplete="off">
			                            </div>
			                        </div>
			                        <div class="form-group col-sm-4">
			                            <label class="col-sm-3 control-label">您的邮箱：</label>
			                            <div class="col-sm-9">
			                                <input type="email" class="form-control" name="email" placeholder="@email.com" autocomplete="off">
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
					<div class="panel">
					    <div class="alert alert-warning" role="alert">
					        <p>这里展示下面列表的使用方式说明</p>
					    </div>
					    <div class="panel-body">
					        <table class="table table-bordered table-hover dataTable table-striped width-full text-nowrap" id="dataTableExample" data-plugin="dataTable">
					            <thead>
					            <tr>
					                <th>姓名</th>
					                <th>职位</th>
					                <th>工作地点</th>
					                <th>年龄</th>
					                <th>入职时间</th>
					                <th>年薪</th>
					            </tr>
					            </thead>
					            <tfoot>
					            <tr>
					                <th>姓名</th>
					                <th>职位</th>
					                <th>工作地点</th>
					                <th>年龄</th>
					                <th>入职时间</th>
					                <th>年薪</th>
					            </tr>
					            </tfoot>
					            <tbody>
					            <tr>
					                <td>李霞</td>
					                <td>系统架构师</td>
					                <td>北京</td>
					                <td>61</td>
					                <td>2011/04/25</td>
					                <td>&yen;320,800</td>
					            </tr>
					            <tr>
					                <td>杜重治</td>
					                <td>会计</td>
					                <td>上海</td>
					                <td>63</td>
					                <td>2011/07/25</td>
					                <td>&yen;170,750</td>
					            </tr>
					            <tr>
					                <td>陈锋</td>
					                <td>初级开发者</td>
					                <td>深圳</td>
					                <td>66</td>
					                <td>2009/01/12</td>
					                <td>&yen;86,000</td>
					            </tr>
					            <tr>
					                <td>郑伯宁</td>
					                <td>高级JavaScript开发者</td>
					                <td>北京</td>
					                <td>22</td>
					                <td>2012/03/29</td>
					                <td>&yen;433,060</td>
					            </tr>
					            <tr>
					                <td>施华军</td>
					                <td>会计</td>
					                <td>上海</td>
					                <td>33</td>
					                <td>2008/11/28</td>
					                <td>&yen;162,700</td>
					            </tr>
					            <tr>
					                <td>吴书振</td>
					                <td>集成专家</td>
					                <td>南京</td>
					                <td>61</td>
					                <td>2012/12/02</td>
					                <td>&yen;372,000</td>
					            </tr>
					            <tr>
					                <td>张宁</td>
					                <td>销售代表</td>
					                <td>深圳</td>
					                <td>59</td>
					                <td>2012/08/06</td>
					                <td>&yen;137,500</td>
					            </tr>
					            <tr>
					                <td>马世波</td>
					                <td>集成专家</td>
					                <td>上海</td>
					                <td>55</td>
					                <td>2010/10/14</td>
					                <td>&yen;327,900</td>
					            </tr>
					            <tr>
					                <td>马世波</td>
					                <td>集成专家</td>
					                <td>上海</td>
					                <td>55</td>
					                <td>2010/10/14</td>
					                <td>&yen;327,900</td>
					            </tr>
					            <tr>
					                <td>马世波</td>
					                <td>集成专家</td>
					                <td>上海</td>
					                <td>55</td>
					                <td>2010/10/14</td>
					                <td>&yen;327,900</td>
					            </tr>
					            <tr>
					                <td>马世波</td>
					                <td>集成专家</td>
					                <td>上海</td>
					                <td>55</td>
					                <td>2010/10/14</td>
					                <td>&yen;327,900</td>
					            </tr>
					            </tbody>
					        </table>
					    </div>
					</div>
		        </div>
		    </div>
		</div>
	</div>
</body>