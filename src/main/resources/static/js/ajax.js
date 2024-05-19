window.onload = redirectHome;

function redirectDeals() {
    $("#ajax-container").load("/deals");

}
function redirectPromo() {
    $("#ajax-container").load("/promo");

}

function redirectDealsItem(category, item ) {
    $("#product22").load("/product/getAJ/"+category+"/?item="+item).ajaxComplete(hj());


}

function hj(){
    const sizeList = document.querySelector(".product__size-list");

    for (let child of sizeList.children) {
        child.addEventListener('click', (e) => {

            for (let child1 of sizeList.children) {
                child1.classList.remove("active-size");
            }
            e.target.classList.add("active-size");
        });
    }
}
