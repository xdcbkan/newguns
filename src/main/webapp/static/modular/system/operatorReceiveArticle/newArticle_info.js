/**
 * 初始化全部文章详情对话框
 */
var NewArticleInfoDlg = {
    newArticleInfoData : {},
    validateFields: {
        returnMessage: {
            validators: {
                notEmpty: {
                    message: '退回原因不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
NewArticleInfoDlg.clearData = function() {
    this.newArticleInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NewArticleInfoDlg.set = function(key, val) {
    this.newArticleInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NewArticleInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
NewArticleInfoDlg.close = function() {
    parent.layer.close(window.parent.NewArticle.layerIndex);
}

/**
 * 收集数据
 */
NewArticleInfoDlg.collectData = function() {
    this
    .set('id')
    .set('articleNum')
    .set('mainTitle')
    .set('subheading')
    .set('content')
    .set('prefix')
    .set('articleStatus')
    .set('pictureNum')
    .set('articleTypeId')
    .set('examine1Id')
    .set('examine2Id')
    .set('layoutId')
    .set('operateId')
    .set('purchaseId')
    .set('editorNewFlag')
    .set('returnMessage')
    .set('createTime')
    .set('updateTime')
    .set('createUserId')
    .set('updateUserId')
    .set('articlePrice')
    .set('prescription')
    .set('label')
    .set('projectTypeId')
    .set('illustrationType');
}


/**
 * 验证作废是否为空
 */
NewArticleInfoDlg.validateRmg = function () {
    var returnMessage = this.get("returnMessage");
    if (returnMessage && returnMessage.trim("") != "") {
        return true;
    } else {
        return false;
    }
};

/**
 * 作废
 */
NewArticleInfoDlg.returnSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validateRmg()) {
        Feng.error("作废操作时作废原因不能为空!");
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/newArticle/cancelArticle", function(data){
        Feng.success("该文章已成功作废!");
        window.parent.NewArticle.table.refresh();
        NewArticleInfoDlg.close();
    },function(data){
        Feng.error("文章作废失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.newArticleInfoData);
    ajax.start();
}

$(function() {

});

// =======================================

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
    parent.layer.close(window.parent.NewArticle.layerIndex);
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
    var ajax = new $ax(Feng.ctxPath + "/newArticle/addAddress", function(data){
        Feng.success("添加成功!");
        window.parent.NewArticle.table.refresh();
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
    var ajax = new $ax(Feng.ctxPath + "/newArticle/updateAddress", function(data){
        Feng.success("修改成功!");
        window.parent.NewArticle.table.refresh();
        ArticlePublishAddressInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.articlePublishAddressInfoData);
    ajax.start();
}

$(function() {

});

