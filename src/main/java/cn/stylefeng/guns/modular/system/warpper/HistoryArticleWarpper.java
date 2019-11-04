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
public class HistoryArticleWarpper extends BaseControllerWrapper {

    public HistoryArticleWarpper(Map<String, Object> single) {
        super(single);
    }

    public HistoryArticleWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public HistoryArticleWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public HistoryArticleWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("createUserName", ConstantFactory.me().getUserNameById((Integer) map.get("create_user_id")));
        map.put("articleTypeName", ConstantFactory.me().getArticleTypeNames((Integer) map.get("article_type_id")));
        map.put("articleStatus", ConstantFactory.me().getArticleStatus((Integer) map.get("article_status")));
        map.put("prefix", ConstantFactory.me().getPrefix((Integer) map.get("prefix")));
        map.put("prescription", ConstantFactory.me().getTimelinessCategory((Integer) map.get("prescription")));
        map.put("examine1Id", ConstantFactory.me().getUserNameById((Integer) map.get("examine1_id")));
        map.put("examine2Id", ConstantFactory.me().getUserNameById((Integer) map.get("examine2_id")));
        map.put("layoutId", ConstantFactory.me().getUserNameById((Integer) map.get("layout_id")));
        map.put("operateId", ConstantFactory.me().getUserNameById((Integer) map.get("operate_id")));
    }
}
