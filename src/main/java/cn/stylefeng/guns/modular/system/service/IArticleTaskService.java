package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.ArticleTask;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章任务表 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-21
 */
public interface IArticleTaskService extends IService<ArticleTask> {

    /**
     * @param title
     * @param timelinessCategory
     * @param taskStatus
     * @Author: BaiYang
     * @Description: 根据条件查询文章列表
     * @Date: 2019/5/21
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> selectArticleTasks(String title, Integer timelinessCategory, Integer taskStatus, Integer type);

    /**
     * 导入
     * @param fileName
     * @param file
     * @return
     * @throws Exception
     */
    boolean batchImport(String fileName, MultipartFile file) throws Exception;

}
