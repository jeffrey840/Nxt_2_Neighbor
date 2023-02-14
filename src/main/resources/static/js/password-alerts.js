

/* this function will alert the new user if the passwords match*/
var passConfirm = function() {
	if (document.getElementById("password").value ===
		document.getElementById("confirm_password").value) {
		document.getElementById("Message").style.color = "Green";
		document.getElementById("Message").style.fontSize = "2vh";
		document.getElementById("Message").innerHTML = "Passwords match!"
	} else {
		document.getElementById("Message").style.color = "Red";
		document.getElementById("Message").style.fontSize = "2vh";
		document.getElementById("Message").innerHTML = "Passwords do NOT match!"
	}
}

alert("testing")
