function jah_switch(url,target)
{

    if (document.getElementById(target).innerHTML != '')
    {
        document.getElementById(target).innerHTML = '';
    }
    else
    {

        jah(url,target);
    }

}
function jah(url,target) {
    // native XMLHttpRequest object
        document.getElementById(target).innerHTML = '<span class="ajah_waiting"><a href="java script:jah(\''+url+'\',\''+target+'\');">sending...</a></span>';
    	
    	
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
        req.onreadystatechange = function() {jahDone(target);};
        
        
        req.open("GET", url, true);
        req.send(null);
    // IE/Windows ActiveX version
    } else if (window.ActiveXObject) {
        req = new ActiveXObject("Microsoft.XMLHTTP");
        if (req) {
            req.onreadystatechange = function() {jahDone(target);};
            req.open("GET", url, true);
            req.send();
        }
    }
}

function jah1(url,target,str) {
    // native XMLHttpRequest object
        
        var quesno= encodeURIComponent(document.getElementById('quesno').value);
		
		var ques= encodeURIComponent(document.getElementById('ques').value);
	
		var ans = encodeURIComponent(document.getElementById('answer1').value);
    	
    	url = url + "?quesno="+quesno+"&ques=" +ques+"&ans=" + ans;
        document.getElementById(target).innerHTML = '<span class="ajah_waiting"><a href="java script:jah1(\''+url+'\',\''+target+'\');">sending...</a></span>';
    	
    	
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
        req.onreadystatechange = function() {jahDone(target);};
       
        
       
        req.open("GET", url, true);
        req.send(null);
    // IE/Windows ActiveX version
    } else if (window.ActiveXObject) {
        req = new ActiveXObject("Microsoft.XMLHTTP");
        if (req) {
            req.onreadystatechange = function() {jahDone(target);};
            
            
            req.open("GET", url, true);
            req.send();
        }
    }
}
function jah2(url,target,str) {
    // native XMLHttpRequest object
        
        var quesno= encodeURIComponent(document.getElementById('quesno').value);
		
		var ques= encodeURIComponent(document.getElementById('ques').value);
	
		var ans = encodeURIComponent(document.getElementById('answer1').value);
    	
    	url = url + "&quesno1="+quesno+"&ques=" +ques+"&ans=" + ans;
        document.getElementById(target).innerHTML = '<span class="ajah_waiting"><a href="java script:jah1(\''+url+'\',\''+target+'\');">sending...</a></span>';
    	
    	
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
        req.onreadystatechange = function() {jahDone(target);};
       
        
       
        req.open("GET", url, true);
        req.send(null);
    // IE/Windows ActiveX version
    } else if (window.ActiveXObject) {
        req = new ActiveXObject("Microsoft.XMLHTTP");
        if (req) {
            req.onreadystatechange = function() {jahDone(target);};
            
            
            req.open("GET", url, true);
            req.send();
        }
    }
}
var nocache = 0;    
function test(j) {
		
		var elementName = "link" + j;							
		var links = document.getElementById(elementName).value;
		
		
		
		jah(links,'inner_response1');
}
								
							
function jahDone(target) {
    // only if req is "loaded"
    if (req.readyState == 4) {
        // only if "OK"
        if (req.status == 200) {
            results = req.responseText;
            document.getElementById(target).innerHTML = results;
        } else {
            document.getElementById(target).innerHTML="jah error:\n" +
                req.statusText;
        }
    }
}
