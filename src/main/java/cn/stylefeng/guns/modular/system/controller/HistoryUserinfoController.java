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
import cn.stylefeng.guns.modular.system.model.HistoryUserinfo;
import cn.stylefeng.guns.modular.system.service.IHistoryUserinfoService;

/**
 * 用户信息历史版本控制器
 *
 * @author fengshuonan
 * @Date 2019-05-23 11:38:41
 */
@Controller
@RequestMapping("/historyUserinfo")
public class HistoryUserinfoController extends BaseController {

    private String PREFIX = "/system/historyUserinfo/";

    @Autowired
    private IHistoryUserinfoService historyUserinfoService;

    /**
     * 跳转到用户信息历史版本首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "historyUserinfo.html";
    }

    /**
     * 跳转到添加用户信息历史版本
     */
    @RequestMapping("/historyUserinfo_add")
    public String historyUserinfoAdd() {
        return PREFIX + "historyUserinfo_add.html";
    }

    /**
     * 跳转到修改用户信息历史版本
     */
    @RequestMapping("/historyUserinfo_update/{historyUserinfoId}")
    public String historyUserinfoUpdate(@PathVariable Integer historyUserinfoId, Model model) {
        HistoryUserinfo historyUserinfo = historyUserinfoService.selectById(historyUserinfoId);
        model.addAttribute("item",historyUserinfo);
        LogObjectHolder.me().set(historyUserinfo);
        return PREFIX + "historyUserinfo_edit.html";
    }

    /**
     * 获取用户信息历史版本列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return historyUserinfoService.selectList(null);
    }

    /**
     * 新增用户信息历史版本
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(HistoryUserinfo historyUserinfo) {
        historyUserinfoService.insert(historyUserinfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户信息历史版本
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer historyUserinfoId) {
        historyUserinfoService.deleteById(historyUserinfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户信息历史版本
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(HistoryUserinfo historyUserinfo) {
        historyUserinfoService.updateById(historyUserinfo);
        return SUCCESS_TIP;
    }

    /**
     * 用户信息历史版本详情
     */
    @RequestMapping(value = "/detail/{historyUserinfoId}")
    @ResponseBody
    public Object detail(@PathVariable("historyUserinfoId") Integer historyUserinfoId) {
        return historyUserinfoService.selectById(historyUserinfoId);
    }
}
