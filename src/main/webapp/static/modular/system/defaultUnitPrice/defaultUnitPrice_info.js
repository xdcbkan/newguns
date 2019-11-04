/**
 * 初始化文章默认售价详情对话框
 */
var DefaultUnitPriceInfoDlg = {
    defaultUnitPriceInfoData : {}
};

/**
 * 清除数据
 */
DefaultUnitPriceInfoDlg.clearData = function() {
    this.defaultUnitPriceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DefaultUnitPriceInfoDlg.set = function(key, val) {
    this.defaultUnitPriceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DefaultUnitPriceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DefaultUnitPriceInfoDlg.close = function() {
    parent.layer.close(window.parent.DefaultUnitPrice.layerIndex);
}

/**
 * 收集数据
 */
DefaultUnitPriceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('purchasePrice')
    .set('settlementPrice');
}

/**
 * 提交添加
 */
DefaultUnitPriceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/defaultUnitPrice/add", function(data){
        Feng.success("添加成功!");
        window.parent.DefaultUnitPrice.table.refresh();
        DefaultUnitPriceInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.defaultUnitPriceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DefaultUnitPriceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/defaultUnitPrice/update", function(data){
        Feng.success("修改成功!");
        window.parent.DefaultUnitPrice.table.refresh();
        DefaultUnitPriceInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.defaultUnitPriceInfoData);
    ajax.start();
}

$(function() {

});
