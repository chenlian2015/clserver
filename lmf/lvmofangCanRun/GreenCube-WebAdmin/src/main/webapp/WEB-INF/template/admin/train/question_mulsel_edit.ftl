[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]
[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]

[@ui.page]
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/chosen.css" />
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/select2_metro.css" />
<script type="text/javascript" src="${static_server}/script/kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="${static_server}/themes/metro/js/jquery.upload.js"></script>
<style>
</style>
[@ui.plugin plugin="upload"][/@ui.plugin]
[@jqPlugin.plugin date='1'/]

		<div class="container-fluid">
			<!-- BEGIN PAGE HEADER-->
			<div class="row-fluid">
				<div class="span12">
					<h3 class="page-title">
						添加多选题目
					</h3>
					<ul class="breadcrumb">
						<li><i class="icon-home"></i><a href="/admin">首页</a> <i
							class="icon-angle-right"></i></li>
						<li>多选题目管理<i class="icon-angle-right"></i>
						</li>
						<li>创建多项选择题题目</li>
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
								<i class="icon-reorder"></i><span class="hidden-480">创建多项选择题目</span>
							</div>
						</div>
						<div class="portlet-body form">
							<form id="addform" class="form-horizontal">
								<input type="hidden" name="t.trainId" value="${t.trainId}">
								<input type="hidden" name="questionId" value="${t.id}">
								<input type="hidden" name="t.type" value="${type }" />
								<input type="hidden" id="an" name="t.answer" value ="${t.answer }" />
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
												<label class="control-label"><input type="checkbox" [#if t.answer?contains(il.itemSeq) ]checked[/#if] name="answer" value="${il.itemSeq }" />${il.itemSeq } <input type="hidden"  name="itemSeq" value="${il.itemSeq }" /></label>
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
											<label class="control-label"><input type="checkbox" name="answer" value="A" />A: <input type="hidden" name="itemSeq" value="A" /></label>
											<div class="controls">
												<textarea id="name1" index="1" class="items" name="name">${il.optionItem }</textarea>
				                                <script>
													Utils.createEditor("name1", "100%", '100px');
												</script>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label"><input type="checkbox" name="answer" value="B" />B: <input type="hidden" name="itemSeq" value="B" /></label>
											<div class="controls">
												<textarea id="name2" index="2" class="items"  name="name">${il.optionItem }</textarea>
				                                <script>
													Utils.createEditor("name2", "100%", '100px');
												</script>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label"><input type="checkbox" name="answer" value="C" />C: <input type="hidden" name="itemSeq" value="C" /></label>
											<div class="controls">
												<textarea id="name3" index="3"  class="items" name="name">${il.optionItem }</textarea>
				                                <script>
													Utils.createEditor("name3", "100%", '100px');
												</script>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label"><input type="checkbox" name="answer" value="D" />D: <input type="hidden" name="itemSeq" value="D" /></label>
											<div class="controls">
												<textarea id="name4" index="4"  class="items" name="name">${il.optionItem }</textarea>
				                                <script>
													Utils.createEditor("name4", "100%", '100px');
												</script>
											</div>
										</div>
								[/#if]
								
								<div class="control-group">
									<div class="controls">
										<a href="javascript:void(0);" class="btn green addItems">添加选项</a>
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
			
			var length = $("input[type=checkbox]:checked").length;
			if(length == 0){
				alert("请至少选择一个答案！");
				return false;
			}
			
			var answer ="";
			$("input[name=answer]:checked").each(function(){
				var an =  $(this).attr("value");
				answer +=an;
			});
			$("#an").val(answer);
			Submit.submitForm("/admin/train/question_update", "addform",function(result){
				if(result.success == false){
					showErrorDialog(result.msg);
				} else{
					window.location.assign("/admin/train/question_list?trainId=${t.trainId}");
				}
			},null,false,null, false);
		});
		
		 
		 $(".addItems").on("click",function(){
			 var arr = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N'];
			 var index = $(".items:last").attr("index");
			 var i = parseInt(index)+1;
			 var html="<div class='control-group' ><label class='control-label'><div class='checker'><span><span><input type='checkbox'  class='newItem' name='answer' value='"+arr[index]+"' /></div>"+arr[index]+": <input type='hidden' name='itemSeq' value='"+arr[index]+"' /></label>"
			 +"<div class='controls'><textarea id='name" + i + "' index='"+i+"'  class='items' name='name'></textarea>"
			 +"<a href='javascript:void(0);' style='vertical-align: middle;margin-top: 5px;margin-left: 10px' data-id='' data-topic='' class='btn mini remove'>X</i></a></div></div>";
			 $(this).parent().parent().before(html);
			 Utils.createEditor('name'+i, '100%', '100px');
		 });
		 $(".remove").live("click",function(){
			$(this).parent().parent().remove();
		 });
		 $(".newItem").live("click",function(){
			 $(this).parent().toggleClass("checked");
		 });
	});
	
	
</script>