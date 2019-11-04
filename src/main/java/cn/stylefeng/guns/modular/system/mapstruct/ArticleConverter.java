package cn.stylefeng.guns.modular.system.mapstruct;

import cn.stylefeng.guns.modular.system.model.HistoricalArticle;
import cn.stylefeng.guns.modular.system.model.NewArticle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @Program: mc2
 * @InterfaceName: ArticleConverter
 * @Description: TODO
 * @Author: BaiYang
 * @Create: 2019-04-11 16:34
 * @Version: 1.0
 **/
@Mapper
public interface ArticleConverter {

    ArticleConverter INSTANCE = Mappers.getMapper(ArticleConverter.class);

    /**
     * @Author: ZhangLu
     * @Description: 文章新表转历史表
     * @Date: 2019/5/16
     * @param newArticle
     * @return: cn.stylefeng.guns.modular.system.model.NewArticle
     **/
    @Mapping(source = "id", target = "newArticleId")
    HistoricalArticle newArticleToHistory(NewArticle newArticle);

}
