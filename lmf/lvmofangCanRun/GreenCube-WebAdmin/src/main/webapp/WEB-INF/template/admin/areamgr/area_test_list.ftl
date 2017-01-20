[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	<link href="${static_server}/themes/metro/css/jquery.nestable.css" rel="stylesheet" type="text/css"/>
	<style>
		.dd-my{
			cursor: default !important;
		}
	</style>
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">各地区会计考试管理 <small>各地区列表</small></h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>各地区会计考试
					</li>
					<li class="pull-right no-text-shadow">
						<div id="dashboard-report-range" class="dashboard-date-range responsive"  style="display: block;">
							<i class="icon-calendar"></i>
							<span id="dynamictime"></span>
						</div>
					</li>
					
				</ul>
			</div>
		</div>
				
		<div class="row-fluid">
			<div class="span12">
				<div class="portlet box purple">
					<div class="portlet-title">
						<div class="caption"><i class="icon-cogs"></i>各地区会计考试</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
					<div class="portlet-body">
						<div class="dd" id="nestable_list_1">
						 	<ol class="dd-list">
								[#list areas as area]
									<li class="dd-item" data-id="${area.CName }" >
										<div class="dd-my">${area.CName }
											[#if area.tests??] 
												<span style="color:blue;">[已添加 ${area.tests?size}个考试 ]</span>
											[/#if]											
											<span style="float:right"><a href="/admin/areatest/test_edit?areaid=${area.id}"  style="color:red"><i class="icon-plus"></i>添加考试计划</a></span>
										</div>
										[#if area.tests??] 
											<ol class="dd-list">
												[#list area.tests as test ]
													<a href="/admin/areatest/test_edit?id=${test.id}">${test.CTestSubjectName} ,</a>
												[/#list]
											</ol>
										[/#if]
											[#if area.NIsProvinceCity != 1]
												<ol class="dd-list">
													[#list area.subAreas as sub ]
														<li class="dd-item" data-id="${sub.CName }" >
															<div class="dd-my">${sub.CName } <span style="color:blue;">[已添加 ${sub.tests?size}个考试 ]</span>
																<span style="float:right"><a href="/admin/areatest/test_edit?areaid=${sub.id}"  style="color:red"><i class="icon-plus"></i>添加考试计划</a></span>
															</div>
															[#if sub.tests??]
																<ol class="dd-list">
																	[#list sub.tests as test]
																		<a href="/admin/areatest/test_edit?id=${test.id}">${test.CTestSubjectName} ,</a>
																	[/#list]
																</ol>
															[#else]
															
															[/#if]
														</li>
														[/#list]
												</ol> 
											[/#if]
									</li>
								[/#list]
							</ol>  
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>

	</div>
[/@ui.page]
	<script src="${static_server}/themes/metro/js/jquery.nestable.js" type="text/javascript"></script>
<script>
	$(document).ready(function(){
		DynamicTime.init("dynamictime");
		$('#nestable_list_1').nestable({
            group: 1
        });
		$('.dd').nestable('collapseAll');
	});
	function deletecity(id){
		Submit.invok("/admin/area/deleteCity?id=" +id, function() {
			window.location.reload(true);
		});
	}

	
</script>