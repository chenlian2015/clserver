
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/chosen.css" />
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/select2_metro.css" />
<script type="text/javascript" src="${static_server}/script/ckeditor/ckeditor.js"></script>
[@ui.plugin plugin="upload"][/@ui.plugin]
[@jqPlugin.plugin date='1'/]

		<div class="container-fluid">
			<!-- BEGIN PAGE HEADER-->
			<div class="row-fluid">
				<div class="span12">
					<h3 class="page-title">
						编辑业务类别
					</h3>
					<ul class="breadcrumb">
						<li><i class="icon-home"></i><a href="/admin">首页</a> <i
							class="icon-angle-right"></i></li>
						<li>业务类别管理<i class="icon-angle-right"></i>
						</li>
						<li>编辑</li>
					</ul>
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row-fluid">
				<div class="span12">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-reorder"></i><span class="hidden-480">编辑业务类别</span>
							</div>
						</div>
						<div class="portlet-body form">
							<form id="addform" class="form-horizontal">
								<input type="hidden" name="id" id="categoryId" value="${category.id}">
								<input type="hidden" name="parentId" id="parentId" value="${parentId}">
								<div class="control-group">
									<label class="control-label">业务类别名称</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]"  name="category.name"  value="${category.name }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">业务类别顺序</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]"  name="category.order"  value="${category.order }"/>
									</div>
								</div>
								
								<div class="form-actions">
									<a href="javascript:void(0);" class="btn blue btnsubmit">提交</a>
									<a href="javascript:window.history.go(-1);" class="btn" >取消</a>
								</div>
							</form>
						</div>
					</div>
					<!-- END SAMPLE FORM PORTLET-->
				</div>
			</div>
			<!-- END PAGE CONTENT-->
		</div>
	[/@ui.page]
<script type="text/javascript" src="${static_server}/themes/metro/js/bootstrap-fileupload.js"></script>
<script type="text/javascript" src="${static_server}/themes/metro/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${static_server}/themes/metro/js/select2.min.js"></script>
<script>
	$(document).ready(function (){
		
		$(".btnsubmit").click(function(){
			Submit.submitForm("/admin/business_category/category_update", "addform",function(result){
				if(result.success == false){
					showErrorDialog(result.msg);
				} else{
					window.location.assign("/admin/business_category/category_list");
				}
			},null,false,null, false);
		});
	});
	
</script>