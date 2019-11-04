/**
 * 用户信息历史版本管理初始化
 */
var HistoryUserinfo = {
    id: "HistoryUserinfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
HistoryUserinfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '用户编号', field: 'usernumber', visible: true, align: 'center', valign: 'middle'},
            {title: '账号', field: 'account', visible: true, align: 'center', valign: 'middle'},
            {title: '名字', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '生日', field: 'birthday', visible: true, align: 'center', valign: 'middle'},
            {title: '性别', field: 'sex', visible: true, align: 'center', valign: 'middle'},
            {title: '邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'},
            {title: '电话', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '身份证号', field: 'idcard', visible: true, align: 'center', valign: 'middle'},
            {title: '支付宝', field: 'alipay', visible: true, align: 'center', valign: 'middle'},
            {title: '领取上限', field: 'receivenum', visible: true, align: 'center', valign: 'middle'},
            {title: '文章单价', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
HistoryUserinfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        HistoryUserinfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户信息历史版本
 */
HistoryUserinfo.openAddHistoryUserinfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户信息历史版本',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/historyUserinfo/historyUserinfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户信息历史版本详情
 */
HistoryUserinfo.openHistoryUserinfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户信息历史版本详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/historyUserinfo/historyUserinfo_update/' + HistoryUserinfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户信息历史版本
 */
HistoryUserinfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/historyUserinfo/delete", function (data) {
            Feng.success("删除成功!");
            HistoryUserinfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("historyUserinfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户信息历史版本列表
 */
HistoryUserinfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    HistoryUserinfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = HistoryUserinfo.initColumn();
    var table = new BSTable(HistoryUserinfo.id, "/historyUserinfo/list", defaultColunms);
    table.setPaginationType("client");
    HistoryUserinfo.table = table.init();
});
