/**
 * 
 */

$(document).ready(function(){
	//alert('hello');
	//contextpath=myContextPath;
	//var contextPath = "<%=request.getContextPath()%>";
	$("#homepage").on("click",function(){
		$('#showdata').hide();
		$('#featured').show();
		$('#welcome').show();
		$("#insertfile").show();
		
	})
	
	$("#mylist").on("click",function(){
		alert('helloin'); 
  $('#welcome').show();
	//e.preventDefault();
	$('#myTable').html("");
	$("#audname").html("");
	$("#insertfile").hide();

  
  //alert('helloin'); 
	//alert("inside ajax");
	//$("#userdetails").html("");
	$('#myTable').html("");
	$("#audname").html("");
	$("#insertfile").hide();
	$.ajax({
		type:"GET",
		url:"display.htm?action=mylist",
		//{name:"Yusuf"}
		//data:"key="+"Image",
		//dataType:'text',
		contentType: "application/json",
		//async: false,
		dataType : 'json',
		success: function(response) {
		alert("success");	
		$('#welcome').hide();
		$('#featured').hide();
		//$('#showdata').show();

		//alert(response.length);

		//$('#showdata').append("<ul>");
		if(response.length >0)
		{	//alert(response.toSource());
			//var x = document.createElement("TABLE");
          //x.setAttribute("id", "myTable");
          //document.getElementById("showdata").appendChild(x);
			//alert("in if");
			
			

			for(var i=0, l = response.length; i < l; i++){	
				//alert("infor");
				var mycontent = response[i];
				var y = document.createElement("TR");
              y.setAttribute("id", "myTr" + i);
              document.getElementById("myTable").appendChild(y);
              
              var z = document.createElement("TD");
              var ctype=mycontent.content_type;
              //alert(ctype);
              if(ctype=="Article")
              	{
              	//alert(ctype);
              	var csrc=myContextPath+'/resources/articles/'+mycontent.content_name;
                  var t = document.createElement("iframe");
                  //t.canPlayType('video/mp4; codecs="avc1.42E01E,mp4a.40.2"')
                  t.setAttribute("src",csrc);
                  t.setAttribute("type","application/pdf");

                  t.height="400";
                  t.width="400";
                  

                  z.appendChild(t);
              	}
              if(ctype=="Image")
              	{
              	
              	csrc=myContextPath+'/resources/images/'+mycontent.content_name;
              	//alert(csrc);
              	var t = document.createElement("img");
                  t.src=csrc;
                  t.height="200";
                  t.width="400";
                  z.appendChild(t);
              	}
              if(ctype=="Video")
              	{
              	//alert(ctype);
              	csrc=myContextPath+'/resources/videos/'+mycontent.content_name;
              	var t = document.createElement("VIDEO");
                  t.canPlayType('video/mp4; codecs="avc1.42E01E,mp4a.40.2"')
                  t.setAttribute("src",csrc);
                  t.load();
                  //t.play();
                  t.setAttribute("controls", "controls");
                  z.appendChild(t);
              	}
              if(ctype=="Audio")
              	{
              	//alert(ctype);
              	csrc=myContextPath+'/resources/audios/'+mycontent.content_name;
              	var t = document.createElement("AUDIO");
                  t.setAttribute("canPlayType","audio/mpeg")
                  t.setAttribute("src",csrc);
                  t.load();
                  t.setAttribute("controls", "controls");
                  z.appendChild(t);
              	}
              document.getElementById("myTr" + i).appendChild(z);

              var z = document.createElement("TD");
              var a = document.createElement('a');
              var linkText = document.createTextNode("ShareToAll");
              a.appendChild(linkText);
              a.title = "ShareToAll";
              a.href="message.htm?action=sendToAll&description="+csrc;
              //alert(a.href);

              z.appendChild(a);
              document.getElementById("myTr" + i).appendChild(z);
              
              var rowID="myTr"+i;
              var z = document.createElement("TD");
              var id=mycontent.contentID;
              var a = document.createElement('a');
              var linkText = document.createTextNode("Delete");
              a.appendChild(linkText);
              a.title = "Delete";
              //a.href="deleteusers.htm?action=delete&cid="+content.contentID;
              a.href="#";
              //alert(a.href);
              a.onclick = (function (id, rowID) {
                  return function () {
                      deleteRow(id, rowID);
                  	alert("deleted");
                  }
              })(id, rowID);
              z.appendChild(a);
              document.getElementById("myTr" + i).appendChild(z);
			}
		}
		else {
          document.getElementById("showdata").innerHTML="Nothing Found";
      }
	
	},

		error:function(jqXHR, textStatus, errorThrown){
			
			  //alert(textStatus);
				//console.log(errorThrown);
			  //alert(errorThrown);
			  alert("OH, No!");
		}
	});

	
});

$('#articles').click(function() {
	//alert("inside ajax");
	$('#myTable').html("");
	$("#audname").html("");
	$.ajax({
		type:"GET",
		url:"display.htm?action=displayall&key=Article",
		//{name:"Yusuf"}
		//data:"key="+"Image",
		//dataType:'text',
		contentType: "application/json",
		//async: false,
		dataType : 'json',
		//async: false,
		success: function(response) {
		alert("success");	
		$('#welcome').hide();
		$('#featured').hide();
		$('#showdata').show();

		//alert(response.length);

		//$('#showdata').append("<ul>");
		if(response.length >0)
		{	//alert(response.toSource());
			//var x = document.createElement("TABLE");
            //x.setAttribute("id", "myTable");
            //document.getElementById("showdata").appendChild(x);
			//alert("in if");
          
			for(var i=0, l = response.length; i < l; i++){	
				//alert("infor");
				var content = response[i];
				var y = document.createElement("TR");
                y.setAttribute("id", "myTr" + i);
                document.getElementById("myTable").appendChild(y);
                
                var z = document.createElement("TD");
                var g=content.content_name;
                var docsrc=myContextPath+'/resources/articles/'+g;
                //var docsrc="resources/articles/"+content.content_name;
                //alert(docsrc);
                var t = document.createElement("iframe");
                //t.canPlayType('video/mp4; codecs="avc1.42E01E,mp4a.40.2"')
                t.setAttribute("src",docsrc);
                t.setAttribute("type","application/pdf");
                //t.load();
                
                //t.setAttribute("controls", "controls");
                //t.autoPlay = true;
                t.height="400";
                t.width="400";
                z.appendChild(t);
                document.getElementById("myTr" + i).appendChild(z);
                
                /*var z=document.createElement("TD");
                var t=document.createTextNode(content.content_name);
                z.appendChild(t);
                document.getElementById("myTr" + i).appendChild(z);*/
                
                var z=document.createElement("TD");
                
                var x = document.createElement("SELECT");
                x.setAttribute("id", "mySelect"+i);
                document.body.appendChild(x);

                var t = document.createElement("option");
                t.setAttribute("value", "Adult");
                var c = document.createTextNode("Adult");
                t.appendChild(c);
                var t1 = document.createElement("option");
                t1.setAttribute("value", "Children");
                var c = document.createTextNode("Children");
                t1.appendChild(c);
                var t2 = document.createElement("option");
                t2.setAttribute("value", "Teenagers");
                var c = document.createTextNode("Teenagers");
                t2.appendChild(c);
                
                document.getElementById("mySelect"+i).appendChild(t);
                document.getElementById("mySelect"+i).appendChild(t1);
                document.getElementById("mySelect"+i).appendChild(t2);
                
                z.appendChild(x);
                
                document.getElementById("myTr" + i).appendChild(z);
                var z = document.createElement("TD");
                var a = document.createElement('a');
                var linkText = document.createTextNode("Categorize");
                a.appendChild(linkText);
                a.title = "Share";
                var cid=content.contentID;
                //alert(cid);
                var e = document.getElementById("mySelect"+i);
                var ctype = e.options[e.selectedIndex].text;
                a.href="categorize.htm?action=send&status="+ctype+"&cid="+cid;
                //alert(a.href);
                /*a.onclick = (function (id, rowID) {
                    return function () {
                        //deleteRow(id, rowID);
                    	alert("share");
                    }
                })(id, rowID);*/
                z.appendChild(a);
                document.getElementById("myTr" + i).appendChild(z);
                
			
				//alert(content.content_name);
			
			}
		}
		else {
            document.getElementById("showdata").innerHTML="Nothing Found";
        }
	
	},

		error:function(jqXHR, textStatus, errorThrown){
			alert("OH, No!");
			  //alert(textStatus);
				//console.log(errorThrown);
			  //alert(errorThrown);
		}
	});
});

$('#videos').click(function() {
	//alert("inside ajax");
	$('#myTable').html("");
	$("#audname").html("");
	$.ajax({
		type:"GET",
		url:"display.htm?action=displayall&key=Video",
		//{name:"Yusuf"}
		//data:"key="+"Image",
		//dataType:'text',
		contentType: "application/json",
		//async: false,
		dataType : 'json',
		//async: false,
		success: function(response) {
		alert("success");	
		$('#welcome').hide();
		$('#featured').hide();
		$('#showdata').show();

		//alert(response.length);

		//$('#showdata').append("<ul>");
		if(response.length >0)
		{	//alert(response.toSource());
			//var x = document.createElement("TABLE");
           // x.setAttribute("id", "myTable");
            //document.getElementById("showdata").appendChild(x);
			//alert("in if");
          
			for(var i=0, l = response.length; i < l; i++){	
				//alert("infor");
				var content = response[i];
				var y = document.createElement("TR");
                y.setAttribute("id", "myTr" + i);
                document.getElementById("myTable").appendChild(y);
                
                var z = document.createElement("TD");
                var g=content.content_name;
                var vidsrc=myContextPath+'/resources/videos/'+g;
                //var vidsrc="resources/videos/"+content.content_name;
                //alert(audsrc);
                var t = document.createElement("VIDEO");
                t.canPlayType('video/mp4; codecs="avc1.42E01E,mp4a.40.2"')
                t.setAttribute("src",vidsrc);
                t.load();
                //t.play();
                t.setAttribute("controls", "controls");
                
                //t.height="200";
                //t.width="400";
                //t.css({ 'height': '200px', 'width': '200px' });
                //z.appendChild();
                z.appendChild(t);
                document.getElementById("myTr" + i).appendChild(z);
                
                /*var z=document.createElement("TD");
                var t=document.createTextNode(content.content_name);
                z.appendChild(t);
                document.getElementById("myTr" + i).appendChild(z);*/

               
                var z=document.createElement("TD");
                
                var x = document.createElement("SELECT");
                x.setAttribute("id", "mySelect"+i);
                document.body.appendChild(x);

                var t = document.createElement("option");
                t.setAttribute("value", "Adult");
                var c = document.createTextNode("Adult");
                t.appendChild(c);
                var t1 = document.createElement("option");
                t1.setAttribute("value", "Children");
                var c = document.createTextNode("Children");
                t1.appendChild(c);
                var t2 = document.createElement("option");
                t2.setAttribute("value", "Teenagers");
                var c = document.createTextNode("Teenagers");
                t2.appendChild(c);
                
                document.getElementById("mySelect"+i).appendChild(t);
                document.getElementById("mySelect"+i).appendChild(t1);
                document.getElementById("mySelect"+i).appendChild(t2);
                
                z.appendChild(x);
                
                document.getElementById("myTr" + i).appendChild(z);	
             
                
                var z = document.createElement("TD");
                var a = document.createElement('a');
                var linkText = document.createTextNode("Categorize");
                a.appendChild(linkText);
                var e = document.getElementById("mySelect"+i);
                var ctype = e.options[e.selectedIndex].text;
                a.href="categorize.htm?action=send&status="+ctype+"&cid="+content.contentID;
                //alert(a.href);
                /*a.onclick = (function (id, rowID) {
                    return function () {
                        //deleteRow(id, rowID);
                    	alert("share");
                    }
                })(id, rowID);*/
                z.appendChild(a);
                document.getElementById("myTr" + i).appendChild(z);
                
			
				//alert(content.content_name);
			
			}
		}
		else {
            document.getElementById("showdata").innerHTML="Nothing Found";
        }
	
	},

		error:function(jqXHR, textStatus, errorThrown){
			alert("OH, No!");
			  /*alert(textStatus);
				console.log(errorThrown);
			  alert(errorThrown);*/
		}
	});
});

$('#music').click(function() {
	//alert("inside ajax");
	$('#myTable').html("");
	$("#audname").html("");
	$.ajax({
		type:"GET",
		url:"display.htm?action=displayall&key=Audio",
		//{name:"Yusuf"}
		//data:"key="+"Image",
		//dataType:'text',
		contentType: "application/json",
		//async: false,
		dataType : 'json',
		//async: false,
		success: function(response) {
		alert("success");	
		$('#welcome').hide();
		$('#featured').hide();
		$('#showdata').show();

		//alert(response.length);

		//$('#showdata').append("<ul>");
		if(response.length >0)
		{	//alert(response.toSource());
			//var x = document.createElement("TABLE");
            //x.setAttribute("id", "myTable");
            //document.getElementById("showdata").appendChild(x);
			//alert("in if");
          
			for(var i=0, l = response.length; i < l; i++){	
				//alert("infor");
				var content = response[i];
				var y = document.createElement("TR");
                y.setAttribute("id", "myTr" + i);
                document.getElementById("myTable").appendChild(y);
               
                var z = document.createElement("TD");
                var g=content.content_name;
                //var ctx =<%=request.getContextPath()%>;
                //alert(contextpath);
                var audsrc=myContextPath+'/resources/audios/'+g;
                //var audsrc="resources/audios/"+content.content_name;
               // alert(audsrc);
                var t = document.createElement("AUDIO");
                t.setAttribute("canPlayType","audio/mpeg")
                t.setAttribute("src",audsrc);
                t.load();
                //t.play();
                t.setAttribute("controls", "controls");
                //t.setAttribute("autoPlay","true");
                
                //t.height="200";
                //t.width="400";
                //t.css({ 'height': '200px', 'width': '200px' });
                //z.appendChild();
                z.appendChild(t);
                document.getElementById("myTr" + i).appendChild(z);
                
                /*var z=document.createElement("TD");
                var t=document.createTextNode(content.content_name);
                z.appendChild(t);
                document.getElementById("myTr" + i).appendChild(z);*/
                
                var z=document.createElement("TD");
                
                var x = document.createElement("SELECT");
                x.setAttribute("id", "mySelect"+i);
                document.body.appendChild(x);

                var t = document.createElement("option");
                t.setAttribute("value", "Adult");
                var c = document.createTextNode("Adult");
                t.appendChild(c);
                var t1 = document.createElement("option");
                t1.setAttribute("value", "Children");
                var c = document.createTextNode("Children");
                t1.appendChild(c);
                var t2 = document.createElement("option");
                t2.setAttribute("value", "Teenagers");
                var c = document.createTextNode("Teenagers");
                t2.appendChild(c);
                
                document.getElementById("mySelect"+i).appendChild(t);
                document.getElementById("mySelect"+i).appendChild(t1);
                document.getElementById("mySelect"+i).appendChild(t2);
                
                z.appendChild(x);
                
                document.getElementById("myTr" + i).appendChild(z);
                
                //var id = content.user.firstname;
                //alert(id);
                //var rowID = "myTr" + i;
                var z = document.createElement("TD");
                var a = document.createElement('a');
                var linkText = document.createTextNode("Categorize");
                a.appendChild(linkText);
                var e = document.getElementById("mySelect"+i);
                var ctype = e.options[e.selectedIndex].text;
                a.href="categorize.htm?action=send&status="+ctype+"&cid="+content.contentID;
                //alert(a.href);
                /*a.onclick = (function (id, rowID) {
                    return function () {
                        //deleteRow(id, rowID);
                    	alert("share");
                    }
                })(id, rowID);*/
                z.appendChild(a);
                document.getElementById("myTr" + i).appendChild(z);
                
			
				//alert(content.content_name);
			
			}
		}
		else {
            document.getElementById("showdata").innerHTML="Nothing Found";
        }
	
	},

		error:function(jqXHR, textStatus, errorThrown){
			alert("OH, No!");
			  /*alert(textStatus);
				console.log(errorThrown);
			  alert(errorThrown);*/
		}
	});
});

$('#images').click(function() {
	
	//alert("inside ajax");
	$('#myTable').html("");
	$("#audname").html("");
	$.ajax({
		type:"GET",
		url:"display.htm?action=displayall&key=Image",
		//{name:"Yusuf"}
		//data:"key="+"Image",
		//dataType:'text',
		contentType: "application/json",
		//async: false,
		dataType : 'json',
		success: function(response) {
		alert("success");	
		$('#welcome').hide();
		$('#featured').hide();
		$('#showdata').show();

		//alert(response.length);

		$('#showdata').append("<ul>");
		if(response.length >0)
		{	//alert(response.toSource());
			//var x = document.createElement("TABLE");
            //x.setAttribute("id", "myTable");
            //document.getElementById("showdata").appendChild(x);
			//alert("in if");
          
			for(var i=0, l = response.length; i < l; i++){	
				//alert("infor");
				var content = response[i];
				var y = document.createElement("TR");
                y.setAttribute("id", "myTr" + i);
                document.getElementById("myTable").appendChild(y);

                var z = document.createElement("TD");
                var imgsrc="resources/images/"+content.content_name;
                var t = document.createElement("img");
                t.src=imgsrc;
                t.height="200";
                t.width="400";
                //t.css({ 'height': '200px', 'width': '200px' });
                //z.appendChild();
                z.appendChild(t);
                document.getElementById("myTr" + i).appendChild(z);
                
                //code for dropdown selection
                var z=document.createElement("TD");
               
                var x = document.createElement("SELECT");
                x.setAttribute("id", "mySelect"+i);
                document.body.appendChild(x);

                var t = document.createElement("option");
                t.setAttribute("value", "Adult");
                var c = document.createTextNode("Adult");
                t.appendChild(c);
                var t1 = document.createElement("option");
                t1.setAttribute("value", "Children");
                var c = document.createTextNode("Children");
                t1.appendChild(c);
                var t2 = document.createElement("option");
                t2.setAttribute("value", "Teenagers");
                var c = document.createTextNode("Teenagers");
                t2.appendChild(c);
                
                document.getElementById("mySelect"+i).appendChild(t);
                document.getElementById("mySelect"+i).appendChild(t1);
                document.getElementById("mySelect"+i).appendChild(t2);
                
                z.appendChild(x);
                
                document.getElementById("myTr" + i).appendChild(z);
                //var id = content.user.firstname;
                //alert(id);
                var rowID = "myTr" + i;
                var z = document.createElement("TD");
                var a = document.createElement('a');
                var linkText = document.createTextNode("Categorize");
                a.appendChild(linkText);
                var e = document.getElementById("mySelect"+i);
                var ctype = e.options[e.selectedIndex].text;
                a.href="categorize.htm?action=send&status="+ctype+"&cid="+content.contentID;
                //alert(a.href);
                /*a.onclick = (function (id, rowID) {
                    return function () {
                        //deleteRow(id, rowID);
                    	alert("share");
                    }
                })(id, rowID);*/
                z.appendChild(a);
                document.getElementById("myTr" + i).appendChild(z);
                
			
				//alert(content.content_name);
			
			}
		}
		else {
            document.getElementById("showdata").innerHTML="Nothing Found";
        }
	
	},

		error:function(jqXHR, textStatus, errorThrown){
			alert("OH, No!");
			  /*alert(textStatus);
				console.log(errorThrown);
			  alert(errorThrown);*/
		}
	});
});


$('#mymessages').click(function() {
	//alert("inside ajax");
	$('#myTable').html("");
	$("#audname").html("");
	$.ajax({
		type:"GET",
		url:"showmessage.htm",
		//{name:"Yusuf"}
		//data:"key="+"Image",
		//dataType:'text',
		contentType: "application/json",
		//async: false,
		dataType : 'json',
		success: function(response) {
		alert("success");	
		$('#welcome').hide();
		$('#featured').hide();
		$('#showdata').show();

		alert(response.length);

		//$('#showdata').append("<ul>");
		if(response.length >0)
		{	//alert(response.toSource());
			//var x = document.createElement("TABLE");
            //x.setAttribute("id", "myTable");
            //document.getElementById("showdata").appendChild(x);
			//alert("in if");
			
			

			for(var i=0, l = response.length; i < l; i++){	
				//alert("infor");
				var message = response[i];
				var y = document.createElement("TR");
                y.setAttribute("id", "myTr" + i);
                document.getElementById("myTable").appendChild(y);

                var z = document.createElement("TD");
                var imgsrc=message.messageBody;
                //alert(imgsrc);
                /*var from=document.createTextNode("From:");
                z.appendChild(from)*/
                var sender=message.sender;
                //alert(sender);
                var y=document.createTextNode("From:"+sender);
                z.appendChild(y);
                document.getElementById("myTr" + i).appendChild(z);
                
                
                var z = document.createElement("TD");
                var t = document.createElement('a');
                var linkText = document.createTextNode(imgsrc);
                t.appendChild(linkText);
                t.title="message";
                t.href=imgsrc;
                //t.css({ 'height': '200px', 'width': '200px' });
                //z.appendChild();
                //alert(linkText);
                z.appendChild(t);
                document.getElementById("myTr" + i).appendChild(z);
                
                /*var id = content.contentID;
                alert(id);
                var rowID = "myTr" + i;
                var z = document.createElement("TD");
                var a = document.createElement('a');
                var linkText = document.createTextNode("Share");
                a.appendChild(linkText);
                a.title = "Share";
                a.href="message.htm?action=send&description="+imgsrc;
                alert(a.href);
                z.appendChild(a);
                document.getElementById("myTr" + i).appendChild(z);*/
                
			
				//alert(content.content_name);
			
			}
		}
		else {
            document.getElementById("showdata").innerHTML="Nothing Found";
        }
	
	},

		error:function(jqXHR, textStatus, errorThrown){
			alert("OH, No!");
			  /*alert(textStatus);
				console.log(errorThrown);
			  alert(errorThrown);*/
		}
	});
});


function sendMessage(id,rowid){
	
	//write model
}
})



