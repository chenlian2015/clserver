/*******************************************************************************
 * * 产品管理界面类 * 作者 胡晓光 * *
 ******************************************************************************/
var ProductBusiness = function() {
	return {
		product_list : function(tabid, url, callback) {
			$(function () {
					'use strict';
					var $tab1 = $('#tab1');
					var $tab2 = $('#tab2');
			    
					// 停止滑动
					$tab1.on('touchend',function(){
						$(".slide-edit").hide();
					});
					$tab2.on('touchend',function(){
						$(".slide-edit").hide();
					});
					
					// 编辑菜单
					$(".content").on('click', '.state' , function(){
						$('.slide-edit').hide();
					    $(this).parent().next().show();
					});
					
					// 上架事件
					$(".content").on('click', '.onShelf', function(){
						var pGoods = $(this).parents("goods-list");
						var id = pGoods.attr("data-id");
						var dataType =  pGoods.attr("data-type");
						if (dataType == "goods") {
							ProductBusiness.goodsOnShelf(id);
						} else {
							ProductBusiness.productOnShelf(id);
						}
					});
					
					// 下架事件
					$(".content").on('click', '.offShelf', function(){
						var pGoods = $(this).parents("goods-list");
						var id = pGoods.attr("data-id");
						var dataType =  pGoods.attr("data-type");
						if (dataType == "goods") {
							ProductBusiness.goodsOffShelf(id);
						} else {
							ProductBusiness.productOffShelf(id);
						}
					});
					
					// 编辑商品事件
					$(".content").on('click', '.editGoods', function(){
						var pGoods = $(this).parents("goods-list");
						var id = pGoods.attr("data-id");
						var dataType =  pGoods.attr("data-type");
						if (dataType == "goods") {
							window.location.assign("product_assemble.html?goodsId=" + id);
						} else {
							window.location.assign("product_assemble.html");
						}
					});
			    	
					// 点击删除商品按钮触发
					$(".content").on('click', '.delete' , function(){
						var currentGoods = $(this).parents('goods-list');
						$.confirm('确定要删除该商品吗？', function(){
							Request.invok(currentGoods.attr("data-url") , function(result){
								if (result.success == true) {
									currentGoods.remove();
								} else {
									$.toast("无法删除！");
								}
							});
						});
					});
					
					// 绑定刷新
					TabListView.init('product_list')
								.create('tab1', '/ucenter/product/manager/data_product_list?label=goods', function(result){
									var hml = '';
									for (var i = 0; i < result.data.length; i ++ ){
										var row = result.data[i];
										hml +=  '					<div class="goods-list" data-id="' + row.id + '" data-type="goods">' +
												'						  <div class="list-block goods-list-block"> ' +
												'						    <li class="item-content">' +
												'						     <div class="item-media">' +
												'						      <i class="icon"></i>' +
												'						     </div>' +
												'						     <div class="item-inner btn-checked">' +
												'						      <div class="item-title">' +
												'						       编号：' +
												'						      </div>' +
												'						      <div class="item-after">' +
												'						       ' + row.num + '' +
												'						      </div>' +
												'						      <div class="state">';
												
												if (row.statusShelf == 1) {
													hml += '已上架';
												} else {
													hml += '已下架';
												}
													
										hml +=  '						        <a href="javascript:;" class="triangle-down"><img class="arrow" src="/themes/app/images/icon/arrow2.png" alt=""></a>' +
												'						      </div>' +
												'						     </div>' +
												'						     <div class="slide-edit">' +
												'						        <ul>' + 
												'						          <div class="triangle-up"></div>';
												if (row.statusShelf == 1) {
													hml += '<li class="offShelf">下架</li>';
												} else {
													hml += '<li class="onShelf">上架</li>';
												}
												
										hml +=	'						          <li class="editGoods">修改</li>' +
												'						          <li class="clored delete" data-url="/service/ShopGoodsService/deleteGoodsById?goodsId=' + row.id + '">删除</li>' +
												'						        </ul>' +
												'						     </div>' +
												'						    </li>' +
												'						  </div>' +
												'						  <div class="goods-list-item clearfix">' +
												'						    <a class="z goods-img" href="javascript:;"><img src="' + row.picMain + '" width="77" height="77"/></a>' +
												'						    <div class="y disc">' +
												'						      <a href="javascript:;" class="info">' + row.name + '</a>' +
												'						      <p class="goods-price">￥' + row.realFee + '.00</p>' +
												'						    </div>' +
												'						  </div>' +
												'						</div>';
									}
									return hml;
								 })
								.create('tab2', '/ucenter/product/manager/data_product_list?label=product', function(result){
									var hml = '';
									for (var i = 0; i < result.data.length; i ++ ){
										var row = result.data[i];
										hml +=  '					<div class="goods-list" data-id="' + row.id + '" data-type="product">' +
												'						  <div class="list-block goods-list-block"> ' +
												'						    <li class="item-content">' +
												'						     <div class="item-media">' +
												'						      <i class="icon"></i>' +
												'						     </div>' +
												'						     <div class="item-inner btn-checked">' +
												'						      <div class="item-title">' +
												'						       编号：' +
												'						      </div>' +
												'						      <div class="item-after">' +
												'						       ' + row.num + '' +
												'						      </div>' +
												'						      <div class="state">';
												
												if (row.statusShelf == 1) {
													hml += '已上架';
												} else {
													hml += '已下架';
												}
													
										hml +=  '						        <a href="javascript:;" class="triangle-down"><img class="arrow" src="/themes/app/images/icon/arrow2.png" alt=""></a>' +
												'						      </div>' +
												'						     </div>' +
												'						     <div class="slide-edit">' +
												'						        <ul>' + 
												'						          <div class="triangle-up"></div>';
												if (row.statusShelf == 1) {
													hml += '<li class="offShelf">下架</li>';
												} else {
													hml += '<li class="onShelf">上架</li>';
												}
												
										hml +=	'						          <li class="editGoods">修改</li>' +
												'						          <li class="clored delete" data-url="/service/ShopGoodsService/deleteGoodsById?goodsId=' + row.id + '">删除</li>' +
												'						        </ul>' +
												'						     </div>' +
												'						    </li>' +
												'						  </div>' +
												'						  <div class="goods-list-item clearfix">' +
												'						    <a class="z goods-img" href="javascript:;"><img src="' + row.picMain + '" width="77" height="77"/></a>' +
												'						    <div class="y disc">' +
												'						      <a href="javascript:;" class="info">' + row.name + '</a>' +
												'						      <p class="goods-price">￥' + row.realFee + '.00</p>' +
												'						    </div>' +
												'						  </div>' +
												'						</div>';
									}
									return hml;
								 })
								.bind();
			    
					$.init();
			});
		},
		
		/**
		 * 产品组装界面
		 */
		goods_edit : function (){
			$(".product-ass li").click(function(){
				dataFor = $(this).attr("data-for");
				if (typeof(dataFor) != "undefined" && !dataFor.isEmpty()) {
					$("#" + dataFor).siblings().hide();
					$("#" + dataFor).show();
				}
			});
			
			// 各界面header的返回按钮点击事件
			$(".goback_goods_edit").click(function(){
				$("#product_assemble").show();
				$("#product_assemble").siblings().hide();
				return false;
			});
			
			// 读取当前所选中的商品
			var goodsId = Utils.getQueryString("token");
			if (!goodsId || goodsId.isEmpty()) {
				// 添加商品
			} else {
				// 编辑商品
				Request.invok('/ucenter/product/manager/data_product_assemble?goodsId=' + goodsId, function(result){
					// 设置界面项
					if (result.success == true) {
						var liChoiceList = $("#li_choice_list");
						if (result.items.length > 0) {
							liChoiceList.addClass("ins-end");
							liChoiceList.find(".icon-check").removeClass("hide");
							
							// 填充已选择的商品
							var selectedProductHtml = '';
							for (var i = 0; i < result.items.length; i ++){
								selectedProductHtml +=  '            <div class="shop-list">' + 
														'	            <div class="pro-inf">' + 
														'	              <img src="/themes/app/images/portrait.jpg" alt="图片">' + 
														'	              <div class="rig-inf">' + 
														'	                <ul>' + 
														'	                  <li>专家会诊是的范德萨发生的都是地方</li>' + 
														'	                  <li>背景儿童医院</li>' + 
														'	                  <li>¥4500 <i class="icon icon-remove delete-icon"></i></li>' + 
														'	                </ul>' + 
														'	              </div>' + 
														'	              <div class="num-operation">' + 
														'	                  <span>2</span>' + 
														'	                  <span>' + 
														'	                    <div class="icon icon-up quantity-increase" id="quantityPlus" onclick="plus(this)"></div>' + 
														'	                    <div class="icon icon-down quantity-decrease" onclick="minus(this)"></div>' + 
														'	                  </span>' + 
														'	              </div>' + 
														'	            </div>' + 
														'	          </div>';
							}
							$("#product_assemble .content").append(selectedProductHtml);
						} else {
							liChoiceList.removeClass("ins-end");
							liChoiceList.find(".icon-check").addClass("hide");
						}
					} 
				});
			}
		},
		
		/**
		 * 编辑产品
		 */
		product_edit : function (){
			// 返回事件
			$(document).on('click','.confirm-title-ok', function () {
		          $.confirm('返回将添加失败', '提示', function () {
		              $.alert('添加失败');
		          });
		    });
			
			// 提交信息按钮触发事件
			$(".sub-btn").click(function(){
				// 制作json数据，并提交
				var jsonStr = Utils.json2String(Utils.getFormJson('#form_product_edit'));
				alert(jsonStr);
				var submitJson = {};
				submitJson.productJson = jsonStr;
				Request.submit('/service/ProductService/updateProduct' ,submitJson, function(result){
					if (result.success == true) {
						$.toast("添加成功！");
						Router.go('product_list.html');
					} else {
						$.toast("服务器繁忙，暂时无法提交，请稍后再试！");
					}
				});
			});
		},
		
		/**
		 * 商品上架
		 */
		goodsOnShelf : function(){
			Request.invok('/service/ShopGoodsService/putShelf?goodsId=' + id, function(result){
				if (result.success == true) {
					$.toast("状态已改变！");
				} else {
					$.toast("此商品无法上架！");
				}
			});
		},
		
		/**
		 * 商品下架
		 */
		goodsOffShelf : function(id){
			Request.invok('/service/ShopGoodsService/offShelf?goodsId=' + id, function(result){
				if (result.success == true) {
					$.toast("状态已改变！");
				} else {
					$.toast("此商品无法上架！");
				}
			});
		},
		
		/**
		 * 产品上架
		 */
		productOnShelf : function(id){
			Request.invok('/service/ShopGoodsService/putShelf?productId=' + id, function(result){
				if (result.success == true) {
					$.toast("状态已改变！");
				} else {
					$.toast("此商品无法上架！");
				}
			});
		},
		
		/**
		 * 产品下架
		 */
		productOffShelf : function(id){
			Request.invok('/service/ShopGoodsService/offShelf?productId=' + id, function(result){
				if (result.success == true) {
					$.toast("状态已改变！");
				} else {
					$.toast("此商品无法上架！");
				}
			});
		}
		
	};
}();