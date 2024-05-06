function foo(e) {
    var d = document.getElementById("big-photo");
    var src = e.target.src;
    e.target.src = d.src
    d.src = src;

}

// function chek(id) {
//
//     let b = document.getElementById("basket_count");
//     alert(id);
//     b.textContent = 1 + parseInt(b.textContent);
//
//     $("#basket").load("/basket");
//     // $.ajax({
//     //     type: "GET",
//     //     url: "http://localhost:8080//basket"
//     // }).done(function () {
//     //         window.location.href = "/";
//     //     })
//     //     .fail(function () {
//     //         alert("Logout error!");
//     //     })
// }

