/**
 * 文章历史版本记录管理初始化
 */
var HistoricalArticle = {
    id: "HistoricalArticleTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
HistoricalArticle.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '文章编号', field: 'article_num', visible: true, align: 'center', valign: 'middle'},
            {title: '主标题', field: 'main_title', visible: true, align: 'center', valign: 'middle'},
            {title: '副标题', field: 'subheading', visible: true, align: 'center', valign: 'middle'},
            {title: '前缀', field: 'prefix', visible: true, align: 'center', valign: 'middle'},
            {title: '文章状态', field: 'articleStatus', visible: true, align: 'center', valign: 'middle'},
            {title: '配图数', field: 'picture_num', visible: true, align: 'center', valign: 'middle'},
            {title: '文章分类', field: 'articleTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '审1领取人id', field: 'examine1Id', visible: true, align: 'center', valign: 'middle'},
            {title: '审2领取人id', field: 'examine2Id', visible: true, align: 'center', valign: 'middle'},
            {title: '配图人id', field: 'layoutId', visible: true, align: 'center', valign: 'middle'},
            {title: '运营领取人id', field: 'operateId', visible: true, align: 'center', valign: 'middle'},
            {title: '退回原因', field: 'return_message', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '原作者', field: 'createUserName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
HistoricalArticle.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        HistoricalArticle.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加文章历史版本记录
 */
HistoricalArticle.openAddHistoricalArticle = function () {
    var index = layer.open({
        type: 2,
        title: '添加文章历史版本记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/historicalArticle/historicalArticle_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看文章历史版本记录详情
 */
HistoricalArticle.openHistoricalArticleDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文章历史版本记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/historicalArticle/historicalArticle_update/' + HistoricalArticle.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除文章历史版本记录
 */
HistoricalArticle.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/historicalArticle/delete", function (data) {
            Feng.success("删除成功!");
            HistoricalArticle.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("historicalArticleId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询文章历史版本记录列表
 */
HistoricalArticle.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    HistoricalArticle.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = HistoricalArticle.initColumn();
    var table = new BSTable(HistoricalArticle.id, "/historicalArticle/list", defaultColunms);
    table.setPaginationType("client");
    HistoricalArticle.table = table.init();
});
