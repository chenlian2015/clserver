[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"/]
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">会费类别管理 <small>当前所有费用类别</small></h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>费用类别列表 
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
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="icon-cogs"></i>当前系统中所有费用类别</div>
							<div class="actions">
								<a href="/admin/fee_category/category_edit" class="btn blue"><i class="icon-plus"></i>&nbsp;添加费用类别</a>
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
										<th>名称</th>
										<th>金额</th>
										<th>适用用户群</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
								<tbody>
									<!-- 合计行 -->
									[#list categories as m]
										<tr class="odd gradeX">
											<td>${m.name }</td>
											<td>${m.fee /100 }</td>
											<td>
												[#if m.suitUserType == 4096]
													<span class="label label-success">店主会</span>
												[#elseif m.suitUserType == 256]
													<span class="label label-important">供产会</span>
												[#elseif m.suitUserType == 16]
													<span class="label label-important">健康会</span>
												[#elseif m.suitUserType == 4369]
													<span class="label label-important">所有会员</span>
												[/#if]
											</td>
											<td nowrap>
												<a href="/admin/fee_category/category_edit?id=${m.id }" class="btn green mini"><i class="icon-edit"></i> 修改</a>
												<a href="javascript:void(0);" data-cid="${m.id }" class="btn red bdelete mini"><i class="icon-trash"></i> 删除</a>
											</td>
										</tr>
									[/#list]
								</tbody>
							</table>
							<div id="pagesplit"></div>
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
		DynamicTime.init("dynamictime");
		$(".bdelete").on("click",function(){
			if (window.confirm("您确定要删除该支付方式吗？")){
				var selId = $(this).attr("data-cid");
				Submit.invok("/admin/fee_category/category_delete?id="+selId,function(){
					window.location.reload(true);
				});				
			}
		});
	});
</script>