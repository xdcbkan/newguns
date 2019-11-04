package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.service.IUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.ProjectType;
import cn.stylefeng.guns.modular.system.service.IProjectTypeService;

import java.util.List;

/**
 * 项目类型控制器
 *
 * @author fengshuonan
 * @Date 2019-05-21 11:26:36
 */
@Controller
@RequestMapping("/projectType")
public class ProjectTypeController extends BaseController {

    private String PREFIX = "/system/projectType/";

    @Autowired
    private IProjectTypeService projectTypeService;

    @Autowired
    private IUserService userService;

    /**
     * 跳转到项目类型首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "projectType.html";
    }

    /**
     * 跳转到添加项目类型
     */
    @RequestMapping("/projectType_add")
    public String projectTypeAdd() {
        return PREFIX + "projectType_add.html";
    }

    /**
     * 跳转到修改项目类型
     */
    @RequestMapping("/projectType_update/{projectTypeId}")
    public String projectTypeUpdate(@PathVariable Integer projectTypeId, Model model) {
        ProjectType projectType = projectTypeService.selectById(projectTypeId);
        model.addAttribute("item", projectType);
        LogObjectHolder.me().set(projectType);
        return PREFIX + "projectType_edit.html";
    }

    /**
     * 获取项目类型列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return projectTypeService.selectList(null);
    }

    /**
     * 新增项目类型
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ProjectType projectType) {
        projectTypeService.insert(projectType);
        return SUCCESS_TIP;
    }

    /**
     * 删除项目类型
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer projectTypeId) {
        projectTypeService.deleteById(projectTypeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改项目类型
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ProjectType projectType) {
        projectTypeService.updateById(projectType);
        return SUCCESS_TIP;
    }

    /**
     * 项目类型详情
     */
    @RequestMapping(value = "/detail/{projectTypeId}")
    @ResponseBody
    public Object detail(@PathVariable("projectTypeId") Integer projectTypeId) {
        return projectTypeService.selectById(projectTypeId);
    }

    /**
     * 获取用户的项目分类列表
     */
    @RequestMapping(value = "/projectTypeTreeListByUserId/{userId}")
    @ResponseBody
    public List<ZTreeNode> roleTreeListByUserId(@PathVariable Integer userId) {
        User theUser = this.userService.selectById(userId);
        String projectTypeId = theUser.getProjectTypeId();
        if (ToolUtil.isEmpty(projectTypeId)) {
            return this.projectTypeService.projectTypeTreeList();
        } else {
            String[] strArray = projectTypeId.split(",");
            return this.projectTypeService.projectTypeTreeListByProjectTypeId(strArray);
        }
    }

}
