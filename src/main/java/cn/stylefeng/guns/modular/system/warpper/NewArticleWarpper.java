package cn.stylefeng.guns.modular.system.warpper;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ZhangLu
 * @time: 2019/5/9 14:32
 */
public class NewArticleWarpper extends BaseControllerWrapper {

    public NewArticleWarpper(Map<String, Object> single) {
        super(single);
    }

    public NewArticleWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public NewArticleWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public NewArticleWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("createUserName", ConstantFactory.me().getUserNameById((Integer) map.get("create_user_id")));
        map.put("articleTypeName", ConstantFactory.me().getArticleTypeNames((Integer) map.get("article_type_id")));
        map.put("articleStatus", ConstantFactory.me().getArticleStatus((Integer) map.get("article_status")));
        map.put("prefix", ConstantFactory.me().getPrefix((Integer) map.get("prefix")));
        map.put("prescription", ConstantFactory.me().getTimelinessCategory((Integer) map.get("prescription")));
        map.put("projectTypeName", ConstantFactory.me().getProjectType((Integer) map.get("project_type_id")));
        map.put("releaseTypeName", ConstantFactory.me().getReleaseTypeName((Integer) map.get("release_type")));
        map.put("isDownloadName", ConstantFactory.me().getIsDownloadName((Integer) map.get("is_download")));
    }
}
