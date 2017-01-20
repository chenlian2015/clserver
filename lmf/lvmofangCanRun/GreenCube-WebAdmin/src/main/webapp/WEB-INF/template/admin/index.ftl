[#assign ui=JspTaglibs["/WEB-INF/classes/tld/ui.tld"] /]
[#assign fmt=JspTaglibs["/WEB-INF/classes/tld/fmt.tld"] /]
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<head>
	<meta charset="utf-8" />
	<title>${systemName}</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="${systemDescription}" name="description" />
	<meta content="${systemAuthor}" name="author" />
	<!-- 全局样式定义 -->
	<link href="${static_server}/themes/metro/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="${static_server}/themes/metro/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="${static_server}/themes/metro/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="${static_server}/themes/metro/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="${static_server}/themes/metro/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="${static_server}/themes/metro/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="${static_server}/themes/metro/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="${static_server}/themes/metro/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<link rel="shortcut icon" href="${static_server}/themes/metro/image/favicon.ico" />
  	<!-- 核心插件 -->        	
  	<script src="${static_server}/themes/metro/js/jquery-1.10.1.min.js" type="text/javascript"></script>	
  	<script src="${static_server}/themes/metro/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>	
  	<script src="${static_server}/themes/metro/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>	
  	<script src="${static_server}/themes/metro/js/bootstrap.min.js" type="text/javascript"></script>	
  	<!--[if lt IE 9]>	
  	<script src="${static_server}/themes/metro/js/excanvas.min.js"></script>	
  	<script src="${static_server}/themes/metro/js/respond.min.js"></script>  	
  	<![endif]-->   	
  	<script src="${static_server}/themes/metro/js/jquery.slimscroll.min.js" type="text/javascript"></script>	
  	<script src="${static_server}/themes/metro/js/jquery.blockui.min.js" type="text/javascript"></script>  	
  	<script src="${static_server}/themes/metro/js/jquery.cookie.min.js" type="text/javascript"></script>	
  	<script src="${static_server}/themes/metro/js/jquery.uniform.min.js" type="text/javascript" ></script>
  	<script src="${static_server}/script/jquery/js/jquery.validationEngine.js?v=1" type="text/javascript"></script>	
  	<script src="${static_server}/script/jquery/js/jquery.validationEngine-zh_CN.js" type="text/javascript" ></script>
  	<script src="${static_server}/script/cnd.js" type="text/javascript"></script></head>
  	<script src="${static_server}/script/cnd.admin.js" type="text/javascript"></script></head>
<body class="page-header-fixed" >
<div id="_dialog_box_" class="modal hide fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
<div class="modal-body"></div></div><style>
		html,body{
			width:100% !important;
			height:100% !important;
		}  
		body{
			background-color: #3d3d3d !important;
		}
	</style>
	<table style="height:100%;width:100%;border:0px;" border="0">
		<tr>
			<td style="height:43px;">
				<div class="header navbar navbar-inverse navbar-fixed-top">
		
				<!-- 顶部导航 -->
				<div class="navbar-inner">
					<div class="container-fluid">
						<!-- LOGO 开始-->
						<a class="brand" href="${setting("site.url")}" style="width:400px;margin-left:40px;">${systemName}</a>
						<!-- END LOGO -->
						<div class="navbar hor-menu hidden-phone hidden-tablet">
							<div class="navbar-inner">
								<ul class="nav" id="top_menu">
									<li class="active">
										<a href="javascript:void(0);" data-role="platform">财务管理<span class="selected"></span></a>
									</li>
									<li><a href="javascript:void(0);" data-role="news">订单管理</a></li>
									<li><a href="javascript:void(0);" data-role="erp">产品管理</a></li>
									<li><a href="javascript:void(0);" data-role="member">会员管理</a></li>
									<li><a href="javascript:void(0);" data-role="customer">客服管理</a></li>
									<li><a href="javascript:void(0);" data-role="information">信息库</a></li>
									<li><a href="javascript:void(0);" data-role="setting">系统管理</a></li>
									<li><a href="javascript:void(0);" data-role="opportunitySpace">工作空间</a></li>
								</ul>
							</div>
		
						</div>
						
						<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
							<img src="${static_server}/themes/metro/image/menu-toggler.png" alt="" />
						</a>          
		
						<!-- 顶部导航菜单 -->              
						<ul class="nav pull-right">
							<!-- 个人信息下拉按钮 -->
							<li class="dropdown user">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<img alt="" src="${static_server}/themes/metro/image/avatar1_small.jpg" />
									<span class="username"></span>
									<i class="icon-angle-down"></i>
								</a>
		
								<ul class="dropdown-menu">
									<li><a href="/admin/editPwd" target="_frmContent_"><i class="icon-envelope"></i> 修改密码</a></li>
									<li class="divider"></li>
									<li><a href="javascript:void(0);" onclick="Login.logout();" ><i class="icon-key"></i> 退出登录</a></li>
								</ul>
							</li>
							<!-- 个人信息结束 -->
							
						</ul>
					</div>
				</div>
			</div>
			<!-- 头部结束 -->
			</td>
		</tr>
		<tr>
			<td valign="top" id="td-content">
				<div class="page-container" style="min-height:100px;height:100%;" id="div-content">
					<!-- 左侧导航条 -->
					<div class="page-sidebar nav-collapse collapse" style="min-height:450px;height:auto !important;" id="div-menu-tree">
			
						<!-- 导航菜单开始 -->        
						<ul class="page-sidebar-menu" id="menu_platform">
							</admin/financial/provider_stli>
								<div class="sidebar-toggler hidden-phone"></div>
							</li>
							<li >
								<a href="#" target="_frmContent_">
									<i class="icon-bookmark-empty"></i> 
									<span class="title">供产会统计</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li ><a href="/admin/finance/provider_memberfi_stat" target="_frmContent_">会费统计</a></li>
									<li ><a href="/admin/finance/provider_managerfi_stat" target="_frmContent_">管理费统计</a></li>
								</ul>
							</li>
							<li>
								<a href="#" target="_frmContent_">
									<i class="icon-table"></i> 
									<span class="title">店主会统计</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li ><a href="/admin/finance/shop_memberfi_stat" target="_frmContent_">会费统计</a></li>
									<li ><a href="/admin/finance/shop_managerfi_stat" target="_frmContent_">管理费统计</a></li>
								</ul>
							</li>
							<li>
								<a href="#" target="_frmContent_">
									<i class="icon-briefcase"></i> 
									<span class="title">健康会统计</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li ><a href="/admin/finance/healthy_trade_stat" target="_frmContent_">交易统计</a></li>
								</ul>
							</li>
							<li>
								<a href="#" target="_frmContent_">
									<i class="icon-gift"></i> 
									<span class="title">注册用户统计</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li ><a href="/admin/finance/user_trade_stat" target="_frmContent_">交易统计</a></li>
								</ul>
							</li>
							<li>
								<a href="#" target="_frmContent_" id="pttj">
									<i class="icon-sitemap"></i> 
									<span class="title">平台统计</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li ><a href="/admin/finance/trade_income_expend" target="_frmContent_">交易收支明细</a></li>
								</ul>
							</li>
							<li class="">
								<a href="#" target="_frmContent_" id="hfgl">
									<i class="icon-folder-open"></i> 
									<span class="title">会费管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<!--<li ><a href="/admin/book/book_list" target="_frmContent_">会费缴纳分析统计</a></li>-->
									<li><a href="/admin/finance/memberfi_list" target="_frmContent_">会费缴纳概览</a></li>
									<li><a href="/admin/finance/reminder_provider" target="_frmContent_">催缴-供产会</a></li>
									<li><a href="/admin/finance/reminder_shop" target="_frmContent_">催缴-店主会</a></li>
								</ul>
							</li>
							
							<li class="">
								<a href="#" target="_frmContent_" id="txgl">
									<i class="icon-user"></i> 
									<span class="title">提现管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li ><a href="/admin/withdraw/withdraw_list" target="_frmContent_">提现申请审核</a></li>
									<li><a href="/admin/finance/withdraw_applied_record" target="_frmContent_">提现记录查询</a></li>
								</ul>
							</li>
							
							<li class="">
								<a href="#" target="_frmContent_" id="txgl">
									<i class="icon-th"></i> 
									<span class="title">退款管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li ><a href="/admin/finance/refund_applied_list" target="_frmContent_">退款申请审核</a></li>
									<li><a href="/admin/finance/refund_applied_record" target="_frmContent_">退款记录查询</a></li>
								</ul>
							</li>
							
							<li class="">
								<a href="#" target="_frmContent_" id="txgl">
									<i class="icon-file-text"></i> 
									<span class="title">发票管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/finance/invoice_applied_list" target="_frmContent_">发票申请列表</a></li>
									<li ><a href="/admin/finance/invoice_record_register" target="_frmContent_">出票备案登记</a></li>
								</ul>
							</li>
						</ul>
						
						<ul class="page-sidebar-menu" id="menu_news" style="display:none">
							<li>
								<div class="sidebar-toggler hidden-phone"></div>
							</li>
							
							<li class="">
								<a href="javascript:void(0);" target="_frmContent_">
									<i class="icon-cogs"></i> 
									<span class="title">订单查询</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/order/order_query_list" target="_frmContent_">订单查询列表</a></li>
								</ul>
							</li>
							
						</ul>
						
						<ul class="page-sidebar-menu" id="menu_erp" style="display:none">
							<li>
								<div class="sidebar-toggler hidden-phone"></div>
							</li>
				
							<li class="">
								<a href="#" target="_frmContent_" id="list_undeal">
									<i class="icon-cogs"></i> 
									<span class="title">供产仓库管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/goods/provider_goods_applied_list" target="_frmContent_">产品审核</a></li>
									<li><a href="/admin/goods/provider_warehouse_brower" target="_frmContent_">供仓一览</a></li>
								</ul>
							</li>
							<li class="">
								<a href="#" target="_frmContent_" >
									<i class="icon-cogs"></i> 
									<span class="title">店主仓库管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/goods/shop_goods_applied_list" target="_frmContent_">产品审核</a></li>
									<li><a href="/admin/goods/shop_warehouse_brower" target="_frmContent_">店仓一览</a></li>
								</ul>
							</li>
						</ul>
						
						<ul class="page-sidebar-menu" id="menu_member" style="display:none">
							<li>
								<div class="sidebar-toggler hidden-phone"></div>
							</li>
				
							<li class="">
								<a href="#" target="_frmContent_">
									<i class="icon-cogs"></i> 
									<span class="title">供产会管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/member/provider_applied_list" target="_frmContent_">会员申请列表</a></li>
									<li><a href="/admin/member/provider_list" target="_frmContent_">会员概览</a></li>
								</ul>
							</li>
							
							<li class="">
								<a href="#" target="_frmContent_" >
									<i class="icon-cogs"></i> 
									<span class="title">店主会管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/member/shop_applied_list" target="_frmContent_">会员申请列表</a></li>
									<li><a href="/admin/member/shop_list" target="_frmContent_">会员概览</a></li>
								</ul>
							</li>
							
							<li class="">
								<a href="#" target="_frmContent_" >
									<i class="icon-cogs"></i> 
									<span class="title">健康会管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/member/healthy_applied_list" target="_frmContent_">会员申请列表</a></li>
									<li><a href="/admin/member/healthy_list" target="_frmContent_">会员概览</a></li>
								</ul>
							</li>
							
							<!--<li class="">
								<a href="#" target="_frmContent_" >
									<i class="icon-cogs"></i> 
									<span class="title">培训管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/member/train_list" target="_frmContent_">试卷管理</a></li>
								</ul>
							</li>-->
							
							<li class="">
								<a href="#" target="_frmContent_" >
									<i class="icon-cogs"></i> 
									<span class="title">注册用户管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/member/user_list" target="_frmContent_">会员概览</a></li>
								</ul>
							</li>
							
								<li class="">
								<a href="#" target="_frmContent_" >
									<i class="icon-cogs"></i> 
									<span class="title">百万医生管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/member/doctor_list" target="_frmContent_">医生概览</a></li>
									<li><a href="#" target="_frmContent_">收入统计</a></li>
								</ul>
							</li>
						</ul>
						
						<!-- 统计分析 -->
						<ul class="page-sidebar-menu" id="menu_customer" style="display:none">
							<li>
								<div class="sidebar-toggler hidden-phone"></div>
							</li>
				
							<li class="">
								<a href="#" target="_frmContent_" id="list_undeal">
									<i class="icon-cogs"></i> 
									<span class="title">供产会/店主会</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/course/course_list" target="_frmContent_">意见反馈</a></li>
									<li><a href="/admin/course/course_list" target="_frmContent_">即时通讯</a></li>
									<li><a href="/admin/course/course_list" target="_frmContent_">发票受理/邮寄</a></li>
								</ul>
							</li>
							
							<li class="">
								<a href="#" target="_frmContent_" id="list_undeal">
									<i class="icon-cogs"></i> 
									<span class="title">健康会/注册用户</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/course/course_list" target="_frmContent_">意见反馈</a></li>
									<li><a href="/admin/course/course_list" target="_frmContent_">即时通讯</a></li>
									<li><a href="/admin/course/course_list" target="_frmContent_">发票受理/邮寄</a></li>
								</ul>
							</li>
						</ul>
						
						<!-- 系统设置 -->
						<ul class="page-sidebar-menu" id="menu_information" style="display:none">
							<li>
								<div class="sidebar-toggler hidden-phone"></div>
							</li>
							
							<li class="">
								<a href="javascript:void(0);" target="_frmContent_" id="list_undeal">
									<i class="icon-cogs"></i> 
									<span class="title">栏目管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/cms/channel_list" target="_frmContent_">栏目管理</a></li>
									<!--<li><a href="/admin/cms/channel_edit" target="_frmContent_">创建栏目</a></li>-->
								</ul>
							</li>
							
							<li class="">
								<a href="/admin/cms/article_list" target="_frmContent_" id="list_undeal">
									<i class="icon-cogs"></i> 
									<span class="title">内容管理</span>
									<span class="arrow "></span>
								</a>
							</li>
							
							<li class="">
								<a href="/admin/area/area_list" target="_frmContent_" id="list_undeal">
									<i class="icon-cogs"></i> 
									<span class="title">健康信息管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/course/course_list" target="_frmContent_">医院信息</a></li>
									<li><a href="/admin/course/course_list" target="_frmContent_">科室信息</a></li>
									<li><a href="/admin/course/course_list" target="_frmContent_">专家信息</a></li>
									<li><a href="/admin/course/course_list" target="_frmContent_">疾病信息</a></li>
									<li><a href="/admin/course/course_list" target="_frmContent_">疾病信息</a></li>
								</ul>
							</li>
							<li class="">
								<a href="#" target="_frmContent_" id="list_undeal">
									<i class="icon-cogs"></i> 
									<span class="title">培训信息管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/train/category_list" target="_frmContent_">培训分类管理</a></li>
									<li><a href="/admin/train/train_list" target="_frmContent_">培训管理</a></li>
									<li><a href="/admin/train/question_list" target="_frmContent_">试题概览</a></li>
								</ul>
							</li>
							<li class="">
								<a href="#" target="_frmContent_" id="list_undeal">
									<i class="icon-cogs"></i> 
									<span class="title">FAQ信息管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/log/log_message_list" target="_frmContent_">一览表</a></li>
								</ul>
							</li>
							<li class="">
								<a href="/wbs/log_apply" target="_frmContent_" id="list_undeal">
									<i class="icon-cogs"></i> 
									<span class="title">视频管理</span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/course/course_list" target="_frmContent_">平台视频列表</a></li>
									<li><a href="/admin/course/course_list" target="_frmContent_">全部视频列表</a></li>
									<li><a href="/admin/course/course_list" target="_frmContent_">视频提交申请</a></li>
								</ul>
							</li>
						</ul>
						
						<ul class="page-sidebar-menu" id="menu_setting" style="display:none">
							<li>
								<div class="sidebar-toggler hidden-phone"></div>
							</li>
							
							<li class="">
								<a href="/admin/area/area_list" target="_frmContent_" id="list_undeal">
									<i class="icon-cogs"></i> 
									<span class="title">数据管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/business_category/business_category_list" target="_frmContent_">经营类别管理</a></li>
									<li><a href="/admin/payment/method/method_list" target="_frmContent_">支付方式管理</a></li>
									<li><a href="/admin/fee_category/category_list" target="_frmContent_">会费类别管理</a></li>
								</ul>
							</li>
							
							<li class="">
								<a href="/admin/area/area_list" target="_frmContent_" id="list_undeal">
									<i class="icon-cogs"></i> 
									<span class="title">角色管理</span>
									<span class="arrow "></span>
								</a>
							</li>
							<li class="">
								<a href="#" target="_frmContent_" id="list_undeal">
									<i class="icon-cogs"></i> 
									<span class="title">数据备份</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/course/course_list" target="_frmContent_">培训信息列表</a></li>
									<li><a href="/admin/course/course_list" target="_frmContent_">发布培训</a></li>
								</ul>
							</li>
							<li class="">
								<a href="#" target="_frmContent_" id="list_undeal">
									<i class="icon-cogs"></i> 
									<span class="title">FAQ信息管理</span>
									<span class="arrow "></span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/log/log_message_list" target="_frmContent_">一览表</a></li>
								</ul>
							</li>
							<li class="">
								<a href="/wbs/log_apply" target="_frmContent_" id="list_undeal">
									<i class="icon-cogs"></i> 
									<span class="title">视频管理</span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/course/course_list" target="_frmContent_">平台视频列表</a></li>
									<li><a href="/admin/course/course_list" target="_frmContent_">全部视频列表</a></li>
									<li><a href="/admin/course/course_list" target="_frmContent_">视频提交申请</a></li>
								</ul>
							</li>
						</ul>
						<!-- 工作空间 -->
						<ul class="page-sidebar-menu" id="menu_opportunitySpace" style="display:none">
							<li>
								<div class="sidebar-toggler hidden-phone"></div>
							</li>
				
							<li class="">
								<a href="#" target="_frmContent_" id="list_undeal">
									<i class="icon-cogs"></i> 
									<span class="title">分类管理</span>
								</a>
								<ul class="sub-menu">
									<li><a href="/admin/opportunity_space/category_list" target="_frmContent_">分类管理</a></li>
								</ul>
							</li>
							
						</ul>
						<!-- 导航菜单结束 -->
					</div>
					<!-- 导航栏结束 -->
			
					<!--  中间页面开始 -->
					<div class="page-content" style="height:100% !important;min-height:430px !important;" id="div-content_frame">
						<iframe frameborder="0" style="width:100%;height:100%;" id="_frmContent_" name="_frmContent_" scrolling="auto"" src="/admin/stat"></iframe>
					</div>
					<!-- 中间页面结束 -->
				</div>
				
			</td>
		</tr>
		<tr>
			<td style="height:40px;">
				<!-- 底部开始 -->
				<div class="footer">
					<div class="footer-inner">${systemCopyright}</div>
					<div class="footer-tools">
						<span class="go-top">
						<i class="icon-angle-up"></i>
						</span>
					</div>
				</div>
				<!-- 底部结束 -->
			</td>
		</tr>
	</table>
	<!-- 装入本页面插件 -->
	[@jqPlugin.plugin formValidation="1" /]
	<script src="${static_server}/themes/metro/js/app.js" type="text/javascript"></script>	<script>		jQuery(document).ready(function() {    
		   App.init(); // 初始化布局和核心插件
        $('form').validationEngine();
		});
	</script></body></html>
<script> 
	
	$(document).ready(function(){
		var menuLi = $(".sub-menu").children();
		menuLi.click(function(){
			menuLi.removeClass("active");
			$(this).addClass("active");
		});
		
		var topMenus = $("#top_menu").find("a");
		topMenus.click(function(){
			var parentLi = $(this).parent();
			var siblings = parentLi.siblings();
			
			siblings.children().find("span").remove();
			siblings.removeClass("active");
			
			parentLi.addClass("active");
			$('<span class="selected"></span>').appendTo($(this));
			
			menuLeft = $("#menu_" + $(this).attr("data-role"));
			menuLeft.siblings().hide();
			menuLeft.show();
		});
		
		if (TNavigator.isIE8()) {
			$("#div-menu-tree").removeClass("collapse");
		}
		var h = $("#td-content").height();
		$("#div-content").height(h);
		$("#div-menu-tree").height(h);
		$("#div-content_frame").height(h);
		$("#_frmContent_").height(h - 20);
	});
	document.body.onresize = function (){
		var h = $("#td-content").height();
		$("#div-content").height(h);
		$("#div-menu-tree").height(h);
		$("#div-content_frame").height(h);
		$("#_frmContent_").height(h - 20);
	}
</script>
 

