const productsBtnB = document.querySelectorAll('.product__check');
const cartProductsList = document.querySelector('.backed-list');
const cartQuantity = document.querySelector('#quantity');
let price = 0;


const randomId = () => {
    return Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15);
};

const priceWithoutSpaces = (str) => {
    return str.replace(/\s/g, '');
};

const normalPrice = (str) => {
    return String(str).replace(/(\d)(?=(\d\d\d)+([^\d]|$))/g, '$1 ') + " ГРН";
};


const printQuantityb = () => {
    const prodStorage = localStorage.getItem("basket") || "[]";
    let productsListLength = JSON.parse(prodStorage);
    cartQuantity.textContent = productsListLength.length;
    document.querySelector(".cart__quantity").textContent=productsListLength.length;
    const cart = document.querySelector('.cart');
    cart.classList.remove('active');

    if (productsListLength.length > 0) {
        clearList();

        for (const i of productsListLength) {
            cartProductsList.insertAdjacentHTML('afterbegin', generateCartProduct(i.img, i.title, i.price, i.color, i.id, i.quantity));
        }
    } else {
        cartProductsList.insertAdjacentHTML("afterbegin", "<p><b>Ваш кошик порожній</b></p>");
    }
    sum();
};

const generateCartProduct = (img, title, price, color, id, quantitys) => {
    return `
		<li class="cart-content__item">
			<article class="cart-content__product cart-product" data-id="${id}">
				<img src="${img}" alt="" class="cart-product__img">
				<div class="cart-product__text">
					<h3 class="cart-product__title">${title}</h3>
					<span class="cart-product__price">${price}</span>
				</div>
				<h3 class="cart-product__title">${color}</h3>
				<div>
				<p class="cart__quantitys">${quantitys}</p>
				<button onclick="minus(event)" class="cart-product__minus" >-</button>

				<button onclick="plus(event)" class="cart-product__pluse" >+</button>
				
				<button class="cart-product__delete" aria-label="Удалить товар"></button>
				
				    
			</div>  
			</article>
		</li>
	`;
};

let deleteProducts = (id) => {
    let prods = JSON.parse(localStorage.getItem("basket"));
    prods.forEach(function (item, index) {
        if (item.id === id) {
            prods.splice(index, 1)
            localStorage.setItem("basket", JSON.stringify(prods));
            clearList();
        }
    });
    printQuantityb();
};


cartProductsList.addEventListener('click', (e) => {
    if (e.target.classList.contains('cart-product__delete')) {
        deleteProducts(e.target.closest('.cart-content__item').querySelector('.cart-product').dataset.id);
    }
});

function minus(e) {
    let parent = e.currentTarget.closest(".cart-product");
    let id = parent.dataset.id;
    let prodStorage = JSON.parse(localStorage.getItem("basket") || "[]");

    prodStorage.forEach((part, index, arr) => {
        if (part.id === id) {
            if (part.quantity > 1) {
                --arr[index].quantity;
                localStorage.setItem("basket", JSON.stringify(prodStorage));
            }
            // else deleteProducts(id);

            clearList();
        }
    });

    printQuantityb();
}

function plus(e) {
    let parent = e.currentTarget.closest(".cart-product");
    let id = parent.dataset.id;
    let prodStorage = JSON.parse(localStorage.getItem("basket") || "[]");

    prodStorage.forEach((part, index, arr) => {
        if (part.id === id) {
            ++arr[index].quantity;
            clearList();
        }
    });

    localStorage.setItem("basket", JSON.stringify(prodStorage));

    printQuantityb();
}

printQuantityb();

function clearList() {
    for (const child of cartProductsList.children) {
        child.remove();
    }
}

function sum() {
    let sum = 0;
    if(cartProductsList.querySelector(".cart__quantitys")!=null){

    for (let child of cartProductsList.children) {
        let q = parseFloat(child.querySelector(".cart__quantitys").textContent);
        let c = parseFloat(child.querySelector(".cart-product__price").textContent);

        sum += q * c;
    }
    }
    document.querySelector(".total_cost").textContent =parseFloat(sum).toFixed(2);
}

function sendBasket(){
    var url = "http://localhost:8080/order/post";
let local=JSON.parse(localStorage.getItem("basket"));

    for (let i of local) {
        $.ajax({
            url: url,
            method: "post",
            // contentType: 'application/json',
            data:{id:i.id, quantity:i.quantity, size:i.size},
            error: function(message) {
                console.log(message);
            },
            success: function(data) {
                console.log(data);
                localStorage.clear();
                window.location.href="/order";
            }
        });
    }

}