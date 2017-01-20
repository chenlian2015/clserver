var artD = null; //对话框全局变量
var artD2 = null;
/*ajax加载对话框：如 新建商品 ajaxLoadDialog('新建商品','createNewPro_ajax.html')*/
/*
tit: 标题
nr_url: ajax要加载的地址
*/
function ajaxLoadDialog(tit,nr_url){
	$.ajax({
	  url: nr_url,
	  cache: false,
	  success: function(html){
		//$("#results").append(html);
		artD2=dialog({
			fixed: true,
			title: tit, 
			lock:true,
			backdropOpacity:0.3,
			content: html  
		}).showModal();
	  }//end success
	});
}


/*关闭对话框函数，基本上对话框都是全局变量 artD。也可以使用其他变量*/
function closeDialog(){
	artD.close();
}
/*关闭对话框函数 end*/

/*操作成功提示
nr: 提示内容。如：“保存成功”
*/
function  okFun(nr){
	dialog({
		fixed: true,
		title: false,
		width:180, 
		lock:true, 
		padding:10,
		 backdropOpacity:0.3,
		skin: 'operation_ok',
		content:'<div class="t_center">'+ nr  +'</div>',
		onshow:function(){
			var t =this; 
			var mys=setTimeout(function(){
				t.close();
				clearTimeout(mys);
			},2000)	
		}
	}).show();
}

/*操作提示函数，有“是”，“否”按钮
fn：为点击“是”后的，操作函数
ts：为实参 this，事件调用的标签
*/
function tishiFun(fn,ts){ 
	var nr = '<div class="t_center mt20 mb10">'+ts.getAttribute("data-info")+"</div>";
	var tit = ts.getAttribute("data-title") 
	artD = dialog({
		fixed: true,
		title: tit,
		width:320, 
		lock:true,
		content: nr, 
		backdropOpacity:0.3,
		cancelValue:"否",
		cancel:function(){},
		ok:function(){
				fn();
			},
		okValue:"是"
	}).showModal();
}
/*操作提示函数 end*/

/*温馨提示,无“否”按钮
info：提示的内容
*/
function wenxinFun(info){
	var nr = '<div class="t_center mt20 mb10">'+info+"</div>"; 
	artD = dialog({
		fixed: true,
		title: "温馨提示",
		width:320, 
		lock:true,
		backdropOpacity:0.3,
		content: nr,
		ok:function(){},
		okValue:"关闭" 
	}).showModal();
}
/*温馨提示 end*/

/*添加学员
fn：为点击“确定”按钮后的操作阐述
addS：为添加学员的模版id
*/
function addStudents(fn){
	var nr = document.getElementById("addS").innerHTML; 
	artD = dialog({
		fixed: true,
		title: "添加新学员", 
		lock:true,
		backdropOpacity:0.3,
		content: nr,
		ok:function(){
			fn();
			},
		okValue:"确定" 
	}).showModal();
}
/*添加学员 end*/

/*添加老师
fn：为点击“确定”按钮后的操作阐述
addS：为添加学员的模版id
*/
function addTeachers(fn){
	var nr = document.getElementById("addTeacher").innerHTML; 
	artD = dialog({
		fixed: true,
		title: "新增师资", 
		lock:true,
		backdropOpacity:0.3,
		content: nr  
	}).showModal();
}
/*添加学员 end*/

/*发送私信*/
function faSixin(fn,t){
	var nr = document.getElementById("sixin").innerHTML; 
	var person = t.getAttribute("data-person");
	var nr_t = !person?nr_t="批量发送私信":nr_t="发给"+person+"的私信";
	if(person){
		artD = dialog({
			fixed: true,
			title: nr_t, 
			lock:true,
			backdropOpacity:0.3,
			content: nr,
			ok:function(){
				fn();
				},
			okValue:"确认发送" 
		}).showModal();
	}else{
		 var inputs = $("#quanxuan_list").find(".checkbox") ;
		 var n = 0;
		 for(var i=0 ; i<inputs.length; i++){
			if(!!inputs[i].checked){
				n++;
			}
		 }
		 if(n==0){
			  artD = dialog({
				fixed: true,
				title: "批量签到", 
				lock:true,
				width:320,
				backdropOpacity:0.3,
				content: "<div class='t_center mb10 mt10'>请至少选中1人</div>",
				ok:function(){ 
					},
				okValue:"知道了" 
			}).showModal(); 
		 }else{
				artD = dialog({
					fixed: true,
					title: nr_t, 
					lock:true,
					backdropOpacity:0.3,
					content: nr,
					ok:function(){
						fn();
						},
					okValue:"确认发送" 
				}).showModal();	 
		 }
		
	}
}
/*发送私信 end*/

/*添加校区*/
function addXiaoqu(){
	var nr = document.getElementById("addxq_moban").innerHTML; 
	artD = dialog({
		fixed: true,
		title: "添加校区", 
		lock:true,
		backdropOpacity:0.3,
		content: nr 
	}).showModal();
}
/*添加校区 end*/

/*初次操作提示*/ 
function removeTag(){ 
		$("#op_tips").remove();
		$("#tips_mask").remove();	
	}
function  opTips(id,url,tit){ 
	if($("#op_tips").length>=1){
		return null;
	}
	var ms = $("<div>");
	var tips = $("<div>");
	var tag = $(id);
	var tp = tag.offset().top; 
	var lf = tag.offset().left;
	tips.attr({"class":"op_tips","id":"op_tips"});
	ms.attr({"class":"tips_mask","id":"tips_mask"});
	tips.appendTo($("body"));
	ms.appendTo($("body")); 
	tips.css({
		top:tp-114,
		left:lf
	});	
	
	var nr = '<div class="rel">'+
			'<span class="op_tips_nr">'+tit+'</span>'+
			'<img src="'+url +'" width="280" height="40" alt=""/>'+
			'<div class="op_tips_txt">以便于接下来开通课程</div>'+
			'<a onclick="removeTag()" href="javascript:void(0)" class="btn btn_green d_ib">我知道了</a>'+
			'</div>';
	tips.html(nr);
	ms.height($(window).height());
	$(window).resize(function(e) {
		if($("#op_tips").length<=0){ return null;}
        var tp = tag.offset().top; 
		var lf = tag.offset().left;
		$("#op_tips").css({
			top:tp-114,
			left:lf
		});	
		$("#tips_mask").height($(window).height());
    });
}
/*初次操作提示 end*/

/*批量签到*/
function piliangQD(fn){
 var inputs = $("#quanxuan_list").find(".checkbox") ;
 var n = 0;
 //判断有多少人被选中
 for(var i=0 ; i<inputs.length; i++){
 	if(!!inputs[i].checked){
		n++;
	}
 }
 
 if(n>0){
	 var nr = "<div class='t_center mb10 mt10'>已选中"+n+"人，是否确认签到？</div>";
	 artD = dialog({
			fixed: true,
			title: "批量签到", 
			lock:true,
			width:320,
			backdropOpacity:0.3,
			content: nr,
			ok:function(){
					fn();
				},
			okValue:"是", 
			cancelValue:"否",
			cancel:function(){}
		}).showModal(); 
  }else{
	    artD = dialog({
			fixed: true,
			title: "批量签到", 
			lock:true,
			backdropOpacity:0.3,
			width:320,
			content: "<div class='t_center mb10 mt10'>请至少选中1人</div>",
			ok:function(){ 
				},
			okValue:"知道了" 
		}).showModal(); 	
  }
}
/*批量签到 end*/

/*批量删除老师*/
function piliangDel(fn){
 var inputs = $("#quanxuan_list").find(".checkbox") ;
 var n = 0;
 var n_status = 0; //正在授课的老师个数
 var names_array = new Array();//老师姓名的数组
  //判断有多少人被选中
 for(var i=0 ; i<inputs.length; i++){
 	if(!!inputs[i].checked){
		n++; 
	    var is1=Number(inputs[i].getAttribute("data-status").replace(/\s/ig,""))
		if(!!is1){ 
			 n_status++;
			 names_array.push(inputs[i].getAttribute("data-name").replace(/\s/ig,""));
		}	
	}
 }
 
 if(!!n_status){
	 var err_info = "<div class='t_center mb10 mt10'>对不起，删除选项中包含<span class='c_red'>"
	 				+n_status
					+"</span>名<span class='c_red'>（"
					+names_array.join("，")
					+"）</span>正在授课的老师，您无法进行该项操作！</div>";
	 dialog({
			fixed: true,
			title: "删除提示", 
			lock:true,
			backdropOpacity:0.3,
			width:420,
			content: err_info ,
			ok:function(){ 
				},
			okValue:"关闭" 
		}).showModal(); 
	 return null;
 }
 
 if(n>0){
	 var nr = "<div class='t_center mb10 mt10'>已选中"+n+"人，是否确认删除？</div>";
	 artD = dialog({
			fixed: true,
			title: "删除提示", 
			lock:true,
			width:320,
			backdropOpacity:0.3,
			content: nr,
			ok:function(){
					fn();
				},
			okValue:"是", 
			cancelValue:"否",
			cancel:function(){}
		}).showModal(); 
  }else{
	    artD = dialog({
			fixed: true,
			title: "删除提示", 
			lock:true,
			width:320,
			backdropOpacity:0.3,
			content: "<div class='t_center mb10 mt10'>请至少选中1人</div>",
			ok:function(){ 
				},
			okValue:"知道了" 
		}).showModal(); 	
  }
}
/*批量删除老师 end*/

/*删除单个老师*/
function delTearcher(fn,ts){
 var inputs = $(ts).parent().parent().find(".checkbox")[0] ; 
 var status = inputs.getAttribute("data-status").replace(/\s/ig,"");  //状态值
 var tname = inputs.getAttribute("data-name").replace(/\s/ig,"");
 if(!!Number(status)){
		dialog({
			fixed: true,
			title: "删除提示", 
			lock:true,
			width:320,
			backdropOpacity:0.3,
			content: "<div class='t_center mb10 mt10'>对不起，您要删除的<span class='c_red'>"
					+tname
					+"</span>老师正在授课，您无法进行该项操作！</div>",
			ok:function(){ 
				},
			okValue:"关闭" 
		}).showModal(); 	
	}else{
		dialog({
			fixed: true,
			title: "删除提示",
			width:320, 
			backdropOpacity:0.3,
			lock:true,
			content: "<div class='t_center mb10 mt10'>您确定要删除<span class='c_red'>"
					+tname
					+"</span>老师？</div>",
			ok:function(){
					fn();
				},
			okValue:"是", 
			cancelValue:"否",
			cancel:function(){}
		}).showModal();
	}
}
/*删除单个老师 end*/
/*单选框*/
function myradio(){
	$(document).on("click","a.a_radio",function(){
  	   var v = $(this).attr("data-value");
	   $(this).addClass("checked").siblings(".a_radio").removeClass("checked");
	   $(this).parent().children("input").val(v);
    })
}

/*多选框*/
function mycheckbox(){
	$(document).on("click","a.a_checkbox",function(event){
	   var t = $(this);
  	   var v = t.attr("data-value");
	   var input = t.parent().children("input[type=hidden]");
	   if( t.hasClass("checked")){
		     t.removeClass("checked");
			 var  st_zz = v;
			 var mystr=input[0].value.replace(new RegExp(st_zz),""); 
		  	mystr = mystr.replace(/^\|+|\|+$/ig,"");
			input[0].value= mystr.replace(/\|+/ig,"|")
		  }else{
			 input[0].value+="|"+v; 
			 input[0].value= input[0].value.replace(/^\|+|\|+$/ig,"");
			   input[0].value= input[0].value.replace(/\|+/ig,"|");
			  
			  t.addClass("checked");
		  }
    })
}

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

/*选择课程*/
function chooseKC(){
	$(document).on("mouseenter","ul.choose_kecheng>li",function(event){
	   var t = $(this);  
	   t.children("span").addClass("current"); 
	   t.children(".kecheng_erji").show();
	   event.stopPropagation(); 
    })	
	$(document).on("mouseleave","ul.choose_kecheng>li",function(event){
	   var t = $(this);  
	   t.children("span").removeClass("current"); 
	   t.children(".kecheng_erji").hide();
	   event.stopPropagation(); 
    })
	$(document).on("click","ul.kecheng_erji>li",function(event){
		var inp = $(this).parent(".kecheng_erji").prev().children("input");
	    var ej = $(this).parent();
		var ej_in = ej.find("input");
	    var i = 0;   
		var x = 0;
		for(i=0; i<ej_in.length ; i++){
			if( !!ej_in[i].checked){
				x++;
				break;
			}
		}
		if(x==0){
			inp[0].checked = false; 
		}else{
		 inp[0].checked = true; 
		}
	   event.stopPropagation(); 
    })
	$(document).on("click","input.kecheng_input",function(event){
	   var t = $(this);  
	   var ins = t.parent().next(".kecheng_erji").find("input");
	   var i = 0;
	   if(t[0].checked){
	   	  	for(i=0; i<ins.length ; i++){ 
			 	ins[i].checked = true;
			}
	   }else{
		   for(i=0; i<ins.length ; i++){ 
			 	ins[i].checked = false;
			}
	   }
	   event.stopPropagation(); 
    })
	$(document).on("click","#add_kc",function(event){ 
	   var mb = $("#kc_moban");
	   var tag = $("#jxdg_nr");
	   var n = tag.children(".jxdg_block").length+1;
	   var nr = $(mb.html());
	   nr.find("h2").html("第"+n+"节"); 
	   nr.appendTo(tag);
	   event.stopPropagation(); 
    })
}
function quanxuanFun(){
	if( $("#quanxuan1").length<=0 ){
		return null;
	}
	var cb=$("#quanxuan_list").find("input.checkbox");
	$("#quanxuan1").click(function(event){
		var i=0; 
		if(!!$(this)[0].checked){
			for(i=0 ; i<cb.length;i++){
				cb[i].checked = true;
			}
			$("#quanxuan2")[0].checked = true;
		}else{
			for(i=0 ; i<cb.length;i++){
				cb[i].checked = false;
			}
			$("#quanxuan2")[0].checked = false;
		}
		
		event.stopPropagation();
	});
	
	$("#quanxuan2").click(function(event){
		var i=0; 
		if(!!$(this)[0].checked){
			for(i=0 ; i<cb.length;i++){
				cb[i].checked = true;
			}
			$("#quanxuan1")[0].checked = true;
		}else{
			for(i=0 ; i<cb.length;i++){
				cb[i].checked = false;
			}
			$("#quanxuan1")[0].checked = false;
		} 
		event.stopPropagation();
	});
	 
}
/*二级菜单*/
function erjiFun(){
	if($(".yiji").length<=0){
		return null;
	}
	$(".yiji").hover(function(){
			$(this).css({zIndex:"10"});
			$(this).children(".erji").show(); 
		},function(){
			$(this).css({zIndex:"0"});
			$(this).children(".erji").hide();
	});
}
/*未读信息提示*/
$(document).ready(function(){
  $(".weidu").mousemove(function(){
  $(".xinxi_tishi").show();
  });
  $(".weidu").mouseout(function(){
  $(".xinxi_tishi").hide();
  });
});


/*弹出框*/
$(document).ready(function(){
	$(".shanchu , .tushu_sc").click(function(){
	$(".tanchu_beijing").show();
		});
	$(".quxiao").click(function(){
	$(".tanchu_beijing").hide();
		});
	});

	
	
/*字体颜色和背景*/
$(document).ready(function(){
	$(".slide_sub>li").click(function(){
		$(this).prevAll("li").removeClass("current1");
		$(this).nextAll("li").removeClass("current1");
		$(this).addClass("current1");
		});
	});
	
/*显示页面*/
$(document).ready(function(){
	$("#kecheng").click(function(){
		$("#kc_nr").show();
		$("#ts_nr").hide();
		$("#jg_nr").hide();
		});
	$("#tushu").click(function(){
		$("#kc_nr").hide();
		$("#ts_nr").show();
		$("#jg_nr").hide();
		});
	$("#jigou").click(function(){
		$("#kc_nr").hide();
		$("#ts_nr").hide();
		$("#jg_nr").show();
		});
	});
	
/*购买*/
// $(document).ready(function(){
	
// 	$(".quanbu_sc>dl>dd>ul>p").mousemove(function(){
// 		var t = $(this);
// 		t.siblings("li").css("color","#5aaf00")
// 		t.siblings("li").children(".yuanjia").hide();
// 		});
// 	$(".quanbu_sc>dl>dd>ul>p").mouseout(function(){
// 		var t = $(this);
// 		t.siblings("li").css("color","#403634")
// 		t.siblings("li").children(".yuanjia").show();
// 		});
	
// 	});
	
	
	/*弹出框*/
$(document).ready(function(){
	$(".shanc").click(function(){
	$(".tanchu_beijing").show();
		});
	$(".quxiao2").click(function(){
	$(".tanchu_beijing").hide();
		});
	});
