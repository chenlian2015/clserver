package com.cnd.greencube.web.base.plugin.fileupload.samba;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.cnd.greencube.web.base.config.SysConfiguration;
import jcifs.smb.SmbFile;

/**
 * samba 工具类
 * 
 * @author huxg
 * 
 */
public class SambaFileUtils {
	/**
	 * 删除Samba服务器上的文件
	 * 
	 * @param relativeFile
	 * @return
	 */
	public static boolean deleteFile(String relativeFile) {
		try {
			SmbFile file = getFile(relativeFile);
			if (file.exists())
				file.delete();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 取得Samba服务器上的文件
	 * 
	 * @param relativePath
	 * @return
	 * @throws Exception
	 */
	public static SmbFile getFile(String filepath) throws Exception {
		String fileRoot = SysConfiguration.getInstance().getProperty("upload.folder");
		SmbFile remoteFolder = new SmbFile(fileRoot + filepath);
		return remoteFolder;
	}

	public static SmbFile getFolder(String relativePath) throws Exception {
		String fileRoot = SysConfiguration.getInstance().getProperty("upload.folder");
		System.out.println("fileRoot=====" + fileRoot);
		SmbFile remoteFolder = new SmbFile(fileRoot + relativePath);
		System.out.println("remoteFolder=====" + remoteFolder);
		if (!remoteFolder.exists()) {
			// 创建远程文件夹
			remoteFolder.mkdirs();
			System.out.println("remoteFolder.mkdirs();=====");
		}
		return remoteFolder;

	}

	public static String getFileUrl(String fileRelativePath) {
		SysConfiguration sys = SysConfiguration.getInstance();
		return sys.getProperty("CFG_URL_O_FS") + "/store" + fileRelativePath;
	}

	/**
	 * 将字符串保存到文件服务器上
	 * 
	 * @param s
	 * @param filename
	 * @return
	 */
	public static boolean saveString2SambaServer(String s, String filename) {
		Writer writer = null;
		try {
			// 取得文件夹
			SmbFile outputFile = getFile(filename);
			String parentFilePath = outputFile.getParent();
			// 判断文件夹是否存在
			SmbFile folder = new SmbFile(parentFilePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			writer = new BufferedWriter(new OutputStreamWriter(outputFile.getOutputStream(), "UTF-8"));
			writer.write(s);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
			}
		}
	}
}
