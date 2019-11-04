package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.NewArticle;
import com.baomidou.mybatisplus.service.IService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 记录最新版本的文章 服务类
 * </p>
 *
 * @author zhanglu
 * @since 2019-05-13
 */
public interface INewArticleService extends IService<NewArticle> {

    /**
     * 根据条件查询任务列表
     */
    List<Map<String, Object>> selectArticleList(String newMainTitle, Integer articleTypeId, Integer createUserId, Integer prescription, Integer[] articleStatus);

    /**
     * 根据创建人查询文章个数
     */
    int selectArticleCount(Integer createUserId);

    /**
     * 审核1领取文章
     */
    int receiveArticle(Integer newArticleId, Integer examineId, Integer examine2Id, Date updateTime, Integer articleStatus);

    /**
     * 不同工种查询文章可领取列表
     */
    List<Map<String, Object>> getArticleList(String newMainTitle, Integer prescription, Integer articleStatus, String[] articleTypeId);

    /**
     * @param title
     * @param prescription
     * @param projectTypeId
     * @param articleTypeId
     * @Author: BaiYang
     * @Description: 运营可领取文章列表
     * @Date: 2019/5/23
     * @return: java.util.List<java.util.Map   <   java.lang.String   ,   java.lang.Object>>
     **/
    List<Map<String, Object>> getOperatorArticleList(String title, Integer prescription, String[] projectTypeId, Integer articleTypeId);

    /**
     * @param title
     * @param prescription
     * @param projectTypeId
     * @param articleTypeId
     * @Author: BaiYang
     * @Description: 运营可领取文章列表
     * @Date: 2019/6/5
     * @return: java.util.List<java.util.Map < java.lang.String , java.lang.Object>>
     **/
    List<Map<String, Object>> getOperatorAvailableArticleList(String title, Integer prescription, String[] projectTypeId, String[] articleTypeId);

    /**
     * 查询配图人员已完成文章列表
     * @param title
     * @param prescription
     * @param articleTypeId
     * @return
     */
    List<Map<String, Object>> getlayoutCompletedArticleList(String title, Integer prescription, String[] articleTypeId);

    /**
     * 不同工种查询文章已领取列表
     */
    List<Map<String, Object>> selectMyArticle(String mainTitle, Integer articleTypeId, Integer prescription, Integer articleStatus, Integer examine1Id, Integer examine2Id, Integer layoutId, Integer operateId);

    /**
     * @param newArticleId
     * @param returnMessage
     * @Author: ZhangLu
     * @Description: 审核1退回
     * @Date: 2019/5/16
     * @return: int
     **/
    int examineReturn(Integer newArticleId, String content, String returnMessage, Integer articleStatus, Date updateTime);

    /**
     * @param newArticleId
     * @Author: ZhangLu
     * @Description: 审核1通过
     * @Date: 2019/5/16
     * @return: int
     **/
    int examinePass(Integer newArticleId, Integer articleStatus, Date updateTime);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-提交数
     * @Date: 2019/5/16
     * @return: int
     **/
    int editorStatisticsSubmit(int editorId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-审1拒
     * @Date: 2019/5/16
     * @return: int
     **/
    int editorStatisticsExamine1Return(int editorId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-审2拒
     * @Date: 2019/5/16
     * @return: int
     **/
    int editorStatisticsExamine2Return(int editorId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-审1修1
     * @Date: 2019/5/16
     * @return: int
     **/
    int editorStatisticsExamine1ReturnEdit1(int editorId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-审1修2
     * @Date: 2019/5/16
     * @return: int
     **/
    int editorStatisticsExamine1ReturnEdit2(int editorId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-审2修1
     * @Date: 2019/5/16
     * @return: int
     **/
    int editorStatisticsExamine2ReturnEdit1(int editorId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-审2修2
     * @Date: 2019/5/16
     * @return: int
     **/
    int editorStatisticsExamine2ReturnEdit2(int editorId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-作废数
     * @Date: 2019/5/16
     * @return: int
     **/
    int editorStatisticsAbolish(int editorId);

    /**
     * @param editorId
     * @Author: BaiYang
     * @Description: 文编每日统计-有效数量(审2通过数-运营作废数)
     * @Date: 2019/5/16
     * @return: int
     **/
    int editorStatisticsPass(int editorId);

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
    int setReceiveArticle(Integer newArticleId, Integer layoutId, Integer operateId, Date updateTime, Integer articleStatus);

    /**
     * @param id
     * @param returnMessage
     * @Author: ZhangLu
     * @Description: 运营作废
     * @Date: 2019/5/17
     * @return: int
     **/
    int cancelArticle(Integer id, String returnMessage);

    /**
     * 运营人员查看自己操作的版本
     */
    List<Map<String, Object>> getOperationRecord(String condition, Integer articleTypeId, Integer prescription, Integer userId);

}
