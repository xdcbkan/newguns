package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.ArticlePublishAddress;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 文章发布地址表 服务类
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-17
 */
public interface IArticlePublishAddressService extends IService<ArticlePublishAddress> {

    ArticlePublishAddress selectAddress(Integer articleId);

    int updateAddress(ArticlePublishAddress articlePublishAddress);

    /**
     * @Author: ZhangLu
     * @Description:
     * @Date: 2019/5/17
     * @param articleId
     * @return: int
     **/
    int getCount(Integer articleId);

}
