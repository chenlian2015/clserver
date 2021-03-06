package com.yuhuayuan.global;

/**
 * Created by cl on 2017/3/2.
 */
public enum ErrorCode {
    SUCCESS("1", "success"),

    FAILED("2", "error"),

    EC_300001("-300001", "该账号异常"),

    EC_400000("-400000", "消息体格式错误"),

    EC_400001("-400001", "服务器内部错误"),
    /**
     * 请求参数不正确
     */
    EC_400004("-400004", "请求参数不正确"),

    EC_400005("-400005", "该编码已存在"),
    EC_400006("-400006", "商品定时上架时间有误！"),
    EC_400007("-400007", "商品定时下架时间有误！"),

    EC_500001("-500001", "输入的密码不正确，请重新输入！"),
    EC_500002("-500002", "联系电话已存在！"),
    EC_600001("-600001", "该服务区域目前已有满减活动，先去把有效的满减失效，再来创建新活动吧。"),
    EC_600002("-600002", "请输入有效期"),
    EC_600003("-600003", "请选择指定区域"),
    EC_700002("-700002", "关店状态不能上架商品！"),
    EC_700003("-700003", "展示商铺不能上架商品！"),
    EC_800001("-800001", "未获取订单"),
    EC_800002("-800002", "订单状态已变更，请刷新后操作！"),
    EC_800003("-800003", "操作非法！"),
    EC_900001("-900001", "修改的发行量不可低于已发放量！"),
    EC_900002("-900002", "优惠券库存不足！"),
    EC_900003("-900003", "请添加要发放优惠券的用户电话！"),
    EC_900004("-900004", "解析用户电话excel失败！"),
    EC_900005("-900005", "修改的发行量不可为0！"),
    EC_1000001("-1000001", "操作失败，有商品正在广告宣传中！"),
    EC_1000002("-1000002", "操作失败，有商品正在推广中！"),
    EC_1000003("-1000003", "操作失败，有店铺正在推广中！"),
    EC_1000006("-1000006", "此商品正在广告宣传中，不能下架呢，如需下架请联系平台的客服人员。"),
    EC_1000007("-1000007", "此商品正在推广中，不能下架呢，如需下架请联系平台的客服人员。");

    private String result;
    private String message;

    private ErrorCode(String code, String message) {
        this.result = code;
        this.message = message;
    }

    public String getCode() {
        return result;
    }

    public void setCode(String code) {
        this.result = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
