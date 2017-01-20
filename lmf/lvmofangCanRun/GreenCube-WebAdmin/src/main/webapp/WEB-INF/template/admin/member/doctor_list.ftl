
[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]

[@ui.page]
	[@ui.plugin plugin="pagesplit"][/@ui.plugin]
	<div class="container-fluid">
		<!-- 页面头部 -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 页面头部定义-->
				<h3 class="page-title">医生概览</h3>
	
				<ul class="breadcrumb"-->>
					<li>
						<i class="icon-home"></i>医生概览
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
				<div class="span12" responsive" data-tablet="span12 fix-offset" data-desktop="span6">
					<!-- 医生概览-->
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption"><i class="icon-cogs"></i>医生概览</div>
							<div class="actions">
							     <input type="file" id="file" style="display:none"> 
									<a href="#" class="btn blue uploadExcel"><i class="icon-plus"></i>&nbsp;导入</a>
									<OBJECT classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 id=WebBrowser width=0></OBJECT>
							</div>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_3">
								<thead>
									<tr>
									   <th>图片</th>
										<th>医生姓名</th>
										<th>性别</th>
										<th>职称</th>
										<th>职务</th>
										<th>科室</th>
										<th>所属医院</th>
										<!--<th>简介</th>
										<th>擅长治疗疾病</th>-->
									</tr>
								</thead>
								<tbody>
									<!-- 合计行 -->
										[#list fadocDoctor as fd]
										<tr class="odd gradeX">
										   <td>${fd["imgUrl"] }</td>
											<td>${fd["name"]}</td>
											<td>${fd["sex"]}</td>
											<td>${fd["jobTitle"] }</td>
											<td>${fd["jobPost"] }</td>
											<td>${fd["hospitalDepartmentName"] }</td>
											<td>${fd["hospitalName"] }</td>
											<!--<td>${fd["desc"] }</td>
											<td>${fd["goodTreatmentDiseases"] }</td>-->
										</tr>
									[/#list]
								</tbody>
							</table>
							<div id="pagesplit"></div>
							[@ui.pagesplit link="/admin/book/book_list" renderTo="pagesplit" /]
						</div>
					</div>
					<!-- 系统通知公告结束-->
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	
	  <div style="display: none;" id="examdialog" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modallabel" aria-hidden="true">  
          <div class="modal-header">  
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>  
              <h3 id="modallabel">请选择要上传组卷试题的Excel</h3>  
          </div>
          <form method="post" id="form_exam" action="/admin/member/import_excel_fadocdoctor" enctype="multipart/form-data">
	           <div class="modal-body">  
	               <table border="0" cellpadding="0" cellspacing="0" width="100%">
	               		<tbody><tr>
	               			<td nowrap="">请选择Excel文件：</td>
	               			<td><input name="excelFile" id="excelFile" type="file"></td>
	               		</tr>
	               </tbody></table>
	           </div>
          
	          <div class="modal-footer">  
	              <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>  
	              <button type="submit" class="btn blue">开始导入</button>  
	          </div>  
          </form>  
     </div>
     
     
[/@ui.page]
<script>
	$(document).ready(function(){
		DynamicTime.init("dynamictime");
		$(".bdelete").on("click",function(){
			if (window.confirm("您确定要删除该条图书信息吗？")){
				var selId = $(this).attr("data-cid");
				Submit.submit("/admin/member/import_excel_fadocdoctor",function(){
					window.location.reload(true);
				});				
			}
		});
		
		$(".uploadExcel").on("click",function(){
			$("#examdialog").modal("show");
		});
	});
	 
</script>