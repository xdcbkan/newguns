package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.service.IArticleRecordService;
import cn.stylefeng.guns.modular.system.service.IUserService;
import cn.stylefeng.guns.modular.system.warpper.ArticleRecordWarpper;
import cn.stylefeng.guns.modular.system.warpper.NewArticleWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 文章记录控制器
 *
 * @author zpy
 * @Date 2019.09.24
 */
@Controller
@RequestMapping("/articleRecord")
public class ArticleRecordController extends BaseController {

    private String PREFIX = "/system/articleRecord/";

    @Autowired
    private IArticleRecordService articleRecordService;
    @Autowired
    private IUserService userService;

    /**
     * 首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "articleRecord.html";
    }

    /**
     * 获取文章列表
     */
    @RequestMapping(value = "/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(@RequestParam(required = false) String articleNum,@RequestParam(required = false) String usernumber) {
        if(articleNum == null && usernumber == null){
            return null;
        }
        String name = "";
        if(usernumber != null && !"".equals(usernumber)){
            User user =  userService.selectByUserNumber(usernumber);
            name = user.getName();
        }
        List<Map<String, Object>> list = articleRecordService.selectArticleList(articleNum,name);
        return super.warpObject(new ArticleRecordWarpper(list));
    }

}
