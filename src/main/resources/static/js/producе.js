function foo(e) {
    var d = document.getElementById("big-photo");
    var src = e.target.src;
    e.target.src = d.src
    d.src = src;

}

var get = "";
var getAll = "";
var sort = "";
var categorys = document.querySelectorAll("input[name=category]")
var sortList = document.querySelectorAll("div.sort__body")

categorys.forEach((el) => el.addEventListener("click", evt => {
    getAll = "";
    let subcategorys = evt.target.parentElement.querySelectorAll(".subcategory");

    for (let sub of subcategorys) {

        getAll += "&category=" + sub.dataset.v;
        sub.checked = false;
        get = "";
        sub.addEventListener("click", e => {
            if (e.target.checked) {
                get = get + "&category=" + e.target.dataset.v;
            } else {
                get = get.replace("&category=" + e.target.dataset.v, '')
            }
            loadPage()
        });
    }
    loadPage();

}))

for (let sortI of sortList) {
    sortI.addEventListener("click", evt => {
        sort=evt.target.dataset.sort
        loadPageSort(sort);
    })

}

function loadPage(){
    if (get === "") {
        $("#productAj").load("/deals/aj/?"+getAll);
    } else {
        $("#productAj").load("/deals/aj/?"+get);
    }
}
function loadPageSort(sort){
    console.log(sort);
    if (get === "") {
        $("#productAj").load("/deals/aj/?"+getAll+"&sort="+sort);
    } else {
        $("#productAj").load("/deals/aj/?"+get+"&sort="+sort);
    }

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

