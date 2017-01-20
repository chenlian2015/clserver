[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"/]
	<style>
		.withDrawDetail td{
			background-color: #F6F29D !important;
		}	
      }
	</style>
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
										<th>申请者</th>
										<th>银行卡号</th>
										<th>申请转账金额</th>
										<th>申请时间</th>
										<th>是否已启用</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
								<tbody>
									<!-- 合计行 -->
									[#list withdraws as m]
										<tr class="odd gradeX" data-id="${m.id}"  >
											<td>${m.userName}</td>
											<td>${m.bankId}</td>
											<td>${m.amount}</td>
											<td>[@fmt.formatDate value=m.applyTime pattern="yyyy-MM-dd HH:mm:ss" /]</td>
											<td>
												[#if m.status ==1]已经申请，待审核[/#if]
												[#if m.status ==2]已转账[/#if]
												[#if m.status ==3]驳回转账[/#if]
											</td>
											<td nowrap>
												<a href="javascript:void(0);" class="btn green btnOk mini" data-id="${m.id}" data-status="${m.status}"><i class="icon-edit"></i> 通过</a>
												<a href="javascript:void(0);" class="btn green btnReject mini" data-id="${m.id}"><i class="icon-trash"></i> 驳回</a>
											</td>
										</tr>
										
									[/#list]
								</tbody>
							</table>
							<div id="pagesplit" style="clear:both;"></div>
							[@ui.pagesplit link="/admin/cms/withdraw_list?columnId=${columnId}" renderTo="pagesplit" /]
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
		$(".btnOk").on("click",function(){
			if (window.confirm("确定要通过该提现申请吗?")){
				var selId = $(this).attr("data-id");
				var selStatus = $(this).attr("data-status");
				Submit.invok("/admin/withdraw/withdraw_approve?id="+selId+"&status="+selStatus,function(result){
					window.location.reload(true);
				});				
			}
		});
		
		
		$(".btnReject").on("click",function(){
			if (window.confirm("确定要退回该提现申请吗？")){
				var selId = $(this).attr("data-id");
				Submit.invok("/admin/withdraw/withdraw_reject?id="+selId,function(result){
					if (result.success == false) {
						showErrorDialog(result.message);
					} else {
						window.location.reload(true);
					}
				});				
			}
		});
	});
	
</script>