package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.service.IUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.ArticleType;
import cn.stylefeng.guns.modular.system.service.IArticleTypeService;

import java.util.List;

/**
 * 文章分类管理控制器
 *
 * @author fengshuonan
 * @Date 2019-05-09 16:28:20
 */
@Controller
@RequestMapping("/articleType")
public class ArticleTypeController extends BaseController {

    private String PREFIX = "/system/articleType/";

    @Autowired
    private IArticleTypeService articleTypeService;

    @Autowired
    private IUserService userService;

    /**
     * 跳转到文章分类管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "articleType.html";
    }

    /**
     * 跳转到添加文章分类管理
     */
    @RequestMapping("/articleType_add")
    public String articleTypeAdd() {
        return PREFIX + "articleType_add.html";
    }

    /**
     * 跳转到修改文章分类管理
     */
    @RequestMapping("/articleType_update/{articleTypeId}")
    public String articleTypeUpdate(@PathVariable Integer articleTypeId, Model model) {
        ArticleType articleType = articleTypeService.selectById(articleTypeId);
        model.addAttribute("item", articleType);
        LogObjectHolder.me().set(articleType);
        return PREFIX + "articleType_edit.html";
    }

    /**
     * 获取文章分类管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return articleTypeService.selectList(null);
    }

    /**
     * 新增文章分类管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ArticleType articleType) {
        articleTypeService.insert(articleType);
        return SUCCESS_TIP;
    }

    /**
     * 删除文章分类管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer articleTypeId) {
        articleTypeService.deleteById(articleTypeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改文章分类管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ArticleType articleType) {
        articleTypeService.updateById(articleType);
        return SUCCESS_TIP;
    }

    /**
     * 文章分类管理详情
     */
    @RequestMapping(value = "/detail/{articleTypeId}")
    @ResponseBody
    public Object detail(@PathVariable("articleTypeId") Integer articleTypeId) {
        return articleTypeService.selectById(articleTypeId);
    }

    /**
     * 获取用户的文章分类列表
     */
    @RequestMapping(value = "/articleTypeTreeListByUserId/{userId}")
    @ResponseBody
    public List<ZTreeNode> roleTreeListByUserId(@PathVariable Integer userId) {
        User theUser = this.userService.selectById(userId);
        String articletypeid = theUser.getArticletypeid();
        if (ToolUtil.isEmpty(articletypeid)) {
            return this.articleTypeService.articleTypeTreeList();
        } else {
            String[] strArray = articletypeid.split(",");
            return this.articleTypeService.articleTypeTreeListByArticleTypeId(strArray);
        }
    }

}
