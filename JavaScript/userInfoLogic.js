createTable();

function createTable() {

    let userModifiableInformationListJson = window.userModifiableInformationListJson;
    let userInfoMapJson = window.userInfoMapJson;
    let userModifiableInformationList = JSON.parse(userModifiableInformationListJson);
    let userInfoMap = JSON.parse(userInfoMapJson);
    // console.log(userModifiableInformationList);
    // console.log(userInfoMap);

    var table = "<table border='1'>";

    for (var i = 0; i < userModifiableInformationList.length; i++) {

        let key = userModifiableInformationList[i];
        let value = userInfoMap[key];
        // console.log(key + ": " + value);

        table += "<tr>";
        table += "<td>" + key + "</td>";
        table += "<td>" + value + "</td>";
        table += "</tr>";
    }

    table += "</table>";

    document.getElementById("user_info_display_div").innerHTML = table;
}

