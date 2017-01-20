[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/chosen.css" />
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/select2_metro.css" />
<script charset="utf-8" src="${static_server}/themes/metro/js/jquery.upload.js"></script>
[@jqPlugin.plugin date="1"/]
		<div class="container-fluid">
			<!-- BEGIN PAGE HEADER-->
			<div class="row-fluid">
				<div class="span12">
					<h3 class="page-title">
						为试卷添加题目
					</h3>
				</div>
			</div>
			<!-- BEGIN PAGE CONTENT-->
			<div class="row-fluid">
				<div class="span12">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-reorder"></i><span class="hidden-480">添加题目</span>
							</div>
						</div>
						<div class="portlet-body form">
							<form id="addform" class="form-horizontal">
								<input type="hidden" name="insid" value="${insid}">
								<table class="table table-striped table-bordered table-hover" id="sample_3">
									<tbody>
										<tr>
											<td width="150">问题：</td>
											<td>
												<input type="text" class="validate[required]" name="name" placeholder="请填写问题">
											</td>
											<td></td>
										</tr>
										<tr>
											<td width="150">答案</td>
											<td>
											       <div class="content">
														<textarea rows="5" cols="50">   </textarea>
			                      	 				</div>
											</td>
											<td></td>
										</tr>
										
										<!-- 遍历课程 -->
										[#list courses as c]
										
											[#assign ischoosed=false ]
											
											[#list vips as v]
												[#if v.CServiceContent == c.id]
													[#assign ischoosed=true ]
												[/#if]
											[/#list]
											
											[#if ischoosed == false]
												<tr class="odd gradeX">
													<td width="20"><input type="checkbox" name="service_course" value="${c.id}"></td>
													<td colspan="2">
														${c.CName}
													</td>
												</tr>
											[/#if]
										[/#list]
									</tbody>
								</table>
								<div class="form-actions" style="background-color: white;">
									<a href="javascript:void(0);" class="btn blue btnsubmit span2" style="width:120px;display:inline-block;">确定</a>
									<a href="javascript:void(0);" class="btn btncancel span2" style="width:120px;display:inline-block;">取消</a>
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
	function showOrHideContent (ts){
		if ($(ts).is(":checked")) {
			$(ts).parents("tr").find(".content").show();
		} else {
			$(ts).parents("tr").find(".content").hide();
		}
	}
	
	$(".btnsubmit").click(function(){
		var logobtn = $('input[name="logo"]:checked');
		if (logobtn.length > 0) {
			if ($("input[name='playerid']").val().length == 0) {
				alert("请输入播放器id");
				return false;				
			}
		}
		
		var freeAccountBtn = $('input[name="service.freeaccount"]:checked');
		if (freeAccountBtn.length > 0) {
			if ($("input[name='service_freeaccount']").val().length == 0) {
				alert("请输入免费账号个数");
				return false;				
			}
		}
		// 提交
		Submit.submitForm('/admin/ins/update_service' , 'addform', function(){
			// 刷新当前页面
			window.location.assign("/admin/ins/vipSetting?id=${insid}");
		}, true);
	});
	$(".btncancel").click(function(){
		window.history.go(-1);
	});
</script>