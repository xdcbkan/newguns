/**
 * 交易流水管理管理初始化
 */
var TransWater = {
    id: "TransWaterTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TransWater.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '交易时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '交易数量', field: 'amount', visible: true, align: 'center', valign: 'middle'},
            {title: '交易状态', field: 'transStatus', visible: true, align: 'center', valign: 'middle'},
            {title: '操作人', field: 'createName', visible: true, align: 'center', valign: 'middle'},
            {title: '交易类型', field: 'transTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '积分余额', field: 'points_balance', visible: true, align: 'center', valign: 'middle'},
            {title: '主体用户', field: 'userName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
TransWater.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TransWater.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加交易流水管理
 */
TransWater.openAddTransWater = function () {
    var index = layer.open({
        type: 2,
        title: '添加交易流水管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/transWater/transWater_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看交易流水管理详情
 */
TransWater.openTransWaterDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '交易流水管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/transWater/transWater_update/' + TransWater.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除交易流水管理
 */
TransWater.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/transWater/delete", function (data) {
            Feng.success("删除成功!");
            TransWater.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("transWaterId",this.seItem.id);
        ajax.start();
    }
};


TransWater.resetSearch = function () {
    $("#beginTime").val("");
    $("#endTime").val("");

    TransWater.search();
}

/**
 * 查询交易流水管理列表
 */
TransWater.search = function () {
    var queryData = {};
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    TransWater.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = TransWater.initColumn();
    var table = new BSTable(TransWater.id, "/transWater/list", defaultColunms);
    table.setPaginationType("client");
    TransWater.table = table.init();
});
