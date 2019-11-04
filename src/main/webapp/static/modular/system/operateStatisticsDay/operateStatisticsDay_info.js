/**
 * 初始化运营每日工作统计详情对话框
 */
var OperateStatisticsDayInfoDlg = {
    operateStatisticsDayInfoData : {}
};

/**
 * 清除数据
 */
OperateStatisticsDayInfoDlg.clearData = function() {
    this.operateStatisticsDayInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OperateStatisticsDayInfoDlg.set = function(key, val) {
    this.operateStatisticsDayInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OperateStatisticsDayInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OperateStatisticsDayInfoDlg.close = function() {
    parent.layer.close(window.parent.OperateStatisticsDay.layerIndex);
}

/**
 * 收集数据
 */
OperateStatisticsDayInfoDlg.collectData = function() {
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
OperateStatisticsDayInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/operateStatisticsDay/add", function(data){
        Feng.success("添加成功!");
        window.parent.OperateStatisticsDay.table.refresh();
        OperateStatisticsDayInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.operateStatisticsDayInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
OperateStatisticsDayInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/operateStatisticsDay/update", function(data){
        Feng.success("修改成功!");
        window.parent.OperateStatisticsDay.table.refresh();
        OperateStatisticsDayInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.operateStatisticsDayInfoData);
    ajax.start();
}

$(function() {

});
