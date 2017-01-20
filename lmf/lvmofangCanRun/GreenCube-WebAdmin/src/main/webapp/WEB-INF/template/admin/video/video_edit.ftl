
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/chosen.css" />
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/select2_metro.css" />
<script type="text/javascript" src="${static_server}/script/ckeditor/ckeditor.js"></script>
[@ui.plugin plugin="upload"][/@ui.plugin]
[@jqPlugin.plugin date='1'/]
<style>
	.video{
		position:absolute;
		right:50px;
		top:180px;
	}
	.polyv_btn{
		width: 88px !important;
	}
</style>
		<div class="container-fluid">
			<!-- BEGIN PAGE HEADER-->
			<div class="row-fluid">
				<div class="span12">
					<h3 class="page-title">
						添加试听课程
					</h3>
					<ul class="breadcrumb">
						<li><i class="icon-home"></i><a href="/admin">首页</a> <i
							class="icon-angle-right"></i></li>
						<li>试听课程管理<i class="icon-angle-right"></i>
						</li>
						<li>创建试听课程</li>
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
								<i class="icon-reorder"></i><span class="hidden-480">创建试听课程</span>
							</div>
						</div>
						<div class="portlet-body form">
							<form id="addform" class="form-horizontal">
								<input type="hidden" name="vid" value="${v.id}">
								<div class="control-group">
									<label class="control-label">试听课程名称</label>
									<div class="controls">
										<input type="text" class="span3 m-wrap validate[required]" maxlength="64" name="v.CName" class="bar validte[require]" maxlength="20" value="${v.CName }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">价格</label>
									<div class="controls">
										<input type="text" class="span3 m-wrap validate[required,custom[number]]" maxlength="20" name="v.NFee" value="${v.NFee }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">主讲老师</label>
									<div class="controls">
										<input type="text" class="span3 m-wrap validate[required]" maxlength="20" name="v.CTeacherName" value="${v.CTeacherName }"/>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">课程特色</label>
									<div class="controls">
										<textarea class="span6 m-wrap" rows="5" name="v.CTeachFeature">${v.CTeachFeature }</textarea>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">视频资源描述</label>
									<div class="controls">
										<textarea class="span6 m-wrap" rows="5" name="v.CDesc">${v.CDesc }</textarea>
									</div>
								</div>
								<div class="video">
									<div class="controls">
										<div class="fileupload fileupload-new" data-provides="fileupload">
											<input type="hidden" name="v.CPicPath" value="${v.CPicPath }" id="CPicPath">
	                      	 				<input type="file" class="" name="importPic" id="importPic">
										</div>
										
										<div class="fileupload fileupload-new" data-provides="fileupload">
											<script>
												var writetoken = 'XZGimwtWGZU6zeo-h0pmRIXdmvhCl1Ec';
												var readtoken = 'tkcfayMuoE-7Na1TJaNG9-kvUPcX9WrK';
											</script>
											<script src='http://static.polyv.net/file/caret/v_uploader.js'></script><br/>
											<input type="hidden" name="videoId" id="videoId" value="${v.CPolvVideoId }"/>
											<input type="hidden" name="mp4Url" id="mp4Url" value="${v.CPolvVideoUrl}"/>
											<input type="hidden" name="swfLink" id="swfLinkId" value="${v.CPolvSwfUrl }"/>
											<input type="hidden" name="duration" id="duration" value="${v.CVideoDuration }"/>
											<input type="hidden" name="firstImage" id="firstImage" value="${v.CPolvVideoPic }"/>
											[#if v.CPolvVideoPic??]
												<img src="${v.CPolvVideoPic }" id="firstImg" />
											[#else]
												<img src="" id="firstImg" style="display:none"/>
											[/#if]
										</div>
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
<script type="text/javascript" src="${static_server}/script/vlink.admin.js"></script>
<script>
	$(document).ready(function (){
		ImageUpload.createUploadImage('importPic', 200, 120 , '[@ui.image url="${v.CPicPath}" /]', function(obj){
			$("#CPicPath").attr("value", obj.data.url);
		}, 0.5);
		
		$(".btnsubmit").click(function(){
			Submit.uploadForm("/admin/video/video_update", "addform",function(result){
				if(result.success == false){
					showErrorDialog(result.msg);
				} else{
					window.location.assign("/admin/video/video_list");
				}
			},null,false,null, false);
		});
	});
	
</script>

<script language="javascript" type="text/javascript">
window.onload = function() {
	setTimeout(function(){
		POLYVJQUERY.polyv.insertHtml = function(thumbnail, html,data) {
			POLYVJQUERY('#videoId').val(data.vid);
			POLYVJQUERY('#mp4Url').val(data.mp4);
			POLYVJQUERY('#swfLinkId').val(data.swf_link);
			POLYVJQUERY('#duration').val(data.duration);
			POLYVJQUERY('#firstImage').val(data.first_image);
			POLYVJQUERY('#firstImg').attr('src',data.first_image).show();
			POLYVJQUERY.polyv.uploading = false;
			POLYVJQUERY(".polyv_uploader").parents(".jquery-dialog:first").find(".dialog-button-close").click();
		}
	}, 2000);
}
</script>