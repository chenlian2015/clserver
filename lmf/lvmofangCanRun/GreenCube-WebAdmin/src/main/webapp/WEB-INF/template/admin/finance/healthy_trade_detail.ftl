
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
				<h3 class="page-title">健康会交易明细</h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>交易明细
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
			<div class="row-fluid">
				<div class="span12" responsive" data-tablet="span12 fix-offset" data-desktop="span6">
					<!-- 交易明细-->
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="icon-cogs"></i>交易明细</div>
							<div class="actions">
									<!--<a href="/admin/book/book_edit" class="btn blue"><i class="icon-plus"></i>&nbsp;添加</a>-->
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
										<th>健康会成员名</th>
										<th>交易金额</th>
										<th>交易时间</th>
										<th>交易状态</th>
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
											<td>李红</td>
											<td>10000</td>
											<td>2016-01-23 15：43：22</td>
											<td>成功</td>
										</tr>
										<tr class="odd gradeX">
											<td>行刚</td>
											<td>66666</td>
											<td>2016-02-14 15：43：22</td>
											<td>成功</td>
										</tr>
										<tr class="odd gradeX">
											<td>于蕊</td>
											<td>99999</td>
											<td>2016-03-16 15：43：22</td>
											<td>成功</td>
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