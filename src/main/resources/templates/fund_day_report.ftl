<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <style>
        .my-table {
            border: 1px solid black;
            border-color: #69CDD2;
        }
        tr:nth-child(even) {
            background: #f1f1f1;
        }
        td {
            height: 40px;
            border: 1px solid black;
            border-color: #69CDD2;
            text-align: center;
            font-size: 22px;
        }

        .tableTitle {
            height: 50px;
            text-align: center;
            color: #ffffff;
            font-weight: 800;
            font-size: 24px;
            background-color: #3968F2;
            border: 1px solid black;
            border-color: #3262E8;
            border-radius: 24px;
        }

        .panel {
            margin-bottom: 0px;
            width: 100%;
            text-align: center;
            border: 1px solid black;
            border-color: #69CDD2;
            /*background-color: #aaaaaa;*/
        }

        .padding-xs {
            padding: 5px;
        }

        .padding-sm {
            padding: 20px;
        }

        .padding {
            padding: 30px;
        }

        .text-x {
            font-size: 16px;
        }

        .text-xs {
            font-size: 20px;
        }

        .text-sm {
            font-size: 24px;
        }

        .text-df {
            font-size: 28px;
        }

        .text-lg {
            font-size: 32px;
        }

        .text-xl {
            font-size: 36px;
        }

        .text-bold {
            font-weight: bold;
        }

        .total {
            height: 50px;
            text-align: center;
            border: 1px solid black;
            border-color: #9AD9E8;
            background-color: #9AD9E8;
        }

        .title-2{
            border-color: #ffffff;
        }
    </style>
</head>

<body>
<div>
    <#--    标题-->
    <#--<div style="text-align: center;width: 100%;">
        <div class="padding-xs text-xl" style="font-weight: bold;">杨掌柜食品科技（河南）有限公司</div>
    </div>-->

    <div style="padding-bottom: 30px">
        <table border="0" cellspacing="0" cellpadding="0" width='100%'>
            <tr class="title-2">
                <th style="width: 20%;text-align: left">
                    <img style="text-align: start;display:inline-block;"
                         src="http://www.yangzhanggui.net/upload/gallery/thumbnail/A3A2354A-C672-8587-BFAFF5A94188-tbl.png"
                         width="200" height="80"/></th>
                <th style="text-align: center;font-size:32px;color: black">
                    <b style="text-align: center;width: 100%;font-size: 38px;font-weight: bold;">${itemTitle}</b>
                    <br/>
                    <b style="text-align: center;width: 100%;font-size: 32px;font-weight: bold;">${itemSubhead}</b>
                </th>
                <th style="width: 20%;font-size: 28px;text-align: right;"><div style="padding-top: 50px">${dataDate}</div></th>
            </tr>
        </table>
    </div>

    <#--   概况 -->
    <div class="panel">
        <div>
            <div class="text-sm text-bold" style="padding-top: 5px">${cash}</div>
            <div class="padding-xs" style="font-size: 38px;color: red">${kcCash.amount!""}</div>
        </div>
        <#--    详情-->
        <div>
            <table border="0" cellspacing="0" cellpadding="0" width='100%'>
                <tr style="border-color: #ffffff;">
                    <th style="width: 33%;text-align: left">
                        <div class="padding-sm" style="text-align: center">
                            <div class="text-sm">${income}</div>
                            <div class="padding-xs text-sm">${amountInstance.srAmount!""}</div>
                        </div>
                    </th>
                    <th style="width: 33%;text-align: center;">
                        <div class="padding-sm" style="text-align: center">
                            <div class="text-sm">${disburse}</div>
                            <div class="padding-xs text-sm">${amountInstance.zfAmount!""}</div>
                        </div>
                    </th>
                    <th style="width: 33%;text-align: right;">
                        <div class="padding-sm" style="text-align: center">
                            <div class="text-sm">${residue}</div>
                            <div class="padding-xs text-sm">${amountInstance.yeAmount!""}</div>
                        </div>
                    </th>
                </tr>
            </table>
        </div>
    </div>
    <div style="">
        <table cellspacing="0" cellpadding="0" width='100%' class="my-table">
            <tr class="tableTitle">
                <th>${thName}</th>
                <th>${thIncome}</th>
                <th>${thDisburse}</th>
                <th>${thResidue}</th>
            </tr>
            <#list amountList as item>
                <tr>
                    <td>${item.name!""}</td>
                    <td>${item.rkAmount!""}</td>
                    <td>${item.zfAmount!""}</td>
                    <td>${item.yeAmount!""}</td>
                </tr>
            </#list>
            <tr class="total" style="background-color: #9AD9E8">
                <td>${sumItem.name!""}</td>
                <td>${sumItem.rkAmount!""}</td>
                <td>${sumItem.zfAmount!""}</td>
                <td>${sumItem.yeAmount!""}</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>