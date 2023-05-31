window.onload = function () {

    createTable();

    function createTable() {

        let visibleUserInformationListJson = window.visibleUserInformationListJson;
        let modifiableUserInformationMapJson = window.modifiableUserInformationMapJson;
        let userInfoMapJson = window.userInfoMapJson;
        let visibleUserInformationList = JSON.parse(visibleUserInformationListJson);
        let modifiableUserInformationMap = JSON.parse(modifiableUserInformationMapJson);
        let userInfoMap = JSON.parse(userInfoMapJson);
        // console.log(userModifiableInformationList);
        // console.log(modifiableUserInformationMap);
        // console.log(userInfoMap);

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

        newEmail = document.getElementById('メールアドレス');
        newHeight = document.getElementById('身長');
        newWeight = document.getElementById('体重');
        console.log(newEmail);
        console.log(newHeight);
        console.log(newWeight);
        e.stopPropagation();
        e.preventDefault();
    }

    document.getElementById('submit').addEventListener('click', sendModifiedUserInfo);
}

function removeAllChildNodes(parent) {

    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}
