
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"][/@ui.plugin]
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">供产仓库产品审核</h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>待审核的共产会商品
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
							<div class="caption"><i class="icon-cogs"></i>待审核商品</div>
							<div class="actions">
									<!--<a href="/admin/book/book_edit" class="btn blue"><i class="icon-plus"></i>&nbsp;添加</a>-->
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
										<th width="60">商品图片</th>
										<th>供应商名称</th>
										<th>商品名称</th>
										<th>原价</th>
										<th>实际价格</th>
										<th>商品分类名称</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
								
								<tbody>
									[#list goods as g]
										<tr class="odd gradeX">
										   <td>${g["picMain"]}</td>
											<td>${g["providerName"] }</td>
											<td>${g["name"]}</td>
											<td>${g["orginalFee"] }</td>
											<td>${g["realFee"]}</td>
											<td>${g["categoryName"] }</td>
											<td nowrap>
											   <a href="/admin/goods/provider_goods_detail?goodsId=${g['id']}" class="btn yellow btnDetail mini" data-id="${g['id']}" data-value="1"><i class="icon-leaf"></i> 详情</a>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${g['id']}" data-value="1"><i class="icon-edit"></i> 通过</a>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${g['id']}" data-value="-1"><i class="icon-trash"></i> 不通过</a>
											</td>
										</tr>
									[/#list]
								</tbody>
								
								<!--<tbody>
										<tr class="odd gradeX">
											<td>暂时没有</td>
											<td>北京301医院</td>
											<td>关爱口腔</td>
											<td>1200</td>
											<td>1500</td>
											<td>挂号</td>
											<td nowrap>
												<a href="/admin/goods/provider_goods_detail?memberId=${g['id']}" class="btn yellow btnDetail mini" data-id="${g['id']}" data-value="1"><i class="icon-leaf"></i> 详情</a>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${g['id']}" data-value="1"><i class="icon-edit"></i> 通过</a>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${g['id']}" data-value="-1"><i class="icon-trash"></i> 不通过</a>
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
			var goodsId = $(this).attr("data-id");
			alert(goodsId);
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