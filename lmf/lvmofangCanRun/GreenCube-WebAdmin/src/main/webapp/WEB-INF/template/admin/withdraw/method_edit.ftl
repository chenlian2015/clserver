
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
						编辑支付方式
					</h3>
					<ul class="breadcrumb">
						<li><i class="icon-home"></i><a href="/admin">首页</a> <i
							class="icon-angle-right"></i></li>
						<li>支付方式管理<i class="icon-angle-right"></i>
						</li>
						<li>编辑支付方式</li>
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
								<i class="icon-reorder"></i><span class="hidden-480">创建类别</span>
							</div>
						</div>
						<div class="portlet-body form">
							<form id="addform" class="form-horizontal">
								<input type="hidden" name="id" value="${m.id}">
								<div class="control-group">
									<label class="control-label">支付方式名称</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]" maxlength="20" name="m.name"  maxlength="100" value="${m.name }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">唯一标识</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]" maxlength="20" name="m.indefier"  maxlength="100" value="${m.indefier }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">客户端类型</label>
									<div class="controls">
										<select name="m.clientType">
											<option value="app" [#if m.clientType == 'app']selected="true"[/#if]>手机App（app）</option>
											<option value="wap" [#if m.clientType == 'wap']selected="true"[/#if]>手机网页（wap）</option>
											<option value="pc" [#if m.clientType == 'pc']selected="true"[/#if]>PC（pc）</option>
										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">备注</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]" maxlength="200" name="m.desc"  maxlength="100" value="${m.desc }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">顺序</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]" maxlength="20" name="m.order"  maxlength="100" value="${m.order }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">LOGO图片</label>
									<div class="controls">
										<input type="hidden" name="m.appLogo1" value="${m.appLogo1 }" id="appLogo1">
	                      	 			<input type="file" class="" name="pic" id="pic">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">是否启用</label>
									<div class="controls">
										<label class="radio">
											<input type="radio" name="optionsRadios1" value="1" [#if m.enable == 1 || m.enable == null]checked="true"[/#if]/>启用
										</label>
										<label class="radio">
											<input type="radio" name="optionsRadios1" value="0" [#if m.enable == 0 ]checked="true"[/#if]/>不启用
										</label>
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
		TImageUpload.createUploadImage('pic', 200, 120 , '[@ui.image url="${m.appLogo1}"/]', function(obj){
			$("#appLogo1").attr("value", obj.data.url);
		}, 0.5);
		$(".btnsubmit").click(function(){
			Submit.submitForm("/admin/payment/method/method_update", "addform",function(result){
				if(result.success == false){
					showErrorDialog(result.msg);
				} else{
					window.location.assign("/admin/payment/method/method_list");
				}
			},null,false,null, false);
		});
	});
</script>