package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.constant.dictmap.ArticleDict;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.system.mapstruct.ArticleTaskConverter;
import cn.stylefeng.guns.modular.system.model.*;
import cn.stylefeng.guns.modular.system.service.*;
import cn.stylefeng.guns.modular.system.warpper.ArticleTaskWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.date.DateTime.now;

/**
 * 文章任务表控制器
 *
 * @author fengshuonan
 * @Date 2019-05-21 15:41:29
 */
@Controller
@RequestMapping("/articleTask")
public class ArticleTaskController extends BaseController {

    private String PREFIX = "/system/articleTask/";

    @Autowired
    private IArticleTaskService articleTaskService;

    @Autowired
    private IArticleTypeService articleTypeService;

    @Autowired
    private INewArticleService newArticleService;

    @Autowired
    private IDefaultUnitPriceService defaultUnitPriceService;

    @Autowired
    private IUserService userService;

    /**
     * 跳转到文章任务表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "articleTask.html";
    }

    /**
     * 跳转到添加文章任务表
     */
    @RequestMapping("/articleTask_add")
    public String articleTaskAdd(Model model) {
        List<ArticleType> articleTypeList = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypeList", articleTypeList);
        return PREFIX + "articleTask_add.html";
    }

    /**
     * 跳转到修改文章任务表
     */
    @RequestMapping("/articleTask_update/{articleTaskId}")
    public String articleTaskUpdate(@PathVariable Integer articleTaskId, Model model) {
        ArticleTask articleTask = articleTaskService.selectById(articleTaskId);
        model.addAttribute("item", articleTask);
        LogObjectHolder.me().set(articleTask);
        List<ArticleType> articleTypeList = articleTypeService.selectList(null);
        model.addAttribute("articleTypeList", articleTypeList);
        return PREFIX + "articleTask_edit.html";
    }

    /**
     * 获取文章任务表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String title, @RequestParam(required = false) Integer timelinessCategory, @RequestParam(required = false) Integer taskStatus, @RequestParam(required = false) Integer type) {
        List<Map<String, Object>> tasks = articleTaskService.selectArticleTasks(title, timelinessCategory, 1, type);
        return new ArticleTaskWarpper(tasks).wrap();
    }

    /**
     * 新增文章任务表
     */
    @RequestMapping(value = "/add")
    @BussinessLog(value = "新增文章任务", key = "mainTitle", dict = ArticleDict.class)
    @ResponseBody
    public Object add(ArticleTask articleTask) {
        articleTaskService.insert(articleTask);
        return SUCCESS_TIP;
    }

    /**
     * 删除文章任务表
     */
    @RequestMapping(value = "/delete")
    @BussinessLog(value = "删除文章任务", key = "articleTaskId", dict = ArticleDict.class)
    @ResponseBody
    public Object delete(@RequestParam Integer articleTaskId) {
        // 判断是否可删除
        ArticleTask task = articleTaskService.selectById(articleTaskId);
        Integer taskStatus = task.getTaskStatus();
        if (taskStatus.equals(2)) {
            ResponseData responseData = new ResponseData();
            responseData.setSuccess(false);
            responseData.setCode(20000);
            responseData.setMessage("该任务已被领取，不可进行删除");
            return responseData;
        }
        articleTaskService.deleteById(articleTaskId);
        return SUCCESS_TIP;
    }

    /**
     * 修改文章任务表
     */
    @RequestMapping(value = "/update")
    @BussinessLog(value = "修改文章任务", key = "mainTitle", dict = ArticleDict.class)
    @ResponseBody
    public Object update(ArticleTask articleTask) {
        // 判断是否可编辑
        Integer articleTaskId = articleTask.getId();
        ArticleTask task = articleTaskService.selectById(articleTaskId);
        Integer taskStatus = task.getTaskStatus();
        if (taskStatus.equals(2)) {
            ResponseData responseData = new ResponseData();
            responseData.setSuccess(false);
            responseData.setCode(20000);
            responseData.setMessage("该任务已被领取，不可进行编辑");
            return responseData;
        }
        articleTaskService.updateById(articleTask);
        return SUCCESS_TIP;
    }

    /**
     * 文章任务表详情
     */
    @RequestMapping(value = "/detail/{articleTaskId}")
    @ResponseBody
    public Object detail(@PathVariable("articleTaskId") Integer articleTaskId) {
        return articleTaskService.selectById(articleTaskId);
    }

    @RequestMapping("/receivedArticle")
    public String receivedArticle() {
        return PREFIX + "receivedArticleTask.html";
    }

    /**
     * 获取已领取文章任务表列表
     */
    @RequestMapping(value = "/receivedArticleList")
    @ResponseBody
    public Object receivedArticleList(@RequestParam(required = false) String title, @RequestParam(required = false) Integer timelinessCategory, @RequestParam(required = false) Integer taskStatus, @RequestParam(required = false) Integer type) {
        List<Map<String, Object>> tasks = articleTaskService.selectArticleTasks(title, timelinessCategory, 2, type);
        return new ArticleTaskWarpper(tasks).wrap();
    }

    @RequestMapping("/available")
    public String indexAvailable() {
        return PREFIX + "availableArticleTask.html";
    }

    /**
     * @param
     * @Author: BaiYang
     * @Description: 查询可领取的文章任务
     * @Date: 2019/5/22
     * @return: java.lang.Object
     **/
    @RequestMapping(value = "/availableTasks")
    @ResponseBody
    public Object selectAvailableTasks(@RequestParam(required = false) String title, @RequestParam(required = false) Integer timelinessCategory, @RequestParam(required = false) Integer type) {
        List<Map<String, Object>> tasks = articleTaskService.selectArticleTasks(title, timelinessCategory, 1, type);
        return new ArticleTaskWarpper(tasks).wrap();
    }

    @RequestMapping(value = "/receive")
    @ResponseBody
    @Transactional
    public Object receiveArticleTask(@RequestParam Integer articleTaskId) {
        ResponseData responseData = new ResponseData();
        try {
            Integer loginUserId = ShiroKit.getUser().getId();
            User user = userService.selectById(loginUserId);
            // 获取任务信息
            ArticleTask task = articleTaskService.selectById(articleTaskId);
            Integer taskStatus = task.getTaskStatus();
            // 判断可否领取
            if (taskStatus.equals(2)) {
                responseData.setSuccess(false);
                responseData.setCode(20000);
                responseData.setMessage("该任务已被领取");
                return responseData;
            }
            //变更任务状态、添加领取人、时间
            task.setTaskStatus(2);
            task.setRecipientsId(loginUserId);
            task.setRecipients(user.getName());
            task.setPickUpTime(now());
            articleTaskService.updateById(task);

            // 实体转换
            NewArticle newArticle = ArticleTaskConverter.INSTANCE.articleTaskToNewArticle(task);
            // 完善信息
            Integer articleCount = newArticleService.selectArticleCount(loginUserId);
            DefaultUnitPrice defaultUnitPrice = defaultUnitPriceService.selectById(1);
            Long articleNum = (Long.valueOf(user.getUsernumber())) * 1000000 + 1;
            newArticle.setCreateTime(now());
            newArticle.setArticleNum(articleNum + articleCount);
            newArticle.setArticleStatus(1);
            newArticle.setPrefix(0);
            newArticle.setEditorNewFlag(1);
            newArticle.setCreateUserId(loginUserId);
            newArticle.setArticlePrice(defaultUnitPrice.getPurchasePrice());
            newArticle.setNewMainTitle(task.getMainTitle());
            newArticle.setNewSubheading(task.getSubheading());
            newArticle.setArticleTypeId(task.getType());
            // 领取的任务变更为文编的草稿
            boolean b2 = newArticleService.insert(newArticle);

            if (b2) {
                return SUCCESS_TIP;
            }

            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            responseData.setSuccess(false);
            responseData.setCode(20000);
            responseData.setMessage("服务器运行异常");
            return responseData;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            responseData.setSuccess(false);
            responseData.setCode(20000);
            responseData.setMessage("服务器运行异常!");
            return responseData;
        }

    }

    /**
     * 导入
     * @param file
     * @return
     */
    @PostMapping("/import")
    public String addArticleTask(@RequestParam("file") MultipartFile file) {
        ResponseData responseData = new ResponseData();
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = articleTaskService.batchImport(fileName, file);
            if (a) {
                return REDIRECT + "/articleTask";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseData.setSuccess(false);
        responseData.setCode(20000);
        responseData.setMessage("服务器运行异常");
        return "服务器运行异常";
    }

    /**
     * 下载文章任务模版
     * @param response
     * @param request
     */
    @RequestMapping("/downloadExcel")
    @ResponseBody
    public void downloadExcel(HttpServletResponse response, HttpServletRequest request) {
        try {
            Workbook workbook = new HSSFWorkbook();
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/x-download");

            String filedisplay = "文章任务模版.xls";

            filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename="+ filedisplay);

            Sheet sheet = workbook.createSheet("文章任务模版");
            // 第三步，在sheet中添加表头第0行
            Row row = sheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            CellStyle style = workbook.createCellStyle();
            style.setAlignment(CellStyle.ALIGN_CENTER);

            Cell cell = row.createCell(0);
            cell.setCellValue("主标题");
            cell.setCellStyle(style);
            sheet.setColumnWidth(0, (25 * 256));

            cell = row.createCell(1);
            cell.setCellValue("副标题");
            cell.setCellStyle(style);
            sheet.setColumnWidth(1, (20 * 256));

            cell = row.createCell(2);
            cell.setCellValue("时效性类别");
            cell.setCellStyle(style);
            sheet.setColumnWidth(2, (30 * 256));

            cell = row.createCell(3);
            cell.setCellValue("文章分类");
            cell.setCellStyle(style);
            sheet.setColumnWidth(3, (15 * 256));

            cell = row.createCell(4);
            cell.setCellValue("url地址");
            cell.setCellStyle(style);
            sheet.setColumnWidth(4, (50 * 256));

            // 第六步，将文件存到指定位置
            try
            {
                OutputStream out = response.getOutputStream();
                workbook.write(out);
                out.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
