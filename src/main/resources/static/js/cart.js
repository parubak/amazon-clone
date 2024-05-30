{
    const cartProductsList = document.querySelector('.cart-content__list');
    const cart = document.querySelector('.cart');
    const cartQuantity = cart.querySelector('.cart__quantity');



    const printQuantity = () => {
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


    const generateCartProduct = (img, title, price, color, id) => {
        return `
		<li class="cart-content__item">
			<article class="cart-content__product cart-product" data-id="${id}">
				<button class="cart-product__delete" aria-label="Удалить товар"></button>
				<img src="${img}" alt="" class="cart-product__img">
				<div class="cart-product__text">
					<h3 class="cart-product__title">${title}</h3>
					<span class="cart-product__price" >${price}</span>
				</div>
				<h3 class="cart-product__title">${color}</h3>
			</article>
		</li>
	`;
    };

    const deleteProducts = (productParent) => {
        let id = productParent.querySelector('.cart-product').dataset.id;

        const prods = JSON.parse(localStorage.getItem("basket") || "[]");
        prods.forEach(function (item, index) {
            if (item.id === id) {
                prods.splice(index, 1);
                localStorage.setItem("basket", JSON.stringify(prods));
                for (const child of cartProductsList.children) {
                    child.remove();
                }
            }
        });
        printQuantity();
    };


    function productBtn() {
        const prodStorage = localStorage.getItem("basket") || "[]";

        let parent = document.querySelector('.product');
        console.log(parent)
        let id = parent.dataset.id;
        let shop = parent.dataset.shop;
        let img = parent.querySelector('.product__big-photo').getAttribute('src');
        let title = parent.querySelector('.product__name').textContent;
        let price = parseFloat(parent.querySelector('.product__prise').textContent);
        let color = parent.querySelector('.product__color').textContent;
        let size = parent.querySelector(".active-size");


        if (size !== null) {
            size = size.textContent;
        } else {
            alert("size not fount");
        }

        const prods = JSON.parse(prodStorage);


        const prod = {id, title, img, color, price, shop, size, quantity: 1};
        let flaf = true;
        for (const i of prods) {
            if (i.id === prod.id) {
                flaf = false;
            }
        }
        if (flaf) {
            localStorage.setItem("basket", JSON.stringify([...prods, prod]));
            printQuantity();
        }

        // self.disabled = true;
    }


    cartProductsList.addEventListener('click', (e) => {
        if (e.target.classList.contains('cart-product__delete')) {
            deleteProducts(e.target.closest('.cart-content__item'));
        }
    });

    printQuantity();

    function onClickSize(event) {
        const sizeList = document.querySelector(".product__size-list");

        for (let child1 of sizeList.children) {
            child1.classList.remove("active-size");
        }
        event.target.classList.add("active-size");
    }
}
