package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.ArticlePublishAddress;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 文章发布地址表 Mapper 接口
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-17
 */
public interface ArticlePublishAddressMapper extends BaseMapper<ArticlePublishAddress> {

    /**
     * @Author: ZhangLu
     * @Description: 根据文章id查询信息
     * @Date: 2019/5/17
     * @param articleId
     * @return: cn.stylefeng.guns.modular.system.model.ArticlePublishAddress
     **/
    ArticlePublishAddress selectAddress(Integer articleId);
    /**
     * @Author: ZhangLu
     * @Description: 根据文章id修改地址
     * @Date: 2019/5/17
     * @param articlePublishAddress
     * @return: int
     **/
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
