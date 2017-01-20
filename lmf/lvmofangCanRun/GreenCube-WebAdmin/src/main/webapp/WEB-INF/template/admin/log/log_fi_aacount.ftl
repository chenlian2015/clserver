[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"][/@ui.plugin]
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">日志管理 <small>资金账户交易列表</small></h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>资金账户交易 
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
					<!-- 居民物业费缴纳表-->
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="icon-cogs"></i>资金账户交易概览</div>
							<div class="actions">
								<div class="btn-group">
									<a class="btn green" href="#" data-toggle="dropdown" style="width:142px;">
										<i class="icon-search"></i> 条件查询
										<i class="icon-angle-down"></i>
									</a>
									<ul class="dropdown-menu pull-right" style="width:50px;">
										<li><a href="DAY" class="btn searchByDay">查询今天</a></li>
										<li><a href="WEEK" class="btn searchByWeek">查询本周</a></li>
										<li><a href="MONTH" class="btn searchByMonth">查询本月</a></li>
										<li><a href="ALL" class="btn searchAll"></i>查询全部</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div class="portlet-body" style="min-height: 400px;">
							<table class="table table-striped table-bordered table-hover" id="sample_3" >
								<thead>
									<tr>
										<th>用户名</th>
										<th>交易类型</th>
										<th>交易方式</th>
										<th>交易金额</th>
										<th>交易时间</th>
									</tr>
								</thead>
								<tbody>
									<!-- 合计行 -->
									[#list fa as fa]
										<tr class="odd gradeX">
											<td>${fa.CUserName }</td>
											<td>${fa.NTransMethod.value }</td>
											<td>${fa.CTransMethodDetail }</td>
											<td>${fa.NFee }</td>
											<td>[@fmt.formatDate value=fa.DTransTime  pattern="yyyy-MM-dd hh:mm:ss"  /]</td>
										</tr>
									[/#list]
								</tbody>
							</table>
							<div id="pagesplit"></div>
							[@ui.pagesplit link="/admin/log/log_fi_account" renderTo="pagesplit" /]
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
	
</script>