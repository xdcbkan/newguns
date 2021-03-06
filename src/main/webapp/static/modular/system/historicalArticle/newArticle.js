/**
 * 文章发布管理初始化
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
        {field: 'selectItem', radio: true},
            {title: '文章编号', field: 'article_num', visible: true, align: 'center', valign: 'middle', sortable: true},
            {title: '文章情况', field: 'prefix', visible: true, align: 'center', valign: 'middle', sortable: true},
            {title: '主标题', field: 'new_main_title', visible: true, align: 'center', valign: 'middle'},
            {title: '副标题', field: 'new_subheading', visible: true, align: 'center', valign: 'middle'},
            {title: '所属分类', field: 'articleTypeName', visible: true, align: 'center', valign: 'middle', sortable: true},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle', sortable: true},
            {title: '文章作者', field: 'createUserName', visible: true, align: 'center', valign: 'middle'},
            {title: '当前状态', field: 'articleStatus', visible: true, align: 'center', valign: 'middle', sortable: true},
            {title: '退回/作废原因', field: 'return_message', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
NewArticle.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一篇文章！");
        return false;
    }else{
        NewArticle.seItem = selected[0];
        return true;
    }
};

/**
 * 打开查看文章历史版本记录详情
 */
NewArticle.openHistoricalArticle = function () {
    if (this.check()) {
        // 更换服务器时，此处地址需修改未对应的访问地址
        var ajax = new $ax("http://127.0.0.1:8085/historicalArticle/list", function (data) {
            window.location.href="http://127.0.0.1:8085/historicalArticle/history";
        }, function (data) {
            Feng.error("出现错误,访问失败!" + data.responseJSON.message + "!");
        });
        ajax.set("articleId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 检查当前状态
 */
NewArticle.checkArticleStatus = function () {
    var articleStatus = $('#' + this.id).bootstrapTable('getSelections')
    if (articleStatus[0].article_status == 1 || articleStatus[0].article_status == 6 || articleStatus[0].article_status == 7 ){
        return true;
    }else{
        Feng.info("该文章当前状态无法编辑修改!");
        return false;
    }
};

/**
 * 打开查看文章发布详情
 */
NewArticle.openNewArticleDetail = function () {

    if (this.check()) {
            var index = layer.open({
                type: 2,
                title: '文章详情',
                area: ['1100px', '700px'], //宽高
                fix: false, //不固定
                maxmin: true,
                content: Feng.ctxPath + '/newArticle/update/' + NewArticle.seItem.id
            });
            this.layerIndex = index;
        }
};


NewArticle.resetSearch = function () {
    $("#condition").val("");
    $("#articleStatus").val(20);
    $("#articleTypeId").val(0);
    $("#prescription").val(0);

    TransWater.search();
}

/**
 * 查询文章发布列表
 */
NewArticle.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['articleTypeId'] = $("#articleTypeId").val();
    queryData['articleStatus'] = $("#articleStatus").val();
    queryData['prescription'] = $("#prescription").val();
    NewArticle.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = NewArticle.initColumn();
    var table = new BSTable(NewArticle.id, "/historicalArticle/newList", defaultColunms);
    table.setPaginationType("client");
    NewArticle.table = table.init();
});
