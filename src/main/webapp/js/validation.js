function validateForm() {
    // Retrieve form field values
    var name = document.getElementById("fn").value.trim();
    var username = document.getElementById("un").value.trim();
    var email = document.getElementById("em").value.trim();
    var phone = document.getElementById("phn").value.trim();
    var password = document.getElementById("fpass").value.trim();
    var cpassword = document.getElementById("confpass").value.trim();
    var validationMessages = document.getElementById("validationMessages");
    
       
    // Clear previous validation messages
    
    validationMessages.classList.add("alert-danger");
    validationMessages.innerHTML = "";
    

    // Validate  name
    if (name == "") {
        validationMessages.innerHTML += "<strong>Alert!</strong> Please enter your Full Name.<br>";
    }

    // Validate user  name
    if (username == "") {
        validationMessages.innerHTML += "<strong>Alert!</strong> Please enter your UserName.<br>";
    }

    // Validate email address
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (email == "" || !emailRegex.test(email)) {
        validationMessages.innerHTML += "<strong>Alert!</strong> Please enter a valid email address, such as name@example.com.<br>";
    }
    
    // Confirm password
    if (phone == "" || phone < 1) {
        validationMessages.innerHTML += "<strong>Alert!</strong> Please enter a valid phone number.<br> ";
    }

    // Validate password
    if ( password == "" || password.length < 8) {
        validationMessages.innerHTML += "<strong>Alert!</strong> Please enter a password with at least 8 characters.<br>";
    }

  
    // Check if any validation messages were added
    if (validationMessages.innerHTML != "") {
     return false;  
    }

    // Form is valid, submit it
    
         return true;
          }