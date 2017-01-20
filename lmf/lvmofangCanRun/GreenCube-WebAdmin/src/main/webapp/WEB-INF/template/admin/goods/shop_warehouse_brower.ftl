[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@jqPlugin.plugin pager="1" /]
	<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/bootstrap-tree.css" />
	<link href="${static_server}/themes/metro/css/search.css" rel="stylesheet" type="text/css"/>
	<style>
	.selarea {
			color: red !important;
		}
	.tree>li{
		width:190px;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
	}
	</style>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div>
						<h3 class="page-title">店主仓库管理</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="/admin">店主仓库管理</a> <i
								class="icon-angle-right"></i></li>
							<li>供仓一览<i class="icon-angle-right"></i>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span3">
					<div>
						<div class="portlet box purple">
							<div class="portlet-title">
								<div class="caption"><i class="icon-comments"></i>店主会会员列表</div>
							</div>
							<div class="portlet-body fuelux">
								<ul class="tree" id="tree_1">
									  <ul>
									  [#list members as m]
										  <li data-id="${m['id']}" class="shop_item"><a href="javascript:void(0);">${m["shopName"] }</a></li>
									  [/#list]
										</ul>
								</ul>
							</div>
						</div>
					</div>
				</div>
				
				<div class="span9">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-comments"></i>
									[#if column??]${column["name"]}[#else]店主会会员商品[/#if]
							</div>
							<div class="tools">
								<a href="javascript:void(0);" class="collapse"></a>
							</div>
							<div class="actions" style="margin-top:10px;">
								<!--<a class="btn yellow channel_add" href="javascript:void(0);">添加内容<i class="icon-plus"></i></a>-->
							</div>
						</div>
						<div class="portlet-body fuelux">
							<div class="clearfix" id="article_list_table">
								<table class="table table-striped table-hover table-bordered">
									<thead>
										<tr>
											<th>商品图片</th>
											<th>商品名称</th>
											<th>创建时间</th>
											<th>上架状态</th>
											<th>总销售数</th>
											<th width="200" nowrap>操作</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
								<div id="pagesplit" style="clear:both;"></div>
								[@ui.pagesplit link="/admin/cms/article_list?columnId=${columnId}" renderTo="pagesplit" /]
							</div>
						</div>
					</div>
					</div>
				</div>
			</div>
		</div>
	[/@ui.page]
<script>

$(document).ready(function (){
      $(".shop_item").click(function(){
	   var goodsElement = $("tbody");
	   var memberId = $(this).attr("data-id");
	   //alert(memberId);
	   Submit.invok("/admin/goods/shop_member_goods?memberId="+memberId,function(result){
	         if(result.success == true) {
	           goodsElement.html("");
	           for (i = 0; i < result.data.length ; i ++){
	                 row = result.data[i];
	                 var _html = ""; 
		              _html +="<tr>";
		              _html +="<td>"+row.picMain+"</td>";
		              _html +="<td>"+row.name+"</td>";
		              _html +="<td>"+row.createTime+"</td>";
		               if(row.statusShelf==1)
				              {
				              _html +="<td>已上架</td>";
				              }
				         else
				              {
				              _html +="<td>未上架</td>";
				              }
		              _html +="<td>"+row.salesCount+"</td>";
		              _html += '<td><a href="/admin/goods/shop_goods_detail?goodsId='+row.id+'" class="btn yellow mini"><i class="icon-leaf"></i> 详情</a></td>';
		              _html +="</tr>";
		              goodsElement.append(_html);
	              }
	         }
	      });
   });
	
});
</script>