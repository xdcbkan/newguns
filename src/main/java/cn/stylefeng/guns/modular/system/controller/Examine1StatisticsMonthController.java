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
import cn.stylefeng.guns.modular.system.model.Examine1StatisticsMonth;
import cn.stylefeng.guns.modular.system.service.IExamine1StatisticsMonthService;

import java.util.List;
import java.util.Map;

/**
 * 审核1每月统计控制器
 *
 * @author fengshuonan
 * @Date 2019-05-20 11:52:36
 */
@Controller
@RequestMapping("/examine1StatisticsMonth")
public class Examine1StatisticsMonthController extends BaseController {

    private String PREFIX = "/system/examine1StatisticsMonth/";

    @Autowired
    private IExamine1StatisticsMonthService examine1StatisticsMonthService;

    /**
     * 跳转到审核1每月统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "examine1StatisticsMonth.html";
    }

    /**
     * 跳转到添加审核1每月统计
     */
    @RequestMapping("/examine1StatisticsMonth_add")
    public String examine1StatisticsMonthAdd() {
        return PREFIX + "examine1StatisticsMonth_add.html";
    }

    /**
     * 跳转到修改审核1每月统计
     */
    @RequestMapping("/examine1StatisticsMonth_update/{examine1StatisticsMonthId}")
    public String examine1StatisticsMonthUpdate(@PathVariable Integer examine1StatisticsMonthId, Model model) {
        Examine1StatisticsMonth examine1StatisticsMonth = examine1StatisticsMonthService.selectById(examine1StatisticsMonthId);
        model.addAttribute("item",examine1StatisticsMonth);
        LogObjectHolder.me().set(examine1StatisticsMonth);
        return PREFIX + "examine1StatisticsMonth_edit.html";
    }

    /**
     * 获取审核1每月统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime,@RequestParam(required = false) String endTime) {
        Integer loginUserId = ShiroKit.getUser().getId();
        if (ShiroKit.hasRole(Const.ADMIN_NAME) || ShiroKit.hasRole(Const.ADMINISTRATION_NAME)){
            List<Map<String, Object>> list = examine1StatisticsMonthService.selectMonthList(null,beginTime,endTime);
            return new StatisticsWarpper(list).wrap();
        }
        List<Map<String, Object>> list = examine1StatisticsMonthService.selectMonthList(loginUserId,beginTime,endTime);
        return new StatisticsWarpper(list).wrap();
    }

    /**
     * 新增审核1每月统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Examine1StatisticsMonth examine1StatisticsMonth) {
        examine1StatisticsMonthService.insert(examine1StatisticsMonth);
        return SUCCESS_TIP;
    }

    /**
     * 删除审核1每月统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer examine1StatisticsMonthId) {
        examine1StatisticsMonthService.deleteById(examine1StatisticsMonthId);
        return SUCCESS_TIP;
    }

    /**
     * 修改审核1每月统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Examine1StatisticsMonth examine1StatisticsMonth) {
        examine1StatisticsMonthService.updateById(examine1StatisticsMonth);
        return SUCCESS_TIP;
    }

    /**
     * 审核1每月统计详情
     */
    @RequestMapping(value = "/detail/{examine1StatisticsMonthId}")
    @ResponseBody
    public Object detail(@PathVariable("examine1StatisticsMonthId") Integer examine1StatisticsMonthId) {
        return examine1StatisticsMonthService.selectById(examine1StatisticsMonthId);
    }
}
