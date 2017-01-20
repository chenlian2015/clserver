package com.cnd.greencube.web.appserver.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnd.greencube.server.entity.VirtualAccount;
import com.cnd.greencube.server.service.UserAccountService;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.util.JsonUtils;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 用户钱包视图类
 * 
 * @author apple
 * 
 */
@Controller("UserWalletController")
@RequestMapping("/service/UserWallet")
public class UserWalletController extends BaseController {

	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
	public static final String path = "e:\\a.jpg";
	String result = "";

	/**
	 * 返回用户钱包当前余额、总收入、总支出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/myWallet", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String myWallet() {
		try {
			result = thriftTemplate.execute("UserAccountService", new ThriftAction() {
				final String id = (String) getParameter("userid");

				@Override
				public String action(TServiceClient userVirtualAccountService) throws Exception {
					return ((UserAccountService.Client) userVirtualAccountService).getUserVirtualAccount(id);
				}
			});

			VirtualAccount account = Message.unpackObject(result, VirtualAccount.class);
			if (account == null) {
				return Message.errorMessage("无效的用户id");
			} else {
				return Message.okMessage(
						new String[] { "current_balance", "total_income", "total_paid", "stat_url" },
						new String[] { decimalFormat.format(account.getBalance()), decimalFormat.format(account.getTotalInCome()),
								decimalFormat.format(account.getTotalPaid()), "" });
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Message.error();
		}

	}

	/**
	 * 返回用户当前收入明细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/myIncomeDetail", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String myIncomeDetail() {
		try {
			return thriftTemplate.execute("UserAccountService", new ThriftAction() {
				final String id = (String) getParameter("userid");

				@Override
				public String action(TServiceClient userAccountService) throws Exception {
					return ((UserAccountService.Client) userAccountService).loadUserIncomeDetail(id, 0);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error();
		}
	}

	/**
	 * 返回用户当前支出明细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/myPaymentDetail", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String myPaymentDetail() {
		try {
			return thriftTemplate.execute("UserAccountService", new ThriftAction() {
				final String id = (String) getParameter("userid");
				int pageNum = 0;

				@Override
				public String action(TServiceClient userAccountService) throws Exception {
					return ((UserAccountService.Client) userAccountService).loadUserPaymentDetail(id, pageNum);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error();
		}
	}

	/**
	 * 返回用户当前收益明细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/myStatDetail", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String StatDetail() {
		String data = "";
		try {
			data = thriftTemplate.execute("UserAccountService", new ThriftAction() {
				final String id = (String) getParameter("userid");

				@Override
				public String action(TServiceClient userAccountService) throws Exception {
					return ((UserAccountService.Client) userAccountService).loadUserStatDetail(id);
				}
			});
			this.createChart(data);

			return Message.okMessage(path);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error();
		}
	}

	public DefaultCategoryDataset createDataset(String data) {

		// 绘图数据集
		DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
		// 曲线名称
		String series = "";
		JSONObject jo = JsonUtils.String2JSONObject(data);
		String strData = jo.getString("data");
		JSONArray ja = JsonUtils.String2JSONArray(strData);
		int len = ja.size();
		JSONObject hh;
		for (int i = 0; i < len; i++) {
			hh = ja.getJSONObject(i);
			linedataset.addValue(Integer.parseInt(String.valueOf(hh.get("revenue"))), series, (Comparable<?>) hh.get("monthy"));
		}

		return linedataset;
	}

	// 生成图标对象JFreeChart

	public void createChart(String data) {
		try {
			// 定义图标对象
			JFreeChart chart = ChartFactory.createLineChart("图表标题", "X轴标题", "Y轴标题", this.createDataset(data), // 获得数据集
					PlotOrientation.VERTICAL, // 绘制方向
					true, // 显示图例
					false, // 不用生成工具 //采用标准生成器
					false // 不用生成URL地址 //是否生成超链接
					);
			// 定义的图标对象的属性
			chart.getTitle().setFont(new Font("隶书", Font.BOLD, 20)); // 设置标题字体
			chart.getLegend().setItemFont(new Font("宋书", Font.PLAIN, 15));// 设置图例类别字体
			chart.setBackgroundPaint(Color.white);// 设置背景色
			chart.getLegend().setVisible(false); // 设置X轴下面的图例是否显示(有多条线是用来标示)

			// 获取绘图区对象
			CategoryPlot plot = chart.getCategoryPlot();
			// 图像属性部分
			plot.setBackgroundPaint(Color.white); // 设置背景颜色为白色
			plot.setDomainGridlinesVisible(true); // 设置背景网格线是否可见
			plot.setDomainGridlinePaint(Color.gray); // 设置背景网格竖线颜色为红色
			plot.setRangeGridlinePaint(Color.gray); // 设置背景网格横线颜色为红色
			plot.setNoDataMessage("没有数据");// 没有数据时显示的文字说明。

			// 设置X轴
			CategoryAxis domainAxis = plot.getDomainAxis();
			domainAxis.setLabelFont(new Font("宋书", Font.PLAIN, 15)); // 设置横轴字体
			domainAxis.setTickLabelFont(new Font("宋书", Font.PLAIN, 15));// 设置坐标轴标尺值字体
			domainAxis.setLowerMargin(0.01);// 左边距 边框距离
			domainAxis.setUpperMargin(0.06);// 右边距 边框距离,防止最后边的一个数据靠近了坐标轴。
			domainAxis.setMaximumCategoryLabelLines(10);
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);// 横轴
																					// //
																					// 45度倾斜
																					// //
																					// DOWN_45

			// 设置Y轴
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
			rangeAxis.setLabelFont(new Font("宋书", Font.PLAIN, 15));
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());// Y轴显示整数
			rangeAxis.setAutoRangeMinimumSize(1000); // 最小跨度
			rangeAxis.setUpperMargin(0.18);// 上边距,防止最大的一个数据靠近了坐标轴。
			rangeAxis.setLowerBound(1000); // 最小值显示0
			rangeAxis.setUpperBound(100000); // 最大值显示100
			rangeAxis.setAutoRange(false); // 不自动分配Y轴数据
			rangeAxis.setTickMarkStroke(new BasicStroke(1.6f)); // 设置坐标标记大小
			rangeAxis.setTickMarkPaint(Color.BLACK); // 设置坐标标记颜色
			rangeAxis.setTickUnit(new NumberTickUnit(10000));// 每10000个刻度显示一个刻度值

			// 数据渲染部分 主要是对折线做操作
			LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
			renderer.setSeriesPaint(0, Color.blue); // 设置折线的颜色
			renderer.setBaseShapesVisible(true); // 设置拐点是否可见/是否显示拐点
			renderer.setBaseFillPaint(Color.red); // 设置折点颜色为红色
			renderer.setSeriesOutlineStroke(0, new BasicStroke(0.2F));// 设置折点的大小
			renderer.setItemLabelsVisible(false);// 是否显示每个点上的数据值
			renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); // 设置折点显示值

			// 创建文件输出流
			File jpg = new File(path);
			// 输出到哪个输出流
			ChartUtilities.saveChartAsJPEG(jpg, chart, 700, 500);// 统计图表对象 宽 高
			// System.out.println("生成折线图OK!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
