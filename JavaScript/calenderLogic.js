const calendarEl = document.getElementById("calendar");

// 今月のカレンダーを生成する
const currentDate = new Date();
const year = currentDate.getFullYear();
const month = currentDate.getMonth();
const firstDate = new Date(year, month, 1);
const lastDate = new Date(year, month + 1, 0);

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

    const cellForm = document.createElement("form");
    cellForm.method = "post";
    cellForm.action = "/DietDiary/MypageServlet";

    let params = { "action": "display", "page": "diary", "year": year, "month": month, "day": date };
    for (let key in params) {
        const cellInput = document.createElement("input");
        cellInput.type = "hidden";
        cellInput.name = key;
        cellInput.value = params[key];
        cellForm.appendChild(cellInput);
    }

    const cell = document.createElement("td");
    cell.textContent = date;

    const submitForm = function () {
        cellForm.submit();
    };

    // クリック時に遷移するリンクを設定
    cell.style.cursor = "pointer";
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