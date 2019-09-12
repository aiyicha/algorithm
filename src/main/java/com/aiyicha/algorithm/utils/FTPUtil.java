package com.aiyicha.algorithm.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FTPUtil
 *
 * @author liyuan
 * @date 2018/9/5
 */
public class FTPUtil {

	private Logger logger = LoggerFactory.getLogger(getClass());

	protected FTPClient ftpClient = null;

	private String hostName;
	//ftp服务器端口号默认为21
	private Integer port;
	//ftp登录账号
	private String userName;
	//ftp登录密码
	private String password;

	public FTPUtil(String hostName, Integer port, String userName, String password) {
		this.hostName = hostName;
		this.port = port;
		this.userName = userName;
		this.password = password;
	}

	private boolean ftpConnection() {
		ftpClient = new FTPClient();
		ftpClient.setControlEncoding("utf-8");
		try {
			System.out.println("connecting...ftp服务器:" + this.hostName + ":" + this.port);
			logger.info("connecting...ftp服务器:" + this.hostName + ":" + this.port);
			//连接ftp服务器
			ftpClient.connect(this.hostName, this.port);
			System.out.println("login...ftp服务器:" + this.userName + ":" + this.password);
			logger.info("login...ftp服务器:" + this.userName + ":" + this.password);
			//登录ftp服务器
			ftpClient.login(this.userName, this.password);
			//是否成功登录服务器
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				logger.info("connect failed...ftp服务器:" + this.hostName + ":" + this.port);
				System.out.println("connect failed...ftp服务器:" + this.hostName + ":" + this.port);
				return false;
			}
			logger.info("connect successfu...ftp服务器:" + this.hostName + ":" + this.port);
			System.out.println("connect successfu...ftp服务器:" + this.hostName + ":" + this.port);
			return true;
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.out.printf(e.getMessage());
			return false;
		}
	}

	/**
	 * 上传文件
	 *
	 * @param pathname       ftp服务保存地址
	 * @param fileName       上传到ftp的文件名
	 * @param originfilename 待上传文件的名称（绝对地址） *
	 * @return
	 */
	public boolean uploadFile(String pathname, String fileName, String originfilename) {
		boolean flag = false;
		InputStream inputStream = null;
		System.out.println("开始上传文件");
		logger.info("开始上传文件");

		try {
			inputStream = new FileInputStream(new File(originfilename));
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			CreateDirecroty(pathname);
			ftpClient.makeDirectory(pathname);
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.storeFile(fileName, inputStream);
			inputStream.close();
			ftpClient.logout();
			flag = true;
			System.out.println("上传文件成功");
			logger.info("上传文件成功");
		} catch (Exception e) {
			System.out.println("上传文件失败");
			logger.error("上传文件失败" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	/**
	 * 上传文件
	 *
	 * @param pathname    ftp服务保存地址
	 * @param fileName    上传到ftp的文件名
	 * @param inputStream 输入文件流
	 * @return
	 */
	public boolean uploadFile(String pathname, String fileName, InputStream inputStream) {
		boolean flag = false;
		try {
			System.out.println("开始上传文件");
			logger.info("开始上传文件");
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			CreateDirecroty(pathname);
			ftpClient.makeDirectory(pathname);
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.storeFile(fileName, inputStream);
			inputStream.close();
			ftpClient.logout();
			flag = true;
			System.out.println("上传文件成功");
			logger.info("上传文件成功");
		} catch (Exception e) {
			System.out.println("上传文件失败");
			e.printStackTrace();
			logger.error("上传文件失败" + e.getMessage());
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

//	/**
//	 * @param files
//	 * @return
//	 */
//	public FunctionResult<List<String>> uploadFile(String pathNameUpload, String pathNameView, List<MultipartFile> files) {
//		List<String> fileNames = new ArrayList<>();
//		String fileName;
//		for (MultipartFile multipartFile : files) {
//			fileName = FileUtil.getFileNameByUUID(multipartFile);
//			fileNames.add(fileName);
//		}
//
//		return uploadFile(pathNameUpload, "", pathNameView, files, fileNames);
//	}

//	/**
//	 * 上传文件
//	 *
//	 * @param files
//	 * @param fileNames
//	 * @return
//	 */
//	public FunctionResult<List<String>> uploadFile(String
//														   pathNameUpload, String folder, String pathNameView, List<MultipartFile> files, List<String> fileNames) {
//		FunctionResult<List<String>> result = new FunctionResult<>(ResultCode.Failed);
//
//		String uploadPath = StringUtils.isBlank(folder) ? pathNameUpload : pathNameUpload + folder + "/";
//		String viewPath = StringUtils.isBlank(folder) ? pathNameView : pathNameView + folder + "/";
//
//		if (files.size() != fileNames.size()) {
//			result.setMessage("文件信息和文件名长度不匹配");
//			return result;
//		}
//
//		InputStream inputStream = null;
//		MultipartFile multipartFile;
//		String fileName;
//		List<String> outFileNames = new ArrayList<>();
//		System.out.println("开始上传文件");
//		logger.info("开始上传文件");
//		try {
//			boolean bReslut = ftpConnection();
//			if (!bReslut) {
//				result.setResultCode(ResultCode.FTPConnectFaild);
//				return result;
//			}
//			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//			CreateDirecroty(uploadPath);
//			ftpClient.makeDirectory(uploadPath);
//			ftpClient.changeWorkingDirectory(uploadPath);
//
//			for (int i = 0; i < files.size(); i++) {
//				multipartFile = files.get(i);
//				fileName = fileNames.get(i);
//				inputStream = multipartFile.getInputStream();
//				ftpClient.storeFile(fileName, inputStream);
//				outFileNames.add(viewPath + fileName);
//			}
//			inputStream.close();
//			ftpClient.logout();
//			System.out.println("上传文件成功");
//			logger.info("上传文件成功");
//			result.setValue(outFileNames);
//			return result.SuccessWithValue();
//		} catch (Exception e) {
//			System.out.println("上传文件失败");
//			e.printStackTrace();
//			logger.error("上传文件失败" + e.getMessage());
//			result.setResultCode(ResultCode.FileUploadFailed);
//			return result;
//		} finally {
//			if (ftpClient.isConnected()) {
//				try {
//					ftpClient.disconnect();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if (null != inputStream) {
//				try {
//					inputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

	//改变目录路径

	public boolean changeWorkingDirectory(String directory) {
		boolean flag = true;
		try {
			flag = ftpClient.changeWorkingDirectory(directory);
			if (flag) {
				System.out.println("进入文件夹" + directory + " 成功！");
				logger.info("进入文件夹" + directory + " 成功！");
			} else {
				System.out.println("进入文件夹" + directory + " 失败！开始创建文件夹");
				logger.info("进入文件夹" + directory + " 失败！开始创建文件夹");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			logger.error(ioe.getMessage());
		}
		return flag;
	}

	//创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
	public boolean CreateDirecroty(String remote) throws IOException {
		boolean success = true;
		String directory = remote + "/";
		// 如果远程目录不存在，则递归创建远程服务器目录
		if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
			int start = 0;
			int end = 0;
			if (directory.startsWith("/")) {
				start = 1;
			} else {
				start = 0;
			}
			end = directory.indexOf("/", start);
			String path = "";
			String paths = "";
			while (true) {
				String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
				path = path + "/" + subDirectory;
				if (!existFile(path)) {
					if (makeDirectory(subDirectory)) {
						changeWorkingDirectory(subDirectory);
					} else {
						System.out.println("创建目录[" + subDirectory + "]失败");
						logger.error("创建目录[" + subDirectory + "]失败");
						changeWorkingDirectory(subDirectory);
					}
				} else {
					changeWorkingDirectory(subDirectory);
				}

				paths = paths + "/" + subDirectory;
				start = end + 1;
				end = directory.indexOf("/", start);
				// 检查所有目录是否创建完毕
				if (end <= start) {
					break;
				}
			}
		}
		return success;
	}

	//判断ftp服务器文件是否存在
	public boolean existFile(String path) throws IOException {
		boolean flag = false;
		FTPFile[] ftpFileArr = ftpClient.listFiles(path);
		if (ftpFileArr.length > 0) {
			flag = true;
		}
		return flag;
	}

	//创建目录
	public boolean makeDirectory(String dir) {
		boolean flag = true;
		try {
			flag = ftpClient.makeDirectory(dir);
			if (flag) {
				System.out.println("创建文件夹" + dir + " 成功！");
				logger.info("创建文件夹" + dir + " 成功！");
			} else {
				System.out.println("创建文件夹" + dir + " 失败！");
				logger.error("创建文件夹" + dir + " 失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 下载文件 *
	 *
	 * @param pathname  FTP服务器文件目录 *
	 * @param filename  文件名称 *
	 * @param localpath 下载后的文件路径 *
	 * @return
	 */
	public boolean downloadFile(String pathname, String filename, String localpath) {
		boolean flag = false;
		OutputStream os = null;
		try {
			System.out.println("开始下载文件");
			//切换FTP目录
			ftpClient.changeWorkingDirectory(pathname);
			FTPFile[] ftpFiles = ftpClient.listFiles();
			for (FTPFile file : ftpFiles) {
				if (filename.equalsIgnoreCase(file.getName())) {
					File localFile = new File(localpath + "/" + file.getName());
					os = new FileOutputStream(localFile);
					ftpClient.retrieveFile(file.getName(), os);
					os.close();
				}
			}
			ftpClient.logout();
			flag = true;
			System.out.println("下载文件成功");
		} catch (Exception e) {
			System.out.println("下载文件失败");
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/**
	 * 下载文件 *
	 *
	 * @param pathname  FTP服务器文件目录 *
	 * @param filename  文件名称 *
	 * @return data
	 */
	public byte[] downloadFile(String pathname, String filename) {
		boolean flag = false;
		OutputStream os = null;
		byte[] data = null;
		InputStream inputStream;
		try {
			System.out.println("开始下载文件");
			boolean bReslut = ftpConnection();
			if (!bReslut) {
				throw new Exception("ftp连接失败！！");
			}
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			//切换FTP目录
			ftpClient.changeWorkingDirectory(pathname);
			FTPFile[] ftpFiles = ftpClient.listFiles();
			for (FTPFile file : ftpFiles) {
				if (filename.equalsIgnoreCase(file.getName())) {
					File tempDir = new File("/temp");
					if (!tempDir.exists()) {
						tempDir.mkdir();
					}
					File localFile = new File("/temp/" + file.getName());
					os = new FileOutputStream(localFile);
					ftpClient.retrieveFile(file.getName(), os);
					os.close();
					inputStream = new FileInputStream(localFile);
					byte[] temp = new byte[10000000];
					byte[] buffer = new byte[1024];
					int ch, index = 0;
					while ((ch = inputStream.read(buffer)) != -1) {
						System.arraycopy(buffer, 0, temp, index, ch);
						index += ch;
					}
					data = Arrays.copyOf(temp, index);
					localFile.delete();
					return data;
				}
			}
			ftpClient.logout();
			System.out.println("下载文件成功");
		} catch (Exception e) {
			System.out.println("下载文件失败");
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}

	/**
	 * 删除文件 *
	 *
	 * @param pathname FTP服务器保存目录 *
	 * @param filename 要删除的文件名称 *
	 * @return
	 */
	public boolean deleteFile(String pathname, String filename) {
		boolean flag = false;
		try {
			System.out.println("开始删除文件");
			//切换FTP目录
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.dele(filename);
			ftpClient.logout();
			flag = true;
			System.out.println("删除文件成功");
		} catch (Exception e) {
			System.out.println("删除文件失败");
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	public static void main(String[] args) throws Exception {
		FTPUtil ftpUtil = new FTPUtil("10.39.12.56", 21, "ftpuser", "passw0rd!");
		ftpUtil.ftpConnection();
		FTPFile[] files = ftpUtil.ftpClient.listDirectories("/");
		for (FTPFile file : files) {
			System.out.println(file.getName());
		}
		ftpUtil.ftpClient.logout();
	}
}
