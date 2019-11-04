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
            {title: '旧主标题', field: 'main_title', visible: true, align: 'center', valign: 'middle'},
            {title: '新主标题', field: 'new_main_title', visible: true, align: 'center', valign: 'middle'},
            {title: '所属分类', field: 'articleTypeName', visible: true, align: 'center', valign: 'middle', sortable: true},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle', sortable: true},
            {title: '文章作者', field: 'createUserName', visible: true, align: 'center', valign: 'middle'},
            {title: '当前状态', field: 'articleStatus', visible: true, align: 'center', valign: 'middle', sortable: true},
            {title: '发布类型', field: 'releaseTypeName', visible: true, align: 'center', valign: 'middle', sortable: true},
            {title: '退回/作废原因', field: 'return_message', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
NewArticle.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        NewArticle.seItem = selected[0];
        return true;
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
 * 点击添加文章发布
 */
NewArticle.openAddNewArticle = function () {
    var index = layer.open({
        type: 2,
        title: '新增文章',
        area: ['1100px', '700px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/newArticle/newArticle_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看文章发布详情
 */
NewArticle.openNewArticleDetail = function () {

    if (this.check()) {
        if (this.checkArticleStatus()){
            var index = layer.open({
                type: 2,
                title: '编辑文章',
                area: ['1100px', '700px'], //宽高
                fix: false, //不固定
                maxmin: true,
                content: Feng.ctxPath + '/newArticle/editor_update/' + NewArticle.seItem.id
            });
            this.layerIndex = index;
        }
    }
};

/**
 * 删除文章发布
 */
NewArticle.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/newArticle/delete", function (data) {
            Feng.success("删除成功!");
            NewArticle.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("newArticleId",this.seItem.id);
        ajax.start();
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
    var table = new BSTable(NewArticle.id, "/newArticle/list", defaultColunms);
    table.setPaginationType("client");
    NewArticle.table = table.init();
});
