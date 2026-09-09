


// Cart Functionality count and add to cart button

let cartCount = 0;

const cartCounter = document.querySelector(".cart-count");

const addButtons = document.querySelectorAll(".add-cart");


addButtons.forEach(function(button){
    button.addEventListener("click",function(){
        cartCount++;
        cartCounter.textContent = cartCount;

        const productCard = this.closest(".product-card");
        const productName = productCard.querySelector("h3").textContent;
        alert(productName +" added to cart!");
    });
});



// search functionality

const foods = [
    { name:"Chiken Pizza", price: 399},
    { name:"Chiken Burger", price: 199},
    { name:"Chiken Pasta", price: 249},
    { name:"Sprite", price: 20}  
];

const searchBtn = document.getElementById("searchBtn");
const searchInput = document.getElementById("searchInput");
const searchResult = document.getElementById("searchResults");

//open search
searchBtn.addEventListener("click", function(){
    searchInput.classList.toggle("active");

    if(searchInput.classList.contains("active")){
        searchInput.focus();
    }else{
        searchResult.classList.remove("active");
    }
});

//search Food
searchInput.addEventListener("keyup", ()=>{
    const keyword = searchInput.value.trim().toLowerCase();

    searchResult.innerHTML = "";

    if(keyword === ""){
        searchResult.classList.remove("active");
        return;
    }

    const filterdFoods = foods.filter(food => food.name.toLowerCase().includes(keyword));

    if(filterdFoods.length === 0){
        searchResult.innerHTML = `<div class='result'> No Food Found</div>`;
        searchResult.classList.add("active");
        return;
    }

    filterdFoods.forEach(food => {
        searchResults.innerHTML += `
            <div class="result">
                <h4>${food.name}</h4>
                <p>₹${food.price}</p>
            </div>
        `;
    });
    searchResult.classList.add("active");
});

//

document.addEventListener("click", (e) => {
    if(!e.target.closest(".search-section")){
        searchInput.classList.remove("active");
        searchResult.classList.remove("active");
    }
});


//Mobile Menu Functionality
const menuBtn = document.getElementById("menuBtn");
const navbar = document.querySelector(".navbar");

menuBtn.addEventListener("click", function(){
    navbar.classList.toggle("active");
})