package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.ArticlePublishAddress;
import cn.stylefeng.guns.modular.system.service.IArticlePublishAddressService;

/**
 * 文章发布地址控制器
 *
 * @author fengshuonan
 * @Date 2019-05-17 15:04:22
 */
@Controller
@RequestMapping("/articlePublishAddress")
public class ArticlePublishAddressController extends BaseController {

    private String PREFIX = "/system/operatorReceiveArticle/";

    @Autowired
    private IArticlePublishAddressService articlePublishAddressService;

    /**
     * 跳转到文章发布地址首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "articlePublishAddress.html";
    }

    /**
     * 跳转到添加文章发布地址
     */
    @RequestMapping("/articlePublishAddress_add")
    public String articlePublishAddressAdd() {
        return PREFIX + "articlePublishAddress_add.html";
    }

    /**
     * 跳转到修改文章发布地址
     */
    @RequestMapping("/articlePublishAddress_update/{articlePublishAddressId}")
    public String articlePublishAddressUpdate(@PathVariable Integer articlePublishAddressId, Model model) {
        ArticlePublishAddress articlePublishAddress = articlePublishAddressService.selectById(articlePublishAddressId);
        model.addAttribute("item",articlePublishAddress);
        LogObjectHolder.me().set(articlePublishAddress);
        return PREFIX + "articlePublishAddress_edit.html";
    }

    /**
     * 获取文章发布地址列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return articlePublishAddressService.selectList(null);
    }

    /**
     * 新增文章发布地址
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ArticlePublishAddress articlePublishAddress) {
        articlePublishAddressService.insert(articlePublishAddress);
        return SUCCESS_TIP;
    }

    /**
     * 删除文章发布地址
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer articlePublishAddressId) {
        articlePublishAddressService.deleteById(articlePublishAddressId);
        return SUCCESS_TIP;
    }

    /**
     * 修改文章发布地址
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ArticlePublishAddress articlePublishAddress) {
        articlePublishAddressService.updateById(articlePublishAddress);
        return SUCCESS_TIP;
    }

    /**
     * 文章发布地址详情
     */
    @RequestMapping(value = "/detail/{articlePublishAddressId}")
    @ResponseBody
    public Object detail(@PathVariable("articlePublishAddressId") Integer articlePublishAddressId) {
        return articlePublishAddressService.selectById(articlePublishAddressId);
    }
}
