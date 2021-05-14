
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        #map {
            margin: auto;
            width: 1300px;
            background-color: #D3D3D3;
        }
    </style>
    <script type="text/javascript">


    </script>
</head>
<body>

<span id="spansID"></span>

<a>
    <div id="map">
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="main" style="height:700px"></div>
        <!-- ECharts单文件引入 -->
        <script src="js/echarts-all.js"></script>

        <script src="js/jquery.1.8.3.min.js" type="text/javascript" charset="UTF-8"></script>
        <script type="text/javascript">
            // 基于准备好的dom，初始化echarts图表
            var myChart = echarts.init(document.getElementById('main'));

            function mun(name) {
                return 0;
            }

            myChart.on('click', function (params) {
                var city = params.name;

                if (city == "南海诸岛") {
                    alert("正在统计中...")
                } else {
                    /*alert(city);*/
                    location.assign("login?mark=animal&id=" + city);//请求servlet  查询数据   servlet转发到top.jsp
                }

            });

            <% HashMap<String,Integer> hashMap = Count.CountNum1();
             HashMap<String,Integer> hashMap1 = Count.CountNum2();
            %>
            var option = {
                // 标签
                title: {
                    text: '各省保护动物物种数量',
                    x: 'center',
                    y: '30'
                },
                //副标签
                tooltip: {
                    trigger: 'item'
                },

                dataRange: {
                    min: 0,
                    max: 200,
                    x: 'left',
                    y: 'bottom',
                    text: ['高', '低'], // 文本，默认为数值文本
                    calculable: true

                },
                roamController: {
                    show: true,
                    x: 'right',
                    mapTypeControl: {
                        'china': true
                    }
                },
                series: [{
                    name: '一级保护动物',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    itemStyle: {
                        normal: {
                            label: {
                                show: true
                            }
                        }
                    },
                    label: {
                        normal: {
                            textStyle: {
                                fontSize: 10,
                                fontWeight: 'bold',
                                color: 'red'
                            }
                        }
                    },
                    data: [{
                        name: '北京',
                        value: <%out.print(hashMap.get("北京")); %>
                    },
                        {
                            name: '天津',
                            value: <%out.print(hashMap.get("天津")); %>
                        },
                        {
                            name: '上海',
                            value: <% out.print(hashMap.get("上海")); %>
                        },
                        {
                            name: '重庆',
                            value: <% out.print(hashMap.get("重庆")); %>
                        },
                        {
                            name: '河北',
                            value:<% out.print(hashMap.get("河北")); %>
                        },
                        {
                            name: '河南',
                            value: <% out.print(hashMap.get("河南")); %>
                        },
                        {
                            name: '云南',
                            value: <% out.print(hashMap.get("云南")); %>
                        },
                        {
                            name: '辽宁',
                            value: <% out.print(hashMap.get("辽宁")); %>
                        },
                        {
                            name: '黑龙江',
                            value: <% out.print(hashMap.get("黑龙江")); %>
                        },
                        {
                            name: '湖南',
                            value: <% out.print(hashMap.get("湖南")); %>
                        },
                        {
                            name: '安徽',
                            value: <% out.print(hashMap.get("安徽")); %>
                        },
                        {
                            name: '山东',
                            value: <% out.print(hashMap.get("山东")); %>
                        },
                        {
                            name: '新疆',
                            value: <% out.print(hashMap.get("新疆")); %>
                        },
                        {
                            name: '江苏',
                            value: <% out.print(hashMap.get("江苏")); %>
                        },
                        {
                            name: '浙江',
                            value:<% out.print(hashMap.get("浙江")); %>
                        },
                        {
                            name: '江西',
                            value: <% out.print(hashMap.get("江西")); %>
                        },
                        {
                            name: '湖北',
                            value: <% out.print(hashMap.get("湖北")); %>
                        },
                        {
                            name: '广西',
                            value: <% out.print(hashMap.get("广西")); %>
                        },
                        {
                            name: '甘肃',
                            value:<% out.print(hashMap.get("甘肃")); %>
                        },
                        {
                            name: '内蒙古',
                            value: <% out.print(hashMap.get("内蒙古")); %>
                        },
                        {
                            name: '陕西',
                            value: <% out.print(hashMap.get("陕西")); %>
                        },
                        {
                            name: '吉林',
                            value:<% out.print(hashMap.get("吉林")); %>
                        },
                        {
                            name: '福建',
                            value: <% out.print(hashMap.get("福建")); %>
                        },
                        {
                            name: '贵州',
                            value:<% out.print(hashMap.get("贵州")); %>
                        },
                        {
                            name: '广东',
                            value:<% out.print(hashMap.get("广东")); %>
                        },
                        {
                            name: '青海',
                            value: <% out.print(hashMap.get("青海")); %>
                        },
                        {
                            name: '西藏',
                            value: <% out.print(hashMap.get("西藏")); %>
                        },
                        {
                            name: '四川',
                            value: <% out.print(hashMap.get("四川")); %>
                        },
                        {
                            name: '宁夏',
                            value: <% out.print(hashMap.get("宁夏")); %>
                        },
                        {
                            name: '海南',
                            value: <% out.print(hashMap.get("海南")); %>
                        },
                        {
                            name: '台湾',
                            value: <% out.print(hashMap.get("台湾")); %>
                        },
                        {
                            name: '香港',
                            value: <% out.print(hashMap.get("香港")); %>
                        },
                        {
                            name: '山西',
                            value: <% out.print(hashMap.get("山西")); %>
                        },
                        {
                            name: '澳门',
                            value: <% out.print(hashMap.get("澳门")); %>
                        }
                    ]
                },
                    {
                        name: '二级保护动物',
                        type: 'map',
                        mapType: 'china',
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true
                                }
                            },
                            emphasis: {
                                label: {
                                    show: true
                                }
                            }
                        },
                        data: [{
                            name: '北京',
                            value: <%out.print(hashMap1.get("北京")); %>
                        },
                            {
                                name: '天津',
                                value: <%out.print(hashMap1.get("天津")); %>
                            },
                            {
                                name: '上海',
                                value: <% out.print(hashMap1.get("上海")); %>
                            },
                            {
                                name: '重庆',
                                value: <% out.print(hashMap1.get("重庆")); %>
                            },
                            {
                                name: '河北',
                                value:<% out.print(hashMap1.get("河北")); %>
                            },
                            {
                                name: '河南',
                                value: <% out.print(hashMap1.get("河南")); %>
                            },
                            {
                                name: '云南',
                                value: <% out.print(hashMap1.get("云南")); %>
                            },
                            {
                                name: '辽宁',
                                value: <% out.print(hashMap1.get("辽宁")); %>
                            },
                            {
                                name: '黑龙江',
                                value: <% out.print(hashMap1.get("黑龙江")); %>
                            },
                            {
                                name: '湖南',
                                value: <% out.print(hashMap1.get("湖南")); %>
                            },
                            {
                                name: '安徽',
                                value: <% out.print(hashMap1.get("安徽")); %>
                            },
                            {
                                name: '山东',
                                value: <% out.print(hashMap1.get("山东")); %>
                            },
                            {
                                name: '新疆',
                                value: <% out.print(hashMap1.get("新疆")); %>
                            },
                            {
                                name: '江苏',
                                value: <% out.print(hashMap1.get("江苏")); %>
                            },
                            {
                                name: '浙江',
                                value:<% out.print(hashMap1.get("浙江")); %>
                            },
                            {
                                name: '江西',
                                value: <% out.print(hashMap1.get("江西")); %>
                            },
                            {
                                name: '湖北',
                                value: <% out.print(hashMap1.get("湖北")); %>
                            },
                            {
                                name: '广西',
                                value: <% out.print(hashMap1.get("广西")); %>
                            },
                            {
                                name: '甘肃',
                                value:<% out.print(hashMap1.get("甘肃")); %>
                            },
                            {
                                name: '内蒙古',
                                value: <% out.print(hashMap1.get("内蒙古")); %>
                            },
                            {
                                name: '陕西',
                                value: <% out.print(hashMap1.get("陕西")); %>
                            },
                            {
                                name: '吉林',
                                value:<% out.print(hashMap1.get("吉林")); %>
                            },
                            {
                                name: '福建',
                                value: <% out.print(hashMap1.get("福建")); %>
                            },
                            {
                                name: '贵州',
                                value:<% out.print(hashMap1.get("贵州")); %>
                            },
                            {
                                name: '广东',
                                value:<% out.print(hashMap1.get("广东")); %>
                            },
                            {
                                name: '青海',
                                value: <% out.print(hashMap1.get("青海")); %>
                            },
                            {
                                name: '西藏',
                                value: <% out.print(hashMap1.get("西藏")); %>
                            },
                            {
                                name: '四川',
                                value: <% out.print(hashMap1.get("四川")); %>
                            },
                            {
                                name: '宁夏',
                                value: <% out.print(hashMap1.get("宁夏")); %>
                            },
                            {
                                name: '海南',
                                value: <% out.print(hashMap1.get("海南")); %>
                            },
                            {
                                name: '台湾',
                                value: <% out.print(hashMap1.get("台湾")); %>
                            },
                            {
                                name: '香港',
                                value: <% out.print(hashMap1.get("香港")); %>
                            },
                            {
                                name: '山西',
                                value: <% out.print(hashMap1.get("山西")); %>
                            },
                            {
                                name: '澳门',
                                value: <% out.print(hashMap1.get("澳门")); %>
                            }
                        ]
                    }]
            };
            // 为echarts对象加载数据
            myChart.setOption(option);
        </script>


    </div>
</a>

</body>
</html>
