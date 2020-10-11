function validateRegister(){
	 	var pass=document.getElementById("pass").value;
	   	var cpass=document.getElementById("cpass").value;
	 
	   	if(pass==cpass){
		   document.getElementById("errpass").innerHTML="";
		   return true;
	  	 }
	  	 else{
		  	 document.getElementById("errpass").innerHTML="Password and Confirm Password does not match";
		   	alert("Password and confirm password dont match ");
		   	return false;
	   }
}

