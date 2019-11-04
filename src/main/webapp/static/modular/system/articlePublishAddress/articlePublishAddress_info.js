/**
 * 初始化文章发布地址详情对话框
 */
var ArticlePublishAddressInfoDlg = {
    articlePublishAddressInfoData : {}
};

/**
 * 清除数据
 */
ArticlePublishAddressInfoDlg.clearData = function() {
    this.articlePublishAddressInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ArticlePublishAddressInfoDlg.set = function(key, val) {
    this.articlePublishAddressInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ArticlePublishAddressInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ArticlePublishAddressInfoDlg.close = function() {
    parent.layer.close(window.parent.ArticlePublishAddress.layerIndex);
}

/**
 * 收集数据
 */
ArticlePublishAddressInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('articleId')
    .set('address1')
    .set('address2')
    .set('address3')
    .set('address4')
    .set('address5')
    .set('address6')
    .set('address7')
    .set('address8')
    .set('address9')
    .set('address10')
    .set('address11')
    .set('address12')
    .set('address13')
    .set('address14')
    .set('address15')
    .set('address16')
    .set('address17')
    .set('address18')
    .set('address19')
    .set('address20');
}

/**
 * 提交添加
 */
ArticlePublishAddressInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/articlePublishAddress/add", function(data){
        Feng.success("添加成功!");
        window.parent.ArticlePublishAddress.table.refresh();
        ArticlePublishAddressInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.articlePublishAddressInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ArticlePublishAddressInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/articlePublishAddress/update", function(data){
        Feng.success("修改成功!");
        window.parent.ArticlePublishAddress.table.refresh();
        ArticlePublishAddressInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.articlePublishAddressInfoData);
    ajax.start();
}

$(function() {

});
