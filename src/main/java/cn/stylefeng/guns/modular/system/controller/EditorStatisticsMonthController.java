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
import cn.stylefeng.guns.modular.system.model.EditorStatisticsMonth;
import cn.stylefeng.guns.modular.system.service.IEditorStatisticsMonthService;

import java.util.List;
import java.util.Map;

/**
 * 文编每月统计控制器
 *
 * @author fengshuonan
 * @Date 2019-05-20 11:52:14
 */
@Controller
@RequestMapping("/editorStatisticsMonth")
public class EditorStatisticsMonthController extends BaseController {

    private String PREFIX = "/system/editorStatisticsMonth/";

    @Autowired
    private IEditorStatisticsMonthService editorStatisticsMonthService;

    /**
     * 跳转到文编每月统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "editorStatisticsMonth.html";
    }

    /**
     * 跳转到添加文编每月统计
     */
    @RequestMapping("/editorStatisticsMonth_add")
    public String editorStatisticsMonthAdd() {
        return PREFIX + "editorStatisticsMonth_add.html";
    }

    /**
     * 跳转到修改文编每月统计
     */
    @RequestMapping("/editorStatisticsMonth_update/{editorStatisticsMonthId}")
    public String editorStatisticsMonthUpdate(@PathVariable Integer editorStatisticsMonthId, Model model) {
        EditorStatisticsMonth editorStatisticsMonth = editorStatisticsMonthService.selectById(editorStatisticsMonthId);
        model.addAttribute("item",editorStatisticsMonth);
        LogObjectHolder.me().set(editorStatisticsMonth);
        return PREFIX + "editorStatisticsMonth_edit.html";
    }

    /**
     * 获取文编每月统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime,@RequestParam(required = false) String endTime) {
        Integer loginUserId = ShiroKit.getUser().getId();
        if (ShiroKit.hasRole(Const.ADMIN_NAME) || ShiroKit.hasRole(Const.ADMINISTRATION_NAME)){
            List<Map<String, Object>> list = editorStatisticsMonthService.selectMonthList(null,beginTime,endTime);
            return new StatisticsWarpper(list).wrap();
        }
        List<Map<String, Object>> list = editorStatisticsMonthService.selectMonthList(loginUserId,beginTime,endTime);
        return new StatisticsWarpper(list).wrap();
    }

    /**
     * 新增文编每月统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(EditorStatisticsMonth editorStatisticsMonth) {
        editorStatisticsMonthService.insert(editorStatisticsMonth);
        return SUCCESS_TIP;
    }

    /**
     * 删除文编每月统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer editorStatisticsMonthId) {
        editorStatisticsMonthService.deleteById(editorStatisticsMonthId);
        return SUCCESS_TIP;
    }

    /**
     * 修改文编每月统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(EditorStatisticsMonth editorStatisticsMonth) {
        editorStatisticsMonthService.updateById(editorStatisticsMonth);
        return SUCCESS_TIP;
    }

    /**
     * 文编每月统计详情
     */
    @RequestMapping(value = "/detail/{editorStatisticsMonthId}")
    @ResponseBody
    public Object detail(@PathVariable("editorStatisticsMonthId") Integer editorStatisticsMonthId) {
        return editorStatisticsMonthService.selectById(editorStatisticsMonthId);
    }
}
