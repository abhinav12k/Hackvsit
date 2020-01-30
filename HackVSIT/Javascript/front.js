var nav = document.getElementById('tpp');
var hd = document.getElementById('headd');
var navbar = document.getElementById('nb');
var text = document.getElementById('text');
window.onscroll = function(){
    if (window.pageYOffset > 250){
        nav.style.background = "#060c21";
        nav.style.opacity = 1;
        check.style.background= "transperant";
        check.style.opacity = 0;
        hd.style.height = '500px';
        navbar.style.opacity= 1;
        text.style.display = 'block';

    }
    else {
        nav.style.background = "transperant";
        nav.style.opacity = 0;
        check.style.background= "transperant";
        check.style.opacity = 0;
        hd.style.background = "#060c21";
        navbar.style.opacity = 0;
        text.style.display = 'none';
        navbar.style.opacity = 0;
    }
}

