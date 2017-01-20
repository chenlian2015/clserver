
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="datepicker"/>
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">修改县市<small>修改县市</small></h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>修改县市
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

		<div id="dashboard">
			<div class="row-fluid">
				<div class="span12">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-reorder"></i>
								<span class="hidden-480">
									修改县市
								</span>
							</div>
						</div>
		
						<div class="portlet-body form">
							<form class="form-horizontal form-row-seperated" id="editform2">
								<input type="hidden" id="CParentid" name="city.CParentid" value="${city.CParentid }" />
								<input type="hidden" id="id" name="id" value="${city.id}" />
								<div class="control-group">
									<label class="control-label" for="firstName">行政区号</label>
									<div class="controls">
										<input type="text" id="CResourcesid" class="m-wrap span6 validte[required]"  maxlength="20" name="city.CResourcesid" value="${city.CResourcesid}" >
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="firstName">行政名称</label>
									<div class="controls">
										<input type="text" id="CResourcesname" class="m-wrap span6" name="city.CResourcesname" class="bar validte[require]" maxlength="20" value="${city.CResourcesname}" >
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="firstName">是否使用</label>
									<div class="controls">
										<select  class="m-wrap span6" id="CIsshow" name="city.CIsshow">
											<option value="1" [#if city.CIsshow=='1']selected[/#if] >是</option>
											<option value="0" [#if city.CIsshow=='0']selected[/#if] >否</option>
										</select>
									</div>
								</div>
								
								<div class="form-actions">
									<button type="button" class="btn blue" id="btnsubmit"><i class="icon-ok"></i> 提交</button>
									<button type="button" class="btn red"  onclick="window.history.go(-1);"><i class="icon-cancel"></i> 取消</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
[/@ui.page]
<script>
	$(document).ready(function(){
		DynamicTime.init("dynamictime");
		$('#btnsubmit').on('click', function(){
			var xxid = $("#xxid").val();
			Submit.submitForm("/admin/area/updateCity", "editform2",function(result){
				if(result.success == false){
					showErrorDialog(result.msg);
				} else{
					window.location.assign("/admin/area/getAllAreas");
				}
			},null,false,null, false);
		});
	});
</script>