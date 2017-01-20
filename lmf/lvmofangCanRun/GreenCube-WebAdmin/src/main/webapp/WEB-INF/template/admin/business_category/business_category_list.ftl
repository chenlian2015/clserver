[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]
[@ui.page]
	<link href="${static_server}/themes/metro/css/search.css" rel="stylesheet" type="text/css"/>
	<style>
		.selarea {
			color: red !important;
		}
		.branch{
			margin-left:40px;
			margin-top:10px;
		}
		li{
			list-style: none;
		}
	</style>
	<div class="container-fluid">
		<div class="row-fluid">
			<div>
				<h3 class="page-title">类别管理</h3>
				<ul class="breadcrumb">
					<li>类别设置</li>
				</ul>
			</div>
		</div>
		
		<div class="row-fluid">
				<div class="span12">
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-comments"></i>类别管理
							</div>
							<div class="actions">
								<a href="javascript:void(0);" class="btn blue btnAddRoot"><i class="icon-plus"></i>&nbsp;添加类别</a>
							</div>
						</div>
						<form id="addform" class="form-horizontal">
							<div class="portlet-body fuelux" id="tree_area">
									${treeHtml }
							</div>
							<div class="form-actions blue">
								<input type="hidden" name="outline" id="outline">
								<input type="hidden" name="parentId" value="${id}">
								<a href="javascript:void(0);" id="btnsubmit" class="btn blue btnsubmit span2">保存</a>
								<a href="javascript:window.history.go(-1);" class="btn btncancel span2">返回</a>
							</div>
						</form>
					</div>
				</div>
				
			</div>
		</div>
	</div>
[/@ui.page]
<script>
	var idx = 0;
	$(".btnAddRoot").click(function(){
		$("<ul><li class=\"branch\" id='node_" + idx + "'>名称：<input type='text' data-role='name'><input type='hidden' data-role='id'><span style='margin-left:10px;'>排序：<input style='width:40px;' type='text' data-role='order'></span><div style=\"display: inline-block;margin-left:20px;cursor:pointer;color:blue;\" class=\"add_chapter\" style='z-index:99999' onclick=\"addChildRoot('" + idx + "');\">增加</div> | <div style=\"display: inline-block;cursor:pointer;color:blue;\" class=\"add_chapter\" style='z-index:99999' onclick=\"removeUl(this);\">删除</div></li></li></ul>").appendTo($("#tree_area"));
		idx ++;
	});
	
	function addChildRoot(currentIdx){
		var currentId = currentIdx + "_" + idx;
		$("<ul><li class=\"branch\" id='node_" + currentId + "'>名称：<input type='text' data-role='name'><input type='hidden' data-role='id'><span style='margin-left:10px;'>排序：<input type='text' style='width:40px;' data-role='order'></span><div style=\"display: inline-block;margin-left:20px;cursor:pointer;color:blue;\" class=\"add_chapter\" style='z-index:99999' onclick=\"addChildRoot('" + currentId + "');\">增加</div> | <div style=\"display: inline-block;cursor:pointer;color:blue;\" class=\"add_chapter\" style='z-index:99999' onclick=\"removeUl(this);\">删除</div></li></li></ul>").appendTo($("#node_" + currentIdx));
		idx ++;
	}
	//保存
	$(".btnsubmit").click(function(){
		rootNodes = makeJson();
		$("#outline").attr("value", JSON.stringify(rootNodes));
		Submit.submitForm('/admin/business_category/category_update' , 'addform', function(result){
			if (result.success == true) {
				window.location.reload();
			} else {
				showErrorDialog(result.msg);
			}
		});
	});
	
	function makeJson(){
		var uls = $("#tree_area").children("ul");
		
		var rootNodes = new Array();
		
		if (uls.length > 0){
			$.each(uls, function(index,elUl){
				cname = $(elUl).children("li").children("input[data-role='name']").attr("value");
				cid = $(elUl).children("li").children("input[data-role='id']").attr("value");
				classCode = $(elUl).children("li").children("input[data-role='classCode']").attr("value");
				order = $(elUl).children("li").children("span").children("input[data-role='order']").attr("value");
				
				rootNode = {};
				rootNode["name"] = cname;
				rootNode["id"] = cid;
				rootNode["classCode"] = classCode;
				rootNode["order"] = order;
				rootNode["subCategory"] = new Array();
				rootNodes.push(rootNode);
				subUls = $(elUl).children("li").children("ul");
				if (subUls.length > 0){
					$.each(subUls, function(index,el){
						getJsonValue(rootNode, $(el));
					});
				}
			});
		}
		return rootNodes;
	}
	
	function getJsonValue(parentNode, elUl) {
		var cname = elUl.children("li").children("input[data-role='name']").attr("value");
		var cid = elUl.children("li").children("input[data-role='id']").attr("value");
		var classCode = $(elUl).children("li").children("input[data-role='classCode']").attr("value");
		var order = elUl.children("li").children("span").children("input[data-role='order']").attr("value");
		var childNode = {};
		childNode["name"] = cname;
		childNode["id"] = cid;
		childNode["classCode"] = classCode;
		childNode["order"] = order;
		childNode["subCategory"] = new Array();
		
		parentNode["subCategory"].push(childNode);
		var subUls = elUl.children("li").children("ul");
		
		if (subUls.length > 0){
			$.each(subUls, function(index,el){
				getJsonValue(childNode, $(el));
			});
		}
	}
	
	function removeUl(ts){
		$(ts).parent().parent().remove();
	}
	//删除
	function removeNode(id,classCode,ts){
	  if(id=="")
	  {
	   $(ts).parent().parent().remove();
	  }
	  else{
	  	Submit.invok('/admin/business_category/category_delete?id=' + id+'&classCode='+classCode, function(result){
		if(result.success==true)
		{
		// 删除掉格子
			$(ts).parent().parent().remove();
		}
		});
	  }
	
	}
	
	/**
	 * 层级左移
	 */
	function moveLeft (ts, nodeid) {
		Submit.invok('/admin/business_category/setting/moveLeft?id=' + nodeid, function(){
			window.location.reload(true);
		});
	}
</script>
