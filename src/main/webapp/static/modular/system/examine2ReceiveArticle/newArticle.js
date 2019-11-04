/**
 * 全部文章管理初始化
 */
var NewArticle = {
    id: "NewArticleTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
NewArticle.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
        {title: '文章编号', field: 'article_num', visible: true, align: 'center', valign: 'middle'},
        {title: '文章情况', field: 'prefix', visible: true, align: 'center', valign: 'middle'},
        {title: '主标题', field: 'new_main_title', visible: true, align: 'center', valign: 'middle'},
        {title: '副标题', field: 'new_subheading', visible: true, align: 'center', valign: 'middle'},
        {title: '所属分类', field: 'articleTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '领取时间', field: 'update_time', visible: true, align: 'center', valign: 'middle'},
        {title: '文章作者', field: 'createUserName', visible: true, align: 'center', valign: 'middle'},
        {title: '当前状态', field: 'articleStatus', visible: true, align: 'center', valign: 'middle'},
        {title: '时效性', field: 'prescription', visible: true, align: 'center', valign: 'middle'},
        {title: '是否下载', field: 'isDownloadName', visible: true, align: 'center', valign: 'middle'},
        {title: '下载地址', field: 'dowanload_url_shen_he', visible: true, align: 'center', valign: 'middle', formatter: operateFormatter},
        {title: '退回/作废原因', field: 'return_message', visible: true, align: 'center', valign: 'middle'}
    ];
};

function operateFormatter(value, row, index) {
    return ['<a target="_blank" href="'+row['dowanload_url_shen_he']+'">点击查看</a>'
    ].join('')
}

/**
 * 检查是否选中
 */
NewArticle.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0) {
        Feng.info("请先选中列表中需要领取的一篇文章！");
        return false;
    }else if(selected.length > 1){
        Feng.info("已选中多条，请点击批量按钮！");
        return false;
    }else{
        NewArticle.seItem = selected[0];
        return true;
    }
};

/**
 * 检查是否选中
 * 复选
 */
NewArticle.checks = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中列表中需要领取的一篇文章！");
        return false;
    }else{
        var idS = '';
        for (var i = 0;i < selected.length;i++){
            idS = idS + selected[i].id;
            idS += ",";
        }
        NewArticle.seItem = idS;
        return true;
    }
};

/**
 * 点击添加全部文章
 */
NewArticle.openAddNewArticle = function () {
    var index = layer.open({
        type: 2,
        title: '添加全部文章',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/newArticle/newArticle_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看全部文章详情
 */
NewArticle.openNewArticleDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文章详情',
            area: ['1100px', '700px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/newArticle/newArticle2_update/' + NewArticle.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 领取文章
 */
NewArticle.receive = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/newArticle/examine1receive", function (data) {
            if (data.code === 20000|| data.code === 30000) {
                Feng.error(data.message + "!");
            } else {
                Feng.success("文章已成功领取,可在已领取文章中进行审核!");
                NewArticle.table.refresh();
            }
        }, function (data) {
            Feng.error("文章领取失败!" + data.responseJSON.message + "!");
        });
        ajax.set("newArticleId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 批量审核文章
 */
NewArticle.auditArticles = function () {
    if (this.checks()) {
        var ajax = new $ax(Feng.ctxPath + "/newArticle/examineUpdates", function (data) {
            if (data.code === 20000|| data.code === 30000) {
                Feng.error(data.message + "!");
            } else {
                Feng.success("文章已保存更改并通过审核!");
                NewArticle.table.refresh();
            }
        }, function (data) {
            Feng.error("审核失败!" + data.responseJSON.message + "!");
        });
        ajax.set("newArticleIds",this.seItem);
        ajax.start();
    }
};

/**
 * 导出文章
 */
NewArticle.exportWord = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/newArticle/exportWord", function (data) {
            if (data.code === 20000|| data.code === 30000) {
                Feng.error(data.message + "!");
            } else {
                Feng.success("导出成功,已保存到桌面！");
                NewArticle.table.refresh();
            }
        }, function (data) {
            Feng.error("导出失败!" + data.responseJSON.message + "!");
        });
        ajax.set("newArticleId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 批量导出文章
 */
NewArticle.exportWords = function () {
    if (this.checks()) {
        var ajax = new $ax(Feng.ctxPath + "/newArticle/exportWords", function (data) {
            if (data.code === 20000|| data.code === 30000) {
                Feng.error(data.message + "!");
            } else {
                Feng.success("操作成功,请点击查看！");
                NewArticle.table.refresh();
            }
        }, function (data) {
            Feng.error("导出失败!" + data.responseJSON.message + "!");
        });
        ajax.set("newArticleIds",this.seItem);
        ajax.start();
    }
};

/**
 * 查询全部文章列表
 */
NewArticle.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['articleTypeId'] = $("#articleTypeId").val();
    queryData['prescription'] = $("#prescription").val();
    NewArticle.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = NewArticle.initColumn();
    var table = new BSTable(NewArticle.id, "/newArticle/articleReceiveList", defaultColunms);
    table.setPaginationType("client");
    NewArticle.table = table.init();
});
