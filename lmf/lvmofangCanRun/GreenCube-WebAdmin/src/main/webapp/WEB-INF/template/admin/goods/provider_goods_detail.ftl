
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
					<h3 class="page-title">供产会商品明细</h3>
					<ul class="breadcrumb">
						<li><i class="icon-home"></i> <a href="/admin">产品管理</a> <i
							class="icon-angle-right"></i></li>
						</li>
						<li>明细</li>
					</ul>
				</div>
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="icon-reorder"></i>供产会商品详细信息</div>
							<div class="tools">
								<a href="javascript:void(0);" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body form">
						<form id="addform" class="form-horizontal">
							<div class="form-horizontal form-view">
								<h3 class="form-section">供产会商品基本信息</h3>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="firstName">供应商编号:</label>
											<div class="controls">
												<span class="text">${baseinfo.providerId }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="lastName">供应商名称:</label>
											<div class="controls">
												<span class="text">${baseinfo.providerName }</span>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="firstName">商品名称:</label>
											<div class="controls">
												<span class="text bold">${baseinfo.name }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="lastName">显示顺序:</label>
											<div class="controls">
												<span class="text">${baseinfo.order }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >原价格:</label>
											<div class="controls">
												<span class="text">${baseinfo.orginalFee }</span> 
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >实际价格:</label>
											<div class="controls">
												<span class="text">${baseinfo.realFee }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >商品分类ID:</label>
											<div class="controls">
												<span class="text">${baseinfo.categoryId }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >商品分类名称:</label>
											<div class="controls">                                                
												<span class="text">${baseinfo.categoryName }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >商品简介:</label>
											<div class="controls">
												<span class="text">${baseinfo.introduction }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >商品详细描述:</label>
											<div class="controls">
												<span class="text">${baseinfo.desc }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									
									<div class="span6">
										<div class="control-group">
											<label class="control-label" >商品图片:</label>
											<div class="controls">
												<span class="text">${baseinfo.picMain }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >服务分数:</label>
											<div class="controls">
												<span class="text">${baseinfo.serviceScope }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >所在省:</label>
											<div class="controls">
												<span class="text">${baseinfo.provinceName }</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >所在市:</label>
											<div class="controls">
												<span class="text">${baseinfo.cityName }</span>
											</div>
										</div>
									</div>
								</div>
								
							<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" > 商品服务范围 - 经度:</label>
											<div class="controls">
												<span class="text">${baseinfo.locLon }</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >商品服务范围 - 纬度:</label>
											<div class="controls">
												<span class="text">${baseinfo.locLat }</span>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >手机购买该商品的二维码:</label>
											<div class="controls">
												<span class="text">${baseinfo.qrImage }</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >创建时间:</label>
											<div class="controls">
												<span class="text">${baseinfo.createTime }</span>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >开始时间:</label>
											<div class="controls">
												<span class="text">${baseinfo.beginDate }</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >结束时间:</label>
											<div class="controls">
												<span class="text">${baseinfo.endDate }</span>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >总库存:</label>
											<div class="controls">
												<span class="text">${baseinfo.amount }</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >总销量:</label>
											<div class="controls">
												<span class="text">${baseinfo.salesCount }</span>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >商品类型:</label>
											<div class="controls">
												<span class="text">
													[#if baseinfo.type == 1]产品[/#if]
											      [#if baseinfo.type == 2]服务[/#if]
												</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >上架状态：</label>
											<div class="controls">
												<span class="text">
													[#if baseinfo.statusShelf == 0]未上架[/#if]
											      [#if baseinfo.statusShelf == 1]已上架[/#if]
												</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >审核状态:</label>
											<div class="controls">
												<span class="text">
													[#if baseinfo.statusAudit == 0]待审核[/#if]
											      [#if baseinfo.statusAudit == 1]已审核[/#if]
											      [#if baseinfo.statusAudit == -1]已驳回[/#if]
												</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >销售状态:</label>
											<div class="controls">
												<span class="text">
													[#if baseinfo.statusSale == 0]原价销售[/#if]
											      [#if baseinfo.statusSale == 1]折扣中[/#if]
											      [#if baseinfo.statusSale == 2]促销中[/#if]
												</span>
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