[@jqPlugin.plugin jcrop="1" /]
 <style>
 	.uploadify-button {
 		color: #fff;
		background: #acd17c;
		border-radius: 2px;
		height: 32px;
		cursor: pointer;
		overflow: visible;
		font-family: Verdana,Arial,"Microsoft Yahei","宋体";
		outline: none;
		width:160px;
		font-weight:lighter;
		text-shadow:none;
	}
	.uploadify-button>span{
		padding-left:54px;
	}
	.uploadify:hover .uploadify-button {
		color: white;
		border-radius: 2px;
		height: 32px;
		background:#5aaf00;
		border: 1px solid white;
		cursor: pointer;
		overflow: visible;
		font-family: Verdana,Arial,"Microsoft Yahei","宋体";
		outline: none;
		width:50px;
		text-shadow:none;
	}
	.uploadify-progress-bar {
		background-color: red;
		height: 10px;
		width: 1px;
	}
	.uploadify-queue-item {
		-webkit-border-radius: 3px;
		-moz-border-radius: 3px;
		border-radius: 3px;
		font: 11px Verdana, Geneva, sans-serif;
		margin-top: 0px;
		max-width: 100%;
		padding: 0px;
		height:10px;
		line-height:10px;
		background-color: transparent;
	}
	.uploadify-queue-item .fileName{
		display:none;
	}
	.uploadify-queue-item .data{
		display:none;
	}
	.uploadify-queue-item .cancel{
		display:none;
	}
	.uploadify-progress {
		background-color: #E5E5E5;
		margin-top: 0px;
		width: 100%;
	}
	.btn_green2{ border-radius:2px; height:32px; padding-left:20px; padding-right:20px; color:#fff; background:#acd17c;cursor:pointer;font-size:14px;}
	.btn_green2:hover{ background:#5aaf00;}
	.btn_green2:active,.btn_green:active{ background:rgb(87,153,2);}
.jcrop-holder .preview-pane {
	    width: 150px;
    height: 150px;
    position: absolute;
    left: 430px;
    top:67px;
}
.jcrop-holder .preview-pane0 {
	    width: 150px;
    height: 150px;
    position: absolute;
    left: 430px;
    top:67px;
}

.jcrop-holder #preview-pane1 {
	    width: 150px;
    height: 150px;
    position: absolute;
    left: 460px;
    top:200px;
}

.jcrop-holder #preview-pane2 {
	width: 150px;
    height: 150px;
    position: absolute;
    left: 475px;
    top:327px;
}

.jcrop-holder{
	margin:auto;
}
.jcrop-holder>img {
    border: 1px solid lightgray !important;
}

.preview-pane .preview-container {
	width: 120px;
	height: 120px;
	overflow: hidden;
}

#bgc_buttons .cxbtn {
	text-shadow: none;
}
#photoPreview{
	border: 1px solid #D3D3D3; 
	display:none;
}
.image_preview{
	position:absolute;
	left:430px;
	padding-top:15px;
	*position:none;
}
 </style>
 <!-- 上传头像 -->
 <div class="w600 upload_header" id="image_cut">
 	<div class="fl" style="width:402px">
    	<!-- 大图 -->
         <div class="w400  border1px" style="height:400px;text-align:center;background-color:#F1F1F1;">
    	 	<img src="" alt="" id="photoPreview"/>
    	 </div>
         <!-- 大图 end-->
         <div class="mt10 clearfix">
         	<div style="width:200px;float:left; ">
         		<input type="file" name="uploadify" id="uploadify" />
         	</div>
         	<div style="width:160px;float:right;">
	         	<form name="form-imgcut" id="form-imgcut">
	         		<input type="hidden" id="pt" name="pt" />
					<input type="hidden" id="ims" name="ims" />
	         		<input type="button" style="padding-left:5px; padding-right:5px; width:160px; " class="btn  ml10 mr10 btn_green2 btn-save" value="保存图片">
	         	</form>
         	</div>
         </div>
    </div>
    <div class="fr t_center image_preview">
    	${sbhtml }
    </div>
 	<div class="clears"></div>
 </div>
 <div style="width:400px;height:10px;line-height:10px;" id="fileQueue">
 </div>
<script>

jQuery(function($){
  var jcrop_api,
  boundx,
  boundy,
  
  $preview = $('.preview-pane'), $pcnt = $('.preview-pane .preview-container'), $pimg = $('.preview-pane .preview-container img'),
  
  xsize = 150,
  ysize = 150;
  
  $("#uploadify").uploadify({
  	'buttonText': '选择照片',
  	'fileSizeLimit' : '2MB',
  	'fileTypeDesc' : '图片文件',
  	'fileTypeExts' : '*.jpg; *.png; *.gif',
      height        : 30,
      swf           : '/script/uploadify/uploadify.swf',
      uploader      : '/fileupload?fn=photo&fid=uploadify&strictSize=false&maxsize=2000x2000&strictLength=true&maxlen=2',
      width         : 160,
      'fileObjName' : 'photo',
      'simUploadLimit' : 1,
      'multi' : false,
      'queueID' : 'fileQueue',
      'onUploadSuccess': function(file , data, response){
	      	var jsonobj = eval('('+data+')');
	      	if (jsonobj.success === true) {
	      		$("#photoPreview").attr('src', jsonobj.data.url);
	      		$('.preview-pane .preview-container img').attr('src', jsonobj.data.url);
	      		$("#photoPreview").show();
	      		
	      		/* if ($(".jcrop-tracker").width()){
	      			
	      		} */
	      		/* $(".preview-pane").addCss("margin-left", ""); */
	      		
	      		w = jsonobj.data.imageWidth;
	      		h = jsonobj.data.imageHeight;
	      		if (w > h) {
	      			scale = 400 / w;
	      			
	      			$('#photoPreview').width(400);
		      		$('#photoPreview').height(h * scale);
	      		} else if (w < h) {
					scale = 400 / h;
	      			
	      			$('#photoPreview').width(w * scale);
		      		$('#photoPreview').height(400);
	      		} else {
	      			$('#photoPreview').width(400);
		      		$('#photoPreview').height(400);
	      		}
	      		
	      		if (jcropInitied === true) {
	      			jcrop_api.destroy();
	      			initJcrop();
	      		} else {
	      			initJcrop();	
	      		}
	      	}
	    }
  });
  
  // 保存图片
  $(".btn-save").click(function(){
	// 遍历预览图片是否存在缩放
	  previewContainers = $(".preview-container>img");
	  if (previewContainers.length == 0){
		  window.alert("无预览图像，无法进行裁剪");
		  return;
	  }
	  
	  isStretch = true;
	  $.each(previewContainers, function(index, el){
		  if ($(el).attr("src").length == 0 || $(el).attr("style") == null || $(el).attr("style").length == 0) {
			  isStretch = false;
		  }
	  });
	  
	  if (isStretch === false) {
		  window.alert("请先上传并裁剪图片，然后再保存");
		  return;
	  }
	  
	  // 读取上传的图像样式
	  var images = [];
	  $.each(previewContainers, function(index, el){
		  im = {};
			im["width"] = $(el).css("width");
			im["height"] = $(el).css("height");
			im["left"] = $(el).css("margin-left");
			im["top"] = $(el).css("margin-top");
			im["standardWidth"] = $(el).attr("width");
			im["standardHeight"] = $(el).attr("height");
			im["src"] = $(el).attr("src");
			images.push(im);
	  });
	  
	  $("input[name=ims]").attr("value",encodeURIComponent(encodeURIComponent(JSON.stringify(images))));
	  
	  Request.submit('/fileupload/cutimg','form-imgcut',function(resultdata){
		  if (resultdata.success == true) {
			  // 关闭对话框，设置前一个对话框中图片的值
			 $("#${imgid}").attr("src", resultdata.msg);
			 $("#${hid}").attr("value", resultdata.msg);
			  
			 Dialog.closeDialog("image_cut");
		  }	
	  });
  });
  
  jcropInitied = false;
  var currentJCrop;
  function initJcrop(){
	  currentJCrop = $('#photoPreview').Jcrop({
		    onChange: updatePreview,
		    onSelect: updatePreview,
		    aspectRatio: 1,
		    keySupport:false
		  },function(){
		    var bounds = this.getBounds();
		    boundx = bounds[0];
		    boundy = bounds[1];
		    jcrop_api = this;
		    $preview.appendTo(jcrop_api.ui.holder);
		    jcropInitied = true;
		    
		    if ($("#photoPreview").width() < 400){
		    	marginLeft = (400 - $("#photoPreview").width()) / 2;
		    	$(".preview-pane").css("margin-left", "-" + marginLeft + "px");
		    }
	  });
  }
  
  function updatePreview(c){
    if (parseInt(c.w) > 0) {
      
      if ($pimg.length > 0){
    	$.each($pimg, function(index, el){
    		 var rx = parseInt($(el).attr("data-width")) / c.w;
    	     var ry = parseInt($(el).attr("data-height")) / c.h;
    	     
    		$(el).css({
    			width: Math.round(rx * boundx) + 'px',
    	        height: Math.round(ry * boundy) + 'px',
    	        marginLeft: '-' + Math.round(rx * c.x) + 'px',
    	        marginTop: '-' + Math.round(ry * c.y) + 'px'
    		});
    	});
      }
      /* $pimg.css({
        width: Math.round(rx * boundx) + 'px',
        height: Math.round(ry * boundy) + 'px',
        marginLeft: '-' + Math.round(rx * c.x) + 'px',
        marginTop: '-' + Math.round(ry * c.y) + 'px'
      }); */
    }
  };
});
</script>
