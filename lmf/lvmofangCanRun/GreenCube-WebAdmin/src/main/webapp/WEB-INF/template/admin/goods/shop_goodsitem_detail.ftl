
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
[@jqPlugin.plugin date='1'/]
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
					<h3 class="page-title">店主会商品条目明细</h3>
					<ul class="breadcrumb">
						<li><i class="icon-home"></i> <a href="/admin">产品管理</a> <i
							class="icon-angle-right"></i></li>
						</li>
						<li>明细</li>
					</ul>
				</div>
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="icon-reorder"></i>店主会商品条目详细信息</div>
							<div class="tools">
								<a href="javascript:void(0);" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body form">
						<form id="addform" class="form-horizontal">
							<div class="form-horizontal form-view">
								<h3 class="form-section">店主会商品条目基本信息</h3>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="firstName">店铺编号:</label>
											<div class="controls">
												<span class="text">${GoodsItem.shopId }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="lastName"></label>
											<div class="controls">
												<span class="text"></span>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="firstName">商品ID:</label>
											<div class="controls">
												<span class="text bold">${GoodsItem.shopGoodsId }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="lastName">商品名称:</label>
											<div class="controls">
												<span class="text bold">${GoodsItem.shopGoodsName }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >店铺设定的商品原价格:</label>
											<div class="controls">
												<span class="text">${GoodsItem.shopOrginalFee }</span> 
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >店铺设定的商品实际价格:</label>
											<div class="controls">
												<span class="text">${GoodsItem.shopRealFee }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >供应商提供的商品ID：</label>
											<div class="controls">
												<span class="text">${GoodsItem.providerGoodsId }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >供应商提供的商品名称:</label>
											<div class="controls">                                                
												<span class="text">${GoodsItem.providerGoodsName }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >供应商提供的商品图片:</label>
											<div class="controls">
												<span class="text">${GoodsItem.providerGoodsImage }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="coJTA 中的UserTransactionntrol-label" ></label>
											<div class="controls">
												<span class="text"></span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									
									<div class="span6">
										<div class="control-group">
											<label class="control-label" >供应商设定的商品原价格:</label>
											<div class="controls">
												<span class="text">${GoodsItem.providerOrginalFee }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >供应商设定的商品实际价格:</label>
											<div class="controls">
												<span class="text">${GoodsItem.providerRealFee }</span>
											</div>JTA 中的UserTransaction
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >商品类型:</label>
											<div class="controls">
												<span class="text">
													[#if GoodsItem.type == 1]产品[/#if]
											      [#if GoodsItem.type == 2]服务[/#if]
												</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >排序:</label>
											<div class="controls">
												<span class="text">${GoodsItem.order }</span>
											</div>
										</div>
									</div>
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
		DynamicTime.init("dynamictime");
	});
</script>