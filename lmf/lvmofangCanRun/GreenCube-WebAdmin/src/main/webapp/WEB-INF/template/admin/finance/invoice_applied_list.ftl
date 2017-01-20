
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"][/@ui.plugin]
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">发票审核列表</h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>发票审核
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
							<div class="caption"><i class="icon-cogs"></i>待审核发票申请</div>
							<div class="actions">
								<!--<a href="/admin/book/book_edit" class="btn blue"><i class="icon-plus"></i>&nbsp;添加</a>-->
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
									   <th>发票类型</th>
										<th>发票抬头</th>
										<th>抬头类别</th>
										<th>地址</th>
										<th>邮编</th>
										<th>电话</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
								<tbody>
										<tr class="odd gradeX">
											<td>机打发票</td>
											<td>北京恒江联盟科技有限公司</td>
											<td>单位</td>
											<td>北京市丰台区</td>
											<td>100000</td>
											<td>15836462029</td>
											<td nowrap>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${m['id']}" data-value="1"><i class="icon-edit"></i> 通过</a>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${m['id']}" data-value="-1"><i class="icon-trash"></i> 不通过</a>
											</td>
										</tr>
										<tr class="odd gradeX">
											<td>增值税发票</td>
											<td>呼啸光</td>
											<td>个人</td>
											<td>北京市丰台区</td>
											<td>100000</td>
											<td>15527436389</td>
											<td nowrap>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${m['id']}" data-value="1"><i class="icon-edit"></i> 通过</a>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${m['id']}" data-value="-1"><i class="icon-trash"></i> 不通过</a>
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
		//审核
		$(".btnAudit").on("click",function(){
			var goodsId = $(this).attr("data-id");
			var auditStatus = $(this).attr("data-value");
			Submit.invok("/admin/goods/provider_goods_audit?auditStatus="+auditStatus + "&goodsId=" + goodsId,function(data){
				if (data.success == true){
					window.location.reload(true);
				} else {
					showErrorDialog(data.message);
				}
			});				
		});
	
		
	});
</script>