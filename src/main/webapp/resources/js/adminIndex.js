/**
 * 
 */

$(document).ready(function(){
	
	//alert('hello');
	$("#homepage").on("click",function(){
		$('#showdata').hide();
		$('#featured').show();
		$('#welcome').show();
		
	})
	
	
	//contextpath=myContextPath;
	//var contextPath = "<%=request.getContextPath()%>";
	$("#manageUsers").on("click",function(){
		//alert("in manage users");
		$('#welcome').hide();
		$('#featured').hide();
		$('#showdata').show();
		
	
	$("#users").change(function(){
		
		//alert("in fun");
		var value = $("#users option:selected").val();
		
		//alert(value);
		$("#myTable").html("");
		$.ajax({
			type:"GET",
			url:"showusers.htm?userid="+value,
			contentType: "application/json",
			dataType : 'json',
			success: function(response) {
			alert("success");	

			
			//alert(response.toSource());
			$('#userdetails').show();
			//var x = document.createElement("TABLE");
            //x.setAttribute("id", "myTable");
            //x.style.border="1px solid black";
           // document.getElementById("userdetails").appendChild(x);
            var y = document.createElement("TR");
            y.setAttribute("id", "myTr");
            document.getElementById("myTable").appendChild(y);

            var z = document.createElement("TH");
            var t = document.createTextNode("User Name");
            z.appendChild(t);
            document.getElementById("myTr").appendChild(z);

            var z = document.createElement("TH");
            var t = document.createTextNode("Email");
            z.appendChild(t);
            document.getElementById("myTr").appendChild(z);

            var z = document.createElement("TH");
            var t = document.createTextNode("User Role");
            z.appendChild(t);
            document.getElementById("myTr").appendChild(z);
            
            var z = document.createElement("TH");
            var t = document.createTextNode("Delete");
            z.appendChild(t);
            document.getElementById("myTr").appendChild(z);
			//$('#showdata').append("<ul>");
			if(response!=null)
			{	//alert(response.toSource());
	

				//alert("in if");
				
				//for(var i=0, l = response.length; i < l; i++){	
					//alert("infor");
					var user = response;
					var y = document.createElement("TR");
	                y.setAttribute("id", "myTrs");
	                document.getElementById("myTable").appendChild(y);

	                var z = document.createElement("TD");
	                var accountuser=user.firstname+user.lastname;
	                //alert(sender);
	                var y=document.createTextNode(accountuser);
	                z.appendChild(y);
	                document.getElementById("myTrs").appendChild(z);
	                
	                var z = document.createElement("TD");
	                var emailid=user.emailId;
	                //alert(emailid);
	                var y=document.createTextNode(emailid);
	                z.appendChild(y);
	                document.getElementById("myTrs").appendChild(z);
	                
	                var z = document.createElement("TD");
	                var role=user.roletype;
	                //alert(role);
	                var y=document.createTextNode(role);
	                z.appendChild(y);
	                document.getElementById("myTrs").appendChild(z);
	                
	                
	                var id =user.personID;
	                //alert(id);
	                var z = document.createElement("TD");
	                var t = document.createElement('a');
	                var linkText = document.createTextNode("delete");
	                var rowid="myTable";
	                t.appendChild(linkText);
	                t.title="delete";
	                t.onclick = (function (id, rowid) {
	                	//alert("in delete");
                        return function () {
                            deleteUser(id, rowid);
                            //location.reload();
                        }
                    })(id, rowid);
	                //t.href="showusers.htm?action=delete&uid="+user.personID;

	                z.appendChild(t);
	                document.getElementById("myTrs").appendChild(z);
				
				//}
			}
			else {
	            document.getElementById("userdetails").innerHTML="Nothing Found";
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
	
	
	});

$("#myList").on("click",function(){
		//alert('helloin'); 
		//alert("inside ajax");
		//$("#userdetails").html("");
		$('#myTable').html("");
		$("#audname").html("");
		$("#insertfile").hide();
		$("#showdata").hide();
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
	                    	//alert("deleted");
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


$('#mymessages').click(function() {
	//alert("inside ajax");
	//$("#userdetails").html("");
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

		//alert(response.length);

		//$('#showdata').append("<ul>");
		if(response.length >0)
		{	//alert(response.toSource());
			var x = document.createElement("TABLE");
            x.setAttribute("id", "myTable");
            document.getElementById("showdata").appendChild(x);
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
			
			  //alert(textStatus);
				//console.log(errorThrown);
			  //alert(errorThrown);
			  alert("OH, No!");
		}
	});
});


function deleteUser(id,rowid){
	//alert("inside delete function");
	//alert(id);
	//alert(rowid);
	$.ajax({
		
		type:"GET",
		url:"deleteusers.htm?action=delete&uid="+id,
		contentType: "application/json",
		dataType : 'json',
		success: function(response) {
		alert("success");	
        var row = document.getElementById(rowid);
        row.parentNode.removeChild(row);
        location.reload();
		},
		error:function(jqXHR, textStatus, errorThrown){
			
			  //alert(textStatus);
				//console.log(errorThrown);
			  //alert(errorThrown);
			  alert("OH, No!");
		}
	
	});

	return false;
}

function deleteRow(id,rowid){
	/*alert("inside delete function");
	alert(id);
	alert(rowid);*/
	$.ajax({
		
		type:"GET",
		url:"deletecontent.htm?action=delete&cid="+id,
		contentType: "application/json",
		dataType : 'json',
		success: function(response) {
		alert("success");	
        var row = document.getElementById(rowid);
        row.parentNode.removeChild(row);
        location.reload();
		},
		error:function(jqXHR, textStatus, errorThrown){
			
			  //alert(textStatus);
				//console.log(errorThrown);
			  //alert(errorThrown);
			  alert("OH, No!");
		}
	
	});

	return false;
}
})



