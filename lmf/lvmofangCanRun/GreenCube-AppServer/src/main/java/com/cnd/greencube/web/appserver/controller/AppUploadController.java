package com.cnd.greencube.web.appserver.controller;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cnd.greencube.web.base.filter.parameter.ParameterWrapper;
import com.cnd.greencube.web.base.plugin.fileupload.TFile;
import com.cnd.greencube.web.base.plugin.fileupload.UploadFileManager;
import com.cnd.greencube.web.base.util.StringUtils;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 文件上传视图类
 * 
 * @author apple
 * 
 */
@Controller("AppUploadController")
@RequestMapping("/upfile")
public class AppUploadController extends BaseController {
	// 最大允许上传5M
	private final int MAX_FILE_LENGTH = 5;
	private final String MAX_IMAGE_SIZE = "2000x2000";

	/**
	 * 上传文件
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "null" })
	@RequestMapping(value = "", produces = "text/html;charset=UTF-8")
	public @ResponseBody
	String index() {
		String type = (String) getParameter("type");

		// 默认文件类型
		if (StringUtils.isEmpty(type))
			type = "image";

		Map uploadFiles = (Map) getParameter(ParameterWrapper.UPLOAD_FILES);

		try {
			checkUploadFile(uploadFiles, type);

			Iterator iter = uploadFiles.keySet().iterator();
			String key;
			MultipartFile uploadfile;

			List<TFile> files = new ArrayList<TFile>();

			while (iter.hasNext()) {
				key = (String) iter.next();
				uploadfile = (MultipartFile) uploadFiles.get(key);
				if (uploadfile == null && uploadfile.getSize() <= 0) {
					continue;
				}

				try {
					// 读取文件，并判断文件尺寸
					TFile file = UploadFileManager.uploadFile(uploadfile);
					cutImage(file);
					files.add(file);
				} catch (Exception e) {
					throw new Exception("文件上传失败！");
				}
			}

			return Message.okMessage(files);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.errorMessage(e.getMessage());
		}
	}

	@SuppressWarnings({ "rawtypes", "unused", "null" })
	void checkUploadFile(Map uploadFiles, String type) throws Exception {
		if (null == uploadFiles || uploadFiles.size() == 0) {
			throw new Exception("未上传任何文件");
		}

		// 最大允许上传文件的大小
		String maxlen = (String) getParameter("maxlen");

		String maxsize = (String) getParameter("maxsize");

		// 是否校验上传图片尺寸
		String strictsize = (String) getParameter("strictSize");

		// 是否校验上传文件大小
		String strictLen = (String) getParameter("strictLength");

		boolean verifySize = false;
		boolean verifyLen = false;

		try {
			verifySize = Boolean.parseBoolean(strictsize);
		} catch (Exception e) {
		}

		try {
			verifyLen = Boolean.parseBoolean(strictLen);
		} catch (Exception e) {
		}

		Iterator iter = uploadFiles.keySet().iterator();

		String key;
		MultipartFile uploadfile;

		List<TFile> files = new ArrayList<TFile>();
		while (iter.hasNext()) {
			key = (String) iter.next();
			uploadfile = (MultipartFile) uploadFiles.get(key);
			if (uploadfile == null && uploadfile.getSize() <= 0) {
				continue;
			}

			// 检查上传文件大小
			if (verifyLen) {
				int max = MAX_FILE_LENGTH;
				try {
					max = Integer.parseInt(maxlen);
				} catch (Exception e) {
				}

				// 检查文件大小
				if (overflowMaxSize(max, uploadfile.getSize())) {
					// 错误，发送错误通知，同时删除文件
					throw new Exception("文件大小超过限制，您上传的文件不能超过：" + max);
				}
			}

			if ("image".equals(type)) {
				if (verifySize) {
					// 验证图片尺寸
					String max = MAX_IMAGE_SIZE;
					if (!StringUtils.isEmpty(maxsize)) {
						max = maxsize;
					}

					String[] size = max.split("x");
					try {
						int width = Integer.parseInt(size[0]);
						int height = Integer.parseInt(size[1]);

						// 读取图片文件，并检查文件尺寸大小
						BufferedImage sourceImg = ImageIO.read(uploadfile.getInputStream());
						int realWidth = sourceImg.getWidth();
						int realHeight = sourceImg.getHeight();

						if (realWidth > width || realHeight > height) {
							throw new Exception("图片上传失败，您上传的图片尺寸为" + realWidth + "x" + realHeight + "像素，超过了限定尺寸：" + max + "像素");
						}
					} catch (Exception e) {
						throw new Exception("图片上传失败，图片尺寸不符：" + max);
					}
				}
			}
		}
	}

	/**
	 * 裁剪图片
	 */
	void cutImage(TFile file) {
		String scalesize = (String) getParameter("scalesize");

		if (!StringUtils.isEmpty(scalesize) && scalesize.contains("x")) {
			String[] p = scalesize.split("x");
			int width = 0, height = 0;
			try {
				width = Integer.parseInt(p[0]);
				height = Integer.parseInt(p[1]);
			} catch (Exception e) {
				return;
			}

			String src = UploadFileManager.getRealPath(file);
			String dest = UploadFileManager.copyNewFileName(src);

			double wr = 0, hr = 0;
			File srcFile = new File(src);
			File destFile = new File(dest);

			try {
				BufferedImage bufImg = ImageIO.read(srcFile);

				Image Itemp = bufImg.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
				wr = width * 1.0 / bufImg.getWidth();
				hr = width * 1.0 / bufImg.getHeight();
				AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
				Itemp = ato.filter(bufImg, null);

				ImageIO.write((BufferedImage) Itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile);

				file.setCWjljXd(UploadFileManager.convert2RelativePath(dest));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private boolean overflowMaxSize(long maxsize, long realsize) {
		long mb = realsize / 1024 / 1024;
		return mb > maxsize;
	}

}
