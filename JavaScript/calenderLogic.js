let calendarEl = document.getElementById("calendar");
let currentDate = new Date();
let currentYear = currentDate.getFullYear();
let currentMonth = currentDate.getMonth();
let firstDate = new Date(currentYear, currentMonth, 1);
let lastDate = new Date(currentYear, currentMonth + 1, 0);

const viewPrevMonth = () => {
    removeAllChildElement("calendar");
    currentDate.setDate(0);
    currentDate.setDate(1);
    currentYear = currentDate.getFullYear();
    currentMonth = currentDate.getMonth();
    firstDate = new Date(currentYear, currentMonth, 1);
    lastDate = new Date(currentYear, currentMonth + 1, 0);
    generateCalender();
}

const viewNextMonth = () => {
    removeAllChildElement("calendar");
    currentDate.setDate(1);
    currentDate.setMonth(currentMonth + 1);
    currentYear = currentDate.getFullYear();
    currentMonth = currentDate.getMonth();
    firstDate = new Date(currentYear, currentMonth, 1);
    lastDate = new Date(currentYear, currentMonth + 1, 0);
    generateCalender();
}

document.getElementById("view-prev-month").addEventListener("click", viewPrevMonth);
document.getElementById("view-next-month").addEventListener("click", viewNextMonth);
generateCalender();

function generateCalender() {

    // カレンダーのヘッダーを作成
    currentYearMonth = document.getElementById("current-year-month");
    currentYearMonth.textContent = currentYear + "年" + (currentMonth + 1) + "月";

    // カレンダーのテーブルのヘッダーを作成
    const headerRow = document.createElement("tr");
    headerRow.style.backgroundColor = "skyblue";
    const days = ["日", "月", "火", "水", "木", "金", "土"];
    for (const day of days) {
        const cell = document.createElement("td");
        cell.textContent = day;
        headerRow.appendChild(cell);
    }
    calendarEl.appendChild(headerRow);

    // カレンダーの日付の行を作成
    let row = document.createElement("tr");
    for (let i = 0; i < firstDate.getDay(); i++) {
        row.appendChild(document.createElement("td"));
    }
    for (let date = 1; date <= lastDate.getDate(); date++) {

        const cell = document.createElement("td");
        cell.textContent = date;

        // ダイアリー画面へPOSTするためのフォームを生成
        const cellForm = document.createElement("form");
        cellForm.method = "post";
        cellForm.action = "/DietDiary/MypageServlet";

        // リクエストパラメータを設定
        let params = { "action": "display", "page": "diary", "year": currentYear, "month": (currentMonth + 1), "day": date };
        for (let key in params) {
            const cellInput = document.createElement("input");
            cellInput.type = "hidden";
            cellInput.name = key;
            cellInput.value = params[key];
            cellForm.appendChild(cellInput);
        }

        // クリック時にリクエストを送るイベントを設定
        cell.style.cursor = "pointer";
        const submitForm = () => {
            cellForm.submit();
        };
        cell.addEventListener("click", submitForm);

        cell.appendChild(cellForm);
        row.appendChild(cell);

        if (row.children.length === 7) {
            calendarEl.appendChild(row);
            row = document.createElement("tr");
        }
    }
    if (row.children.length > 0) {
        calendarEl.appendChild(row);
    }
}

function removeAllChildElement(parentElementID) {

    var parentElement = document.getElementById(parentElementID);
    while (parentElement.firstChild) {
        parentElement.removeChild(parentElement.firstChild);
    }
}