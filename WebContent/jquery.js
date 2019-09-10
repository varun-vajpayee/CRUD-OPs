var postData = "text";
$('#myForm').ready(function() {
	$('#insert').submit(function() {
		$.ajax({
			type : "post",
			url : "LoginServlet",
			data : postData,
			contentType : "application/x-www-form-urlencoded",
			success : function(responseData, textStatus, jqXHR) {
				alert("data saved")
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
			}
		});
	});
});
