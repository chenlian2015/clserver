package com.cnd.greencube.web.base.plugin.fileupload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jcifs.smb.SmbFile;

import org.springframework.web.multipart.MultipartFile;

/**
 * MultiPart 本地文件
 * 
 * @author huxg
 * 
 */
public class MyMultiPartFile implements MultipartFile {
	private File file = null;
	private SmbFile smbFile = null;

	public MyMultiPartFile(File file) {
		this.file = file;
	}

	public MyMultiPartFile(SmbFile smbFile) {
		this.smbFile = smbFile;
	}

	@Override
	public String getName() {
		return smbFile == null ? file.getName() : smbFile.getName();
	}

	@Override
	public String getOriginalFilename() {
		return smbFile == null ? file.getName() : smbFile.getName();
	}

	@Override
	public String getContentType() {
		return "application/octet-stream";
	}

	@Override
	public boolean isEmpty() {
		if (smbFile == null)
			return !this.file.exists() || file.length() == 0;
		else
			try {
				return !this.smbFile.exists() || smbFile.length() == 0;
			} catch (Exception e) {
				return true;
			}
	}

	@Override
	public long getSize() {
		if (file != null) {
			return file.length();
		} else {
			try {
				return smbFile.length();
			} catch (Exception e) {
				return -1;
			}
		}
	}

	@Override
	public byte[] getBytes() throws IOException {
		ByteArrayOutputStream out = null;
		InputStream in = null;
		try {
			out = new ByteArrayOutputStream();
			in = getInputStream();
			int count = -1;
			byte[] data = new byte[1024];

			while ((count = in.read(data, 0, 1024)) != -1) {
				out.write(data, 0, count);
			}
			return out.toByteArray();
		} catch (Exception e) {

		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (Exception e) {
			}
		}
		return null;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		if (this.file != null)
			return new FileInputStream(this.file);
		else {
			return smbFile.getInputStream();
		}
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
	}
}