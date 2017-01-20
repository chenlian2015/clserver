[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]


	[@ui.plugin plugin="pagesplit"][/@ui.plugin]
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">试听课程管理 <small>试听课程列表</small></h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>试听课程 
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
					<!-- 居民物业费缴纳表-->
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="icon-cogs"></i>试听课程概览</div>
							<div class="actions">
								<a href="/admin/video/video_edit" class="btn blue"><i class="icon-plus"></i>&nbsp;添加</a>
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
										<th width="60">略缩图</th>
										<th>试听课程名称</th>
										<th>试听课程时长</th>
										<th>主讲老师</th>
										<th>价格</th>
										<th>上传者</th>
										<th>所属机构</th>
										<th>上传日期</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
								<tbody>
									<!-- 合计行 -->
									[#list vlist as v]
										<tr class="odd gradeX">
											<td><img src="[@ui.image url="${v.CPicPath }" /]" alt="${v.CName }" width="48" height="48"/></td>
											<td>${v.CName }</td>
											<td>${v.CVideoDuration }</td>
											<td>${v.CTeacherName }</td>
											<td>[@nf value=v.NFee pattern="0.00"/]</td>
											<td>${v.CUploaderName }</td>
											<td>${v.CInsName???string(v.CInsName, '鼎尖教育') }</td>
											<td>[@fmt.formatDate value=v.DUploadTime  pattern="yyyy-MM-dd"/]  </td>
											<td nowrap>
												<a href="/admin/video/video_edit?videoId=${v.id }" class="btn green mini"><i class="icon-edit"></i> 修改</a>
												<a href="javascript:void(0);" data-cid="${v.id }" class="btn red bdelete mini"><i class="icon-trash"></i> 删除</a>
											</td>
										</tr>
									[/#list]
								</tbody>
							</table>
							<div id="pagesplit"></div>
							[@ui.pagesplit link="/admin/video/video_list" renderTo="pagesplit" /]
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
			if (window.confirm("您确定要删除该条试听课程信息吗？")){
				var selId = $(this).attr("data-cid");
				Submit.invok("/admin/video/video_delete?videoId="+selId,function(){
					window.location.reload(true);
				});				
			}
		});
	});
</script>