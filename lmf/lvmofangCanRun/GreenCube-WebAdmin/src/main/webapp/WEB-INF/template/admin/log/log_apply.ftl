[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/bootstrap-tree.css" />
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div>
						<h3 class="page-title">申领题库账号管理</h3>
					</div>
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-comments"></i>账号列表
							</div>
							<div class="tools">
								<a href="javascript:void(0);" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body fuelux">
						<div class="clearfix">
								<table class="table table-striped table-hover table-bordered">
									<thead>
										<tr>
											<th class="span3">申请信息</th>
											<th class="span3">IP地址</th>
											<th class="span3">申请日期</th>
										</tr>
									</thead>
									<tbody>
										[#list logApplylist as c]
											<tr>
												<td>${c.CMessage}</td>
												<td>${c.CIp}</td>
												<td>[@fmt.formatDate value=c.DLogDate type="both" pattern="yyyy/MM/dd HH:mm:ss"/]</td>
											</tr>
										[/#list]
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	[/@ui.page]
