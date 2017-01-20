
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
						发票备案
					</h3>
					<ul class="breadcrumb">
						<li><i class="icon-home"></i><a href="/admin">首页</a> <i
							class="icon-angle-right"></i></li>
						<li>发票管理<i class="icon-angle-right"></i>
						</li>
						<li>发票备案</li>
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
								<i class="icon-reorder"></i><span class="hidden-480">编辑发票</span>
							</div>
						</div>
						<div class="portlet-body form">
							<form id="addform" class="form-horizontal">
								<input type="hidden" name="id" value="${m.id}">
									<div class="control-group">
									<label class="control-label">发票抬头类型</label>
									<div class="controls">
												<input type="radio" checked="checked" />单位
												<input type="radio" />个人
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">发票抬头</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]" maxlength="20" name="m.name"  maxlength="100" value="${m.name }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">单位名称</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]" maxlength="20" name="m.indefier"  maxlength="100" value="${m.indefier }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">发票类型</label>
									<div class="controls">
										<select name="m.clientType">
											<option value="app">机打发票</option>
											<option value="wap"]>增值税发票</option>
										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">纳税人识别码</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]" maxlength="200" name="m.desc"  maxlength="100" value="${m.desc }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">订单号</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]" maxlength="20" name="m.order"  maxlength="100" value="${m.order }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">发票图片</label>
									<div class="controls">
										<input type="hidden" name="m.appLogo1" value="${m.appLogo1 }" id="appLogo1">
	                      	 			<input type="file" class="" name="pic" id="pic">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">注册地址</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]" maxlength="20" name="m.order"  maxlength="100" value="${m.order }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">注册电话</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]" maxlength="20" name="m.order"  maxlength="100" value="${m.order }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">开户银行</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]" maxlength="20" name="m.order"  maxlength="100" value="${m.order }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">开户账号</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]" maxlength="20" name="m.order"  maxlength="100" value="${m.order }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" style="float:left;margin-left:20px;">发票接受者姓名</label>
									<div class="controls" style="float:left;margin-left:21px;">
										<input type="text"/>
									</div>
									<label class="control-label" style="float:left;margin-left:40px;">发票接受者电话</label>
									<div class="controls" style="float:left;margin-left:41px;">
										<input type="text"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" style="float:left;margin-left:20px;">省</label>
									<div class="controls" style="float:left;margin-left:21px;">
										<input type="text"/>
									</div>
									<label class="control-label" style="float:left;margin-left:40px;">市</label>
									<div class="controls" style="float:left;margin-left:41px;">
										<input type="text"/>
									</div>
								</div>
									<div class="control-group">
									<label class="control-label">详细地址</label>
									<div class="controls">
										<input type="text" class="span6 m-wrap validate[required]" maxlength="20" name="m.order"  maxlength="100" value="${m.order }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" style="float:left;margin-left:20px;">快递登记时间</label>
									<div class="controls" style="float:left;margin-left:21px;">
										<input type="text"/>
									</div>
									<label class="control-label" style="float:left;margin-left:40px;">快递完成时间</label>
									<div class="controls" style="float:left;margin-left:41px;">
										<input type="text"/>
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