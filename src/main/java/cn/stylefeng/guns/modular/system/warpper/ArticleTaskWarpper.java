package cn.stylefeng.guns.modular.system.warpper;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * @Program: article_manage
 * @ClassName: ArticleTaskWarpper
 * @Description: TODO
 * @Author: BaiYang
 * @Create: 2019-05-21 17:32
 * @Version: 1.0
 **/
public class ArticleTaskWarpper extends BaseControllerWrapper {
    public ArticleTaskWarpper(Map<String, Object> single) {
        super(single);
    }

    public ArticleTaskWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public ArticleTaskWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public ArticleTaskWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("timelinessCategoryName", ConstantFactory.me().getTimelinessCategory((Integer) map.get("timeliness_category")));
        map.put("taskStatusName", ConstantFactory.me().getTaskStatus((Integer) map.get("task_status")));
        map.put("typeName", ConstantFactory.me().getArticleTypeNames((Integer) map.get("type")));
    }
}
