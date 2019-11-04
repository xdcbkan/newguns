/**
 * 初始化配图每月统计详情对话框
 */
var LayoutStatisticsMonthInfoDlg = {
    layoutStatisticsMonthInfoData : {}
};

/**
 * 清除数据
 */
LayoutStatisticsMonthInfoDlg.clearData = function() {
    this.layoutStatisticsMonthInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LayoutStatisticsMonthInfoDlg.set = function(key, val) {
    this.layoutStatisticsMonthInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LayoutStatisticsMonthInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LayoutStatisticsMonthInfoDlg.close = function() {
    parent.layer.close(window.parent.LayoutStatisticsMonth.layerIndex);
}

/**
 * 收集数据
 */
LayoutStatisticsMonthInfoDlg.collectData = function() {
    this
    .set('id')
    .set('receive')
    .set('userId')
    .set('articleNum')
    .set('pictureNum')
    .set('statisticsTime');
}

/**
 * 提交添加
 */
LayoutStatisticsMonthInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/layoutStatisticsMonth/add", function(data){
        Feng.success("添加成功!");
        window.parent.LayoutStatisticsMonth.table.refresh();
        LayoutStatisticsMonthInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.layoutStatisticsMonthInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
LayoutStatisticsMonthInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/layoutStatisticsMonth/update", function(data){
        Feng.success("修改成功!");
        window.parent.LayoutStatisticsMonth.table.refresh();
        LayoutStatisticsMonthInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.layoutStatisticsMonthInfoData);
    ajax.start();
}

$(function() {

});
