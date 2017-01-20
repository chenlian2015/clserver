
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"][/@ui.plugin]
	<style>
		.expendDetail {
			cursor:pointer;
			font-size:24px;margin:auto;text-align: center;
		}
		.goodsDetail td{
			background-color: #F6F29D !important;
		}	
	</style>
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">店主仓库产品审核</h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>待审核的店主会商品
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
							<div class="caption"><i class="icon-cogs"></i>待审核商品</div>
							<div class="actions">
									<!--<a href="/admin/book/book_edit" class="btn blue"><i class="icon-plus"></i>&nbsp;添加</a>-->
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
									   <td style="text-align:center;">展开</td>
									   <th>商品图片</th>
										<th>店铺名称</th>
										<th>商品名称</th>
										<th>原价</th>
										<th>实际价格</th>
										<th>商品分类</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
								
								<tbody>
									[#list goods as g]
										<tr class="odd gradeX" data-id="${g['id']}">
											<td class="expendDetail" align="center" style="text-align: center;">+</td>
										   <td>${g["picMain"]}</td>
											<td>${g["shopName"] }</td>
											<td>${g["name"]}</td>
											<td>${g["orginalFee"] }</td>
											<td>${g["realFee"]}</td>
											<td>${g["categoryName"] }</td>
											<td nowrap>
												 <a href="/admin/goods/shop_goods_detail?goodsId=${g['id']}" class="btn yellow btnDetail mini" data-id="${g['id']}" data-value="1"><i class="icon-leaf"></i> 详情</a>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${g['id']}" data-value="1"><i class="icon-edit"></i> 通过</a>
												<a href="javascript:void(0);" class="btn green btnAudit mini" data-id="${g['id']}" data-value="-1"><i class="icon-trash"></i> 不通过</a>
											</td>
										</tr>
										<tr style="display:none">
											<td colspan="10">
												<div style="background-color:#F6F29D;width:98%;margin:auto;">
													<table class="goodsDetail">
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
							[@ui.pagesplit link="/adMessagemin/book/book_list" renderTo="pagesplit" /]
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
			    	var goodsId = $(this).parent().attr("data-id");
			    	Submit.invok("/admin/goods/shop_goodsitem_list?goodsId="+goodsId,function(result){
			    		
					   if (result.success == true){
					        nextElement.find("thead").html("");
					        nextElement.find("tbody").html("");
							  nextElement.show();
							      var  _head = "<tr><th>商品类型</th><th>商品名称</th><th>店铺商品实际价格</th><th>供应商商品实际价格</th><th>供应商商品名称</th><th>供应商商品图片</th><th colspan='2'>操作</th></tr>";
							      nextElement.find("thead").append(_head);
							  for (i = 0; i < result.data.length ; i ++){
							  		row = result.data[i];
							  		var _html = ""; 
							  		_html += "<tr>";
							  		_html += "<td>" + row.type + "</td>";
							  		_html += "<td>" + row.shopGoodsName + "</td>";
							  		_html += "<td>" + row.shopRealFee + "</td>";
							  		_html += "<td>" + row.shopGoodsName + "</td>";
							  		_html += "<td>" + row.providerRealFee + "</td>";
							  		_html += "<td>" + row.providerGoodsImage + "</td>";
							  		_html += '<td><a href="/admin/goods/shop_goodsitem_detail?goodsitemId='+row.id+'" class="btn yellow mini"><i class="icon-leaf"></i> 详情</a></td>';
							  		_html += "</tr>";
							  		nextElement.find("tbody").append(_html);
							   }
						  } else {
						     nextElement.hide();
						   }
			           });			
			} else {
				nextElement.hide();
			}
		});
		
			$(".btnAudit").on("click",function(){
			var goodsId = $(this).attr("data-id");
			alert(goodsId);
			var auditStatus = $(this).attr("data-value");
			Submit.invok("/admin/goods/shop_goods_audit?auditStatus="+auditStatus + "&goodsId=" + goodsId,function(data){
				if (data.success == true){
					window.location.reload(true);
				} else {
					showErrorDialog(data.message);
				}
			});				
		});				
	});
</script>