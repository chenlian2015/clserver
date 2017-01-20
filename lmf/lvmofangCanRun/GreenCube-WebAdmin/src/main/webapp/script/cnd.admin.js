
var $modal = $('#ajax-modal');

/**
 * 产生一个随机数字
 * @author 胡晓光
 * @returns {Number}
 */
function generateRandomCode(){
	var r = Math.random() * 100000;
	return r;
}
function loadContent(url){
	if (url)
		window.top.frames["_frmContent_"].location.assign(url);
	else
		window.top.frames["_frmContent_"].location.reload(true);
}

function showIFrameDialog(url, title, width, height){
	var topwin = window.top.jQuery;
	topwin("#_dialog_box_").html("");
	
	var _height_ = height ? height : 400;
	if (title && typeof(title) != "undefined" && title.length > 0) {
		_height_ = _height_ - 50;
	} else{
		_height_ = _height_ - 15;
	}
	var _width_ = width  ? width - 40 : 700;
	var hml = '<div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>';
	if (title && typeof(title) != "undefined" && title != null) {
		hml += '<h3 id="myModalLabel2">' + title + '</h3></div>';
	}
	hml +='<div class="modal-body" style="padding:0px;padding-top:15px;width:' + _width_ + 'px">'; 
	hml += '<iframe frameborder="0" id="_popu_frmContent_" name="_popu_frmContent_" scrolling="yes" src="' + url + '" style="width:' + _width_ + 'px;height:' + height + 'px" ></iframe>';
	hml +='</div>';
	
	topwin("#_dialog_box_").append(hml);
	topwin("#_dialog_box_").modal('show').css({  
        width: 'auto'
    });
}

function showLoading(url, title, width, height){
	var topwin = window.top.jQuery;
	var ajaxHtml = "<div id=\"_loading_dialog_\" style='z-index:1000001;opacity: 0.5;width:100%;height:100%;position:absolute;left:0px;top:0px;background-color:black;'><div class=\"modal-body\" style='position:relative;top:50%;font-size:15px;color:white;text-align:center;'>正在执行，请稍后 ...</div></div>";
	topwin('body').append(ajaxHtml); 
}

/**
 * 清除loading
 */
function clearLoading(){
	var topwin = window.top.jQuery;
	topwin('#_loading_dialog_').remove(); 
}

function closeIFrameDialog(){
	var topwin = window.top.jQuery;
	topwin("#_dialog_box_").modal('hide');
}

/**
 * 产生一个uuid
 * @author 胡晓光
 * @param len
 * @param radix
 * @returns
 */
function uuid(len, radix) {
    var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    var uuid = [], i;
    radix = radix || chars.length;
 
    if (len) {
      for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
    } else {
      var r;
 
      // rfc4122 requires these characters
      uuid[8] = uuid[13] = uuid[18] = uuid[23] = '';
      uuid[14] = '4';
 
      for (i = 0; i < 36; i++) {
        if (!uuid[i]) {
          r = 0 | Math.random()*16;
          uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
        }
      }
    }
 
    return uuid.join('');
}

/////////////////////////////loading对话框/////////////////////////
/**
 * 显示提示对话框
 */
function showConfirmDialog(message,title, okFunction, cancelFunction){
	var topwin = window.top.jQuery;
	var hml = '<div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button><h3 id="myModalLabel2">' + title + '</h3></div><div class="modal-body">' +
		'<p>' + message + '</p></div><div class="modal-footer"><button class="btn" data-dismiss="modal" aria-hidden="true">取消</button><button data-dismiss="modal" class="btn blue" id="_dialog_box_confirm_ok" >确定</button></div>';
	topwin("#_dialog_box_").html(hml);
	$("#_dialog_box_confirm_ok").click(function(){
		okFunction();
	});
	topwin("#_dialog_box_").modal('show');
}


/**
 * 显示错误信息对话框
 */
function showErrorDialog(message){
	clearLoading();
	var topwin = window.top.jQuery;
	topwin("#_dialog_box_").html("");
	var hml = '<div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button><h3 id="myModalLabel2">出错了</h3></div><div class="modal-body">' +
		'<p>' + message + '</p></div><div class="modal-footer"><button data-dismiss="modal" class="btn green">关闭</button></div>';
	topwin("#_dialog_box_").append(hml);
	topwin("#_dialog_box_").modal();

}

/**
 * 弹出对话框
 * @param title
 * @param content
 * @param fnclose
 */
function showMessageBox(title,content,fnclose){
    jQuery("#_tips_box_ h1").html(title);
    jQuery("#_tips_box_ p").html(content);
    if (fnclose) jQuery("#_tips_box_").unbind("popupafterclose").bind("popupafterclose", function() { fnclose(); });
    jQuery("#_tips_box_").popup("open");
}

/**
 * 显示页面
 * @param url
 */
function showPage(url){
	jQuery("#_frmContent_").location = url;
}

/**
 * 装载页面
 * @author 胡晓光 
 * @param service
 * @param params
 * @param blockname
 * @param abpath
 * @param _call_back_
 */
function ajax_fill (service, params, blockname,abpath,_call_back_) {
	var random = generateRandomCode();
	
	if (abpath && abpath != null && abpath.length > 0)
		prefix = abpath;
	else
		
	jQuery.get(service + params + "&v=" + random, function(result,textStatus) {
		jQuery("#" + blockname).html(result);
		if (_call_back_){
			_call_back_(result,textStatus);
		}
	});
}

/**
 * 装载页面
 * @author 胡晓光
 * @param url
 * @param blockname
 */
function ajax_fill_page (url, blockname, isShowLoading) {
	var _showLoading_ = isShowLoading;
	if (_showLoading_ && _showLoading_ === true) {
		showLoading();
	}
		
	
	var random = generateRandomCode();
	
	if(url.indexOf("?") > 0 ){
		url += "&v=" + random;
	} else{
		url += "?v=" + random;
	}

	jQuery.get(url, function(result) {
		jQuery("#" + blockname).html(result);
		if (_showLoading_ && _showLoading_ == true)
			clearLoading();
	});
	
}

/**
 * ajax方式上传文件
 * @returns {Boolean}
 */
function ajaxFileUpload(_url_, _formid_ , successFn, errorFn) {  
	// 判断当前Form中是否存在文件
	var inputs = jQuery("#" + _formid_ + " input[type=file]");
	var fid = "";
	if (inputs && inputs.length > 0){
		// 存在文件
		for (var i = 0; i < inputs.length; i ++){
			if (i == 0)
				fid += inputs[i].id;
			else 
				fid += "," + inputs[i].id;
		}
	}
	var _params_ = getFormJson(jQuery("#" +_formid_));  
	var params = _params_;
    jQuery.ajaxFileUpload({  
            url: _url_ ,//用于文件上传的服务器端请求地址  
            secureuri:false,//一般设置为false 这个为空ajaxfileupload中的iframe不显示  
            fileElementId: fid,//文件上传空间的id属性  <input type="file" id="file" name="file" />  
            dataType: 'json',//返回值类型 一般设置为json  
            data : params,
            success: function (data, status) {
            	if (data.success && typeof(data.success) != "undefined" && data.success == true){
//					showSuccessNotify("操作成功");
				}
            	
            	if (successFn){
            		successFn(data,status);
            	} 
            },  
            error: function (data, status, e){
            	if (errorFn){
            		errorFn(data, status, e);
            	}
            }
        });
    return false;  
}

/**
 * 图片上传类
 */
var ImageUpload = function (){
	return {
		/**
		 * strictsize -boolean 是否限制上传图片尺寸
		 * width 缩略图宽度
		 * height 缩略图高度
		 * _initpic_ 初始图片
		 * funcallback 回调函数
		 * _maxUploadSize_ 允许最大上传多大尺寸的图片（指图片尺寸）
		 * _strictsize_ 是否严格限定上传文件尺寸
		 * _strictlen_ 是否严格限定上传文件大小
		 * maxlen 最大上传大小
		 * maxsize 最大上传尺寸
		 */
		createUploadImage : function(fieldid, width, height , _initpic_  , funcallback, strictlen, maxlen, strictsize, maxsize){
			var _strictlen_ = true;
			var _maxlen_ = 1;
			var _strictsize_ = true;
			var _maxsize_ = "1000x1000";
			
			initpic = "/themes/topacc/images/no_pic.jpg";
			
			if (typeof (_initpic_) != "undefined") 
				initpic = _initpic_;
			
			if (typeof(strictlen) != "undefined") 
				_strictlen_ = strictlen;
			
			if (typeof(maxlen) != "undefined") 
				_maxlen_ = maxlen;
			
			if (typeof(strictsize) != "undefined") 
				_strictsize_ = strictsize;
			
			if (typeof(maxsize) != "undefined") 
				_maxsize_ = maxsize;
			
			if (initpic == null)
				initpic = "/themes/topacc/images/no_pic.jpg";
			
			buttonClassName = 'uploadify_' + fieldid;
			styleHtml = '';
			styleHtml +='.' + buttonClassName + ' {';
			styleHtml +="	background: url('" + initpic + "');";
			styleHtml +='	border-radius: 2px;';
			styleHtml +='	border:1px solid lightgray;';
			styleHtml +='	background-size:100% 100%;';
			styleHtml +='	-moz-background-size:100% 100%;';
			styleHtml +='	width:' + width + 'px;';
			styleHtml +='	height: ' + height + 'px;';
			styleHtml +='	cursor: pointer;';
			styleHtml +='	overflow: visible;';
			styleHtml +='	font-family: Verdana,Arial,"Microsoft Yahei","宋体";';
			styleHtml +='	outline: none;';
			styleHtml +='	font-weight:lighter;';
			styleHtml +='	text-shadow:none;';
			styleHtml +='}';
			
			styleHtml +='.uploadify:hover .' + buttonClassName + ' {';
			styleHtml +="	background: url('" + initpic + "');";
			styleHtml +='	border-radius: 2px;';
			styleHtml +='	border:1px solid #99E448;';
			styleHtml +='	background-size:100% 100%;';
			styleHtml +='	-moz-background-size:100% 100%;';
			styleHtml +='	width:' + width + 'px;';
			styleHtml +='	height: ' + height + 'px;';
			styleHtml +='	cursor: pointer;';
			styleHtml +='	overflow: visible;';
			styleHtml +='	font-family: Verdana,Arial,"Microsoft Yahei","宋体";';
			styleHtml +='	outline: none;';
			styleHtml +='	font-weight:lighter;';
			styleHtml +='	text-shadow:none;';
			styleHtml +='}';
			
			// 创建一个style
			
			$("<style id='" + buttonClassName + "_" + Utils.generateRandomCode() + "'></style>").text(styleHtml).appendTo($("head"));
			
			el = $("#" + fieldid);
			uploadFileName = el.attr("name");
			
			// 创建按钮
  			el.uploadify({
  			  	'buttonText': '',
  			  	'buttonClass' :  buttonClassName,
  			  	'fileSizeLimit' : _maxlen_ + 'MB',
  			  	'fileTypeDesc' : '图片文件',
  			  	'fileTypeExts' : '*.jpg; *.png; *.gif',
  			      height        : height,
  			      width         : width,
  			      swf           : '/script/uploadify/uploadify.swf',
  			      uploader      : '/upload?fn=' + uploadFileName + "&fid=" + buttonClassName + "&strictSize=" + _strictsize_ +"&maxsize=" +  _maxsize_ + "&strictLength=" + _strictlen_ + "&maxlen=" + _maxlen_,
  			      'fileObjName' : uploadFileName,
  			      'simUploadLimit' : 1,
  			      'multi' : false,
  			      'onUploadSuccess': function(file , data, response){
  				      	var jsonobj = eval('('+data+')');
  				      	if (jsonobj.success === true) {
  				      		$("." + jsonobj.data.fid).css("background-image" , "url('" + jsonobj.data.url + "')");
  				      		if (typeof(funcallback) != "undefined"){
  				      			funcallback(jsonobj);
  				      		}
  				      	} else {
  				      		showErrorDialog(jsonobj.msg);
  				      	}
  				    }
  			});
		}
	};
}();

var Submit = {
		uploadForm: function(_url_ , _formid_, sucessCallback, toBeRefreshedAreaId){
			if (typeof(jQuery("#" + _formid_).validationEngine) != "undefined"){
				if (!jQuery("#" + _formid_).validationEngine("validate")){
					return;
				}
			}
			
			var inputs = jQuery("#" + _formid_ + " input[type=file]");
			var topwin = window.top.jQuery;
			if (inputs && inputs.length > 0){
				setTimeout(function(){
					ajaxFileUpload(_url_,_formid_,sucessCallback); 
				  }, 1000);
			} else{
				showLoading();
				var _params_ = getFormJson(jQuery("#" +_formid_));
				var params = _params_;
				jQuery.ajax({
					type : "post",
					cache : false,
					url : _url_,
					data : jQuery.param(params),
					dataType: "json",
					success : function(resultdata, textStatus){
						if (toBeRefreshedAreaId && typeof(toBeRefreshedAreaId) != "undefined" && toBeRefreshedAreaId != null){
							// 顶部显示提示信息
							if (resultdata.success && typeof(resultdata.success) != "undefined" && resultdata.success == true){
								jQuery("#" + toBeRefreshedAreaId).html(resultdata.msg);
							}
						} 
						if (sucessCallback){
							sucessCallback(resultdata, textStatus);
						}
						clearLoading();
					}
				});
			}
		},
		submitForm: function(_url_ , _formid_, sucessCallback, toBeRefreshedAreaId){
			if (typeof(jQuery("#" + _formid_).validationEngine) != "undefined"){
				if (!jQuery("#" + _formid_).validationEngine("validate")){
					return;
				}
			}
			
			var topwin = window.top.jQuery;
			showLoading();
			var _params_ = getFormJson(jQuery("#" +_formid_));
			var params = _params_;
			jQuery.ajax({
				type : "post",
				cache : false,
				url : _url_,
				data : jQuery.param(params),
				dataType: "json",
				success : function(resultdata, textStatus){
					if (toBeRefreshedAreaId && typeof(toBeRefreshedAreaId) != "undefined" && toBeRefreshedAreaId != null){
						// 顶部显示提示信息
						if (resultdata.success && typeof(resultdata.success) != "undefined" && resultdata.success == true){
							jQuery("#" + toBeRefreshedAreaId).html(resultdata.msg);
						}
					} 
					if (sucessCallback){
						sucessCallback(resultdata, textStatus);
					}
					clearLoading();
				}
			});
		},
		invok : function (_url_,sucessCallback, isShowLoading) {
			var _isShowLoading_ = isShowLoading ? isShowLoading : true;
			if (_isShowLoading_ === true)
				showLoading();
			
			jQuery.ajax({
				type : "post",
				cache : false,
				dataType: 'json',
				url : _url_,
				success : function(resultdata, textStatus){
					if (_isShowLoading_ === true)
						clearLoading();
					
					if (sucessCallback){
						sucessCallback(resultdata, textStatus);
					}
				}
			});
		},
		// 本函数用于获取后台反馈的数据，并以json格式返回
		request : function (_url_,sucessCallback){
			jQuery.ajax({
				type : "post",
				cache : false,
				dataType: 'json',
				url : _url_,
				success : function(resultdata, textStatus){
					if (resultdata && resultdata.success === true) {
						if (sucessCallback){
							sucessCallback(resultdata, textStatus);
						}
					} else {
						return null;
					}
				}
			});
		}
};


function getFormJson (frm) {
    var o = {};
    var a = jQuery(frm).serializeArray();
    jQuery.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

/**
 * 初始化左侧菜单
 */
function initLeftMenu(){
	var showText = 'Show';
	var hideText = 'Hide';
	var is_visible = false;
	jQuery('.toggle').prev().append(' <a href="#" class="toggleLink">' + showText + '</a>');
	jQuery('.toggle').hide();
	jQuery('a.toggleLink').click(function() {
		is_visible = !is_visible;
		if (jQuery(this).text() == showText) {
			jQuery(this).text(hideText);
			jQuery(this).parent().next('.toggle').slideDown('slow');
		} else {
			jQuery(this).text(showText);
			jQuery(this).parent().next('.toggle').slideUp('slow');
		}
		return false;
	});
}

jQuery(document).ready(function() {
//	jQuery("#_frmContent_").load(function(){
//		var mainheight = jQuery(this).contents().find("body").height() + 0;
//		jQuery(this).height(mainheight);
//	}); 
//	
//	jQuery(window.parent.document).find("#_frmContent_").load(function(){
//		var main = jQuery(window.parent.document).find("#_frmContent_");
//		var thisheight = jQuery(document).height()+0;
//		main.height(thisheight);
//	});
	var headerLinks = jQuery(".section_title").children();
	jQuery.each(headerLinks,function(s,val){
		jQuery(this).attr("target","_frmContent_");
		jQuery(this).click(function(){
			jQuery("#_vlink_navigator_").html(jQuery(this).attr("title"));
		});
		//showPage();
	});
	
	// 处理radio
	var aryInputs = jQuery('input');
	jQuery.each(aryInputs,function(s,val){
		var isRequired = jQuery(this).attr('required');
		if (isRequired && typeof(isRequired) != "undefined" && isRequired == "required"){
			jQuery(this).after("<font color='red'>*</font>");
		} else{
			var requiredClass = jQuery(this).hasClass("validate[required]");
			if (requiredClass === true){
				jQuery(this).after("<span style=' margin-left:5px;color:red;font-size:18px;font-size:13px;margin-right:5px;'>(必填)</span>");
			}
		} 
	});
	
	// 处理radio
	var arysRadios = jQuery('div[type=radio]');
	jQuery.each(arysRadios,function(s,val){
		var isConverted = jQuery(this).attr('convert');
		if(isConverted && isConverted == 'true'){
			var value = jQuery(this).attr("value");
			if (value){
				// 查找其下的radio
				radios = jQuery(this).find("input[type=radio]");
				jQuery.each(radios,function(s,val){
					var radioVal = jQuery(this).attr("value");
					if (radioVal && value == radioVal){
						jQuery(this).attr("checked","checked");
					}
				});
			} else{
				setDefValue = jQuery(this).attr("setDefaultValue");
				if(setDefValue && setDefValue == "true"){ 
					radios = jQuery(this).children("input[type=radio]");
					if (radios && radios.length > 0)
						jQuery(radios[0]).attr("checked","checked");
				}
			}
		}
	});
});

/**
 * 检查是否为空
 * @param str
 * @returns {Boolean}
 */
function isEmpty(str){
	if (!str)
		return true;
	 var v = jQuery.trim(str);
	 if (v.length == 0)
		 return true;
	 return false;
}

/**
 * 上传图片并预览
 * @param fileid
 * @param imgid
 * @returns {Boolean}
 */
function previewImage(fileid,imgid){
	if(typeof FileReader==='undefined'){ 
	    alert("您的浏览器还不支持HTML5的FileReader接口,无法使用图片本地预览,请更新浏览器获得最好体验。"); 
	    return;
	}
	var file = document.getElementById(fileid).files[0];
	if(!/image\/\w+/.test(file.type)){ 
        alert("文件必须为图片！"); 
        return false; 
    } 
	var reader = new FileReader(); 
    reader.readAsDataURL(file); 
    reader.onload = function(e){ 
        jQuery("#" + imgid).attr("src",this.result);
    };
}




function showModalIFrameDialog(isModal, title, width, height, url, callback, isMutil, isNotCheckLogin, callback2) {
        /**/
        if (window.event && window.event.srcElement) {
            if (window.event.srcElement.href == "javascript:;") {
                window.event.srcElement.href = "###";
            }
        }
        
        /**/
        IsDialogSaved = false;
        //随机生成Dom的Id
        var id = 'dlg' + uuid(12);
        var iframeid = 'IframeStage' + uuid(12); 
        var mainwin = window.top;
        //获取是否是弹出页参数
        var params = "cdfcid=" + iframeid;
        //为url加上弹出ifreamId
        if (url && url.indexOf("?") < 0) {
            url += ("?" + params);
        }
        else {
            url += ("&" + params);
        }

        if (mainwin.jQuery('#' + id)) {
            if (url.indexOf("?") > 0) {
                url += "&dialogid=" + id;
            }
            else {
                url += "?dialogid=" + id;
            }

            var div = '<div  id="' + id + '" title="' + title + '" style="display:none;padding:5px 10px 10px; z-index:1000;background-color:#ffffff;"><iframe id="'
        + iframeid + '"  scrolling="auto" frameborder="0" marginheight="0" marginwidth="0" src="'
        + url + '" style="overflow:hidden; text-align:center; width:' + (width - 20) + 'px;height:' + (height - 20)
        + 'px;padding:0;" ></iframe><div class="window-shadow" style="top:-6px;z-index:-1;left:-6px;width: '
        + (width - 2 + 14) + 'px; height: ' + (height + 43) + 'px;"></div></div>';
            mainwin.jQuery("#_dialog_box_").append(div);
            //jQuery("#" + iframeid).get(0).src = url;
        };
        // 宽度高度自适应
        mainwin.jQuery('#' + iframeid).bind("load",
			function () {
			    var oDoc = mainwin.jQuery('#' + iframeid).attr("contentWindow") || mainwin.jQuery('#' + iframeid).attr("contentDocument");
			    if (oDoc.document) {
			    	oDoc = oDoc.document;
			    }
			    // 高度自适应
//			    var newHeight = oDoc.body.scrollHeight + 20;
//			    if (newHeight > height) {
//				    mainwin.jQuery('#' + iframeid).height(oDoc.body.scrollHeight + 20 + 'px');
//				    //阴影部分高度调整
//				    jQuery(".ui-dialog").find(".window-shadow").css("height", (newHeight + 48) + "px");
//			    }
			}
        );
        
        ///初始化Dialog
        mainwin.jQuery('#' + id).dialog({
            autoOpen: true,
            bgiframe: true,
            dialogClass: '',
            width: width,
            title: title,
            modal: isModal,
            resizable: false,
            stack: true,
            draggable: true,
            minHeight: 60,
            position: 'center',
            //close:function(){mainwin.jQuery('#' + id).remove('#' + id);},
            beforeclose: function (event, ui) {
                if (callback2) {
                    callback2();
                }
                try {
                    if (mainwin.IsDialogSaved) {
                        if (callback) {
                            callback(mainwin, iframeid);
                        }
                        else if (window.JSCallPageRefreshEvent) {
                            window.JSCallPageRefreshEvent();
                        }
                    }
                } catch (e) { }
            }
        });
        
        mainwin.jQuery('#' + id).dialog('open');
        jQuery(".ui-dialog").css("overflow", "visible");
        jQuery(".ui-dialog").css("border-color", "#003366");
        jQuery(".ui-dialog").find("a.ui-dialog-titlebar-close").remove();
        jQuery(".ui-dialog .ui-dialog-titlebar").append("<div class='ui-dialog-titlebar-close' onclick=" + "\"jQuery('#" + id + "').dialog('close');return false;\"></div>");
        jQuery(".ui-dialog").append("<iframe id='if" + id + "' style='z-index:-1;width:" + width + "px;height:" + height + "px;position:absolute;border:0;left:0;top:0;' frameborder='0' scrolling='0'></iframe>");
        //mainwin.jQuery('#' + id).remove('#' + id);
};

function heightResize(height){
	jQuery(window.top.document).find("#_frmContent_").height(height);
}

function getCurrentTime(){ 
	var now= new Date();
	var hour = now.getHours();
	var minute=now.getMinutes();
	var second=now.getSeconds();
	return hour + ":" + minute + ":" + second;
}

var DynamicTime = function() {
	return {
		init : function(containerId){
			this._containerId_ = containerId;
			setInterval("DynamicTime.getTime()", 1000);
		},
		getTime:function(){
			var mon, day, now, hour, min, ampm, time, str, tz, end, beg, sec;  
		    day = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");  
		    now = new Date();  
		    hour = now.getHours();  
		    min = now.getMinutes();  
		    sec = now.getSeconds();  
		    if (hour < 10) {  
		        hour = "0" + hour;  
		    }  
		    if (min < 10) {  
		        min = "0" + min;  
		    }  
		    if (sec < 10) {  
		        sec = "0" + sec;  
		    }  
		    
		    y = now.getFullYear();
		    m =  now.getMonth() + 1;
		    d = now.getDate();
		    w = day[now.getDay()];
		    $("#" + this._containerId_).html(  
		            "<nobr>" + y + "-" + m + "-" + d + " " +  hour   + ":" + min + ":" + sec + ", " + day[now.getDay()] +  "</nobr>"); 
		}
	};
}();

var Login = function(){
	return {
		login : function(containerId){
			var errors = new Array("用户名不能为空，请输入用户名密码！","密码不正确，请确认密码！","验证码不能为空，请输入验证码！","没有此用户。");

			$("#login_tips").html("");
			var v1 = $.trim($("#username").val());
			
			if (v1 == ""){
				$("#login_tips").html(errors[0]);
				return;
			}
			
			var v2 = $.trim($("#password").val());
			if (v2 == ""){
				$("#login_tips").html(errors[1]);
				return;
			}
			$("#j_username").val($("#m").val() + ";" + v1 + ";" + v2 );
			Submit.submitForm("/login/login","fLogin",function(data){
				var result = data;
				
				if(result.success === true){
					var expiresDate = new Date();
		        	expiresDate.setTime(expiresDate.getTime() + 60 * 60 * 1000 * 24 * 365);
		        	$.cookie(result.asdfsd, result.zqccc , { expires: expiresDate , path: '/' });
					if ($("#_refresh_top_") && $("#_refresh_top_").val() == 1){
						if('reload'==result.rdp){
							window.top.location.reload();
						}else{
							window.top.location = result.rdp;
						}			
					} else {
						if('reload'==result.rdp){
							window.top.location.reload();
						}else{
							window.location.assign(result.rdp);
						}
					}
				}else{
					if("editProfile"==result.error){
					}else{
						$("#login_tips").html(result.error);
					}			
					return;
				}
			});
		},
		 
		/**
		 * 退出登录
		 */
		logout : function () {
			showConfirmDialog("确定要退出系统吗？","操作提示", function(){
				Submit.invok("/login/logout", function(result){
					var expiresDate = new Date();
		        	expiresDate.setTime(expiresDate.getTime() - 10000 );
					$.cookie(result.asdfsd, result.zqccc , { expires: expiresDate , path: '/' });
					$.cookie("_topacc_user_", "" , { expires: expiresDate , path: '/' });
					window.location.assign(result.url);
				});
			});
		}
	};
}();

var PayCalculator = function(){
	return {
		caculate : function(price, amount, labelId, inputId){
			if (amount == null)
				amount = 0;
			var money = price * amount;
			money = Math.round(money*Math.pow(10,2)) / Math.pow(10,2);  
			$("#" + labelId).html(money);
			$("#" + inputId).val(money);
		}
	};
}();

function reloadcode(){
    var verify = document.getElementById('img_data');
    verify.setAttribute('src','/SCS?v=' + generateRandomCode());
}




