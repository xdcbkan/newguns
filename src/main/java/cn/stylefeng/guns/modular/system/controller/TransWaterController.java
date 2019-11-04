package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.system.warpper.TransWaterWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.TransWater;
import cn.stylefeng.guns.modular.system.service.ITransWaterService;

import java.util.List;
import java.util.Map;

/**
 * 交易流水管理控制器
 *
 * @author fengshuonan
 * @Date 2019-05-14 17:26:42
 */
@Controller
@RequestMapping("/transWater")
public class TransWaterController extends BaseController {

    private String PREFIX = "/system/transWater/";

    @Autowired
    private ITransWaterService transWaterService;

    /**
     * 跳转到交易流水管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "transWater.html";
    }

    /**
     * 跳转到添加交易流水管理
     */
    @RequestMapping("/transWater_add")
    public String transWaterAdd() {
        return PREFIX + "transWater_add.html";
    }

    /**
     * 跳转到修改交易流水管理
     */
    @RequestMapping("/transWater_update/{transWaterId}")
    public String transWaterUpdate(@PathVariable Integer transWaterId, Model model) {
        TransWater transWater = transWaterService.selectById(transWaterId);
        model.addAttribute("item",transWater);
        LogObjectHolder.me().set(transWater);
        return PREFIX + "transWater_edit.html";
    }

    /**
     * 获取交易流水管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String beginTime, String endTime) {
        //        获取登录人的id
        Integer loginUserId = ShiroKit.getUser().getId();
        if (ShiroKit.hasRole(Const.ADMIN_NAME)){
            List<Map<String, Object>> list =transWaterService.selectTransWaterList(null,null,beginTime,endTime);
            return new TransWaterWarpper(list).wrap();
        }
        if (ShiroKit.hasRole(Const.EDITOR_NAME)){
            List<Map<String, Object>> list =transWaterService.selectTransWaterList(loginUserId,null,beginTime,endTime);
            return new TransWaterWarpper(list).wrap();
        }
        if (ShiroKit.hasRole(Const.BACKSTAGE_FINANCE_NAME)){
            List<Map<String, Object>> list =transWaterService.selectTransWaterList(null,loginUserId,beginTime,endTime);
            return new TransWaterWarpper(list).wrap();
        }
        return SUCCESS_TIP;
    }

    /**
     * 新增交易流水管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TransWater transWater) {
        transWaterService.insert(transWater);
        return SUCCESS_TIP;
    }

    /**
     * 删除交易流水管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer transWaterId) {
        transWaterService.deleteById(transWaterId);
        return SUCCESS_TIP;
    }

    /**
     * 修改交易流水管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TransWater transWater) {
        transWaterService.updateById(transWater);
        return SUCCESS_TIP;
    }

    /**
     * 交易流水管理详情
     */
    @RequestMapping(value = "/detail/{transWaterId}")
    @ResponseBody
    public Object detail(@PathVariable("transWaterId") Integer transWaterId) {
        return transWaterService.selectById(transWaterId);
    }
}
