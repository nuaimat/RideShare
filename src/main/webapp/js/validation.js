/**
 * 
 */

window.onload = function() { 
	
	var registerbtn= document.getElementById("regbtn"); 
	registerbtn.onclick = function() {
		if(document.getElementById("input1").value==""){
			alert("Empty Name");
			document.getElementById("myForm").reset();document.getElementById("myForm").reset();
			
		}
		else if(document.getElementById("input2").value==""){
			alert("Empty password");
			document.getElementById("myForm").reset();
					}
		else if(document.getElementById("input5").value==""){
			alert("Empty Email");
			document.getElementById("myForm").reset();
		}
		else if(document.getElementById("input3").value==""){
			alert("Empty City");
			document.getElementById("myForm").reset();
		}
		else if(document.getElementById("input4").value==""){
			alert("Empty Street");
			document.getElementById("myForm").reset();
		}
		else if(document.getElementById("input8").value==""){
			alert("Empty Street");
			document.getElementById("myForm").reset();
		}		else{
			
		}
	};
	
};

