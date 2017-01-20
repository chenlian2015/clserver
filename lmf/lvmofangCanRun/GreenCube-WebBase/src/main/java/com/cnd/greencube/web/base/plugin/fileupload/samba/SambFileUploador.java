package com.cnd.greencube.web.base.plugin.fileupload.samba;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

import com.cnd.greencube.web.base.config.SysConfiguration;
import com.cnd.greencube.web.base.plugin.fileupload.IFileUploador;
import com.cnd.greencube.web.base.plugin.fileupload.MyMultiPartFile;
import com.cnd.greencube.web.base.plugin.fileupload.TFile;
import com.cnd.greencube.web.base.plugin.fileupload.UploadFileManager;
import com.cnd.greencube.web.base.plugin.fileupload.UploadFileManager.FILE_TYPE;
import com.cnd.greencube.web.base.util.uuid.Key;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传客户端类 <b> 该类可以将文件上传到文件服务器上去</b>
 * 
 * @author huxg
 * 
 */
public class SambFileUploador implements IFileUploador {

	/**
	 * 返回值为数组，元素0 标识文件实际位置，元素1标识文件相对的http访问路径
	 * 
	 * @param file
	 * @param rp
	 * @return
	 */
	@Override
	public TFile uploadFile(MultipartFile file) {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		// 从缓存服务器中读取配置信息
		try {
			// 按照日期来创建文件夹
			SimpleDateFormat sf = new SimpleDateFormat("yyy" + File.separatorChar + "MM" + File.separatorChar + "dd");
			String current = sf.format(new Date());
			String outputFolder = "/" + current;
			SmbFile folder = SambaFileUtils.getFolder(outputFolder);

			int idx = file.getOriginalFilename().lastIndexOf(".");
			String ext = "";
			if (idx >= 0) {
				ext = file.getOriginalFilename().substring(idx + 1);
			}

			// 文件名
			String fileid = Key.key();
			String filename = fileid + "." + ext;

			// 目标文件
			SmbFile targetFile = new SmbFile(folder, filename);

			in = new BufferedInputStream(file.getInputStream());
			out = new BufferedOutputStream(new SmbFileOutputStream(targetFile));

			byte[] data = new byte[1024];
			int count = 0;
			while ((count = in.read(data, 0, 1024)) != -1) {
				out.write(data, 0, count);
			}

			TFile l = new TFile();
			l.setCYwjm(file.getOriginalFilename());
			l.setCWjljXd(SysConfiguration.getInstance().getProperty("system.static_server") + "/" + current + "/" + filename);
			l.setCLx(FILE_TYPE.getFileType(ext).name());
			l.setCBh(fileid);
			l.setNYwjdx(file.getSize());
			l.setCKzm(ext);
			if (l.getCLx().equals(FILE_TYPE.PIC.name())) {
				l.setCSltUrl(l.getCSltUrl());
			} else if (l.getCLx().equals(FILE_TYPE.VIDEO.name())) {
				l.setCSltUrl(UploadFileManager.URL_VIDEO_NOIMAGE);
			} else if (l.getCLx().equals(FILE_TYPE.DOC.name())) {
				l.setCSltUrl(UploadFileManager.URL_DOC_NOIMAGE);
			}

			// 当前所属分类编号，默认分类编号
			l.setCFlbh("-1");
			l.setDScsj(new Date());
			l.setNDjcs(0L);
			l.setNSccs(0L);
			l.setNPlcs(0L);
			l.setNFz(0);
			l.setNSfkf(0);
			l.setNXzcs(0L);
			l.setNFxcs(0L);

			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (Exception e) {

			}
		}
	}

	@Override
	public MultipartFile getFileFromUrl(String url) {
		String relativePath = url;
		if (url.startsWith("http")) {
			int idx = url.indexOf(":");
			relativePath = url.substring(idx + 3);
			idx = relativePath.indexOf("/");
			relativePath = relativePath.substring(idx);
		}

		try {
			SmbFile file = SambaFileUtils.getFile(relativePath);
			MultipartFile mf = new MyMultiPartFile(file);
			return mf;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据URL获取到某一个文件在文件服务器中的位置
	 */
	@Override
	public InputStream getFileInputStreamFromUrl(String url) throws Exception {
		MultipartFile mf = getFileFromUrl(url);
		return mf.getInputStream();
	}
}
