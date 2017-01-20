
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"][/@ui.plugin]
	[@jqPlugin.plugin date="1" /]
	<style>
		.button.green{
		   width:70px;
		   height:30px;
      }
	</style>
	<head>
	</head>
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">会费缴纳概览</h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>会费概览
					</li>
					<li class="pull-right no-text-shadow">
						<div id="dashboard-report-range" class="dashboard-date-range responsive"  style="display: block;">
							<i class="icon-calendar"></i>
							<span id="dynamictime"></span>
						</div>
					</li>
				</ul>
			</div>
		</div>

		<div id="dashboard">
			<div>
			<form id="formSearch" action="/admin/finance/provider_memberfi_stat" method="post">
			  用户名：<input type="text"  name="userame"  placeholder="请输入要查询的用户名"/>
			  缴费时间：<input type="text"  name="startTime" onclick="selectMonth();" placeholder="请输入开始时间"/>
				-----
				<input type="text"  name="endTime" onclick="selectMonth();" placeholder="请输入结束时间"/>
				类别：
				<select>
					<option>共产会</option>
					<option>店主会</option>
				</select>
				<button name="query" id="query" class="button green tags" type="submit">查询</button>
			 </form>
		   </div>
			<div class="row-fluid">
				<div class="span12" responsive" data-tablet="span12 fix-offset" data-desktop="span6">
					<!-- 会费概览-->
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="icon-cogs"></i>会费概览</div>
							<div class="actions">
									<!--<a href="/admin/book/book_edit" class="btn blue"><i class="icon-plus"></i>&nbsp;添加</a>-->
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
										<th>类别</th>
										<th>用户名</th>
										<th>缴费时间</th>
										<th>到期时间</th>
										<th>缴费金额</th>
									</tr>
								</thead>
								<!--<tbody>
									[#list finance as f]
										<tr class="odd gradeX">
											<td>${f["payTime"] }</td>
											<td>${f["count"]}</td>
											<td>${f["fee"] }</td>
										</tr>
									[/#list]
								</tbody>-->
								<tbody>
										<tr class="odd gradeX">
											<td>供产会</td>
											<td>玲玲</td>
											<td>2015-11-27 09：18：26</td>
											<td>2016-11-27 09：18：26</td>
											<td>5800</td>
										</tr>
										<tr class="odd gradeX">
											<td>供产会</td>
											<td>君君</td>
											<td>2015-06-27 09：18：26</td>
											<td>2016-106-27 09：18：26</td>
											<td>5800</td>
										</tr>
										<tr class="odd gradeX">
											<td>店主会</td>
											<td>赵峰</td>
											<td>2016-03-07 09：18：26</td>
											<td>2017-03-07 09：18：26</td>
											<td>5800</td>
										</tr>
										<tr class="odd gradeX">
											<td>店主会</td>
											<td>美林</td>
											<td>2015-01-08 09：18：26</td>
											<td>2016-01-08 09：18：26</td>
											<td>5800</td>
										</tr>
										<tr class="odd gradeX">
											<td>供产会</td>
											<td>严如玉</td>
											<td>2015-11-27 15：35：23</td>
											<td>2016-11-27 15：35：23</td>
											<td>5800</td>
										</tr>
								</tbody>
							</table>
							<div id="pagesplit"></div>
							[@ui.pagesplit link="/admin/book/book_list" renderTo="pagesplit" /]
						</div>
					</div>
					<!-- 系统通知公告结束-->
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
[/@ui.page]
<script>
	$(document).ready(function(){
		DynamicTime.init("dynamictime");
		$(".bdelete").on("click",function(){
			if (window.confirm("您确定要删除该条图书信息吗？")){
				var selId = $(this).attr("data-cid");
				Submit.invok("/admin/book/book_delete?id="+selId,function(){
					window.location.reload(true);
				});				
			}
		});
	});
	
	function selectMonth() {  
        WdatePicker({ dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false });  
    }  
</script>