
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
					<h3 class="page-title">供产会会员明细</h3>
					<ul class="breadcrumb">
						<li><i class="icon-home"></i> <a href="/admin">会员管理</a> <i
							class="icon-angle-right"></i></li>
						</li>
						<li>明细</li>
					</ul>
				</div>
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="icon-reorder"></i>供产会会员详细信息</div>
							<div class="tools">
								<a href="javascript:void(0);" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body form">
						<form id="addform" class="form-horizontal">
							<div class="form-horizontal form-view">
								<h3 class="form-section">供产会会员基本信息</h3>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="firstName">供产会名称:</label>
											<div class="controls">
												<span class="text">${baseinfo.providerName }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="lastName">店铺首图:</label>
											<div class="controls">
												<span class="text">${baseinfo.photo }</span>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="firstName">店铺编码:</label>
											<div class="controls">
												<span class="text">${baseinfo.memberCode }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="lastName">店铺电话:</label>
											<div class="controls">
												<span class="text bold">${baseinfo.memberCode }</span>
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
											<label class="control-label" >定位-精度:</label>
											<div class="controls">
												<span class="text">${baseinfo.locLon }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >定位-纬度:</label>
											<div class="controls">                                                
												<span class="text">${baseinfo.locLat }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >业务分类:</label>
											<div class="controls">
												<span class="text">${baseinfo.businessCategoryName }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >店铺评级:</label>
											<div class="controls">
												<span class="text">${baseinfo.level }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									
									<div class="span6">
										<div class="control-group">
											<label class="control-label" >关注的人:</label>
											<div class="controls">
												<span class="text">${baseinfo.attentionMembers }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >待售商品总数目:</label>
											<div class="controls">
												<span class="text">${baseinfo.goodsAmountOnSale }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >总销售数:</label>
											<div class="controls">
												<span class="text">${baseinfo.salesAmount }</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >总赞数:</label>
											<div class="controls">
												<span class="text">${baseinfo.praiseAmount }</span>
											</div>
										</div>
									</div>
								</div>
								
							<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >总粉丝数:</label>
											<div class="controls">
												<span class="text">${baseinfo.fansAmount }</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >总关注数:</label>
											<div class="controls">
												<span class="text">${baseinfo.focusAmount }</span>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >评价次数:</label>
											<div class="controls">
												<span class="text">${baseinfo.commentAmount }</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >店铺总评分:</label>
											<div class="controls">
												<span class="text">${baseinfo.commentScore }</span>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >累计消费金额:</label>
											<div class="controls">
												<span class="text">${baseinfo.totalConsume }</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >审核状态:</label>
											<div class="controls">
												<span class="text">
												   [#if baseinfo.isAudit == 0]待审核[/#if]
											      [#if baseinfo.isAudit == 1]已审核[/#if]
											      [#if baseinfo.isAudit == -1]已驳回[/#if]
												</span>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >是否有效:</label>
											<div class="controls">
												<span class="text">
													[#if baseinfo.isValid == 0]无效[/#if]
											      [#if baseinfo.isValid == 1]有效[/#if]
												</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >是否上架:</label>
											<div class="controls">
												<span class="text">
												   [#if baseinfo.isFrontShow == 0]未上架[/#if]
											      [#if baseinfo.isFrontShow == 1]已上架[/#if]
												</span>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >是否推荐店铺:</label>
											<div class="controls">
												<span class="text">
												 [#if baseinfo.isRecommend == 0]否[/#if]
											    [#if baseinfo.isRecommend == 1]是[/#if]
												</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" ></label>
											<div class="controls">
												<span class="text"></span>
											</div>
										</div>
									</div>
								</div>
								
								
							</div>
							
							<div class="form-horizontal form-view">
								<h3 class="form-section">供产会会员隐私信息</h3>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="firstName">姓名:</label>
											<div class="controls">
												<span class="text">${member.ownerName }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="lastName">性别:</label>
											<div class="controls">
												<span class="text">${member.ownerSex }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >银行类别:</label>
											<div class="controls">
												<span class="text">${member.bankTypeName }</span> 
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >银行名称:</label>
											<div class="controls">
												<span class="text bold">${member.bankName }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >持卡人:</label>
											<div class="controls">
												<span class="text bold">${member.cardHolderName }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >卡号:</label>
											<div class="controls">                                                
												<span class="text bold">${member.cardNum }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >持卡人电话:</label>
											<div class="controls">
												<span class="text">${member.cardHolderPhone }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >地址:</label>
											<div class="controls">
												<span class="text">${member.address }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									
									<div class="span6">
										<div class="control-group">
											<label class="control-label" >邮编:</label>
											<div class="controls">
												<span class="text">${member.zipCode }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >注册时间:</label>
											<div class="controls">
												<span class="text">${member.registTime }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >证件类型:</label>
											<div class="controls">
												<span class="text">${member.identityType }</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >证件号码:</label>
											<div class="controls">
												<span class="text">${member.identityNum }</span>
											</div>
										</div>
									</div>
								</div>
								
									<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" ></label>
											<div class="controls">
												<span class="text"></span>
											</div>
										</div>
									</div>
									<!--/span-->
									
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