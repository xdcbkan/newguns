package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.ArticlePublishAddress;
import cn.stylefeng.guns.modular.system.dao.ArticlePublishAddressMapper;
import cn.stylefeng.guns.modular.system.service.IArticlePublishAddressService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章发布地址表 服务实现类
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-17
 */
@Service
public class ArticlePublishAddressServiceImpl extends ServiceImpl<ArticlePublishAddressMapper, ArticlePublishAddress> implements IArticlePublishAddressService {

    @Override
    public ArticlePublishAddress selectAddress(Integer articleId) {
        return this.baseMapper.selectAddress(articleId);
    }

    @Override
    public int updateAddress(ArticlePublishAddress articlePublishAddress) {
        return this.baseMapper.updateAddress(articlePublishAddress);
    }

    @Override
    public int getCount(Integer articleId) {
        return this.baseMapper.getCount(articleId);
    }
}
