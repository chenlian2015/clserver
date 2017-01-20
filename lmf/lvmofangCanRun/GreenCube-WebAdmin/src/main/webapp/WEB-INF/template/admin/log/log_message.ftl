[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"][/@ui.plugin]
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">日志管理 <small>错误日志列表</small></h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>错误日志 
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
							<div class="caption"><i class="icon-cogs"></i>错误日志概览</div>
							<div class="actions">
								<!-- <div class="btn-group">
									<a class="btn green" href="#" data-toggle="dropdown" style="width:142px;">
										<i class="icon-search"></i> 条件查询
										<i class="icon-angle-down"></i>
									</a>
									<ul class="dropdown-menu pull-right" style="width:50px;">
										<li><a href="DAY" class="btn searchByDay">查询今天</a></li>
										<li><a href="WEEK" class="btn searchByWeek">查询本周</a></li>
										<li><a href="MONTH" class="btn searchByMonth">查询本月</a></li>
										<li><a href="" class="btn searchAll"></i>查询全部</a></li>
									</ul>
								</div> -->
							</div>
						</div>
						<div class="portlet-body" style="min-height: 400px;">
							<!-- <div class="dataTables_filter" id="sample_2_filter">
								<form action="">
									<label style="float:right;margin-right:20px;"><input type="text" name="key" placeholder="请输入关键字..." style="min-width:100px;" class="m-wrap "><a href="#" class="btn blue" ><i class="icon-search"></i>查询</a></label>
								</form>
							</div> -->
							<table class="table table-striped table-bordered table-hover" id="sample_3" >
								<thead>
									<tr>
										<th width="140px" nowrap>日志生成时间</th>
										<th>日志事件</th>
									</tr>
								</thead>
								<tbody>
									<!-- 合计行 -->
									[#list lm as lm]
										<tr class="odd gradeX">
											<td>[@fmt.formatDate value=lm.DLogTime  pattern="yyyy-MM-dd hh:mm:ss"  /]</td>
											<td>${lm.CError }</td>
										</tr>
									[/#list]
								</tbody>
							</table>
							<div id="pagesplit"></div>
							[@ui.pagesplit link="/admin/log/log_message_list" renderTo="pagesplit" /]
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
		$(".dropdown-menu a").on("click",function(){
			var param = $(this).attr("href");
			window.location.assign("/admin/log/log_message_list?cxfs="+param);
			return false;
		});
	});
</script>