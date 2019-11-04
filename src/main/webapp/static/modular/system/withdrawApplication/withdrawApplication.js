/**
 * 提现申请管理初始化
 */
var WithdrawApplication = {
    id: "WithdrawApplicationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
WithdrawApplication.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        // {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '用户姓名', field: 'realName', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '支付宝账号', field: 'ali_pay', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '提现金额', field: 'amount', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '提现状态', field: 'withdrawStatusName', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '提现反馈', field: 'error_message', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '申请时间', field: 'create_time', visible: true, align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
WithdrawApplication.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        WithdrawApplication.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加提现申请
 */
WithdrawApplication.openAddWithdrawApplication = function () {
    var index = layer.open({
        type: 2,
        title: '添加提现申请',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/withdrawApplication/withdrawApplication_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看提现申请详情
 */
WithdrawApplication.openWithdrawApplicationDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '提现申请详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/withdrawApplication/withdrawApplication_update/' + WithdrawApplication.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开失败反馈页面
 */
WithdrawApplication.paymentFailed = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (this.check()) {
        if (selected[0].withdraw_status === 1) {
            var index = layer.open({
                type: 2,
                title: '失败反馈',
                area: ['800px', '420px'], //宽高
                fix: false, //不固定
                maxmin: true,
                content: Feng.ctxPath + '/withdrawApplication/withdrawApplication_paymentFailed/' + WithdrawApplication.seItem.id
            });
            this.layerIndex = index;
        } else {
            Feng.error("请正确操作!");
        }
    }
};

/**
 * 成功支付提现申请
 */
WithdrawApplication.paymentSuccessful = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (this.check()) {
        if (selected[0].withdraw_status === 1) {
            var ajax = new $ax(Feng.ctxPath + "/withdrawApplication/paymentSuccessful", function (data) {
                if (data.code === 20000) {
                    Feng.error(data.message + "!");
                } else {
                    Feng.success("更新成功!");
                }
                WithdrawApplication.table.refresh();
            }, function (data) {
                Feng.error("更新失败!" + data.responseJSON.message + "!");
            });
            ajax.set("withdrawApplicationId", this.seItem.id);
            ajax.start();
        } else {
            Feng.error("请正确操作!");
        }
    }
};


/**
 * 删除提现申请
 */
WithdrawApplication.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/withdrawApplication/delete", function (data) {
            Feng.success("删除成功!");
            WithdrawApplication.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("withdrawApplicationId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询提现申请列表
 */
WithdrawApplication.search = function () {
    var queryData = {};
    queryData['name'] = $("#name").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    queryData['withdrawStatus'] = $("#withdrawStatus").val();
    WithdrawApplication.table.refresh({query: queryData});
};


WithdrawApplication.resetSearch = function () {
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");
    $("#withdrawStatus").val("");
    WithdrawApplication.search();
}

$(function () {
    var defaultColunms = WithdrawApplication.initColumn();
    var table = new BSTable(WithdrawApplication.id, "/withdrawApplication/list", defaultColunms);
    table.setPaginationType("client");
    WithdrawApplication.table = table.init();
});
