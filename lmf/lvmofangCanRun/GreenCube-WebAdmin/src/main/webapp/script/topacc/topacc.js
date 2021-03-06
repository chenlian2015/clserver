if (typeof (console) == "undefined") {
	var names = [ "log", "debug", "info", "warn", "error", "assert", "dir",
			"dirxml", "group", "groupEnd", "time", "timeEnd", "count", "trace",
			"profile", "profileEnd" ];

	window.console = {};
	for (var i = 0; i < names.length; ++i)
		window.console[names[i]] = function() {
		}
}
/**
 * JS前端 类定义
 * 
 * @param str
 * @returns
 */

/*******************************************************************************
 * * String扩展 * 作者 胡晓光 * *
 ******************************************************************************/
String.prototype.endWith = function(str) {
	var reg = new RegExp(str + "$");
	return reg.test(this);
};

String.prototype.trim = function () {
	return this .replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
};

String.prototype.toDate = function(separator) {
	if (!separator) {
		separator = "-";
	}
	var dateArr = this.split(separator);
	var year = parseInt(dateArr[0]);
	var month;

	// 处理月份为04这样的情况
	if (dateArr[1].indexOf("0") == 0) {
		month = parseInt(dateArr[1].substring(1));
	} else {
		month = parseInt(dateArr[1]);
	}
	var day = parseInt(dateArr[2]);
	var date = new Date(year, month - 1, day);
	return date;
};

String.prototype.delHtmlTag = function() {
	return this.replace(/<[^>]+>/g, "");// 去掉所有的html标记
};

String.prototype.startWith = function(str) {
	if (str == null || str == "" || this.length == 0
			|| str.length > this.length)
		return false;
	if (this.substr(0, str.length) == str)
		return true;
	else
		return false;
	return true;
};

String.prototype.toJson = function() {
	return JSON.parse(this);
};

Array.prototype.indexOf = function(val) {              
    for (var i = 0; i < this.length; i++) {  
        if (this[i] == val) return i;  
    }  
    return -1;  
};  

Array.prototype.remove = function(val) {  
    var index = this.indexOf(val);  
    if (index > -1) {  
        this.splice(index, 1);  
    }  
};  
/*******************************************************************************
 * * 日期格式化 * 作者 胡晓光 * *
 ******************************************************************************/
var DateFormat = function() {
	return {
		format : function(dateobj, pattern) {
			var d = new Date(dateobj.time);
			var date = {
				"M+" : d.getMonth() + 1,
				"d+" : d.getDate(),
				"H+" : d.getHours(),
				"m+" : d.getMinutes(),
				"s+" : d.getSeconds(),
				"q+" : Math.floor((d.getMonth() + 3) / 3),
				"S+" : d.getMilliseconds()
			};
			if (/(y+)/i.test(pattern)) {
				pattern = pattern.replace(RegExp.$1, (d.getFullYear() + '')
						.substr(4 - RegExp.$1.length));
			}
			for ( var k in date) {
				if (new RegExp("(" + k + ")").test(pattern)) {
					pattern = pattern.replace(RegExp.$1,
							RegExp.$1.length == 1 ? date[k] : ("00" + date[k])
									.substr(("" + date[k]).length));
				}
			}
			return pattern;
		}
	};
}();

/*******************************************************************************
 * * 对话框类 * 作者 胡晓光 * *
 ******************************************************************************/
var Dialog = function() {
	return {
		/**
		 * 显示一个迷你的登录对话框
		 */
		showLoginDialogMini : function(url){
			var hml = '<iframe frameborder="0" id="'
				+ Utils.generateRandomCode()
				+ '" name="_popu_frmContent_" scrolling="yes" src="' + url
				+ '" style="width:' + 400 + 'px;height:' + 295
				+ 'px" ></iframe>';
			dialog({
				fixed : true,
				title : '您尚未登录',
				lock : true,
				backdropOpacity : 0.3,
				skin:'pop_login',
				content : hml
			}).showModal();
		},
		
		/**
		 * 显示出绿色的消息对话框，两秒后自动消失，非模太对话框 @ message 要显示的内容
		 */
		showMessage : function(message) {
			dialog({
				fixed : true,
				title : false,
				width : 180,
				lock : true,
				padding : 10,
				backdropOpacity : 0.3,
				skin : 'operation_ok',
				content : '<div class="t_center">' + message + '</div>',
				onshow : function() {
					var t = this;
					var mys = setTimeout(function() {
						t.remove();
						clearTimeout(mys);
					}, 2000)
				}
			}).show();
		},

		/**
		 * 显示错误信息，非模态对话框 @ message 错误信息内容
		 */
		showErrorMessage : function(message) {
			dialog({
				fixed : true,
				title : false,
				width : 180,
				lock : true,
				padding : 10,
				backdropOpacity : 0.3,
				skin : 'operation_error',
				content : '<div class="t_center">' + message + '</div>',
				onshow : function() {
					var t = this;
					var mys = setTimeout(function() {
						t.close();
						clearTimeout(mys);
					}, 4000)
				}
			}).show();
		},

		/**
		 * 弹出显示一个确认对话框，点击确定按钮后回调自定义函数，模态对话框 @ message 消息内容 @ fn 程序员自定义函数
		 */
		showConfirmDialog : function(message, fn) {
			var nr = '<div class="t_center mt20 mb10">' + message + "</div>";
			var tit = "温馨提示";
			artD = dialog({
				fixed : true,
				title : tit,
				width : 320,
				lock : true,
				content : nr,
				backdropOpacity : 0.3,
				cancelValue : "否",
				cancel : function() {
				},
				ok : function() {
					fn();
				},
				okValue : "是"
			}).showModal();
		},
		
		/**
		 * 弹出显示一个确认对话框，点击确定按钮后回调自定义函数，模态对话框 @ message 消息内容 @ fn 程序员自定义函数
		 */
		showInformationDialog : function(message, fn) {
			var nr = '<div class="t_center mt20 mb10">' + message + "</div>";
			var tit = "温馨提示";
			artD = dialog({
				fixed : true,
				title : tit,
				width : 320,
				lock : true,
				content : nr,
				backdropOpacity : 0.3,
				ok : function() {
					fn();
				},
				okValue : "确定"
			}).showModal();
		},

		/**
		 * 显示一个错误对话框，对话框上有错误标志 @ title 对话框标题 @ message 对话框要显示的内容
		 */
		showErrorDialog : function(title, message) {
			dialog(
					{
						id : '_page_loading_dialog_',
						fixed : true,
						title : title,
						lock : true,
						padding : 10,
						backdropOpacity : 0.3,
						skin : 'dialog_error',
						ok : function() {
							this.remove();
						},
						okValue : "关闭",
						content : '<div class="t_center"><img src="/themes/topacc/images/error_tips.png"> '
								+ message + '</div>'
					}).showModal();
		},

		/**
		 * 弹出对话框 @ url 需要显示的网页的网址 @ title 对话框标题
		 */
		showDialog : function(url, title) {
			Dialog.showLoading("正在读取，请稍后 ...");
			$.ajax({
				url : url,
				cache : false,
				success : function(html) {
					try {
						dialog({
							fixed : true,
							title : title,
							lock : true,
							backdropOpacity : 0.3,
							content : html
						}).showModal();
					} catch (exception) {
						console.log(exception);
					}
					Dialog.closeLoading();
				}
			});
		},

		/**
		 * 关闭对话框
		 */
		closeDialog : function(dialogId) {
			dialogObj = $("#" + dialogId).parents("div[class='ui-dialog']");
			if (dialogObj.length > 0) {
				dialogClose = $(dialogObj[0]).find(
						"button[class='ui-dialog-close']");
				dialogClose.click();
			}
		},
		/**
		 * 显示一个IFrame对话框
		 */
		showIFrameDialog : function(url, title, width, height) {
			var hml = '<iframe frameborder="0" id="'
					+ Utils.generateRandomCode()
					+ '" name="_popu_frmContent_" scrolling="yes" src="' + url
					+ '" style="width:' + width + 'px;height:' + height
					+ 'px" ></iframe>';
			dialog({
				fixed : true,
				title : title,
				lock : true,
				backdropOpacity : 0.3,
				content : hml
			}).showModal();
		},

		/**
		 * 显示正在加载遮罩进度条 @ message 要显示的提示消息内容
		 */
		showLoading : function(message) {
			dialog(
					{
						id : '_page_loading_dialog_',
						fixed : true,
						title : false,
						width : 180,
						lock : true,
						padding : 10,
						backdropOpacity : 0.3,
						skin : 'operation_ok',
						content : '<div class="t_center" style="background: url(/themes/topacc/images/loading16.gif) no-repeat; height:25px;line-height:25px;padding-left:32px;background-size:31px 31px;-moz-background-size:25px 25px;-webkit-background-size:25px 25px;font-size:12px;">'
								+ message + '</div>'
					}).showModal();
		},

		/**
		 * 关闭正在加载遮罩进度条
		 */
		closeLoading : function() {

			try {
				if (typeof (dialog) != "undefined" && dialog != null
						&& dialog.list['_page_loading_dialog_'] != null) {
					dialog.list['_page_loading_dialog_'].remove();
				}
			} catch (exception) {
				// console.log(exception);
			}
		}
	};
}();

/*******************************************************************************
 * * 工具类 * 作者 胡晓光 * *
 ******************************************************************************/
var Utils = function() {
	return {
		isMSEdgeBrowser : function(){
			// IE11 /IE Edge
			if (/rv:11.0./i.test(navigator.userAgent)){
				   return true;
			}
			return false;
		},
		stopPropagation : function (e){
			var ev = e || window.event;
	        if (ev.stopPropagation) {
	            ev.stopPropagation();
	        }
	        else if (window.event) {//IE
	            window.event.cancelBubble = true;//IE
	        }
		},
		
		gotoAnchor : function (theid){
			$('html,body').animate({scrollTop:$('#' + theid).offset().top}, 800);
		},
		/**
		 * 生成一个链接
		 */
		sendApply : function(formid, okmessage) {
			Request.submit('/wbs/apply', formid,function(result){
				if (typeof(okmessage) != "undefined")
					Dialog.showMessage(okmessage);
			}, null, true);
		},
		
		/**
		 * 生成一个链接
		 */
		computeUrl : function(url) {
			// if (!url.startWith("/")) {
			// url = "/ptapi?m=" + url;
			// }
			return url;
		},

		isWeiXin : function() {
			var ua = navigator.userAgent.toLowerCase();
			if (ua.match(/MicroMessenger/i) == "micromessenger") {
				return true;
			} else {
				return false;
			}
		},

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
		},

		/**
		 * 判断机构是否登录
		 */
		assertInslogin : function() {
			// 发送同步请求
			$.ajax({
				type : "post",
				cache : false,
				async : false,
				url : '/login/isInslogin',
				dataType : "json",
				success : function(resultdata, textStatus) {
					if (resultdata.success === true) {
						return true;
					} else {
						window.location.assign("/institution/login");
					}
				}
			});
		},

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
		},

		/**
		 * 解析服务器端返回的错误
		 */
		parseResultError : function(status) {
			if (status.success === false) {
				els = status.elements;
				if (els.length == 0)
					return;

				if (els.length == 1 && els[0].element == "") {
					Dialog.showErrorMessage(els[0].message);
				} else if (els.length > 0) {
					$.each(els, function(index, el) {
						Utils.errorInput(el.element, el.message);
					});
				}
			} else {
				// 正确操作，读取消息内容
				els = status.elements;
				if (els.length == 0)
					return;

				msg = els[0];
				if ($("#message-content").length > 0) {
					$("#message-content").html(msg.message);

					url = "";
					if (msg.redirect.length > 0) {
						url = msg.redirect;
					} else {
						url = "/";
					}
					var jump_minute = 5;
					setInterval(
							function() {
								jump_minute--;
								document.getElementById("minute-jump").innerHTML = jump_minute;
							}, 1000);
					setTimeout(function() {
						window.location.assign(url);
						window.close();
					}, 5 * 1000);
				} else {
					Dialog.showMessage(msg.message);
				}
			}
		},

		/**
		 * 显示在input输入框上方的错误提示信息框
		 */
		errorInput : function(ts, message) {
			$(ts).validationEngine('showPrompt', message, 'formError', null,
					true);
		},

		/**
		 * 判断是否存在一个元素
		 */
		existElement : function(indefier) {
			return $(indefier).length > 0;
		},

		/**
		 * 将json对象转换为字符串
		 */
		json2String : function(obj) {
			return JSON.stringify(obj)
		},

		/**
		 * 将字符串转换为json对象
		 */
		string2Json : function(str) {
			return JSON.parse(str)
		},

		/**
		 * 取得参数
		 */
		getQueryString : function(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return unescape(r[2]);
			return "";
		},

		getAction : function() {
			var localObj = window.location;
			ps = localObj.pathname.split("/");

			if (ps == null || ps.length == 0)
				return "";

			if (ps.length > 2) {
				return ps[2];
			}
			return "";
		},

		/**
		 * 产生一个随机数
		 */
		generateRandomCode : function() {
			var r = Math.random() * 100000;
			return r;
		},

		getContextPath : function() {
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
		 * 发送手机验证码
		 */
		sendVerifyCode : function(phone, msg) {
			params = {};
			params["phone"] = phone;
			params["msg"] = msg;
			_p_ = jQuery.param(params);
			jQuery.ajax({
				type : "post",
				cache : false,
				data : _p_,
				dataType : 'json',
				url : '/wbs/sendMobileVerifyCode',
				success : function(result, textStatus) {
					if (result.success === true) {
						Dialog.showMessage("验证码已发送至您的手机");
					} else {
						alert(result.msg);
					}
				}
			});
		},

		/**
		 * 取得当前时间
		 * 
		 * @returns
		 */
		getCurrentTime : function() {
			var now = new Date();
			var hour = now.getHours();
			var minute = now.getMinutes();
			var second = now.getSeconds();
			return hour + ":" + minute + ":" + second;
		},

		/**
		 * 获取当前日期时间 yyyy-MM-dd HH:mm
		 */
		getCurrentDateTime : function() {
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
			m = now.getMonth() + 1;
			d = now.getDate();
			w = day[now.getDay()];
			return y + "-" + m + "-" + d + " " + hour + ":" + min + ":" + sec;
		},

		showWeixinQRCode : function() {
			Dialog.showDialog("/qrcode", "微信扫码");
		},

		playVideo : function(vid, title) {
			playurl = "/video/" + vid;
			$.ajax({
				url : playurl,
				cache : false,
				success : function(html) {
					dialog({
						fixed : true,
						title : title,
						lock : true,
						width : 560,
						height : 400,
						backdropOpacity : 0.3,
						content : html
					}).showModal();
				}
			});
		}
	};
}();

/*******************************************************************************
 * * 提示工具 * 作者 胡晓光 * *
 ******************************************************************************/
var Tips = function() {
	return {
		removeTips : function() {
			$("#op_tips").remove();
			$("#tips_mask").remove();
		},
		showAccountTips : function(id, url, tit) {
			if ($("#op_tips").length >= 1) {
				return null;
			}
			var ms = $("<div>");
			var tips = $("<div>");
			var tag = $(id);
			var tp = tag.offset().top;
			var lf = tag.offset().left;
			tips.attr({
				"class" : "op_tips",
				"id" : "op_tips"
			});
			ms.attr({
				"class" : "tips_mask",
				"id" : "tips_mask"
			});
			tips.appendTo($("body"));
			ms.appendTo($("body"));
			tips.css({
				top : tp - 114,
				left : lf
			});

			var nr = '<div class="rel">'
					+ '<span class="op_tips_nr">'
					+ tit
					+ '</span>'
					+ '<img src="'
					+ url
					+ '" width="280" height="40" alt=""/>'
					+ '<div class="op_tips_txt">首次登录请完善本机构信息</div>'
					+ '<a onclick="Tips.removeTips()" href="javascript:void(0)" class="btn btn_green d_ib">我知道了</a>'
					+ '</div>';
			tips.html(nr);
			ms.height($(window).height());
			$(window).resize(function(e) {
				if ($("#op_tips").length <= 0) {
					return null;
				}
				var tp = tag.offset().top;
				var lf = tag.offset().left;
				$("#op_tips").css({
					top : tp - 114,
					left : lf
				});
				$("#tips_mask").height($(window).height());
			});
		},

		/**
		 * 显示提示
		 */
		showTips : function(id, url, tit) {
			if ($("#op_tips").length >= 1) {
				return null;
			}
			var ms = $("<div>");
			var tips = $("<div>");
			var tag = $(id);
			var tp = tag.offset().top;
			var lf = tag.offset().left;
			tips.attr({
				"class" : "op_tips",
				"id" : "op_tips"
			});
			ms.attr({
				"class" : "tips_mask",
				"id" : "tips_mask"
			});
			tips.appendTo($("body"));
			ms.appendTo($("body"));
			tips.css({
				top : tp - 114,
				left : lf
			});

			var nr = '<div class="rel">'
					+ '<span class="op_tips_nr">'
					+ tit
					+ '</span>'
					+ '<img src="'
					+ url
					+ '" width="280" height="40" alt=""/>'
					+ '<div class="op_tips_txt">以便于接下来开通课程</div>'
					+ '<a onclick="Tips.removeTips()" href="javascript:void(0)" class="btn btn_green d_ib">我知道了</a>'
					+ '</div>';
			tips.html(nr);
			ms.height($(window).height());
			$(window).resize(function(e) {
				if ($("#op_tips").length <= 0) {
					return null;
				}
				var tp = tag.offset().top;
				var lf = tag.offset().left;
				$("#op_tips").css({
					top : tp - 114,
					left : lf
				});
				$("#tips_mask").height($(window).height());
			});
		}
	};
}();

/*******************************************************************************
 * * 地区选择类 * 作者 胡晓光 * *
 ******************************************************************************/
var AreaSelect = function() {
	return {
		create : function() {
			if (typeof (areas) != "undefined") {
				selectedProvince = $("#province").attr("data-selected");
				selectedCity = $("#city").attr("data-selected");

				if (typeof (selectedProvince) == "undefined") {
					selectedProvince = "";
				}

				if (typeof (selectedCity) == "undefined") {
					selectedCity = "";
				}

				provinceHtml = "";
				$.each(areas, function(index, element) {
					if (element.CClassCode.length == 0) {
						if (element.CId == selectedProvince) {
							provinceHtml += "<option selected value='"
									+ element.CId + "_" + element.CName + "'>"
									+ element.CName + "</option>";
						} else {
							provinceHtml += "<option value='" + element.CId
									+ "_" + element.CName + "'>"
									+ element.CName + "</option>";
						}
					}
				});
				$("#province").html(provinceHtml);

				$('#province').bind(
						'change',
						function() {
							selid = $("#province").val();
							cs = $("#province").attr("data-selected")

							if (typeof (cs) != "undefined" && cs == selid) {
								return;
							}

							$("#province").attr("data-selected", selid);

							$('#city').empty();
							len = areas.length;
							cityhtml = "";
							for (var i = 0; i < len; i++) {
								vname = areas[i].CId + "_" + areas[i].CName;
								if (vname === selid) {
									// 判断当前选定的是否是直辖市
									if (areas[i].NIsProvinceCity == 1) {
										cityhtml += "<option value='"
												+ areas[i].CId + "_"
												+ areas[i].CName + "'>"
												+ areas[i].CName + "</option>";
										break;
									} else {
										$.each(areas[i].subAreas, function(
												index, element) {
											cityhtml += "<option value='"
													+ element.CId + "_"
													+ element.CName + "'>"
													+ element.CName
													+ "</option>";
										});
										break;
									}
								}
							}
							$("#city").html(cityhtml);
						});

				if (selectedCity.length > 0) {
					// 将市列表显示到市区的select中去
					selid = $("#province").val();
					$('#city').empty();
					len = areas.length;

					cityhtml = "";
					for (var i = 0; i < len; i++) {
						vname = areas[i].CId + "_" + areas[i].CName;
						if (vname === selid) {
							// 判断当前选定的是否是直辖市
							if (areas[i].NIsProvinceCity == 1) {
								cityhtml += "<option value='" + areas[i].CId
										+ "_" + areas[i].CName + "'>"
										+ areas[i].CName + "</option>";
							} else {
								$.each(areas[i].subAreas, function(index,
										element) {
									if (selectedCity == element.CId) {
										cityhtml += "<option value='"
												+ element.CId + "_"
												+ element.CName
												+ "' selected='selected'>"
												+ element.CName + "</option>";
									} else
										cityhtml += "<option value='"
												+ element.CId + "_"
												+ element.CName + "'>"
												+ element.CName + "</option>";
								});
							}
							break;
						}
					}
					$("#city").html(cityhtml);
				}
			}
		},
		gotoLocation : function(lon, lat, name) {
			this.map.clearOverlays();
			this.point = new BMap.Point(lon, lat);
			this.map.centerAndZoom(this.point, 15);

			var marker = new BMap.Marker(this.point);

			if (name && typeof (name) != "undefined")
				marker.setTitle(name);

			this.map.addOverlay(marker);
		}
	};
}();

/*******************************************************************************
 * * 百度地图类 * 作者 胡晓光 * *
 ******************************************************************************/
var BaiduMap = function() {
	return {
		createMap : function(container, lon, lat, name) {
			this.map = new BMap.Map(container);
			this.point = new BMap.Point(lon, lat);
			this.map.centerAndZoom(this.point, 15);

			// 创建标注
			var marker = new BMap.Marker(this.point);
			if (name && typeof (name) != "undefined") {
				marker.setTitle(name);
				this.map.addOverlay(marker);

				marker.setAnimation(BMAP_ANIMATION_BOUNCE); // 跳动的动画
				var label = new BMap.Label(name, {
					offset : new BMap.Size(0, 30)
				});
				marker.setLabel(label);
			}

			this.map.enableScrollWheelZoom(); // 启用滚轮放大缩小，默认禁用
			this.map.enableContinuousZoom(); // 启用地图惯性拖拽，默认禁用

			this.map.addControl(new BMap.NavigationControl()); // 添加默认缩放平移控件
			this.map.addControl(new BMap.OverviewMapControl()); // 添加默认缩略地图控件

		},
		gotoLocation : function(lon, lat, name) {
			this.map.clearOverlays();
			this.point = new BMap.Point(lon, lat);
			this.map.centerAndZoom(this.point, 15);

			var marker = new BMap.Marker(this.point);

			if (name && typeof (name) != "undefined")
				marker.setTitle(name);

			this.map.addOverlay(marker);
		},
		searchLine : function(x, y) {
			var localSearch = new BMap.LocalSearch(this.map);
			this.map.clearOverlays();
			var p2;
			var keyword = $("#startLocation").val();
			var cxfs = $("input[name=travel]:checked").attr("value");
			var p1 = new BMap.Point(x, y);

			currentMap = this.map;
			localSearch.setSearchCompleteCallback(function(searchResult) {
				var poi = searchResult.getPoi(0);
				if (typeof (poi) == 'undefined') {
					Dialog.showErrorMessage("不存在的坐标！请重新输入！");
					return false;
				} else {
					p2 = new BMap.Point(poi.point.lng, poi.point.lat);
					if (cxfs == 0) {
						var transit = new BMap.TransitRoute(currentMap, {
							renderOptions : {
								map : currentMap
							}
						});
						transit.search(p2, p1);
					} else if (cxfs == 1) {
						var driving = new BMap.DrivingRoute(currentMap, {
							renderOptions : {
								map : currentMap,
								autoViewport : true
							}
						});
						driving.search(p2, p1);
					}
				}
			});
			localSearch.search(keyword);
		}

	};
}();

/*******************************************************************************
 * * 编辑器类 * 作者 胡晓光 * *
 ******************************************************************************/
var KEditor = function() {
	return {
		/**
		 * 创建一个编辑器
		 */
		createEditor : function(editorID, _width_, _height_) {
			var nextFormControl = 'input:not([type="hidden"]), textarea:not(.ke-edit-textarea), button[type="submit"], select';
			editorTool = [ 'formatblock', 'fontname', 'fontsize', '|',
					'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
					'|', 'justifyleft', 'justifycenter', 'justifyright',
					'insertorderedlist', 'insertunorderedlist', '|',
					'emoticons', 'image', 'code', 'link', '|', 'removeformat',
					'undo', 'redo', 'fullscreen', 'source', 'about' ];

			var K = KindEditor, $editor = $('#' + editorID);
			var options = {
				width : _width_,
				height : _height_,
				items : editorTool,
				filterMode : true,
				bodyClass : 'article-content',
				urlType : 'relative',
				uploadJson : "/fileupload/forkindeditor?fn=imgFile&fid=url",
				allowFileManager : true,
				langType : 'zh_CN',
				afterBlur : function() {
					this.sync();
					$editor.prev('.ke-container').removeClass('focus');
				},
				afterFocus : function() {
					$editor.prev('.ke-container').addClass('focus');
				},
				afterChange : function() {
					$editor.change().hide();
				},
				afterCreate : function() {
					var doc = this.edit.doc;
					var cmd = this.edit.cmd;
					if (!K.WEBKIT && !K.GECKO) {
						var pasted = false;
						$(doc.body).bind('paste', function(ev) {
							pasted = true;
							return true;
						});
						setTimeout(function() {
							$(doc.body).bind('keyup', function(ev) {
								if (pasted) {
									pasted = false;
									return true;
								}
								if (ev.keyCode == 86 && ev.ctrlKey)
									alert('您的浏览器不支持粘贴图片！');
							})
						}, 10);
					}
				},
				afterTab : function(id) {
					var $next = $editor.next(nextFormControl);
					if (!$next.length)
						$next = $editor.parent().next().find(nextFormControl);
					if (!$next.length)
						$next = $editor.parent().parent().next().find(
								nextFormControl);
					$next = $next.first().focus();
					var keditor = $next.data('keditor');
					if (keditor)
						keditor.focus();
					else if ($next.hasClass('chosen'))
						$next.trigger('chosen:activate');
				}
			};
			try {
				if (!window.editor)
					window.editor = {};
				var keditor = K.create('#' + editorID, options);
				window.editor['#'] = window.editor[editorID] = keditor;
				$editor.data('keditor', keditor);
			} catch (e) {
			}
		}
	};
}();

/*******************************************************************************
 * * 不可剪切的图片点击直接上传 * 作者 胡晓光 * *
 ******************************************************************************/
/*******************************************************************************
 * * 不可剪切的图片点击直接上传 * 作者 胡晓光 * *
 ******************************************************************************/
var ImageUpload = function() {
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

			initpic = "/themes/topacc/images/no_pic.jpg";

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
				initpic = "/themes/topacc/images/no_pic.jpg";

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
				uploader : '/fileupload?fn=' + uploadFileName + "&fid="
						+ buttonClassName + "&strictSize=" + _strictsize_
						+ "&maxsize=" + _maxsize_ + "&strictLength="
						+ _strictlen_ + "&maxlen=" + _maxlen_,
				'fileObjName' : uploadFileName,
				'simUploadLimit' : 1,
				'multi' : false,
				'onUploadSuccess' : function(file, data, response) {
					var jsonobj = eval('(' + data + ')');
					if (jsonobj.success === true) {
						$("." + jsonobj.msg.fid).css("background-image",
								"url('" + jsonobj.msg.url + "')");
						$("." + jsonobj.msg.fid).css(
								"filter",
								"progid:DXImageTransform.Microsoft.AlphaImageLoader( src='"
										+ jsonobj.msg.url
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

/*******************************************************************************
 * * 可剪切图片上传类 * 作者 胡晓光 * *
 ******************************************************************************/
var ImageCut = function() {
	return {
		/**
		 * imgid 图片id hid hidden id previewOptions -
		 * 有几个预览，尺寸多少例:[{"width":"150", "height":150},{"width":"120",
		 * "height":"120"}];
		 */
		openImageCutDialog : function(imgid, hid, previewOptions) {
			url = '/wbs/imgcut?imgid=' + imgid + "&hid=" + hid;
			if (typeof (previewOptions) != "undefined") {
				preview = encodeURIComponent(encodeURIComponent(JSON
						.stringify(previewOptions)));
				url = url + "&preview=" + preview;
			}
			Dialog.showDialog(url, '图片裁剪');
		}
	};
}();

/*******************************************************************************
 * * 可剪切图片上传类 * 作者 胡晓光 * *
 ******************************************************************************/
var FileUpload = function() {
	return {
		/**
		 * imgid 图片id hid hidden id previewOptions -
		 * 有几个预览，尺寸多少例:[{"width":"150", "height":150},{"width":"120",
		 * "height":"120"}];
		 */
		createUploadButton : function(fieldid, hid, _maxUploadSize_,
				funcallback, type, isMulti) {
			_isMulti = typeof(isMulti) != "undefined" ? isMulti : true;
			maxUploadSize = 1;

			// 判定两个参数是否给定
			if (typeof (_maxUploadSize_) != "undefined")
				maxUploadSize = _maxUploadSize_;

			if (maxUploadSize == null)
				maxUploadSize = 200;

			if (maxUploadSize > 200)
				maxUploadSize = 200;

			el = $("#" + fieldid);
			uploadFileName = el.attr("name");

			_type_ = "image";
			if (typeof (type) != "undefined")
				_type_ = type;

			// 创建按钮
			el.uploadify({
						'buttonClass' : 'uploadify_file',
						'buttonText' : '请选择文件',
						'fileSizeLimit' : maxUploadSize + 'MB',
						'fileTypeDesc' : '文件',
						'fileTypeExts' : '*.png;*.jpg;*.gif',
						swf : '/script/uploadify/uploadify.swf',
						uploader : '/fileupload?fn=' + uploadFileName + "&fid="
								+ hid + "&strictsize=true&maxsize="
								+ maxUploadSize + "&type=" + _type_,
						'fileObjName' : uploadFileName,
						'queueSizeLimit' :10 ,
						'simUploadLimit' : 1,
						'multi' : _isMulti,
						'onUploadSuccess' : function(file, data, response) {
							var jsonobj = eval('(' + data + ')');
							if (jsonobj.success === true) {
								urlVal = $("#" + jsonobj.msg.fid).attr("value");
								if(urlVal.length == 0){
									$("#" + jsonobj.msg.fid).attr("value", jsonobj.msg.url);
									$("#span_display_" + jsonobj.msg.fid).html(jsonobj.msg.filename);
								} else {
									$("#" + jsonobj.msg.fid).attr("value", urlVal + "," +  jsonobj.msg.url);
									$("#span_display_" + jsonobj.msg.fid).html($("#span_display_" + jsonobj.msg.fid).html() + "," + jsonobj.msg.filename);
								}
								
								if (typeof (funcallback) != "undefined") {
									funcallback(jsonobj);
								}
							} else {
								Dialog.showErrorMessage(jsonobj.msg);
							}
						}
					});

			$(".uploadify")
					.append(
							"<span id='span_display_"
									+ hid
									+ "' style='float: right;position: absolute;top: 10px;left: 130px;width: 200px;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;'></span>");
		},
		createVideoButton : function(fieldid, hid, _maxUploadSize_,
				funcallback, type, isMulti) {
			_isMulti = typeof(isMulti) != "undefined" ? isMulti : true;
			maxUploadSize = 1;

			// 判定两个参数是否给定
			if (typeof (_maxUploadSize_) != "undefined")
				maxUploadSize = _maxUploadSize_;

			if (maxUploadSize == null)
				maxUploadSize = 200;

			if (maxUploadSize > 200)
				maxUploadSize = 200;

			el = $("#" + fieldid);
			uploadFileName = el.attr("name");

			_type_ = "image";
			if (typeof (type) != "undefined")
				_type_ = type;

			// 创建按钮
			el.uploadify({
						'buttonClass' : 'uploadify_file',
						'buttonText' : '请选择文件',
						'fileSizeLimit' : maxUploadSize + 'MB',
						'fileTypeDesc' : '视频文件',
						'fileTypeExts' : '*.mepg; *.mpg; *.avi; *.wmv; *.flv; *.mp4',
						swf : '/script/uploadify/uploadify.swf',
						uploader : '/fileupload?fn=' + uploadFileName + "&fid="
								+ hid + "&strictsize=true&maxsize="
								+ maxUploadSize + "&type=" + _type_,
						'fileObjName' : uploadFileName,
						'simUploadLimit' : 1,
						'multi' : _isMulti,
						'onUploadSuccess' : function(file, data, response) {
							var jsonobj = eval('(' + data + ')');
							if (jsonobj.success === true) {
								urlVal = $("#" + jsonobj.msg.fid).attr("value");
								if(urlVal.length == 0){
									$("#" + jsonobj.msg.fid).attr("value", jsonobj.msg.url);
									$("#span_display_" + jsonobj.msg.fid).html(jsonobj.msg.filename);
								} else {
									$("#" + jsonobj.msg.fid).attr("value", urlVal + "," +  jsonobj.msg.url);
									$("#span_display_" + jsonobj.msg.fid).html($("#span_display_" + jsonobj.msg.fid).html() + "," + jsonobj.msg.filename);
								}
								
								if (typeof (funcallback) != "undefined") {
									funcallback(jsonobj);
								}
							} else {
								Dialog.showErrorMessage(jsonobj.msg);
							}
						}
					});

			$(".uploadify")
					.append(
							"<span id='span_display_"
									+ hid
									+ "' style='float: right;position: absolute;top: 10px;left: 130px;width: 200px;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;'></span>");
		},
		createExcelButton : function(fieldid, hid, _maxUploadSize_,
				funcallback, type, isMulti) {
			_isMulti = typeof(isMulti) != "undefined" ? isMulti : true;
			maxUploadSize = 1;

			// 判定两个参数是否给定
			if (typeof (_maxUploadSize_) != "undefined")
				maxUploadSize = _maxUploadSize_;

			if (maxUploadSize == null)
				maxUploadSize = 200;

			if (maxUploadSize > 200)
				maxUploadSize = 200;

			el = $("#" + fieldid);
			uploadFileName = el.attr("name");

			_type_ = "image";
			if (typeof (type) != "undefined")
				_type_ = type;

			// 创建按钮
			el.uploadify({
						'buttonClass' : 'uploadify_file',
						'buttonText' : '请选择文件',
						'fileSizeLimit' : maxUploadSize + 'MB',
						'fileTypeDesc' : 'Excel文件',
						'fileTypeExts' : '*.xls;*.xlsx',
						swf : '/script/uploadify/uploadify.swf',
						uploader : '/fileupload?fn=' + uploadFileName + "&fid="
								+ hid + "&strictsize=true&maxsize="
								+ maxUploadSize + "&type=" + _type_,
						'fileObjName' : uploadFileName,
						'simUploadLimit' : 1,
						'multi' : false,
						'onUploadSuccess' : function(file, data, response) {
							var jsonobj = eval('(' + data + ')');
							if (jsonobj.success === true) {
								urlVal = $("#" + jsonobj.msg.fid).attr("value");
								if(urlVal.length == 0){
									$("#" + jsonobj.msg.fid).attr("value", jsonobj.msg.locateUrl);
									$("#span_display_" + jsonobj.msg.fid).html(jsonobj.msg.filename);
								} else {
									$("#" + jsonobj.msg.fid).attr("value", urlVal + "," +  jsonobj.msg.locateUrl);
									$("#span_display_" + jsonobj.msg.fid).html($("#span_display_" + jsonobj.msg.fid).html() + "," + jsonobj.msg.filename);
								}
								
								if (typeof (funcallback) != "undefined") {
									funcallback(jsonobj);
								}
							} else {
								Dialog.showErrorMessage(jsonobj.msg);
							}
						}
					});

			$("#teachFiles")
					.append(
							"<span id='span_display_"
									+ hid
									+ "' style='width: 200px;height:100px;position:absolute;top:1px;left:130px;'></span>");
		}
	};
}();

/*******************************************************************************
 * * 用户位置定位 * 作者 胡晓光 * *
 ******************************************************************************/
var UserLocationSniffer = function() {
	return {
		init : function() {
			area = $.cookie("user_area_name");
			area_id = $.cookie("user_area_id");

			// 如果不存在用户的区域信息，则为用户默认地区
			if (area == null || area_id == null || area.length == 0 || area_id.length == 0) {
				Request.invok('/index/locateProvince', function(result) {
					if (result.success === true) {
						UserLocationSniffer.change(result.region_id, result.region_name);
					}
				});
			}
		},
		change : function(areaId, areaName){
			var expiresDate = new Date();
			expiresDate.setTime(expiresDate.getTime() + 60 * 60 * 1000 * 24
					* 365);
			$.cookie("user_area_id", areaId, {expires : expiresDate,path : '/'});
			$.cookie("user_area_name", areaName, {expires : expiresDate,path : '/'});
			if ($("#_area_")) {
				$("#_area_").html(areaName);
			}
		},
		changeArea : function(areaId, areaName) {
			this.change(areaId, areaName);
			
			// 判断当前是否处于按照地区展示的页面
			window.location.reload();
		}
	};
}();

/*******************************************************************************
 * * 请求类 * 作者 胡晓光 * *
 ******************************************************************************/
var Request = {
	/**
	 * 提交参数
	 * 
	 * @param sv
	 * @param params
	 *            js json对象
	 * @param _callback_
	 */
	post : function(sv, params, _callback_) {
		if (params != null) {
			_p_ = jQuery.param(params);
			jQuery.ajax({
				type : "post",
				cache : false,
				data : _p_,
				dataType : 'json',
				url : sv,
				success : function(result, textStatus) {
					if (typeof (_callback_) != "undefined") {
						_callback_(result);
					}
				}
			});
		} else {
			jQuery.ajax({
				type : "post",
				cache : false,
				dataType : 'json',
				url : sv,
				success : function(result, textStatus) {
					if (typeof (_callback_) != "undefined") {
						_callback_(result);
					}
				}
			});
		}
	},

	submit : function(_url_, _formid_, sucessCallback, toBeRefreshedAreaId, isShowLoading) {
		if (typeof(isShowLoading) != "undefine" && isShowLoading == true) {
			Dialog.showLoading("正在执行，请稍后 ...");
		}
		url = Utils.computeUrl(_url_);
		if (typeof (_formid_) == "string") {
			if (typeof (jQuery("#" + _formid_).validationEngine) != "undefined") {
				
				$('#' + _formid_).validationEngine({
					maxErrorsPerField:1
				});
				
				if (!jQuery("#" + _formid_).validationEngine("validate")) {
					Dialog.closeLoading();
					return;
				}
			}
			var _params_ = Utils.getFormJson(jQuery("#" + _formid_));
			_params_["_rd_url_"] = window.location.href;
			var params = _params_;
			jQuery.ajax({
				type : "post",
				cache : false,
				url : url,
				data : jQuery.param(params),
				dataType : "json",
				success : function(resultdata, textStatus) {
					if (toBeRefreshedAreaId
							&& typeof (toBeRefreshedAreaId) != "undefined"
							&& toBeRefreshedAreaId != null) {
						// 顶部显示提示信息
						if (resultdata.success
								&& typeof (resultdata.success) != "undefined"
								&& resultdata.success == true) {
							jQuery("#" + toBeRefreshedAreaId).html(
									resultdata.msg);
						}
					}
					if (sucessCallback) {
						sucessCallback(resultdata, textStatus);
					}
					
					if (typeof(isShowLoading) != "undefine" && isShowLoading == true) {
						Dialog.closeLoading();
					}
				}
			});
		} else if (typeof (_formid_) == "object") {
			jQuery.ajax({
				type : "post",
				cache : false,
				url : url,
				data : jQuery.param(_formid_),
				dataType : "json",
				success : function(resultdata, textStatus) {
					if (toBeRefreshedAreaId
							&& typeof (toBeRefreshedAreaId) != "undefined"
							&& toBeRefreshedAreaId != null) {
						// 顶部显示提示信息
						if (resultdata.success
								&& typeof (resultdata.success) != "undefined"
								&& resultdata.success == true) {
							jQuery("#" + toBeRefreshedAreaId).html(
									resultdata.msg);
						}
					}
					if (sucessCallback) {
						sucessCallback(resultdata, textStatus);
					}
					
					if (typeof(isShowLoading) != "undefine" && isShowLoading == true) {
						Dialog.closeLoading();
					}
				}
			});
		}
	},
	invok : function(_url_, sucessCallback) {
		jQuery.ajax({
			type : "post",
			cache : false,
			dataType : 'json',
			url : _url_,
			success : function(resultdata, textStatus) {
				if (sucessCallback) {
					sucessCallback(resultdata, textStatus);
				}
			}
		});
	},
	// 本函数用于获取后台反馈的数据，并以json格式返回
	request : function(_url_, sucessCallback) {
		jQuery.ajax({
			type : "post",
			cache : false,
			dataType : 'json',
			url : _url_,
			success : function(resultdata, textStatus) {
				if (resultdata && resultdata.success === true) {
					if (sucessCallback) {
						sucessCallback(resultdata, textStatus);
					}
				} else {
					return null;
				}
			}
		});
	},
	fill : function(url, areaId, isShowLoading, func) {
		_isShowLoading_ = typeof (isShowLoading) == "undefined"
				|| isShowLoading == null ? true : isShowLoading;
		if (_isShowLoading_)
			Dialog.showLoading("正在加载，请稍后 ...");

		var random = Utils.generateRandomCode();
		if (url.indexOf("?") > 0) {
			url += "&v=" + random;
		} else {
			url += "?v=" + random;
		}

		$.get(url, function(result) {
			try {
				
				if (areaId.charAt(0) != '#' && areaId.charAt(0) != '.') {
					$("#" + areaId).html(result);
				} else {
					$(areaId).html(result);
				}
				
				if (typeof(func) != "undefined")
					func();
				
			} catch (exception) {
				console.log(exception);
			}

			Dialog.closeLoading();
		});
	},

	fillWithParams : function(url, params, areaId, isShowLoading, func) {
		_isShowLoading_ = typeof (isShowLoading) == "undefined"
				|| isShowLoading == null ? true : isShowLoading;
		if (_isShowLoading_)
			Dialog.showLoading("正在加载，请稍后 ...");

		var random = Utils.generateRandomCode();
		if (url.indexOf("?") > 0) {
			url += "&v=" + random;
		} else {
			url += "?v=" + random;
		}

		jQuery.ajax({
			type : "post",
			cache : false,
			url : url,
			data : jQuery.param(params),
			dataType : "html",
			success : function(result) {
				try {
					if (areaId.charAt(0) != '#' && areaId.charAt(0) != '.') {
						$("#" + areaId).html(result);
					} else {
						$(areaId).html(result);
					}

					if (typeof (func) != "undefined") {
						func(result);
					}
				} catch (exception) {
					console.log(exception);
				}

				Dialog.closeLoading();
			}
		});
	},

	/**
	 * ajax方式上传文件
	 * 
	 * @returns {Boolean}
	 */
	ajaxFileUpload : function(_url_, _formid_, successFn, errorFn) {
		// 判断当前Form中是否存在文件
		var inputs = $("#" + _formid_ + " input[type=file]");
		var fid = "";
		if (inputs && inputs.length > 0) {
			// 存在文件
			for (var i = 0; i < inputs.length; i++) {
				if (i == 0)
					fid += inputs[i].id;
				else
					fid += "," + inputs[i].id;
			}
		}
		var _params_ = Utils.getFormJson(jQuery("#" + _formid_));
		var params = _params_;
		jQuery.ajaxFileUpload({
			url : _url_,// 用于文件上传的服务器端请求地址
			secureuri : false,// 一般设置为false 这个为空ajaxfileupload中的iframe不显示
			fileElementId : fid,// 文件上传空间的id属性 <input type="file" id="file"
			// name="file" />
			dataType : 'json',// 返回值类型 一般设置为json
			data : params,
			success : function(data, status) {
				if (data.success && typeof (data.success) != "undefined"
						&& data.success == true) {
				}

				if (successFn) {
					successFn(data, status);
				}
			},
			error : function(data, status, e) {
				if (errorFn) {
					errorFn(data, status, e);
				}
			}
		});
		return false;
	}
};

var Buttons = function() {
	return {
		bindButtons : function(buttons) {
			tp = typeof (buttons);
			if (tp == "string") {
				cmd = "Buttons." + buttons;
				if (typeof (eval(cmd)) == "function") {
					fn = eval(cmd + "()");
					fn;
				}
			} else if (tp == "object") {
				if (buttons instanceof Array) {
					for ( var s in buttons) {
						cmd = "Buttons." + buttons[s];
						if (typeof (eval(cmd)) == "function") {
							fn = eval(cmd + "()");
							fn;
						}
					}
				}
			}

		},

		/**
		 * 展开已选择商品事件
		 */
		show_or_hide_selected_goods : function() {
			// 展开已选择商品
			$(".show_or_hide_selected_goods:not(.fnbtn-inited)").click(
					function() {
						if ($(this).hasClass("on")) {
							$(this).removeClass("on");
							$("#seled-items").hide();
						} else {
							$(this).addClass("on");
							$("#seled-items").show();
						}
					}).addClass("fnbtn-inited");
		},

		// 购买商品按钮
		buy_goods : function() {
			$(".buy_goods:not(.fnbtn-inited)").click(function() {
				var gid = $(this).attr("data-value");
				var category = $(this).attr("data-category");
				var count = $(this).attr('data-count');
				var isGotoCart = typeof($(this).attr("data-redirect-cart")) != "undefined";
				ShoppingCart.add2Cart(gid, category, count, isGotoCart);
				return false;
			}).addClass("fnbtn-inited");
		},

		// mini注册框
		regist_mini_dialog : function() {
			$(".regist_mini_dialog:not(.fnbtn-inited)").click(function() {
				var hml = '<iframe frameborder="0" id="'
					+ Utils.generateRandomCode()
					+ '" name="_popu_frmContent_" scrolling="yes" src="/regist/regist_fast" style="width:' + 476 + 'px;height:' + 315
					+ 'px" ></iframe>';
				dialog({
					fixed : true,
					title : '快速注册',
					lock : true,
					backdropOpacity : 0.3,
					skin:'pop_login',
					content : hml
				}).showModal();
				return false;
			}).addClass("fnbtn-inited");
		},
		
		// mini找回密码框
		find_pwd_dialog : function() {
			$(".find_pwd_dialog:not(.fnbtn-inited)").click(function() {
				var hml = '<iframe frameborder="0" id="'
					+ Utils.generateRandomCode()
					+ '" name="_popu_frmContent_" scrolling="yes" src="/forgetpwd/mini" style="width:' + 476 + 'px;height:' + 220
					+ 'px" ></iframe>';
				dialog({
					fixed : true,
					title : '找回密码',
					lock : true,
					backdropOpacity : 0.3,
					skin:'pop_login',
					content : hml
				}).showModal();
				return false;
			}).addClass("fnbtn-inited");
		},
		
		/**
		 * 添加到收藏夹
		 */
		add2Store : function() {
			stores = $(".add2Store:not(.fnbtn-inited)");
			params = {};
			stores.click(function() {
				ts = $(this);
				params["o"] = ts.attr("data-id");
				params["c"] = ts.attr("data-category");
				Utils.doAfterLogin(function() {
					Request.post('/store/add', params, function() {
						ts.html("已收藏");
						Dialog.showMessage("已添加至收藏夹");
					});
				});
				return false;
			}).addClass("fnbtn-inited");

			if (stores.length > 0) {
				storebtns = new Array();
				stores.each(function(index, element) {
					storebtns.push($(element).attr("data-id"));
				});
				params = {};
				params["stores"] = storebtns.toString();
				Request.post('/index/isStored', params, function(result) {
					if (result != null && result.msg != null) {
						stored = result.msg;
						if (stored.length > 0) {
							$.each(stored,
									function(idx, el) {
										$(".add2Store[data-id=" + el + "]")
												.html("已收藏");
										$(".add2Store[data-id=" + el + "]")
												.unbind();

									});
						}
					}
				});
			}
		},

		/**
		 * 将网站添加到收藏夹
		 */
		addWebSit2Store : function() {
			$(".addWebSit2Store:not(.fnbtn-inited)").click(
					function() {
						var ctrl = (navigator.userAgent.toLowerCase())
								.indexOf('mac') != -1 ? 'Command/Cmd' : 'CTRL';
						if (document.all) {
							window.external
									.addFavorite(_SITE_URL_, _SITE_NAME_);
						} else if (window.sidebar) {
							window.sidebar
									.addPanel(_SITE_NAME_, _SITE_URL_, "");
						} else {
							alert('您可以通过快捷键' + ctrl + ' + D 加入到收藏夹');
						}
						return false;
					}).addClass("fnbtn-inited");

		},

		/**
		 * 退出登录
		 */
		btnLogout : function() {
			$(".btnLogout:not(.fnbtn-inited)").click(function() {
				Dialog.showConfirmDialog("确定要退出系统吗？", function() {
					Request.post('/login/logout', null, function(result) {
						var expiresDate = new Date();
						expiresDate.setTime(expiresDate.getTime() - 10000);
						$.cookie(result.asdfsd, result.zqccc, {
							expires : expiresDate,
							path : '/'
						});
						$.cookie("_topacc_user_", "", {
							expires : expiresDate,
							path : '/'
						});
						window.location.assign(result.url);
					});
				});
				return false;
			}).addClass("fnbtn-inited");
		},

		/**
		 * 登录
		 */
		btnLogin : function() {
			$(".btnLogin:not(.fnbtn-inited)").click(
					function() {
						act = $("#login-form").attr("action");
						Request.submit(act, 'login-form', function(result,
								textStatus) {
							if (result.success === true) {
								var expiresDate = new Date();
								expiresDate.setTime(expiresDate.getTime() + 60
										* 60 * 1000 * 24 * 365);
								returnval = Utils.string2Json(result.data);
								$.cookie(returnval.asdfsd, returnval.zqccc, {
									expires : expiresDate,
									path : '/'
								});
								$.cookie("_topacc_user_", Utils
										.json2String(returnval.u), {
									expires : expiresDate,
									path : '/'
								});
								window.top.location.assign(returnval.rdp);
							} else {
								Utils.parseResultError(result);
							}
						});
					}).addClass("fnbtn-inited");

			$("#login-form").keypress(function(e) {
				if (e.which == 13) {
					$(".btnLogin").click();
				}
			});
		},

		/**
		 * 向下翻页按钮事件
		 */
		btnPageDown : function(container) {
			var unbindPageDown = $('#btnPageDown:not(.fnbtn-inited)');
			$.each(unbindPageDown, function(index, btn) {
				DownPageSplit.createPageSplit($(btn).attr("id"));
			});
			unbindPageDown.addClass("fnbtn-inited");

			// $('#btnPageDown:not(.fnbtn-inited)').click(function () {
			// url = $(this).attr("href");
			// container = $(this).attr("target");
			// if (_current_page_ == 0) {
			// $.get(url, function(result) {
			// $("#" + container).html(result);
			// ++ _current_page_;
			// });
			// } else {
			// if (_total_page_ <= _current_page_){
			// Dialog.showMessage('已到达最后');
			// return false;
			// }
			// currentStart = _current_page_ * _page_size_;
			// _current_page_ = _current_page_ + 1;
			// $.get(url + "&start=" + currentStart, function(result) {
			// $("#" + container).append(result);
			// if (_current_page_ >= _total_page_) {
			// $("#btnPageDown").hide();
			// }
			// });
			// }
			// return false;
			// }).addClass("fnbtn-inited");
		},

		btnLink : function() {
			$(".btnLinkAjax:not(.fnbtn-inited)").click(function() {
				url = $(this).attr("href");
				callback = $(this).attr("data-callback");
				Request.invok(url, function(resultdata) {
					try {
						if (typeof (callback) == "function") {
							callback();
						} else {
							if (resultdata.success === false) {
								Dialog.showErrorMessage("操作失败");
							} else {
								Dialog.showMessage("操作成功");
							}
						}
					} catch (e) {

					}
				});
			}).addClass("fnbtn-inited");
			return false;
		},

		/**
		 * 刷新验证码
		 */
		authcode_reload : function() {
			$(".authcode_reload:not(.fnbtn-inited)").click(
					function() {
						var verify = document.getElementById('img_data');
						verify.setAttribute('src', '/SCS?v='
								+ Utils.generateRandomCode());
						return false;
					});
		}
	};
}();

var TopBar = function() {
	return {
		init : function() {
			if (user != null) {
				$(".top-le-enter").children(":last").show().children(":first").append(user.nickname);
				$(".top-right .user-center>span>a").attr("href", "/ucenter");
				$(".top-right .user-center").mouseover(function() {
					if ($(".top-right .user-center>div").is(":hidden")) {
						$(".top-right .user-center>div").show();
					}
				}).mouseout(function() {
					$(".top-right .user-center>div").hide();
				});
			} else {
				$(".top-le-enter").children(":first").show();
				$(".top-right .user-center>span>a").attr("href", "/login");
			}

			$('.top-right li[class=custom-service]').hover(function() {
				$(this).children('div').toggle();
			});

			// 初始化顶部购物车
			if ($(".top-cen .top-cart").length > 0) {
				$('.top-cen > li ').hover(function() {
					$(this).children('.cart-list').toggle();
				});
				TopBar.loadShoppingCart();
			}
		},

		/**
		 * 读取购物车信息
		 */
		loadShoppingCart : function() {
			// 初始化顶部购物车
			if ($(".top-cen .top-cart").length > 0) {
					Request.invok("/shopping/topcart",
						function(result) {
							if (result.success === true) {
								var output = Mustache.render('{{#msg}}<li  class="clearfix"><a href="/shopping/cart" title="{{CGoodsName}}">{{CGoodsName}}</a><strong style="width:26px;"><span class="remove-item-bar" data-value="{{CId}}">删除</span></strong><span>￥<em>{{formtTotalPrice}}</em></span></li>{{/msg}}', result);
								$(".cart-items-list").html(output);
								$(".top-cart>span>em").html( result.msg.length);
								if (result.msg.length == 0)
									$(".cart-items-list").next().html("购物车还是空的哦^_^");
								else
									$(".cart-items-list").next().html("购物车还有" + result.msg.length + "件宝贝");
								
								$(".top-cart>span>a").html("购物车(<em>" + result.msg.length + "</em>)");
								$(".remove-item-bar").click(function() {
										var t = $(this);
										Dialog.showConfirmDialog("确定要移除该商品吗？",function() {
															ShoppingCart.removeItem("shopping",t.attr("data-value"),t);
															Dialog.showMessage("商品已移除");
										});
								});
							}
						});
			}
		}
	};
}();

var IndexTop = function() {
	return {
		init : function() {
			// 地区切换下拉
			$(".Spand").hover(function() {
				$('.Spandiv').show();
			}, function() {
				$('.Spandiv').hide();
			});
			$(".Spandiv span").click(
					function() {
						UserLocationSniffer.changeArea($(this).attr("data-value"), $(this).html());
						$("#sel-area").html($(this).html());
					});
			
			if ($(".Spandiv").length > 0) {
				var uan = $.cookie("user_area_name");
				$("#sel-area").html(uan);
			}

			// 导航菜单选中状态
			ctx = Utils.getContextPath();
			if (ctx == null || ctx.length == 0 || "index" == ctx) {
				$(".ind-nav>ul>li:first-child").addClass("on");
			} else {
				$(".menu-" + ctx).parent().addClass("on");
			}

			// 鼠标移入搜索框出现下拉
			$('.hd-select').click(function() {
				$(this).children('ul').toggle();
			});
			$('.hd-select li').click(
					function() {
						var text = $(this).text();
						$(this).parent().siblings('span').text(text);
						$("#index_form").attr("action",
								"/" + $(this).attr("data-type"));
					});

		}
	};
}();

var ShoppingCart = function() {
	return {
		/**
		 * 将商品添加到购物车
		 */
		add2Cart : function(gid, category, count , isGotoCart) {
			_count_param_ = ""
			if (typeof (count) != "undefined") {
				_count_param_ = "&count=" + count;
			}
			Request.invok("/shopping/add_goods?gid=" + gid + "&category="
					+ category + _count_param_, function(data) {
				if (data.success === true) {
//					if (typeof(isGotoCart) != "undefined" && isGotoCart == true) {
//						window.location.assign("/shopping/cart");
//					} else {
//						Dialog.showMessage("已添加至购物车");
//						setTimeout(function() {
//							window.location.reload(true);
//						}, 2000);
//					}
					window.location.assign("/shopping/cart");
				} else {
					Dialog.showErrorMessage(data.msg);
				}
			});
		},

		/**
		 * 删除购物车中的一项
		 */
		removeItem : function(prefix, gid, t) {
			ShoppingCart.refreshCart();
			context = Utils.getContextPath();
			
			if (context != "order" && context != "shopping") {
				context = "shopping";
			}
			Request.invok("/" + context + "/item_remove?gid="
					+ gid, function() {
				act = Utils.getAction();
				ctx = Utils.getContextPath();
				
				if (typeof (ctx) != "undefined" && act != "undefined") {
					if (ctx == 'shopping') {
						setTimeout(function() {
							window.location.assign("/shopping/cart");
						}, 1000);
					} else {
						// 重新刷新购物车
						TopBar.loadShoppingCart();
					}
				} else {
					TopBar.loadShoppingCart();
				}
			});
		},
		/**
		 * 刷新购物车
		 */
		refreshCart : function() {
			if ($(".tb-course tr").length == 1) {
				$(".tb-course").hide();
			}
			if ($(".tb-book tr").length == 1) {
				$(".tb-book").hide();
			}
			if ($(".tb-course").is(":visible") === false
					&& $(".tb-book").is(":visible") === false) {
				$(".no-cart").show();
			}
		},

		/**
		 * 绑定商品功能
		 */
		initShoppingPage : function() {
			Buttons.bindButtons("buy_goods");
		},

		/**
		 * 初始化结算页面
		 */
		initCheckoutPage : function() {
			// 初始化收货人
			ShoppingCart.initReceiver();

			// 初始化优惠码
			ShoppingCart.initCoupon();

			// 送货清单————查看全部 - 查看全部图书
			$('.item3-con-ri li:gt(3)').toggle();
			var delibool = true;
			$('.item3-con-ri span').click(function() {
				$('.item3-con-ri li:gt(3)').toggle();
				$(this).toggleClass('on');
				if (delibool) {
					$(this).text('收起更多');
					delibool = false;
				} else {
					$(this).text('查看全部');
					delibool = true;
				}
			});

			// 提交订单按钮
			$("#submit-order")
					.click(
							function() {
								sd = "";
								sc = "";
								nr = false;

								// 检查优惠券
								cp = $(".coupons-con.on.usable").find(
										"i[class=on]");
								if (cp.length > 0) {
									sc = $(cp[0]).attr("data-value");
								}
								// MAIN :: 检查各项
								// 检查上课人信息是否都填写了
								gs = $("input[id^='g_']");
								$.each(gs, function(index, element) {
									if ($(element).attr("value").length == 0) {
										$(this).addClass("border-bold");
										return;
									} else {
										$(this).removeClass("border-bold");
									}
								});

								// 检查手机号是否正确
								if ($(".mobile").length > 0) {
									var mobile = $(".imobile").val();
									if (/^13\d{9}$/g.test(mobile)
											|| (/^15[8,9]\d{8}$/g.test(mobile))) {
										return true;
									} else {
										alert("请输入正确的手机号码！");
										return false;
									}
								}

								// 检查收货人信息是否填写
								dr = $(".delivery-receiver")
								dro = $(".delivery-receiver.on");

								// 如果购买了图书，且未选择收货人，则此处进行提示
								if ($("#receivers").length > 0 && dro.length == 0 ) {
									$("#receivers").addClass("border-bold");
									Utils.gotoAnchor("receivers");
									Dialog.showErrorMessage("请选择收货地址");
									return false;
								} else {
									if (dr.length > 0 && dro.length == 0) {
										$("#receivers").addClass("border-bold");
										Utils.gotoAnchor("receivers");
										Dialog.showErrorMessage("请选择收货地址");
										return;
									} else if (dr.length > 0 && dro.length == 1) {
										sd = $(dro[0]).attr("data-value");
										nr = true;
										$("#receivers").removeClass("border-bold");
									} else {
										$("#receivers").removeClass("border-bold");
									}
								}
								
								$("#sd").attr("value", sd);
								$("#sc").attr("value", sc);
								$("#nr").attr("value", nr);
								Request
										.submit(
												"/shopping/order_make",
												"form-order",
												function(resultdata) {
													if (resultdata.success == false) {
														$(
																"#"
																		+ resultdata.field)
																.addClass(
																		"border-bold");
														Dialog
																.showErrorMessage(resultdata.msg);
													} else {
														data = Utils
																.string2Json(resultdata.data);
														window.location
																.assign("/payment?o="
																		+ data.o);
													}
												});
							});

			if (typeof (gotocheck) != "undefined" && gotocheck === "true") {
				$("#submit-order").click();
			}
		},

		/**
		 * 初始化优惠码操作
		 */
		initCoupon : function() {
			// 使用优惠券tab切换
			$('.coupons-tit span').click(
					function() {
						$(this).addClass('on').siblings().removeClass('on');
						$('.coupons-box .coupons-con').eq($(this).index())
								.addClass('on').siblings().removeClass('on');
					});

			// 选择优惠券
			$('.coupons-con.usable li').click(function() {
				$(this).siblings().children('i').removeClass("on");
				$(this).children('i').toggleClass('on');
			});

			// 优惠码充值
			$(".coupon-charge-btn").click(
					function() {
						var coupon_no = $("#coupon_no").attr("value");
						if (coupon_no.length < 5 || coupon_no.length > 10) {
							Utils.errorInput("#coupon_no", "优惠码无效");
							return;
						}
						Request.submit("/coupon/coupon_charge",
								"coupon-charge-form", function(resultdata) {
									if (resultdata.success == false) {
										Dialog.showErroMessage(resultdata.msg);
									} else {
										Request.fill(
												'/order/fragment_coupon_list?o='
														+ _oi_, 'coupons-div');
									}
								});
					});
		},

		/**
		 * 初始化收货人
		 */
		initReceiver : function() {
			// 选择收货人，让选择框变绿
			$('.fill-order .item2 dd>span').click(function() {
				ts = $(this).parent();
				var dataValue = ts.attr("data-value");
				if (dataValue) {
					ts.siblings().removeClass("on");
				}
				ts.toggleClass('on');
			});
			// 点击更多地址
			$('.more-address').click(function() {
				$('.item2 dd:not(":first,:last")').toggle();
				$(this).toggleClass('pack-up');
				if (adbool) {
					$(this).text('收起地址');
					adbool = false;
				} else {
					$(this).text('更多地址');
					adbool = true;
				}
			});
			$('.fill-order .item2 ul li').click(function() {
				$(this).addClass('on');
			});

			// 弹出新增收货地址
			$('.fill-order dt span,.fill-order .item2 dd li.editor').click(
					function() {
						var gid = $(this).attr("data-value");
						Request.fill('/shopping/receiver_edit?id=' + gid,
								'reciever-detail');
						$('.address-mask').show();
					});
			$('.address-mask h2 em').click(function() {
				$("#reciever-detail").html("");
				$('.address-mask').hide();
			});

			// 删除一个收货人
			$(".remove-receiver").click(
					function() {
						if (window.confirm("确定要删除常用地址吗？")) {
							var gid = $(this).attr("data-value");
							Request.invok(
									"/ucenter/devlivery_delete?id=" + gid,
									function(result) {
										Request.fill("/shopping/receiver_list",
												"receivers");
									});
						}
					});

			// 设置一个收货人为默认
			$(".setdefault-receiver").click(
					function() {
						var gid = $(this).attr("data-value");
						Request.invok(
								"/ucenter/devlivery_setdefault?id=" + gid,
								function() {
									Dialog.showMessage("已设为默认收货人");
									Request.fill("/shopping/receiver_list",
											"receivers");
								});
					});
		}
	};
}();

/**
 * 显示更多分页按钮
 */
var DownPageSplit = function() {
	return {
		createPageSplit : function(pageid) {
			if (typeof (this.pages) == "undefined") {
				this.pages = {};
			}

			DownPage = {};
			DownPage["pageid"] = pageid;
			DownPage["url"] = $("#" + pageid).attr("href");
			DownPage["target"] = $("#" + pageid).attr("target");
			DownPage["current_page"] = 0;
			DownPage["start"] = 0;

			PageParameter = {};
			DownPage["parameter"] = PageParameter;

			this.pages[pageid] = DownPage;

			$("#" + pageid).click(function() {
				thisid = $(this).attr("id");
				dp = DownPageSplit.pages[thisid];
				if (dp != "undefined") {
					DownPageSplit.pageDown(dp);
				}
				return false;
			});
		},

		pageDown : function(dp) {
			if (typeof (_total_page_) != "undefined") {
				if (_total_page_ <= dp.current_page) {
					Dialog.showMessage('已到达最后');
					$("#" + dp.pageid).hide();
					return false;
				}
			}

			// 附带分页
			var o = {};
			if (dp.parameter != null) {
				// 对每一个参数进行双编码
				for ( var p in dp.parameter) {
					o[p] = dp.parameter[p] || '';
				}
			}
			o["start"] = dp.start;

			Dialog.showLoading("正在加载，请稍后 ...");
			jQuery.ajax({
				type : "post",
				cache : false,
				url : dp.url,
				data : jQuery.param(o),
				dataType : "html",
				success : function(result) {
					try {
						$("#" + dp.target).append(result);
						dp.current_page = dp.current_page + 1;
						currentStart = dp.current_page * _page_size_;
						dp.start = currentStart;
						if (_total_page_ <= dp.current_page) {
							$("#" + dp.pageid).hide();
						}
					} catch (exception) {
						console.log(exception);
					}

					Dialog.closeLoading();
				}
			});
		},
		clearParameter : function(pageid) {
			dp = DownPageSplit.pages[pageid];
			if (dp != "undefined") {
				dp.parameter = {};
			}
			return DownPageSplit;
		},
		setParameter : function(pageid, key, value) {
			dp = DownPageSplit.pages[pageid];
			if (dp != "undefined") {
				if (dp.parameter != null) {
					dp.parameter[key] = value;
				}
			}
			return DownPageSplit;
		},
		setParameters : function(pageid, params) {
			dp = DownPageSplit.pages[pageid];
			if (dp != "undefined") {
				if (dp.parameter != null) {
					for ( var p in params) {
						dp.parameter[p] = params[p] || '';
					}
				}
			}
			return DownPageSplit;
		},

		refresh : function(pageid) {
			dp = DownPageSplit.pages[pageid];
			window._total_page_ = null;
			window._page_size_ = null;
			if (dp != "undefined") {
				dp.start = 0;
				dp.current_page = 0;
				dp.parameter["start"] = dp.current_page;
				Request.fillWithParams(dp.url, dp.parameter, dp.target, true,
						function() {
							try {
								dp.current_page = dp.current_page + 1;
								currentStart = dp.current_page * _page_size_;
								dp.start = currentStart;
								if (_total_page_ <= dp.current_page) {
									$("#" + dp.pageid).hide();
								} else {
									$("#" + dp.pageid).show();
								}
							} catch (exception) {
								console.log(exception);
							}
						});
			}
		}
	};
}();



/*******************************************************************************
 * * 日期格式化 * 作者 胡晓光 * *
 ******************************************************************************/
var BaiduSiteStatics = function() {
	return {
		init : function(dateobj, pattern) {
			var _hmt = _hmt || [];
			(function() {
			  var hm = document.createElement("script");
			  hm.src = "//hm.baidu.com/hm.js?a451d2ff3899d85cd516c5630082719a";
			  var s = document.getElementsByTagName("script")[0];
			  s.parentNode.insertBefore(hm, s);
			})();
		}
	};
}();

// 对用户进行处理
var user = null;
var _current_page_ = 0;
try {
	user = Utils.string2Json($.cookie("_topacc_user_"));
} catch (e) {
}

 
$(document).ready(function() {
	// 检测浏览器版本
	var ie6 = !-[ 1, ] && !window.XMLHttpRequest;
	if (ie6 === true) {
		window.location.assign("/index/browser");
	}

	// 回到顶部
	$('.to-top,.side-nav .side-top').click(function() {
		$('html,body').animate({
			scrollTop : '0'
		}, 300);
	});
	$(window).scroll(function() {
		var scrollTop = $(window).scrollTop();
		if (scrollTop > 200) {
			$('.to-top').fadeIn();
		} else {
			$('.to-top').fadeOut();
		}
	});

	// 处理当前登录用户
	if (typeof ($.cookie("_topacc_user_")) != "undefined") {
		$.ajax({
			type : "post",
			cache : false,
			url : '/login/islogin',
			dataType : "json",
			success : function(resultdata, textStatus) {
				if (resultdata.success === false) {
					if (typeof ($.cookie("_topacc_user_")) != "undefined") {
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
			}
		});
	}

	// 错误提示增加点击消失功能
	$(".formError").click(function() {
		$(this).hide();
	});

	// 加载必要的js
	var context = Utils.getContextPath();
	if (context == null || context.length == 0 || "index" == context) {
		context = "index";
	}

	var action = Utils.getAction();
	if (action == null || action.length == 0)
		action = "index";

	UserLocationSniffer.init();
	BaiduSiteStatics.init();
	
	// 对页面上控制页面按钮的处理
	fnbtns = $(".fnbtn");
	if (fnbtns.length > 0) {
		$.each(fnbtns, function(index, ele) {
			allclass = $(ele).attr("class");
			classarray = allclass.split(" ");
			if (classarray.length > 0) {
				$.each(classarray, function(index, el) {
					fnname = "Buttons." + el;
					try {
						fn = eval(fnname);
						if (typeof (fn) == "function") {
							fn();
						}
					} catch (e) {

					}
				});
			}
		});
	}

	$.ajax({
		url : "/script/topacc/topacc." + context + ".js",
		dataType : "script",
		cache : true
	}).done(function(data) {
		try{
			if (data.lengt > 0)
				eval(data);
		} catch(exception){
			
		}
		
		fnname = context + "." + action;
		isError = true;
		try {
			fn = eval(fnname);
			if (typeof (fn) == "function") {
				isError = false;
				fn();
			}
		} catch (e) {
		}

		if (isError == true) {
			fname = context + ".getfunc";
			try {
				fn2 = eval(context + ".getfunc");
				if (typeof (fn2) == "function") {
					handl = fn2();
					if (handl != null)
						handl();
				}
			} catch (e) {
				// console.error(e);
			}
		}
	}).fail(function(jqXHR, textStatus) {
	});
});
