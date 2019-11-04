package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.modular.system.dao.ArticleTaskMapper;
import cn.stylefeng.guns.modular.system.dao.ArticleTypeMapper;
import cn.stylefeng.guns.modular.system.model.ArticleTask;
import cn.stylefeng.guns.modular.system.service.IArticleTaskService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章任务表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-21
 */
@Service
public class ArticleTaskServiceImpl extends ServiceImpl<ArticleTaskMapper, ArticleTask> implements IArticleTaskService {
    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    @Override
    public List<Map<String, Object>> selectArticleTasks(String title, Integer timelinessCategory, Integer taskStatus, Integer type) {
        return this.baseMapper.selectArticleTasks(title, timelinessCategory, taskStatus, type);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {

        boolean notNull = false;
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new Exception("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            notNull = true;
        }
        ArticleTask articleTask;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            articleTask = new ArticleTask();

            if (row.getCell(0) != null) {
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                articleTask.setMainTitle(row.getCell(0).getStringCellValue());
            }
            if (row.getCell(1) != null) {
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                articleTask.setSubheading(row.getCell(1).getStringCellValue());
            } else {
                articleTask.setSubheading("");
            }
            if (row.getCell(2) != null && row.getCell(2).equals("")) {
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                articleTask.setTimelinessCategory(Integer.valueOf(ConstantFactory.me().getCodeByName(row.getCell(2).getStringCellValue())));
            } else {
                articleTask.setTimelinessCategory(Integer.valueOf(ConstantFactory.me().getCodeByName("其他类文章")));
            }
            if (row.getCell(3) != null || !row.getCell(3).equals("")) {
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                articleTask.setType(articleTypeMapper.findByName(row.getCell(3).getStringCellValue()).getId());
            } else {
                articleTask.setType(articleTypeMapper.findByName("其他").getId());
            }
            if (row.getCell(4) != null) {
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                articleTask.setUrl(row.getCell(4).getStringCellValue());
            }
            this.baseMapper.insert(articleTask);
        }
        return notNull;
    }
}
