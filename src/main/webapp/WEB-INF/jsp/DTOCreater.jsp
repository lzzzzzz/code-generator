<%--
  Created by IntelliJ IDEA.
  User: LZ
  Date: 2017/6/20
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>DTOCreater</title>
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<!-- 遮罩层DIV -->
<div style="width: 100%;position: relative; background: #dfdfdf; background-color: #dfdfdf;">
<%--<div  id="overlay" style="text-align: center;margin-top: 20px; background-color: #000; opacity:0.3;z-index: 2" >
    <textarea style="border:solid; border-color: #ffffff;"> sdfsdfsdfsdfsdf</textarea>
</div>--%>
    <div style="text-align: center;margin-top: 20px; width:60%; float: left">

        <div style="text-align: center;margin-top: 20px;">
            <font size="6" style="text-align: center">DTO Creater</font>
            </div>
            <div style="text-align: center;margin-top: 20px">
            <form id="createrForm" method="post">
                <div style="display:inline">
                <input type="button" value="DTO" onclick="createAction(1)"/>
                <input type="button" value="Service" onclick="createAction(2)"/>
                <input type="button" value="ServiceImpl" onclick="createAction(3)"/>
                <input type="button" value="Controller" onclick="createAction(4)"/>
                </div>
                <div align="center" style="margin-top: 30px"><font size="4" >name：</font></div>
                <table align="center" style="margin-top: 30px">
                    <tr>
                        <th><u>\${className}</u>：</th>
                        <th><input type="text" id="className"/></th>
                    </tr>
                    <tr>
                        <th><u>\${className_zn}</u>：</th>
                        <th><input type="text" id="className_zn"/></th>
                    </tr>
                    <tr>
                        <th><u>\${controller_desc}</u>：</th>
                        <th><input type="text" id="controller_desc"/></th>
                    </tr>
                </table>

                <div align="center" style="margin-top: 30px"><font size="4" >property：</font></div>

                <table align="center" style="margin-top: 30px" id="attrs">

                    <%--<tr>
                        <th>name：<input id="attr2" type="text" name="attrs[0].name" style="margin-top: 15px"></th>
                        <th>type：<input id="attr1" type="text" name="attrs[0].value"/></th>
                        <th>desc：<input id="attr3" type="text" name="attrs[0].desc"/></th>
                        <th><img src="http://pic.qiantucdn.com/58pic/15/31/24/74658PICq32_1024.jpg">
                        <th><input type="button" style="border: none; background-image: url('http://pic.qiantucdn.com/58pic/15/31/24/74658PICq32_1024.jpg'); width:32px;height:32px; background-size: 100%;" onclick="replayTh()"/></th>
                    </tr>--%>
                </table>
            </form>
                <button  id="addButton" data-number="0" onclick="addProperties()" style="border: none; background-image: url('http://pic2.16pic.com/00/21/82/16pic_2182030_b.jpg'); width:35px;height:35px; background-size: 100%; margin-top: 15px"/>
            </div>
    </div>

    <div  style="background-color: #ffffff; width:40%;height: 100%;  float: right" >
        <textarea id="overlay" style="margin-top: 50px; border:solid; background-color:#ffffff; border-color: #ffffff; width: 100%; height: 90%" disabled="disabled"> Source Code</textarea>
    </div>
</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/DtoCreater.js"></script>
</body>
</html>
