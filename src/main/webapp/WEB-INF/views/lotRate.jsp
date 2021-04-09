<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.button {
	    background-color: #b1cdf9; /* Green */
	    border: none;
	    color: white;
	    padding: 5px 5px;
	    margin: 2px 2px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 16px;
	}
table, th, td {
   border: 1px solid black;
   
}
td {
padding: 2px;
}
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}


/* Modal Content/Box */
.modal-content {
    background-color: #fefefe;
    margin: 15% auto; /* 15% from the top and centered */
    padding: 20px;
    border: 1px solid #888;
    width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button */
.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
		<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery.min.js"></script>
		<script>
		
		var shownLotId=-1;
		function exportData(link){
			var val = $("#id_selectList").val();
			
			if (val!=""){
				link.href = "ExportLotRates?lotId="+val;
			}
		}
		function loadLotTenderWise( lotId, tenderId){
			$.ajax({
	            type: "GET",
	            url: "tenderLotDetails",
	            data: {
	            	lotId:lotId,
	            	tenderId: tenderId
	            },
	            dataType: "json",
	            async: true,
	            success: function (data) {
	            	//showLotId=control;
	            	var innerText="<table cellpadding='0' cellspacing='0' style='width: 100%'><tr style='background-color: #D3D3D3;'><td>Party Name </td><td>Priority</td><td>Rate</td><td>Amount</td><td> Rank</td></tr>";
	            	for (var itr=0;itr<data.length;itr++){
	            		innerText+=
	            		"<tr>"+
	            		"<td >"+data[itr].party_name+"</td>"+
	            		"<td >"+data[itr].priority+"</td>"+
	            		"<td >"+data[itr].rate+"</td>"+
	            		"<td >"+data[itr].amount+"</td>"+
	            		"<td >"+data[itr].rank+"</td>"+
	            		//"<td >"+data[itr].bid_count+" <a href='#' onclick='loadLotTenderWise("+control+"," + data[itr].tender_id+")'> Details </a> </td>
	            		"</tr>";
	            	}
	            	
	            	
	            	$("#lotDetails").css({ display: "block" });
	            	$("#lotData").html( innerText +"</table>");
	            },
	            error: function (data) {
	                
	            },
	        });
		}
		
		function loadLotData(cur){
			
			var control = cur;
			
			if (control != shownLotId){
				$("#bid_summary").html("");
				$.ajax({
		            type: "GET",
		            url: "lotBids",
		            data: {
		            	lotId:control
		            },
		            dataType: "json",
		            async: true,
		            success: function (data) {
		            	showLotId=control;
		            	var innerText="<table cellpadding='0' cellspacing='0' style='width: 100%'><tr style='background-color: #D3D3D3;'><td>Tender Name</td><td>Party Name </td><td>Rate</td><td> Bid Count</td></tr>";
		            	for (var itr=0;itr<data.length;itr++){
		            		innerText+=
		            		"<tr><td >"+data[itr].tender_name+"</td>"+
		            		"<td >"+data[itr].party_name+"</td>"+
		            		"<td >"+data[itr].rate+"</td>"+
		            		"<td >"+data[itr].bid_count+ " <a href='#' onclick='loadLotTenderWise("+control+"," + data[itr].tender_id+")'> Details </a> </td></tr>";
		            	}
		            	$("#bid_summary").html( innerText +"</table>");
		            },
		            error: function (data) {
		                
		            },
		        });
			}
			
			$.ajax({
	            type: "GET",
	            url: "lotRates",
	            data: {
	            	lotId:control
	            },
	            dataType: "json",
	            async: true,
	            success: function (data) {
	            	cur=1;
	            	addNewRow(cur-1);
	            	for (var itr=0;itr<data.length;itr++){
	            	
	            	
	            	$("#tr_"+cur).find('[id^=select_lid]').attr("data-value",data[itr].lot_id);
	            	setLotValue(cur,data[itr].lot_id);
	            	$("#tr_"+cur).find("#id_weight").val(data[itr].weight);
	    			$("#tr_"+cur).find("#id_qty").val(data[itr].qty)
	    			$("#tr_"+cur).find("#id_labour").val(data[itr].labour)
	    			$("#tr_"+cur).find("#id_gst").val(data[itr].gst_rate)
	    			
	    			$("#tr_"+cur).find("#id_kgRate").val(data[itr].kg_rate);
	    			$("#tr_"+cur).find("#id_sbRate").val(data[itr].sb_rate);
	    			
	    			$("#tr_"+cur).find("#id_kgTax").val(data[itr].kg_tax);
	    			$("#tr_"+cur).find("#id_kgExpenses").val(data[itr].kg_expenses);
	    			$("#tr_"+cur).find("#id_kgGST").val(data[itr].kg_gst);
	    			$("#tr_"+cur).find("#id_kgTotal").val(data[itr].kg_total);
	    			
	    			$("#tr_"+cur).find("#id_sbTax").val(data[itr].sb_tax);
	    			$("#tr_"+cur).find("#id_sbExpenses").val(data[itr].sb_expenses);
	    			$("#tr_"+cur).find("#id_sbGST").val(data[itr].sb_gst);
	    			$("#tr_"+cur).find("#id_sbTotal").val(data[itr].sb_total);
	    			$("#tr_"+cur).find("#id_totalValue").val(data[itr].total_value);
	    			$("#tr_"+cur).find("#id_kgCost").val(data[itr].kg_cost);
	    			$("#tr_"+cur).find("#id_pref").val(data[itr].preference);
	    			cur=cur+1;
	    			addNewRow(cur-1);
	            	}
	            },
	            error: function (data) {
	                
	            },
	        });
			
		}
		
		function loadLotDetails(cur){
			
			var control = $("#select_lid"+cur).attr("data-value");
			if (control==""){
				return;
			}
			if (control != shownLotId){
			$.ajax({
	            type: "GET",
	            url: "lotBids",
	            data: {
	            	lotId:control
	            },
	            dataType: "json",
	            async: true,
	            success: function (data) {
	            	shownLotId=data;
	            	var innerText="<table cellpadding='0' cellspacing='0' style='width: 100%'><tr style='background-color: #D3D3D3;'><td>Tender Name</td><td>Party Name </td><td>Rate</td><td>Priority</td><td>Rank</td><td> Bid Count</td></tr>";
	            	for (var itr=0;itr<data.length;itr++){
	            		innerText+=
	            		"<tr><td >"+data[itr].tender_name+"</td>"+
	            		"<td >"+data[itr].party_name+"</td>"+
	            		"<td >"+data[itr].rate+"</td>"+
	            		"<td >"+data[itr].priority+"</td>"+
	            		"<td >"+data[itr].rank+"</td>"+
	            		"<td >"+data[itr].bid_count+" <a href='#' onclick='loadLotTenderWise("+control+"," + data[itr].tender_id+")'> Details </a> </td></tr>";
	            	}
	            	$("#bid_summary").html( innerText +"</table>");
	            },
	            error: function (data) {
	                
	            },
	        });
			}
			
		}
		
		function calculate(cur, type){
			var weight =parseFloat($("#tr_"+cur).find("#id_weight").val());
			var qty = parseFloat($("#tr_"+cur).find("#id_qty").val());
			var labour = parseFloat($("#tr_"+cur).find("#id_labour").val());
			var gst = parseFloat($("#tr_"+cur).find("#id_gst").val());
			
			var kgRate = parseFloat($("#tr_"+cur).find("#id_kgRate").val()).toFixed(2);
			var sbRate = parseFloat($("#tr_"+cur).find("#id_sbRate").val()).toFixed(0);
			
			if (type==2){
				var kg_total= parseFloat($("#tr_"+cur).find("#id_kgTotal").val()).toFixed(2);
				var kg_basic = parseFloat((kg_total/((100+gst)/100)-labour)/1.03).toFixed(2);
				kgRate=kg_basic;
			}
			
			if (type==1){
				kgRate=parseFloat(sbRate/weight).toFixed(2);
				$("#tr_"+cur).find("#id_kgRate").val(kgRate);
			}else{
				sbRate=kgRate*weight;
				$("#tr_"+cur).find("#id_sbRate").val(sbRate);
			}
			
			var sbRate = parseFloat($("#tr_"+cur).find("#id_sbRate").val());
			
			$("#tr_"+cur).find("#id_kgRate").val(parseFloat(kgRate).toFixed(2));
			$("#tr_"+cur).find("#id_sbRate").val(parseFloat(sbRate).toFixed(0));
			$("#tr_"+cur).find("#id_kgTax").val((kgRate*0.03).toFixed(2));
			$("#tr_"+cur).find("#id_kgExpenses").val(labour);
			$("#tr_"+cur).find("#id_kgGST").val((((kgRate*1.03)+labour)*gst/100).toFixed(2));
			$("#tr_"+cur).find("#id_kgCost").val((((kgRate*1.03)+labour)).toFixed(2));
			$("#tr_"+cur).find("#id_kgTotal").val((((kgRate*1.03)+labour)*(100+gst)/100).toFixed(2));
			
			$("#tr_"+cur).find("#id_sbTax").val((sbRate*0.03).toFixed(2));
			$("#tr_"+cur).find("#id_sbExpenses").val((labour*weight).toFixed(2));
			$("#tr_"+cur).find("#id_sbGST").val((((sbRate*1.03)+(labour*weight))*gst/100).toFixed(2));
			$("#tr_"+cur).find("#id_sbTotal").val((((sbRate*1.03)+(labour*weight))*(100+gst)/100).toFixed(2));
			$("#tr_"+cur).find("#id_totalValue").val((sbRate*qty).toFixed(2));
		}
		
		function clearTable(){
			$("#table_lot").find("tr:gt(0)").remove();
		}
		function clearRow(cur){
			$("#tr_"+cur).find("#id_weight").val("");
			$("#tr_"+cur).find("#id_qty").val("");
			$("#tr_"+cur).find("#id_labour").val("18");
			$("#tr_"+cur).find("#id_gst").val("18");
			
			$("#tr_"+cur).find("#id_kgRate").val("");
			$("#tr_"+cur).find("#id_sbRate").val("");
			
			
			
			$("#tr_"+cur).find("#id_sbRate").val("");
			
			
			$("#tr_"+cur).find("#id_kgTax").val("");
			$("#tr_"+cur).find("#id_kgExpenses").val("");
			$("#tr_"+cur).find("#id_kgGST").val("");
			$("#tr_"+cur).find("#id_kgCost").val("");
			$("#tr_"+cur).find("#id_kgTotal").val("");
			
			$("#tr_"+cur).find("#id_sbTax").val("");
			$("#tr_"+cur).find("#id_sbExpenses").val("");
			$("#tr_"+cur).find("#id_sbGST").val("");
			$("#tr_"+cur).find("#id_sbTotal").val("");
			$("#tr_"+cur).find("#id_totalValue").val("");
		}
		
		function saveForm(){
			console.log("inside saveForm()");
			var jsonArray = [];
			$('[id^=tr_]').each(function (){
				var weight =parseInt($(this).find("#id_weight").val());
				var qty = parseInt($(this).find("#id_qty").val());
				var labour = parseInt($(this).find("#id_labour").val());
				var gst = parseInt($(this).find("#id_gst").val());
				var kgRate = $(this).find("#id_kgRate").val();
				var sbRate = $(this).find("#id_sbRate").val();
				var kgTax = $(this).find("#id_kgTax").val();
				var sbTax = $(this).find("#id_sbTax").val();
				var kgExpenses = $(this).find("#id_kgExpenses").val();
				var kgGst=$(this).find("#id_kgGST").val();
				var kgTotal=$(this).find("#id_kgTotal").val();
				var sbExpenses = $(this).find("#id_sbExpenses").val();
				var sbGst=$(this).find("#id_sbGST").val();
				var sbTotal=$(this).find("#id_sbTotal").val();
				
				var lotId= $(this).find('[id^=select_lid]').attr("data-value");
				
				var sbExpenses = $(this).find("#id_sbExpenses").val();
				var sbTotalValue = $(this).find("#id_totalValue").val();
				var kgCost = $(this).find("#id_kgCost").val();
				var pref = $(this).find("#id_pref").val();
				var listId = $("#id_selectList").val();
				
				var json =  {
		            	"lot_id":lotId,
		            	"qty":qty,
		            	"weight":weight,
		            	"labour":labour,
		            	"gst_rate":gst,
		            	"kg_rate":kgRate,
		            	"sb_rate":sbRate,
		            	"kg_tax":kgTax,
		            	"kg_cost":kgCost,
		            	"total_value":sbTotalValue,
		            	"sb_tax":sbTax,
		            	"kg_expenses":kgExpenses,
		            	"sb_expenses":sbExpenses,
		            	"kg_gst":kgGst,
		            	"sb_gst":sbGst,
		            	"kg_total":kgTotal,
		            	"preference":pref,
		            	"sb_total":sbTotal,
		            	"list_id":listId
		            };
				if (json["lot_id"]!=""){
					jsonArray.push(json);
				}
				
			});
				
			var a = JSON.stringify(jsonArray);
			$.ajax({
	            type: "POST",
	            url: "saveLotData",
	            data:a,
	            contentType: "application/json; charset=utf-8",
	            
	            async: false,
	            success: function (data) {
	            	/* $('[id^=tr_]').each(function (){
	            		$(this).remove();
	            	});
	            	addNewRow(1); */
	            	alert("data saved successfully");
	            },
	            error: function (data) {
	                alert("error");
	            },
	        });
		}
		
		function removeRow(curRow){
			$("#tr_"+curRow ).remove();
		}
		
		function addNewRow(cur_idx){
			if (cur_idx>0){
				$("#tr_"+cur_idx+" td:first").empty().append("<a href='#' onclick='removeRow("+cur_idx+")'> Del</a>");	
				$("#tr_"+cur_idx+" td:last").empty().append("<a href='#' onclick='removeRow("+cur_idx+")'> Del</a>");
			}
			var new_idx=cur_idx+1;
			var row_text = "<tr id='tr_"+new_idx+"'> <td><a href='#' onclick='addNewRow("+new_idx+")' >Add</a></td>"+
							"<td><input type='text' name='lid' data-value='' id='select_lid"+new_idx+"' list='list_name'  onchange='lotChange("+new_idx+")' onfocus='loadLotDetails("+new_idx+")'> "  +
							
		    		"</select> </td>"+
		    		"<td> <input type='text' id='id_pref' size='3' onfocus='loadLotDetails("+new_idx+")' ></td>"+
					"<td>  <input type='text' id='id_weight' onchange='calculate("+new_idx+");' size='3' onfocus='loadLotDetails("+new_idx+")' > </td>"+
			    	"<td> <input type='text' id='id_qty' onchange='calculate("+new_idx+");' size='5' onfocus='loadLotDetails("+new_idx+")' > </td>"+
			    	"<td> <input type='text'  id='id_labour' onchange='calculate("+new_idx+");' value='18' size='3' onfocus='loadLotDetails("+new_idx+")'></td>"+
			    	"<td> <input type='text'  id='id_gst' onchange='calculate("+new_idx+");' value='18' size='3' onfocus='loadLotDetails("+new_idx+")'> 	</td>"+
			    	"<td> <input type='text' id='id_kgTotal' onchange='calculate("+new_idx+",2);' size='10' onfocus='loadLotDetails("+new_idx+")'></td>"+
			    	"<td> <input type='text' id='id_sbRate' onchange='calculate("+new_idx+",1);' size='5' onfocus='loadLotDetails("+new_idx+")'></td>"+
			    	"<td> <input type='text' id='id_kgCost' size='5' > <input type='hidden' id='id_kgRate' onchange='calculate("+new_idx+");' size='0'> <input type='hidden' id='id_kgExpenses' size='3'  onfocus='loadLotDetails("+new_idx+")'></td>"+
					"<td> <input type='text' disabled id='id_kgTax' size='7' onfocus='loadLotDetails("+new_idx+")'></td>"+
			    	"<td> <input type='text' disabled id='id_kgGST' size='5' onfocus='loadLotDetails("+new_idx+")'></td>"+
			    	"<td> <input type='text' disabled id='id_sbTax' size='5' onfocus='loadLotDetails("+new_idx+")'> </td>"+
			    	"<td> <input type='text' disabled id='id_sbExpenses' size='5' onfocus='loadLotDetails("+new_idx+")'></td>"+
			    	"<td> <input type='text' disabled id='id_sbGST' size='5' onfocus='loadLotDetails("+new_idx+")'></td>"+
					"<td> <input type='text' disabled id='id_totalValue' size='10' onfocus='loadLotDetails("+new_idx+")'><input type='hidden' id='id_sbTotal' size='10' ></td>"+
					"<td><a href='#' onclick='addNewRow("+new_idx+")' >Add</a></td>"+
			   		"</tr>";
			$("#table_lot").append(row_text);
			$("#select_lid"+new_idx).focus();
		}
		
		function setLotValue(cur,lot_id){
		    
		    var listvalue="";
		    if($('#list_name').find('option').filter(function(){
		    	if ($(this).attr("data-value") == lot_id){
		    		listvalue=this.value;
		    		return $(this).attr("data-value") == lot_id;
		    	}
		    }).length) {
	    		$("#select_lid"+cur).val(listvalue);
		    }
		}
		
		function lotChange(cur){
			
		    var val = $("#select_lid"+cur).val();
		    
		    var listvalue=-1;
		    $("#select_lid"+cur).attr("data-value","");
		    if($('#list_name').find('option').filter(function(){
		    	if (this.value.toUpperCase() === val.toUpperCase()){
		    		listvalue=$(this).attr("data-value");
		    		return this.value.toUpperCase() === val.toUpperCase();
		    	}
		    }).length) {
		    	if (listvalue!=-1){
		    		var match=false;
		    		$('[id^=tr_]').each(function (){
						var lotId= $(this).find('[id^=select_lid]').attr("data-value");
						var cur_id = $(this).attr("id");
						console.log(cur_id +"  " +lotId+ "  "+ listvalue);
						if (cur_id ==  "tr_"+cur){
							console.log("ignore id");
						}else{
							
							if (lotId==listvalue){
								match=true;
							}
						}
		    		});		    		
		    		if (!match){
		    			$("#select_lid"+cur).attr("data-value",listvalue);
			    		clearRow(cur);
		    		}else{
		    			 $("#select_lid"+cur).val("");
		    			 alert("This lot is already added in the list..");
		    		}
		    	}
		    }
		}
		
		function changeLotList(obj){
			clearTable();
			if (obj.value!=-1){
				loadLotData(obj.value);	
			}
		}
		</script>
</head>
<body>
	<div>
	
	<select name="select_lotList" id="id_selectList" onchange="changeLotList(this)">
	<option value="-1" selected="selected">Select</option>
		<c:forEach items='${lot_list}' var='lists' > 
			 <option value=<c:out value='${lists.id}'/> >  <c:out value='${lists.name}'/></option>
		</c:forEach>
	</select>
	<a href="#" id="btn_createList" class="button" >New List</a>
	<span style="font-size: 25pt;padding-left:100px;padding-right: 100px;"> Lot Rate </span>
	
	
	<a href="home"  class="button" >Home</a>
	</div>
	<div>
	
	<div style="width: 100%;float: top; height: 400px;overflow: scroll;">
	<table cellpadding="0" cellspacing="0" id="table_lot">
	<tr style='background-color: #D3D3D3;'>
		<td></td>
		<td> Lot Name </td>
		<td>preference</td>

		<td> Weight  </td>
		<td> Qty </td>
    	<td>Expenses(Per KG) </td>
    	<td>GST %</td>
    	<td> KG - INC GST</td>
    	<td> SB Praposed Rate</td>
    	<td> KG Excl GST</td>
    	
    	
    	<td> KG Tax </td>
    	
    	<td> KG GST</td>
    	
    	
    	
    	
    	<td> SB Tax</td>
    	<td> SB Expenses</td>
    	<td> SB GST</td>
    	<td> Lot Value</td>
    	<td> </td>
		</tr>
	

   	</table>
	</div>
		<input type="button" value="Save" onclick="saveForm();">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="#" onclick="exportData(this)">Export Data</a>
		
		
	<br>
	
		<div style="float:bottom;height: 200px;overflow: scroll;" id="bid_summary">
		</div>
	</div>
	
	<datalist id="list_name">
	<c:forEach items='${lot_name}' var='lists' > 
		 <option data-value=<c:out value='${lists.id}'/> >  <c:out value='${lists.lot_given_name}'/>-<c:out value='${lists.lot_name}'/> </option>
	</c:forEach>
	</datalist>
	
	<div id="myModal" class="modal">
		<div class="modal-content">
		<span class="close">&times;</span>
		List Name: <input type="text" name="listName" id="id_listName">
		<br>
		<a href="#" id="btn_create" class="button" >Create</a>
		</div>
	</div>
	
	<div id="lotDetails" class="modal">
		<div class="modal-content">
			<span class="close">&times;</span>
			<div id="lotData">
			
			</div>	
		</div>
	</div>
	
	
	<script type="text/javascript">
		var span = document.getElementsByClassName("close")[0];
		var modal = document.getElementById('myModal');
		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
		    modal.style.display = "none";
		}
		
		$(".close").click(function (div){
			$(event.target).closest(".modal").css({ display: "none" });
		});
	
		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
		    if (event.target == modal) {
		        modal.style.display = "none";
		    }
		}
		
		var btn_createList = document.getElementById("btn_createList");
		btn_createList.onclick = function() {
			modal.style.display = "block";
		}
		var btn_create = document.getElementById("btn_create");
		btn_create.onclick = function() {
			var name = $("#id_listName").val();
			if (name==""){
				alert("Error: List Name is required..");
				return;
			}else{
				
				$.ajax({
		            type: "POST",
		            url: "saveListName",
		            data:{
		            	listName : name
		            },
		            dataType: "json",
		            async: false,
		            success: function (data) {
		            	if (data==0){
		            		alert("List Name already Exists");
		            	}else{
		            		alert("List Name created Successfully");
		            	}
		            },
		            error: function (data) {
		                alert("error");
		            },
		        });
				
			}
		}
		
	</script>
	    
</body>

</html>