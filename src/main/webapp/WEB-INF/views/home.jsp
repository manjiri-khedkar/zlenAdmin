<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="mp.procurement.model.Tender" %>
<html>
<head>

<style type="text/css">
	.button {
	    background-color: #b1cdf9; /* Green */
	    border: none;
	    color: white;
	    padding: 20px 40px;
	    margin: 30px 30px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 16px;
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
    margin: auto auto; /* 15% from the top and centered */
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
	<script type="text/javascript">
	function calculateLot(){
		var count=0;
		$('input:checked[name=chk]').each(function(){
			count += parseInt($(this).attr("cnt"));
		});
		
		$("#txt_lotCount").val(count);
	}
	function startBrowser(typeId){
		$.ajax({
            type: "GET",
            url: "startBrowser",
            data: {type : typeId},
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: true,
            success: function (data) {
            	
            },
            error: function (data) {
                
            },
        });
	}
	
	function result(){
		$.ajax({
            type: "GET",
            url: "result",
            data: "",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: true,
            success: function (data) {
            	
            },
            error: function (data) {
                
            },
        });
	}
	
	function tenderResult(){
		var id= $("#select_tid_result").val();
		var link = document.createElement("a");
	    link.download = name;
	    link.href = "tenderResult?tenderId="+id;
	    link.click();
	}
	
	
	function resultProces(){
		var id= $("#select_tid_result").val();
		var minRate= $("#minRate").val();
		var orderBy= $("#orderBy").val();
		var capping= $("#capping").val();
		
		
		$.ajax({
            type: "GET",
            url: "result",
            data: {tenderId: id,minRate: minRate, orderBy: orderBy,capingRate: capping },
            dataType: "json",
            async: true,
            success: function (data) {
            	alert("Result Process has been started and will take around 10 min.");
            },
            error: function (data) {
                
            },
        });
	}
	
	function datacollection(partyData){
		var url = "dataCollection";
		if (partyData){
			url+="?partyData=true";
		}
		$.ajax({
            type: "GET",
            url: "dataCollection",
            data: "",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: true,
            success: function (data) {
            	alert("Data collection process has been started... please wait it will take time to collect data.");
            },
            error: function (data) {
                
            },
        });
	}
	
	function fillForm(){
		$.ajax({
            type: "GET",
            url: "fillForm",
            data: "",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: true,
            success: function (data) {
            	alert("Data collection process has been started... please wait it will take time to collect data.");
            },
            error: function (data) {
                
            },
        });
	}
	
	function showTenderBids(){
		var tid = $("#id_resultUpdateForm").find("#select_year").val();
		var stateId = $("#id_resultUpdateForm").find("#select_state").val();
		$("#div_tenderBids").html("");
		$.ajax({
            type: "GET",
            url: "tenderYearBids",
            data: {year: tid,
            	state: stateId
            },
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: false,
            success: function (data) {
            	var html = "<table cellpading=20>";
            	var cur_lot=-1;
            	$.each(data,function(idx,val){
            		if (cur_lot!=val.lot_id){
            			cur_lot=val.lot_id;
            			html += " <tr style='background-color:lightgreen' ><td colspan=2>Lot No: " + val.lot_name + "</td><td colspan=2>Lot No: " + val.lot_given_name + "</td></tr>";
            		}
            		var checked=val.is_selected?"checked":"";
            		html+= "<tr> <td> <input  style='height:25px; width:25px; vertical-align: middle;' type=radio name='"+val.lot_id+"' value='"+val.id+"' "+ checked+" > </td><td> "  + val.round + "</td> <td> "  + val.party_name + "</td> <td>" + val.rate +"</td><td>" + val.rank +"</td></tr>" ;  
            	});
            	html += "</table>";
            	$("#div_tenderBids").html(html);
            	//console.log(data);
            },
            error: function (data) {
                alert("There is some error!");
            },
        });
	}

	
	</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery.min.js"></script>
		<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
</head>
<body>
<div style="text-align: center;">
	<h2>MP Procurement Tender Data System</h2>
</div>
<div style="width: 100%;height: 100%;text-align: center; vertical-align: middle;"  >
	<a href="#" id="url_MPDataCollection" class="button">MP Data Collection</a>
	
	
	<a href="#" id="url_CGDataCollection" class="button">CG Data Collection</a>
	<br>
	<a href="#" id="url_result" class="button" >Result Processing</a>
	<a href="#" id="url_tenderResult" class="button" >Tender Result</a>
	<br>
	<a href="#" class="button" id="btn_partywiseReport">Party Wise Report (MP) </a>
	<a href="partyReport?stateNo=2" class="button" >Party Wise Report (CG) </a>
	<br>
	<a href="#" class="button"  id="btn_lotwiseReport">Lot Wise Report</a>
	<a href="lotRate" class="button"  id="btn_lotRate">Lot Rate</a>
	<a href="#" class="button"  id="btn_resultUpdate">Result update</a>
	
	<a href="yearWiseLotRate?state=1" class="button"  id="btn_resultUpdate">MP Year Wise Lot Rate</a>
	
	 
</div>
<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>
    <p>MP Federation web wite will get open in new IE browser. Login with user id password and digital signature. Once login open the Tender page and click on the Start Data collection button. </p>
    <p> Please note it will take hours to collect all the data of tender. Time duration will depend on the number of bids.</p>
    <div style="text-align: center; width: 100%">
		<a href="#" onclick="datacollection();" class="button">Data collection</a>
		<a href="#" onclick="fillForm();" class="button">Form Fill Up </a>
		<a href="#" onclick="datacollection(true);" class="button">Party Details </a>
	</div>    
  </div>

</div>

<div id="partyReportModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>
    
    <form action="partyReport" method="POST"  id="id_partyForm">
	    <table>
	     <tr>
		 	<td colspan=3> Tender </td>
		 </tr>
	    <tr>
    	<c:forEach items="${tenderList}" var="lists" varStatus="count">
    		<td>
    			<input type="checkbox" name="chk_tender" value=${lists.id}  ><c:out value="${lists.tender_name}"/>
    		</td>
    		
    		<c:if test="${(count.index % 3) eq 0}">
    			</tr>
    			<tr>
    		</c:if>
		</c:forEach>
		</tr>
		</table>
		
		 <table>
		 <tr>
		 	<td colspan=3> Party Name:</td>
		 </tr>
	    <tr>
    	<c:forEach items="${partyList}" var="lists" varStatus="count">
    		<td>
    			<input type="checkbox" name="chk_party" value=${lists.id}  ><c:out value="${lists.party_name}"/>
    		</td>
    		
    		<c:if test="${(count.index % 3) eq 0}">
    			</tr>
    			<tr>
    		</c:if>
		</c:forEach>
		</tr>
		</table>
		
   	<div style="text-align: center; width: 100%">
		<input type="submit" value="submit">
	</div>    
	</form>
  </div>

</div>

<div id="resultUpdateModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>
    
    <form action="resultUpdate" method="POST"  id="id_resultUpdateForm">
	    Year: <select name="tid" id="select_year"> 
			    	<c:forEach items="${yearList}" var="lists">
			    		<option value=<c:out value="${lists.year}"></c:out>><c:out value="${lists.year}"></c:out> </option>
					</c:forEach>
    			</select>
    	 State: <select name="tid" id="select_state"> 
			    		<option value=1>MP </option>
			    		<option value=2>CG </option>
    			</select>
		<a href="#" onclick="showTenderBids();" id="btn_showTenderBids" class="button" >Show</a> 
		
		<div id="div_tenderBids">
		
		</div>
	   	<div style="text-align: center; width: 100%">
			<input type="submit" value="submit">
		</div>    
	</form>
  </div>

</div>

<div id="lotReportModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>
    
    <form action="lotReport" method="POST"  id="id_lotForm">
    Total Lot Count: <input type="text" name="lotCount" id="txt_lotCount"/>
    <br>
    Email id: <input type="text" name="emailid" id="txt_emailId"/>
	    &nbsp;&nbsp;&nbsp;Division List:&nbsp;&nbsp;&nbsp;
	    <table>
	    <tr>
    	<c:forEach items="${divisionList}" var="lists" varStatus="count">
    		<td>
    		<input type="checkbox" name="chk" value="${lists.division}" cnt="${lists.count}" onclick="calculateLot()"><c:out value="${lists.division}"/>(<c:out value="${lists.count}"/>)
    		
    		</td>
    		
    		<c:if test="${(count.index % 3) eq 0}">
    			</tr>
    			<tr>
    		</c:if>
		</c:forEach>
		</tr>
		</table>
		
   	<div style="text-align: center; width: 100%">
		<input type="submit" value="submit">
	</div>    
	</form>
  </div>

</div>
<div id="resultModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>
    &nbsp;&nbsp;&nbsp;Tender No:&nbsp;&nbsp;&nbsp;
    <select name="tid" id="select_tid_result"> 
    	<c:forEach items="${tenderList}" var="lists">
    		<option value=<c:out value="${lists.id}"></c:out>><c:out value="${lists.tender_name}"></c:out> </option>
		</c:forEach>
    </select>
    <br>
    &nbsp;&nbsp;&nbsp;Min Rate:&nbsp;&nbsp;&nbsp;
    <input id="minRate" type="text" value="0">
    <br>
    &nbsp;&nbsp;&nbsp;Order By:&nbsp;&nbsp;&nbsp;
    <select name="orderBy" id="orderBy">
    	<option value="rate">rate </option>
    	<option value="priority">priority </option>
    </select>
    
    <br>
    &nbsp;&nbsp;&nbsp;Lot Capping Percentage :&nbsp;&nbsp;&nbsp;
    <input id="capping" type="text" value="100">
    
    <div style="text-align: center; width: 100%">
		<a href="#" onclick="resultProces();" id="btn_resultProcess" class="button" >Process</a>
		<a href="#" onclick="tenderResult();" id="btn_showResult" class="button" >Show Result</a>
	</div>    
  </div>
  

</div>
<div style="width: 100%; height: 20px;background-color: #b1cdf9; bottom: 0; position: fixed; margin:0px; padding-right:20px; padding:2px; font-size: 11pt; text-align: right; vertical-align:  middle;">
		InfoSane, Nagpur(MH)- infosane.nagpur@gmail.com, 8989000400
</div>
<script type="text/javascript">
	var modal = document.getElementById('myModal');
	var modal_result = document.getElementById('resultModal');
	var modal_lotReport = document.getElementById('lotReportModal');
	var modal_partyReport = document.getElementById('partyReportModal');

	
	//Get the button that opens the modal
	var btn_mp = document.getElementById("url_MPDataCollection");
	var btn_cg = document.getElementById("url_CGDataCollection");
	btn_mp.onclick = function() {
    	modal.style.display = "block";
    	startBrowser(1);
	}
	
	btn_cg.onclick = function() {
    	modal.style.display = "block";
    	startBrowser(2);
	}
	
	var btn_result = document.getElementById("url_result");
	btn_result.onclick = function() {
    	modal_result.style.display = "block";
    	$("#btn_showResult").hide();
    	$("#minRate").show();
    	$("#btn_resultProcess").show();
	}
	
	var btn_tenderResult = document.getElementById("url_tenderResult");
	btn_tenderResult.onclick = function() {
    	modal_result.style.display = "block";
    	$("#btn_resultProcess").hide();
    	$("#minRate").hide();
    	$("#btn_showResult").show();
	}
	
	$(".close").click(function (div){
		$(event.target).closest(".modal").css({ display: "none" });
	});
	
	$("#btn_resultUpdate").click(function (){
		$('#resultUpdateModal').attr("style", "display: block");
	});
	
	var btn_lotReport = document.getElementById("btn_lotwiseReport");
	btn_lotReport.onclick = function() {
		modal_lotReport.style.display = "block";
	}
	var btn_partyReport = document.getElementById("btn_partywiseReport");
	btn_partyReport.onclick = function() {
		modal_partyReport.style.display = "block";
	}
	
	var span = document.getElementsByClassName("close")[0];
	var span1 = document.getElementsByClassName("close")[1];
	
	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
	    modal.style.display = "none";
	}
	
	span1.onclick = function() {
	    modal_result.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	}
	var frm4 = $('#id_lotForm');
	   frm4.submit(function (e) {
	        e.preventDefault();
	        //validatePurform();
	        
	        var arr=[];
			
    		$('input:checked[name=chk]').each(function(){
        		arr.push($(this).val());
    		});

    		var emailId= $("#txt_emailId").val();
    		
    		var ids=arr.join(':');
	        var json = {
	        		chk: ids,
	        		emailId:emailId
	        };
	        
			var link = document.createElement("a");
		    link.download = name;
		    link.href = "lotReport?chk="+ids +"&emailId="+emailId;
		    link.click();

	        return false;
	    });
	   var frm5 = $('#id_partyForm');
	   frm5.submit(function (e) {
	        e.preventDefault();
	        //validatePurform();
	        
	        var arr_party=[];
	        var arr_tender=[];
			
    		$('input:checked[name=chk_party]').each(function(){
    			arr_party.push(parseInt($(this).val()));
    		});
    		$('input:checked[name=chk_tender]').each(function(){
    			arr_tender.push(parseInt($(this).val()));
    		});

    		
    		//var ids_party=arr_party.join(',');
    		//var ids_tender=arr_tender.join(':');
	        var json = {
	        		party_id: arr_party,
	        		tender_id: arr_tender
	        };
	        var str_json = JSON.stringify(json);
			var link = document.createElement("a");
		    link.download = name;
		    link.href = "partyReport?stateNo=1&json="+encodeURIComponent(str_json) ;
		    link.click();

	        return false;
	    });
	   $('#id_resultUpdateForm').submit(function(e){
		   e.preventDefault();
	        
	        var arr_ids=[];
	        
	        var tid = $("#id_resultUpdateForm").find("#select_year").val();
			var stateId = $("#id_resultUpdateForm").find("#select_state").val();
			
	        $("#div_tenderBids").find("input[type=radio]:checked").each(function(){
	        	arr_ids.push(parseInt($(this).val()));
   			});
   	
   		
	        $.ajax({
	            type: "POST",
	            url: "updateTenderResult",
	            data:JSON.stringify( {
	            	bid_ids: arr_ids,
	            	year:tid,
	            	state: stateId
	            }),
	            contentType: "application/json; charset=utf-8",
	            dataType: "json",
	            async: false,
	            success: function (data) {
	            	alert("result updated successfully..");
	            },
	            error: function (data) {
	                
	            },
	        });

	        return true;
	   });
</script>
</body>
</html>

