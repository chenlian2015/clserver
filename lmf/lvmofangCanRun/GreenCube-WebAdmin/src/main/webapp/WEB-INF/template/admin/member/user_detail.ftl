
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
					<h3 class="page-title">注册用户会员明细</h3>
					<ul class="breadcrumb">
						<li><i class="icon-home"></i> <a href="/admin">会员管理</a> <i
							class="icon-angle-right"></i></li>
						</li>
						<li>明细</li>
					</ul>
				</div>
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption"><i class="icon-reorder"></i>注册用户会员详细信息</div>
							<div class="tools">
								<a href="javascript:void(0);" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body form">
						<form id="addform" class="form-horizontal">
							<div class="form-horizontal form-view">
								<h3 class="form-section">注册用户会员基本信息</h3>
								
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="firstName">姓名:</label>
											<div class="controls">
												<span class="text">${baseinfo.name }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" for="lastName">昵称:</label>
											<div class="controls">
												<span class="text">${baseinfo.name }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >照片:</label>
											<div class="controls">
												<span class="text">${baseinfo.photo }</span> 
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >最近登录时间:</label>
											<div class="controls">
												<span class="text bold">${baseinfo.lastLoginTime }</span>
											</div>
										</div>
									</div>
								</div>
									<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >手机号:</label>
											<div class="controls">
												<span class="text bold">${baseinfo.mobile }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >邮箱:</label>
											<div class="controls">
												<span class="text">${baseinfo.email }</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >QQ登录用户的唯一标识:</label>
											<div class="controls">
												<span class="text">${baseinfo.qqOpenId }</span>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label" >是否有效:</label>
											<div class="controls">                                                
												<span class="text">
													[#if baseinfo.valid == 0]无效[/#if]
											      [#if baseinfo.valid == 1]有效[/#if]
												</span>
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