
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
				<h3 class="page-title">健康会交易统计</h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>交易统计
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
				<form id="formSearch" action="/admin/finance/healthy_trade_stat" method="post">
				<input type="text"  name="startTime" onclick="selectMonth();" placeholder="请输入开始时间"/>
				-----
				<input type="text"  name="endTime" onclick="selectMonth();" placeholder="请输入结束时间"/>
				<button name="query" id="query" class="button green tags" type="submit">查询</button>
			 </form>
		   </div>
			<div class="row-fluid">
				<div class="span12" responsive" data-tablet="span12 fix-offset" data-desktop="span6">
					<!-- 交易统计-->
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="icon-cogs"></i>交易统计</div>
							<div class="actions">
									<!--<a href="/admin/book/book_edit" class="btn blue"><i class="icon-plus"></i>&nbsp;添加</a>-->
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
										<th>月份</th>
										<th>交易金额</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
								<!--<tbody>
										[#list members as m]
										<tr class="odd gradeX">
											<td>${m["photo"] }</td>
											<td>${m["providerName"]}</td>
											<td>${m["businessCategoryName"] }</td>
										</tr>
									[/#list]
								</tbody>-->
								<tbody>
										<tr class="odd gradeX">
											<td>2016-01</td>
											<td>10000</td>
											<td>
											<a href="/admin/finance/healthy_trade_detail?id=${m['id']}" class="btn green btnDetail mini" data-id="${m['id']}" data-value="1"><i class="icon-leaf"></i> 条目</a>
											</td>
										</tr>
										<tr class="odd gradeX">
											<td>2016-02</td>
											<td>58900</td>
											<td>
											<a href="/admin/finance/healthy_trade_detail?id=${m['id']}" class="btn green btnDetail mini" data-id="${m['id']}" data-value="1"><i class="icon-leaf"></i> 条目</a>
											</td>
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