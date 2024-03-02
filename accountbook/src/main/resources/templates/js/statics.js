const categoryList = {
  food: "식비",
  cafe: "카페",
  mart: "마트/생필품",
  culture: "문화생활",
  medical: "의료비",
  dues: "공과금",
  transportation: "교통비",
  communication: "통신비",
  subscription: "구독료",
  hobby: "취미",
  shopping: "쇼핑",
  beauty: "미용",
  gift: "경조사/선물",
  travel: "여행",
  etc: "기타",
  salary: "급여",
  additional: "부수입",
  allowance: "용돈",
};
const indexColorList = [
  "#FFA8BE",
  "#F8DDDC",
  "#FFCDCE",
  "#F3CDCC",
  "#F5E0CE",
  "#FFF5EB",
  "#FFECC8",
  "#FFE0D1",
  "#EDD2E1",
  "#F5C8DF",
  "#DDE7D0",
  "#C8E6D1",
  "#C7D7EA",
  "#D7CFE2",
  "#DDD7D3",
];

let month = new Date().getMonth();
let year = new Date().getFullYear();

function renderDate() {
  const dateElement = document.querySelector(".date");
  const prevBtn = document.querySelector(".prev.btn");
  const nextBtn = document.querySelector(".next.btn");

  dateElement.textContent = `${year}년 ${month + 1}월`;

  prevBtn.addEventListener("click", () => {
    month--;
    if (month < 0) {
      month = 11;
      year--;
    }
    dateElement.textContent = `${year}년 ${month + 1}월`;
    displayTotalAmount();
    displayStatics();
  });
  nextBtn.addEventListener("click", () => {
    month++;
    if (month > 11) {
      month = 0;
      year++;
    }
    dateElement.textContent = `${year}년 ${month + 1}월`;
    displayTotalAmount();
    displayStatics();
  });
}

// 임의로 작성한 데이터
const totalData = {
  year: 2024,
  month: 2,
  "expense-total": 1001486,
  "income-total": 2547000,
};

// 지출/수입 총액 input label에 추가하기
function displayTotalAmount() {
  async function getData() {
    const res = await fetch(
      `/users/${username}/statics/total?year=${year}&month=${month}`
    );
    const totalData = await res.json();
  }
  // getData();

  const expenseLabelElement = document.querySelector(".expense-label");
  const incomeLabelElement = document.querySelector(".income-label");

  expenseTotal = document.createTextNode(
    `지출 ${totalData["expense-total"].toLocaleString()}원`
  );
  incomeTotal = document.createTextNode(
    `수입 ${totalData["income-total"].toLocaleString()}원`
  );

  expenseLabelElement.replaceChild(expenseTotal, expenseLabelElement.lastChild);
  incomeLabelElement.replaceChild(incomeTotal, incomeLabelElement.lastChild);
}

// 지출/수입 선택 따라 차트 생성
function displayStatics() {
  // 지출/수입 중에 무엇을 선택했는지 확인
  const division = document.querySelector('input[type="radio"]:checked').value;

  const totalAmount =
    division === "expense"
      ? totalData["expense-total"]
      : totalData["income-total"];

  // let data = {};
  // 해당 연도, 달의 지출 or 수입 데이터 불러오기
  async function getData(division) {
    const res = await fetch(
      `/users/${username}/statics/category/${division}?year=${year}&month=${month}`
    );
    data = res.json();
  }
  // getData();

  // !!!!! 임의로 작성한 데이터
  const data = {
    year: 2024,
    month: 2,
    food: 523800,
    cafe: 41500,
    mart: 21980,
    culture: 31000,
    medical: 5000,
    dues: 40230,
    transportation: 63250,
    communication: 33000,
    subscription: 17900,
    hobby: 59900,
    shopping: 89900,
    beauty: 22000,
    gift: 50000,
    travel: 0,
    etc: 0,
  };
  // 임의로 작성한 데이터 !!!!!

  // 카테고리별 총액 데이터만 남김
  delete data.year;
  delete data.month;

  // 카테고리명 변경
  for (const key in data) {
    data[categoryList[key]] = data[key];
    delete data[key];
  }

  // 총액이 큰 순서대로 정렬
  const sortedData = Object.fromEntries(
    Object.entries(data).sort(([, a], [, b]) => (a > b ? -1 : 1))
  );

  const totalAmountElement = document.querySelector(".total-amount");
  totalAmountElement.textContent = totalAmount.toLocaleString() + "원";

  // 도넛 차트 생성
  const ctx = document.getElementById("myChart");

  if (myChart instanceof Chart) {
    myChart.destroy();
  }

  myChart = new Chart(ctx, {
    type: "doughnut",
    data: {
      labels: [...Object.keys(sortedData)],
      datasets: [
        {
          label: "total",
          data: [...Object.values(sortedData)],
          backgroundColor: [...indexColorList],
          borderWidth: 1,
        },
      ],
    },
    options: {
      responsive: false,
      cutout: "30%",
      radius: "80%",
      plugins: {
        legend: {
          display: false,
        },
      },
    },
  });

  // 범례 생성
  let i = 0;
  const legend = document.querySelector(".legend");
  legend.innerHTML = "";
  for (const key in sortedData) {
    const legendItem = document.createElement("div");
    const leftDiv = document.createElement("div");
    const indexColor = document.createElement("div");
    const category = document.createElement("div");
    const percent = document.createElement("div");
    const total = document.createElement("div");

    indexColor.style.backgroundColor = indexColorList[i];
    i++;
    category.textContent = key;
    percent.textContent =
      Math.round((sortedData[key] / totalAmount) * 100) + "%";
    total.textContent = sortedData[key].toLocaleString() + "원";

    legendItem.classList.add("legend-item");
    leftDiv.classList.add("left-div");
    indexColor.classList.add("index-color");
    percent.classList.add("percent");
    total.classList.add("total");

    legend.appendChild(legendItem);
    leftDiv.appendChild(indexColor);
    leftDiv.appendChild(category);
    leftDiv.appendChild(percent);
    legendItem.appendChild(leftDiv);
    legendItem.appendChild(total);
  }
}

renderDate();
displayTotalAmount();
displayStatics();

const divisions = document.querySelectorAll("input[name='division']");
divisions.forEach((division) => {
  division.addEventListener("click", displayStatics);
});
