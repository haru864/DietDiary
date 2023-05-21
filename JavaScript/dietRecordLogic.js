

function showFoodName() {

    console.log('<%= jsonMap %>');

    var foodGroup = document.getElementById("food_group").value;
    var jsObject = JSON.parse('<%= jsonMap %>');

    if (foodGroup != "") {
        document.getElementById("food_name_div").style.display = "block";
    } else {
        document.getElementById("food_name_div").style.display = "none";
    }
}


