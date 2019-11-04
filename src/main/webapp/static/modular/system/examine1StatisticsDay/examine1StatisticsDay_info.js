/**
 * 初始化审核1每日工作统计详情对话框
 */
var Examine1StatisticsDayInfoDlg = {
    examine1StatisticsDayInfoData : {}
};

/**
 * 清除数据
 */
Examine1StatisticsDayInfoDlg.clearData = function() {
    this.examine1StatisticsDayInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Examine1StatisticsDayInfoDlg.set = function(key, val) {
    this.examine1StatisticsDayInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Examine1StatisticsDayInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
Examine1StatisticsDayInfoDlg.close = function() {
    parent.layer.close(window.parent.Examine1StatisticsDay.layerIndex);
}

/**
 * 收集数据
 */
Examine1StatisticsDayInfoDlg.collectData = function() {
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
Examine1StatisticsDayInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/examine1StatisticsDay/add", function(data){
        Feng.success("添加成功!");
        window.parent.Examine1StatisticsDay.table.refresh();
        Examine1StatisticsDayInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.examine1StatisticsDayInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
Examine1StatisticsDayInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/examine1StatisticsDay/update", function(data){
        Feng.success("修改成功!");
        window.parent.Examine1StatisticsDay.table.refresh();
        Examine1StatisticsDayInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.examine1StatisticsDayInfoData);
    ajax.start();
}

$(function() {

});
