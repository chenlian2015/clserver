package com.cnd.greencube.web.base.plugin.fileupload.alioos;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.cnd.greencube.web.base.plugin.fileupload.IFileUploador;
import com.cnd.greencube.web.base.plugin.fileupload.TFile;

/**
 * 阿里云OOS文件上传插件
 * 
 * @author huxg
 * 
 */
public class AliOOSFileUploador implements IFileUploador {
//	@R
//	String accessid = "";
	
	@Override
	public TFile uploadFile(MultipartFile file) {
//		if (imgFile != null) {
//			string accessid = "ut5hhgas69gKf3jT"; // AccessID
//			string accesskey = "Rv4crh7unAKz8SJbZ3TffNwdLsblze"; // AccessKey
//			ossClient = new Aliyun.OpenServices.OpenStorageService.OssClient(accessid, accesskey); // 当然这里可以封装下
//			ObjectMetadata meta = new ObjectMetadata();
//			meta.ContentType = "image/jpeg";
//			string key = "pic/" + imgFile.FileName;
//			PutObjectResult result = ossClient.PutObject(bucketName, key, imgFile.InputStream, meta);// 上传图片
//			AccessControlList accs = ossClient.GetBucketAcl(bucketName);
//			string imgurl = string.Empty;
//			if (!accs.Grants.Any())// 判断是否有读取权限
//			{
//				imgurl = ossClient.GeneratePresignedUri(bucketName, key, DateTime.Now.AddMinutes(5)).AbsoluteUri; // 生成一个签名的Uri
//																													// 有效期5分钟
//			} else {
//
//				imgurl = string.Format("http://{0}.oss.aliyuncs.com/{1}", bucketName, key);
//			}
//			context.Response.Write(String.Format("{0}|{1}|{2}|{3}", imgurl, imgurl, imgurl, key));//
//		}
		return null;
	}

	@Override
	public InputStream getFileInputStreamFromUrl(String url) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultipartFile getFileFromUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

}
