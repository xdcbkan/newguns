package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.ArticleReceive;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * <p>
 * 领取文章记录表
 * Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-14
 */
public interface ArticleReceiveMapper extends BaseMapper<ArticleReceive> {

    /**
     * @param
     * @Author: ZhangLu
     * @Description: 审核通过或者退回文章同时修改文章记录表信息
     * @Date: 2019/5/16
     * @return: int
     **/
    int updateReceive(@Param("resultsEnforcement") Integer resultsEnforcement, @Param("operateTime") Date operateTime, @Param("articleId") Integer articleId, @Param("userId") Integer userId);

    // 审核1每日统计内容

    /**
     * @param examiner1Id
     * @Author: BaiYang
     * @Description: 审核1每日统计- 领取数
     * @Date: 2019/5/16
     * @return: int
     **/
    int examine1StatisticsReceive(int examiner1Id);

    /**
     * @param examiner1Id
     * @Author: BaiYang
     * @Description: 审核1每日统计- 通过数
     * @Date: 2019/5/16
     * @return: int
     **/
    int examine1StatisticsPass(int examiner1Id);

    /**
     * @param examiner1Id
     * @Author: BaiYang
     * @Description: 审核1每日统计- 退回数
     * @Date: 2019/5/16
     * @return: int
     **/
    int examine1StatisticsUnPass(int examiner1Id);

    /**
     * @param examiner1Id
     * @Author: BaiYang
     * @Description: 审核1每日统计- 审核中数
     * @Date: 2019/5/16
     * @return: int
     **/
    int examine1StatisticsExamining(int examiner1Id);

    /**
     * @param examiner1Id
     * @Author: BaiYang
     * @Description: 审核1每日统计- 作废数
     * @Date: 2019/5/16
     * @return: int
     **/
    int examine1StatisticsAbolish(int examiner1Id);


    // 审核2每日统计内容

    /**
     * @param examiner2Id
     * @Author: BaiYang
     * @Description: 审核2每日统计- 领取数
     * @Date: 2019/5/16
     * @return: int
     **/
    int examine2StatisticsReceive(int examiner2Id);

    /**
     * @param examiner2Id
     * @Author: BaiYang
     * @Description: 审核2每日统计- 通过数
     * @Date: 2019/5/16
     * @return: int
     **/
    int examine2StatisticsPass(int examiner2Id);

    /**
     * @param examiner2Id
     * @Author: BaiYang
     * @Description: 审核2每日统计- 退回数
     * @Date: 2019/5/16
     * @return: int
     **/
    int examine2StatisticsUnPass(int examiner2Id);

    /**
     * @param examiner2Id
     * @Author: BaiYang
     * @Description: 审核2每日统计- 审核中数
     * @Date: 2019/5/16
     * @return: int
     **/
    int examine2StatisticsExamining(int examiner2Id);

    /**
     * @param examiner2Id
     * @Author: BaiYang
     * @Description: 审核2每日统计- 作废数
     * @Date: 2019/5/16
     * @return: int
     **/
    int examine2StatisticsAbolish(int examiner2Id);

    // 配图每日统计内容

    /**
     * @param layoutId
     * @Author: BaiYang
     * @Description: 配图每日统计- 领取数
     * @Date: 2019/5/17
     * @return: int
     **/
    int layoutStatisticReceive(int layoutId);

    /**
     * @param layoutId
     * @Author: BaiYang
     * @Description: 配图每日统计- 配图文章数
     * @Date: 2019/5/17
     * @return: int
     **/
    int layoutStatisticArticleNum(int layoutId);

    /**
     * @param layoutId
     * @Author: BaiYang
     * @Description: 配图每日统计- 配图图片数
     * @Date: 2019/5/17
     * @return: int
     **/
    int layoutStatisticPictureNum(int layoutId);

    // 运营每日统计内容

    /**
     * @param operatorId
     * @Author: BaiYang
     * @Description: 运营每日统计内容- 领取数
     * @Date: 2019/5/20
     * @return: int
     **/
    int operatorStatisticReceive(int operatorId);

    /**
     * @param operatorId
     * @Author: BaiYang
     * @Description: 运营每日统计内容- 作废数
     * @Date: 2019/5/20
     * @return: int
     **/
    int operatorStatisticAbolish(int operatorId);
}
