window.onload = function () {

    let visibleUserInformationListJson = window.visibleUserInformationListJson;
    let modifiableUserInformationMapJson = window.modifiableUserInformationMapJson;
    let userInfoMapJson = window.userInfoMapJson;
    let visibleUserInformationList = JSON.parse(visibleUserInformationListJson);
    let modifiableUserInformationMap = JSON.parse(modifiableUserInformationMapJson);
    let userInfoMap = JSON.parse(userInfoMapJson);
    // console.log(userModifiableInformationList);
    // console.log(modifiableUserInformationMap);
    // console.log(userInfoMap);

    createTable();

    function createTable() {

        let table = document.createElement("table");

        for (let i = 0; i < visibleUserInformationList.length; i++) {

            let column = visibleUserInformationList[i];
            let data = userInfoMap[column];
            let row = document.createElement("tr");

            let columnCell = document.createElement("td");
            columnCell.textContent = column;
            row.appendChild(columnCell);

            let dataCell = document.createElement("td");
            dataCell.textContent = data;
            row.appendChild(dataCell);

            let buttonCell = document.createElement("td");
            if (modifiableUserInformationMap[column] == true) {

                let button = document.createElement("button");
                button.innerHTML = "編集";

                button.onclick = function () {

                    let textbox = document.createElement('input');
                    textbox.id = column;
                    if (dataCell.contains(document.getElementById(column)) == true) {
                        return;
                    }
                    let initValue = dataCell.textContent;
                    removeAllChildNodes(dataCell);
                    textbox.setAttribute("type", "text");
                    textbox.setAttribute("value", initValue);
                    dataCell.appendChild(textbox);
                };

                buttonCell.appendChild(button);
            }
            row.appendChild(buttonCell);

            table.appendChild(row);
        }

        document.getElementById("user_info_display_div").appendChild(table);
    }

    const sendModifiedUserInfo = function (e) {

        let newEmail = userInfoMap['メールアドレス'];
        let newHeight = userInfoMap['身長'];
        let newWeight = userInfoMap['体重'];

        if (document.getElementById('メールアドレス')) {
            newEmail = document.getElementById('メールアドレス').value;
        }
        if (document.getElementById('身長')) {
            newHeight = document.getElementById('身長').value;
        }
        if (document.getElementById('体重')) {
            newWeight = document.getElementById('体重').value;
        }
        // console.log(document.getElementById('メールアドレス'));
        // console.log(document.getElementById('身長'));
        // console.log(document.getElementById('体重'));
        // console.log(newEmail);
        // console.log(newHeight);
        // console.log(newWeight);

        let invalidList = [];

        if (isValidateEmail(newEmail) == false) {
            invalidList.push('メールアドレス');
        }
        if (isValidateHeight(newHeight) == false) {
            invalidList.push('身長');
        }
        if (isValidateWeight(newWeight) == false) {
            invalidList.push('体重');
        }

        if (invalidList.length > 0) {
            e.stopPropagation();
            e.preventDefault();
            let errMsg = '次の値が不正です。\n';
            for (let elem of invalidList) {
                errMsg += elem + "\n";
            }
            alert(errMsg);
            return;
        }

        let form = document.getElementById('submit_userInfo');

        let inputEmail = document.createElement('input');
        inputEmail.type = 'hidden';
        inputEmail.name = 'email';
        inputEmail.value = newEmail;

        let inputHeight = document.createElement('input');
        inputHeight.type = 'hidden';
        inputHeight.name = 'height';
        inputHeight.value = newHeight;
        
        let inputWeight = document.createElement('input');
        inputWeight.type = 'hidden';
        inputWeight.name = 'weight';
        inputWeight.value = newWeight;

        form.appendChild(inputEmail);
        form.appendChild(inputHeight);
        form.appendChild(inputWeight);
    }

    document.getElementById('submit').addEventListener('click', sendModifiedUserInfo);
}

function removeAllChildNodes(parent) {

    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

function isValidateEmail(email) {

    let pattern = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]+.[A-Za-z0-9]+$/;
    return pattern.test(email);
}

function isValidateHeight(height) {

    return height > 0;
}

function isValidateWeight(weight) {

    return weight > 0;
}
