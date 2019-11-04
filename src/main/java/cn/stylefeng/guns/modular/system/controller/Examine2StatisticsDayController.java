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
import cn.stylefeng.guns.modular.system.model.Examine2StatisticsDay;
import cn.stylefeng.guns.modular.system.service.IExamine2StatisticsDayService;

import java.util.List;
import java.util.Map;

/**
 * 审核2每日工作统计控制器
 *
 * @author fengshuonan
 * @Date 2019-05-17 14:04:29
 */
@Controller
@RequestMapping("/examine2StatisticsDay")
public class Examine2StatisticsDayController extends BaseController {

    private String PREFIX = "/system/examine2StatisticsDay/";

    @Autowired
    private IExamine2StatisticsDayService examine2StatisticsDayService;

    /**
     * 跳转到审核2每日工作统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "examine2StatisticsDay.html";
    }

    /**
     * 跳转到添加审核2每日工作统计
     */
    @RequestMapping("/examine2StatisticsDay_add")
    public String examine2StatisticsDayAdd() {
        return PREFIX + "examine2StatisticsDay_add.html";
    }

    /**
     * 跳转到修改审核2每日工作统计
     */
    @RequestMapping("/examine2StatisticsDay_update/{examine2StatisticsDayId}")
    public String examine2StatisticsDayUpdate(@PathVariable Integer examine2StatisticsDayId, Model model) {
        Examine2StatisticsDay examine2StatisticsDay = examine2StatisticsDayService.selectById(examine2StatisticsDayId);
        model.addAttribute("item",examine2StatisticsDay);
        LogObjectHolder.me().set(examine2StatisticsDay);
        return PREFIX + "examine2StatisticsDay_edit.html";
    }

    /**
     * 获取审核2每日工作统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime,@RequestParam(required = false) String endTime) {
        Integer loginUserId = ShiroKit.getUser().getId();
        if (ShiroKit.hasRole(Const.ADMIN_NAME) || ShiroKit.hasRole(Const.ADMINISTRATION_NAME)){
            List<Map<String, Object>> list = examine2StatisticsDayService.selectDayList(null,beginTime,endTime);
            return new StatisticsWarpper(list).wrap();
        }
        List<Map<String, Object>> list = examine2StatisticsDayService.selectDayList(loginUserId,beginTime,endTime);
        return new StatisticsWarpper(list).wrap();
    }

    /**
     * 新增审核2每日工作统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Examine2StatisticsDay examine2StatisticsDay) {
        examine2StatisticsDayService.insert(examine2StatisticsDay);
        return SUCCESS_TIP;
    }

    /**
     * 删除审核2每日工作统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer examine2StatisticsDayId) {
        examine2StatisticsDayService.deleteById(examine2StatisticsDayId);
        return SUCCESS_TIP;
    }

    /**
     * 修改审核2每日工作统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Examine2StatisticsDay examine2StatisticsDay) {
        examine2StatisticsDayService.updateById(examine2StatisticsDay);
        return SUCCESS_TIP;
    }

    /**
     * 审核2每日工作统计详情
     */
    @RequestMapping(value = "/detail/{examine2StatisticsDayId}")
    @ResponseBody
    public Object detail(@PathVariable("examine2StatisticsDayId") Integer examine2StatisticsDayId) {
        return examine2StatisticsDayService.selectById(examine2StatisticsDayId);
    }
}
