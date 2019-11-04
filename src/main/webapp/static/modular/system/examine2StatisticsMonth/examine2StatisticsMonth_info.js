/**
 * 初始化审核2每月统计详情对话框
 */
var Examine2StatisticsMonthInfoDlg = {
    examine2StatisticsMonthInfoData : {}
};

/**
 * 清除数据
 */
Examine2StatisticsMonthInfoDlg.clearData = function() {
    this.examine2StatisticsMonthInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Examine2StatisticsMonthInfoDlg.set = function(key, val) {
    this.examine2StatisticsMonthInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Examine2StatisticsMonthInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
Examine2StatisticsMonthInfoDlg.close = function() {
    parent.layer.close(window.parent.Examine2StatisticsMonth.layerIndex);
}

/**
 * 收集数据
 */
Examine2StatisticsMonthInfoDlg.collectData = function() {
    this
    .set('id')
    .set('receive')
    .set('pass')
    .set('unpass')
    .set('examiningNum')
    .set('userId')
    .set('abolish')
    .set('statisticsTime');
}

/**
 * 提交添加
 */
Examine2StatisticsMonthInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/examine2StatisticsMonth/add", function(data){
        Feng.success("添加成功!");
        window.parent.Examine2StatisticsMonth.table.refresh();
        Examine2StatisticsMonthInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.examine2StatisticsMonthInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
Examine2StatisticsMonthInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/examine2StatisticsMonth/update", function(data){
        Feng.success("修改成功!");
        window.parent.Examine2StatisticsMonth.table.refresh();
        Examine2StatisticsMonthInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.examine2StatisticsMonthInfoData);
    ajax.start();
}

$(function() {

});
