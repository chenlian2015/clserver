
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
					<h3 class="page-title">健康会会员明细</h3>
					<ul class="breadcrumb">
						<li><i class="icon-home"></i> <a href="/admin">会员管理</a> <i
							class="icon-angle-right"></i></li>
						</li>
						<li>明细</li>
					</ul>
				</div>
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="icon-reorder"></i>健康会会员详细信息</div>
							<div class="tools">
								<a href="javascript:void(0);" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body form">
						<form id="addform" class="form-horizontal">
						
						   <div class="form-horizontal form-view">
								<h3 class="form-section">健康会会员基本信息</h3>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="firstName">用户id:</label>
											<div class="controls">
												<span class="text">${baseinfo.userid }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="lastName">姓名:</label>
											<div class="controls">
												<span class="text">${baseinfo.name }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="firstName">用户编码:</label>
											<div class="controls">
												<span class="text">${baseinfo.memberCode }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="lastName">关注的人:</label>
											<div class="controls">
												<span class="text">${baseinfo.attentionMembers }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="firstName">所在省:</label>
											<div class="controls">
												<span class="text">${baseinfo.provinceName }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="lastName">所在市:</label>
											<div class="controls">
												<span class="text">${baseinfo.fansAmount }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="firstName">总关注数:</label>
											<div class="controls">
												<span class="text">${baseinfo.focusAmount }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="lastName">总粉丝数:</label>
											<div class="controls">
												<span class="text">${baseinfo.fansAmount }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="firstName">评价次数:</label>
											<div class="controls">
												<span class="text">${baseinfo.commentAmount }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="lastName">累计消费金额:</label>
											<div class="controls">
												<span class="text">${baseinfo.totalConsume }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="firstName">审核状态:</label>
											<div class="controls">
												<span class="text">
													[#if baseinfo.isAudit == 0]待审核[/#if]
											      [#if baseinfo.isAudit == 1]已审核[/#if]
											      [#if baseinfo.isAudit == -1]已驳回[/#if]
												</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="lastName">是否有效:</label>
											<div class="controls">
												<span class="text">
												   [#if baseinfo.isValid == 0]无效[/#if]
											      [#if baseinfo.isValid == 1]有效[/#if]
												</span>
											</div>
										</div>
									</div>
								</div>
								
							</div>
							
							<div class="form-horizontal form-view">
								<h3 class="form-section">健康会会员隐私信息</h3>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="firstName">健康会会员标识:</label>
											<div class="controls">
												<span class="text">${member.healthyId }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="lastName">性别:</label>
											<div class="controls">
												<span class="text">${member.sex }</span>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >通信地址:</label>
											<div class="controls">
												<span class="text">${member.address }</span> 
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >邮编:</label>
											<div class="controls">
												<span class="text bold">${member.zipCode }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >证件类型:</label>
											<div class="controls">
												<span class="text bold">${member.identityType }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >证件号码:</label>
											<div class="controls">                                                
												<span class="text bold">${member.identityNum }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >注册时间:</label>
											<div class="controls">
												<span class="text">${member.registTime }</span>
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