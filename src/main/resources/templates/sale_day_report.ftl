<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <style>
        table {
            border: 1px solid black;
            border-color: #69CDD2;
        }

        th {
            height: 50px;
            text-align: center;
            color: #ffffff;
            font-weight: 800;
            font-size: 24px;
            background-color: #3968F2;
            border: 1px solid black;
            border-color: #3262E8;
        }

        td {
            border: 1px solid black;
            border-color: #69CDD2;
            text-align: center;
        }

        .total {
            height: 50px;
            text-align: center;
            border: 1px solid black;
            border-color: #9AD9E8;
        }
    </style>
</head>

<body>

<div>
    <table cellspacing="0" cellpadding="0" width='100%'>
        <caption>
            <div>
                <img style="text-align: start;display:inline-block;"
                     src="http://www.yangzhanggui.net/upload/gallery/thumbnail/A3A2354A-C672-8587-BFAFF5A94188-tbl.png"
                     width="100" height="30"/>
                <a style="text-align: center;display:inline-block;">销售日报表</a>
            </div>
        </caption>
        <tr>
            <th>品名</th>
            <th>规格</th>
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
                <td>${item.cinvstd!""}</td>
                <td>${item.drfh!""}</td>
                <td>${item.ylfh!""}</td>
                <td>${item.drbh!""}</td>
                <td>${item.wf!""}</td>
                <td>${item.kc!""}</td>
                <td>${item.fhRatio!""}</td>
            </tr>
        </#list>
        <tr class="total">
            <td>合计</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td colspan="8" style="text-align: right;padding-right: 10px;">制表人:广岛小男孩</td>
        </tr>
    </table>
</div>
</body>
</html>