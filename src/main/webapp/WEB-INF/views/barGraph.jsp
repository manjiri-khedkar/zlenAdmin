<html>
<head>
<meta charset="ISO-8859-1" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />

</head>
<body>
	
		<h2 align="center">Data Graph</h2>

		<div id="container"  style="width: 550px; height: 400px; margin: 0 auto"></div>
	
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> 
	<!-- Latest compiled and minified JavaScript -->
	
	 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	 <script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script> 
	
   <script th:inline="javascript">
   
     //var $container = $('#container');
   
	     
	     
	$(function()
			{
		var name, date, listt, datecounter;
		$.ajax( {
		      type : "Get", 
		      url : "http://localhost:8080/user/bar-chart", 
		      contentType : "application/json", 
		      dataType : 'json', 
		      //data : data,
		         /*  data :  {
		          "firstName" : [[${Name}]],
		          "Date" : [[${Date}]],
		          "List" : [[${listt}]]
		      },     */
		      success : function (data) {
		    	  //name =  data.name;
		    	  //date = data.date;
		    	  listt = data.listt;
		    	  count = data.count;
		    	  datecounter = data.datecounter;
		    	  
		    	  console.log("success", data.name );
		    	  //console.log(data);
		    	  console.log("listt", listt);
		    	  //console.log("name", name);
		    	 // console.log("date", date);
		    	  console.log("datecounter", datecounter);
		   
		
    Highcharts.chart('container', {
        chart: {
            type: 'column'
        }, 
       /*  title: {
            text: 'Organization'
        }, */
        subtitle: {
            text: 'User-Details'
        },
        xAxis: {
            categories : listt, 
            //categories: ["akshay","rock","john","will","kiti"],
            crosshair: true
        },
        yAxis: {
            min: 0,
            max:100,
            title: {
                text: '<b>Users Count</b>'
                
            }       
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:1">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y}</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.3,
                borderWidth: 2
            }
        },
         series: [{
        	
            name: 'User Count',
           //data : [12,14,16,23,23,24]
           data : datecounter
         
        }] 
       
      });
		 },
	     /*   error : function() {
             console("not success");
         }  */
		   });
	     
			});
</script>
</body>
</html>

