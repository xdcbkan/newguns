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
import cn.stylefeng.guns.modular.system.model.Examine2StatisticsMonth;
import cn.stylefeng.guns.modular.system.service.IExamine2StatisticsMonthService;

import java.util.List;
import java.util.Map;

/**
 * 审核2每月统计控制器
 *
 * @author fengshuonan
 * @Date 2019-05-20 11:52:50
 */
@Controller
@RequestMapping("/examine2StatisticsMonth")
public class Examine2StatisticsMonthController extends BaseController {

    private String PREFIX = "/system/examine2StatisticsMonth/";

    @Autowired
    private IExamine2StatisticsMonthService examine2StatisticsMonthService;

    /**
     * 跳转到审核2每月统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "examine2StatisticsMonth.html";
    }

    /**
     * 跳转到添加审核2每月统计
     */
    @RequestMapping("/examine2StatisticsMonth_add")
    public String examine2StatisticsMonthAdd() {
        return PREFIX + "examine2StatisticsMonth_add.html";
    }

    /**
     * 跳转到修改审核2每月统计
     */
    @RequestMapping("/examine2StatisticsMonth_update/{examine2StatisticsMonthId}")
    public String examine2StatisticsMonthUpdate(@PathVariable Integer examine2StatisticsMonthId, Model model) {
        Examine2StatisticsMonth examine2StatisticsMonth = examine2StatisticsMonthService.selectById(examine2StatisticsMonthId);
        model.addAttribute("item",examine2StatisticsMonth);
        LogObjectHolder.me().set(examine2StatisticsMonth);
        return PREFIX + "examine2StatisticsMonth_edit.html";
    }

    /**
     * 获取审核2每月统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime,@RequestParam(required = false) String endTime) {
        Integer loginUserId = ShiroKit.getUser().getId();
        if (ShiroKit.hasRole(Const.ADMIN_NAME) || ShiroKit.hasRole(Const.ADMINISTRATION_NAME)){
            List<Map<String, Object>> list = examine2StatisticsMonthService.selectMonthList(null,beginTime,endTime);
            return new StatisticsWarpper(list).wrap();
        }
        List<Map<String, Object>> list = examine2StatisticsMonthService.selectMonthList(loginUserId,beginTime,endTime);
        return new StatisticsWarpper(list).wrap();
    }

    /**
     * 新增审核2每月统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Examine2StatisticsMonth examine2StatisticsMonth) {
        examine2StatisticsMonthService.insert(examine2StatisticsMonth);
        return SUCCESS_TIP;
    }

    /**
     * 删除审核2每月统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer examine2StatisticsMonthId) {
        examine2StatisticsMonthService.deleteById(examine2StatisticsMonthId);
        return SUCCESS_TIP;
    }

    /**
     * 修改审核2每月统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Examine2StatisticsMonth examine2StatisticsMonth) {
        examine2StatisticsMonthService.updateById(examine2StatisticsMonth);
        return SUCCESS_TIP;
    }

    /**
     * 审核2每月统计详情
     */
    @RequestMapping(value = "/detail/{examine2StatisticsMonthId}")
    @ResponseBody
    public Object detail(@PathVariable("examine2StatisticsMonthId") Integer examine2StatisticsMonthId) {
        return examine2StatisticsMonthService.selectById(examine2StatisticsMonthId);
    }
}
