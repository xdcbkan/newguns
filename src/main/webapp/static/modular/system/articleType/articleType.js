/**
 * 文章分类管理管理初始化
 */
var ArticleType = {
    id: "ArticleTypeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ArticleType.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '文章分类名称', field: 'name', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ArticleType.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ArticleType.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加文章分类管理
 */
ArticleType.openAddArticleType = function () {
    var index = layer.open({
        type: 2,
        title: '添加文章分类管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/articleType/articleType_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看文章分类管理详情
 */
ArticleType.openArticleTypeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文章分类管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/articleType/articleType_update/' + ArticleType.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除文章分类管理
 */
ArticleType.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/articleType/delete", function (data) {
            Feng.success("删除成功!");
            ArticleType.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("articleTypeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询文章分类管理列表
 */
ArticleType.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ArticleType.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ArticleType.initColumn();
    var table = new BSTable(ArticleType.id, "/articleType/list", defaultColunms);
    table.setPaginationType("client");
    ArticleType.table = table.init();
});
