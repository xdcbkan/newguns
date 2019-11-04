package cn.stylefeng.guns.modular.system.controller;

import cn.afterturn.easypoi.word.WordExportUtil;
import cn.hutool.core.lang.Assert;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.system.mapstruct.ArticleConverter;
import cn.stylefeng.guns.modular.system.model.*;
import cn.stylefeng.guns.modular.system.service.*;
import cn.stylefeng.guns.modular.system.warpper.NewArticleWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.hutool.core.date.DateTime.now;

/**
 * 文章发布控制器
 *
 * @author fengshuonan
 * @Date 2019-05-13 13:18:19
 */
@Controller
@RequestMapping("/newArticle")
public class NewArticleController extends BaseController {

    private String PREFIX = "/system/newArticle/";

    private String SHENHE1 = "/system/examine1NewArticle/";

    private String SHENHE1RECEIVE = "/system/examine1ReceiveArticle/";

    private String SHENHE2 = "/system/examine2NewArticle/";

    private String SHENHE2RECEIVE = "/system/examine2ReceiveArticle/";

    private String LAYOUT = "/system/layoutNewArticle/";

    private String LAYOUTRECEIVE = "/system/layoutReceiveArticle/";

    private String LAYOUTCOMPLETED = "/system/layoutCompletedArticle/";

    private String OPERATOR = "/system/operatorNewArticle/";

    private String OPERATORRECEIVE = "/system/operatorReceiveArticle/";

    private String ADDRESS = "/system/operatorReceiveArticle/";

    private String HISTORY = "/system/historicalArticle/";

    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    private static final String regEx_html = "<[^>]+>";
    private static final String regEx_space = "\\s*|\t|\r|\n";
    private static final String regEx_w = "<w[^>]*?>[\\s\\S]*?<\\/w[^>]*?>";
    private static final String regEx_p = "<p[^>]+>";

    @Autowired
    private INewArticleService newArticleService;

    @Autowired
    private IArticleTypeService articleTypeService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IDefaultUnitPriceService defaultUnitPriceService;

    @Autowired
    private IArticleReceiveService iArticleReceiveService;

    @Autowired
    private IHistoricalArticleService historicalArticleService;

    @Autowired
    private IArticlePublishAddressService articlePublishAddressService;

    @Autowired
    private IProjectTypeService projectTypeService;

    @Autowired
    private IArticleRecordService articleRecordService;


    /**
     * 跳转到文章历史版本记录首页
     */
    @RequestMapping("/history")
    public String index() {
        return HISTORY + "historicalArticle.html";
    }

    /**
     * 跳转到添加文章发布地址
     */
    @RequestMapping("/articlePublishAddress_add/{articlePublishAddressId}")
    public String articlePublishAddressAdd(@PathVariable Integer articlePublishAddressId, Model model) {
        model.addAttribute("item", articlePublishAddressId);
        return ADDRESS + "articlePublishAddress_add.html";
    }

    /**
     * 跳转到修改文章发布地址
     */
    @RequestMapping("/articlePublishAddress_update/{articlePublishAddressId}")
    public String articlePublishAddressUpdate(@PathVariable Integer articlePublishAddressId, Model model) {
        ArticlePublishAddress articlePublishAddress = articlePublishAddressService.selectAddress(articlePublishAddressId);
        model.addAttribute("item", articlePublishAddress);
        LogObjectHolder.me().set(articlePublishAddress);
        return ADDRESS + "articlePublishAddress_edit.html";
    }

    /**
     * 跳转到文编发布首页
     */
    @RequestMapping("/edit")
    public String index(Model model) {
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        return PREFIX + "newArticle.html";
    }

    /**
     * 跳转到审核1可领取首页
     */
    @RequestMapping("/examine1")
    public String examine1(Model model) {
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        return SHENHE1 + "newArticle.html";
    }

    /**
     * 跳转到审核2可领取首页
     */
    @RequestMapping("/examine2")
    public String examine2(Model model) {
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        return SHENHE2 + "newArticle.html";
    }

    /**
     * 跳转到配图可领取首页
     */
    @RequestMapping("/layout")
    public String layout(Model model) {
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        return LAYOUT + "newArticle.html";
    }

    /**
     * 跳转到运营可领取首页
     */
    @RequestMapping("/operator")
    public String operator(Model model) {
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        return OPERATOR + "newArticle.html";
    }

    /**
     * 跳转到审核1已领取首页
     */
    @RequestMapping("/examine1ReceiveArticle")
    public String examine1Receive(Model model) {
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        return SHENHE1RECEIVE + "newArticle.html";
    }

    /**
     * 跳转到审核2已领取首页
     */
    @RequestMapping("/examine2ReceiveArticle")
    public String examine2Receive(Model model) {
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        return SHENHE2RECEIVE + "newArticle.html";
    }

    /**
     * 跳转到配图已领取首页
     */
    @RequestMapping("/layoutReceiveArticle")
    public String layoutReceive(Model model) {
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        return LAYOUTRECEIVE + "newArticle.html";
    }

    /**
     * 跳转到配图已完成首页
     */
    @RequestMapping("/layoutCompletedArticle")
    public String layoutCompleted(Model model) {
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        return LAYOUTCOMPLETED + "newArticle.html";
    }

    /**
     * 跳转到运营已领取首页
     */
    @RequestMapping("/operateReceiveArticle")
    public String operatorReceive(Model model) {
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        return OPERATORRECEIVE + "newArticle.html";
    }

    /**
     * 跳转到添加文章发布
     */
    @RequestMapping("/newArticle_add")
    public String newArticleAdd(Model model) {
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        return PREFIX + "newArticle_add.html";
    }


    /**
     * 跳转到修改文章发布
     */
    @RequestMapping("/newArticle_update/{newArticleId}")
    public String newArticleUpdate(@PathVariable Integer newArticleId, Model model) {
        NewArticle newArticle = newArticleService.selectById(newArticleId);
        model.addAttribute("item", newArticle);
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        LogObjectHolder.me().set(newArticle);
        return SHENHE1RECEIVE + "newArticle_edit.html";
    }

    /**
     * 审核1查看文章展示效果
     */
    @RequestMapping("/newArticle_view/{newArticleId}")
    public String newArticleView(@PathVariable Integer newArticleId, Model model) {
        NewArticle newArticle = newArticleService.selectById(newArticleId);
        model.addAttribute("item", newArticle);
        return SHENHE1RECEIVE + "newArticle_view.html";
    }

    /**
     * 审核2跳转到修改文章发布
     */
    @RequestMapping("/newArticle2_update/{newArticleId}")
    public String newArticle2Update(@PathVariable Integer newArticleId, Model model) {
        NewArticle newArticle = newArticleService.selectById(newArticleId);
        model.addAttribute("item", newArticle);
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        LogObjectHolder.me().set(newArticle);
        return SHENHE2RECEIVE + "newArticle_edit.html";
    }

    /**
     * 配图跳转到配图
     */
    @RequestMapping("/layout_update/{newArticleId}")
    public String layoutUpdate(@PathVariable Integer newArticleId, Model model) {
        NewArticle newArticle = newArticleService.selectById(newArticleId);
        List<ProjectType> projectTypes = projectTypeService.selectList(null);
        model.addAttribute("item", newArticle);
        model.addAttribute("projectTypes", projectTypes);
        LogObjectHolder.me().set(newArticle);
        return LAYOUTRECEIVE + "newArticle_edit.html";
    }

    /**
     * 运营跳转到详情
     */
    @RequestMapping("/operator_update/{newArticleId}")
    public String operatorUpdate(@PathVariable Integer newArticleId, Model model) {
        NewArticle newArticle = newArticleService.selectById(newArticleId);
        model.addAttribute("item", newArticle);
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        LogObjectHolder.me().set(newArticle);
        return OPERATORRECEIVE + "newArticle_edit.html";
    }


    /**
     * 文编跳转到修改文章发布
     */
    @RequestMapping("/editor_update/{newArticleId}")
    public String editorUpdate(@PathVariable Integer newArticleId, Model model) {
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        NewArticle newArticle = newArticleService.selectById(newArticleId);
        model.addAttribute("item", newArticle);
        LogObjectHolder.me().set(newArticle);
        return PREFIX + "newArticle_edit.html";
    }

    /**
     * 管理查看文章详情
     */
    @RequestMapping("/update/{newArticleId}")
    public String update(@PathVariable Integer newArticleId, Model model) {
        NewArticle newArticle = newArticleService.selectById(newArticleId);
        model.addAttribute("item", newArticle);
        LogObjectHolder.me().set(newArticle);
        return HISTORY + "newArticle_view.html";
    }

    /**
     * 获取文章发布列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String condition, @RequestParam(required = false) Integer articleTypeId, @RequestParam(required = false) Integer articleStatus, @RequestParam(required = false) Integer prescription) {
        Integer loginUserId = ShiroKit.getUser().getId();
        System.out.println("测试数字传入" + articleStatus + ";" + articleTypeId);
        if (articleStatus != null && articleStatus == 0) {
            Integer[] strArray = {6, 7};
            List<Map<String, Object>> list = newArticleService.selectArticleList(condition, articleTypeId, loginUserId, prescription, strArray);
            return new NewArticleWarpper(list).wrap();
        }
        if (articleStatus != null && articleStatus == 8) {
            Integer[] strArray = {8, 14};
            List<Map<String, Object>> list = newArticleService.selectArticleList(condition, articleTypeId, loginUserId, prescription, strArray);
            return new NewArticleWarpper(list).wrap();
        }
        if (articleStatus != null && articleStatus == 20) {
            List<Map<String, Object>> list = newArticleService.selectArticleList(condition, articleTypeId, loginUserId, prescription, null);
            return new NewArticleWarpper(list).wrap();
        }
        if (articleStatus == null && articleTypeId == null) {
            List<Map<String, Object>> list = newArticleService.selectArticleList(condition, 0, loginUserId, prescription, null);
            return new NewArticleWarpper(list).wrap();
        }
        Integer[] strArray = {articleStatus};
        List<Map<String, Object>> list = newArticleService.selectArticleList(condition, articleTypeId, loginUserId, prescription, strArray);
        return new NewArticleWarpper(list).wrap();
    }

    /**
     * 新增文章发布
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(NewArticle newArticle) {
        Integer loginUserId = ShiroKit.getUser().getId();
        User user = userService.selectById(loginUserId);
        Integer articleCount = newArticleService.selectArticleCount(loginUserId);
        DefaultUnitPrice defaultUnitPrice = defaultUnitPriceService.selectById(1);
        Long articleNum = (Long.valueOf(user.getUsernumber())) * 1000000 + 1;
        newArticle.setCreateTime(now());
        newArticle.setUpdateTime(now());
        newArticle.setArticleNum(articleNum + articleCount);
        newArticle.setArticleStatus(2);
        newArticle.setPrefix(1);
        newArticle.setEditorNewFlag(1);
        newArticle.setCreateUserId(loginUserId);
        newArticle.setArticlePrice(defaultUnitPrice.getPurchasePrice());
        newArticle.setReleaseType(2);
        newArticle.setMainTitle(newArticle.getNewMainTitle());
        newArticle.setSubheading(newArticle.getNewSubheading());
        Boolean a = newArticleService.insert(newArticle);
        if (a == true) {
            articleRecordService.addArticleRecord(newArticle.getArticleNum(), "提交");
        }
        return SUCCESS_TIP;
    }

    /**
     * 文章保存草稿
     */
    @RequestMapping(value = "/addDraft")
    @ResponseBody
    public Object addDraft(NewArticle newArticle) {
        Integer loginUserId = ShiroKit.getUser().getId();
        User user = userService.selectById(loginUserId);
        Integer articleCount = newArticleService.selectArticleCount(loginUserId);
        DefaultUnitPrice defaultUnitPrice = defaultUnitPriceService.selectById(1);
        Long articleNum = (Long.valueOf(user.getUsernumber())) * 1000000 + 1;
        newArticle.setCreateTime(now());
        newArticle.setUpdateTime(now());
        newArticle.setArticleNum(articleNum + articleCount);
        newArticle.setArticleStatus(1);
        newArticle.setPrefix(0);
        newArticle.setEditorNewFlag(1);
        newArticle.setCreateUserId(loginUserId);
        newArticle.setArticlePrice(defaultUnitPrice.getPurchasePrice());
        newArticle.setReleaseType(2);
        Boolean a = newArticleService.insert(newArticle);
        if (a == true) {
            articleRecordService.addArticleRecord(newArticle.getArticleNum(), "保存");
        }
        return SUCCESS_TIP;
    }

    /**
     * 删除文章发布
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer newArticleId) {
        newArticleService.deleteById(newArticleId);
        return SUCCESS_TIP;
    }

    /**
     * 修改文章发布
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(NewArticle newArticle) {
        NewArticle newArticle1 = newArticleService.selectById(newArticle.getId());
        if (newArticle1.getArticleStatus() != 1) {
            HistoricalArticle historicalArticle = ArticleConverter.INSTANCE.newArticleToHistory(newArticle1);
            historicalArticle.setEditorNewFlag(0);
            historicalArticleService.insert(historicalArticle);
            Integer articleStatus = newArticle1.getArticleStatus();
            Integer prefix = newArticle1.getPrefix();
            if (articleStatus == 6 && prefix == 1) {
                newArticle.setArticleStatus(2);
                newArticle.setPrefix(2);
                newArticle.setExamine1Id(null);
                newArticle.setUpdateTime(now());
                newArticleService.updateById(newArticle);
                return SUCCESS_TIP;
            }
            if (articleStatus == 6 && prefix == 2) {
                newArticle.setArticleStatus(2);
                newArticle.setPrefix(3);
                newArticle.setExamine1Id(null);
                newArticle.setUpdateTime(now());
                newArticleService.updateById(newArticle);
                return SUCCESS_TIP;
            }
            if (articleStatus == 7 && prefix != 4) {
                newArticle.setArticleStatus(3);
                newArticle.setPrefix(4);
                newArticle.setExamine2Id(null);
                newArticle.setUpdateTime(now());
                newArticleService.updateById(newArticle);
                return SUCCESS_TIP;
            }
            if (articleStatus == 7 && prefix == 4) {
                newArticle.setArticleStatus(3);
                newArticle.setPrefix(5);
                newArticle.setExamine2Id(null);
                newArticle.setUpdateTime(now());
                newArticleService.updateById(newArticle);
                return SUCCESS_TIP;
            }
        }
        newArticle.setArticleStatus(2);
        newArticle.setPrefix(1);
        newArticle.setUpdateTime(now());
        newArticle.setMainTitle(newArticle.getNewMainTitle());
        newArticle.setSubheading(newArticle.getNewSubheading());
        Boolean a = newArticleService.updateById(newArticle);
        if (a == true) {
            articleRecordService.addArticleRecord(newArticle1.getArticleNum(), "提交");
        }
        return SUCCESS_TIP;
    }

    /**
     * 文章发布详情
     */
    @RequestMapping(value = "/detail/{newArticleId}")
    @ResponseBody
    public Object detail(@PathVariable("newArticleId") Integer newArticleId) {
        return newArticleService.selectById(newArticleId);
    }

    /**
     * 审核领取
     */
    @RequestMapping(value = "/examine1receive")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Object examine1receive(@RequestParam Integer newArticleId) {
        if (ShiroKit.hasRole(Const.EXAMINER_ONE_NAME)) {
            Integer userId = ShiroKit.getUser().getId();
            User user = userService.selectById(userId);
            NewArticle newArticle = newArticleService.selectById(newArticleId);
            Integer count = user.getResiduereceivenum();
            if (count == 0) {
                ResponseData responseData = new ResponseData();
                responseData.setMessage("当前审核1账号可领取文章数为0，请明日重置后再领取");
                responseData.setCode(20000);
                return responseData;
            }
            if (newArticle.getArticleStatus() != 2) {
                ResponseData responseData = new ResponseData();
                responseData.setMessage("该文章已被其他审核1人员领取，请刷新列表后选择其他文章");
                responseData.setCode(20000);
                return responseData;
            }
//        领取文章减少一次剩余次数
            userService.setUserReceiveNum(userId);
//        变更文章信息为已领取
            newArticleService.receiveArticle(newArticleId, userId, null, now(), 4);
//        领取记录表插入领取记录
            ArticleReceive articleReceive = new ArticleReceive();
            articleReceive.setArticleId(newArticleId);
            articleReceive.setOperateTime(now());
            articleReceive.setUserId(userId);
            Boolean a = iArticleReceiveService.insert(articleReceive);
            if (a == true) {
                articleRecordService.addArticleRecord(newArticle.getArticleNum(), "领取");
            }
            return SUCCESS_TIP;
        }
        if (ShiroKit.hasRole(Const.EXAMINER_TWO_NAME)) {

            Integer userId = ShiroKit.getUser().getId();
            User user = userService.selectById(userId);
            Integer count = user.getResiduereceivenum();
            NewArticle newArticle = newArticleService.selectById(newArticleId);
            if (count == 0) {
                ResponseData responseData = new ResponseData();
                responseData.setMessage("当前审核2账号可领取文章数为0，请明日重置后再领取");
                responseData.setCode(20000);
                return responseData;
            }
            if (newArticle.getArticleStatus() != 3) {
                ResponseData responseData = new ResponseData();
                responseData.setMessage("该文章已被其他审核2人员领取，请刷新列表后选择其他文章");
                responseData.setCode(20000);
                return responseData;
            }
//        领取文章减少一次剩余次数
            userService.setUserReceiveNum(userId);
//        变更文章信息为已领取
            newArticleService.receiveArticle(newArticleId, null, userId, now(), 5);
//        领取记录表插入领取记录
            ArticleReceive articleReceive = new ArticleReceive();
            articleReceive.setArticleId(newArticleId);
            articleReceive.setOperateTime(now());
            articleReceive.setUserId(userId);
            Boolean a = iArticleReceiveService.insert(articleReceive);
            if (a == true) {
                articleRecordService.addArticleRecord(newArticle.getArticleNum(), "领取");
            }
            return SUCCESS_TIP;
        } else {
            ResponseData responseData = new ResponseData();
            responseData.setMessage("您不是审核，暂无权限领取文章");
            responseData.setCode(30000);
            return responseData;
        }
    }

    /**
     * 批量审核领取
     */
    @RequestMapping(value = "/examine1receives")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Object examine1receives(@RequestParam String newArticleIds) {
        String[] newArticleIdList = newArticleIds.split(",");
        if (ShiroKit.hasRole(Const.EXAMINER_ONE_NAME)) {
            for (int i = 0;i < newArticleIdList.length; i++){
                Integer newArticleId = Integer.valueOf(newArticleIdList[i]);

                Integer userId = ShiroKit.getUser().getId();
                User user = userService.selectById(userId);
                NewArticle newArticle = newArticleService.selectById(newArticleId);
                Integer count = user.getResiduereceivenum();
                if (count == 0) {
                    ResponseData responseData = new ResponseData();
                    responseData.setMessage("当前审核1账号可领取文章数为0，请明日重置后再领取");
                    responseData.setCode(20000);
                    return responseData;
                }
                if (newArticle.getArticleStatus() != 2) {
                    ResponseData responseData = new ResponseData();
                    responseData.setMessage("该文章已被其他审核1人员领取，请刷新列表后选择其他文章");
                    responseData.setCode(20000);
                    return responseData;
                }
                //领取文章减少一次剩余次数
                userService.setUserReceiveNum(userId);
                //变更文章信息为已领取
                newArticleService.receiveArticle(newArticleId, userId, null, now(), 4);
                //领取记录表插入领取记录
                ArticleReceive articleReceive = new ArticleReceive();
                articleReceive.setArticleId(newArticleId);
                articleReceive.setOperateTime(now());
                articleReceive.setUserId(userId);
                Boolean a = iArticleReceiveService.insert(articleReceive);
                if (a == true) {
                    articleRecordService.addArticleRecord(newArticle.getArticleNum(), "领取");
                }
            }
            return SUCCESS_TIP;
        }
        if (ShiroKit.hasRole(Const.EXAMINER_TWO_NAME)) {
            for (int i = 0;i < newArticleIdList.length; i++){
                Integer newArticleId = Integer.valueOf(newArticleIdList[i]);

                Integer userId = ShiroKit.getUser().getId();
                User user = userService.selectById(userId);
                Integer count = user.getResiduereceivenum();
                NewArticle newArticle = newArticleService.selectById(newArticleId);
                if (count == 0) {
                    ResponseData responseData = new ResponseData();
                    responseData.setMessage("当前审核2账号可领取文章数为0，请明日重置后再领取");
                    responseData.setCode(20000);
                    return responseData;
                }
                if (newArticle.getArticleStatus() != 3) {
                    ResponseData responseData = new ResponseData();
                    responseData.setMessage("该文章已被其他审核2人员领取，请刷新列表后选择其他文章");
                    responseData.setCode(20000);
                    return responseData;
                }
                //领取文章减少一次剩余次数
                userService.setUserReceiveNum(userId);
                //变更文章信息为已领取
                newArticleService.receiveArticle(newArticleId, null, userId, now(), 5);
                //领取记录表插入领取记录
                ArticleReceive articleReceive = new ArticleReceive();
                articleReceive.setArticleId(newArticleId);
                articleReceive.setOperateTime(now());
                articleReceive.setUserId(userId);
                Boolean a = iArticleReceiveService.insert(articleReceive);
                if (a == true) {
                    articleRecordService.addArticleRecord(newArticle.getArticleNum(), "领取");
                }

            }
            return SUCCESS_TIP;
        }
        ResponseData responseData = new ResponseData();
        responseData.setMessage("您不是审核，暂无权限领取文章");
        responseData.setCode(30000);
        return responseData;

    }

    /**
     * 配图运营领取
     */
    @RequestMapping(value = "/layoutReceive")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Object layoutReceive(@RequestParam Integer newArticleId) {
        if (ShiroKit.hasRole(Const.LAYOUT_NAME)) {
            Integer userId = ShiroKit.getUser().getId();
            User user = userService.selectById(userId);
            Integer count = user.getResiduereceivenum();
            NewArticle newArticle = newArticleService.selectById(newArticleId);
            if (count == 0) {
                ResponseData responseData = new ResponseData();
                responseData.setMessage("当前配图账号可领取文章数为0，请明日重置后再领取");
                responseData.setCode(20000);
                return responseData;
            }
            if (newArticle.getArticleStatus() != 9) {
                ResponseData responseData = new ResponseData();
                responseData.setMessage("该文章已被其他配图人员领取，请刷新列表后选择其他文章");
                responseData.setCode(20000);
                return responseData;
            }
//        领取文章减少一次剩余次数
            userService.setUserReceiveNum(userId);
//        变更文章信息为已领取
            newArticleService.setReceiveArticle(newArticleId, userId, null, now(), 10);
//        领取记录表插入领取记录
            ArticleReceive articleReceive = new ArticleReceive();
            articleReceive.setArticleId(newArticleId);
            articleReceive.setOperateTime(now());
            articleReceive.setUserId(userId);
            iArticleReceiveService.insert(articleReceive);
            articleRecordService.addArticleRecord(newArticle.getArticleNum(), "领取");
            return SUCCESS_TIP;
        }
        if (ShiroKit.hasRole(Const.OPERATOR_NAME)) {
            NewArticle newArticle = newArticleService.selectById(newArticleId);
            Integer userId = ShiroKit.getUser().getId();
            User user = userService.selectById(userId);
            Integer count = user.getResiduereceivenum();
            if (count == 0) {
                ResponseData responseData = new ResponseData();
                responseData.setMessage("当前运营账号可领取文章数为0，请明日重置后再领取");
                responseData.setCode(20000);
                return responseData;
            }
            if (newArticle.getArticleStatus() != 11) {
                ResponseData responseData = new ResponseData();
                responseData.setMessage("该文章已被其他运营人员领取，请刷新列表后选择其他文章");
                responseData.setCode(20000);
                return responseData;
            }
//        领取文章减少一次剩余次数
            userService.setUserReceiveNum(userId);
//        变更文章信息为已领取
            newArticleService.setReceiveArticle(newArticleId, null, userId, now(), 12);
//        领取记录表插入领取记录
            ArticleReceive articleReceive = new ArticleReceive();
            articleReceive.setArticleId(newArticleId);
            articleReceive.setOperateTime(now());
            articleReceive.setUserId(userId);
            iArticleReceiveService.insert(articleReceive);
            articleRecordService.addArticleRecord(newArticle.getArticleNum(), "领取");
            return SUCCESS_TIP;
        } else {
            ResponseData responseData = new ResponseData();
            responseData.setMessage("您不是配图或运营，暂无权限领取文章");
            responseData.setCode(30000);
            return responseData;
        }
    }

    /**
     * 配图运营批量领取
     */
    @RequestMapping(value = "/layoutReceives")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Object layoutReceives(@RequestParam String newArticleIds) {
        String[] newArticleIdList = newArticleIds.split(",");
        if (ShiroKit.hasRole(Const.LAYOUT_NAME)) {
            for (int i = 0; i < newArticleIdList.length; i++) {
                Integer newArticleId = Integer.valueOf(newArticleIdList[i]);
                Integer userId = ShiroKit.getUser().getId();
                User user = userService.selectById(userId);
                Integer count = user.getResiduereceivenum();
                NewArticle newArticle = newArticleService.selectById(newArticleId);
                if (count == 0) {
                    ResponseData responseData = new ResponseData();
                    responseData.setMessage("当前配图账号可领取文章数为0，请明日重置后再领取");
                    responseData.setCode(20000);
                    return responseData;
                }
                if (newArticle.getArticleStatus() != 9) {
                    ResponseData responseData = new ResponseData();
                    responseData.setMessage("该文章已被其他配图人员领取，请刷新列表后选择其他文章");
                    responseData.setCode(20000);
                    return responseData;
                }
                //        领取文章减少一次剩余次数
                userService.setUserReceiveNum(userId);
                //        变更文章信息为已领取
                newArticleService.setReceiveArticle(newArticleId, userId, null, now(), 10);
                //        领取记录表插入领取记录
                ArticleReceive articleReceive = new ArticleReceive();
                articleReceive.setArticleId(newArticleId);
                articleReceive.setOperateTime(now());
                articleReceive.setUserId(userId);
                iArticleReceiveService.insert(articleReceive);
                articleRecordService.addArticleRecord(newArticle.getArticleNum(), "领取");
            }
            return SUCCESS_TIP;
        }
        if (ShiroKit.hasRole(Const.OPERATOR_NAME)) {
            for (int i = 0; i < newArticleIdList.length; i++) {
                Integer newArticleId = Integer.valueOf(newArticleIdList[i]);
                NewArticle newArticle = newArticleService.selectById(newArticleId);
                Integer userId = ShiroKit.getUser().getId();
                User user = userService.selectById(userId);
                Integer count = user.getResiduereceivenum();
                if (count == 0) {
                    ResponseData responseData = new ResponseData();
                    responseData.setMessage("当前运营账号可领取文章数为0，请明日重置后再领取");
                    responseData.setCode(20000);
                    return responseData;
                }
                if (newArticle.getArticleStatus() != 11) {
                    ResponseData responseData = new ResponseData();
                    responseData.setMessage("该文章已被其他运营人员领取，请刷新列表后选择其他文章");
                    responseData.setCode(20000);
                    return responseData;
                }
    //        领取文章减少一次剩余次数
                userService.setUserReceiveNum(userId);
    //        变更文章信息为已领取
                newArticleService.setReceiveArticle(newArticleId, null, userId, now(), 12);
    //        领取记录表插入领取记录
                ArticleReceive articleReceive = new ArticleReceive();
                articleReceive.setArticleId(newArticleId);
                articleReceive.setOperateTime(now());
                articleReceive.setUserId(userId);
                iArticleReceiveService.insert(articleReceive);
                articleRecordService.addArticleRecord(newArticle.getArticleNum(), "领取");
            }
            return SUCCESS_TIP;
        } else {
            ResponseData responseData = new ResponseData();
            responseData.setMessage("您不是配图或运营，暂无权限领取文章");
            responseData.setCode(30000);
            return responseData;
        }
    }

    /**
     * 获取文章发布列表
     */
    @RequestMapping(value = "/articleList")
    @ResponseBody
    public Object getArticleList(@RequestParam(required = false) String condition, @RequestParam(required = false) Integer prescription, @RequestParam(required = false) Integer articleTypeNum) {
        Integer loginUserId = ShiroKit.getUser().getId();
        User user = userService.selectById(loginUserId);

        if (ShiroKit.hasRole(Const.ADMIN_NAME)) {
            List<Map<String, Object>> list = newArticleService.getArticleList(condition, prescription, null, null);
            return new NewArticleWarpper(list).wrap();
        } else {
            String articleType = user.getArticletypeid();
//            if (!ShiroKit.hasRole(Const.OPERATOR_NAME) && ToolUtil.isEmpty(articleType)) {
//                List<Map<String, Object>> list = newArticleService.getArticleList(condition, prescription, 0, null);
//                return new NewArticleWarpper(list).wrap();
//            }
            String[] strArray = null;
            if (ShiroKit.hasRole(Const.EXAMINER_ONE_NAME)) {
                if (ToolUtil.isNotEmpty(articleType)) {
                    strArray = articleType.split(",");
                }
                List<Map<String, Object>> list = newArticleService.getArticleList(condition, prescription, 2, strArray);
                return new NewArticleWarpper(list).wrap();
            }
            if (ShiroKit.hasRole(Const.EXAMINER_TWO_NAME)) {
                if (ToolUtil.isNotEmpty(articleType)) {
                    strArray = articleType.split(",");
                }
                List<Map<String, Object>> list = newArticleService.getArticleList(condition, prescription, 3, strArray);
                return new NewArticleWarpper(list).wrap();
            }
            if (ShiroKit.hasRole(Const.LAYOUT_NAME)) {
                if (ToolUtil.isNotEmpty(articleType)) {
                    strArray = articleType.split(",");
                }
                List<Map<String, Object>> list = newArticleService.getArticleList(condition, prescription, 9, strArray);
                return new NewArticleWarpper(list).wrap();
            }
            if (ShiroKit.hasRole(Const.OPERATOR_NAME)) {
                List<Map<String, Object>> list;
                // 可运营项目类型
                String projectTypeId = user.getProjectTypeId();
                String[] projectTypeArray = null;
                String[] articleTypeArray = null;
                if (ToolUtil.isNotEmpty(projectTypeId)) {
                    projectTypeArray = projectTypeId.split(",");
                }
                if (ToolUtil.isNotEmpty(articleType)) {
                    articleTypeArray = articleType.split(",");
                }
                list = newArticleService.getOperatorAvailableArticleList(condition, prescription, projectTypeArray, articleTypeArray);
//                if (ToolUtil.isEmpty(projectTypeId)) {
//                    list = newArticleService.getOperatorArticleList(condition, prescription, null, articleTypeNum);
//                } else {
//                    String[] strArray = projectTypeId.split(",");
//                    list = newArticleService.getOperatorArticleList(condition, prescription, strArray, articleTypeNum);
//                }

                return new NewArticleWarpper(list).wrap();
            }
            List<Map<String, Object>> list = newArticleService.getArticleList(condition, prescription, 0, null);
            return new NewArticleWarpper(list).wrap();
        }
    }

    /**
     * 查询配图人员已完成文章列表
     */
    @RequestMapping(value = "/layoutCompletedArticleList")
    @ResponseBody
    public Object getLayoutCompletedArticleList(@RequestParam(required = false) String condition, @RequestParam(required = false) Integer prescription) {
        Integer loginUserId = ShiroKit.getUser().getId();
        User user = userService.selectById(loginUserId);
        String articleType = user.getArticletypeid();

        List<Map<String, Object>> list;
        String[] articleTypeArray = null;
        if (ToolUtil.isNotEmpty(articleType)) {
            articleTypeArray = articleType.split(",");
        }

        list = newArticleService.getlayoutCompletedArticleList(condition, prescription, articleTypeArray);

        return new NewArticleWarpper(list).wrap();
    }

    /**
     * 获取文章已领取列表
     */
    @RequestMapping(value = "/articleReceiveList")
    @ResponseBody
    public Object selectMyArticle(@RequestParam(required = false) String condition, @RequestParam(required = false) Integer articleTypeId, @RequestParam(required = false) Integer prescription) {
        Integer loginUserId = ShiroKit.getUser().getId();
//        User user = userService.selectById(loginUserId);

//            if (ShiroKit.hasRole(Const.ADMIN_NAME)){
//                List<Map<String, Object>> list = newArticleService.selectMyArticle(condition, articleTypeId,null,null,null,null,null);
//                return new NewArticleWarpper(list).wrap();
//            }
        if (ShiroKit.hasRole(Const.EXAMINER_ONE_NAME)) {
            List<Map<String, Object>> list = newArticleService.selectMyArticle(condition, articleTypeId, prescription, 4, loginUserId, null, null, null);
            return new NewArticleWarpper(list).wrap();
        }
        if (ShiroKit.hasRole(Const.EXAMINER_TWO_NAME)) {
            List<Map<String, Object>> list = newArticleService.selectMyArticle(condition, articleTypeId, prescription, 5, null, loginUserId, null, null);
            return new NewArticleWarpper(list).wrap();
        }
        if (ShiroKit.hasRole(Const.LAYOUT_NAME)) {
            List<Map<String, Object>> list = newArticleService.selectMyArticle(condition, articleTypeId, prescription, 10, null, null, loginUserId, null);
            return new NewArticleWarpper(list).wrap();
        }
        if (ShiroKit.hasRole(Const.OPERATOR_NAME)) {
            //
            List<Map<String, Object>> list = newArticleService.selectMyArticle(condition, articleTypeId, prescription, 12, null, null, null, loginUserId);
            return new NewArticleWarpper(list).wrap();
        }
        List<Map<String, Object>> list = newArticleService.selectMyArticle(condition, articleTypeId, prescription, 0, null, null, null, null);
        return new NewArticleWarpper(list).wrap();
//        }
    }

    /**
     * 审核退回
     */
    @RequestMapping(value = "/returnArticle")
    @ResponseBody
    public Object examineReturn(NewArticle newArticle) {
        Integer loginUserId = ShiroKit.getUser().getId();
        NewArticle newArticle1 = newArticleService.selectById(newArticle.getId());
        HistoricalArticle historicalArticle = ArticleConverter.INSTANCE.newArticleToHistory(newArticle1);
        if (ShiroKit.hasRole(Const.EXAMINER_ONE_NAME)) {
            if (newArticle1.getPrefix() == 3) {
                newArticleService.examineReturn(newArticle.getId(), newArticle.getContent(), newArticle.getReturnMessage(), 8, now());
                iArticleReceiveService.updateReceive(4, now(), newArticle.getId(), loginUserId);
                articleRecordService.addArticleRecord(newArticle1.getArticleNum(), "审核退回");
                return SUCCESS_TIP;
            }
            newArticleService.examineReturn(newArticle.getId(), newArticle.getContent(), newArticle.getReturnMessage(), 6, now());
            iArticleReceiveService.updateReceive(3, now(), newArticle.getId(), loginUserId);
            articleRecordService.addArticleRecord(newArticle1.getArticleNum(), "审核退回");
            return SUCCESS_TIP;
        } else {
            if (newArticle1.getPrefix() == 5) {
                newArticleService.examineReturn(newArticle.getId(), newArticle.getContent(), newArticle.getReturnMessage(), 8, now());
                iArticleReceiveService.updateReceive(4, now(), newArticle.getId(), loginUserId);
                articleRecordService.addArticleRecord(newArticle1.getArticleNum(), "审核退回");
                return SUCCESS_TIP;
            }
            newArticleService.examineReturn(newArticle.getId(), newArticle.getContent(), newArticle.getReturnMessage(), 7, now());
            iArticleReceiveService.updateReceive(3, now(), newArticle.getId(), loginUserId);
            articleRecordService.addArticleRecord(newArticle1.getArticleNum(), "审核退回");
            return SUCCESS_TIP;
        }
    }

    /**
     * 审核通过
     */
    @RequestMapping(value = "/pass")
    @ResponseBody
    public Object examinePass(NewArticle newArticle) {
        Integer loginUserId = ShiroKit.getUser().getId();
        if (ShiroKit.hasRole(Const.EXAMINER_ONE_NAME)) {
            newArticleService.examinePass(newArticle.getId(), 3, now());
            iArticleReceiveService.updateReceive(2, now(), newArticle.getId(), loginUserId);
            return SUCCESS_TIP;
        } else {
            newArticleService.examinePass(newArticle.getId(), 9, now());
            iArticleReceiveService.updateReceive(2, now(), newArticle.getId(), loginUserId);
            return SUCCESS_TIP;
        }
    }

    /**
     * 审核保存并通过文章
     */
    @RequestMapping(value = "/examineUpdate")
    @ResponseBody
    public Object examineUpdate(NewArticle newArticle) {
        Integer loginUserId = ShiroKit.getUser().getId();
        NewArticle newArticle1 = newArticleService.selectById(newArticle.getId());
        HistoricalArticle historicalArticle = ArticleConverter.INSTANCE.newArticleToHistory(newArticle1);
        historicalArticle.setNewMainTitle(newArticle1.getNewMainTitle());
        historicalArticle.setNewSubheading(newArticle1.getNewSubheading());
        historicalArticle.setReleaseType(newArticle1.getReleaseType());
        if (ShiroKit.hasRole(Const.EXAMINER_ONE_NAME)) {
            historicalArticle.setArticleStatus(3);
            historicalArticleService.insert(historicalArticle);
            newArticle.setArticleStatus(3);
            newArticle.setEditorNewFlag(0);
            newArticle.setPrefix(1);
            newArticle.setUpdateTime(now());
            newArticleService.updateById(newArticle);
            iArticleReceiveService.updateReceive(2, now(), newArticle.getId(), loginUserId);
            articleRecordService.addArticleRecord(newArticle1.getArticleNum(), "审核通过");
        }
        if (ShiroKit.hasRole(Const.EXAMINER_TWO_NAME)) {
            historicalArticle.setArticleStatus(11);
            historicalArticleService.insert(historicalArticle);
            newArticle.setArticleStatus(9);
            newArticle.setEditorNewFlag(0);
            newArticle.setPrefix(1);
            newArticle.setUpdateTime(now());
            newArticleService.updateById(newArticle);
            iArticleReceiveService.updateReceive(2, now(), newArticle.getId(), loginUserId);
            articleRecordService.addArticleRecord(newArticle1.getArticleNum(), "审核通过");
        }
        return SUCCESS_TIP;
    }

    /**
     * 批量审核保存并通过文章
     */
    @RequestMapping(value = "/examineUpdates")
    @ResponseBody
    public Object examineUpdates(@RequestParam String newArticleIds) {
        String[] newArticleIdList = newArticleIds.split(",");
        if (ShiroKit.hasRole(Const.EXAMINER_ONE_NAME)) {
            for (int i = 0;i < newArticleIdList.length; i++) {
                Integer newArticleId = Integer.valueOf(newArticleIdList[i]);
                Integer loginUserId = ShiroKit.getUser().getId();
                NewArticle newArticle1 = newArticleService.selectById(newArticleId);
                HistoricalArticle historicalArticle = ArticleConverter.INSTANCE.newArticleToHistory(newArticle1);
                historicalArticle.setNewMainTitle(newArticle1.getNewMainTitle());
                historicalArticle.setNewSubheading(newArticle1.getNewSubheading());
                historicalArticle.setReleaseType(newArticle1.getReleaseType());
                historicalArticle.setArticleStatus(3);
                historicalArticleService.insert(historicalArticle);
                newArticle1.setArticleStatus(3);
                newArticle1.setEditorNewFlag(0);
                newArticle1.setPrefix(1);
                newArticle1.setUpdateTime(now());
                newArticleService.updateById(newArticle1);
                iArticleReceiveService.updateReceive(2, now(), newArticle1.getId(), loginUserId);
                articleRecordService.addArticleRecord(newArticle1.getArticleNum(), "审核通过");
            }
        }
        if (ShiroKit.hasRole(Const.EXAMINER_TWO_NAME)) {
            for (int i = 0;i < newArticleIdList.length; i++) {
                Integer newArticleId = Integer.valueOf(newArticleIdList[i]);
                Integer loginUserId = ShiroKit.getUser().getId();
                NewArticle newArticle1 = newArticleService.selectById(newArticleId);
                HistoricalArticle historicalArticle = ArticleConverter.INSTANCE.newArticleToHistory(newArticle1);
                historicalArticle.setNewMainTitle(newArticle1.getNewMainTitle());
                historicalArticle.setNewSubheading(newArticle1.getNewSubheading());
                historicalArticle.setReleaseType(newArticle1.getReleaseType());
                historicalArticle.setArticleStatus(11);
                historicalArticleService.insert(historicalArticle);
                newArticle1.setArticleStatus(9);
                newArticle1.setEditorNewFlag(0);
                newArticle1.setPrefix(1);
                newArticle1.setUpdateTime(now());
                newArticleService.updateById(newArticle1);
                iArticleReceiveService.updateReceive(2, now(), newArticle1.getId(), loginUserId);
                articleRecordService.addArticleRecord(newArticle1.getArticleNum(), "审核通过");
            }
        }
        return SUCCESS_TIP;
    }

    /**
     * 配图提交文章
     */
    @RequestMapping(value = "/layoutArticle")
    @ResponseBody
    public Object layoutArticle(NewArticle newArticle) {
        Integer loginUserId = ShiroKit.getUser().getId();
        NewArticle newArticle1 = newArticleService.selectById(newArticle.getId());
        HistoricalArticle historicalArticle = ArticleConverter.INSTANCE.newArticleToHistory(newArticle1);
        historicalArticle.setArticleStatus(11);
        historicalArticleService.insert(historicalArticle);
        newArticle.setArticleStatus(11);
        newArticle.setEditorNewFlag(0);
        newArticle.setPrefix(1);
        newArticle.setUpdateTime(now());
        newArticle.setContent(newArticle.getContent());
        newArticle.setWordCount(delHTMLTag(newArticle.getContent(),1).length());
        newArticleService.updateById(newArticle);
        iArticleReceiveService.updateReceive(5, now(), newArticle.getId(), loginUserId);
        articleRecordService.addArticleRecord(newArticle1.getArticleNum(), "配图提交");
        return SUCCESS_TIP;
    }

    /**
     * 清除富文本的格式，获取纯文本
     *
     * @param htmlStr
     * @return
     */
    public static String delHTMLTag(String htmlStr,Integer isSpace) {
        Pattern p_w = Pattern.compile(regEx_w, Pattern.CASE_INSENSITIVE);
        Matcher m_w = p_w.matcher(htmlStr);
        htmlStr = m_w.replaceAll("");

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll("");

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");

        Pattern p_html2 = Pattern.compile(regEx_p, Pattern.CASE_INSENSITIVE);
        Matcher m_html2 = p_html2.matcher(htmlStr);
        htmlStr = m_html2.replaceAll("aaaabbbbcccc");

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll("");

        if(isSpace == 1){
            Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
            Matcher m_space = p_space.matcher(htmlStr);
            htmlStr = m_space.replaceAll("");
        }

        htmlStr = htmlStr.replaceAll(" ", "");
        return htmlStr.trim();
    }

    /**
     * 运营作废文章
     */
    @RequestMapping(value = "/cancelArticle")
    @ResponseBody
    public Object cancelArticle(NewArticle newArticle) {
        Integer loginUserId = ShiroKit.getUser().getId();
        historicalArticleService.updateHistory(newArticle.getId());
        newArticleService.cancelArticle(newArticle.getId(), newArticle.getReturnMessage());
        iArticleReceiveService.updateReceive(4, now(), newArticle.getId(), loginUserId);
        return SUCCESS_TIP;
    }

    /**
     * 新增文章发布地址
     */
    @RequestMapping(value = "/addAddress")
    @ResponseBody
    public Object add(ArticlePublishAddress articlePublishAddress) {
        Integer loginUserId = ShiroKit.getUser().getId();
        Integer count = articlePublishAddressService.getCount(articlePublishAddress.getArticleId());
        if (count > 0) {
            articlePublishAddressService.updateAddress(articlePublishAddress);
            return SUCCESS_TIP;
        }
        articlePublishAddress.setUserId(loginUserId);
        articlePublishAddressService.insert(articlePublishAddress);
        return SUCCESS_TIP;
    }

    /**
     * 修改文章发布地址
     */
    @RequestMapping(value = "/updateAddress")
    @ResponseBody
    public Object update(ArticlePublishAddress articlePublishAddress) {
        articlePublishAddressService.updateById(articlePublishAddress);
        return SUCCESS_TIP;
    }

    /**
     * 文章发布地址详情
     */
    @RequestMapping(value = "/detailAddress/{articlePublishAddressId}")
    @ResponseBody
    public Object detailAddress(@PathVariable("articlePublishAddressId") Integer articlePublishAddressId) {
        return articlePublishAddressService.selectById(articlePublishAddressId);
    }

    /**
     * 导出word
     * @param newArticleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exportWord")
    @ResponseBody
    public Object exportWord(@RequestParam Integer newArticleId) throws Exception {
        ResponseData responseData = new ResponseData();
        NewArticle newArticle = newArticleService.selectById(newArticleId);
        if(newArticle.getIsDownload().equals(1)) {

            //创建桌面文件夹
            File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
            //本地
            //String desktopPath = desktopDir.getAbsolutePath();
            //linux
            String desktopPath = "/var/local/doc";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String format = sdf.format(new Date());
            String externalFolder  = desktopPath + "/" + format + "/";
            File uploadFolder = new File(externalFolder);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }
            String savePath = externalFolder + "/" + newArticle.getArticleNum() + " " + newArticle.getMainTitle() + "/";
            File uploadDir = new File(savePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            XWPFDocument document = new XWPFDocument();

            XWPFParagraph firstParagraph = document.createParagraph();
            String mainTitle = "主标题" + ":" + newArticle.getMainTitle();
            firstParagraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun r0 = firstParagraph.createRun();
            r0.setText(mainTitle);
            r0.setBold(true);
            r0.setFontSize(15);

            XWPFParagraph firstParagraph1 = document.createParagraph();
            String subheading = "副标题" + ":" + newArticle.getSubheading();
            firstParagraph1.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun r1 = firstParagraph1.createRun();
            r1.setText(subheading);
            r1.setFontSize(15);

            String allContent = delHTMLTag(newArticle.getContent(), 0);
            String[] contents = allContent.split("aaaabbbbcccc");
            System.out.println("段落一共有" + contents.length);
            for(String content:contents)
            {
                XWPFParagraph firstParagraph2 = document.createParagraph();
                firstParagraph2.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun r2 = firstParagraph2.createRun();
                r2.setText(content);
                r2.setFontSize(13);
                r2.addBreak();
            }

            FileOutputStream out = new FileOutputStream(savePath + newArticle.getArticleNum() + " " + newArticle.getMainTitle() + ".doc");
            document.write(out);
            out.close();

            //更新文章下载状态
            newArticle.setIsDownload(2);
            newArticleService.updateById(newArticle);
            return SUCCESS_TIP;
        }
        responseData.setMessage("该文章已下载，请勿重复下载");
        responseData.setCode(30000);
        return responseData;
    }

    /**
     * 批量导出word
     * @param newArticleIds
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exportWords")
    @ResponseBody
    public Object exportWords(@RequestParam String newArticleIds,@RequestParam(required = false) Integer operationType) throws Exception {
        String[] newArticleIdList = newArticleIds.split(",");
        ResponseData responseData = new ResponseData();
        for (int i = 0;i < newArticleIdList.length; i++){
            Integer newArticleId = Integer.valueOf(newArticleIdList[i]);
            NewArticle newArticle = newArticleService.selectById(newArticleId);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
                //创建桌面文件夹-本地
//                File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
//                String desktopPath = desktopDir.getAbsolutePath();
                //linux
                String desktopPath = "/attached/DOCS";
                String externalFolder = "";
                String shenheFolder = "";
                String finalFolder = "";

                if(operationType != null && operationType == 2){
                    desktopPath = desktopPath + "/PeiTuDoCs";
                    String dateFolder = desktopPath+ "/" + sdf.format(newArticle.getCreateTime()) + "/";
                    File folder = new File(dateFolder);
                    if (!folder.exists()) {
                        folder.mkdirs();
                    }
                    externalFolder  = dateFolder + newArticle.getArticleNum() + "/";
                    File uploadFolder = new File(externalFolder);
                    if (!uploadFolder.exists()) {
                        uploadFolder.mkdirs();
                    }
                }else{
                    desktopPath = desktopPath + "/ShenHeDoCs";
                    User user = userService.selectById(newArticle.getCreateUserId());
                    shenheFolder = desktopPath + "/" + user.getUsernumber() + "/";
                    File uploadFolder = new File(shenheFolder);
                    if (!uploadFolder.exists()) {
                        uploadFolder.mkdirs();
                    }
                    finalFolder = shenheFolder + sdf.format(newArticle.getCreateTime()) + "/";
                    File folder = new File(finalFolder);
                    if (!folder.exists()) {
                        folder.mkdirs();
                    }
                }
                XWPFDocument document = new XWPFDocument();

                XWPFParagraph firstParagraph = document.createParagraph();
                firstParagraph.setAlignment(ParagraphAlignment.LEFT);
                String mainTitle = "主标题" + ":" + newArticle.getMainTitle();
                XWPFRun r0 = firstParagraph.createRun();
                r0.setText(mainTitle);
                r0.setBold(true);
                r0.setFontSize(15);

                XWPFParagraph firstParagraph1 = document.createParagraph();
                String subheading = "副标题" + ":" + newArticle.getSubheading();
                firstParagraph1.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun r1 = firstParagraph1.createRun();
                r1.setText(subheading);
                r1.setFontSize(15);

                String allContent = delHTMLTag(newArticle.getContent(), 0);
                String[] contents = allContent.split("aaaabbbbcccc");
                System.out.println("段落一共有" + contents.length);
                for(String content : contents)
                {
                    XWPFParagraph firstParagraph2 = document.createParagraph();
                    XWPFRun r2 = firstParagraph2.createRun();
                    firstParagraph2.setAlignment(ParagraphAlignment.LEFT);
                    r2.setText(content);
                    r2.setFontSize(13);
                    r2.addBreak();
                }

                if(operationType != null && operationType == 2){
                    FileOutputStream out = new FileOutputStream(externalFolder + newArticle.getArticleNum() + " " + newArticle.getMainTitle() + ".doc");
                    document.write(out);
                    out.close();
                    newArticle.setDowanloadUrl(externalFolder + newArticle.getArticleNum() + " " + newArticle.getMainTitle() + ".doc");
                }else{
                    FileOutputStream out = new FileOutputStream(finalFolder + newArticle.getArticleNum() + " " + newArticle.getMainTitle() + ".doc");
                    document.write(out);
                    out.close();
                    newArticle.setDowanloadUrlShenHe(finalFolder + newArticle.getArticleNum() + " " + newArticle.getMainTitle() + ".doc");
                }
                //更新文章下载状态
                newArticle.setIsDownload(2);
                newArticleService.updateById(newArticle);

                responseData.setCode(200);
                responseData.setMessage("成功");
                responseData.setSuccess(true);
        }
        return responseData;
    }

}
