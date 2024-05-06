window.onload = redirectHome;

function redirectDeals() {
    $("#ajax-container").load("/deals");

}
function redirectPromo() {
    $("#ajax-container").load("/promo");

}

function redirectDealsItem(category, item ) {
    $("#product22").load("/product/getAJ/"+category+"/?item="+item);
}
