
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
						添加培训
					</h3>
					<ul class="breadcrumb">
						<li><i class="icon-home"></i><a href="/admin">首页</a> <i
							class="icon-angle-right"></i></li>
						<li>培训管理<i class="icon-angle-right"></i>
						</li>
						<li>创建培训</li>
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
								<i class="icon-reorder"></i><span class="hidden-480">创建培训</span>
							</div>
						</div>
						<div class="portlet-body form">
							<form id="addform" class="form-horizontal">
								<input type="hidden" name="trainId" id="trainId" value="${e.id}">
								<div class="control-group">
									<label class="control-label">培训名称</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]"  name="e.name"  value="${e.name }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">类别名称</label>
									<div class="controls">
										<select class="span6 m-wrap" name="e.trainCategoryId" id="trainCategoryId">
											[#list categories as c]
												<option value="${c.id}" [#if c.id == e.trainCategoryId]selected="true"[/#if]>${c.name }</option>
											[/#list]
										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">描述</label>
									<div class="controls">
										<textarea rows="5" class="span6 m-wrap" name="e.desc" >${e.desc }</textarea>
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
			Submit.submitForm("/admin/train/train_update", "addform",function(result){
				if(result.success == false){
					showErrorDialog(result.msg);
				} else{
					window.location.assign("/admin/train/train_list");
				}
			},null,false,null, false);
		});
	});
	
</script>