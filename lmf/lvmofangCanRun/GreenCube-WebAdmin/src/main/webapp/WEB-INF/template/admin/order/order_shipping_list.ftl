[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]


	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">
					订单管理 <small>已签收订单列表</small>
				</h3>

				<ul class="breadcrumb">
					<li><i class="icon-home"></i>订单列表</li>
					<li class="pull-right no-text-shadow">
						<div id="dashboard-report-range"
							class="dashboard-date-range responsive" style="display: block;">
							<i class="icon-calendar"></i> <span id="dynamictime"></span>
						</div>
					</li>
				</ul>
			</div>
		</div>

		<div id="dashboard">
			<div class="row-fluid">
				<div class="span12" responsive" data-tablet="span12 fix-offset"
					data-desktop="span6">
					<!-- 居民物业费缴纳表-->
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-cogs"></i>已签收订单概览
							</div>
							<div class="actions">
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover"
								id="sample_3">
								<thead>
									<tr>
										<th>购书者姓名</th>
										<th>订单编号</th>
										<th>购书者手机号码</th>
										<th>是否需要发票</th>
										<th>下单时间</th>
										<th>备注</th>
										<th>状态</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
								<tbody>
									<!-- 合计行 -->
									[#list order_receive_list as o]
										<tr class="odd gradeX">
											<td>${o.CUserName }</td>
											<td>${o.COrderNum }</td>
											<td>${o.CMobile }</td>
											<td>[#if o.NNeedInvoice == 0]
												是
											[#else]
												否
											[/#if]</td>
											<td>[@fmt.formatDate value=o.DOrderTime  pattern="yyyy-MM-dd" /]</td>
											<td>${o.CRemark }</td>
											<td>[#if o.NStatus ==0 ]
													未处理订单
												[#elseif o.NStatus ==1]
													处理中订单
												[#elseif o.NStatus ==2]
													已发货
												[#elseif o.NStatus ==3]
													已接收
												[#elseif o.NStatus ==4]
													结单
												[/#if]</td>
											<td nowrap>
												<a href="javascript:void(0);" data-id="${o.id }" class="btn green mini"><i class="icon-edit"></i>签收</a> 
												<%-- <a href="javascript:void(0);" data-cid="${b.id }" class="btn red bdelete mini"><i class="icon-trash"></i>删除</a> --%>
											</td>
										</tr>
										[/#list]
								</tbody>
							</table>
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
		$(".green").on("click",function(){
			var cid = $(this).attr("data-id");
			Submit.invok("/admin/order/order_sign?orderId="
					+ cid, function() {
				window.location.reload("/admin/order/order_receive_list");
				alert("签收成功！");
			});
			
		});
		
	});
</script>