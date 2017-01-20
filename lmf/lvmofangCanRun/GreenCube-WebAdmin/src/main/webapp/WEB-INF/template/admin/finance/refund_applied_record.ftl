
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"][/@ui.plugin]
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">退款申请记录</h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>退款申请记录
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
		 <div>
			<form id="formSearch" action="/admin/finance/provider_memberfi_stat" method="post">
			  姓名：<input type="text"  name="userame"  placeholder="请输入要查询的姓名"/>
				退款方式：
				<select>
					<option>微信转账</option>
					<option>银行卡转账</option>
				</select>
				状态：
				<select>
					<option>已转账</option>
					<option>转账中</option>
					<option>转账失败</option>
					<option>驳回转账</option>
				</select>
				<button name="query" id="query" class="button green tags" type="submit">查询</button>
			 </form>
		   </div>
			<div class="row-fluid">
				<div class="span12" responsive" data-tablet="span12 fix-offset" data-desktop="span6">
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="icon-cogs"></i>记录列表</div>
							<div class="actions">
								<!--<a href="/admin/book/book_edit" class="btn blue"><i class="icon-plus"></i>&nbsp;添加</a>-->
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
										<th>退款者姓名</th>
										<th>退款方式</th>
										<th>退款金额</th>
										<th>申请时间</th>
										<th>审核时间</th>
										<th>审核人</th>
										<th>状态</th>
									</tr>
								</thead>
								<tbody>
										<tr class="odd gradeX">
											<td>王新</td>
											<td>微信转账</td>
											<td>10000</td>
											<td>2016-01-21 14：59：24</td>
											<td>2016-01-22 14：59：24</td>
											<td>胡锦涛</td>
											<td>已转账</td>
										</tr>
										<tr class="odd gradeX">
											<td>易东</td>
											<td>银行卡转账</td>
											<td>9999</td>
											<td>2016-02-23 18：26：37</td>
											<td>2016-02-24 14：59：24</td>
											<td>习近平</td>
											<td>驳回转账</td>
										</tr>
											<tr class="odd gradeX">
											<td>胡晓光</td>
											<td>微信转账</td>
											<td>200000</td>
											<td>2016-03-08 15：53：16</td>
											<td>2016-03-08 15：59：24</td>
											<td>江泽民</td>
											<td>正在转账</td>
										</tr>
								</tbody>
							</table>
							<div id="pagesplit"></div>
							[@ui.pagesplit link="/admin/book/book_list" renderTo="pagesplit" /]
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
		//审核
		$(".btnAudit").on("click",function(){
			var goodsId = $(this).attr("data-id");
			var auditStatus = $(this).attr("data-value");
			Submit.invok("/admin/goods/provider_goods_audit?auditStatus="+auditStatus + "&goodsId=" + goodsId,function(data){
				if (data.success == true){
					window.location.reload(true);
				} else {
					showErrorDialog(data.message);
				}
			});				
		});
	
		
	});
</script>