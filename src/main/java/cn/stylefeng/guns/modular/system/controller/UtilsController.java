package cn.stylefeng.guns.modular.system.controller;

import java.util.*;
import java.text.SimpleDateFormat;

import cn.stylefeng.guns.core.util.NameComparator;
import cn.stylefeng.guns.core.util.SizeComparator;
import cn.stylefeng.guns.core.util.TypeComparator;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.json.simple.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

import java.io.IOException;

/**
 * @description:
 * @author: ZhangLu
 * @time: 2019/5/14 17:24
 */
@RestController
public class UtilsController {
    @Autowired
    private HttpServletRequest request;

    @Value("${uploadPrefix}")
    private String uploadPrefix;

    /**
     * 暂时没用
     * 上传图片
     *
     * @param mf
     * @param articleNum
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object UpLoadImg(@RequestParam(value = "myFileName") MultipartFile mf, @RequestParam(value = "articleNum") String articleNum) {
        System.out.println(articleNum);
        String realPath = request.getSession().getServletContext().getRealPath("upload");

        //获取源文件
        String filename = mf.getOriginalFilename();
        String[] names = filename.split("\\.");
        String tempNum = (int) (Math.random() * 100000) + "";
        String uploadFileName = tempNum + System.currentTimeMillis() + "." + names[names.length - 1];
        File targetFile = new File(realPath, uploadFileName);

        //开始从源文件拷贝到目标文件

        //传图片一步到位
        try {
            mf.transferTo(targetFile);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("data", request.getContextPath() + uploadFileName);
        return map;
    }

    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toJSONString();
    }

    /**
     * 在用
     * 上传图片
     *
     * @param request
     * @param response
     * @param dir
     * @throws Exception
     */
    @RequestMapping("/uploadJson")
    public void uploadJson(HttpServletRequest request, HttpServletResponse response, String dir) throws Exception {
        String articleNum = request.getParameter("articleNum");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

        //文件保存目录路径
        String savePath = uploadPrefix + "/attached/";

        //文件保存目录URL
        String saveUrl = uploadPrefix + "/attached";

        System.out.println(savePath);
        System.out.println(saveUrl);

        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,pdf");

        //最大文件大小
        long maxSize = 1000000;

        response.setContentType("text/html; charset=UTF-8");

        if (!ServletFileUpload.isMultipartContent(request)) {
            out.println(getError("请选择文件。"));
            return;
        }
        //检查目录
        File uploadDir = new File(savePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        if (!uploadDir.isDirectory()) {
            out.println(getError("上传目录不存在。"));
            return;
        }
        //检查目录写权限
        if (!uploadDir.canWrite()) {
            out.println(getError("上传目录没有写权限。"));
            return;
        }

        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if (!extMap.containsKey(dirName)) {
            out.println(getError("目录名不正确。"));
            return;
        }
        //创建文件夹
        savePath += dirName + "/";
        saveUrl += dirName + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        savePath += ymd + "/";
        saveUrl += ymd + "/";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        //此处是直接采用Spring的上传
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();
            String fileFullname = mf.getOriginalFilename();
            fileFullname = fileFullname.replace('&', 'a');
            fileFullname = fileFullname.replace(',', 'b');
            fileFullname = fileFullname.replace('，', 'c');
            //检查扩展名
            String fileExt = fileFullname.substring(fileFullname.lastIndexOf(".") + 1).toLowerCase();
            if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
                return;
            }

            String newFileName = articleNum + "_" + new Random().nextInt(1000) + "." + fileExt;

            File uploadFile = null;
            if (extMap.get("file").contains(fileExt)) {
                uploadFile = new File(savePath + fileFullname);
            } else {
                uploadFile = new File(savePath + newFileName);
            }
            try {
                FileCopyUtils.copy(mf.getBytes(), uploadFile);
                JSONObject obj = new JSONObject();
                obj.put("errno", 0);
                obj.put("url", request.getContextPath() + "/attached/" + dirName + "/" + ymd + "/" + newFileName);
                out.println(obj.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
                out.println(getError("上传文件失败。"));
                return;
            }
        }
    }

    @RequestMapping("/fileManagerJson")
    public void fileManagerJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        //根目录路径，可以指定绝对路径，比如 /var/www/attached/
        String rootPath = uploadPrefix + "/attached/";
        //根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
        String rootUrl = request.getContextPath() + "/attached/";
        //图片扩展名
        String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};

        String dirName = request.getParameter("dir");
        if (dirName != null) {
            if (!Arrays.<String>asList(new String[]{"image", "flash", "media", "file"}).contains(dirName)) {
                out.println("Invalid Directory name.");
                return;
            }
            rootPath += dirName + "/";
            rootUrl += dirName + "/";
            File saveDirFile = new File(rootPath);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }
        }
        //根据path参数，设置各路径和URL
        String path = request.getParameter("path") != null ? request.getParameter("path") : "";
        String currentPath = rootPath + path;
        String currentUrl = rootUrl + path;
        String currentDirPath = path;
        String moveupDirPath = "";
        if (!"".equals(path)) {
            String str = currentDirPath.substring(0, currentDirPath.length() - 1);
            moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
        }

        //排序形式，name or size or type
        String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

        //不允许使用..移动到上一级目录
        if (path.indexOf("..") >= 0) {
            out.println("Access is not allowed.");
            return;
        }
        //最后一个字符不是/
        if (!"".equals(path) && !path.endsWith("/")) {
            out.println("Parameter is not valid.");
            return;
        }
        //目录不存在或不是目录
        File currentPathFile = new File(currentPath);
        if (!currentPathFile.isDirectory()) {
            out.println("Directory does not exist.");
            return;
        }

        //遍历目录取的文件信息
        List<Hashtable> fileList = new ArrayList<Hashtable>();
        if (currentPathFile.listFiles() != null) {
            for (File file : currentPathFile.listFiles()) {
                Hashtable<String, Object> hash = new Hashtable<String, Object>();
                String fileName = file.getName();
                if (file.isDirectory()) {
                    hash.put("is_dir", true);
                    hash.put("has_file", (file.listFiles() != null));
                    hash.put("filesize", 0L);
                    hash.put("is_photo", false);
                    hash.put("filetype", "");
                } else if (file.isFile()) {
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    hash.put("is_dir", false);
                    hash.put("has_file", false);
                    hash.put("filesize", file.length());
                    hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
                    hash.put("filetype", fileExt);
                }
                hash.put("filename", fileName);
                hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
                fileList.add(hash);
            }
        }

        if ("size".equals(order)) {
            Collections.sort(fileList, new SizeComparator());
        } else if ("type".equals(order)) {
            Collections.sort(fileList, new TypeComparator());
        } else {
            Collections.sort(fileList, new NameComparator());
        }
        JSONObject result = new JSONObject();
        result.put("moveup_dir_path", moveupDirPath);
        result.put("current_dir_path", currentDirPath);
        result.put("current_url", currentUrl);
        result.put("total_count", fileList.size());
        result.put("file_list", fileList);

        out.println(result.toJSONString());
    }
}