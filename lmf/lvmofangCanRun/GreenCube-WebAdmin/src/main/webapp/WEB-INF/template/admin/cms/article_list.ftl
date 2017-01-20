[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@jqPlugin.plugin pager="1" /]
	<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/bootstrap-tree.css" />
	<link href="${static_server}/themes/metro/css/search.css" rel="stylesheet" type="text/css"/>
	<style>
	.selarea {
			color: #FFFFFF !important;
			background-color:red !important;
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
						<h3 class="page-title">内容管理</h3>
						<ul class="breadcrumb">
							<li><i class="icon-home"></i> <a href="/admin">首页</a> <i
								class="icon-angle-right"></i></li>
							<li>信息发布管理<i class="icon-angle-right"></i>
							</li>
							<li>栏目管理</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span3">
					<div>
						<div class="portlet box purple">
							<div class="portlet-title">
								<div class="caption"><i class="icon-comments"></i>栏目列表</div>
							</div>
							<div class="portlet-body fuelux">
								<ul class="tree" id="tree_1">
									${treeHtml }
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
									[#if column??]${column["name"]}[#else]内容列表[/#if]
							</div>
							<div class="tools">
								<a href="javascript:void(0);" class="collapse"></a>
							</div>
							<div class="actions" style="margin-top:10px;">
								<a class="btn yellow channel_add" href="javascript:void(0);">添加内容<i class="icon-plus"></i></a>
							</div>
						</div>
						<div class="portlet-body fuelux">
							<div class="clearfix" id="article_list_table">
								<table class="table table-striped table-hover table-bordered">
									<thead>
										<tr>
											<th nowrap>顺序</th>
											<th>标题</th>
											<th>状态</th>
											<th>发布时间</th>
											<th width="200" nowrap>操作</th>
										</tr>
									</thead>
									<tbody>
										[#list articles as a]
											<tr>
												<td>${a.order }</td>
												<td><a href="/news/${a.id}" style="text-decoration: underline;color:blue;" target="_blank" title="点击可预览和复制网址">${a.title}</a></td>
												<td>
													[#if a.isRelease == 30]
															<span class="label label-success">已发布</span> 
													[#else]
															<span class="label label-important">未发布</span> 
													[/#if]
												</td>
												<td>[@fmt.formatDate value=a.createTime type="both" pattern="yyyy/MM/dd"/]</td>
												<td nowrap>
													[#if a.isRelease == 30]
															<a class="btn mini green unpublish-article" href="javascript:void(0);" data-value="${a.id}">
																<i class="icon-external-link"></i>取消发布
															</a>
													[#else]
															<a class="btn mini green publish-article" href="javascript:void(0);" data-value="${a.id}">
																<i class="icon-external-link"></i>发布
															</a>
													[/#if]
													[#if a.isTop == 1]
															<a class="btn mini red top-article" href="javascript:void(0);" data-status="${a.isTop}" data-value="${a.id}">
																<i class="icon-external-link"></i>取消置顶
															</a>
													[#else]
															<a class="btn mini green top-article" href="javascript:void(0);" data-status="${a.isTop}" data-value="${a.id}">
																<i class="icon-external-link"></i>文章置顶
															</a>
													[/#if]
													<a class="btn mini green" href="/admin/cms/article_edit?id=${a.id}">
														<i class="icon-edit"></i>编辑
													</a>
													<a class="btn mini red btndelete" data-id="${a.id}" href="javascript:void(0);">
														<i class="icon-trash"></i>删除
													</a>
												</td>
											</tr>
										[/#list]
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
var selectedChannel = "${columnId}";

$(document).ready(function (){
	var channelId = "${columnId}";
	$("#" + channelId).addClass("selarea");
	
	$(".btndelete").click(function(){
		if (window.confirm("您确定要删除此文章吗？")) {
			var cid = $(this).attr("data-id");
			Submit.invok("/admin/cms/article_delete?id=" +cid, function() {
				window.location.reload(true);
			});			
		}
	});
	$(".channel").click(function(){
		var channelId = $(this).attr("data-value");
		$(".channel").removeClass("selarea");
		$(this).addClass("selarea");
		selectedChannel = channelId;
		
		// 刷新文章界面
		window.location.assign("/admin/cms/article_list?columnId=" + channelId, "article_list_table");
	});
	$(".channel_add").click(function(){
		if (selectedChannel == null) {
			window.alert("请先选择栏目，然后再添加文章");
			return;
		}
		window.location.assign("/admin/cms/article_edit?columnId=" + selectedChannel);	
	});
	$(".publish-article").click(function(){
		if (window.confirm("您确定要发布此文章吗？")) {
			var cid = $(this).attr("data-value");
			Submit.invok("/admin/cms/article_publish?flag=true&id=" +cid, function() {
				window.location.reload(true);
			});			
		}
	});
	$(".unpublish-article").click(function(){
		if (window.confirm("您确定要取消发布此文章吗？")) {
			var cid = $(this).attr("data-value");
			Submit.invok("/admin/cms/article_publish?flag=false&id=" +cid, function() {
				window.location.reload(true);
			});			
		}
	});
	$(".top-article").click(function(){
		var st = $(this).attr("data-status");
		var tips = "";
		if(st == 1){
			tips ="您确定要取消置顶此文章吗？";
		}else{
			tips ="您确定要置顶此文章吗？";
		}
		var cid = $(this).attr("data-value");
		if (window.confirm(tips)) {
			Submit.invok("/admin/cms/article_top?id=" +cid, function(result) {
				if(result.cussess == false){
					showErrorDialog(result.msg);
				}else{
					window.location.reload(true);
				}
				
			});	
		}
		
	});
});
</script>