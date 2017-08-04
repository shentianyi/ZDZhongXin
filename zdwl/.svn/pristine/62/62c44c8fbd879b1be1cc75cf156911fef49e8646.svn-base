document.write("<div id='meizzCalendarLayer' style='position: absolute; z-index: 9999; width: 151; height:193; display: none'>");
document.write("<iframe name='meizzCalendarIframe' id='meizzCalendarIframe' scrolling=no frameborder=0 width=151px height=193px></iframe></div>");
function writeIframe()
{
	var tableMainHeight = 162;
	if(WebCalendar.hourShow||WebCalendar.minuteShow||WebCalendar.secondShow){
		tableMainHeight += 18;
	}
	
    var strIframe = "<html><head><meta http-equiv='Content-Type' content='text/html;'><style>"+
    "*{font-size: 12px; font-family: Arial,Verdana}"+
    ".bg{  color: "+ WebCalendar.lightColor +"; cursor: default; background-color: "+ WebCalendar.darkColor +";}"+
    "table#tableMain{ width: 149; height: "+tableMainHeight+";}"+
    "table#tableWeek td{ color: "+ WebCalendar.lightColor +";}"+
    "table#tableDay  td{ font-weight: bold;}"+
    "td#meizzYearHead, td#meizzYearMonth{color: "+ WebCalendar.wordColor +"}"+
    ".out { text-align: center; border-top: 1px solid "+ WebCalendar.DarkBorder +"; border-left: 1px solid "+ WebCalendar.DarkBorder +";"+
    "border-right: 1px solid "+ WebCalendar.lightColor +"; border-bottom: 1px solid "+ WebCalendar.lightColor +";color: #000000;}"+
    ".over{ text-align: center; border-top: 1px solid #FFFFFF; border-left: 1px solid #FFFFFF;"+
    "border-bottom: 1px solid "+ WebCalendar.DarkBorder +"; border-right: 1px solid "+ WebCalendar.DarkBorder +"}"+
    "</style></head><body onselectstart='return false' style='margin: 0px' oncontextmenu='return false'><form id=meizz name=meizz>";

    strIframe += "<scr"+"ipt language=javascript>"+
    "document.onkeydown = function (e){ e = e ? e : window.event;var charCode = e.which ? e.which : e.keyCode;switch(charCode){  case 27 : parent.hiddenCalendar(); break;"+
    "case 37 : parent.prevM(); break; case 38 : parent.prevY(); break; case 39 : parent.nextM(); break; case 40 : parent.nextY(); break;"+
    "case 84 : document.forms[0].today.click(); break;} charCode = 0; evt.returnValue= false;}</scr"+"ipt>";

    strIframe += "<select name=tmpYearSelect  onblur='parent.hiddenSelect(this)' style='z-index:1;position:absolute;top:3;left:18;display:none'"+
    " onchange='parent.WebCalendar.thisYear =this.value; parent.hiddenSelect(this); parent.writeCalendar();'></select>"+
    "<select name=tmpMonthSelect onblur='parent.hiddenSelect(this)' style='z-index:1; position:absolute;top:3;left:78;display:none'"+
    " onchange='parent.WebCalendar.thisMonth=this.value; parent.hiddenSelect(this); parent.writeCalendar();'></select>"+
    "<select name=tmpHourSelect onblur='parent.hiddenSelect(this)' style='z-index:1; position:absolute;top:171;left:1;display:none'"+
    " onchange='parent.WebCalendar.thisHour=this.value; parent.hiddenSelect(this); parent.writeCalendar();'></select>"+
    "<select name=tmpMinuteSelect onblur='parent.hiddenSelect(this)' style='z-index:1; position:absolute;top:171;left:45;display:none'"+
    " onchange='parent.WebCalendar.thisMinute=this.value; parent.hiddenSelect(this); parent.writeCalendar();'></select>"+
    "<select name=tmpSecondSelect onblur='parent.hiddenSelect(this)' style='z-index:1; position:absolute;top:171;left:95;display:none'"+
    " onchange='parent.WebCalendar.thisSecond=this.value; parent.hiddenSelect(this); parent.writeCalendar();'></select>"+

    "<table id=tableMain class=bg border=0 cellspacing=2 cellpadding=0>"+
    "<tr><td bgcolor='"+ WebCalendar.lightColor +"'>"+
    "    <table width=147 id=tableHead border=0 cellspacing=1 cellpadding=0><tr align=center>"+
    "    <td width=16 height=19 class=bg title='向前翻 1 月&#13;快捷键：←' style='cursor: hand' onclick='parent.prevM()'><b>&lt;</b></td>"+
    "    <td width=63 id=meizzYearHead  title='点击此处选择年份' onclick='parent.funYearSelect(parseInt(this.innerHTML, 10))'"+
    "        onmouseover='this.bgColor=parent.WebCalendar.darkColor; this.style.color=parent.WebCalendar.lightColor'"+
    "        onmouseout='this.bgColor=parent.WebCalendar.lightColor; this.style.color=parent.WebCalendar.wordColor'></td>"+
    "    <td width=52 id=meizzYearMonth title='点击此处选择月份' onclick='parent.funMonthSelect(parseInt(this.innerHTML, 10))'"+
    "        onmouseover='this.bgColor=parent.WebCalendar.darkColor; this.style.color=parent.WebCalendar.lightColor'"+
    "        onmouseout='this.bgColor=parent.WebCalendar.lightColor; this.style.color=parent.WebCalendar.wordColor'></td>"+
    "    <td width=16 class=bg title='向后翻 1 月&#13;快捷键：→' onclick='parent.nextM()' style='cursor: hand'><b>&gt;</b></td></tr></table>"+
    "</td></tr><tr><td height=20><table id=tableWeek border=1 width=147 cellpadding=0 cellspacing=0 ";

    strIframe += " borderColorLight='"+ WebCalendar.darkColor +"' borderColorDark='"+ WebCalendar.lightColor +"'>"+
    "    <tr align=center>"+
"<td height=20 title='星期日'>日</td><td title='星期一'>一</td><td title='星期二'>二</td><td title='星期三'>三</td><td title='星期四'>四</td><td title='星期五'>五</td><td title='星期六'>六</td></tr></table>"+
    "</td></tr><tr><td valign=top width=147 bgcolor='"+ WebCalendar.lightColor +"'>"+
    "    <table id=tableDay height=120 width=147 border=0 cellspacing=1 cellpadding=0>";
         for(var x=0; x<5; x++){ strIframe += "<tr>";
         for(var y=0; y<7; y++)  strIframe += "<td class=out id='meizzDay"+ (x*7+y) +"'></td>"; strIframe += "</tr>";}
         strIframe += "<tr>";
         for(var x=35; x<37; x++) strIframe += "<td class=out id='meizzDay"+ x +"'></td>";
         strIframe +="<td colspan=5 class=out title=''><span onclick='parent.CalendarNull()' title='将日期置空'>置空</span>&nbsp;&nbsp;"+
         "<span onclick='parent.CalendarToday()' title='当前日期时间'>当前</span>&nbsp;&nbsp;" + 
         "<span onfocus='this.blur()' onclick='parent.hiddenCalendar()' title='关闭日历'>关闭</span></td></tr></table>"+
         "</td></tr>";
	
	if(WebCalendar.hourShow||WebCalendar.minuteShow||WebCalendar.secondShow){
		
		 
		strIframe += "<tr><td bgcolor='"+ WebCalendar.lightColor +"'><table border=0 cellpadding=0 cellspacing=1 width=147><tr align='center' height=18 bgcolor='"+ WebCalendar.darkColor +"'><td width=49 id=meizzHourHead  title='点击此处选择小时' onclick='parent.funHourSelect(parseInt(this.innerHTML, 10))'"+
		"        onmouseover='this.bgColor=parent.WebCalendar.darkColor; this.style.color=parent.WebCalendar.lightColor'"+
		"        onmouseout='this.bgColor=parent.WebCalendar.darkColor; this.style.color=parent.WebCalendar.wordColor'></td>"+
		   "<td width=49 id=meizzMinuteHead  title='点击此处选择分钟' onclick='parent.funMinuteSelect(parseInt(this.innerHTML, 10))'"+
		"        onmouseover='this.bgColor=parent.WebCalendar.darkColor; this.style.color=parent.WebCalendar.lightColor'"+
		"        onmouseout='this.bgColor=parent.WebCalendar.darkColor; this.style.color=parent.WebCalendar.wordColor'></td>"+
		   "<td width=49 id=meizzSecondHead  title='点击此处选择秒' onclick='parent.funSecondSelect(parseInt(this.innerHTML, 10))'"+
		"        onmouseover='this.bgColor=parent.WebCalendar.darkColor; this.style.color=parent.WebCalendar.lightColor'"+
		"        onmouseout='this.bgColor=parent.WebCalendar.darkColor; this.style.color=parent.WebCalendar.wordColor'></td></tr>";
		
	}
	
    strIframe += "</table></table></form></body></html>";
    with(WebCalendar.iframe)
    {
        document.writeln(strIframe); document.close();
        for(var i=0; i<37; i++)
        {
            WebCalendar.dayObj[i] = document.getElementById("meizzDay"+ i);
            WebCalendar.dayObj[i].onmouseover = dayMouseOver;
            WebCalendar.dayObj[i].onmouseout  = dayMouseOut;
            WebCalendar.dayObj[i].onclick     = returnDate;
        }
    }
}
function WebCalendar() //初始化日历的设置
{
    this.daysMonth  = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
    this.day        = new Array(37);
    this.dayObj     = new Array(37);
    this.dateStyle  = null;
    this.objExport  = null;                     //日历回传的显示控件
    this.eventSrc   = null;                     //日历显示的触发控件
    this.inputDate  = null;                     //转化外的输入的日期(d/m/yyyy)
    this.thisYear   = new Date().getFullYear();
    this.thisMonth  = new Date().getMonth()+ 1;
    this.thisDay    = new Date().getDate();
    this.thisHour   = new Date().getHours();
    this.thisMinute = new Date().getMinutes();
    this.thisSecond = new Date().getSeconds();
    this.today      = this.thisDay +"/"+ this.thisMonth +"/"+ this.thisYear;   //今天(d/m/yyyy)
    this.iframe     = window.frames["meizzCalendarIframe"]; //日历的 iframe 载体
    this.calendar   = getObjectById("meizzCalendarLayer");  //日历的层
    this.dateReg    = "";           //日历格式验证的正则式

    this.yearFall   = 50;           //定义年下拉框的年差值
	this.hourShow=false;
	this.minuteShow=false;
	this.secondShow=false;
	//this.timeShow   = true;
    this.format     = "yyyy-mm-dd"; 
    this.darkColor  = "#c6262d";    //控件的暗色 8DBDFE    
    this.lightColor = "#FFFFFF";    //控件的亮色
    this.btnBgColor = "#FFFFFF";    //控件的按钮背景色 C9DDFE
    this.wordColor  = "#000040";    //控件的文字颜色
    this.wordDark   = "#B4B4B4";    //控件的暗文字颜色 DCDCDC
    this.dayBgColor = "#FFFFFF";    //日期数字背景色  
    this.todayColor = "#fedd9e";    //今天在日历上的标示背景色  
    this.DarkBorder = "#c6262d";    //日期显示的立体表达色
}

var WebCalendar = new WebCalendar();

function getTarget(e){
	var ev = e || window.event;
	var _target = ev.target || ev.srcElement;
	if(_target.getAttribute("eventType")=="target"){
		return _target;
	}
	
	if(_target.getAttribute("target")){
		return document.getElementById(_target.getAttribute("target"));
	}
	
	if(_target.getAttribute("eventType")=="event"){
		var parentObj = _target.getAttribute("parentNode");
		var inputs = parentObj.getElementsByTagName("INPUT");
		for(var i=0;i<inputs.length;i++){
			if(inputs[i].getAttribute("target")=="target"){
				return inputs[i];
			}
		}
	}
	
	
	return null;
}

function calendarForDay(){
	var evt = window.event || arguments.callee.caller.arguments[0];
	WebCalendar.hourShow=false;
	WebCalendar.minuteShow=false;
	WebCalendar.secondShow=false;
	calendar(evt);
}
function calendarForHour(){
	var evt = window.event || arguments.callee.caller.arguments[0];
	WebCalendar.hourShow=true;
	WebCalendar.minuteShow=false;
	WebCalendar.secondShow=false;
	calendar(evt);
}
function calendarForMinute(){
	var evt = window.event || arguments.callee.caller.arguments[0];
	WebCalendar.hourShow=true;
	WebCalendar.minuteShow=true;
	WebCalendar.secondShow=false;
	calendar(evt);
}
function calendarForSecond(){
	var evt = window.event || arguments.callee.caller.arguments[0];
	WebCalendar.hourShow=true;
	WebCalendar.minuteShow=true;
	WebCalendar.secondShow=true;
	calendar(evt);
}


function calendar(evt)
{
	var e = getTarget(evt);   writeIframe();
    var o = WebCalendar.calendar.style; WebCalendar.eventSrc = e;
	WebCalendar.objExport = e;
    WebCalendar.iframe.document.getElementById("tableWeek").style.cursor = "default";
 var t = e.offsetTop,  h = e.clientHeight, l = e.offsetLeft, p = e.type;
 while (e = e.offsetParent){t += e.offsetTop; l += e.offsetLeft;}
    o.display = ""; WebCalendar.iframe.document.body.focus();
    var cw = WebCalendar.calendar.clientWidth, ch = WebCalendar.calendar.clientHeight;
    var dw = document.body.clientWidth, dl = document.body.scrollLeft, dt = document.body.scrollTop;
    
    if (document.body.clientHeight + dt - t - h >= ch) o.top = (p=="image")? t + h : t + h + 6;
    else o.top  = (t - dt < ch) ? ((p=="image")? t + h : t + h + 6) : t - ch;
    if (dw + dl - l >= cw) o.left = l; else o.left = (dw >= cw) ? dw - cw + dl : dl;
	
	WebCalendar.dateReg = /^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/;
	if(WebCalendar.hourShow)
		WebCalendar.dateReg = /^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2}) (\d{1,2})$/;
	if(WebCalendar.minuteShow)
		WebCalendar.dateReg = /^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2})$/;
	if(WebCalendar.secondShow)
		WebCalendar.dateReg = /^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;

    //if (!WebCalendar.timeShow) WebCalendar.dateReg = /^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/;
    //else WebCalendar.dateReg = /^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;

    try{
        if (WebCalendar.objExport.value.trim() != ""){
            WebCalendar.dateStyle = WebCalendar.objExport.value.trim().match(WebCalendar.dateReg);
            if (WebCalendar.dateStyle == null)
            {
                writeCalendar(); return false;
            }
            else
            {
                WebCalendar.thisYear   = parseInt(WebCalendar.dateStyle[1], 10);
                WebCalendar.thisMonth  = parseInt(WebCalendar.dateStyle[3], 10);
                WebCalendar.thisDay    = parseInt(WebCalendar.dateStyle[4], 10);
                
                WebCalendar.inputDate  = parseInt(WebCalendar.thisDay, 10) +"/"+ parseInt(WebCalendar.thisMonth, 10) +"/"+ 
                parseInt(WebCalendar.thisYear, 10); 
                if (WebCalendar.hourShow)
                  WebCalendar.thisHour   = parseInt(WebCalendar.dateStyle[5], 10);
                if (WebCalendar.minuteShow)
                  WebCalendar.thisMinute = parseInt(WebCalendar.dateStyle[6], 10);
                if (WebCalendar.secondShow)
                  WebCalendar.thisSecond = parseInt(WebCalendar.dateStyle[7], 10);
                writeCalendar();
            }
        }  else {
        	writeCalendar();
        }
    }  catch(e){
    	writeCalendar();
    }
}
function funMonthSelect() 
{
    var m = isNaN(parseInt(WebCalendar.thisMonth, 10)) ? new Date().getMonth() + 1 : parseInt(WebCalendar.thisMonth);
    var e = WebCalendar.iframe.document.forms[0].tmpMonthSelect;
    for (var i=1; i<13; i++) e.options.add(new Option(i +"月", i));
    e.style.display = ""; e.value = m; e.focus(); window.status = e.style.top;
}
function funYearSelect() 
{
    var n = WebCalendar.yearFall;
    var e = WebCalendar.iframe.document.forms[0].tmpYearSelect;
    var y = isNaN(parseInt(WebCalendar.thisYear, 10)) ? new Date().getFullYear() : parseInt(WebCalendar.thisYear);
        y = (y <= 1000)? 1000 : ((y >= 9999)? 9999 : y);
    var min = (y - n >= 1000) ? y - n : 1000;
    var max = (y + n <= 9999) ? y + n : 9999;
        min = (max == 9999) ? max-n*2 : min;
        max = (min == 1000) ? min+n*2 : max;
    for (var i=min; i<=max; i++) e.options.add(new Option(i +"年", i));
    e.style.display = ""; e.value = y; e.focus();
}
function prevM()  
{
    WebCalendar.thisDay = 1;
    if (WebCalendar.thisMonth==1)
    {
        WebCalendar.thisYear--;
        WebCalendar.thisMonth=13;
    }
    WebCalendar.thisMonth--; writeCalendar();
}
function nextM()  //往后翻月份
{
    WebCalendar.thisDay = 1;
    if (WebCalendar.thisMonth==12)
    {
        WebCalendar.thisYear++;
        WebCalendar.thisMonth=0;
    }
    WebCalendar.thisMonth++; writeCalendar();
}
function prevY(){WebCalendar.thisDay = 1; WebCalendar.thisYear--; writeCalendar();}//往前翻 Year
function nextY(){WebCalendar.thisDay = 1; WebCalendar.thisYear++; writeCalendar();}//往后翻 Year
function hiddenSelect(e){for(var i=e.options.length; i>-1; i--)e.options.remove(i); e.style.display="none";}
function getObjectById(id){ if(document.all) return(eval("document.all."+ id)); return(eval(id)); }
function hiddenCalendar(){getObjectById("meizzCalendarLayer").style.display = "none";};
function appendZero(n){return(("00"+ n).substr(("00"+ n).length-2));}//日期自动补零程序
String.prototype.trim=function (){return this.replace(/(^\s*)|(\s*$)/g,"");}
function dayMouseOver()
{
    this.className = "over";
    this.style.backgroundColor = WebCalendar.darkColor;
    if(WebCalendar.day[this.id.substr(8)].split("/")[1] == WebCalendar.thisMonth)
    this.style.color = WebCalendar.lightColor;
}
function dayMouseOut()
{
    this.className = "out"; var d =WebCalendar.day[this.id.substr(8)], a = d.split("/");
    this.style.backgroundColor="";
    if(a[1] == WebCalendar.thisMonth && d != WebCalendar.today)
    {
        if(WebCalendar.dateStyle && a[0] == parseInt(WebCalendar.dateStyle[4], 10))
        this.style.color = WebCalendar.lightColor;
        this.style.color = WebCalendar.wordColor;
    }
}
function writeCalendar() //对日历显示的数据的处理程序
{
    var y = WebCalendar.thisYear;
    var m = WebCalendar.thisMonth; 
    var d = WebCalendar.thisDay;
    WebCalendar.daysMonth[1] = (0==y%4 && (y%100!=0 || y%400==0)) ? 29 : 28;
    if (!(y<=9999 && y >= 1000 && parseInt(m, 10)>0 && parseInt(m, 10)<13 && parseInt(d, 10)>0)){
        alert("对不起，你输入了错误的日期！" + y + "/" + m + "/" + d);
        WebCalendar.thisYear   = new Date().getFullYear();
        WebCalendar.thisMonth  = new Date().getMonth()+ 1;
        WebCalendar.thisDay    = new Date().getDate(); }
    y = WebCalendar.thisYear;
    m = WebCalendar.thisMonth;
    d = WebCalendar.thisDay;
    WebCalendar.iframe.document.getElementById("meizzYearHead").innerHTML  = y +" 年";
    WebCalendar.iframe.document.getElementById("meizzYearMonth").innerHTML  = parseInt(m, 10) +" 月";
    WebCalendar.daysMonth[1] = (0==y%4 && (y%100!=0 || y%400==0)) ? 29 : 28; //闰年二月为29天
    
    var w = new Date(y, m-1, 1).getDay();
    var prevDays = m==1  ? WebCalendar.daysMonth[11] : WebCalendar.daysMonth[m-2];
    for(var i=(w-1); i>=0; i--) 
    {
        WebCalendar.day[i] = prevDays +"/"+ (parseInt(m, 10)-1) +"/"+ y;
        if(m==1){
        	WebCalendar.day[i] = prevDays +"/"+ 12 +"/"+ (parseInt(y, 10)-1);
        }
        prevDays--;
    }
    for(var i=1; i<=WebCalendar.daysMonth[m-1]; i++) WebCalendar.day[i+w-1] = i +"/"+ m +"/"+ y;
    for(var i=1; i<37-w-WebCalendar.daysMonth[m-1]+1; i++)
    {
        WebCalendar.day[WebCalendar.daysMonth[m-1]+w-1+i] = i +"/"+ (parseInt(m, 10)+1) +"/"+ y;
        if(m==12) WebCalendar.day[WebCalendar.daysMonth[m-1]+w-1+i] = i +"/"+ 1 +"/"+ (parseInt(y, 10)+1);
    }
    for(var i=0; i<37; i++)    
    {
        var a = WebCalendar.day[i].split("/");
        WebCalendar.dayObj[i].innerHTML    = a[0];
        WebCalendar.dayObj[i].title        = a[2] +"-"+ appendZero(a[1]) +"-"+ appendZero(a[0]);
        WebCalendar.dayObj[i].bgColor      = WebCalendar.dayBgColor;
        WebCalendar.dayObj[i].style.color  = WebCalendar.wordColor;
        if ((i<10 && parseInt(WebCalendar.day[i], 10)>20) || (i>27 && parseInt(WebCalendar.day[i], 10)<12))
            WebCalendar.dayObj[i].style.color = WebCalendar.wordDark;
        if (WebCalendar.inputDate==WebCalendar.day[i])    //设置输入框里的日期在日历上的颜色
        {WebCalendar.dayObj[i].bgColor = WebCalendar.darkColor; WebCalendar.dayObj[i].style.color = WebCalendar.lightColor;}
        if (WebCalendar.day[i] == WebCalendar.today)      //设置今天在日历上反应出来的颜色
        {WebCalendar.dayObj[i].bgColor = WebCalendar.todayColor; WebCalendar.dayObj[i].style.color = WebCalendar.lightColor;}
    }
	
	if(WebCalendar.hourShow){
       var h = WebCalendar.thisHour;
       if(!(h<=23 && h>=0)){
         WebCalendar.thisHour   = new Date().getHours();
       	 h = WebCalendar.thisHour;
	   }
       WebCalendar.iframe.document.getElementById("meizzHourHead").innerHTML    = h +" 时";
	}
	
	if(WebCalendar.minuteShow){
       var mi= WebCalendar.thisMinute; 
       if(!(mi <=59 && mi >=0)){
        WebCalendar.thisMinute = new Date().getMinutes();
        mi= WebCalendar.thisMinute; 
	   }
       WebCalendar.iframe.document.getElementById("meizzMinuteHead").innerHTML  = mi +" 分";
	}
	
	if(WebCalendar.secondShow){
       var s = WebCalendar.thisSecond;
       if(!(s <= 59 && s >= 0)){
        WebCalendar.thisSecond = new Date().getSeconds();
        s = WebCalendar.thisSecond;
	   }
      WebCalendar.iframe.document.getElementById("meizzSecondHead").innerHTML  = s +" 秒";
	}
	
	
	/*
    if(WebCalendar.timeShow)
    {
       var h = WebCalendar.thisHour;
       var mi= WebCalendar.thisMinute; 
       var s = WebCalendar.thisSecond;
       if(!(h<=23 && h>=0 && mi <=59 && m >=0 && s <= 59 && s >= 0)){
         WebCalendar.thisHour   = new Date().getHours();
        WebCalendar.thisMinute = new Date().getMinutes();
        WebCalendar.thisSecond = new Date().getSeconds();
        h = WebCalendar.thisHour;
        mi= WebCalendar.thisMinute; 
        s = WebCalendar.thisSecond;
      }
      WebCalendar.iframe.meizzHourHead.innerHTML    = h +" 时";
      WebCalendar.iframe.meizzMinuteHead.innerHTML  = mi +" 分";
      WebCalendar.iframe.meizzSecondHead.innerHTML  = s +" 秒";
    }
	*/
}
function returnDate() //根据日期格式等返回用户选定的日期
{
    if(WebCalendar.objExport)
    {
        var returnValue;
        var a =  WebCalendar.day[this.id.substr(8)].split("/");
        var d = WebCalendar.format.match(/^(\w{4})(-|\/|.|)(\w{1,2})\2(\w{1,2})$/);
        if(d==null){alert("你设定的日期输出格式不对！"); return false;}
        var flag = d[3].length==2 || d[4].length==2; 
        returnValue = flag ? a[2] +d[2]+ appendZero(a[1]) +d[2]+ appendZero(a[0]) : a[2] +d[2]+ a[1] +d[2]+ a[0];
	
		if(WebCalendar.hourShow){
		   var h = WebCalendar.thisHour;
            returnValue += flag ? " " + appendZero(h) : " " + h;
		}
	
		if(WebCalendar.minuteShow){
		   var m = WebCalendar.thisMinute;
            returnValue += flag ? ":" + appendZero(m) : ":" + m;
		}
	
		if(WebCalendar.secondShow){
		   var s = WebCalendar.thisSecond;
            returnValue += flag ? ":" + appendZero(s) : ":" + s;
		}
		/*
        if(WebCalendar.timeShow)
        {
            var h = WebCalendar.thisHour;
            var m = WebCalendar.thisMinute;
            var s = WebCalendar.thisSecond;
            returnValue += flag ? " "+ appendZero(h) +":"+ appendZero(m) +":"+ appendZero(s) : " "+  h  +":"+ m +":"+ s;
        }
		*/
		
		WebCalendar.objExport.innerHTML?WebCalendar.objExport.innerHTML=returnValue:WebCalendar.objExport.value = returnValue;
		
		
		
        
        hiddenCalendar();
    }
}
document.onclick = function ()
{
    var e = getTarget();
	if(e==null)
		hiddenCalendar();
}
//增加 小时、分钟
function funHourSelect(strHour) //小时的下拉框
{
 if (!WebCalendar.hourShow){return;}
 
    var h = isNaN(parseInt(WebCalendar.thisHour, 10)) ? new Date().getHours() : parseInt(WebCalendar.thisHour);
    var e = WebCalendar.iframe.document.forms[0].tmpHourSelect;
    for (var i=0; i<24; i++) e.options.add(new Option(i+"时", i));
    e.style.display = ""; e.value = h; e.focus();
}

function funMinuteSelect(strMinute) //分钟的下拉框
{
 if (!WebCalendar.minuteShow){return;}
 
    var mi = isNaN(parseInt(WebCalendar.thisMinute, 10)) ? new Date().getMinutes() : parseInt(WebCalendar.thisMinute);
    var e = WebCalendar.iframe.document.forms[0].tmpMinuteSelect;
    for (var i=0; i<60; i++) e.options.add(new Option(i+"分", i));
    e.style.display = ""; e.value = mi; e.focus();
}

function funSecondSelect(strSecond) //秒的下拉框
{
 if (!WebCalendar.secondShow){return;}
 
    var m = isNaN(parseInt(WebCalendar.thisSecond, 10)) ? new Date().getSeconds() : parseInt(WebCalendar.thisSecond);
    var e = WebCalendar.iframe.document.forms[0].tmpSecondSelect;
    for (var i=0; i<60; i++) e.options.add(new Option(i+"秒", i));
    e.style.display = ""; e.value = m; e.focus();
}
function CalendarToday() //Today Button
{
  if(WebCalendar.objExport)
  {
    WebCalendar.thisYear  = new Date().getFullYear();
   WebCalendar.thisMonth   = new Date().getMonth()+1;
   WebCalendar.thisDay    = new Date().getDate();
    returnValue = WebCalendar.thisYear + "-" + appendZero(WebCalendar.thisMonth) + "-" + appendZero(WebCalendar.thisDay);
    
	
	if(WebCalendar.hourShow){
      WebCalendar.thisHour  = new Date().getHours();
		returnValue += " " + appendZero(WebCalendar.thisHour);
	}
	
	if(WebCalendar.minuteShow){
     WebCalendar.thisMinute = new Date().getMinutes();
		returnValue += ":" + appendZero(WebCalendar.thisMinute);
	}
	
	if(WebCalendar.secondShow){
     WebCalendar.thisSecond = new Date().getSeconds();
		returnValue += ":" + appendZero(WebCalendar.thisSecond);
	}
	
	
	/*
	if(WebCalendar.timeShow)
    {
      WebCalendar.thisHour  = new Date().getHours();
     WebCalendar.thisMinute = new Date().getMinutes();
     WebCalendar.thisSecond = new Date().getSeconds();
      returnValue += " " + appendZero(WebCalendar.thisHour) + ":" + appendZero(WebCalendar.thisMinute) + ":" + appendZero(WebCalendar.thisSecond);
    }
	*/
    WebCalendar.objExport.value = returnValue;
    hiddenCalendar();
  }
}
function CalendarNull()
{
  WebCalendar.objExport.value = '';
  hiddenCalendar();
}
function EngMonth(iMonth)
{
	if(iMonth == 1) return "Jan.";
	if(iMonth == 2) return "Feb.";
	if(iMonth == 3) return "Mar.";
	if(iMonth == 4) return "Apr.";
	if(iMonth == 5) return "May.";
	if(iMonth == 6) return "Jun.";
	if(iMonth == 7) return "Jul.";
	if(iMonth == 8) return "Aug.";
	if(iMonth == 9) return "Sep.";
	if(iMonth == 10) return "Oct.";
	if(iMonth == 11) return "Nov.";
	if(iMonth == 12) return "Dec.";
}
