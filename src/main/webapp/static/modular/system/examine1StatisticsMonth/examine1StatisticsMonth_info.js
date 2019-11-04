/**
 * 初始化审核1每月统计详情对话框
 */
var Examine1StatisticsMonthInfoDlg = {
    examine1StatisticsMonthInfoData : {}
};

/**
 * 清除数据
 */
Examine1StatisticsMonthInfoDlg.clearData = function() {
    this.examine1StatisticsMonthInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Examine1StatisticsMonthInfoDlg.set = function(key, val) {
    this.examine1StatisticsMonthInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Examine1StatisticsMonthInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
Examine1StatisticsMonthInfoDlg.close = function() {
    parent.layer.close(window.parent.Examine1StatisticsMonth.layerIndex);
}

/**
 * 收集数据
 */
Examine1StatisticsMonthInfoDlg.collectData = function() {
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
Examine1StatisticsMonthInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/examine1StatisticsMonth/add", function(data){
        Feng.success("添加成功!");
        window.parent.Examine1StatisticsMonth.table.refresh();
        Examine1StatisticsMonthInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.examine1StatisticsMonthInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
Examine1StatisticsMonthInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/examine1StatisticsMonth/update", function(data){
        Feng.success("修改成功!");
        window.parent.Examine1StatisticsMonth.table.refresh();
        Examine1StatisticsMonthInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.examine1StatisticsMonthInfoData);
    ajax.start();
}

$(function() {

});
