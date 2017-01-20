
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"][/@ui.plugin]
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">健康会会员申请列表</h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>待审核的健康会用户
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
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="icon-cogs"></i>待审核会员</div>
							<div class="actions">
									<!--<a href="/admin/book/book_edit" class="btn blue"><i class="icon-plus"></i>&nbsp;添加</a>-->
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
										<th>姓名</th>
										<th>累计消费金额</th>
										<th>是否有效</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
								
								<tbody>
									[#list members as m]
										<tr class="odd gradeX">
											<td>${m["name"]}</td>
											<td>${m["commentAmount"] }</td>
											<td>
											[#if m["isValid"] == 0]无效[/#if]
											[#if m["isValid"] == 1]有效[/#if]
											</td>
											<td nowrap>
												 <a href="/admin/member/healthy_applied_detail?memberId=${m['id']}" class="btn yellow btnDetail mini" data-id="${m['id']}" data-value="1"><i class="icon-leaf"></i> 详情</a>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${m['id']}" data-value="1"><i class="icon-edit"></i> 通过</a>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${m['id']}" data-value="-1"><i class="icon-trash"></i> 不通过</a>
											</td>
										</tr>
									[/#list]
								</tbody>
								
								<!--<tbody>
										<tr class="odd gradeX">
											<td>10242942354</td>
											<td>河北省</td>
											<td>保定市</td>
											<td>305</td>
											<td>305</td>
											<td>800</td>
											<td>10456.00</td>
											<td>未通过</td>
											<td>有效</td>
											<td nowrap>
												 <a href="/admin/member/healthy_applied_detail?memberId=${m['id']}" class="btn yellow btnDetail mini" data-id="${m['id']}" data-value="1"><i class="icon-leaf"></i> 详情</a>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${m['id']}" data-value="1"><i class="icon-edit"></i> 通过</a>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${m['id']}" data-value="-1"><i class="icon-trash"></i> 不通过</a>
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
		$(".btnAudit").on("click",function(){
			var memberId = $(this).attr("data-id");
			alert(memberId);
			var auditStatus = $(this).attr("data-value");
			Submit.invok("/admin/member/healthy_applied_audit?auditStatus="+auditStatus + "&memberId=" + memberId,function(data){
				if (data.success == true){
					window.location.reload(true);
				} else {
					showErrorDialog(data.message);
				}
			});				
		});
	});
</script>