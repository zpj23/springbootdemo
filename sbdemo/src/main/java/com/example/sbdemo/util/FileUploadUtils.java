package com.example.sbdemo.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


/**
 * 关于文件上传下载用到的工具类
 * 
 */
@SuppressWarnings("unchecked")
public class FileUploadUtils {

	private static final int BUFFER_SIZE = 16 * 1024; // 上传文件的缓冲区大小

	private static Logger log = LoggerFactory.getLogger(FileUploadUtils.class);

	/**
	 * 读取本地文件
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音�?影像等文件�?
	 * @param filePathAndName
	 *            String 文件路径及名�?如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void readFileByBytes(String filePathAndName) {
		File file = new File(filePathAndName);
		InputStream in = null;
		StringBuffer sfb = new StringBuffer();
		/*   try {
            //System.out.println("以字节为单位读取文件内容，一次读�?��字节�?);
            // �?��读一个字�?
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }*/
		try {
			//System.out.println("以字节为单位读取文件内容，一次读多个字节�?);
			// �?��读多个字�?
			byte[] tempbytes = new byte[1024*10];
			int byteread = 0;
			in = new FileInputStream(filePathAndName);

			// 读入多个字节到字节数组中，byteread为一次读入的字节�?
			while ((byteread = in.read(tempbytes)) != -1) {
				System.out.write(tempbytes, 0, byteread);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
	}
	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名�?如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static boolean delFile(String filePathAndName) {

		File myDelFile = new java.io.File(filePathAndName);
		if (!myDelFile.exists()) {
			return true;
		}
		return myDelFile.delete();
	}

	/**
	 * 删除指定文件路径下面的所有文件和文件�?
	 * 
	 * @param file
	 */
	public static boolean delFiles(File file) {
		boolean flag = false;
		try {
			if (file.exists()) {
				if (file.isDirectory()) {
					String[] contents = file.list();
					for (int i = 0; i < contents.length; i++) {
						File file2X = new File(file.getAbsolutePath() + "/"
								+ contents[i]);
						if (file2X.exists()) {
							if (file2X.isFile()) {
								flag = file2X.delete();
							} else if (file2X.isDirectory()) {
								delFiles(file2X);
							}
						} else {
							throw new RuntimeException("File not exist!");
						}
					}
				}
				flag = file.delete();
			} else {
				throw new RuntimeException("File not exist!");
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 判断文件名是否已经存在，如果存在则在后面�?n)的形式返回新的文件名，否则返回原始文件名 例如：已经存在文件名 log4j.htm
	 * 则返回log4j(1).htm
	 * 
	 * @param fileName
	 *            文件�?
	 * @param dir
	 *            判断的文件路�?
	 * @return 判断后的文件�?
	 */
	public static String checkFileName(String fileName, String dir) {
		boolean isDirectory = new File(dir + fileName).isDirectory();
		if (FileUploadUtils.isFileExist(fileName, dir)) {
			int index = fileName.lastIndexOf(".");
			StringBuffer newFileName = new StringBuffer();
			String name = isDirectory ? fileName : fileName.substring(0, index);
			String extendName = isDirectory ? "" : fileName.substring(index);
			int nameNum = 1;
			while (true) {
				newFileName.append(name).append("(").append(nameNum)
				.append(")");
				if (!isDirectory) {
					newFileName.append(extendName);
				}
				if (FileUploadUtils.isFileExist(newFileName.toString(), dir)) {
					nameNum++;
					newFileName = new StringBuffer();
					continue;
				}
				return newFileName.toString();
			}
		}
		return fileName;
	}

	/**
	 * 返回上传的结果，成功与否
	 * 
	 * @param uploadFileName
	 * @param savePath
	 * @param uploadFile
	 * @return
	 */
	public static boolean upload(String uploadFileName, String savePath,
			File uploadFile) {
		boolean flag = false;
		try {
			uploadForName(uploadFileName, savePath, uploadFile);
			flag = true;
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 上传文件并返回上传后的文件名
	 * 
	 * @param uploadFileName
	 *            被上传的文件名称
	 * @param savePath
	 *            文件的保存路�?
	 * @param uploadFile
	 *            被上传的文件
	 * @return 成功与否
	 * @throws IOException
	 */
	public static String uploadForName(String uploadFileName, String savePath,
			File uploadFile) throws IOException {
		//String newFileName = checkFileName(uploadFileName, savePath);
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			fos = new FileOutputStream(savePath + uploadFileName);
			fis = new FileInputStream(uploadFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		return uploadFileName;
	}

	/**
	 * 根据路径创建�?��列的目录
	 * 
	 * @param path
	 */
	public static boolean mkDirectory(String path) {
		File file = null;
		try {
			file = new File(path);
			if (!file.exists()) {
				return file.mkdirs();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			file = null;
		}
		return false;
	}


	/**
	 * 判断文件是否存在
	 * 
	 * @param fileName
	 * @param dir
	 * @return
	 */
	public static boolean isFileExist(String fileName, String dir) {
		File files = new File(dir + fileName);
		return (files.exists()) ? true : false;
	}

	/**
	 * 获得随机文件�?保证在同�?��文件夹下不同�?
	 * 
	 * @param fileName
	 * @param dir
	 * @return
	 */
	public static String getRandomName(String fileName, String dir) {
		String[] split = fileName.split("\\.");// 将文件名�?的形式拆�?
		String extendFile = "." + split[split.length - 1].toLowerCase(); // 获文件的有效后缀

		Random random = new Random();
		int add = random.nextInt(1000000); // 产生随机�?0000以内
		String ret = add + extendFile;
		while (isFileExist(ret, dir)) {
			add = random.nextInt(1000000);
			ret = fileName + add + extendFile;
		}
		return ret;
	}

	/**
	 * 创建缩略�?
	 * 
	 * @param file
	 *            上传的文件流
	 * @param height
	 *            �?��的尺�?
	 * @throws IOException
	 */
//	public static void createMiniPic(File file, float width, float height)
//			throws IOException {
//		Image src = javax.imageio.ImageIO.read(file); // 构�?Image对象
//		int old_w = src.getWidth(null); // 得到源图�?
//		int old_h = src.getHeight(null);
//		int new_w = 0;
//		int new_h = 0; // 得到源图�?
//		float tempdouble;
//		if (old_w >= old_h) {
//			tempdouble = old_w / width;
//		} else {
//			tempdouble = old_h / height;
//		}
//
//		if (old_w >= width || old_h >= height) { // 如果文件小于锁略图的尺寸则复制即�?
//			new_w = Math.round(old_w / tempdouble);
//			new_h = Math.round(old_h / tempdouble);// 计算新图长宽
//			while (new_w > width && new_h > height) {
//				if (new_w > width) {
//					tempdouble = new_w / width;
//					new_w = Math.round(new_w / tempdouble);
//					new_h = Math.round(new_h / tempdouble);
//				}
//				if (new_h > height) {
//					tempdouble = new_h / height;
//					new_w = Math.round(new_w / tempdouble);
//					new_h = Math.round(new_h / tempdouble);
//				}
//			}
//			BufferedImage tag = new BufferedImage(new_w, new_h,
//					BufferedImage.TYPE_INT_RGB);
//			tag.getGraphics().drawImage(src, 0, 0, new_w, new_h, null); // 绘制缩小后的�?
//			FileOutputStream newimage = new FileOutputStream(file); // 输出到文件流
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
//			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(tag);
//			param.setQuality((float) (100 / 100.0), true);// 设置图片质量,100�?��,默认70
//			encoder.encode(tag, param);
//			encoder.encode(tag); // 将JPEG编码
//			newimage.close();
//		}
//	}

	/**
	 * 判断文件类型是否是合法的,就是判断allowTypes中是否包含contentType
	 * 
	 * @param contentType
	 *            文件类型
	 * @param allowTypes
	 *            文件类型列表
	 * @return 是否合法
	 */
	public static boolean isValid(String contentType, String[] allowTypes) {
		if (null == contentType || "".equals(contentType)) {
			return false;
		}
		for (String type : allowTypes) {
			if (contentType.equals(type)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unused")
	private static void getDir(String directory, String subDirectory) {
		String dir[];
		File fileDir = new File(directory);
		try {
			if (subDirectory == "" && fileDir.exists() != true)
				fileDir.mkdir();
			else if (!subDirectory.equals("")) {
				dir = subDirectory.replace('\\', '/').split("/");
				for (int i = 0; i < dir.length; i++) {
					File subFile = new File(directory + File.separator + dir[i]);
					if (subFile.exists() == false)
						subFile.mkdir();
					directory += File.separator + dir[i];
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 自己封装的一个把源文件对象复制成目标文件对象
	public static void copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst),
					BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 自己封装的计算上传文件的大小并格式化 以设置为文件属�?
	 */
	public static String formatFileLength(long size) {

		Double a;
		a = Double.valueOf(size);
		StringBuffer sb = new StringBuffer();
		java.text.DecimalFormat myformat = new java.text.DecimalFormat("#0.00");
		if (a >= 1024 * 1024 * 1024) {
			a = a / 1024 / 1024 / 1024 * 1.00;
			sb.append(myformat.format(a));
			sb.append(" GB");
		} else if (a >= 1024 * 1024 && a < 1024 * 1024 * 1024) {
			a = a / 1024 / 1024 * 1.00;
			sb.append(myformat.format(a));
			sb.append(" MB");
		} else if (a >= 1024 && a < 1024 * 1024) {
			a = a / 1024 * 1.00;
			sb.append(myformat.format(a));
			sb.append(" KB");
		} else {
			sb.append(myformat.format(a));
			sb.append(" 字节");
		}

		return sb.toString();
	}

	/**
	 * 根据文件名得到文件的后缀�?不带.
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtension(String filename) {
		int i = filename.lastIndexOf(".");
		if ((i > -1) && (i < (filename.length() - 1))) {
			return filename.substring(i + 1);
		} else {
			return "data";
		}
	}

	/**
	 * 根据文件�?基路�?得到上传文件在服务器上存储的唯一路径,并创建存储目�?
	 * 
	 * @param filename
	 *            文件�?
	 * @param basePath1
	 *            文件保存的基路径，如“D:/file/�?
	 * @param basePath2
	 *            文件保存的二级目录，如�?OA_DOC_ATTACACHMENT/�?
	 * @return
	 */
	public static String getRealFilePath(String fileName, String basePath1,
			String basePath2) {
		String uuid = UUID.randomUUID().toString();
		String fileType = getExtension(fileName);
		String fileName_UUID = uuid.toString() + "." + fileType;// 得到以唯�?��识符重命名的文件�?

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + "/" + "MM" + "/"
				+ "dd" + "/");

		String rq = sdf.format(date);
		int i = basePath2.lastIndexOf("/");

		// 如果保存的文件夹路径不以"/"结尾,则添加上，如“OA_DOC_ATTACACHMENT/�?
		if ((i >= -1) && (i != basePath2.length() - 1)) {
			basePath2 = basePath2 + "/";
		}

		int j = basePath1.lastIndexOf("/");

		// 如果基路径不�?/"结尾,则添加上，如“D:/file/�?
		if ((j >= -1) && (j != basePath1.length() - 1)) {
			basePath1 = basePath1 + "/";
		}

		mkDirectory(basePath1 + basePath2 + rq); // 根据路径创建�?��列的目录

		log.debug(basePath1 + basePath2 + rq);

		String realFilePath = basePath2 + rq + fileName_UUID; // 得到在服务器上存储的唯一路径

		log.debug(realFilePath);

		return realFilePath;
	}



	/**
	 * 描述：解析XML文件并返回内�?br>
	 *
	 * @param contentUrl
	 * @return String
	 *
	 * 作�?:lvj<br>
	 * 创建时间:2012-8-24 下午04:48:23
	 */
	public static String parserXml4Content(String contentUrl){
		File inputXml = new File(contentUrl);
		SAXReader saxReader = new SAXReader();
		String content = "";
		try {
			Document document = saxReader.read(inputXml);
			Element contentElement = document.getRootElement();
			content = contentElement.getText();
		} catch (DocumentException e) {
			//System.out.println(e.getMessage());
		}
		return content;
	}

	public static void main(String[] args) {
		parserXml4Content("");
	}

	public static String upload(MultipartFile file, String destDir,HttpServletRequest request) throws Exception {
		try {
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
			String realPath = request.getSession().getServletContext().getRealPath("/");
			File destFile = new File(realPath+destDir);
			if(!destFile.exists()){
				destFile.mkdirs();
			}
			String fileNameNew = getFileNameNew()+"."+suffix;
			File f = new File(destFile.getAbsoluteFile()+"/"+fileNameNew);
			file.transferTo(f);
			f.createNewFile();
			return  fileNameNew;
		} catch (Exception e) {
			throw e;
		}
	}
	
//	public static String uploadFtp(MultipartFile file, String destDir,HttpServletRequest request) throws Exception {
//		//创建客户端对象
//       FTPClient ftp = new FTPClient();
//       //InputStream local=null;
//		try {
//	     //连接ftp服务器
//           ftp.connect(request.getServletContext().getInitParameter("ftpIP"),Integer.parseInt(request.getServletContext().getInitParameter("ftpPort")));
//           //登录
//           ftp.login(request.getServletContext().getInitParameter("ftpUsername"), request.getServletContext().getInitParameter("ftpPassword"));
//         //检查上传路径是否存在 如果不存在返回false
//           boolean flag = ftp.changeWorkingDirectory(destDir);
//           if(!flag){
//               //创建上传的路径  该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
//               ftp.makeDirectory(destDir);
//           }
//           //指定上传路径
//           ftp.changeWorkingDirectory(destDir);
//           //指定上传文件的类型  二进制文件
//           ftp.setFileType(FTP.BINARY_FILE_TYPE);
//           String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
//           String fileNameNew = getFileNameNew()+"."+suffix;
//           //第一个参数是文件名
//           ftp.storeFile(fileNameNew, file.getInputStream());
//           return  fileNameNew;
//		} catch (Exception e) {
//			throw e;
//		}finally {
//            try {
//                //退出
//                ftp.logout();
//                //断开连接
//                ftp.disconnect();
//                
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//	}
	
	private static String getFileNameNew(){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return fmt.format(new Date());
	}

	/*
	 * 新方法增加年月日目录
	 */
	public static String upload2(MultipartFile file, String destDir,HttpServletRequest request) throws Exception {
		try {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
			String realPath = request.getSession().getServletContext().getRealPath("/");
			String filePath = destDir+fmt.format(new Date());
			File destFile = new File(realPath+filePath);
			if(!destFile.exists()){
				destFile.mkdirs();
			}
			String fileNameNew = getFileNameNew()+"."+suffix;
			filePath += "/"+fileNameNew;
			File f = new File(destFile.getAbsoluteFile()+"/"+fileNameNew);
			file.transferTo(f);
			f.createNewFile();
			return  filePath;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/*
	 * 新方法增加年月日目录
	 */
//	public static String uploadFtp2(MultipartFile file, String destDir,HttpServletRequest request) throws Exception {
//		
//		//创建客户端对象
//	       FTPClient ftp = new FTPClient();
//	       //InputStream local=null;
//			try {
//				
//		     //连接ftp服务器
//	           ftp.connect("192.168.11.49", 21);
//	           //登录
//	           ftp.login("ftptest", "111111");
//	           SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
//	         //检查上传路径是否存在 如果不存在返回false
//	           boolean flag = ftp.changeWorkingDirectory(destDir+fmt.format(new Date()));
//	           if(!flag){
//	               //创建上传的路径  该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
//	               ftp.makeDirectory(destDir+fmt.format(new Date()));
//	           }
//	           //指定上传路径
//	           ftp.changeWorkingDirectory(destDir+fmt.format(new Date()));
//	           //指定上传文件的类型  二进制文件
//	           ftp.setFileType(FTP.BINARY_FILE_TYPE);
//	           String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
//	           String fileNameNew = getFileNameNew()+"."+suffix;
//	           //第一个参数是文件名
//	           ftp.storeFile(fileNameNew, file.getInputStream());
//				return  destDir+fmt.format(new Date())+fileNameNew;
//			} catch (Exception e) {
//				throw e;
//			}finally {
//	            try {
//	                //退出
//	                ftp.logout();
//	                //断开连接
//	                ftp.disconnect();
//	                
//	            } catch (IOException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	}
}
