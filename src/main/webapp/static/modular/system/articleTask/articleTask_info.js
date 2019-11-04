/**
 * 初始化文章任务表详情对话框
 */
var ArticleTaskInfoDlg = {
    articleTaskInfoData: {}
};

/**
 * 清除数据
 */
ArticleTaskInfoDlg.clearData = function () {
    this.articleTaskInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ArticleTaskInfoDlg.set = function (key, val) {
    this.articleTaskInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ArticleTaskInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ArticleTaskInfoDlg.close = function () {
    parent.layer.close(window.parent.ArticleTask.layerIndex);
}

/**
 * 收集数据
 */
ArticleTaskInfoDlg.collectData = function () {
    this
        .set('id')
        .set('mainTitle')
        .set('subheading')
        .set('content')
        .set('timelinessCategory')
        .set('taskStatus')
        .set('type')
        .set('url');
}

/**
 * 提交添加
 */
ArticleTaskInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/articleTask/add", function (data) {
        Feng.success("添加成功!");
        window.parent.ArticleTask.table.refresh();
        ArticleTaskInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.articleTaskInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ArticleTaskInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/articleTask/update", function (data) {
        if (data.code === 20000) {
            Feng.error(data.message + "!");
        } else {
            Feng.success("修改成功!");
        }
        window.parent.ArticleTask.table.refresh();
        ArticleTaskInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.articleTaskInfoData);
    ajax.start();
}

$(function () {

});
