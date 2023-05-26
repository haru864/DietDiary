window.onload = function () {

    const showFoodName = function () {

        let foodNameMapJsonFromWindow = window.foodNameMapJson;
        var foodNameMap = JSON.parse(foodNameMapJsonFromWindow);
        var foodGroupNumber = document.getElementById("food_group").value;
        // console.log(foodGroupNumber + ', ' + (foodGroupNumber != ""));

        if (foodGroupNumber != "") {

            let foodNameSelect = document.getElementById("food_name");
            removeAllChildNodes(foodNameSelect);

            let option = document.createElement("option");
            option.value = '';
            option.text = '--選択してください--';
            foodNameSelect.appendChild(option);

            for (let key in foodNameMap) {

                if (key != foodGroupNumber) {
                    continue;
                }

                let foodNameList = foodNameMap[key];

                for (let i = 0; i < foodNameList.length; i++) {
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
            document.getElementById("grams_input_field_div").style.display = "none";
        }
    }

    const showGramsInputField = function () {

        let foodNameSelect = document.getElementById("food_name").value;
        // console.log(foodNameSelect + ', ' + (foodNameSelect != ""));

        if (foodNameSelect != "") {
            document.getElementById("grams_input_field_div").style.display = "block";
        } else {
            document.getElementById("grams_input_field_div").style.display = "none";
        }
    }

    const checkGramsInput = function () {

        let gramsInput = document.getElementById("grams_input").value;
        console.log(gramsInput);

        if (isNaN(gramsInput) == true) {
            alert('数値を入力してください');
            return;
        }

        if (isPositiveDecimal(gramsInput) == false) {
            alert('10進数の正整数を入力してください');
            return;
        }
    }

    const checkInputFields = function (e) {

        let foodGroupNumber = document.getElementById('food_group').value;
        let foodName = document.getElementById('food_name').value;
        let foodWeightGrams = document.getElementById('grams_input').value;

        if (foodGroupNumber == "" || foodName == "" || foodWeightGrams == "") {
            alert('すべての入力値を設定してください');
            e.preventDefault();
        }
    }

    document.getElementById('food_group').addEventListener('change', showFoodName);
    document.getElementById('food_name').addEventListener('change', showGramsInputField);
    document.getElementById('grams_input').addEventListener('change', checkGramsInput);
    document.getElementById('submit_inputs').addEventListener('click', checkInputFields);

};

function isPositiveDecimal(value) {

    return /^\d+$/.test(String(value));
}

function removeAllChildNodes(parent) {

    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}
