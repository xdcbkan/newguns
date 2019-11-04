/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.core.interceptor;

import cn.stylefeng.guns.modular.system.model.*;
import cn.stylefeng.guns.modular.system.service.*;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.HttpSessionContext;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static cn.hutool.core.date.DateTime.now;

/**
 * 静态调用session的拦截器
 *
 * @author fengshuonan
 * @date 2016年11月13日 下午10:15:42
 */
@Aspect
@Component
@EnableScheduling
@EnableAsync
public class SessionHolderInterceptor extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private INewArticleService newArticleService;

    @Autowired
    private IEditorStatisticsDayService editorStatisticsDayService;

    @Autowired
    private IArticleReceiveService articleReceiveService;

    @Autowired
    private IExamine1StatisticsDayService examine1StatisticsDayService;

    @Autowired
    private IExamine2StatisticsDayService examine2StatisticsDayService;

    @Autowired
    private ILayoutStatisticsDayService layoutStatisticsDayService;

    @Autowired
    private IOperateStatisticsDayService operateStatisticsDayService;

    @Autowired
    private IEditorStatisticsMonthService editorStatisticsMonthService;

    @Autowired
    private IExamine1StatisticsMonthService examine1StatisticsMonthService;

    @Autowired
    private IExamine2StatisticsMonthService examine2StatisticsMonthService;

    @Autowired
    private ILayoutStatisticsMonthService layoutStatisticsMonthService;

    @Autowired
    private IOperateStatisticsMonthService operateStatisticsMonthService;

    @Autowired
    private IDefaultUnitPriceService defaultUnitPriceService;

    @Autowired
    private ITransWaterService transWaterService;

    @Pointcut("execution(* cn.stylefeng.guns.*..controller.*.*(..))")
    public void cutService() {
    }

    @Around("cutService()")
    public Object sessionKit(ProceedingJoinPoint point) throws Throwable {
        HttpSessionContext.put(super.getHttpServletRequest().getSession());
        try {
            return point.proceed();
        } finally {
            HttpSessionContext.remove();
        }
    }

    /**
     * @param
     * @Author: BaiYang
     * @Description: 重置后台工作人员文章领取次数
     * @Date: 2019/5/17
     * @return: void
     **/
    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    public void resetResidueReceiveNum() {
        // 重置自身单独设置的领取上限
        // 获取单独设置文章领取上限人员的id
        List<Integer> ids = userService.selectAllSpecialPopulationsIds();
        // 重置
        if (ToolUtil.isNotEmpty(ids)) {
            userService.resetResidueReceiveNumSpecialPopulations(ids);
        }

        // 根据角色id重置自身没有单独设置领取上限的 审核1 8 审核2 9 配图11 运营 10
        userService.resetResidueReceiveNumByRoleId(8);
        userService.resetResidueReceiveNumByRoleId(9);
        userService.resetResidueReceiveNumByRoleId(10);
        userService.resetResidueReceiveNumByRoleId(11);

    }

    /**
     * @param
     * @Author: BaiYang
     * @Description: 文编每日统计
     * @Date: 2019/5/16
     * @return: void
     **/
    @Async
    @Scheduled(cron = "0 0 23 * * ?")
    public void editorStatisticsDay() {
        ArrayList<EditorStatisticsDay> editorStatisticsDays = new ArrayList<>();
        // 获取文编id集合
        List<Integer> editorIds = userService.selectAllEditorId();
        if (ToolUtil.isNotEmpty(editorIds)) {
            System.out.println("editorIds：" + editorIds);
            for (Integer editorId : editorIds) {
                // 获取当前文编的统计的各项数据
                // 文编今日提交
                int submit = newArticleService.editorStatisticsSubmit(editorId);
                // 审1拒
                int examine1Return = newArticleService.editorStatisticsExamine1Return(editorId);
                // 审2拒
                int examine2Return = newArticleService.editorStatisticsExamine2Return(editorId);
                // 审1修1
                int examine1ReturnEdit1 = newArticleService.editorStatisticsExamine1ReturnEdit1(editorId);
                // 审1修2
                int examine1ReturnEdit2 = newArticleService.editorStatisticsExamine1ReturnEdit2(editorId);
                // 审2修1
                int examine2ReturnEdit1 = newArticleService.editorStatisticsExamine2ReturnEdit1(editorId);
                // 审2修2
                int examine2ReturnEdit2 = newArticleService.editorStatisticsExamine2ReturnEdit2(editorId);
                // 作废数
                int abolish = newArticleService.editorStatisticsAbolish(editorId);
                // 有效文章数
                int pass = newArticleService.editorStatisticsPass(editorId);
                // 如果数据全为0，则不记录该条记录
                int count = submit + examine1Return + examine2Return + examine1ReturnEdit1 + examine1ReturnEdit2 + examine2ReturnEdit1 + examine2ReturnEdit2 + abolish + pass;
                if (count > 0) {
                    // 塞入实体，加入集合
                    EditorStatisticsDay editorStatisticsDay = new EditorStatisticsDay();
                    editorStatisticsDay.setUserId(editorId);
                    editorStatisticsDay.setSubmit(submit);
                    editorStatisticsDay.setExamine1Return(examine1Return);
                    editorStatisticsDay.setExamine2Return(examine2Return);
                    editorStatisticsDay.setExamine1ReturnEdit1(examine1ReturnEdit1);
                    editorStatisticsDay.setExamine1ReturnEdit2(examine1ReturnEdit2);
                    editorStatisticsDay.setExamine2ReturnEdit1(examine2ReturnEdit1);
                    editorStatisticsDay.setExamine2ReturnEdit2(examine2ReturnEdit2);
                    editorStatisticsDay.setAbolish(abolish);
                    editorStatisticsDay.setPass(pass);
                    System.out.println(editorStatisticsDay.toString());
                    editorStatisticsDays.add(editorStatisticsDay);
                    // 更新文编总有效文章数
                    EntityWrapper<User> ew = new EntityWrapper<>();
                    ew.eq("id", editorId);
                    String setSql = "passnum = passnum +" + pass;
                    boolean temp = userService.updateForSet(setSql, ew);
                }
            }
            // 向文编每日统计表插入记录,一次400条
            int rows = 0;
            for (int i = 0; i < editorStatisticsDays.size() / 400 + 1; i++) {
                int end = (i + 1) * 400;
                if (end >= editorStatisticsDays.size()) {
                    end = editorStatisticsDays.size();
                }
                List<EditorStatisticsDay> list = editorStatisticsDays.subList(i * 400, end);
                rows += editorStatisticsDayService.insertEditorStatisticsDay(list);
            }
            System.out.println("结束时间：" + now());
            System.out.println("受影响的行数：" + rows);
        }

    }


    /**
     * @param
     * @Author: BaiYang
     * @Description: 审核1每日统计
     * @Date: 2019/5/17
     * @return: void
     **/
    @Async
    @Scheduled(cron = "0 0 23 * * ?")
    public void examine1StatisticsDay() {
        ArrayList<Examine1StatisticsDay> examine1StatisticsDays = new ArrayList<>();
        // 获取审核1 id集合
        List<Integer> examiner1Ids = userService.selectAllExaminer1Id();
        if (ToolUtil.isNotEmpty(examiner1Ids)) {
            // 获取当前审核1 统计的各项数据
            for (Integer examiner1Id : examiner1Ids) {
                // 今日领取数
                int receive = articleReceiveService.examine1StatisticsReceive(examiner1Id);
                // 今日通过数
                int pass = articleReceiveService.examine1StatisticsPass(examiner1Id);
                // 今日退回数
                int unPass = articleReceiveService.examine1StatisticsUnPass(examiner1Id);
                // 今日审核中
                int examining = articleReceiveService.examine1StatisticsExamining(examiner1Id);
                // 今日作废数
                int abolish = articleReceiveService.examine1StatisticsAbolish(examiner1Id);
                // 如果数据全为0，则不记录该条记录
                int count = receive + pass + unPass + examining + abolish;
                if (count > 0) {
                    // 塞入实体，加入集合
                    Examine1StatisticsDay examine1StatisticsDay = new Examine1StatisticsDay();
                    examine1StatisticsDay.setUserId(examiner1Id);
                    examine1StatisticsDay.setReceive(receive);
                    examine1StatisticsDay.setPass(pass);
                    examine1StatisticsDay.setUnpass(unPass);
                    examine1StatisticsDay.setExaminingNum(examining);
                    examine1StatisticsDay.setAbolish(abolish);
                    examine1StatisticsDays.add(examine1StatisticsDay);
                }
            }
            // 向审核1每日统计表插入记录,一次400条
            int rows = 0;
            for (int i = 0; i < examine1StatisticsDays.size() / 400 + 1; i++) {
                int end = (i + 1) * 400;
                if (end >= examine1StatisticsDays.size()) {
                    end = examine1StatisticsDays.size();
                }
                List<Examine1StatisticsDay> list = examine1StatisticsDays.subList(i * 400, end);
                rows += examine1StatisticsDayService.insertExamine1StatisticsDay(list);

            }
            System.out.println("结束时间：" + now());
            System.out.println("受影响的行数：" + rows);
        }

    }

    /**
     * @param
     * @Author: BaiYang
     * @Description: 审核2每日统计
     * @Date: 2019/5/17
     * @return: void
     **/
    @Async
    @Scheduled(cron = "0 0 23 * * ?")
    public void examine2StatisticsDay() {
        ArrayList<Examine2StatisticsDay> examine2StatisticsDays = new ArrayList<>();
        // 获取审核2 id集合
        List<Integer> examiner2Ids = userService.selectAllExaminer2Id();
        if (ToolUtil.isNotEmpty(examiner2Ids)) {
            // 获取当前审核2 统计的各项数据
            for (Integer examiner2Id : examiner2Ids) {
                // 今日领取数
                int receive = articleReceiveService.examine2StatisticsReceive(examiner2Id);
                // 今日通过数
                int pass = articleReceiveService.examine2StatisticsPass(examiner2Id);
                // 今日退回数
                int unPass = articleReceiveService.examine2StatisticsUnPass(examiner2Id);
                // 今日审核中
                int examining = articleReceiveService.examine2StatisticsExamining(examiner2Id);
                // 今日作废数
                int abolish = articleReceiveService.examine2StatisticsAbolish(examiner2Id);
                // 如果数据全为0，则不记录该条记录
                int count = receive + pass + unPass + examining + abolish;
                if (count > 0) {
                    // 塞入实体，加入集合
                    Examine2StatisticsDay examine2StatisticsDay = new Examine2StatisticsDay();
                    examine2StatisticsDay.setUserId(examiner2Id);
                    examine2StatisticsDay.setReceive(receive);
                    examine2StatisticsDay.setPass(pass);
                    examine2StatisticsDay.setUnpass(unPass);
                    examine2StatisticsDay.setExaminingNum(examining);
                    examine2StatisticsDay.setAbolish(abolish);
                    examine2StatisticsDays.add(examine2StatisticsDay);
                }
            }
            // 向审核1每日统计表插入记录,一次400条
            int rows = 0;
            for (int i = 0; i < examine2StatisticsDays.size() / 400 + 1; i++) {
                int end = (i + 1) * 400;
                if (end >= examine2StatisticsDays.size()) {
                    end = examine2StatisticsDays.size();
                }
                List<Examine2StatisticsDay> list = examine2StatisticsDays.subList(i * 400, end);
                rows += examine2StatisticsDayService.insertExamine2StatisticsDay(list);
            }
            System.out.println("结束时间：" + now());
            System.out.println("受影响的行数：" + rows);
        }

    }

    /**
     * @param
     * @Author: BaiYang
     * @Description: 配图每日统计
     * @Date: 2019/5/17
     * @return: void
     **/
    @Async
    @Scheduled(cron = "0 20 11 * * ?")
    public void layoutStatisticsDay() {
        ArrayList<LayoutStatisticsDay> layoutStatisticsDays = new ArrayList<>();
        // 获取审核2 id集合
        List<Integer> layoutIds = userService.selectAllLayoutId();
        if (ToolUtil.isNotEmpty(layoutIds)) {
            // 获取当前配图 统计的各项数据
            for (Integer layoutId : layoutIds) {
                // 领取数
                int receive = articleReceiveService.layoutStatisticReceive(layoutId);
                // 配图文章数
                int articleNum = articleReceiveService.layoutStatisticArticleNum(layoutId);
                // 配图图片数
                int pictureNum = articleReceiveService.layoutStatisticPictureNum(layoutId);
                // 如果数据全为0，则不记录该条记录
                int count = receive + articleNum + pictureNum;
                if (count > 0) {
                    // 塞入实体，加入集合
                    LayoutStatisticsDay layoutStatisticsDay = new LayoutStatisticsDay();
                    layoutStatisticsDay.setUserId(layoutId);
                    layoutStatisticsDay.setReceive(receive);
                    layoutStatisticsDay.setArticleNum(articleNum);
                    layoutStatisticsDay.setPictureNum(pictureNum);
                    layoutStatisticsDays.add(layoutStatisticsDay);
                }
            }
            // 向文编每日统计表插入记录,一次400条
            int rows = 0;
            for (int i = 0; i < layoutStatisticsDays.size() / 400 + 1; i++) {
                int end = (i + 1) * 400;
                if (end >= layoutStatisticsDays.size()) {
                    end = layoutStatisticsDays.size();
                }
                List<LayoutStatisticsDay> list = layoutStatisticsDays.subList(i * 400, end);
                rows += layoutStatisticsDayService.insertLayoutStatisticsDay(list);
            }
            System.out.println("结束时间：" + now());
            System.out.println("受影响的行数：" + rows);
        }

    }

    /**
     * @param
     * @Author: BaiYang
     * @Description: 运营每日统计
     * @Date: 2019/5/17
     * @return: void
     **/
    @Async
    @Scheduled(cron = "0 0 23 * * ?")
    public void operateStatisticsDay() {
        ArrayList<OperateStatisticsDay> operateStatisticsDays = new ArrayList<>();
        // 获取运营 id集合
        List<Integer> operatorIds = userService.selectAllOperatorId();
        if (ToolUtil.isNotEmpty(operatorIds)) {
            // 获取当前配图 统计的各项数据
            for (Integer operatorId : operatorIds) {
                // 领取数
                int receive = articleReceiveService.operatorStatisticReceive(operatorId);
                // 作废数
                int abolish = articleReceiveService.operatorStatisticAbolish(operatorId);
                // 如果数据全为0，则不记录该条记录
                int count = receive + abolish;
                if (count > 0) {
                    // 塞入实体，加入集合
                    OperateStatisticsDay operateStatisticsDay = new OperateStatisticsDay();
                    operateStatisticsDay.setUserId(operatorId);
                    operateStatisticsDay.setReceive(receive);
                    operateStatisticsDay.setAbolish(abolish);
                    operateStatisticsDays.add(operateStatisticsDay);
                }
            }
            // 向文编每日统计表插入记录,一次400条
            int rows = 0;
            for (int i = 0; i < operateStatisticsDays.size() / 400 + 1; i++) {
                int end = (i + 1) * 400;
                if (end >= operateStatisticsDays.size()) {
                    end = operateStatisticsDays.size();
                }
                List<OperateStatisticsDay> list = operateStatisticsDays.subList(i * 400, end);
                rows += operateStatisticsDayService.insertOperateStatisticsDay(list);
            }
            System.out.println("结束时间：" + now());
            System.out.println("受影响的行数：" + rows);
        }

    }

    /**
     * @param
     * @Author: BaiYang
     * @Description: 文编每月统计结算
     * @Date: 2019/5/20
     * @return: void
     **/
    @Async
    @Scheduled(cron = "0 0 1 1 * ?")
    public void editorStatisticsMonth() {
        ArrayList<EditorStatisticsMonth> editorStatisticsMonths = new ArrayList<>();
        // 获取文编id 集合
        List<Integer> editorIds = userService.selectAllEditorId();
        if (ToolUtil.isNotEmpty(editorIds)) {
            // 每个文编上月信息统计
            for (Integer editorId : editorIds) {
                // 上月信息合计
                EditorStatisticsMonth editorStatisticsMonth = editorStatisticsMonthService.statisticEditorLastMonth(editorId);
                if (ToolUtil.isNotEmpty(editorStatisticsMonth)) {
                    // 结算上月积分
                    User user = userService.selectById(editorId);
                    Integer balance = user.getBalance();
                    Integer price = user.getPrice();
                    Integer settlementAmount = 0;
                    // 判断是否单独设置了结算单价
                    if (ToolUtil.isEmpty(price) || price.equals(0)) {
                        // 获取默认结算单价
                        List<DefaultUnitPrice> defaultUnitPrices = defaultUnitPriceService.selectList(null);
                        Integer settlementPrice = defaultUnitPrices.get(0).getSettlementPrice();
                        settlementAmount = editorStatisticsMonth.getPass() * settlementPrice;
                    } else {
                        settlementAmount = editorStatisticsMonth.getPass() * price;
                    }
                    // 完善上月统计信息
                    editorStatisticsMonth.setUserId(editorId);
                    editorStatisticsMonth.setSettlementAmount(settlementAmount);
                    // 加入集合
                    editorStatisticsMonths.add(editorStatisticsMonth);
                    if (settlementAmount > 0) {
                        // 更新文编资产
                        EntityWrapper<User> ew = new EntityWrapper<>();
                        ew.eq("id", editorId);
                        String setSql = "balance = balance +" + settlementAmount;
                        userService.updateForSet(setSql, ew);
                        // 生成交易流水
                        TransWater transWater = new TransWater();
                        transWater.setUserId(editorId);
                        transWater.setCreateUser(0);
                        transWater.setAmount(settlementAmount);
                        // 1 现金 2积分
                        transWater.setTransType(2);
                        // 1消费、2充值、3文编结算、4文编提现、5提现失败退款
                        transWater.setTransStatus(3);
                        transWater.setPointsBalance(balance + settlementAmount);
                        transWater.setCreateTime(now());
                        transWaterService.insert(transWater);
                    }
                }

            }
            // 向文编每月统计表插入记录,一次400条
            int rows = 0;
            for (int i = 0; i < editorStatisticsMonths.size() / 400 + 1; i++) {
                int end = (i + 1) * 400;
                if (end >= editorStatisticsMonths.size()) {
                    end = editorStatisticsMonths.size();
                }
                List<EditorStatisticsMonth> list = editorStatisticsMonths.subList(i * 400, end);
                rows += editorStatisticsMonthService.insertEditorStatisticsMonth(list);
            }
            System.out.println("结束时间：" + now());
            System.out.println("受影响的行数：" + rows);
        }

    }

    /**
     * @param
     * @Author: BaiYang
     * @Description: 审核1每月统计
     * @Date: 2019/5/20
     * @return: void
     **/
    @Async
    @Scheduled(cron = "0 0 1 1 * ?")
    public void examine1StatisticsMonth() {
        ArrayList<Examine1StatisticsMonth> examine1StatisticsMonths = new ArrayList<>();
        // 获取审核1id 集合
        List<Integer> examiner1Ids = userService.selectAllExaminer1Id();
        if (ToolUtil.isNotEmpty(examiner1Ids)) {
            // 获取当前审核1人员的上月统计信息
            for (Integer examiner1Id : examiner1Ids) {
                Examine1StatisticsMonth examine1StatisticsMonth = examine1StatisticsMonthService.statisticExamine1LastMonth(examiner1Id);
                if (ToolUtil.isNotEmpty(examine1StatisticsMonth)) {
                    // 完善统计信息
                    examine1StatisticsMonth.setUserId(examiner1Id);
                    // 加入集合
                    examine1StatisticsMonths.add(examine1StatisticsMonth);
                }
            }
            // 向审核1每月统计表插入记录,一次400条
            int rows = 0;
            for (int i = 0; i < examine1StatisticsMonths.size() / 400 + 1; i++) {
                int end = (i + 1) * 400;
                if (end >= examine1StatisticsMonths.size()) {
                    end = examine1StatisticsMonths.size();
                }
                List<Examine1StatisticsMonth> list = examine1StatisticsMonths.subList(i * 400, end);
                rows += examine1StatisticsMonthService.insertExamine1StatisticsMonth(list);
            }
            System.out.println("结束时间：" + now());
            System.out.println("受影响的行数：" + rows);
        }

    }

    /**
     * @param
     * @Author: BaiYang
     * @Description: 审核2每月统计
     * @Date: 2019/5/20
     * @return: void
     **/
    @Async
    @Scheduled(cron = "0 0 1 1 * ?")
    public void examine2StatisticsMonth() {
        ArrayList<Examine2StatisticsMonth> examine2StatisticsMonths = new ArrayList<>();
        // 获取审核2id 集合
        List<Integer> examiner2Ids = userService.selectAllExaminer2Id();
        if (ToolUtil.isNotEmpty(examiner2Ids)) {
            // 获取当前审核2人员的上月统计信息
            for (Integer examiner2Id : examiner2Ids) {
                Examine2StatisticsMonth examine2StatisticsMonth = examine2StatisticsMonthService.statisticExamine2LastMonth(examiner2Id);
                if (ToolUtil.isNotEmpty(examine2StatisticsMonth)) {
                    // 完善统计信息
                    examine2StatisticsMonth.setUserId(examiner2Id);
                    // 加入集合
                    examine2StatisticsMonths.add(examine2StatisticsMonth);
                }
            }
            // 向审核2每月统计表插入记录,一次400条
            int rows = 0;
            for (int i = 0; i < examine2StatisticsMonths.size() / 400 + 1; i++) {
                int end = (i + 1) * 400;
                if (end >= examine2StatisticsMonths.size()) {
                    end = examine2StatisticsMonths.size();
                }
                List<Examine2StatisticsMonth> list = examine2StatisticsMonths.subList(i * 400, end);
                rows += examine2StatisticsMonthService.insertExamine2StatisticsMonth(list);
            }
            System.out.println("结束时间：" + now());
            System.out.println("受影响的行数：" + rows);
        }

    }

    /**
     * @param
     * @Author: BaiYang
     * @Description: 配图每月统计
     * @Date: 2019/5/20
     * @return: void
     **/
    @Async
    @Scheduled(cron = "0 0 1 1 * ?")
    public void layoutStatisticsMonth() {
        ArrayList<LayoutStatisticsMonth> layoutStatisticsMonths = new ArrayList<>();
        // 获取配图id 集合
        List<Integer> layoutIds = userService.selectAllLayoutId();
        if (ToolUtil.isNotEmpty(layoutIds)) {
            // 获取当前配图人员的上月统计信息
            for (Integer layoutId : layoutIds) {
                LayoutStatisticsMonth layoutStatisticsMonth = layoutStatisticsMonthService.statisticLayoutLastMonth(layoutId);
                if (ToolUtil.isNotEmpty(layoutStatisticsMonth)) {
                    // 完善统计信息
                    layoutStatisticsMonth.setUserId(layoutId);
                    // 加入集合
                    layoutStatisticsMonths.add(layoutStatisticsMonth);
                }
            }
            // 向配图每月统计表插入记录,一次400条
            int rows = 0;
            for (int i = 0; i < layoutStatisticsMonths.size() / 400 + 1; i++) {
                int end = (i + 1) * 400;
                if (end >= layoutStatisticsMonths.size()) {
                    end = layoutStatisticsMonths.size();
                }
                List<LayoutStatisticsMonth> list = layoutStatisticsMonths.subList(i * 400, end);
                rows += layoutStatisticsMonthService.insertLayoutStatisticsMonth(list);
            }
            System.out.println("结束时间：" + now());
            System.out.println("受影响的行数：" + rows);
        }

    }

    /**
     * @param
     * @Author: BaiYang
     * @Description: 运营每月统计
     * @Date: 2019/5/20
     * @return: void
     **/
    @Async
    @Scheduled(cron = "0 0 1 1 * ?")
    public void operateStatisticsMonth() {
        ArrayList<OperateStatisticsMonth> operateStatisticsMonths = new ArrayList<>();
        // 获取运营id 集合
        List<Integer> operatorIds = userService.selectAllOperatorId();
        if (ToolUtil.isNotEmpty(operatorIds)) {
            // 获取当前运营人员的上月统计信息
            for (Integer operatorId : operatorIds) {
                OperateStatisticsMonth operateStatisticsMonth = operateStatisticsMonthService.statisticOperatorLastMonth(operatorId);
                if (ToolUtil.isNotEmpty(operateStatisticsMonth)) {
                    // 完善统计信息
                    operateStatisticsMonth.setUserId(operatorId);
                    // 加入集合
                    operateStatisticsMonths.add(operateStatisticsMonth);
                }
            }
            // 向运营每月统计表插入记录,一次400条
            int rows = 0;
            for (int i = 0; i < operateStatisticsMonths.size() / 400 + 1; i++) {
                int end = (i + 1) * 400;
                if (end >= operateStatisticsMonths.size()) {
                    end = operateStatisticsMonths.size();
                }
                List<OperateStatisticsMonth> list = operateStatisticsMonths.subList(i * 400, end);
                rows += operateStatisticsMonthService.insertOperateStatisticsMonth(list);
            }
            System.out.println("结束时间：" + now());
            System.out.println("受影响的行数：" + rows);
        }

    }

}
