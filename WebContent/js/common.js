/**
 * 
 */

//$objs : $("input[name]")
//count > 0 : return false
//count == 0 : return true

function checkInputEmpty( $objs ) {
	$(".error").css("display", "none");
	$(".notMatch").css("display", "none");
	
	var count = 0;
	
	$objs.each(function(i, obj) {
		if($(obj).val() == ""){
			$(obj).siblings(".error").eq(0).css("display", "inline");
			count++;
		}
	})
	
	if(count > 0){
		return false;
	}
	
	return true;
} 