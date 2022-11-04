<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <style>
        .my-table {
            border: 1px solid black;
            border-color: #69CDD2;
        }
        .title {
            height: 50px;
            text-align: center;
            color: #ffffff;
            font-weight: 800;
            font-size: 24px;
            background-color: #3968F2;
            border: 1px solid black;
            border-color: #3262E8;
        }
        .title-2{
            border-color: #ffffff;
        }
        td {
            height: 40px;
            border: 1px solid black;
            border-color: #69CDD2;
            text-align: center;
            font-size: 22px;
        }
        .total {
            height: 40px;
            text-align: center;
            border: 1px solid black;
            border-color: #9AD9E8;
            background-color: #9AD9E8;
        }


    </style>
</head>

<body>

<div>
    <div>
        <table border="0" cellspacing="0" cellpadding="0" width='100%'>
            <tr class="title-2">
                <th style="width: 20%;text-align: left">
                    <img style="text-align: start;display:inline-block;"
                         src="http://www.yangzhanggui.net/upload/gallery/thumbnail/A3A2354A-C672-8587-BFAFF5A94188-tbl.png"
                         width="220" height="100"/></th>
                <th style="text-align: center;font-size:32px;color: black">
                    <b style="text-align: center;width: 100%;font-size: 38px;font-weight: bold;">杨掌柜食品科技（河南）有限公司</b>
                    <br/>
                    <b style="text-align: center;width: 100%;font-size: 32px;font-weight: bold;">发货日报表</b>
                </th>
                <th style="width: 20%;font-size: 28px;text-align: right;"><div style="padding-top: 50px">${dataDate}</div></th>
            </tr>
        </table>
    </div>
    <table cellspacing="0" cellpadding="0" width='100%' class="my-table">
        <tr class="title">
            <th>品名</th>
            <#--<th>规格</th>-->
            <th>当日发货</th>
            <th>已发数量</th>
            <th>发货占比</th>
            <th>今日报货</th>
            <th>未发数量</th>
            <th>仓库库存</th>
        </tr>
        <#list entityList as item>
            <tr>
                <td>${item.cinvcname!""}</td>
                <#--<td>${item.cinvstd!""}</td>-->
                <td>${item.drfh!""}</td>
                <td>${item.ylfh!""}</td>
                <td>${item.fhRatio!""}</td>
                <td>${item.drbh!""}</td>
                <td>${item.wf!""}</td>
                <td>${item.kc!""}</td>
            </tr>
        </#list>
        <tr class="total">
            <td>${sumItem.cinvcname!""}</td>
            <#--<td>${sumItem.cinvstd!""}</td>-->
            <td>${sumItem.drfh!""}</td>
            <td>${sumItem.ylfh!""}</td>
            <td>${sumItem.fhRatio!""}</td>
            <td>${sumItem.drbh!""}</td>
            <td>${sumItem.wf!""}</td>
            <td>${sumItem.kc!""}</td>
        </tr>
<#--        <tr>-->
<#--            <td colspan="7" style="text-align: right;padding-right: 10px;">制表人：管理中心-信息部</td>-->
<#--        </tr>-->
    </table>
</div>
</body>
</html>