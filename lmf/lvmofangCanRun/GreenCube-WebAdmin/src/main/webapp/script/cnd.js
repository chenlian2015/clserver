var simpleTools = 
	[ 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic','underline', '|', 
	'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist', 'insertunorderedlist', '|',
	'emoticons', 'image', 'code', 'link', '|', 'removeformat','undo', 'redo', 'fullscreen', 'source', 'about'];

/**
 * 浏览器类
 */
var TNavigator = function(){
	return {
		/**
		 * 判断是否是IE8浏览器
		 */
		isIE8 : function(price, amount, labelId, inputId){
		    var ver = TNavigator.getIEVersion();
		    if (ver > -1) {
		        if (ver >= 8.0)
		            return true;
		        else
		            return false;
		    }
		},
		
		/**
		 * 取得IE浏览器版本
		 */
		getIEVersion:function(){
			var rv = -1; // Return value assumes failure.
		    if (navigator.appName == 'Microsoft Internet Explorer') {
		        var ua = navigator.userAgent;
		        var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
		        if (re.exec(ua) != null)
		            rv = parseFloat(RegExp.$1);
		    }
		    return rv;
		},
		
		/**
		 * 判定是否是IE11（Edge浏览器）
		 */
		isMSEdgeBrowser : function(){
			// IE11 /IE Edge
			if (/rv:11.0./i.test(navigator.userAgent)){
				   return true;
			}
			return false;
		},
		
		/**
		 * 判断是否来自于微信浏览器
		 */
		isWeiXin : function() {
			var ua = navigator.userAgent.toLowerCase();
			if (ua.match(/MicroMessenger/i) == "micromessenger") {
				return true;
			} else {
				return false;
			}
		},
		
		/**
		 * 用于检测flash版本是否适合，是否需要升级
		 */
		isFlashVersionSuitable : function() {
			var hasFlash = 0; // 是否安装了flash
			var flashVersion = 0; // flash版本
			var isIE = 0; // 是否IE浏览器
			if (isIE) {
				try {
					var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
					if (swf) {
						hasFlash = 1;
						flashVersion = swf.GetVariable("$version");
					}
				} catch (exception) {
					console.log(exception);
				}

			} else {
				if (navigator.plugins && navigator.plugins.length > 0) {
					var swf = navigator.plugins["Shockwave Flash"];
					if (swf) {
						hasFlash = 1;
						flashVersion = swf.description.split(" ");
					}
				}
			}

			if (hasFlash == 0) {
				Dialog
						.showErrorDialog(
								'插件错误',
								"非常抱歉，你使用的Microsoft IE浏览器尚未安装Flash Player播放器插件，因此无法观看课程。<br/>你可以点击<a href='/download/Adobe_Flash_Player_for_IE_19.0.0.207.exe' style='color:blue;'>此处</a>安装Flash Player播放器。我们强烈推荐使用<span style='color:red;'>谷歌浏览器</span>以获得更好的播放效果。");
				return false;
			}
		}
	};
}();


/**
 * 浏览器类
 */
var TLoginChecker = function(){
	return {
		/**
		 * 在登录后执行回调
		 */
		doAfterLogin : function(_func_, isPop, redirectUrl) {
			// 发送同步请求
			url = typeof(redirectUrl) == "undefined" ? window.location.href : redirectUrl;
			
			$.ajax({
				type : "post",
				cache : false,
				async : true,
				url : '/login/islogin?_rd_url_=' + window.location.href,
				dataType : "json",
				success : function(resultdata, textStatus) {
					if (resultdata.success == true) {
						if (typeof (_func_) != "undefined") {
							_func_();
						}
					} else {
						var expiresDate = new Date();
						expiresDate.setTime(expiresDate.getTime() - 10000);
						$.cookie('TXv8CYuoO0OCREMxPZ', '', {
							expires : expiresDate,
							path : '/'
						});
						$.cookie("_topacc_user_", "", {
							expires : expiresDate,
							path : '/'
						});
						if (typeof(isPop) != "undefined" && isPop == true) {
							Dialog.showLoginDialogMini('/login/mini');
						} else 
							window.location.assign("/login");
					}
				}
			});
		},
		
		/**
		 * 判断是否登录
		 */
		assertlogin : function() {
			// 发送同步请求
			$.ajax({
				type : "post",
				cache : false,
				async : false,
				url : '/login/islogin',
				dataType : "json",
				success : function(resultdata, textStatus) {
					if (resultdata.success === true) {
						return true;
					} else {
						var expiresDate = new Date();
						expiresDate.setTime(expiresDate.getTime() - 10000);
						$.cookie('TXv8CYuoO0OCREMxPZ', '', {
							expires : expiresDate,
							path : '/'
						});
						$.cookie("_topacc_user_", "", {
							expires : expiresDate,
							path : '/'
						});
						window.location.assign("/login")
					}
				}
			});
		}
	};
}();

/**
 * 浏览器类
 */
var TJsonUtils = function(){
	return {
		/**
		 * 取得form中所有input元素的值，并组合成json对象
		 */
		getFormJson : function(frm) {
			var o = {};
			var a = jQuery(frm).serializeArray();
			jQuery.each(a, function() {
				if (o[this.name] !== undefined) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		},
		
		/**
		 * 将json对象转换为字符串
		 */
		json2String : function (obj){
			return JSON.stringify(obj)
		},
		
		/**
		 * 将字符串转换为json对象
		 */
		string2Json : function(str){
			return JSON.parse(str)
		},
	};
}();

/**
 * 工具类
 * @author 
 */
var Utils = function(){
	return {
		/**
		 * 跳转到指定的锚点
		 */
		gotoAnchor : function (theid){
			$('html,body').animate({scrollTop:$('#' + theid).offset().top}, 800);
		},
		
		/**
		 * 禁止事件冒泡，可兼容浏览器
		 */
		stopPropagation : function (e){
			var ev = e || window.event;
	        if (ev.stopPropagation) {
	            ev.stopPropagation();
	        }
	        else if (window.event) {//IE
	            window.event.cancelBubble = true;//IE
	        }
		},
		
		/**
		 * 取得参数
		 */
		getQueryString : function(name){
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if(r!=null)return  unescape(r[2]); return "";
		},
		
		getAction : function (name) {
			return Utils.getQueryString("action");
		},
		
		/**
		 * 产生一个随机数
		 */
		generateRandomCode : function(){
			var r = Math.random() * 100000;
			return r;
		},
		
		getContextPath : function (){
			var localObj = window.location;
			var contextPath = localObj.pathname.split("/")[1];
			if (contextPath == null || contextPath.length == 0)
				return contextPath;
			
			if (contextPath.endWith(".do")) {
				contextPath = contextPath.substring(0, contextPath.length - 3);
			}
			return contextPath;
		},
		
		/**
		 * 取得当前时间
		 * @returns
		 */
		getCurrentTime : function ()  { 
			var now= new Date();
			var hour = now.getHours();
			var minute=now.getMinutes();
			var second=now.getSeconds();
			return hour + ":" + minute + ":" + second;
		},
		
		/**
		 * 获取当前日期时间 yyyy-MM-dd HH:mm
		 */
		getCurrentDateTime :function(){
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
		    return  y + "-" + m + "-" + d + " " +  hour   + ":" + min + ":" + sec ;
		},
		
		/**
		 * 创建一个编辑器
		 */
		createEditor : function (editorID, _width_, _height_){
				var nextFormControl = 'input:not([type="hidden"]), textarea:not(.ke-edit-textarea), button[type="submit"], select';
		        editorTool = simpleTools;
		        
		        var K = KindEditor, $editor = $('#' + editorID);
		        var options =  {
		            width: _width_,
		            height: _height_,
		            items:editorTool,
		            filterMode: false, 
		            bodyClass:'article-content',
		            urlType:'relative', 
		            uploadJson: "/upload/forkindeditor?fn=imgFile&fid=url",
		            allowFileManager:true,
		            langType:'zh_CN',
		            afterBlur: function(){this.sync();$editor.prev('.ke-container').removeClass('focus');},
		            afterFocus: function(){$editor.prev('.ke-container').addClass('focus');},
		            afterChange: function(){$editor.change().hide();},
		            afterCreate : function() {
		                var doc = this.edit.doc; 
		                var cmd = this.edit.cmd; 
		                if(!K.WEBKIT && !K.GECKO)
		                {
		                    var pasted = false;
		                    $(doc.body).bind('paste', function(ev)
		                    {
		                        pasted = true;
		                        return true;
		                    });
		                    setTimeout(function()
		                    {
		                        $(doc.body).bind('keyup', function(ev)
		                        {
		                            if(pasted)
		                            {
		                                pasted = false;
		                                return true;
		                            }
		                            if(ev.keyCode == 86 && ev.ctrlKey) alert('您的浏览器不支持粘贴图片！');
		                        })
		                    }, 10);
		                }
		            },
		            afterTab: function(id)
		            {
		                var $next = $editor.next(nextFormControl);
		                if(!$next.length) $next = $editor.parent().next().find(nextFormControl);
		                if(!$next.length) $next = $editor.parent().parent().next().find(nextFormControl);
		                $next = $next.first().focus();
		                var keditor = $next.data('keditor');
		                if(keditor) keditor.focus();
		                else if($next.hasClass('chosen')) $next.trigger('chosen:activate');
		            }
		        };
		        try
		        {
		            if(!window.editor) window.editor = {};
		            var keditor = K.create('#' + editorID, options);
		            window.editor['#'] = window.editor[editorID] = keditor;
		            $editor.data('keditor', keditor);
		        }
		        catch(e){}
		},
		showMessage : function (nr){
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
		},
		showWeixinQRCode : function(){
			var nr = '<div class="t_center mt20 mb10"><img src="/themes/front/images/qrcode.jpg"></div>'; 
			dialog({
				fixed: true,
				title: "鼎尖教育官方微信",
				width:450, 
				height:450,
				lock:false,
				backdropOpacity:0.3,
				content: nr
			}).showModal();
		}
	};
}();

/**
 * 浏览器类
 */
var TSMSUtils = function(){
	return {
		/**
		 * 发送手机验证码
		 */
		sendVerifyCode : function (phone, msg){
			params = {};
			params["phone"] = phone;
			params["msg"] = msg;
			_p_ = jQuery.param(params);
			jQuery.ajax({
				type : "post",
				cache : false,
				data : _p_,
				dataType: 'json',
				url : '/wbs/sendMobileVerifyCode',
				success : function(result, textStatus){
					if (result.success === true) {
						Utils.showMessage("验证码已发送至您的手机");
					} else{
						alert(result.msg);
					}
				}
			});
		}
	};
}();

/*******************************************************************************
 * * 不可剪切的图片点击直接上传 * 作者 胡晓光 * *
 ******************************************************************************/
var TImageUpload = function() {
	return {
		/**
		 * strictsize -boolean 是否限制上传图片尺寸 width 缩略图宽度 height 缩略图高度 _initpic_
		 * 初始图片 funcallback 回调函数 _maxUploadSize_ 允许最大上传多大尺寸的图片（指图片尺寸）
		 * _strictsize_ 是否严格限定上传文件尺寸 _strictlen_ 是否严格限定上传文件大小 maxlen 最大上传大小
		 * maxsize 最大上传尺寸
		 */
		createUploadImage : function(fieldid, width, height, _initpic_,
				funcallback, strictlen, maxlen, strictsize, maxsize) {
			var _strictlen_ = true;
			var _maxlen_ = 1;
			var _strictsize_ = true;
			var _maxsize_ = "1000x1000";

			initpic = "/themes/_default_/images/no_pic.jpg";

			if (typeof (_initpic_) != "undefined")
				initpic = _initpic_;

			if (typeof (strictlen) != "undefined")
				_strictlen_ = strictlen;

			if (typeof (maxlen) != "undefined")
				_maxlen_ = maxlen;

			if (typeof (strictsize) != "undefined")
				_strictsize_ = strictsize;

			if (typeof (maxsize) != "undefined")
				_maxsize_ = maxsize;

			if (initpic == null)
				initpic = "/themes/_default_/images/no_pic.jpg";

			buttonClassName = 'uploadify_' + fieldid;

			styleHtml = '';
			styleHtml += '.' + buttonClassName + ' {';
			styleHtml += "	background: url('" + initpic + "');";
			styleHtml += '	border-radius: 2px;';
			styleHtml += '	border:1px solid lightgray;';
			styleHtml += '	background-size:100% 100%;';
			styleHtml += '	-moz-background-size:100% 100%;';
			styleHtml += '	width:' + width + 'px;';
			styleHtml += '	height: ' + height + 'px;';
			styleHtml += '	cursor: pointer;';
			styleHtml += '	overflow: visible;';
			styleHtml += '	font-family: Verdana,Arial,"Microsoft Yahei","宋体";';
			styleHtml += '	outline: none;';
			styleHtml += '	font-weight:lighter;';
			styleHtml += '	text-shadow:none;';

			// 对于IE7/IE8浏览器不支持CSS3的措施
			styleHtml += "   filter: progid:DXImageTransform.Microsoft.AlphaImageLoader( src='"
					+ initpic + "',sizingMethod='scale');";
			styleHtml += '}';

			styleHtml += '.uploadify:hover .' + buttonClassName + ' {';
			styleHtml += "	background: url('" + initpic + "');";
			styleHtml += '	border-radius: 2px;';
			styleHtml += '	border:1px solid #99E448;';
			styleHtml += '	background-size:100% 100%;';
			styleHtml += '	-moz-background-size:100% 100%;';
			styleHtml += '	width:' + width + 'px;';
			styleHtml += '	height: ' + height + 'px;';
			styleHtml += '	cursor: pointer;';
			styleHtml += '	overflow: visible;';
			styleHtml += '	font-family: Verdana,Arial,"Microsoft Yahei","宋体";';
			styleHtml += '	outline: none;';
			styleHtml += '	font-weight:lighter;';
			styleHtml += '	text-shadow:none;';
			// 对于IE7/IE8浏览器不支持CSS3的措施
			styleHtml += "   filter: progid:DXImageTransform.Microsoft.AlphaImageLoader( src='"
					+ initpic + "',sizingMethod='scale');";
			styleHtml += '}';

			// 创建一个style
			$(
					"<style id='" + buttonClassName + "_"
							+ Utils.generateRandomCode() + "'>" + styleHtml
							+ "</style>").appendTo($("head"));
			el = $("#" + fieldid);
			uploadFileName = el.attr("name");
			// 创建按钮
			el.uploadify({
				'buttonText' : '',
				'buttonClass' : buttonClassName,
				'fileSizeLimit' : _maxlen_ + 'MB',
				'fileTypeDesc' : '图片文件',
				'fileTypeExts' : '*.jpg; *.png; *.gif; *.jpeg',
				height : height,
				width : width,
				swf : '/script/uploadify/uploadify.swf',
				uploader : '/upload?fn=' + uploadFileName + "&fid="
						+ buttonClassName + "&strictSize=" + _strictsize_
						+ "&maxsize=" + _maxsize_ + "&strictLength="
						+ _strictlen_ + "&maxlen=" + _maxlen_,
				'fileObjName' : uploadFileName,
				'simUploadLimit' : 1,
				'multi' : false,
				'onUploadSuccess' : function(file, data, response) {
					var jsonobj = eval('(' + data + ')');
					if (jsonobj.success === true) {
						$("." + jsonobj.data.fid).css("background-image",
								"url('" + jsonobj.data.url + "')");
						$("." + jsonobj.data.fid).css(
								"filter",
								"progid:DXImageTransform.Microsoft.AlphaImageLoader( src='"
										+ jsonobj.data.url
										+ "',sizingMethod='scale')");
						if (typeof (funcallback) != "undefined") {
							funcallback(jsonobj);
						}
					} else {
						Dialog.showErrorMessage(jsonobj.msg);
					}
				}
			});
		}
	};
}();