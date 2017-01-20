[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]
<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<th nowrap>栏目</th>
			<th nowrap>显示顺序</th>
			<th>标题</th>
			<th>状态</th>
			<th>发布时间</th>
			<th width="200">操作</th>
		</tr>
	</thead>
	<tbody>
		[#list articles as a]
			<tr>
				<td>${a.CChannelName }</td>
				<td>${a.NOrder }</td>
				<td>${a.CTitle}</td>
				<td>
					[#if a.NIsRelease == 1]
							<span class="label label-success">已发布</span> 
					[#else]
							<span class="label label-important">未发布</span> 
					[/#if]
				</td>
				<td>[@fmt.formatDate value=a.DCreateTime type="both" pattern="yyyy/MM/dd"/]</td>
				<td nowrap>
					[#if a.NIsRelease == 1]
							<a class="btn mini green unpublish-article" href="javascript:void(0);" data-value="${a.id }">
								<i class="icon-external-link"></i>取消发布
							</a>
					[#else]
							<a class="btn mini green publish-article" href="javascript:void(0);" data-value="${a.id }">
								<i class="icon-external-link"></i>发布
							</a>
					[/#if]
					[#if a.NIsTop == 1]
							<a class="btn mini red top-article" href="javascript:void(0);" data-status="${a.NIsTop }" data-value="${a.id }">
								<i class="icon-external-link"></i>取消置顶
							</a>
					[#else]
							<a class="btn mini green top-article" href="javascript:void(0);" data-status="${a.NIsTop }" data-value="${a.id }">
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
<script>
$(document).ready(function (){
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
	$(".btndelete").on("click",function(){
		if (window.confirm("您确定要删除此文章吗？")) {
			var cid = $(this).attr("data-id");
			Submit.invok("/admin/cms/article_delete?id=" +cid, function(result) {
				if(result.cussess == false){
					showErrorDialog(result.msg);
				}else{
					window.location.reload(true);
				}
				
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