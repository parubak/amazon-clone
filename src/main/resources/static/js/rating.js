"use strict"

const ratings = document.querySelectorAll(".rating");
if (ratings.length > 0) {
    initRatings();
}

function initRatings() {
    let ratingActive, ratingValue;

    for (let i = 0; i < ratings.length; i++) {
        const r = ratings[i];
        initRating(r);
    }

    function initRating(rating) {
        initRatingVars(rating);
        setRatingActiveWidth();
    }

    function initRatingVars(rating){
        ratingActive=rating.querySelector(".rating__active");
        // ratingValue=rating.querySelector(".rating__value");
    }

    function setRatingActiveWidth(i=0){
        const rActiveWidth=i / 0.05;
        ratingActive.style.width=rActiveWidth+'%';
    }

}
function btn(){
    let ii=document.querySelectorAll(".simple-rating__item:checked");

    let sum=0.0;
    for (let e of ii){
        if (e.checked){
            sum+=parseFloat(e.value);
        }
    }

    let eff=document.querySelector(".rating__active");

    console.log(sum);
        eff.style.width=((sum/3.0)/ 0.05)+"%";

}
let stars=document.querySelectorAll(".simple-rating__item");
for (let star of stars) {
    star.addEventListener("click", btn);
}

formResponse.onsubmit = (e) => {
    e.preventDefault();
    let rating=formResponse.querySelector(".rating__active").style.width;
    const url = "http://localhost:8080/responseAdd/";

    var fd = new FormData();
    fd.append( 'orderId', formResponse.dataset.id);
    if (formResponse.file.files.length>0){
        fd.append( 'file', formResponse.file.files[0].name);
    }

    fd.append( 'comment', formResponse.comment.value);
    fd.append( 'riting', 0.05*parseInt(rating));

    console.log(fd);

    $.ajax({
        url: url,
        data: fd,
        processData: false,
        contentType: false,
        type: 'POST',
        success: function(data){

            document.location.href="/response-ok"
            alert(data);
        }
    });

};