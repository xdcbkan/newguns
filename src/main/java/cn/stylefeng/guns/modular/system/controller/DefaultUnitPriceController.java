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
import cn.stylefeng.guns.modular.system.model.DefaultUnitPrice;
import cn.stylefeng.guns.modular.system.service.IDefaultUnitPriceService;

/**
 * 文章默认售价控制器
 *
 * @author fengshuonan
 * @Date 2019-05-13 16:37:52
 */
@Controller
@RequestMapping("/defaultUnitPrice")
public class DefaultUnitPriceController extends BaseController {

    private String PREFIX = "/system/defaultUnitPrice/";

    @Autowired
    private IDefaultUnitPriceService defaultUnitPriceService;

    /**
     * 跳转到文章默认售价首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "defaultUnitPrice.html";
    }

    /**
     * 跳转到添加文章默认售价
     */
    @RequestMapping("/defaultUnitPrice_add")
    public String defaultUnitPriceAdd() {
        return PREFIX + "defaultUnitPrice_add.html";
    }

    /**
     * 跳转到修改文章默认售价
     */
    @RequestMapping("/defaultUnitPrice_update/{defaultUnitPriceId}")
    public String defaultUnitPriceUpdate(@PathVariable Integer defaultUnitPriceId, Model model) {
        DefaultUnitPrice defaultUnitPrice = defaultUnitPriceService.selectById(defaultUnitPriceId);
        model.addAttribute("item",defaultUnitPrice);
        LogObjectHolder.me().set(defaultUnitPrice);
        return PREFIX + "defaultUnitPrice_edit.html";
    }

    /**
     * 获取文章默认售价列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return defaultUnitPriceService.selectList(null);
    }

    /**
     * 新增文章默认售价
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DefaultUnitPrice defaultUnitPrice) {
        defaultUnitPriceService.insert(defaultUnitPrice);
        return SUCCESS_TIP;
    }

    /**
     * 删除文章默认售价
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer defaultUnitPriceId) {
        defaultUnitPriceService.deleteById(defaultUnitPriceId);
        return SUCCESS_TIP;
    }

    /**
     * 修改文章默认售价
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DefaultUnitPrice defaultUnitPrice) {
        defaultUnitPriceService.updateById(defaultUnitPrice);
        return SUCCESS_TIP;
    }

    /**
     * 文章默认售价详情
     */
    @RequestMapping(value = "/detail/{defaultUnitPriceId}")
    @ResponseBody
    public Object detail(@PathVariable("defaultUnitPriceId") Integer defaultUnitPriceId) {
        return defaultUnitPriceService.selectById(defaultUnitPriceId);
    }
}
