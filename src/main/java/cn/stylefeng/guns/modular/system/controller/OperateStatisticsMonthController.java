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
import cn.stylefeng.guns.modular.system.model.OperateStatisticsMonth;
import cn.stylefeng.guns.modular.system.service.IOperateStatisticsMonthService;

import java.util.List;
import java.util.Map;

/**
 * 运营每月统计控制器
 *
 * @author fengshuonan
 * @Date 2019-05-20 11:53:25
 */
@Controller
@RequestMapping("/operateStatisticsMonth")
public class OperateStatisticsMonthController extends BaseController {

    private String PREFIX = "/system/operateStatisticsMonth/";

    @Autowired
    private IOperateStatisticsMonthService operateStatisticsMonthService;

    /**
     * 跳转到运营每月统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "operateStatisticsMonth.html";
    }

    /**
     * 跳转到添加运营每月统计
     */
    @RequestMapping("/operateStatisticsMonth_add")
    public String operateStatisticsMonthAdd() {
        return PREFIX + "operateStatisticsMonth_add.html";
    }

    /**
     * 跳转到修改运营每月统计
     */
    @RequestMapping("/operateStatisticsMonth_update/{operateStatisticsMonthId}")
    public String operateStatisticsMonthUpdate(@PathVariable Integer operateStatisticsMonthId, Model model) {
        OperateStatisticsMonth operateStatisticsMonth = operateStatisticsMonthService.selectById(operateStatisticsMonthId);
        model.addAttribute("item",operateStatisticsMonth);
        LogObjectHolder.me().set(operateStatisticsMonth);
        return PREFIX + "operateStatisticsMonth_edit.html";
    }

    /**
     * 获取运营每月统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime,@RequestParam(required = false) String endTime) {
        Integer loginUserId = ShiroKit.getUser().getId();
        if (ShiroKit.hasRole(Const.ADMIN_NAME) || ShiroKit.hasRole(Const.ADMINISTRATION_NAME)){
            List<Map<String, Object>> list = operateStatisticsMonthService.selectMonthList(null,beginTime,endTime);
            return new StatisticsWarpper(list).wrap();
        }
        List<Map<String, Object>> list = operateStatisticsMonthService.selectMonthList(loginUserId,beginTime,endTime);
        return new StatisticsWarpper(list).wrap();
    }

    /**
     * 新增运营每月统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(OperateStatisticsMonth operateStatisticsMonth) {
        operateStatisticsMonthService.insert(operateStatisticsMonth);
        return SUCCESS_TIP;
    }

    /**
     * 删除运营每月统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer operateStatisticsMonthId) {
        operateStatisticsMonthService.deleteById(operateStatisticsMonthId);
        return SUCCESS_TIP;
    }

    /**
     * 修改运营每月统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(OperateStatisticsMonth operateStatisticsMonth) {
        operateStatisticsMonthService.updateById(operateStatisticsMonth);
        return SUCCESS_TIP;
    }

    /**
     * 运营每月统计详情
     */
    @RequestMapping(value = "/detail/{operateStatisticsMonthId}")
    @ResponseBody
    public Object detail(@PathVariable("operateStatisticsMonthId") Integer operateStatisticsMonthId) {
        return operateStatisticsMonthService.selectById(operateStatisticsMonthId);
    }
}
