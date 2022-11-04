<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <style>
        .my-table {
            border: 1px solid black;
            border-color: #69CDD2;
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
            margin-bottom: 5px;
            width: 100%;
            text-align: center;
            border: 1px solid black;
            border-color: #aaaaaa;

            /*background-color: #aaaaaa;*/
        }

        .padding-xs {
            padding: 10px;
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
    </style>
</head>

<body>
<div>
    <#--    标题-->
    <div style="text-align: center;width: 100%;">
        <div class="padding-xs text-xl" style="font-weight: bold;">标题</div>
    </div>
    <#--   概况 -->
    <div class="panel">
        <div>
            <div class="text-sm text-bold" style="padding-top: 5px">库存现金</div>
            <div class="padding-xs" style="font-size: 38px;color: red">20,221</div>
        </div>
        <#--    详情-->
        <div>
            <table border="0" cellspacing="0" cellpadding="0" width='100%'>
                <tr style="border-color: #ffffff;">
                    <th style="width: 33%;text-align: left">
                        <div class="padding-sm" style="text-align: center">
                            <div class="text-sm">收入</div>
                            <div class="padding-xs text-sm">10,000</div>
                        </div>
                    </th>
                    <th style="width: 33%;text-align: center;">
                        <div class="padding-sm" style="text-align: center">
                            <div class="text-sm">支出</div>
                            <div class="padding-xs text-sm">10,000</div>
                        </div>
                    </th>
                    <th style="width: 33%;text-align: right;">
                        <div class="padding-sm" style="text-align: center">
                            <div class="text-sm">余额</div>
                            <div class="padding-xs text-sm">10,000</div>
                        </div>
                    </th>
                </tr>
            </table>
        </div>
    </div>


    <div style="">
        <table cellspacing="0" cellpadding="0" width='100%' class="my-table">
            <tr class="tableTitle">
                <th>今日报货</th>
                <th>今日报货</th>
                <th>今日报货</th>
                <th>今日报货</th>
            </tr>
            <tr class="total">
                <td>总计</td>
                <td>0</td>
                <td>0</td>
                <td>0</td>
            </tr>
        </table>
    </div>
</div>

</body>
</html>