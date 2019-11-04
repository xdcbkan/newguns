package cn.stylefeng.guns.modular.system.mapstruct;

import cn.stylefeng.guns.modular.system.model.ArticleTask;
import cn.stylefeng.guns.modular.system.model.NewArticle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @Program: article_manage
 * @InterfaceName: ArticleTaskConverter
 * @Description: 文章任务-文章 转换
 * @Author: BaiYang
 * @Create: 2019-05-22 14:04
 * @Version: 1.0
 **/
@Mapper
public interface ArticleTaskConverter {

    ArticleTaskConverter INSTANCE = Mappers.getMapper(ArticleTaskConverter.class);

    /**
     * @param articleTask
     * @Author: BaiYang
     * @Description: 文章任务转换为新文章
     * @Date: 2019/5/22
     * @return: cn.stylefeng.guns.modular.system.model.NewArticle
     **/
    @Mappings({
            @Mapping(source = "id", target = "articleTaskId"),
            @Mapping(source = "timelinessCategory", target = "prescription"),
    })
    NewArticle articleTaskToNewArticle(ArticleTask articleTask);
}
