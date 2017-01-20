
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"][/@ui.plugin]
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">培训管理</h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>试卷列表
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
					<!-- 试卷列表 -->
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="icon-cogs"></i>试卷列表</div>
							<div class="actions">
									<a href="/admin/member/train_add" class="btn blue"><i class="icon-plus"></i>&nbsp;添加试卷</a>
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
										<th>试卷名称</th>
										<th>创建时间</th>
										<th>描述</th>
										<th>试卷类型</th>
										<th>题目总数</th>
										<th>试卷类别</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
								<!--<tbody>
									[#list members as m]
										<tr class="odd gradeX">
											<td>${m["name"]}</td>
											<td>${m["createTime"] }</td>
											<td>${m["desc"]}</td>
											<td>${m["composeMethod"] }</td>
											<td>${m["topicCount"]}</td>
											<td>${m["categoryName"] }</td>
											<td nowrap>
												<a href="/admin/member/healthy_applied_detail?memberId=${m['id']}" class="btn yellow btnDetail mini" data-id="${m['id']}" data-value="1"><i class="icon-leaf"></i> 题目列表</a>
											</td>
										</tr>
									[/#list]
								</tbody>-->
								
								<tbody>
										<tr class="odd gradeX">
											<td>共产会通关考试</td>
											<td>2016-02-17 14：00：08</td>
											<td>共产会申请入会通关考试</td>
											<td>普通组卷</td>
											<td>10</td>
											<td>共产会</td>
											<td nowrap>
												<a href="/admin/member/train_question_list?memberId=${m['id']}" class="btn yellow btnDetail mini" data-id="${m['id']}" data-value="1"><i class="icon-leaf"></i> 题目列表</a>
											</td>
										</tr>
										<tr class="odd gradeX">
											<td>店主会通关考试</td>
											<td>2016-02-17 14：00：08</td>
											<td>店主会申请入会通关考试</td>
											<td>智能组卷</td>
											<td>10</td>
											<td>店主会</td>
											<td nowrap>
												<a href="/admin/member/train_question_list?memberId=${m['id']}" class="btn yellow btnDetail mini" data-id="${m['id']}" data-value="1"><i class="icon-leaf"></i> 题目列表</a>
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
</script>