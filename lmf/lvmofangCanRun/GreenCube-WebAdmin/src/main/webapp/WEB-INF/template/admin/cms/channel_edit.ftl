
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
<script src='/script/kindeditor/kindeditor-min.js' type='text/javascript'></script>
<script src='/script/kindeditor/lang/zh_CN.js' type='text/javascript'></script>
	<div class="container-fluid" style="height:800px;">
		<!-- BEGIN PAGE HEADER-->
		<div class="row-fluid">
			<div class="span12">
				<h3 class="page-title">
					栏目信息<small>栏目信息管理</small>
				</h3>
				<ul class="breadcrumb">
					<li><i class="icon-home"></i> <a href="/admin">首页</a> <i
						class="icon-angle-right"></i></li>
					<li>信息发布管理<i class="icon-angle-right"></i>
					</li>
					<li>栏目信息维护</li>
				</ul>
			</div>
		</div>
		<!-- END PAGE HEADER-->
		<!-- BEGIN PAGE CONTENT-->
		<div class="row-fluid">
			<div class="span12">
				<!-- BEGIN SAMPLE FORM PORTLET-->
				<div class="portlet box purple">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-reorder"></i><span class="hidden-480">栏目信息管理</span>
						</div>
					</div>
					<div class="portlet-body form">
						<form id="editform" class="form-horizontal">
							<input type="hidden" name="cid" value="${channel.id}"/>
							<div class="control-group">
								<label class="control-label">栏目名称</label>
								<div class="controls">
									<input type="text" class="span6 m-wrap validate[required]" maxlength="20" name="channel.CName" value="${channel.CName}"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">栏目模板</label>
								<div class="controls">
									<select class="span6 m-wrap validate[required]" data-placeholder="请选择模板" tabindex="1" name="channel.CTemplateName" id="CTemplateName">
										<option value="" [#if channel.CTemplateName == '']selected="selected"[/#if]>无</option>
										<option value="NavArticleCMSTemplate" [#if channel.CTemplateName == 'NavArticleCMSTemplate']selected="selected"[/#if]>类似帮助中心、服务支持、关于鼎尖的布局</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">栏目类别</label>
								<div class="controls">
									<select class="span6 m-wrap validate[required]" data-placeholder="请选择栏目类别" tabindex="1" name="CColumnType" id="CColumnType">
										<option value="News" [#if channel.CColumnType == 'News']selected="true"[/#if]>资讯</option>
										<option value="Adv" [#if channel.CColumnType == 'Adv']selected="true"[/#if]>广告</option>
									</select>
								</div>
							</div>
							
							<div class="control-group" id="dv_CPicSize" style="doisplay:hidden;">
								<label class="control-label">广告图片尺寸</label>
								<div class="controls">
									<input type="text" class="span6 m-wrap" maxlength="10" name="channel.CPicSize" value="${channel.CPicSize}"/>
									<label style="color:red;">填写广告图片尺寸类似于 300x200</label>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">是否可删除</label>
								<div class="controls">
									<select class="span6 m-wrap validate[required]" data-placeholder="请选择栏目类别" tabindex="1" name="channel.NCanDelete" id="NCanDelete">
										<option value="1" [#if channel.NCanDelete == 0]selected="true"[/#if]>可删除</option>
										<option value="0" [#if channel.NCanDelete == 1]selected="true"[/#if]>不可删除</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">栏目描述</label>
								<div class="controls">
									<textarea id="CNr" name="channel.CDesc">${channel.CDesc}</textarea>
	                                <script>
										Utils.createEditor("CNr", "520px", '300px');
									</script>
									<span class="help-inline">对栏目信息的简单描述</span>
								</div>
							</div>
							
							<div class="form-actions">
								<a href="javascript:void(0);" class="btn blue btnsubmit span2">确定</a>
								<a href="javascript:window.history.go(-1);" class="btn btncancel span2">取消</a>
							</div>
						<div style="clear:both;"></div>
						</form>
					</div>
					<div style="clear:both;"></div>
				</div>
				<div style="clear:both;"></div>
				<!-- END SAMPLE FORM PORTLET-->
			</div>
			<div style="clear:both;"></div>
		</div>
		<div style="clear:both;"></div>
		<!-- END PAGE CONTENT-->
	</div>
	<div style="clear:both;"></div>
[/@ui.page]
<script>
	$(document).ready(function (){
		$(".btnsubmit").click(function(){
			Submit.submitForm("/admin/cms/channel_update", "editform",function(result){
				if(result.success == false){
					showErrorDialog(result.msg);
				} else{
					window.location.assign("/admin/cms/channel_list")
				}
			},null,false,null, false);
		});
		
		$('#CColumnType').change(function(){
			var ct = $(this).children('option:selected').val();
			if (ct == "News") {
				$("#dv_CPicSize").hide();
			} else {
				$("#dv_CPicSize").show();
			}
		});
		
		var ct = $('#CColumnType').val();
		if (ct == "News") {
			$("#dv_CPicSize").hide();
		} else {
			$("#dv_CPicSize").show();
		}
	});
	
</script>