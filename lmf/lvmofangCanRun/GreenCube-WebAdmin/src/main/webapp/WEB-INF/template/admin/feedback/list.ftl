[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"/]
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
				<div>
					<h3 class="page-title">意见反馈列表</h3>
					<ul class="breadcrumb">
						<li><i class="icon-home"></i> <a href="/admin">首页</a> <i
							class="icon-angle-right"></i></li>
						<li>意见反馈管理<i class="icon-angle-right"></i>
						</li>
						<li>意见反馈列表</li>
					</ul>
				</div>
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-comments"></i>意见反馈列表
						</div>
						<div class="tools">
							<a href="javascript:void(0);" class="collapse"></a>
						</div>
						<div class="actions" style="margin-top:10px;">
						</div>
					</div>
					<div class="portlet-body fuelux">
						<div class="clearfix">
							<table class="table table-striped table-hover table-bordered">
								<thead>
									<tr>
										<th>反馈人</th>
										<th>反馈内容</th>
										<th>联系方式</th>
										<th>反馈时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									[#list advices as advice]
										<tr>
											<td>${advice.CUserName}</td>
											<td>${advice.CAdvice}</td>
											<td>${advice.CLxfs}</td>
											<td>[@fmt.formatDate value=advice.DFeedbackTime  pattern="yyyy-MM-dd hh:mm:ss"/]</td>
											<td>
												<a class="btn mini blue btnedit" data-id="${advice.id}" href="/admin/feedback/detail?advicefbId=${advice.id }">
												<i class="icon-edit"></i>详情</a>
												<a class="btn mini red btndelete" data-id="${advice.id}" href="javascript:void(0);">
												<i class="icon-trash"></i>删除</a>
											</td>
										</tr>
									[/#list]
								</tbody>
							</table>
							<div id="pagesplit"></div>
							[@ui.pagesplit link="/admin/feedback/advicefb_list" renderTo="pagesplit" /]
						</div>
					</div>
				</div>
				</div>
			</div>
		</div>
	[/@ui.page]
<script>
	$(document).ready(function (){
		$(".btndelete").click(function(){
			if (window.confirm("您确定要删除该意见反馈吗？")) {
					var cid = $(this).attr("data-id");
					Submit.invok("/admin/feedback//delete?advicefbId=" +cid, function() {
						window.location.reload(true);
					});	
			}
		});
	});
</script>