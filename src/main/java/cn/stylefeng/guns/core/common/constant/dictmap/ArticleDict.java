package cn.stylefeng.guns.core.common.constant.dictmap;

import cn.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 文章的字典
 *
 * @author zpy
 * @date 2019.09.18
 */
public class ArticleDict extends AbstractDictMap {

    @Override
    public void init() {
        put("mainTitle", "文章标题");
        put("subheading", "副标题");
        put("content", "文章内容");
        put("url", "url地址");
        put("articleTaskId", "当前操作人");
    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("articleTaskId", "getOperatorName");
    }
}
