// JavaScript Document

//购物车
$('.top_select').hover(
function(){
	$(this).children('div').show();
},
function(){
	$(this).children('div').hide();
});

//input获得焦点时变换边框颜色
$('input').focusin(function(){
	$(this).css("border","1px solid #5aaf00");
});
$('input').focusout(function(){
	$(this).css("border","1px solid #d5d5d5");
});

/*机构评价
fn：为点击“确定”按钮后的操作阐述
*/
function pingjiafun(fn){
	var nr = document.getElementById("pingjia").innerHTML; 
	artD = dialog({
		fixed: true,
		title: "机构评价", 
		lock:true,
		backdropOpacity:0.3,
		content: nr 
	}).showModal();
}
/*机构评价 end*/

/*填写您关注的考试*/
function examfun(fn){
	var nr = document.getElementById("exam").innerHTML; 
	artD = dialog({
		fixed: true,
		title: "填写您关注的考试", 
		lock:true,
		backdropOpacity:0.3,
		content: nr 
	}).showModal();
}
/*填写您关注的考试 end*/

/*支付提示*/
function zhifufun(fn){
	var nr = document.getElementById("zhifu").innerHTML; 
	artD = dialog({
		fixed: true,
		title: "支付提示", 
		lock:true,
		backdropOpacity:0.3,
		content: nr 
	}).showModal();
}
/*支付提示 end*/

/*上传头像
fn：为点击“确定”按钮后的操作阐述
*/
function uploadtxfun(fn){
	var nr = document.getElementById("upload_tx").innerHTML; 
	artD = dialog({
		fixed: true,
		title: "上传头像", 
		lock:true,
		backdropOpacity:0.3,
		content: nr 
	}).showModal();
}
/*上传头像 end*/

//首页网络课程学习进度
var m=$('.jindu i').length;
for(var t=0;t<m;t++){
	var num=$('.percent b').eq(t).html()+"px";
	$('.jindu i').eq(t).css({width:num});
}
//我的题库学习进度
var n=$('.khzy_jindu i').length;
for(var t=0;t<n;t++){
	var num=($('.khzy_tw b').eq(t).html())*76/100+"px";
	$('.khzy_jindu i').eq(t).css({width:num});
}

//tabs
$('.tabs_title li').click(function(){
	var i=$(this).index();
	$(this).addClass('on');
	$(this).siblings('.tabs_title li').removeClass('on');
	$(this).parent().parent().parent().children('.tabs_content').children('div').hide();
	$(this).parent().parent().parent().children('.tabs_content').children('div').eq(i).show();
});

$('.tabs2_title li').click(function(){
	var i=$(this).index();
	$(this).addClass('on');
	$(this).siblings('.tabs2_title li').removeClass('on');
	$(this).parent().parent().parent().children('.tabs2_content').children('div').hide();
	$(this).parent().parent().parent().children('.tabs2_content').children('div').eq(i).show();
});
//优惠券tab
$('.youhuijuan li').click(function(){
	var i=$(this).index();
	$(this).addClass('on');
	$(this).siblings('.youhuijuan li').removeClass('on');
	$(this).parent().parent().parent().children('.tabs3_content').children('div').hide();
	$(this).parent().parent().parent().children('.tabs3_content').children('div').eq(i).show();
});


//课程展开与收起
$('.kt_select a').toggle(
function(){
	$(this).parent().parent().children('.kt_selcon').slideDown(500);
	$(this).addClass('selecton');
},
function(){
	$(this).parent().parent().children('.kt_selcon').slideUp(500);
	$(this).removeClass('selecton');
});
//课程目录展开与收起
$('.kcml_btn a').toggle(
function(){
	$(this).parent().parent().children('.kcml_con').slideDown(500);
	$(this).addClass('on');
},
function(){
	$(this).parent().parent().children('.kcml_con').slideUp(500);
	$(this).removeClass('on');
});
//题库展开与收起
$('.wdtk_btn a').toggle(
function(){
	$(this).parent().parent().parent().children('.wdtk_con').slideDown(500);
	$(this).addClass('on');
},
function(){
	$(this).parent().parent().parent().children('.wdtk_con').slideUp(500);
	$(this).removeClass('on');
});


//查看教学大纲
$('#ckdg a').click(function(){
	$('.teach_dagang').show();
});

//选择日期
$('.chongzhijilu>li>a').click(function(){
	$(this).addClass('on');
	$(this).siblings('.chongzhijilu>li>a').removeClass('on');
});

//换肤
$('.change_color span').click(function(){
	var i=$(this).index();
	var se=new Array("#01052b","#7f0a8c","#956134","#ea5514","#036eb8","#017f76","#00913a");
	$('.banner').css({background:se[i]});
	$('.notice h3').css({color:se[i]});
});

//选中
$(".course_year a").click(function(){
  $(this).addClass("on");
  $(this).siblings('.course_year a').removeClass('on');
});
$(".calendar_rili tr td").click(function(){
  $(this).parent().parent().children('tr').children('td').removeClass('on');
  $(this).addClass("on");
});

$(document).ready(function(e) {
    pingjia();	
	kaoshi();  
	selects();
});
/*下拉列表*/
function selects(){
	$(document).on("click","dl.selects",function(event){
	   var t = $(this); 
  	   var inputs = t.children("input");
	   var s = t.children("dt").children("span");
	   var dd =  t.find("dd");
	   t.css("zIndex",1);
	   dd.show();
	   dd.children("a").off().click(function(event){
	   		 s.html($(this).html());
			 inputs.val($(this).html());
			 dd.hide();
			 event.stopPropagation(); 
	   })
    })
	$(document).on("mouseleave","dl.selects",function(event){
	   var t = $(this); 
	  t.css("zIndex",0);
	   t.find("dd").hide(); 
	   event.stopPropagation(); 
    })
}
//评价星星选择
function pingjia(){
	$(document).on("click",".pjmyd p span",function(){
		var i=$(this).index();
		for(var n=0;n<i;n++){
			$(this).parent().children('span').eq(n).addClass("choosed");
		}
    })
}
//选择考试类型
function kaoshi(){
	$(document).on("click",".exam_type span",function(){
		$(this).addClass("on");
        $(this).siblings('.exam_type span').removeClass('on');
    })
}

//支付提示
var bodyHeight=document.body.offsetHeight;
$('.popbg').css('height',bodyHeight);
function zhifutishi(){
	$('.popbg').show();
	$('.popwindow').show();
	$('.pop_close').click(function(){
		$('.popbg').hide();
	    $('.popwindow').hide();
	})
}

//收藏试题
$('.collect_title').click(function(){
	$(this).parent().children('.collect_edit').show();
});

$('.collect_edit').click(function(){
	$(this).parent().children('.collect_input').show();
});

$(document).ready(function(){
$(".quxiao1").click(function(){
		$(".tanchu_beijing").show();
		});
		$(".quxiao").click(function(){
	$(".tanchu_beijing").hide();
		});
});