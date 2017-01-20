
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"][/@ui.plugin]
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">注册用户会员概览</h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>会员概览
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
					<!-- 会员概览-->
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="icon-cogs"></i>会员概览</div>
							<div class="actions">
									<!--<a href="/admin/book/book_edit" class="btn blue"><i class="icon-plus"></i>&nbsp;添加</a>-->
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
									   <th>照片</th>
										<th width="60">姓名</th>
										<th>昵称</th>
										<th>上次登录时间</th>
										<th>用户类型</th>
										<th>手机</th>
										<th>邮箱</th>
										<th>是否有效</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
									<tbody>
									<!-- 合计行 -->
									[#list members as m]
										<tr class="odd gradeX">
											<td>${m["photo"] }</td>
											<td>${m["name"]}</td>
											<td>${m["nickname"] }</td>
											<td>${m["lastLoginTime"] }</td>
											<td>${m["userType"] }</td>
											<td>${m["mobile"] }</td>
											<td>${m["email"] }</td>
											<td>
											[#if m["valid"] == 0]无效[/#if]
											[#if m["valid"] == 1]有效[/#if]
											</td>
											<td nowrap>
											    <a href="/admin/member/user_detail?memberId=${m['id']}" class="btn yellow btnDetail mini" data-id="${m['id']}" data-value="1"><i class="icon-leaf"></i> 详情</a>
											</td>
										</tr>
										[/#list]
								</tbody>
								<!--<tbody>
										<tr class="odd gradeX">
											<td>123</td>
											<td>苏丹</td>
											<td>核定红</td>
											<td>2016-02-16 15：23：34</td>
											<td>普通用户</td>
											<td>15324628743</td>
											<td>sdgj@163.com</td>
											<td>是</td>
											<td nowrap>
											    <a href="/admin/member/user_detail?memberId=${m['id']}" class="btn yellow btnDetail mini" data-id="${m['id']}" data-value="1"><i class="icon-leaf"></i> 详情</a>
											</td>
										</tr>
								</tbody>-->
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