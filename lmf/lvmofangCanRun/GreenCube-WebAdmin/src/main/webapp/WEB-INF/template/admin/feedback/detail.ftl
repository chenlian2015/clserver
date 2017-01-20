[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">意见反馈 <small>反馈明细</small></h3>
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>意见反馈
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
					<div class="tab-content">
						<div class="tab-pane active" id="tab_1">
							<div class="portlet box purple">
								<div class="portlet-title">
									<div class="caption"><i class="icon-reorder"></i>意见反馈明细</div>
									<div class="tools">
										<a href="javascript:;" class="collapse"></a>
									</div>
								</div>
								<div class="portlet-body form">
									<!-- BEGIN FORM-->
									<!-- <form name="editform1" id="editform1" action="/admin/order/deliver" method="post"> -->
									<div class="form-horizontal form-view">
										<div class="row-fluid">
											<div class="span6 ">
												<div class="control-group">
													<label class="control-label" for="firstName">反馈人</label>
													<div class="controls">
														<span class="text">${advice.CUserName }</span>
													</div>
												</div>
											</div>
										</div>
										<div class="row-fluid">
											<div class="span6 ">
												<div class="control-group">
													<label class="control-label" >反馈内容</label>
													<div class="controls">
														<span class="text">${advice.CAdvice }</span>
													</div>
												</div>
											</div>
										</div>
										<div class="row-fluid">
											<div class="span6 ">
												<div class="control-group">
													<label class="control-label" >联系方式</label>
													<div class="controls">
														<span class="text">${advice.CLxfs }</span>
													</div>
												</div>
											</div>
										</div>
										<div class="row-fluid">
											<div class="span6 ">
												<div class="control-group">
													<label class="control-label" >反馈日期</label>
													<div class="controls">
														<span class="text">[@fmt.formatDate value=advice.DFeedbackTime  pattern="yyyy-MM-dd hh:mm:ss"/]</span>
													</div>
												</div>
											</div>
										</div>
									<div class="form-actions">
											<a href="/admin/feedback/list" class="btn green" >返回</a>
									</div>
									<!-- END FORM--> 
									<!-- </form> -->
								</div>
							</div>
						</div>
					</div>
		</div>
	</div>
[/@ui.page]
<script>
	$(document).ready(function(){
		DynamicTime.init("dynamictime");
	});
	
</script>