

function showFoodName() {

    let foodNameMapJsonFromWindow = window.foodNameMapJson;
    // console.log(foodNameMapJsonFromWindow);

    var foodNameMap = JSON.parse(foodNameMapJsonFromWindow);
    // console.log(foodNameMap);

    // var jsObject = JSON.parse('{"1":["じゃがいも","米","パン"],"2":["牛肉","鶏肉","さかな"]}'); // success
    // var jsObject = JSON.parse("{\"1\":[\"じゃがいも\",\"米\",\"パン\"],\"2\":[\"牛肉\",\"鶏肉\",\"さかな\"]}"); // success

    var foodGroupNumber = document.getElementById("food_group").value;
    console.log('foodGroupNumber: ' + foodGroupNumber);

    if (foodGroupNumber != "") {

        let foodNameSelect = document.getElementById("food_name");
        removeAllChildNodes(foodNameSelect);

        for (let key in foodNameMap) {

            if (key != foodGroupNumber) {
                continue;
            }
            // console.log('key:' + key + ' value:' + foodNameMap[key]);

            let foodNameList = foodNameMap[key];

            for (let i = 0; i < foodNameList.length; i++) {
                // console.log('i:' + i + ' element:' + foodNameList[i]);
                let option = document.createElement("option");
                option.value = foodNameList[i];
                option.text = foodNameList[i];
                foodNameSelect.appendChild(option);
            }
            break;
        }

        document.getElementById("food_name_div").style.display = "block";

    } else {

        document.getElementById("food_name_div").style.display = "none";
    }
}

function removeAllChildNodes(parent) {

    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}
