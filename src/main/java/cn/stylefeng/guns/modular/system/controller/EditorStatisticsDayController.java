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
import cn.stylefeng.guns.modular.system.model.EditorStatisticsDay;
import cn.stylefeng.guns.modular.system.service.IEditorStatisticsDayService;

import java.util.List;
import java.util.Map;

/**
 * 文编每日统计控制器
 *
 * @author fengshuonan
 * @Date 2019-05-15 16:05:10
 */
@Controller
@RequestMapping("/editorStatisticsDay")
public class EditorStatisticsDayController extends BaseController {

    private String PREFIX = "/system/editorStatisticsDay/";

    @Autowired
    private IEditorStatisticsDayService editorStatisticsDayService;

    /**
     * 跳转到文编每日统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "editorStatisticsDay.html";
    }

    /**
     * 跳转到添加文编每日统计
     */
    @RequestMapping("/editorStatisticsDay_add")
    public String editorStatisticsDayAdd() {
        return PREFIX + "editorStatisticsDay_add.html";
    }

    /**
     * 跳转到修改文编每日统计
     */
    @RequestMapping("/editorStatisticsDay_update/{editorStatisticsDayId}")
    public String editorStatisticsDayUpdate(@PathVariable Integer editorStatisticsDayId, Model model) {
        EditorStatisticsDay editorStatisticsDay = editorStatisticsDayService.selectById(editorStatisticsDayId);
        model.addAttribute("item",editorStatisticsDay);
        LogObjectHolder.me().set(editorStatisticsDay);
        return PREFIX + "editorStatisticsDay_edit.html";
    }

    /**
     * 获取文编每日统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime,@RequestParam(required = false) String endTime) {
        Integer loginUserId = ShiroKit.getUser().getId();
        if (ShiroKit.hasRole(Const.ADMIN_NAME) || ShiroKit.hasRole(Const.ADMINISTRATION_NAME)){
            List<Map<String, Object>> list = editorStatisticsDayService.selectDayList(null,beginTime,endTime);
            return new StatisticsWarpper(list).wrap();
        }
        List<Map<String, Object>> list = editorStatisticsDayService.selectDayList(loginUserId,beginTime,endTime);
        return new StatisticsWarpper(list).wrap();
    }

    /**
     * 新增文编每日统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(EditorStatisticsDay editorStatisticsDay) {
        editorStatisticsDayService.insert(editorStatisticsDay);
        return SUCCESS_TIP;
    }

    /**
     * 删除文编每日统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer editorStatisticsDayId) {
        editorStatisticsDayService.deleteById(editorStatisticsDayId);
        return SUCCESS_TIP;
    }

    /**
     * 修改文编每日统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(EditorStatisticsDay editorStatisticsDay) {
        editorStatisticsDayService.updateById(editorStatisticsDay);
        return SUCCESS_TIP;
    }

    /**
     * 文编每日统计详情
     */
    @RequestMapping(value = "/detail/{editorStatisticsDayId}")
    @ResponseBody
    public Object detail(@PathVariable("editorStatisticsDayId") Integer editorStatisticsDayId) {
        return editorStatisticsDayService.selectById(editorStatisticsDayId);
    }
}
