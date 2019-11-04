/**
 * 初始化配图每日工作统计详情对话框
 */
var LayoutStatisticsDayInfoDlg = {
    layoutStatisticsDayInfoData : {}
};

/**
 * 清除数据
 */
LayoutStatisticsDayInfoDlg.clearData = function() {
    this.layoutStatisticsDayInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LayoutStatisticsDayInfoDlg.set = function(key, val) {
    this.layoutStatisticsDayInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LayoutStatisticsDayInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LayoutStatisticsDayInfoDlg.close = function() {
    parent.layer.close(window.parent.LayoutStatisticsDay.layerIndex);
}

/**
 * 收集数据
 */
LayoutStatisticsDayInfoDlg.collectData = function() {
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
LayoutStatisticsDayInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/layoutStatisticsDay/add", function(data){
        Feng.success("添加成功!");
        window.parent.LayoutStatisticsDay.table.refresh();
        LayoutStatisticsDayInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.layoutStatisticsDayInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
LayoutStatisticsDayInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/layoutStatisticsDay/update", function(data){
        Feng.success("修改成功!");
        window.parent.LayoutStatisticsDay.table.refresh();
        LayoutStatisticsDayInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.layoutStatisticsDayInfoData);
    ajax.start();
}

$(function() {

});
