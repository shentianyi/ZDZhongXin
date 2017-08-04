var getFFVersion=navigator.userAgent.substring(navigator.userAgent.indexOf("Firefox")).split("/")[1]
//extra height in px to add to iframe in FireFox 1.0+ browsers
var FFextraHeight=getFFVersion>=0.1? 16 : 0

function dyniframesize(down) {
var pTar = null;
if (document.getElementById){
pTar = document.getElementById(down);
 if(pTar == null)
	 pTar =  parent.document.getElementById(down);
}
else{
eval('pTar = ' + down + ';');
}

if (pTar && !window.opera){
//begin resizing iframe
pTar.style.display="block";

if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight){
//ns6 syntax
pTar.height = pTar.contentDocument.body.offsetHeight+FFextraHeight+20;
	if(pTar.height < 200){
		pTar.height = pTar.contentDocument.body.offsetHeight+FFextraHeight+120+"px";
	}
}
else if (pTar.Document && pTar.Document.body.scrollHeight){
//ie5+ syntax
pTar.height = pTar.Document.body.scrollHeight+20;
	if(pTar.height < 200){
		pTar.height = pTar.Document.body.scrollHeight+120+"px";
	}
}
}
}
