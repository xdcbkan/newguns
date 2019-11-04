package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.NewArticle;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 记录最新版本的文章 Mapper 接口
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-13
 */
public interface NewArticleMapper extends BaseMapper<NewArticle> {

    /**
     * 根据条件查询文章列表
     */
    List<Map<String, Object>> selectArticleList(@Param("newMainTitle") String newMainTitle, @Param("articleTypeId") Integer articleTypeId, @Param("createUserId") Integer createUserId, @Param("prescription") Integer prescription, @Param("articleStatus") Integer[] articleStatus);

    /**
     * 根据创建人查询文章个数
     */
    int selectArticleCount(Integer createUserId);

    /**
     * 审核1领取文章
     */
    int receiveArticle(@Param("id") Integer newArticleId, @Param("examineId") Integer examineId, @Param("examine2Id") Integer examine2Id, @Param("updateTime") Date updateTime, @Param("articleStatus") Integer articleStatus);

    /**
     * 不同工种查询文章可领取列表
     */
    List<Map<String, Object>> getArticleList(@Param("newMainTitle") String newMainTitle, @Param("prescription") Integer prescription, @Param("articleStatus") Integer articleStatus, @Param("articleTypeId") String[] articleTypeId);

    /**
     * 查询运营人员可领取文章列表
     */
    List<Map<String, Object>> getOperatorArticleList(@Param("title") String title, @Param("prescription") Integer prescription, @Param("projectTypeId") String[] projectTypeId, @Param("articleTypeId") Integer articleTypeId);

    /**
     * 查询运营人员可领取文章列表(可根据项目分类、文章分类查询)
     */
    List<Map<String, Object>> getOperatorAvailableArticleList(@Param("title") String title, @Param("prescription") Integer prescription, @Param("projectTypeId") String[] projectTypeId, @Param("articleTypeId") String[] articleTypeId);

    /**
     * 查询配图人员已完成文章列表(可根据项目分类、文章分类查询)
     */
    List<Map<String, Object>> getlayoutCompletedArticleList(@Param("title") String title, @Param("prescription") Integer prescription, @Param("articleTypeId") String[] articleTypeId);

    /**
     * 运营人员查看自己操作的版本
     */
    List<Map<String, Object>> getOperationRecord(@Param("condition") String condition, @Param("articleTypeId") Integer articleTypeId, @Param("prescription") Integer prescription, @Param("userId") Integer userId);

    /**
     * 不同工种查询文章已领取列表
     */
    List<Map<String, Object>> selectMyArticle(@Param("mainTitle") String mainTitle, @Param("articleTypeId") Integer articleTypeId, @Param("prescription") Integer prescription, @Param("articleStatus") Integer articleStatus, @Param("examine1Id") Integer examine1Id, @Param("examine2Id") Integer examine2Id, @Param("layoutId") Integer layoutId, @Param("operateId") Integer operateId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-提交数
     * @Date: 2019/5/15
     * @return: int
     **/
    int editorStatisticsSubmit(int editorId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-审1拒
     * @Date: 2019/5/15
     * @return: int
     **/
    int editorStatisticsExamine1Return(int editorId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-审2拒
     * @Date: 2019/5/15
     * @return: int
     **/
    int editorStatisticsExamine2Return(int editorId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-审1修1
     * @Date: 2019/5/15
     * @return: int
     **/
    int editorStatisticsExamine1ReturnEdit1(int editorId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-审1修2
     * @Date: 2019/5/15
     * @return: int
     **/
    int editorStatisticsExamine1ReturnEdit2(int editorId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-审2修1
     * @Date: 2019/5/15
     * @return: int
     **/
    int editorStatisticsExamine2ReturnEdit1(int editorId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-审2修2
     * @Date: 2019/5/15
     * @return: int
     **/
    int editorStatisticsExamine2ReturnEdit2(int editorId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-作废数
     * @Date: 2019/5/15
     * @return: int
     **/
    int editorStatisticsAbolish(int editorId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-审2通过数
     * @Date: 2019/5/15
     * @return: int
     **/
    int editorStatisticsExamine2Pass(int editorId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-运营作废数
     * @Date: 2019/5/16
     * @return: int
     **/
    int editorStatisticsOperatorAbolish(int editorId);

    /**
     * @param newArticleId
     * @param returnMessage
     * @Author: ZhangLu
     * @Description: 审核1退回
     * @Date: 2019/5/16
     * @return: int
     **/
    int examineReturn(@Param("newArticleId") Integer newArticleId,@Param("content") String content, @Param("returnMessage") String returnMessage, @Param("articleStatus") Integer articleStatus, @Param("updateTime") Date updateTime);

    /**
     * @param newArticleId
     * @Author: ZhangLu
     * @Description: 审核1通过
     * @Date: 2019/5/16
     * @return: int
     **/
    int examinePass(@Param("newArticleId") Integer newArticleId, @Param("articleStatus") Integer articleStatus, @Param("updateTime") Date updateTime);

    /**
     * @param newArticleId
     * @param layoutId
     * @param operateId
     * @param updateTime
     * @param articleStatus
     * @Author: ZhangLu
     * @Description: 配图运营领取文章
     * @Date: 2019/5/17
     * @return: int
     **/
    int setReceiveArticle(@Param("id") Integer newArticleId, @Param("layoutId") Integer layoutId, @Param("operateId") Integer operateId, @Param("updateTime") Date updateTime, @Param("articleStatus") Integer articleStatus);

    /**
     * @param id
     * @param returnMessage
     * @Author: ZhangLu
     * @Description: 运营作废
     * @Date: 2019/5/17
     * @return: int
     **/
    int cancelArticle(@Param("id") Integer id, @Param("returnMessage") String returnMessage);
}
