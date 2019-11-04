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
import cn.stylefeng.guns.modular.system.model.OperateStatisticsDay;
import cn.stylefeng.guns.modular.system.service.IOperateStatisticsDayService;

import java.util.List;
import java.util.Map;

/**
 * 运营每日工作统计控制器
 *
 * @author fengshuonan
 * @Date 2019-05-17 16:37:42
 */
@Controller
@RequestMapping("/operateStatisticsDay")
public class OperateStatisticsDayController extends BaseController {

    private String PREFIX = "/system/operateStatisticsDay/";

    @Autowired
    private IOperateStatisticsDayService operateStatisticsDayService;

    /**
     * 跳转到运营每日工作统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "operateStatisticsDay.html";
    }

    /**
     * 跳转到添加运营每日工作统计
     */
    @RequestMapping("/operateStatisticsDay_add")
    public String operateStatisticsDayAdd() {
        return PREFIX + "operateStatisticsDay_add.html";
    }

    /**
     * 跳转到修改运营每日工作统计
     */
    @RequestMapping("/operateStatisticsDay_update/{operateStatisticsDayId}")
    public String operateStatisticsDayUpdate(@PathVariable Integer operateStatisticsDayId, Model model) {
        OperateStatisticsDay operateStatisticsDay = operateStatisticsDayService.selectById(operateStatisticsDayId);
        model.addAttribute("item",operateStatisticsDay);
        LogObjectHolder.me().set(operateStatisticsDay);
        return PREFIX + "operateStatisticsDay_edit.html";
    }

    /**
     * 获取运营每日工作统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime,@RequestParam(required = false) String endTime) {
        Integer loginUserId = ShiroKit.getUser().getId();
        if (ShiroKit.hasRole(Const.ADMIN_NAME) || ShiroKit.hasRole(Const.ADMINISTRATION_NAME)){
            List<Map<String, Object>> list = operateStatisticsDayService.selectDayList(null,beginTime,endTime);
            return new StatisticsWarpper(list).wrap();
        }
        List<Map<String, Object>> list = operateStatisticsDayService.selectDayList(loginUserId,beginTime,endTime);
        return new StatisticsWarpper(list).wrap();
    }

    /**
     * 新增运营每日工作统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(OperateStatisticsDay operateStatisticsDay) {
        operateStatisticsDayService.insert(operateStatisticsDay);
        return SUCCESS_TIP;
    }

    /**
     * 删除运营每日工作统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer operateStatisticsDayId) {
        operateStatisticsDayService.deleteById(operateStatisticsDayId);
        return SUCCESS_TIP;
    }

    /**
     * 修改运营每日工作统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(OperateStatisticsDay operateStatisticsDay) {
        operateStatisticsDayService.updateById(operateStatisticsDay);
        return SUCCESS_TIP;
    }

    /**
     * 运营每日工作统计详情
     */
    @RequestMapping(value = "/detail/{operateStatisticsDayId}")
    @ResponseBody
    public Object detail(@PathVariable("operateStatisticsDayId") Integer operateStatisticsDayId) {
        return operateStatisticsDayService.selectById(operateStatisticsDayId);
    }
}
