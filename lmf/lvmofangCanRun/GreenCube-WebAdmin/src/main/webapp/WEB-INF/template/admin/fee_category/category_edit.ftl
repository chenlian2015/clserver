
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/chosen.css" />
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/select2_metro.css" />
<script type="text/javascript" src="${static_server}/script/ckeditor/ckeditor.js"></script>
[@ui.plugin plugin="upload"][/@ui.plugin]
[@jqPlugin.plugin date="1"/]

		<div class="container-fluid">
			<!-- BEGIN PAGE HEADER-->
			<div class="row-fluid">
				<div class="span12">
					<h3 class="page-title">
						缴费科目
					</h3>
					<ul class="breadcrumb">
						<li><i class="icon-home"></i><a href="/admin">首页</a> <i
							class="icon-angle-right"></i></li>
						<li>缴费科目管理<i class="icon-angle-right"></i>
						</li>
						<li>缴费科目</li>
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
								<i class="icon-reorder"></i><span class="hidden-480">编辑科目</span>
							</div>
						</div>
						<div class="portlet-body form">
							<form id="addform" class="form-horizontal">
								<input type="hidden" name="id" value="${m.id}">
								<div class="control-group">
									<label class="control-label">科目名称</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]" maxlength="20" name="m.name"  maxlength="100" value="${m.name }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">金额（元）</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]" maxlength="20" name="m.fee"  maxlength="100" value="${m.fee }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">适用群体</label>
									<div class="controls">
										<select name="m.suitUserType">
											<option value="256" [#if m.suitUserType == 256 ]selected="true"[/#if]>供产会会员</option>
											<option value="4096" [#if m.suitUserType == 4096]selected="true"[/#if]>店主会会员</option>
											<option value="16" [#if m.suitUserType == 16]selected="true"[/#if]>健康会会员</option>
											<option value="4369" [#if m.suitUserType == 4369]selected="true"[/#if]>所有会员</option>
											
										</select>
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
			Submit.submitForm("/admin/fee_category/category_update", "addform",function(result){
				if(result.success == false){
					showErrorDialog(result.msg);
				} else{
					window.location.assign("/admin/fee_category/category_list");
				}
			},null,false,null, false);
		});
	});
</script>