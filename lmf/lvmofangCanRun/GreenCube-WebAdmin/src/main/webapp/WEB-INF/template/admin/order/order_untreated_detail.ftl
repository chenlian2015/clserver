
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
<style type="text/css">
	#myModal {
		top:300px;
	}
	#reviewback{
		top:300px;
		z-index:1;
	}

</style>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
				<div>
					<h3 class="page-title">订单管理</h3>
					<ul class="breadcrumb">
						<li><i class="icon-home"></i> <a href="/admin">首页</a> <i
							class="icon-angle-right"></i></li>
						<li>订单管理<i class="icon-angle-right"></i>
						</li>
						<li>订单处理</li>
					</ul>
				</div>
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="icon-reorder"></i>订单详情</div>
							<div class="tools">
								<a href="javascript:void(0);" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body form">
						<form id="addform" class="form-horizontal">
							<div class="form-horizontal form-view">
								<h3 class="form-section">订单详情</h3>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="firstName">订单编号:</label>
											<div class="controls">
												<span class="text">${o.COrderNum }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >用户手机号码:</label>
											<div class="controls">
												<span class="text">${o.CMobile }</span> 
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >是否需要发票:</label>
											<div class="controls">
												<span class="text bold">${o.NNeedInvoice }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >订单日期:</label>
											<div class="controls">
												<span class="text">${o.DOrderTime }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >备注:</label>
											<div class="controls">
												<span class="text">${o.CRemark }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="form-actions">
									<a href="javascript:void(0);" data-id="${o.id }" id="distribute" data-toggle="modal"  class="btn blue"><i class="icon-pencil"></i>派单</a>
									<a href="javascript:void(0);" data-id="${o.id }" id="back" data-toggle="modal" class="btn"><i class="icon-back"></i>退回</a>
								</div>
							</div>
							</form>
							<!-- END FORM-->  
						</div>
					</div>
				</div>
			</div>
		</div>
	[/@ui.page]
<script>
	$(document).ready(function (){
		
		$("#distribute").on("click",function(){
			var cid = $(this).attr("data-id");
			 Submit.invok("/admin/order/order_start?orderId="+cid, function() {
				window.location.assign("/admin/order/order_untreated_list");
				alert("派单成功！");
			});
		});
		$("#back").on("click",function(){
			var cid = $(this).attr("data-id");
			 Submit.invok("/admin/order/order_goods_back?orderId="+cid, function() {
				window.location.assign("/admin/order/order_untreated_list");
				alert("退货成功！");
			});
			
		});
		
	});
</script>