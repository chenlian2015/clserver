
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/chosen.css" />
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/select2_metro.css" />
<script type="text/javascript" src="${static_server}/script/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${static_server}/themes/metro/js/jquery.upload.js"></script>
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/select2_metro.css" />

<style>
	.movie-result{
	}
	.movie-result td:hover{
		background-color:#DADADA;
	}
</style>
[@ui.plugin plugin="upload"][/@ui.plugin]
[@jqPlugin.plugin date="1"/]

		<div class="container-fluid">
			<!-- BEGIN PAGE HEADER-->
			<div class="row-fluid">
				<div class="span12">
					<h3 class="page-title">
						添加题目
					</h3>
					<ul class="breadcrumb">
						<li><i class="icon-home"></i><a href="/admin">首页</a> <i
							class="icon-angle-right"></i></li>
						<li>题目管理<i class="icon-angle-right"></i>
						</li>
						<li>创建选择题题目</li>
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
								<i class="icon-reorder"></i><span class="hidden-480">创建题目</span>
							</div>
						</div>
						<div class="portlet-body form">
							<form id="addform" class="form-horizontal">
								<input type="hidden" name="t.trainId" value="${t.trainId}">
								<input type="hidden" name="questionId" value="${t.id}">
								<input type="hidden" name="t.type" value="${type }" />
								<div class="control-group">
									<label class="control-label">题目</label>
									<div class="controls">
										<textarea id="name" name="t.name">${t.name}</textarea>
		                                <script>
											Utils.createEditor("name", "100%", '100px');
										</script>
									</div>
								</div>
								[#if t.id??]
									[#list itemlist as il]
											<div class="control-group">
												<label class="control-label"><input type="radio" [#if t.answer == il.itemSeq ]checked[/#if] name="t.answer" value="${il.itemSeq }" />${il.itemSeq } <input type="hidden"  name="itemSeq" value="${il.itemSeq }" /></label>
												<div class="controls">
													<textarea id="name_${il_index }" class="items" index="${il_index+1 }" name="name">${il.optionItem }</textarea>
					                                <script>
														Utils.createEditor("name_${il_index }", "100%", '100px');
													</script>
												</div>
											</div>
									[/#list]
								[#else]
										<div class="control-group">
											<label class="control-label"><input type="radio" name="t.answer" value="A" />A: <input type="hidden" name="itemSeq" value="A" /></label>
											<div class="controls">
												<textarea id="name1"  class="items" name="name">${il.optionItem }</textarea>
				                                <script>
													Utils.createEditor("name1", "100%", '100px');
												</script>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label"><input type="radio" name="t.answer" value="B" />B: <input type="hidden" name="itemSeq" value="B" /></label>
											<div class="controls">
												<textarea id="name2" class="items"  name="name">${il.optionItem }</textarea>
				                                <script>
													Utils.createEditor("name2", "100%", '100px');
												</script>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label"><input type="radio" name="t.answer" value="C" />C: <input type="hidden" name="itemSeq" value="C" /></label>
											<div class="controls">
												<textarea id="name3" class="items"  name="name">${il.optionItem }</textarea>
				                                <script>
													Utils.createEditor("name3", "100%", '100px');
												</script>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label"><input type="radio" name="t.answer" value="D" />D: <input type="hidden" name="itemSeq" value="D" /></label>
											<div class="controls">
												<textarea id="name4" class="items"  name="name">${il.optionItem }</textarea>
				                                <script>
													Utils.createEditor("name4", "100%", '100px');
												</script>
											</div>
										</div>
								[/#if]
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
			var length = $("input[type=radio]:checked").length;
			if(length == 0){
				alert("请至少选择一个答案！");
				return false;
			}
			
			Submit.submitForm("/admin/train/question_update", "addform",function(result){
				if(result.success == false){
					showErrorDialog(result.msg);
				} else{
					window.location.assign("/admin/train/question_list?trainId=${t.trainId}");
				}
			},null,false,null, false);
		});
	});
</script>