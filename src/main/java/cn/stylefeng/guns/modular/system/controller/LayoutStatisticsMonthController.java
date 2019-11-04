package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.system.warpper.StatisticsWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.LayoutStatisticsMonth;
import cn.stylefeng.guns.modular.system.service.ILayoutStatisticsMonthService;

import java.util.List;
import java.util.Map;

/**
 * 配图每月统计控制器
 *
 * @author fengshuonan
 * @Date 2019-05-20 11:53:10
 */
@Controller
@RequestMapping("/layoutStatisticsMonth")
public class LayoutStatisticsMonthController extends BaseController {

    private String PREFIX = "/system/layoutStatisticsMonth/";

    @Autowired
    private ILayoutStatisticsMonthService layoutStatisticsMonthService;

    /**
     * 跳转到配图每月统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "layoutStatisticsMonth.html";
    }

    /**
     * 跳转到添加配图每月统计
     */
    @RequestMapping("/layoutStatisticsMonth_add")
    public String layoutStatisticsMonthAdd() {
        return PREFIX + "layoutStatisticsMonth_add.html";
    }

    /**
     * 跳转到修改配图每月统计
     */
    @RequestMapping("/layoutStatisticsMonth_update/{layoutStatisticsMonthId}")
    public String layoutStatisticsMonthUpdate(@PathVariable Integer layoutStatisticsMonthId, Model model) {
        LayoutStatisticsMonth layoutStatisticsMonth = layoutStatisticsMonthService.selectById(layoutStatisticsMonthId);
        model.addAttribute("item",layoutStatisticsMonth);
        LogObjectHolder.me().set(layoutStatisticsMonth);
        return PREFIX + "layoutStatisticsMonth_edit.html";
    }

    /**
     * 获取配图每月统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime,@RequestParam(required = false) String endTime) {
        Integer loginUserId = ShiroKit.getUser().getId();
        if (ShiroKit.hasRole(Const.ADMIN_NAME) || ShiroKit.hasRole(Const.ADMINISTRATION_NAME)){
            List<Map<String, Object>> list = layoutStatisticsMonthService.selectMonthList(null,beginTime,endTime);
            return new StatisticsWarpper(list).wrap();
        }
        List<Map<String, Object>> list = layoutStatisticsMonthService.selectMonthList(loginUserId,beginTime,endTime);
        return new StatisticsWarpper(list).wrap();
    }
    /**
     * 新增配图每月统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(LayoutStatisticsMonth layoutStatisticsMonth) {
        layoutStatisticsMonthService.insert(layoutStatisticsMonth);
        return SUCCESS_TIP;
    }

    /**
     * 删除配图每月统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer layoutStatisticsMonthId) {
        layoutStatisticsMonthService.deleteById(layoutStatisticsMonthId);
        return SUCCESS_TIP;
    }

    /**
     * 修改配图每月统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(LayoutStatisticsMonth layoutStatisticsMonth) {
        layoutStatisticsMonthService.updateById(layoutStatisticsMonth);
        return SUCCESS_TIP;
    }

    /**
     * 配图每月统计详情
     */
    @RequestMapping(value = "/detail/{layoutStatisticsMonthId}")
    @ResponseBody
    public Object detail(@PathVariable("layoutStatisticsMonthId") Integer layoutStatisticsMonthId) {
        return layoutStatisticsMonthService.selectById(layoutStatisticsMonthId);
    }
}
