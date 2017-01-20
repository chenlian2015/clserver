
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]
[@ui.page]
<script type="text/javascript">
$(document).ready(function(){
	 $(".nav").find("li").not(":has(ul)").children("a").css({textDecoration:"none",color:"#333",background:"none"})
	                              .click(function(){
	                            	  $(this).find("i").css("display","none");
								  $(this).get(0).location.href="'"+$(this).attr("href")+"'";
	   });
	$(".nav").find("li:has(ul)").children("a").css({background:"url(images/statu_close.gif) no-repeat left top;"})
                                                  .click(function(){ $(this).find("i").removeClass("icon-plus").addClass("icon-minus");
	     if($(this).next("ul").is(":hidden")){			   
		   $(this).next("ul").slideDown("slow");
		   if($(this).parent("li").siblings("li").children("ul").is(":visible")){
		      $(this).parent("li").siblings("li").find("ul").slideUp("1000");
		      $(this).parent("li").siblings("li:has(ul)").children("a").css({background:"url(images/statu_close.gif) no-repeat left top;"})
			            .end().find("li:has(ul)").children("a").css({background:"url(images/statu_close.gif) no-repeat left top;"});}
		   $(this).css({background:"url(images/statu_open.gif) no-repeat left top;"});
		   return false;
       }else{
		   $(this).next("ul").slideUp("normal");  
		   $(this).css({background:"url(images/statu_close.gif) no-repeat left top;"});
		   $(this).next("ul").children("li").find("ul").fadeOut("normal");
		   $(this).find("i").removeClass("icon-minus").addClass("icon-plus");
		   $(this).next("ul").find("li:has(ul)").children("a").css({background:"url(images/statu_close.gif) no-repeat left top;"});
		   return false;
		 }
      
	 });
});
</script>
	[@jqPlugin.plugin pager="1" /]
	<link rel="stylesheet" type="text/css" href="${static_server}/themes/metro/css/bootstrap-tree.css" />
	<style>
		.selarea {
			color: #FFFFFF !important;
			background-color:red !important;
		}
	</style>
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">题目管理 <small>题目列表</small></h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>题目 
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
							<div class="caption"><i class="icon-cogs"></i>题目概览</div>
							<div class="actions">
								<a href="/admin/train/question_edit?type=1&trainId=${trainId}"  class="btn mini yellow"><i class="icon-plus"></i>&nbsp;添加单选题</a>
								<a href="/admin/train/question_edit?type=2&trainId=${trainId}"  class="btn mini yellow"><i class="icon-plus"></i>&nbsp;添加多选题</a>
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
										<th>题目名称</th>
										<th>题型</th>
										<th nowrap>正确答案</th>
										<th colspan="2">操作</th>
									</tr>
								</thead>
								<tbody>
									<!-- 合计行 -->
									[#list tlist as t]
										<tr class="odd gradeX" >
											<td>
												[@ui.htmlFormat html=t.name length=35 /]
											</td>
											<td nowrap>
												[#if t.type == 1]
													单选题
												[#elseif t.type ==2 ]
													多选题
												[/#if]
											</td>
											<td nowrap>${t.answer } </td>
											<td nowrap>
												<a href="/admin/train/preview/web?questionId=${t.id}" class="btn green mini" target="_blank"><i class="icon-edit"></i> 预览</a>
												<a href="/admin/train/question_edit?questionId=${t.id }&type=${t.type}" class="btn green mini" id="btnEdit"><i class="icon-edit"></i> 修改</a>
												<a href="javascript:void(0);" data-cid="${t.id }" class="btn red bdelete mini"><i class="icon-trash"></i> 删除</a>
											</td>
										</tr>
									[/#list]
								</tbody>
							</table>
							<div id="pagesplit" style="clear:both;"></div>
							<div  style="clear:both;"></div>
							[@ui.pagesplit link="/admin/train/search_question" renderTo="pagesplit" /]
						</div>
					</div>
					<!-- 系统通知公告结束-->
				</div>	
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<div id="select" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modallabel" aria-hidden="true">  
         <div class="modal-header">  
             <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>  
             <h3 id="modallabel">请选择题目类型</h3>  
         </div>
         <form method="post" id="form1" action="">
          <div class="modal-body">  
              <select id="typesel" >
              	<option value ="1">单选题</option>
              	<option value ="2">多选题</option>
              </select>
          </div>
         </form>  
         <div class="modal-footer">  
             <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>  
             <a href="javascript:void(0);"  class="btn blue sel">确定</a>  
         </div>  
    </div> 
[/@ui.page]
<script>
	$(document).ready(function(){
		var selectedTrain = "${trainId}";
		$("#" + selectedTrain).addClass("selarea");
		
		$(".sel").on("click",function(){
			var type = $("#typesel option:selected").val();
			window.location.assign("/admin/train/question_edit?type="+type);
		});
		$(".bdelete").on("click",function(){
			if (window.confirm("您确定要删除该题目吗？")){
				var selId = $(this).attr("data-cid");
				Submit.invok("/admin/train/question_delete?questionId="+selId,function(){
					window.location.reload(true);
				});				
			}
		});
		
		$(".btnSearch").on("click",function(){
			var ttype = $("#topicType option:selected").val();
			var tnum = $("#topicNum").val();
			window.location.assign("/admin/train/search_question?ttype="+ttype+"&tnum="+tnum);
		});
		
		$("#btnEdit").click(function(){
			url = $(this).attr("href");
			if ($("#topicNum").val().length > 0){
				url += "&tnum=" + $("#topicNum").val();
			}
			if ($("#topicType").val().length > 0){
				url += "&ttype=" + $("#topicType").val();
			}
			window.location.assign(url);
			return false;
		});
	});
</script>