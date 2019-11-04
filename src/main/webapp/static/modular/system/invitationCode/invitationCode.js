/**
 * 注册邀请码管理管理初始化
 */
var InvitationCode = {
    id: "InvitationCodeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
InvitationCode.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            // {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '邀请码', field: 'invitation_code', visible: true, align: 'center', valign: 'middle'},
            {title: '剩余注册次数', field: 'amount', visible: true, align: 'center', valign: 'middle', sortable: true},
            {title: '创建人', field: 'createUserName', visible: true, align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
InvitationCode.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        InvitationCode.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加注册邀请码管理
 */
InvitationCode.openAddInvitationCode = function () {
    var index = layer.open({
        type: 2,
        title: '添加注册邀请码管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/invitationCode/invitationCode_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看注册邀请码管理详情
 */
InvitationCode.openInvitationCodeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '注册邀请码管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/invitationCode/invitationCode_update/' + InvitationCode.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除注册邀请码管理
 */
InvitationCode.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/invitationCode/delete", function (data) {
            Feng.success("删除成功!");
            InvitationCode.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("invitationCodeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询注册邀请码管理列表
 */
InvitationCode.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    InvitationCode.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = InvitationCode.initColumn();
    var table = new BSTable(InvitationCode.id, "/invitationCode/list", defaultColunms);
    table.setPaginationType("client");
    InvitationCode.table = table.init();
});
