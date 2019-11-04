package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.system.model.ArticleType;
import cn.stylefeng.guns.modular.system.model.NewArticle;
import cn.stylefeng.guns.modular.system.model.ProjectType;
import cn.stylefeng.guns.modular.system.service.IArticleTypeService;
import cn.stylefeng.guns.modular.system.service.INewArticleService;
import cn.stylefeng.guns.modular.system.service.IProjectTypeService;
import cn.stylefeng.guns.modular.system.warpper.HistoryArticleWarpper;
import cn.stylefeng.guns.modular.system.warpper.NewArticleWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.HistoricalArticle;
import cn.stylefeng.guns.modular.system.service.IHistoricalArticleService;

import java.util.List;
import java.util.Map;

/**
 * 文章历史版本记录控制器
 *
 * @author fengshuonan
 * @Date 2019-05-16 13:09:11
 */
@Controller
@RequestMapping("/historicalArticle")
public class HistoricalArticleController extends BaseController {

    private String PREFIX = "/system/historicalArticle/";

    @Autowired
    private IHistoricalArticleService historicalArticleService;

    @Autowired
    private INewArticleService newArticleService;

    @Autowired
    private IArticleTypeService articleTypeService;

    @Autowired
    private IProjectTypeService projectTypeService;

    /**
     * 跳转到文章历史版本记录首页
     */
    @RequestMapping("/history")
    public String index() {
        return PREFIX + "historicalArticle.html";
    }

    /**
     * 跳转到用户文章首页
     */
    @RequestMapping("/newArticle")
    public String newArticle(Model model) {
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        return PREFIX + "newArticle.html";
    }

    /**
     * 跳转到添加文章历史版本记录
     */
    @RequestMapping("/historicalArticle_add")
    public String historicalArticleAdd() {
        return PREFIX + "historicalArticle_add.html";
    }

    /**
     * 跳转到修改文章历史版本记录
     */
    @RequestMapping("/historicalArticle_update/{historicalArticleId}")
    public String historicalArticleUpdate(@PathVariable Integer historicalArticleId, Model model) {
        HistoricalArticle historicalArticle = historicalArticleService.selectById(historicalArticleId);
        model.addAttribute("item", historicalArticle);
        LogObjectHolder.me().set(historicalArticle);
        return PREFIX + "historicalArticle_edit.html";
    }

    /**
     * 跳转到个人操作历史版本
     */
    @RequestMapping("/personal_version")
    public String toHistoricalArticleVersion(Model model) {
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        return PREFIX + "personalOperationRecord.html";
    }

    /**
     * 查看文章版本详情
     */
    @RequestMapping("/view/{articleId}")
    public String view(@PathVariable Integer articleId, Model model) {
        if (ShiroKit.hasRole(Const.OPERATOR_NAME)) {
            // 是运营，从文章新表查询
            NewArticle newArticle = newArticleService.selectById(articleId);
            model.addAttribute("item", newArticle);
            LogObjectHolder.me().set(newArticle);
        } else {
            // 审核1 审核2 配图 从文章历史表查询
            HistoricalArticle historicalArticle = historicalArticleService.selectById(articleId);
            model.addAttribute("item", historicalArticle);
            LogObjectHolder.me().set(historicalArticle);
        }
        List<ArticleType> articleTypes = this.articleTypeService.selectList(null);
        model.addAttribute("articleTypes", articleTypes);
        List<ProjectType> projectTypes = projectTypeService.selectList(null);
        model.addAttribute("projectTypes", projectTypes);
        return PREFIX + "historicalArticle_view.html";
    }

    /**
     * 获取文章历史版本记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer articleId) {
        Session session = ShiroKit.getSession();
        if (articleId == null) {
            Integer id = (Integer) session.getAttribute("articleId");
            List<Map<String, Object>> list = historicalArticleService.getList(id);
            return new HistoryArticleWarpper(list).wrap();
        } else {
            session.setAttribute("articleId", articleId);
            Integer id = (Integer) session.getAttribute("articleId");
            List<Map<String, Object>> list = historicalArticleService.getList(id);
            return new HistoryArticleWarpper(list).wrap();
        }
    }

    /**
     * 获取文章列表
     */
    @RequestMapping(value = "/newList")
    @ResponseBody
    public Object newList(@RequestParam(required = false) String condition, @RequestParam(required = false) Integer articleTypeId, @RequestParam(required = false) Integer prescription, @RequestParam(required = false) Integer articleStatus) {
        List<Map<String, Object>> list = historicalArticleService.selectArticleList(condition, articleTypeId, prescription, articleStatus);
        return new NewArticleWarpper(list).wrap();
    }

    /**
     * 获取个人文章操作版本列表，最新200条
     */
    @RequestMapping(value = "/personalOperationRecord")
    @ResponseBody
    public Object getPersonalOperationRecord(@RequestParam(required = false) String condition, @RequestParam(required = false) Integer articleTypeId, @RequestParam(required = false) Integer prescription) {
//        List<Map<String, Object>> list = historicalArticleService.selectArticleList(condition, articleTypeId, prescription, articleStatus);
//        return new NewArticleWarpper(list).wrap();
        // 当前操作人id
        Integer loginUserId = ShiroKit.getUser().getId();

        if (ShiroKit.hasRole(Const.OPERATOR_NAME)) {
            // 是运营，从最新文章中查取作废内容
            List<Map<String, Object>> list = newArticleService.getOperationRecord(condition, articleTypeId, prescription, loginUserId);
            return new NewArticleWarpper(list).wrap();
        }

        // 审核1 审核2 配图，从文章历史版本中查取个人操作版本
        List<Map<String, Object>> list = historicalArticleService.getPersonalOperationRecord(condition, articleTypeId, prescription, loginUserId);
        return new HistoryArticleWarpper(list).wrap();

    }

    /**
     * 新增文章历史版本记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(HistoricalArticle historicalArticle) {
        historicalArticleService.insert(historicalArticle);
        return SUCCESS_TIP;
    }

    /**
     * 删除文章历史版本记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer historicalArticleId) {
        historicalArticleService.deleteById(historicalArticleId);
        return SUCCESS_TIP;
    }


    /**
     * 修改文章历史版本记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(HistoricalArticle historicalArticle) {
        historicalArticleService.updateById(historicalArticle);
        return SUCCESS_TIP;
    }

    /**
     * 文章历史版本记录详情
     */
    @RequestMapping(value = "/detail/{historicalArticleId}")
    @ResponseBody
    public Object detail(@PathVariable("historicalArticleId") Integer historicalArticleId) {
        return historicalArticleService.selectById(historicalArticleId);
    }
}
