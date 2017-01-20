[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
[@jqPlugin.plugin date="1"/]
[@jqPlugin.plugin uploadify="1"/]

	<link rel="stylesheet" href="${static_server}/script/kindeditor/themes/default/default.css" />
<script src='/script/kindeditor/kindeditor.js' type='text/javascript'></script>
<script src='/script/kindeditor/lang/zh_CN.js' type='text/javascript'></script>
	<div class="container-fluid" style="height:800px;">
		<!-- BEGIN PAGE HEADER-->
		<div class="row-fluid">
			<div class="span12">
				<h3 class="page-title">
					资讯管理<small> 添加资讯</small>
				</h3>
				<ul class="breadcrumb">
					<li><i class="icon-home"></i> <a href="/admin">首页</a> <i
						class="icon-angle-right"></i></li>
					<li>信息发布管理<i class="icon-angle-right"></i>
					</li>
					<li>添加资讯</li>
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
							<i class="icon-reorder"></i><span class="hidden-480">
								[#if column??]
									修改资讯信息
								[#else]
									${channel.CName } - 添加资讯		
								[/#if]
							</span>
						</div>
					</div>
					<div class="portlet-body form">
						<form id="editform" class="form-horizontal">
							<input type="hidden" name="id" value="${article.id}"/>
							<input type="hidden" name="columnId" value="${columnId}"/>
							<div class="control-group">
								<label class="control-label">所属栏目</label>
								<div class="controls">
									<label style="line-height:35px;">${columnName }</label>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">首图</label>
								<div class="controls">
									<input type="hidden" name="b.firstPicture" value="${b.firstPicture }" id="firstPicture">
	                      	 		<input type="file" class="" name="pic" id="pic">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">文章模板</label>
								<div class="controls">
									<select class="span6 m-wrap validate[required]" data-placeholder="请选择模板" tabindex="1" name="article.CTemplateName" id="CTemplateName">
										<option value="WholeArticleCMSTemplate" [#if article.templateName == 'WholeArticleCMSTemplate']selected="selected"[/#if]>简单头+新闻+尾布局</option>
										<option value="AritcleAndAdvCMSTemplate" [#if article.templateName == 'AritcleAndAdvCMSTemplate']selected="selected"[/#if]>含菜单头+左侧新闻+右侧广告+右侧下部排行</option>
										<option value="WholeArticleCMSTemplate2" [#if article.templateName == 'WholeArticleCMSTemplate2']selected="selected"[/#if]>类似注册协议的布局</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">显示顺序</label>
								<div class="controls">
									<input type="text" class="span6 m-wrap validate[required,custom[number]]"  name="article.order" value="${article.order}"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">文章标题</label>
								<div class="controls">
									<input type="text" class="span6 m-wrap validate[required]" maxlength="50" name="article.title" value="${article.title}"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">来源</label>
								<div class="controls">
									<input type="text" class="span6 m-wrap validate[required]" maxlength="50" name="article.author" value="${article.author}"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">编辑方式</label>
								<div class="controls">
									<select class="span6 m-wrap validate[required]" data-placeholder="请选择编辑器" name="article.editorType" id="CEditorType">
										<option value="1" [#if article.editorType == '1']selected="selected"[/#if]>Editor</option>
										<option value="2" [#if article.editorType == '2']selected="selected"[/#if]>纯文本</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">内容</label>
								<div class="controls">
									<textarea  name="article.content" id="CContent" style="width:600px;height:300px;">${article.content}</textarea>
								</div>
							</div>
							<div class="form-actions">
								<a href="javascript:void(0);" class="btn blue btnsubmit span2">确定</a>
								<a href="javascript:window.history.go(-1);" class="btn btncancel span2">取消</a>
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
<script>
	$(document).ready(function (){
		TImageUpload.createUploadImage('pic', 200, 120 , '[@ui.image url="${b.firstPicture}"/]', function(obj){
			$("#firstPicture").attr("value", obj.data.url);
		}, 0.5);
		
		$(".btnsubmit").click(function(){
			Submit.submitForm("/admin/cms/article_update", "editform",function(result){
				if(result.success == false){
					showErrorDialog(result.msg);
				} else{
					window.location.assign("/admin/cms/article_list?columnId=" + result.msg);
				}
			},null,false,null, false);
		});
		
		[#if article.editorType == '1']
			Utils.createEditor("CContent", "520px", '300px');
		[/#if]
	});
</script>
