/**
 * 文章管理初始化
 */
var ArticleRecord = {
    id: "ArticleRecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ArticleRecord.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '文章编号', field: 'article_num', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '主标题', field: 'main_title', visible: true, align: 'center', valign: 'middle'},
        {title: '副标题', field: 'subheading', visible: true, align: 'center', valign: 'middle'},
        {title: '操作类型', field: 'operation_type', visible: true, align: 'center', valign: 'middle'},
        {title: '操作人', field: 'operator', visible: true, align: 'center', valign: 'middle'},
        {title: '操作内容', field: 'operation_content', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
ArticleRecord.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        ArticleRecord.seItem = selected[0];
        return true;
    }
};

ArticleRecord.resetSearch = function () {
    $("#articleNum").val("");
    $("#usernumber").val("");
    ArticleRecord.search();
};

/**
 * 查询文章发布列表
 */
ArticleRecord.search = function () {
    var queryData = {};
    queryData['articleNum'] = $("#articleNum").val();
    queryData['usernumber'] = $("#usernumber").val();
    ArticleRecord.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ArticleRecord.initColumn();
    var table = new BSTable(ArticleRecord.id, "/articleRecord/list", defaultColunms);
    table.setPaginationType("client");
    ArticleRecord.table = table.init();
});
