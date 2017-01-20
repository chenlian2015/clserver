[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@jqPlugin.plugin date="1" /]
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">编制考试计划</h3>
	
				<ul class="breadcrumb">
					<li>
						<i class="icon-home"></i>编制考试计划
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
									编制考试计划
								</span>
							</div>
						</div>
		
						<div class="portlet-body form">
							<form class="form-horizontal form-row-seperated" id="editform2">
								<input type="hidden" id="areaid" name="areaid" value="${areaid }" />
								<input type="hidden" id="id" name="id" value="${test.id}" />
								<div class="control-group">
									<label class="control-label" for="firstName">所在地区</label>
									<div class="controls" style="margin-top:6px;"><label>${area.CName}</label></div>
								</div>
								<div class="control-group">
									<label class="control-label" for="firstName">对应考试类别</label>
									<div class="controls">
										<select data-placeholder="请选择课程 ..." class="chosen span6"  tabindex="6" id="selectedCourses" name="test.CTestSubjectId">
											[#list subjects as s]
												<option value="${s.id }" [#if test.CTestSubjectId == s.id ]selected="selected"[/#if]>${s.CName }</option>
											[/#list]
										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="firstName">报名时间</label>
									<div class="controls">
										<input type="text" class="span3 m-wrap" name="test.DRegistBeginTime" onclick="WdatePicker();" value='[@fmt.formatDate value=test.DRegistBeginTime  pattern="yyyy-MM-dd" /]'  />
										<span style="margin-left:5px; margin-right:5px; margin-top:15px;">至</span>
										<input type="text" class="span3 m-wrap" name="test.DRegistEndTime" onclick="WdatePicker();" value='[@fmt.formatDate value=test.DRegistEndTime pattern="yyyy-MM-dd" /]'  />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="firstName">考试时间</label>
									<div class="controls">
										<input type="text" class="span3 m-wrap" name="test.DTestBeginTime" onclick="WdatePicker();" value='[@fmt.formatDate value=test.DTestBeginTime pattern="yyyy-MM-dd" /]'  />
										<span style="margin-left:5px; margin-right:5px; margin-top:15px;">至</span>
										<input type="text" class="span3 m-wrap" name="test.DTestENDTime" onclick="WdatePicker();" value='[@fmt.formatDate value=test.DTestENDTime pattern="yyyy-MM-dd" /]'  />
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
			Submit.submitForm("/admin/areatest/test_update", "editform2",function(result){
				if(result.success == false){
					showErrorDialog(result.msg);
				} else{
					window.location.assign("/admin/areatest/test_list");
				}
			},null,false,null, false);
		});
	});
</script>