/**
 * 文章发布地址管理初始化
 */
var ArticlePublishAddress = {
    id: "ArticlePublishAddressTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ArticlePublishAddress.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id(运营人员id)', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '文章id', field: 'articleId', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址1', field: 'address1', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址2', field: 'address2', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址3', field: 'address3', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址4', field: 'address4', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址5', field: 'address5', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址6', field: 'address6', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址7', field: 'address7', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址8', field: 'address8', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址9', field: 'address9', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址10', field: 'address10', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址11', field: 'address11', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址12', field: 'address12', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址13', field: 'address13', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址14', field: 'address14', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址15', field: 'address15', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址16', field: 'address16', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址17', field: 'address17', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址18', field: 'address18', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址19', field: 'address19', visible: true, align: 'center', valign: 'middle'},
            {title: '发布地址20', field: 'address20', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ArticlePublishAddress.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ArticlePublishAddress.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加文章发布地址
 */
ArticlePublishAddress.openAddArticlePublishAddress = function () {
    var index = layer.open({
        type: 2,
        title: '添加文章发布地址',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/articlePublishAddress/articlePublishAddress_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看文章发布地址详情
 */
ArticlePublishAddress.openArticlePublishAddressDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文章发布地址详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/articlePublishAddress/articlePublishAddress_update/' + ArticlePublishAddress.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除文章发布地址
 */
ArticlePublishAddress.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/articlePublishAddress/delete", function (data) {
            Feng.success("删除成功!");
            ArticlePublishAddress.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("articlePublishAddressId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询文章发布地址列表
 */
ArticlePublishAddress.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ArticlePublishAddress.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ArticlePublishAddress.initColumn();
    var table = new BSTable(ArticlePublishAddress.id, "/articlePublishAddress/list", defaultColunms);
    table.setPaginationType("client");
    ArticlePublishAddress.table = table.init();
});
