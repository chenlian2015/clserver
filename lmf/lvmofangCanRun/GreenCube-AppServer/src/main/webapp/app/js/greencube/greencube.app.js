// 全局变量
var g_site_url = 'http://localhost:8080'; 
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
String.prototype.isEmpty = function() {
	if (typeof(this) == "undefined")
		return true;
	if (this.length == "")
		return true;
	return false;
};

String.prototype.toJson = function() {
	return JSON.parse(this);
};

/*******************************************************************************
 * * TabListView组件 * 作者 胡晓光 * *
 ******************************************************************************/
var TabListView = function( tabid, url) {
	return {
		loading: false,
		pageId : '',
		tabs : new Array(),
		init : function(pageId) {
			this.pageId = pageId;
			return this;
		},
		create : function(tabid, url, callback) {
			ts = {};
			ts.url = url;
			ts.tabid = tabid;
			ts.pageNum = 1;
			ts.fn = callback;
			this.tabs.push(ts);
			return this;
		},
		loadData : function(tabid){
			if (tabid) {
				for (s in TabListView.tabs){
					
					obj = TabListView.tabs[s];
					if (obj.tabid == tabid) {
						// 刷新页面
						_url_ = obj.url.indexOf("?") > 0 ? obj.url + "&start=" + obj.pageNum : obj.url + "?start=" + obj.pageNum; 
		    			Request.invok(_url_, function(result){
		    				TabListView.loading = false;
		    				if (result.success == true) {
		    					if (result.page.showRows > 0 ){
		    						obj.pageNum ++;
		    						
		        					outhtml = obj.fn ? obj.fn(result) : '';
		        					$("#" + tabid).find('.list-container').append(outhtml);
		        					$.refreshScroller();
		    					} else {
		    						$("#" + tabid).find(".infinite-scroll-preloader").hide();
		    						$('#' + tabid + ' .infinite-scroll-preloader').hide();
		    						$.detachInfiniteScroll($("#" + tabid));
		    						TabListView.loading = false;
		    					}
		    				}
		    			});
						
					}
				}
			} else {
				// 当前是哪个标签
				var currentTabId = $(".tabs").find('.infinite-scroll.active').attr('id');
				for (s in TabListView.tabs){
					obj = TabListView.tabs[s];
					if (obj.tabid == currentTabId) {
						// 刷新页面
						_url_ = obj.url.indexOf("?") > 0 ? obj.url + "&start=" + obj.pageNum : obj.url + "?start=" + obj.pageNum; 
		    			Request.invok(_url_, function(result){
		    				TabListView.loading = false;
		    				if (result.success == true) {
		    					if (result.page.showRows > 0 ){
		    						obj.pageNum ++;
		    						
		        					outhtml = obj.fn ? obj.fn(result) : '';
		        					$('.infinite-scroll.active .list-container').append(outhtml);
		        					$.refreshScroller();
		    					} else {
		    						$("#" + obj.tabid).find(".infinite-scroll-preloader").hide();
		    						$('#' + obj.tabid + ' .infinite-scroll-preloader').hide();
		    						$.detachInfiniteScroll($("#" + obj.tabid));
		    						TabListView.loading = false;
		    					}
		    				}
		    			});
						
					}
				}
			}
		},
		
		bind : function(){
			TabListView.loadData();
			$(document).on("pageInit", "#" + this.pageId , function(e, id, page){
	    		$(page).on('infinite', function() {
	    			if (TabListView.loading) return;
	    			TabListView.loading = true;
	    			TabListView.loadData();
	    		});
	    	});
		}
	};
}();

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
 * * 日期格式化 * 作者 胡晓光 * *
 ******************************************************************************/
var Router = function() {
	return {
		go : function(url) {
			window.location.assign(url);
		}
	};
}();
/*******************************************************************************
 * * 工具类 * 作者 胡晓光 * *
 ******************************************************************************/
var Utils = function() {
	return {
		getToken : function(){
			return this.getQueryString("token");
		},
		/**
		 * 生成一个链接
		 */
		computeUrl : function(url) {
			return url;
		},
		
		isWeiXin : function () {
			var ua = navigator.userAgent.toLowerCase();
			if(ua.match(/MicroMessenger/i)=="micromessenger") {
				return true;
		 	} else {
				return false;
			}
		},
		
		doAfterLogin:function( _func_){
			// 发送同步请求
			$.ajax({
				type : "post",
				cache : false,
				async : true,
				url : '/login/islogin',
				dataType : "json",
				success : function(resultdata, textStatus) {
					if (resultdata.success == true) {
						if (typeof(_func_) != "undefined"){
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
						window.location.assign("/login")
					}
				}
			});
		},
		
		/**
		 * 取得form中所有input元素的值，并组合成json对象
		 */
		getFormJson : function(frm) {
			var o = {};
			var a = $(frm).serializeArray();
			$.each(a, function() {
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
			_p_ = $.param(params);
			$.ajax({
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
			Dialog.showDialog("/qrcode","微信扫码");
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
				funcallback, type) {
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
			if (typeof(type) != "undefined") 
				_type_= type;
			
			// 创建按钮
			el
					.uploadify({
						'buttonClass' : 'uploadify_file',
						'buttonText' : '请选择文件',
						'fileSizeLimit' : maxUploadSize + 'MB',
						'fileTypeDesc' : '视频文件',
						'fileTypeExts' : '*.mepg; *.mpg; *.avi; *.wmv; *.flv; *.mp4;',
						swf : '/script/uploadify/uploadify.swf',
						uploader : '/fileupload?fn='
								+ uploadFileName + "&fid=" + hid
								+ "&strictsize=true&maxsize=" + maxUploadSize + "&type=" + _type_,
						'fileObjName' : uploadFileName,
						'simUploadLimit' : 1,
						'multi' : true,
						'onUploadSuccess' : function(file, data, response) {
							var jsonobj = eval('(' + data + ')');
							if (jsonobj.success === true) {
								$("#" + jsonobj.msg.fid).attr("value",
										jsonobj.msg.url);
								$("#span_display_" + jsonobj.msg.fid).html(
										jsonobj.msg.filename);
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
									+ "' style='float: right;position: absolute;top: 10px;left: 130px;'></span>");
		}
	};
}();

/*******************************************************************************
 * * 用户位置 * 作者 胡晓光 * *
 ******************************************************************************/
var UserLocationSniffer = function() {
	return {
		init : function() {
//			area = $.cookie("user_area_name");
//			area_id = $.cookie("user_area_id");
//
//			// 如果不存在用户的区域信息，则为用户默认地区
//			if (area == null || area_id == null) {
//				Request.invok('/wbs/locateProvince',
//						function(result) {
//							if (result.success === true) {
//								UserLocationSniffer.changeArea(
//										result.region_id, result.region_name);
//							}
//						});
//			}
		},
		changeArea : function(areaId, areaName, func) {
			var expiresDate = new Date();
			expiresDate.setTime(expiresDate.getTime() + 60 * 60 * 1000 * 24
					* 365);
			$.cookie("user_area_id", areaId, {
				expires : expiresDate,
				path : '/'
			});
			$.cookie("user_area_name", areaName, {
				expires : expiresDate,
				path : '/'
			});
			if ($("#_area_")) {
				$("#_area_").html(areaName);
			}
			
			if (typeof(func) == "undefined") {
				// 判断当前是否处于按照地区展示的页面
				window.location.reload();
			} else {
				func();
			}
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
			_p_ = $.param(params);
			$.ajax({
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
			$.ajax({
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

	submit : function(_url_, _formid_, sucessCallback, toBeRefreshedAreaId) {
		url = Utils.computeUrl(_url_);
		if (typeof (_formid_) == "string") {
			if (typeof ($("#" + _formid_).validationEngine) != "undefined") {
				if (!FormValidate.validate("validate")) {
					return;
				}
			}
			var _params_ = Utils.getFormJson($("#" + _formid_));
			_params_["_rd_url_"] = window.location.href; 
			var params = _params_;
			$.ajax({
				type : "post",
				cache : false,
				url : url,
				data : $.param(params),
				dataType : "json",
				success : function(resultdata, textStatus) {
					if (toBeRefreshedAreaId
							&& typeof (toBeRefreshedAreaId) != "undefined"
							&& toBeRefreshedAreaId != null) {
						// 顶部显示提示信息
						if (resultdata.success
								&& typeof (resultdata.success) != "undefined"
								&& resultdata.success == true) {
							$("#" + toBeRefreshedAreaId).html(
									resultdata.msg);
						}
					}
					if (sucessCallback) {
						sucessCallback(resultdata, textStatus);
					}
				}
			});
		} else if (typeof (_formid_) == "object") {
			$.ajax({
				type : "post",
				cache : false,
				url : url,
				data : $.param(_formid_),
				dataType : "json",
				success : function(resultdata, textStatus) {
					if (toBeRefreshedAreaId
							&& typeof (toBeRefreshedAreaId) != "undefined"
							&& toBeRefreshedAreaId != null) {
						// 顶部显示提示信息
						if (resultdata.success
								&& typeof (resultdata.success) != "undefined"
								&& resultdata.success == true) {
							$("#" + toBeRefreshedAreaId).html(
									resultdata.msg);
						}
					}
					if (sucessCallback) {
						sucessCallback(resultdata, textStatus);
					}
				}
			});
		}
	},
	invok : function(_url_, sucessCallback) {
		if (_url_.startsWith("/")) {
			_url_ = g_site_url + _url_;
		}
		
		_url_ = _url_.indexOf("?") > 0 ? _url_ + "&token=" + Utils.getToken() : _url_ + "?token=" + Utils.getToken() 
		
		$.ajax({
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
		$.ajax({
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
	fill : function(url, areaId, isShowLoading) {
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

		$.ajax({
			type : "post",
			cache : false,
			url : url,
			data : $.param(params),
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
		var _params_ = Utils.getFormJson($("#" + _formid_));
		var params = _params_;
		$.ajaxFileUpload({
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


// 对用户进行处理

var user = null;
var _current_page_ = 0;
try {
	//alert($.fn.cookie("_greencube_user_"));
	user = Utils.string2Json($.cookie("_greencube_user_"));
} catch (e) {
}

$(document).ready(function() {

	UserLocationSniffer.init();

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
});
