// ============================================
// SHOPAI PRODUCT JAVASCRIPT
// ============================================


// Load products when products.html opens

document.addEventListener("DOMContentLoaded", function () {

    if (document.getElementById("productContainer")) {

        loadProducts();

    }

});


// ============================================
// LOAD ALL PRODUCTS
// ============================================

function loadProducts() {

    const container =
        document.getElementById("productContainer");

    container.innerHTML =
        "<p>Loading products...</p>";


    fetch("products")

        .then(function (response) {

            if (!response.ok) {

                throw new Error(
                    "Server error: " + response.status
                );

            }

            return response.json();

        })

        .then(function (products) {

            displayProducts(products);

        })

        .catch(function (error) {

            console.error(error);

            container.innerHTML =
                `
                <div class="error-box">
                    <h3>Unable to load products</h3>

                    <p>
                        Please check the Tomcat console
                        and database connection.
                    </p>
                </div>
                `;

        });

}


// ============================================
// DISPLAY PRODUCTS
// ============================================

function displayProducts(products) {

    const container =
        document.getElementById("productContainer");


    if (!products || products.length === 0) {

        container.innerHTML =
            `
            <div class="no-products">

                <h2>
                    No products found
                </h2>

                <p>
                    Try another search.
                </p>

            </div>
            `;

        return;

    }


    container.innerHTML = "";


    products.forEach(function (product) {

        const card =
            document.createElement("div");

        card.className = "product-card";


        card.innerHTML =
            `
            <div class="product-image-container">

                <img
                    src="${product.image}"
                    alt="${product.name}"
                    class="product-image"
                    onerror="this.src='https://via.placeholder.com/300x250?text=ShopAI+Product'">

            </div>


            <div class="product-details">

                <div class="product-category">

                    ${product.category}

                </div>


                <h2 class="product-name">

                    ${product.name}

                </h2>


                <p class="product-description">

                    ${product.description}

                </p>


                <div class="product-price">

                    ₹${Number(product.price).toLocaleString('en-IN')}

                </div>


                <button
                    class="add-cart-btn"
                    onclick="addToCart(${product.id})">

                    Add to Cart

                </button>

            </div>
            `;


        container.appendChild(card);

    });

}


// ============================================
// SEARCH PRODUCTS
// ============================================

function searchProducts() {

    const input =
        document.getElementById("searchInput");

    const keyword =
        input.value.trim();


    if (keyword === "") {

        loadProducts();

        return;

    }


    const container =
        document.getElementById("productContainer");


    container.innerHTML =
        "<p>Searching...</p>";


    fetch(
        "search?keyword=" +
        encodeURIComponent(keyword)
    )

        .then(function (response) {

            if (!response.ok) {

                throw new Error(
                    "Search failed"
                );

            }

            return response.json();

        })

        .then(function (products) {

            displayProducts(products);

        })

        .catch(function (error) {

            console.error(error);

            container.innerHTML =
                `
                <div class="error-box">

                    <h3>
                        Search failed
                    </h3>

                    <p>
                        Please try again.
                    </p>

                </div>
                `;

        });

}


// ============================================
// ADD PRODUCT TO CART
// ============================================

function addToCart(productId) {

    const formData =
        new URLSearchParams();


    formData.append(
        "productId",
        productId
    );


    formData.append(
        "quantity",
        "1"
    );


    fetch("cart", {

        method: "POST",

        headers: {

            "Content-Type":
                "application/x-www-form-urlencoded"

        },

        body: formData.toString()

    })

        .then(function (response) {

            return response.json();

        })

        .then(function (result) {

            if (result.success) {

                alert(
                    "Product added to cart successfully!"
                );

            } else {

                alert(result.message);

                if (result.loginRequired) {

                    window.location.href =
                        "login.html";

                }

            }

        })

        .catch(function (error) {

            console.error(error);

            alert(
                "Unable to add product to cart."
            );

        });

}