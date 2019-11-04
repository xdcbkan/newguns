/**
 * 初始化全部文章详情对话框
 */
var NewArticleInfoDlg = {
    newArticleInfoData: {},
    checkNum: 0,           // 查重计数
    textContent: null,      // 纯文本
    contentArray: null,      // 纯文本分割的数组
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
NewArticleInfoDlg.clearData = function () {
    this.newArticleInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NewArticleInfoDlg.set = function (key, val) {
    this.newArticleInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NewArticleInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
NewArticleInfoDlg.close = function () {
    parent.layer.close(window.parent.NewArticle.layerIndex);
}

/**
 * 收集数据
 */
NewArticleInfoDlg.collectData = function () {
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
 * 审核通过
 */
NewArticleInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/newArticle/pass", function (data) {
        Feng.success("该文章已通过审核!");
        window.parent.NewArticle.table.refresh();
        NewArticleInfoDlg.close();
    }, function (data) {
        Feng.error("审核通过失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.newArticleInfoData);
    ajax.start();
}

/**
 * 审核通过
 */
NewArticleInfoDlg.submitPass = function () {

    this.clearData();
    this.collectData();

    // 提交信息
    var ajax = new $ax(Feng.ctxPath + "/newArticle/examineUpdate", function (data) {
        Feng.success("该文章已保存更改并通过审核!");
        window.parent.NewArticle.table.refresh();
        NewArticleInfoDlg.close();
    }, function (data) {
        Feng.error("保存更改并通过审核失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.newArticleInfoData);
    ajax.start();
}

/**
 * 验证退回原因是否为空
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
 * 退回
 */
NewArticleInfoDlg.returnSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validateRmg()) {
        Feng.error("退回操作时退回原因不能为空!");
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/newArticle/returnArticle", function (data) {
        Feng.success("该文章已成功退回!");
        window.parent.NewArticle.table.refresh();
        NewArticleInfoDlg.close();
    }, function (data) {
        Feng.error("文章退回失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.newArticleInfoData);
    ajax.start();
}


/**
 * 百度查重
 */
NewArticleInfoDlg.checkRepeat = function () {
    let queryData = ""
    // 判断是否是接着上一次操作
    if (NewArticleInfoDlg.contentArray == null) {
        let articleContent = $("#content").val()
        NewArticleInfoDlg.textContent = articleContent.replace(/<[^>]+>/g, "").replace(/\s+/g, "");
        NewArticleInfoDlg.contentArray = NewArticleInfoDlg.textContent.split(/[，。！；：、,.!;:]/)
    }
    // 判断是否查重完毕
    if (NewArticleInfoDlg.checkNum >= NewArticleInfoDlg.contentArray.length) {
        Feng.info("全部查重完毕！");
    } else {
        // 这一次开始查询的位置
        for (let i = NewArticleInfoDlg.checkNum; i < NewArticleInfoDlg.contentArray.length; i++) {
            queryData += NewArticleInfoDlg.contentArray[i]
            // 判断查询内容长度
            if (queryData.length > 38) {
                queryData = queryData.replace(NewArticleInfoDlg.contentArray[i], "")
                NewArticleInfoDlg.checkNum = i
                break
            }
            // 刚好达到38 或 在未达到38时，已经没有可查询内容
            if (queryData.length == 38 || i == NewArticleInfoDlg.contentArray.length - 1) {
                NewArticleInfoDlg.checkNum = i + 1
                break
            }

        }
        window.open("https://www.baidu.com/s?ie=utf-8&f=3&rsv_bp=1&rsv_idx=1&tn=baidu&wd=" + queryData)
    }

};


$(function () {
    $("#articleTypeId").val($("#articleTypeIdValue").val());
});
