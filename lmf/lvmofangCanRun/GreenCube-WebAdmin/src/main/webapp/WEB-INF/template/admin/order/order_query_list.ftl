
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"][/@ui.plugin]
	<style>
		.expendDetail {
			cursor:pointer;
			font-size:24px;margin:auto;text-align: center;
		}
		.expendDetail2 {
			cursor:pointer;
			font-size:18px;margin:auto;text-align: center;
		}
		.orderDetail td{
			background-color: #F6F29D !important;
		}	
		.orderDetail2 td{
			background-color: #00FFDB !important;
		}
		.button.green{
		   width:70px;
		   height:30px;
      }
	</style>
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">订单查询列表</h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>订单管理
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
			   <div>
			   		<form id="formSearch" action="/admin/order/order_query_list" method="post">
			   				单号：<input type="search" name="orderNo" placeholder="请输入要查询的单号">
			   				<button name="query" id="query" class="button green tags" type="submit">查询</button>
			   		</form>
			   </div>
				<div class="span12" responsive" data-tablet="span12 fix-offset" data-desktop="span6">
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="icon-cogs"></i>订单列表</div>
							<div class="actions">
									<!--<a href="/admin/book/book_edit" class="btn blue"><i class="icon-plus"></i>&nbsp;添加</a>-->
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
									   <td style="text-align:center;">展开</td>
									   <th>订单号</th>
										<th>订单名称</th>
										<th>下单人</th>
										<th>商品实际价格</th>
										<th>下单时间</th>
										<th>订单状态</th>
										<th>订单价格</th>
									</tr>
								</thead>
								
								<tbody>
									[#list orders as o]
										<tr class="odd gradeX" data-id="${o['id']}">
											<td class="expendDetail" align="center" style="text-align: center;">+</td>
										   <td>${o["orderNum"]}</td>
											<td>${o["orderName"] }</td>
											<td>${o["userName"] }</td>
											<td>${o["realFee"]}</td>
											<td>${o["orderTime"] }</td>
											<td>
											[#if o["status"]==20]未付款[/#if]
											[#if o["status"]==21]付款中[/#if]
											[#if o["status"]==22]已付款[/#if]
											[#if o["status"]==30]待确认[/#if]
											[#if o["status"]==40]待评价[/#if]
											[#if o["status"]==50]已完成[/#if]
											</td>
											<td>${o["realFee"]}</td>
										</tr>
										<tr style="display:none">
											<td colspan="8">
												<div style="background-color:#F6F29D;width:98%;margin:auto;">
													<table class="orderDetail">
													<thead></thead>
													<tbody></tbody>
													</table>
												</div>
											</td>
										</tr>
									[/#list]
								</tbody>
								
							</table>
							<div id="pagesplit"></div>
							[@ui.pagesplit link="/adMessageexpendDetail2min/book/book_list" renderTo="pagesplit" /]
						</div>
					</div>
					<!-- 系统通知公告结束-->
				</div>"
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
[/@ui.page]
<script>
	$(document).ready(function(){
		DynamicTime.init("dynamictime");
		
		$(".expendDetail").click(function(){
			var nextElement = $(this).parent().next();
			if (nextElement.is(":hidden")){
			    	var orderId = $(this).parent().attr("data-id");
			    	Submit.invok("/admin/order/order_shopgoods_list?orderId="+orderId,function(result){
					   if (result.success == true){
					        if(result.data.length > 0)
						        {
						         nextElement.show();
					           nextElement.find(".orderDetail thead").html("");
					           nextElement.find(".orderDetail tbody").html("");
						        	 var  _head = "<tr><th>展开</th><th>商品图片</th><th>商家名称</th><th>商品名称</th><th>商品类型</th><th>单价</th><th>数量</th><th>小计</th></tr>";
							      nextElement.find(".orderDetail thead").append(_head);
							      for (i = 0; i < result.data.length ; i ++){
							  		row = result.data[i];
							  		var _html = ""; 
							  		_html += "<tr data-id='"+row.id+"'>";
							  		_html += "<td class='expendDetail2' align='center' style='text-align: center;'>+</td>";
							  		_html += "<td>" + row.goodsPic + "</td>";
							  		_html += "<td>" + row.shopName + "</td>";
							  		_html += "<td>" + row.shopGoodsName + "</td>";
							  		_html += "<td>" + row.type + "</td>";
							  		_html += "<td>" + row.shopRealFee + "</td>";
							  		_html += "<td>" + row.count + "</td>";
							  		_html += "<td>" + row.total + "</td>";
							  		_html += "</tr>";
							  		
									_html += '<tr style="display:none;"><td colspan="8"><div style="background-color:#00FFDB;width:98%;margin:auto;">';
									_html += '<table class="orderDetail2"><thead></thead><tbody></tbody></table></div></td></tr>';
							  		nextElement.find(".orderDetail tbody").append(_html);
							   }
						        }
						  } else {
						     nextElement.hide();
						   }
			           });			
			} else {
				nextElement.hide();
			}
		});
		
			$(document).on( "click" , ".expendDetail2", function(){
				   var goodsId = $(this).parent().attr("data-id");
				   var nextElement = $(this).parent().next();
					if (nextElement.is(":hidden")){
					Submit.invok("/admin/order/order_shopgoodsItem_list?goodsId="+goodsId,function(result){
					if (result.success == true){
						if(result.data.length > 0){
						     nextElement.show();
							 nextElement.find(".orderDetail2 thead").html("");
					       nextElement.find(".orderDetail2 tbody").html("");
						    var  _head = "<tr><th>商品类型</th><th>店铺-商品名称</th><th>店铺-原价</th><th>店铺-实际价格</th><th>供应商-图片</th><th>供应商-商品名称</th><th>供应商-原价</th><th>供应商-实际价格</th></tr>";
						    nextElement.find(".orderDetail2 thead").append(_head);
				   	    for (i = 0; i < result.data.length ; i ++){
							  		row = result.data[i];
							  		var _html = ""; 
							  		_html += "<tr>";
							  		_html += "<td>" + row.type + "</td>";
							  		_html += "<td>" + row.shopGoodsName + "</td>";
							  		_html += "<td>" + row.shopOrginalFee + "</td>";
							  		_html += "<td>" + row.shopRealFee + "</td>";
							  		_html += "<td>" + row.providerGoodsImage + "</td>";
							  		_html += "<td>" + row.providerGoodsName + "</td>";
							  		_html += "<td>" + row.providerOrginalFee + "</td>";
							  		_html += "<td>" + row.providerRealFee + "</td>";
							  		_html += "</tr>";
							  		nextElement.find(".orderDetail2 tbody").append(_html);
						  }
						}
						else{
						 nextElement.hide();
						}
						
				       }
					});
					 
					}
					else{
					 nextElement.hide();
					}
			});
	});
</script>