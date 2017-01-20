
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"][/@ui.plugin]
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">退款审核列表</h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>退款审核
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
							<div class="caption"><i class="icon-cogs"></i>待审核退款申请</div>
							<div class="actions">
								<!--<a href="/admin/book/book_edit" class="btn blue"><i class="icon-plus"></i>&nbsp;添加</a>-->
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
										<th>退款者姓名</th>
										<th>退款方式</th>
										<th>退款金额</th>
										<th>申请时间</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
								<tbody>
										<tr class="odd gradeX">
											<td>王新</td>
											<td>微信转账</td>
											<td>10000</td>
											<td>2016-01-21 14：59：24</td>
											<td nowrap>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${m['id']}" data-value="1"><i class="icon-edit"></i> 通过</a>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${m['id']}" data-value="-1"><i class="icon-trash"></i> 不通过</a>
											</td>
										</tr>
											<tr class="odd gradeX">
											<td>易东</td>
											<td>银行卡转账</td>
											<td>9999</td>
											<td>2016-02-23 18：26：37</td>
											<td nowrap>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${m['id']}" data-value="1"><i class="icon-edit"></i> 通过</a>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${m['id']}" data-value="-1"><i class="icon-trash"></i> 不通过</a>
											</td>
										</tr>
											<tr class="odd gradeX">
											<td>胡晓光</td>
											<td>微信转账</td>
											<td>200000</td>
											<td>2016-03-08 15：53：16</td>
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