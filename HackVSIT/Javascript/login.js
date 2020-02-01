const signupForm = document.querySelector('.form-signin');
signupForm.addEventListener("submit" , (e)=>{
   e.preventDefault();

   const email = signupForm['inputEmail'].value;
   const password = signupForm['inputPassword'].value;
   au.createUserWithEmailAndPassword(email, password).then(cred => {
        console.log(cred);
   });  
});

