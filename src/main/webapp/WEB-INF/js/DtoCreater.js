/**
 * Created by LZ on 2017/6/20.
 */

//生成表单
function createAction(flag){
    //var demo = document.getElementById('createrForm');
    var createType = document.getElementById('createType');
    getList();
    var action="/dto/dtoCreaterDo";//生成文件内容
    //var action="/dto/dtoCreaterDoFile";//生成文件内容并生成文件
    var params=new Object();
    params.className=document.getElementById("className").value;
    params.className_zn=document.getElementById("className_zn").value;
    params.controller_desc=document.getElementById("controller_desc").value;
    params.attrs=JSON.stringify(getList());
    //params.attrs=getList();
    if(flag==1){
        params.t="dto"
    }else if(flag==2){
        params.t="service"
    }else if(flag==3){
        params.t="serviceImpl"
    }else if(flag==4){
        params.t="controller"
    }else{
        action="";
    }
    if(action!=""){
        por(params,action);
    }
}

//添加属性
function addProperties(){
    var ab = document.getElementById("addButton").getAttribute("data-number");

    var new_ht_str="<th>name：<input id='attr' type='text' name='attrs["+ab+"].name'/></th>"+
    "<th>type：<input id='attr' type='text' name='attrs["+ab+"].value'/></th>"+
    "<th>desc：<input id='attr' type='text' name='attrs["+ab+"].desc'/></th>"+
    "<th><img src='http://pic.qiantucdn.com/58pic/15/31/24/74658PICq32_1024.jpg' onclick='replayTh("+ab+")'style='width:25px;height:25px;'/>"

    var newchild = document.createElement("tr");
    newchild.id=ab;
    newchild.style.marginTop="15px";
    newchild.innerHTML = new_ht_str;
    document.getElementById("attrs").appendChild(newchild);
    document.getElementById("addButton").setAttribute("data-number",parseInt(ab)+1);
    /*
    //添加行
    var newTR = tb.insertRow(tb.rows.length);
    //添加列
    var newNameTD1=newTR.insertCell(0);
    newNameTD1.innerHTML="name";
    newNameTD1.innerHTML = "<input type='text' name='attrs["+ab+"].name'";
    var newNameTD2=newTR.insertCell(1);
    newNameTD2.innerHTML="type";
    newNameTD2.innerHTML = "<input type='text' name='attrs["+ab+"].value'";
    var newNameTD3=newTR.insertCell(2);
    newNameTD3.innerHTML="desc";
    newNameTD3.innerHTML = "<input type='text' name='attrs["+ab+"].desc'";
    document.getElementById("addButton").setAttribute("data-number",ab+1);*/
}

/**获取属性集合*/
 function getList(){
    //alert("getList");
    var attr=new Array();
    var inputs=$("input[id=attr]")
    //alert(inputs.length);
    var j=0;
    for(var i=0; i<inputs.length;i=i+3){
        var param=new Object();
        param.name=inputs[i].value;
        param.value=inputs[i+1].value;
        param.desc=inputs[i+2].value;
        attr[j]=param;
        j++
    }
    return attr;
    /*
    var str="";
    $.each(attr,function (name,value,desc) {
        str=+name+value+desc;
    })
   alert(str);*/
}


function por(obj, action) {
    $.ajax({
        type: "POST",
        cache: false,
        //traditional: true,
        dataType: "json",
        url: action,
        data: obj,
        success:function (data) {
            //alert(data);
            showOverlay(data);
        },
        failed:function (data) {
            alert(data);
            showOverlay(data);
        },
        error:   function(data){
            alert(data.message);
            showOverlay(data);
        }
    });
}

function showOverlay(data) {
    var ov=document.getElementById("overlay");
    ov.value=data;
}
//删除属性
function replayTh(data) {
    var tb=document.getElementById("attrs");
    var tr=document.getElementById(data);
    tb.removeChild(tr);
    /*
    var inputs=$("input[id=attr]")
    //alert(inputs.length);
    for(var i=0; i<inputs.length;i=+3){
        inputs[i].name="attrs["+i+"].name";
        inputs[i+1].name="attrs["+i+"].value";
        inputs[i+2].name="attrs["+i+"].desc";
    }*/
}