[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"/]
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">业务类别管理 <small>业务类别列表</small></h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>业务类别 
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
					<!-- 业务类别表-->
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="icon-cogs"></i>业务类别概览</div>
							<div class="actions">
								<a href="/admin/business_category/category_edit" class="btn blue"><i class="icon-plus"></i>&nbsp;添加父类别</a>
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
										<th>类别名称</th>
										<th>排序</th>
										<th colspan="3">操作</th>
									</tr>
								</thead>
								
								<tbody>
									[#list clist as c]
										<tr class="odd gradeX">
											<td>${c.name }</td>
											<td>${c.order }</td>
											<td nowrap>
											<a href="/admin/business_category/category_edit?categoryId=${c.id }" class="btn blue mini"><i class="icon-plus"></i> 新增子类别</a>
												<a href="/admin/train/category_edit?categoryId=${c.id }" class="btn green mini"><i class="icon-edit"></i> 修改</a>
												<a href="javascript:void(0);" data-cid="${c.id }" class="btn red bdelete mini"><i class="icon-trash"></i> 删除</a>
											</td>
										</tr>
									[/#list]
								</tbody>
								
								<!--<tbody>
										<tr class="odd gradeX">
											<td>服务</td>
											<td>1</td>
											<td nowrap>
											<a href="/admin/business_category/category_edit?parentId=${c.id }" class="btn blue mini"><i class="icon-plus"></i> 新增子类别</a>
												<a href="/admin/business_category/category_edit?categoryId=${c.id }" class="btn green mini"><i class="icon-edit"></i> 修改</a>
												<a href="javascript:void(0);" data-cid="${c.id }" class="btn red bdelete mini"><i class="icon-trash"></i> 删除</a>
											</td>
										</tr>
										<tr class="odd gradeX">
											<td>医疗</td>
											<td>2</td>
											<td nowrap>
											<a href="/admin/business_category/category_edit?parentId=${c.id }" class="btn blue mini"><i class="icon-plus"></i> 新增子类别</a>
												<a href="/admin/train/category_edit?categoryId=${c.id }" class="btn green mini"><i class="icon-edit"></i> 修改</a>
												<a href="javascript:void(0);" data-cid="${c.id }" class="btn red bdelete mini"><i class="icon-trash"></i> 删除</a>
											</td>
										</tr>
								</tbody>-->
								
							</table>
							<div id="pagesplit"></div>
							[@ui.pagesplit link="/admin/train/train_list" renderTo="pagesplit" /]
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
			if (window.confirm("您确定要删除该条培训类别信息吗？")){
				var selId = $(this).attr("data-cid");
				Submit.invok("/admin/train/category_delete?categoryId="+selId,function(){
					window.location.reload(true);
				});				
			}
		});
	});
</script>