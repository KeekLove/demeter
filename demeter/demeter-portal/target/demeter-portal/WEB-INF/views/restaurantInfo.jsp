<%--
  Created by IntelliJ IDEA.
  User: abc
  Date: 2019/7/26
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        html{height:100%}
        body{height:100%;margin:0px;padding:0px
        ;display: flex;
            justify-items: center;
        }
        #container{
            height:600px;
            width: 600px;

        }
        #table{

        }
    </style>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=KiDywwvkKvk9ewvUiBUwYr7cvMeAlqKb">
        //v3.0版本的引用方式：src="http://api.map.baidu.com/api?v=3.0&ak=您的密钥"
    </script>

</head>


<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/html">

<head>
    <meta charset="UTF-8">
    <title>定位</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <style type="text/css">
        html{height:100%}
        body{height:100%;margin:0px;padding:0px}
        #container{
            height:600px;
            width: 600px;

        }
        #table{

        }
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=KiDywwvkKvk9ewvUiBUwYr7cvMeAlqKb">
        //v3.0版本的引用方式：src="http://api.map.baidu.com/api?v=3.0&ak=您的密钥"
    </script>
</head>
<body style="background-image: url('img/bj03.jpg'); ">
<div>
    <form action="/card/upload" enctype="multipart/form-data" method="post">
        <table>
            <tr>
                <th>
                    营业执照
                </th>
                <td>
                    <input type="text" placeholder="餐厅营业执照编号" name="registerId"/>
                </td>
            </tr>
            <tr>
                <th>
                    简介
                </th>
                <td>
                    <input type="text" placeholder="餐厅信息简介" name="about"/>
                </td>
            </tr>
            <tr>
                <th>
                    餐厅图片
                </th>
                <td>
                    <input type="file" name="icon"/>
                </td>
            </tr>
            <tr>
                <th>
                    餐厅名
                </th>
                <td>
                    <input type="text" placeholder="餐馆名字" name="name"/>
                </td>
            </tr>
            <tr>
                <th>
                    省
                </th>
                <td>
                    <input type="text" id="province" placeholder="省份" name="province"/>
                </td>
            </tr>
            <tr>
                <th>
                    市
                </th>
                <td>
                    <input type="text" id="city" placeholder="城市名" name="city"/>
                </td>
            </tr>
            <tr>
                <th>
                    地址
                </th>
                <td>
                    <input type="text" id="address" placeholder="详细地址" name="address"/>
                </td>
            </tr>
            <tr>
                <th>
                    token
                </th>
                <td>
                    <input type="text" placeholder="token" name="token"/>
                </td>
            </tr>
            <tr>
                <th>

                </th>
                <td>

                </td>
            </tr>
            <tr>
                <td align="center">
                    <input type="submit" value="提交" />
                </td>
            </tr>
        </table>

    </form>

</div>

<div id=""  >
    <form>
        <table id="table" border="1" cellspacing="0" cellpadding="1"  width="600px" >
            <tr>
                <th colspan="3"><h2>请输入信息...</h2></th>
            </tr>
            <tr>
                <th>名称:</th>
                <td>
                    <input id="cityName" type="text" size="20" placeholder="请输入城市名" style="width:200px;">
                    <input type="button" value="查询" onclick="theLocation()" />
                </td>

                <div id="r-result2" class="form-group">

                </div>
            </tr>
            <tr>
                <th>城市:</th>
                <td>
                    <div id="r-result"><input type="text" id="suggestId" size="20" placeholder="请输入精准的地点" style="width:200px;" /></div>
                    <div id="searchResultPanel" style="border:1px solid #C0C0C0; width:200px;height:auto; display:none;"></div>
                </td>
            </tr>
        </table>
    </form>
    <div id="container" align="center" ></div>
</div>

<script type="text/javascript">
    // 创建地图实例
    var map = new BMap.Map("container");
    // 创建点坐标
    var point = new BMap.Point(116.404, 39.915);
    // 初始化地图，设置中心点坐标和地图级别
    map.centerAndZoom(point, 15);
    //鼠标控制地图缩放
    map.enableScrollWheelZoom(true);

    //点击地图获取地址
    var geoc = new BMap.Geocoder();
    map.addEventListener("click", function(e){
        var pt = e.point;
        geoc.getLocation(pt, function(rs){
            var addComp = rs.addressComponents;
            $("#province").attr("value",addComp.province);
            $("#city").attr("value",addComp.city);
            console.log(addComp.district+addComp.street+addComp.streetNumber)
            $("#address").attr("value",addComp.district+addComp.street+addComp.streetNumber);

            // alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
        });
    });

    //定位（当前所在城市）
    function myFun(result){
        var cityName = result.name;
        map.setCenter(cityName);
        // alert("当前定位城市:"+cityName);
    }
    var myCity = new BMap.LocalCity();
    myCity.get(myFun);

    //查询城市（查询您想要看的城市地图）
    function theLocation(){
        var city = document.getElementById("cityName").value;
        if(city != ""){
            map.centerAndZoom(city,11);      // 用城市名设置地图中心点
        }
    }


    //下拉框选择

    var size = new BMap.Size(10, 20);
    map.addControl(new BMap.CityListControl({
        anchor: BMAP_ANCHOR_TOP_LEFT,
        offset: size,
        // 切换城市之间事件
        // onChangeBefore: function(){
        //    alert('before');
        // },
        // 切换城市之后事件
        // onChangeAfter:function(){
        //   alert('after');
        // }
    }));




    //查询精准地图

    // 百度地图API功能。。。。。。。。。。。。。。。。。。。。下拉框定位
    function G(id) {
        return document.getElementById(id);
    }

    /*var map = new BMap.Map("l-map");
    map.centerAndZoom("北京",12);*/                   // 初始化地图,设置城市和地图级别。

    var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
        {"input" : "suggestId"
            ,"location" : map
        });

    ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
        var str = "";
        var _value = e.fromitem.value;
        var value = "";
        if (e.fromitem.index > -1) {
            value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }
        str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

        value = "";
        if (e.toitem.index > -1) {
            _value = e.toitem.value;
            value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        }
        str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
        G("searchResultPanel").innerHTML = str;
    });

    var myValue;
    ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
        var _value = e.item.value;
        myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
        G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;

        setPlace();
    });

    function setPlace(){
        map.clearOverlays();    //清除地图上所有覆盖物
        function myFun(){
            var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
            map.centerAndZoom(pp, 18);
            map.addOverlay(new BMap.Marker(pp));    //添加标注
        }
        var local = new BMap.LocalSearch(map, { //智能搜索
            onSearchComplete: myFun
        });
        local.search(myValue);
    }


</script>
</body>
</html>

</html>
