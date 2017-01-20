[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"/]
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">支付方式管理 <small>当前所有支付方式</small></h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>支付方式列表 
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
							<div class="caption"><i class="icon-cogs"></i>当前系统中所有支付方式</div>
							<div class="actions">
								<a href="/admin/payment/method/method_edit" class="btn blue"><i class="icon-plus"></i>&nbsp;添加支付方式</a>
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
										<th>图标</th>
										<th>支付方式</th>
										<th>标识</th>
										<th>顺序</th>
										<th>备注</th>
										<th>是否已启用</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
								<tbody>
									<!-- 合计行 -->
									[#list methods as m]
										<tr class="odd gradeX">
											<td><img src="${m.appLogo1 }" style="width:180px;height:50px;"></td>
											<td>${m.name }</td>
											<td>${m.indefier }</td>
											<td>${m.order }</td>
											<td>${m.desc }</td>
											<td>
												[#if m.enable == 1]
													<span class="label label-success">已发布</span>
												[#else]
													<span class="label label-important">未发布</span>
												[/#if]
											</td>
											<td nowrap>
												<a href="/admin/payment/method/method_edit?id=${m.id }" class="btn green mini"><i class="icon-edit"></i> 修改</a>
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
				Submit.invok("/admin/payment/method/method_delete?id="+selId,function(){
					window.location.reload(true);
				});				
			}
		});
	});
</script>