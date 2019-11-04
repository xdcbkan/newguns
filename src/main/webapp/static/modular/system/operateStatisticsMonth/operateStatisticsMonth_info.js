/**
 * 初始化运营每月统计详情对话框
 */
var OperateStatisticsMonthInfoDlg = {
    operateStatisticsMonthInfoData : {}
};

/**
 * 清除数据
 */
OperateStatisticsMonthInfoDlg.clearData = function() {
    this.operateStatisticsMonthInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OperateStatisticsMonthInfoDlg.set = function(key, val) {
    this.operateStatisticsMonthInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OperateStatisticsMonthInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OperateStatisticsMonthInfoDlg.close = function() {
    parent.layer.close(window.parent.OperateStatisticsMonth.layerIndex);
}

/**
 * 收集数据
 */
OperateStatisticsMonthInfoDlg.collectData = function() {
    this
    .set('id')
    .set('receive')
    .set('userId')
    .set('abolish')
    .set('statisticsTime');
}

/**
 * 提交添加
 */
OperateStatisticsMonthInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/operateStatisticsMonth/add", function(data){
        Feng.success("添加成功!");
        window.parent.OperateStatisticsMonth.table.refresh();
        OperateStatisticsMonthInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.operateStatisticsMonthInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
OperateStatisticsMonthInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/operateStatisticsMonth/update", function(data){
        Feng.success("修改成功!");
        window.parent.OperateStatisticsMonth.table.refresh();
        OperateStatisticsMonthInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.operateStatisticsMonthInfoData);
    ajax.start();
}

$(function() {

});
