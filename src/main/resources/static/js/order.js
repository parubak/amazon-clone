getOrder(1);

function getOrder(order) {
    $("#ajax-container").load("/order/getAJ/" + order + "/");
    let e = document.querySelector(".nav-order__list");

    for (let i of e.children) {
        i.classList.remove('nav-order--active');
    }

    e.children[order-1].classList.add('nav-order--active');

}


const generateCartProduct = (img, title, price, color, id) => {
    return `
		<li class="cart-content__item">
			<article class="cart-content__product cart-product" data-id="${id}">
				<button class="cart-product__delete" aria-label="Удалить товар"></button>
				<img src="${img}" alt="" class="cart-product__img">
				<div class="cart-product__text">
					<h3 class="cart-product__title">${title}</h3>
					<span class="cart-product__price">${parseFloat(price).toFixed(2)}</span>
				</div>
				<h3 class="cart-product__title">${color}</h3>
			</article>
		</li>
	`;
};

const printQuantity = () => {
    const cart = document.querySelector('.cart');
    const cartQuantity = cart.querySelector('.cart__quantity');
    const cartProductsList = document.querySelector('.cart-content__list');
    const prodStorage = localStorage.getItem("basket") || "[]";
    let productsListLength = JSON.parse(prodStorage);

    cartQuantity.textContent = productsListLength.length;
    if (productsListLength.length > 0) {
        cart.classList.add('active');

        for (const child of cartProductsList.children) {
            child.remove();
        }
        for (const i of productsListLength) {
            cartProductsList.insertAdjacentHTML('afterbegin', generateCartProduct(i.img, i.title, i.price, i.color, i.id));
        }
    } else {
        cart.classList.remove('active');
    }
};

function buyBtn(id) {

    const url = "http://localhost:8080/product/getItem/" + id + "/";
    $.ajax({
        url: url,
        method: "post",
        // contentType: 'application/json',
        // data:{id:i.id, quantity:i.quantity},
        error: function (message) {
            console.log(message);
        },
        success: function (data) {
            const prodStorage = localStorage.getItem("basket") || "[]";
            let prods = JSON.parse(prodStorage);
            let newP = JSON.parse(data);
            let flag = true;

            for (let prod of prods) {
                if (prod.id === newP.id) {
                    flag = false;
                }
            }

            if (flag) {
                localStorage.setItem("basket", JSON.stringify([...prods, newP]));
                printQuantity();
            }
        }
    });
}

