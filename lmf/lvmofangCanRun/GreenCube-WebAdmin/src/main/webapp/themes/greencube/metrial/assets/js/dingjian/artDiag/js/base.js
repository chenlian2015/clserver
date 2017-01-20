// JavaScript Document
var dial = null; /*全局对话框变量*/
//重写alert  
function newalert(msg){
	dial = dialog({
		title:"温馨提示",
		width:"200px",
		textAlign:"center",
		quickClose: true,
		backdropOpacity:0.5,
		drag:false,
		okValue:"知道了",
    	ok: function () {
			 this.remove();
			},
		content: msg
	}).show();
}// end newalert
/*newalert(1) ; 警告框*/
function yesOrNot(msg,fn){
	dial = dialog({
		title:false,
		content:("您是否确定要进行<br/><span class='c_red'>"+msg+"</span>操作"),
		width:"200px",
		textAlign:"center",
		quickClose: true,
		backdropOpacity:0.5,
		drag:false,
		 cancelValue: '取消',
    	cancel: function () {},
		okValue:"确定",
    	ok:fn
	}).showModal();
}
//demo function
/*function delDemo(x){
	alert(x)
}
yesOrNot("删除文章",function(){
		delDemo("进行删除");
		dial.remove(); //删除对话框
	});*/
//end demo
function ajaxDialog(ajaxurl,fn){
	dial = dialog({
		title:"ajax内容demo", 
		width:"400px",
		textAlign:"center",
		quickClose: true,
		backdropOpacity:0.5,
		drag:false,
		 cancelValue: '取消',
    	cancel: function () {},
		okValue:"确定",
    	ok:fn
	});
	$.ajax({
		url: ajaxurl, 
		success: function (data) { 
			dial.content(data).show();//装载HTML页面 
			//alert("加载成功")
		},
		cache: false	
	});
}