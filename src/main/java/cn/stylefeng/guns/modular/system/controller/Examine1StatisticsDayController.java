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
import cn.stylefeng.guns.modular.system.model.Examine1StatisticsDay;
import cn.stylefeng.guns.modular.system.service.IExamine1StatisticsDayService;

import java.util.List;
import java.util.Map;

/**
 * 审核1每日工作统计控制器
 *
 * @author fengshuonan
 * @Date 2019-05-17 14:03:58
 */
@Controller
@RequestMapping("/examine1StatisticsDay")
public class Examine1StatisticsDayController extends BaseController {

    private String PREFIX = "/system/examine1StatisticsDay/";

    @Autowired
    private IExamine1StatisticsDayService examine1StatisticsDayService;

    /**
     * 跳转到审核1每日工作统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "examine1StatisticsDay.html";
    }

    /**
     * 跳转到添加审核1每日工作统计
     */
    @RequestMapping("/examine1StatisticsDay_add")
    public String examine1StatisticsDayAdd() {
        return PREFIX + "examine1StatisticsDay_add.html";
    }

    /**
     * 跳转到修改审核1每日工作统计
     */
    @RequestMapping("/examine1StatisticsDay_update/{examine1StatisticsDayId}")
    public String examine1StatisticsDayUpdate(@PathVariable Integer examine1StatisticsDayId, Model model) {
        Examine1StatisticsDay examine1StatisticsDay = examine1StatisticsDayService.selectById(examine1StatisticsDayId);
        model.addAttribute("item",examine1StatisticsDay);
        LogObjectHolder.me().set(examine1StatisticsDay);
        return PREFIX + "examine1StatisticsDay_edit.html";
    }

    /**
     * 获取审核1每日工作统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime,@RequestParam(required = false) String endTime) {
        Integer loginUserId = ShiroKit.getUser().getId();
        if (ShiroKit.hasRole(Const.ADMIN_NAME) || ShiroKit.hasRole(Const.ADMINISTRATION_NAME)){
            List<Map<String, Object>> list = examine1StatisticsDayService.selectDayList(null,beginTime,endTime);
            return new StatisticsWarpper(list).wrap();
        }
        List<Map<String, Object>> list = examine1StatisticsDayService.selectDayList(loginUserId,beginTime,endTime);
        return new StatisticsWarpper(list).wrap();
    }

    /**
     * 新增审核1每日工作统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Examine1StatisticsDay examine1StatisticsDay) {
        examine1StatisticsDayService.insert(examine1StatisticsDay);
        return SUCCESS_TIP;
    }

    /**
     * 删除审核1每日工作统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer examine1StatisticsDayId) {
        examine1StatisticsDayService.deleteById(examine1StatisticsDayId);
        return SUCCESS_TIP;
    }

    /**
     * 修改审核1每日工作统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Examine1StatisticsDay examine1StatisticsDay) {
        examine1StatisticsDayService.updateById(examine1StatisticsDay);
        return SUCCESS_TIP;
    }

    /**
     * 审核1每日工作统计详情
     */
    @RequestMapping(value = "/detail/{examine1StatisticsDayId}")
    @ResponseBody
    public Object detail(@PathVariable("examine1StatisticsDayId") Integer examine1StatisticsDayId) {
        return examine1StatisticsDayService.selectById(examine1StatisticsDayId);
    }
}
