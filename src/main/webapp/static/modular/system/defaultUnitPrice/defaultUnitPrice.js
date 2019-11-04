/**
 * 文章默认售价管理初始化
 */
var DefaultUnitPrice = {
    id: "DefaultUnitPriceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DefaultUnitPrice.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            // {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户购买文章单价', field: 'purchasePrice', visible: true, align: 'center', valign: 'middle'},
            {title: '文编文章结算单价', field: 'settlementPrice', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
DefaultUnitPrice.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DefaultUnitPrice.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加文章默认售价
 */
DefaultUnitPrice.openAddDefaultUnitPrice = function () {
    var index = layer.open({
        type: 2,
        title: '添加文章默认售价',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/defaultUnitPrice/defaultUnitPrice_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看文章默认售价详情
 */
DefaultUnitPrice.openDefaultUnitPriceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文章默认售价详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/defaultUnitPrice/defaultUnitPrice_update/' + DefaultUnitPrice.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除文章默认售价
 */
DefaultUnitPrice.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/defaultUnitPrice/delete", function (data) {
            Feng.success("删除成功!");
            DefaultUnitPrice.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("defaultUnitPriceId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询文章默认售价列表
 */
DefaultUnitPrice.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    DefaultUnitPrice.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = DefaultUnitPrice.initColumn();
    var table = new BSTable(DefaultUnitPrice.id, "/defaultUnitPrice/list", defaultColunms);
    table.setPaginationType("client");
    DefaultUnitPrice.table = table.init();
});
