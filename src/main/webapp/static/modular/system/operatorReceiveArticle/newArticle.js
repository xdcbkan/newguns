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
        {field: 'selectItem', radio: true},
        {title: '文章编号', field: 'article_num', visible: true, align: 'center', valign: 'middle'},
        {title: '主标题', field: 'new_main_title', visible: true, align: 'center', valign: 'middle'},
        {title: '副标题', field: 'new_subheading', visible: true, align: 'center', valign: 'middle'},
        {title: '所属分类', field: 'articleTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '领取时间', field: 'update_time', visible: true, align: 'center', valign: 'middle'},
        {title: '文章作者', field: 'createUserName', visible: true, align: 'center', valign: 'middle'},
        {title: '时效性', field: 'prescription', visible: true, align: 'center', valign: 'middle'},
        {title: '项目分类', field: 'projectTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '当前状态', field: 'articleStatus', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
NewArticle.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中列表中需要审核的一篇文章！");
        return false;
    }else{
        NewArticle.seItem = selected[0];
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
            content: Feng.ctxPath + '/newArticle/operator_update/' + NewArticle.seItem.id
        });
        this.layerIndex = index;
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

// =======================================

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
 * 点击添加文章发布地址
 */
NewArticle.openAddArticlePublishAddress = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '添加文章发布地址',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/newArticle/articlePublishAddress_add/'+ NewArticle.seItem.id
        });
        this.layerIndex = index;
    }

};

/**
 * 打开查看文章发布地址详情
 */
NewArticle.openArticlePublishAddressDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文章发布地址详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/newArticle/articlePublishAddress_update/' + NewArticle.seItem.id
        });
        this.layerIndex = index;
    }
};

