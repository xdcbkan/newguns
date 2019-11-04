/**
 * 初始化审核2每日工作统计详情对话框
 */
var Examine2StatisticsDayInfoDlg = {
    examine2StatisticsDayInfoData : {}
};

/**
 * 清除数据
 */
Examine2StatisticsDayInfoDlg.clearData = function() {
    this.examine2StatisticsDayInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Examine2StatisticsDayInfoDlg.set = function(key, val) {
    this.examine2StatisticsDayInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Examine2StatisticsDayInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
Examine2StatisticsDayInfoDlg.close = function() {
    parent.layer.close(window.parent.Examine2StatisticsDay.layerIndex);
}

/**
 * 收集数据
 */
Examine2StatisticsDayInfoDlg.collectData = function() {
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
Examine2StatisticsDayInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/examine2StatisticsDay/add", function(data){
        Feng.success("添加成功!");
        window.parent.Examine2StatisticsDay.table.refresh();
        Examine2StatisticsDayInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.examine2StatisticsDayInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
Examine2StatisticsDayInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/examine2StatisticsDay/update", function(data){
        Feng.success("修改成功!");
        window.parent.Examine2StatisticsDay.table.refresh();
        Examine2StatisticsDayInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.examine2StatisticsDayInfoData);
    ajax.start();
}

$(function() {

});
