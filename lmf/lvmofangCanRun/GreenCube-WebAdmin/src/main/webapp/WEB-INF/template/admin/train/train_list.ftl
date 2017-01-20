[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
<script charset="utf-8" src="${static_server}/themes/metro/js/jquery.upload.js"></script>
<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/bootstrap-tree.css" />
[@ui.plugin plugin="pagesplit"][/@ui.plugin]
<style>
	.selarea {
			color: #FFFFFF !important;
			background-color:red !important;
		}
	.tree>li{
		width:190px;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
	}
</style>
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">培训管理 <small>培训列表</small></h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>培训 
					</li>
				</ul>
			</div>
		</div>

		<div id="dashboard">
			<div class="row-fluid">
				<div class="span3">
					<div>
						<div class="portlet box purple">
							<div class="portlet-title">
								<div class="caption"><i class="icon-comments"></i>培训分类</div>
							</div>
							<div class="portlet-body fuelux">
								<ul class="tree" id="tree_1">
									${treeHtml }
								</ul>
							</div>
						</div>
					</div>
				</div>
				
				<div class="span9" responsive" data-tablet="span12 fix-offset" data-desktop="span6">
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="icon-cogs"></i>培训列表</div>
							<div class="actions">
								<a href="javascript:void(0);" data-toggle="modal" class="btn blue addTrain"><i class="icon-plus"></i>&nbsp;添加</a>
								<a href="/downloads/test-paper-template.xlsx"  class="btn red"><i class="icon-download"></i>&nbsp;下载模板</a>
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
										<th>培训名称</th>
										<th>题目总数</th>
										<th>培训说明</th>
										<th>创建日期</th>
										<th colspan="2" width="120px">操作</th>
									</tr>
								</thead>
								<tbody>
									<!-- 合计行 -->
									[#list elist as e]
										<tr class="odd gradeX">
											<td>${e.name }</td>
											<td>${e.topicCount }</td>
											<td>${e.desc }</td>
											<td>[@fmt.formatDate value=e.createTime  pattern="yyyy-MM-dd"/]  </td>
											<td nowrap>
												<a href="/admin/train/train_preview?trainId=${e.id }" class="btn mini preview" target="_blank"><i class="icon-eye-open"></i>培训预览</a>
												<a href="/admin/train/train_edit?trainId=${e.id }" class="btn green mini"><i class="icon-edit"></i> 修改</a>
												<a href="javascript:void(0);" data-cid="${e.id }" class="btn red bdelete mini"><i class="icon-trash"></i> 删除</a>
												<a href="javascript:void(0);" data-cid="${e.id }" class="btn uploadExam blue mini"><i class="icon-upload"></i> 上传试题</a>
											</td>
										</tr>
									[/#list]
								</tbody>
							</table>
							<div id="pagesplit"></div>
							[@ui.pagesplit link="/admin/train/train_list" renderTo="pagesplit" /]
						</div>
					</div>
					<!-- 系统通知公告结束-->
				</div>	
			</div>
			<div class="clearfix"></div>
		</div>
	</div> 
	<div id="dialog" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modallabel" aria-hidden="true">  
          <div class="modal-header">  
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>  
              <h3 id="modallabel">请输入各种题型的数量</h3>  
          </div>
          <form method="post" id="form1" action="">
          <input type="hidden" name="trainId" id="trainId" value="" />
           <div class="modal-body">  
               <table cellpadding="0" cellspacing="0" border="0" width="100%">
               		<tr>
               			<td>单选题数量</td>
               			<td><input type="text"  name="simpleCount" value="" />&nbsp;<span style="color:red">题库中单选题总数量为<span id="sc"></span>道</span></td>
               		</tr>
               		<tr>
               			<td>多选题数量</td>
               			<td><input type="text"  name="mulCount" value="" />&nbsp;<span style="color:red">题库中多选题总数量为<span id="mc"></span>道</span></td>
               		</tr>
               		<tr>
               			<td>判断题数量</td>
               			<td><input type="text"  name="judgeCount" value="" />&nbsp;<span style="color:red">题库中判断题总数量为<span id="jc"></span>道</span></td>
               		</tr>
               		<tr>
               			<td>简答题数量</td>
               			<td><input type="text"  name="qaCount" value="" />&nbsp;<span style="color:red">题库中主观题总数量为<span id="qc"></span>道</span></td>
               		</tr>
               </table>
           </div>
          </form>  
          <div class="modal-footer">  
              <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>  
              <a href="javascript:void(0);"  class="btn blue topic">组卷</a>  
          </div>  
     </div>
     <div id="traindialog" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modallabel" aria-hidden="true">  
          <div class="modal-header">  
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>  
              <h3 id="modallabel">请选择要上传组卷试题的Excel</h3>  
          </div>
          <form method="post" id="form_train" action="/admin/train/upload_topic" enctype="multipart/form-data">
	          <input type="hidden" name="trainId" id="testExamId" value="" />
	           <div class="modal-body">  
	               <table cellpadding="0" cellspacing="0" border="0" width="100%">
	               		<tr>
	               			<td nowrap>请选择Excel文件：</td>
	               			<td><input type="file" name="excelFile" id="excelFile"  /></td>
	               		</tr>
	               </table>
	           </div>
          
	          <div class="modal-footer">  
	              <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>  
	              <button type="submit"  class="btn blue trainCombie">组卷</button>  
	          </div>  
          </form>  
     </div>
[/@ui.page]
<script>
	var selectedCategory = "${categoryId}";
	$(document).ready(function(){
		var categoryId = "${categoryId}";
		$("#" + categoryId).addClass("selarea");
		
		$(".addTrain").click(function(){
			if (selectedCategory == null) {
				window.alert("请先选择分类，然后再添加培训");
				return;
			}
			window.location.assign("/admin/train/train_edit?categoryId=" + selectedCategory);	
		});
		
		$(".bdelete").on("click",function(){
			if (window.confirm("您确定要删除该题目吗？")){
				var selId = $(this).attr("data-cid");
				Submit.invok("/admin/train/train_delete?trainId="+selId,function(){
					window.location.reload(true);
				});				
			}
		});
		
		$(".auto").on("click",function(){
			var cid = $(this).attr("data-id");
			Submit.invok("/admin/train/auto_combin",function(result){
				var countArr = result.data.split(",");
				$("#sc").html(countArr[0]);
				$("#mc").html(countArr[1]);
				$("#jc").html(countArr[2]);
				$("#qc").html(countArr[3]);
				$("#trainId").val(cid);
				$("#dialog").modal("show");
			});
			
		});
		
		$("#excelFile").on("change",function(){
			$("#filetxt").html($(this).val());
		});
		
		$(".uploadExam").on("click",function(){
			var trainId = $(this).attr("data-cid");
			$("#testExamId").val(trainId);
			$("#traindialog").modal("show");
		});
	});

</script>