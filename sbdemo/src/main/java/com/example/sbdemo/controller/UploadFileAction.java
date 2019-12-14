package com.example.sbdemo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.example.sbdemo.util.EmiJsonObj;
import com.example.sbdemo.util.FileUploadUtils;
import com.example.sbdemo.util.ResultData;

@Controller
@RequestMapping("/upload")
public class UploadFileAction  {
	
	@RequestMapping("/image")  
	@ResponseBody
	public void image(@RequestParam("file")MultipartFile file,String path,HttpServletRequest request,HttpServletResponse response) throws IOException { 
		ResultData<Object> rd = new ResultData<>();
//		String issave=CodeHelper.getCodeValue("issave");
//		String url="/upload/image.do";
//		String parmas="";
//		parmas="file="+file+"&path="+path;
		try {
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
			if("jpg,png,bmp,jpeg".indexOf(suffix)<0){
				rd.setSuccess(false);
				rd.setMsg("文件格式不正确，请上传jpg,png,bmp,jpeg格式的图片文件！");
			}else{ // 10m
				rd.setMsg(FileUploadUtils.upload(file, path, request));
				rd.setSuccess(true);
			}
//			if(issave.equals("true"))
//				Logger.getLogger(this.getClass()).info(url+"|"+parmas+"|上传图片");
			
		} catch (Exception e) {
			rd.setSuccess(false);
		}
		response.setCharacterEncoding("UTF-8");
		//response.getWriter().write(EmiJsonObj.fromObject(rd).toString());
		response.getWriter().write(EmiJsonObj.fromObject(true).toString());
	} 

	@RequestMapping("/video")  
	@ResponseBody  
	public void video(@RequestParam("file")MultipartFile file,String path,HttpServletRequest request,HttpServletResponse response) throws IOException { 
		ResultData<Object> rd = new ResultData<>();
//		String issave=CodeHelper.getCodeValue("issave");
//		String url="/upload/video.do";
//		String parmas="";
//		parmas="file="+file+"&path="+path;
		try {
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
			if("mp3,wav,wma,mp4".indexOf(suffix)<0){
				rd.setSuccess(false);
				rd.setMsg("文件格式不正确，请上传mp3,wav,wma,mp4格式的音频文件！");
			}else{
				rd.setMsg(FileUploadUtils.upload(file, path, request));
				rd.setSuccess(true);
			}
//			if(issave.equals("true"))
//				Logger.getLogger(this.getClass()).info(url+"|"+parmas+"|上传视频");
		} catch (Exception e) {
			rd.setSuccess(false);
//			Logger.getLogger(this.getClass()).error(e.getMessage()+"|"+url+"|"+parmas+"|上传视频");
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(EmiJsonObj.fromObject(rd).toString());
	}

	@RequestMapping("/photo")  
	@ResponseBody  
	public void photo(@RequestParam("file")MultipartFile file,String path,HttpServletRequest request,HttpServletResponse response) throws IOException { 
		ResultData<Object> rd = new ResultData<>();
//		String issave=CodeHelper.getCodeValue("issave");
//		String url="/upload/photo.do";
//		String parmas="";
//		parmas="file="+file+"&path="+path;
		try {
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
			if("jpg,png,bmp,jpeg".indexOf(suffix)<0){
				rd.setSuccess(false);
				rd.setMsg("文件格式不正确，请上传jpg,png,bmp,jpeg格式的图片文件！");
			}else{
				rd.setMsg(FileUploadUtils.upload(file, path, request));
				rd.setSuccess(true);
			}
//			if(issave.equals("true"))
//				Logger.getLogger(this.getClass()).info(url+"|"+parmas+"|上传照片");
		} catch (Exception e) {
			rd.setSuccess(false);
//			Logger.getLogger(this.getClass()).error(e.getMessage()+"|"+url+"|"+parmas+"|上传照片");
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(EmiJsonObj.fromObject(rd).toString());
	}

	@RequestMapping("/file")  
	@ResponseBody  
	public void file(@RequestParam("file")MultipartFile file,String path,HttpServletRequest request,HttpServletResponse response) { 
		ResultData<Map> rd = new ResultData<>();
//		String issave=CodeHelper.getCodeValue("issave");
//		String url="/upload/file.do";
//		String parmas="";
//		parmas="file="+file+"&path="+path;
		try {
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
			if("mp3,mp4,wav,wma,jpg,png,bmp,jpeg,txt,doc,docx,xls,lrc".indexOf(suffix)<0){
				rd.setSuccess(false);
				rd.setMsg("文件格式不正确，请上传mp3,mp4,wav,wma,jpg,png,bmp,jpeg,txt,doc,docx,xls,lrc格式的文件！");
			}else{
				Map m = new HashMap();
				m.put("path", FileUploadUtils.upload2(file, path, request));
				m.put("name", file.getOriginalFilename());
				rd.setData(m);
				rd.setSuccess(true);
			}
			request.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(EmiJsonObj.fromObject(rd).toString());
//			if(issave.equals("true"))
//				Logger.getLogger(this.getClass()).info(url+"|"+parmas+"|上传文件");
		} catch (Exception e) {
			rd.setSuccess(false);
//			Logger.getLogger(this.getClass()).error(e.getMessage()+"|"+url+"|"+parmas+"|上传文件");
		}
		
	}

	@RequestMapping("/audioimg")
	@ResponseBody  
	public void AudioImg(@RequestParam("file2")MultipartFile file2,String path,HttpServletRequest request,HttpServletResponse response) { 
		ResultData<Map> rd = new ResultData<>();
//		String issave=CodeHelper.getCodeValue("issave");
//		String url="/upload/audioimg.do";
//		String parmas="";
//		parmas="file2="+file2+"&path="+path;
		try {
			String suffix = file2.getOriginalFilename().substring(file2.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
			if("jpg,png,bmp,jpeg".indexOf(suffix)<0){
				rd.setSuccess(false);
				rd.setMsg("文件格式不正确，请上传jpg,png,bmp,jpeg格式的文件！");
			}else{
				Map m = new HashMap();
				m.put("path", FileUploadUtils.upload2(file2, path, request));
				m.put("name", file2.getOriginalFilename());
				rd.setData(m);
				rd.setSuccess(true);
			}
			request.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(EmiJsonObj.fromObject(rd).toString());
//			if(issave.equals("true"))
//				Logger.getLogger(this.getClass()).info(url+"|"+parmas+"|上传图片");
		} catch (Exception e) {
			rd.setSuccess(false);
//			Logger.getLogger(this.getClass()).error(e.getMessage()+"|"+url+"|"+parmas+"|上传图片");
		}
//		responseJson(rd);
	}

	@RequestMapping("/audioimg1")
	@ResponseBody  
	public void AudioImg1(@RequestParam("file1")MultipartFile file2,String path,HttpServletRequest request,HttpServletResponse response) { 
		ResultData<Map> rd = new ResultData<>();
//		String issave=CodeHelper.getCodeValue("issave");
//		String url="/upload/audioimg1.do";
//		String parmas="";
//		parmas="file2="+file2+"&path="+path;
		try {
			String suffix = file2.getOriginalFilename().substring(file2.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
			if("jpg,png,bmp,jpeg".indexOf(suffix)<0){
				rd.setSuccess(false);
				rd.setMsg("文件格式不正确，请上传jpg,png,bmp,jpeg格式的文件！");
			}else{
				Map m = new HashMap();
				m.put("path", FileUploadUtils.upload2(file2, path, request));
				m.put("name", file2.getOriginalFilename());
				rd.setData(m);
				rd.setSuccess(true);
			}
			request.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(EmiJsonObj.fromObject(rd).toString());
//			if(issave.equals("true"))
//				Logger.getLogger(this.getClass()).info(url+"|"+parmas+"|上传图片");
		} catch (Exception e) {
			rd.setSuccess(false);
//			Logger.getLogger(this.getClass()).error(e.getMessage()+"|"+url+"|"+parmas+"|上传图片");
		}
//		responseJson(rd);
	}



	@RequestMapping("/audiomusic")  
	@ResponseBody  
	public void AudioMusic(@RequestParam("file3")MultipartFile file3,String path,HttpServletRequest request,HttpServletResponse response) { 
		SaveMusic(file3,path,request,response);
	}

	@RequestMapping("/audiomv")  
	@ResponseBody  
	public void MVMusic(@RequestParam("filemv")MultipartFile file3,String path,HttpServletRequest request,HttpServletResponse response) { 
		SaveMusic(file3,path,request,response);
	}

	private void SaveMusic(MultipartFile file3,String path,HttpServletRequest request,HttpServletResponse response){
		ResultData<Map> rd = new ResultData<>();
		try {
			String suffix = file3.getOriginalFilename().substring(file3.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
			if("mp3,mp4,wav,wma,avi,rm,rmvb,wmv,mov,3gp,flv".indexOf(suffix)<0){
				rd.setSuccess(false);
				rd.setMsg("文件格式不正确，请上传mp3,mp4,wav,wma,avi,rm,rmvb,wmv,mov,3gp,flv格式的文件！");
			}else{
				Map m = new HashMap();
				m.put("path", FileUploadUtils.upload2(file3, path, request));
				m.put("name", file3.getOriginalFilename());
				m.put("size", file3.getSize());
				rd.setData(m);
				rd.setSuccess(true);
			}
			request.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(EmiJsonObj.fromObject(rd).toString());
		} catch (Exception e) {
			rd.setSuccess(false);
		}
//		responseJson(rd);
	}

	@RequestMapping("/delfile")  
	@ResponseBody  
	public void delfile(String path,HttpServletRequest request,HttpServletResponse response) throws IOException { 
		ResultData<Object> rd = new ResultData<>();
		try {
			String[] paths=path.split(",");
			for(String imagepath:paths){
				String realPath = request.getSession().getServletContext().getRealPath("/");
				rd.setSuccess(FileUploadUtils.delFile( realPath+imagepath));
			}
		} catch (Exception e) {
			rd.setSuccess(false);
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(EmiJsonObj.fromObject(rd).toString());
	}

	@RequestMapping("/uploadfile")  
	@ResponseBody  
	public void springUpload(HttpServletRequest request,HttpServletResponse response) throws Exception{
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
		
		ResultData<Map> rd = new ResultData<>();
		Map m = new HashMap();
		if(multipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
			Iterator iter=multiRequest.getFileNames();
			String path = request.getParameter("path");
			while(iter.hasNext()){
				MultipartFile file=multiRequest.getFile(iter.next().toString());
				if(file!=null){
					String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
					if("jpg,png,bmp,jpeg".indexOf(suffix)<0){
						rd.setSuccess(false);
						rd.setMsg("文件格式不正确，请上传jpg,png,bmp,jpeg格式的文件！");
					}else{
						m.put("path", FileUploadUtils.upload2(file, path, request));
						m.put("name", file.getOriginalFilename());
					}
				}
			}
		}
		rd.setData(m);
		rd.setSuccess(true);
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(EmiJsonObj.fromObject(rd).toString());
//		responseJson(rd);
	}
	
	
	
	
	@RequestMapping("/audioall")  
	@ResponseBody  
	public void audioall(@RequestParam("fileAll")MultipartFile file3,String path,HttpServletRequest request,HttpServletResponse response)throws Exception { 
		SaveFileAll(file3,path,request,response);
	}

	private void SaveFileAll(MultipartFile file3,String path,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ResultData<Map> rd = new ResultData<>();
		try {			
			Map m = new HashMap();
			m.put("path", FileUploadUtils.upload2(file3, path, request));
			m.put("name", file3.getOriginalFilename());
			m.put("size", file3.getSize());
			rd.setData(m);
			rd.setSuccess(true);			
		} catch (Exception e) {
			rd.setSuccess(false);
		}
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(EmiJsonObj.fromObject(rd).toString());
//		responseJson(rd);
	}
	
	@RequestMapping("/word")  
	@ResponseBody  
	public void word(@RequestParam("file")MultipartFile file,String path,HttpServletRequest request,HttpServletResponse response) throws Exception { 
		ResultData<Map> rd = new ResultData<>();
//		String issave=CodeHelper.getCodeValue("issave");
//		String url="/upload/file.do";
//		String parmas="";
//		parmas="file="+file+"&path="+path;
		try {
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
			if("doc,xls,ppt,pdf,docx,xlsx,pptx".indexOf(suffix)<0){
				rd.setSuccess(false);
				rd.setMsg("文件格式不正确，请上传doc,xls,ppt,pdf,docx,xlsx,pptx格式的文件！");
			}else{
				Map m = new HashMap();
				m.put("path", FileUploadUtils.upload2(file, path, request));
				m.put("name", file.getOriginalFilename());
				rd.setData(m);
				rd.setSuccess(true);
			}
//			if(issave.equals("true"))
//				Logger.getLogger(this.getClass()).info(url+"|"+parmas+"|上传文件");
		} catch (Exception e) {
			rd.setSuccess(false);
//			Logger.getLogger(this.getClass()).error(e.getMessage()+"|"+url+"|"+parmas+"|上传文件");
		}
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(EmiJsonObj.fromObject(rd).toString());
//		responseJson(rd);
	}
	
	@RequestMapping("/allvideo")  
	@ResponseBody  
	public void allvideo(@RequestParam("file")MultipartFile file,String path,HttpServletRequest request,HttpServletResponse response)throws Exception { 
		ResultData<Map> rd = new ResultData<>();
//		String issave=CodeHelper.getCodeValue("issave");
//		String url="/upload/file.do";
//		String parmas="";
//		parmas="file="+file+"&path="+path;
		try {
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
			if("mp3,mp4,wav,wma,avi,rm,rmvb,wmv,mov,3gp,flv,jpg,png,bmp,jpeg".indexOf(suffix)<0){
				rd.setSuccess(false);
				rd.setMsg("文件格式不正确，请上传mp3,mp4,wav,wma,avi,rm,rmvb,wmv,mov,3gp,flv,jpg,png,bmp,jpeg格式的文件！");
			}else{
				Map m = new HashMap();
				m.put("path", FileUploadUtils.upload2(file, path, request));
				m.put("name", file.getOriginalFilename());
				rd.setData(m);
				rd.setSuccess(true);
			}
//			if(issave.equals("true"))
//				Logger.getLogger(this.getClass()).info(url+"|"+parmas+"|上传文件");
		} catch (Exception e) {
			rd.setSuccess(false);
//			Logger.getLogger(this.getClass()).error(e.getMessage()+"|"+url+"|"+parmas+"|上传文件");
		}
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(EmiJsonObj.fromObject(rd).toString());
//		responseJson(rd);
	}
}
