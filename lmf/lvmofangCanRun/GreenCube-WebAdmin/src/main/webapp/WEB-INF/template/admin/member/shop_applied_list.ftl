
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"][/@ui.plugin]
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">店主会会员申请列表</h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>待审核的店主会用户
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
									   <th>图片</th>
										<th width="60">店铺名称</th>
										<th>业务分类</th>
										<th>店铺电话</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
								<tbody>
									[#list members as m]
										<tr class="odd gradeX">
										   <td>${m["photo"] }</td>
											<td>${m["shopName"]}</td>
											<td>${m["businessCategoryName"] }</td>
											<td>${m["shopPhone"] }</td>
											<td nowrap>
											    <a href="/admin/member/shop_applied_detail?memberId=${m['id']}" class="btn yellow btnDetail mini" data-id="${m['id']}" data-value="1"><i class="icon-leaf"></i> 详情</a>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${m['id']}" data-value="1"><i class="icon-edit"></i> 通过</a>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${m['id']}" data-value="-1"><i class="icon-trash"></i> 不通过</a>
											</td>
										</tr>
									[/#list]
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
		$(".btnAudit").on("click",function(){
			var memberId = $(this).attr("data-id");
			alert(memberId);
			var auditStatus = $(this).attr("data-value");
			Submit.invok("/admin/member/shop_applied_audit?auditStatus="+auditStatus + "&memberId=" + memberId,function(data){
				if (data.success == true){
					window.location.reload(true);
				} else {
					showErrorDialog(data.message);
				}
			});				
		});
	});
</script>