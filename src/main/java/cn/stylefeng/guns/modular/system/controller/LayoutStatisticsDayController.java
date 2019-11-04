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
import cn.stylefeng.guns.modular.system.model.LayoutStatisticsDay;
import cn.stylefeng.guns.modular.system.service.ILayoutStatisticsDayService;

import java.util.List;
import java.util.Map;

/**
 * 配图每日工作统计控制器
 *
 * @author fengshuonan
 * @Date 2019-05-17 16:37:08
 */
@Controller
@RequestMapping("/layoutStatisticsDay")
public class LayoutStatisticsDayController extends BaseController {

    private String PREFIX = "/system/layoutStatisticsDay/";

    @Autowired
    private ILayoutStatisticsDayService layoutStatisticsDayService;

    /**
     * 跳转到配图每日工作统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "layoutStatisticsDay.html";
    }

    /**
     * 跳转到添加配图每日工作统计
     */
    @RequestMapping("/layoutStatisticsDay_add")
    public String layoutStatisticsDayAdd() {
        return PREFIX + "layoutStatisticsDay_add.html";
    }

    /**
     * 跳转到修改配图每日工作统计
     */
    @RequestMapping("/layoutStatisticsDay_update/{layoutStatisticsDayId}")
    public String layoutStatisticsDayUpdate(@PathVariable Integer layoutStatisticsDayId, Model model) {
        LayoutStatisticsDay layoutStatisticsDay = layoutStatisticsDayService.selectById(layoutStatisticsDayId);
        model.addAttribute("item",layoutStatisticsDay);
        LogObjectHolder.me().set(layoutStatisticsDay);
        return PREFIX + "layoutStatisticsDay_edit.html";
    }

    /**
     * 获取配图每日工作统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime,@RequestParam(required = false) String endTime) {
        Integer loginUserId = ShiroKit.getUser().getId();
        if (ShiroKit.hasRole(Const.ADMIN_NAME) || ShiroKit.hasRole(Const.ADMINISTRATION_NAME)){
            List<Map<String, Object>> list = layoutStatisticsDayService.selectDayList(null,beginTime,endTime);
            return new StatisticsWarpper(list).wrap();
        }
        List<Map<String, Object>> list = layoutStatisticsDayService.selectDayList(loginUserId,beginTime,endTime);
        return new StatisticsWarpper(list).wrap();
    }

    /**
     * 新增配图每日工作统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(LayoutStatisticsDay layoutStatisticsDay) {
        layoutStatisticsDayService.insert(layoutStatisticsDay);
        return SUCCESS_TIP;
    }

    /**
     * 删除配图每日工作统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer layoutStatisticsDayId) {
        layoutStatisticsDayService.deleteById(layoutStatisticsDayId);
        return SUCCESS_TIP;
    }

    /**
     * 修改配图每日工作统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(LayoutStatisticsDay layoutStatisticsDay) {
        layoutStatisticsDayService.updateById(layoutStatisticsDay);
        return SUCCESS_TIP;
    }

    /**
     * 配图每日工作统计详情
     */
    @RequestMapping(value = "/detail/{layoutStatisticsDayId}")
    @ResponseBody
    public Object detail(@PathVariable("layoutStatisticsDayId") Integer layoutStatisticsDayId) {
        return layoutStatisticsDayService.selectById(layoutStatisticsDayId);
    }
}
