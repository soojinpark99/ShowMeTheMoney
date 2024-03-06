let month = new Date().getMonth();
let year = new Date().getFullYear();

// 현재 사용자의 username 얻기
let username;
async function getUsername() {
  try {
    const res = await fetch("/username");
    const json = await res.json();
    username = json.username;
  } catch (error) {
    console.error("Error fetching username:", error);
  }
}

function handlePageBtns() {
  const staticsPage = document.querySelector(".statics-page");
  staticsPage.addEventListener("click", () => {
    window.location.pathname = `/statics/users/${username}`;
  });
}

// !!!!! 임의로 작성한 데이터
// const totalData = {
//   year: 2024,
//   month: 3,
//   "expense-total": 1001486,
//   "income-total": 2547000,
// };
// 임의로 작성한 데이터 !!!!!

async function renderMonthlyTotalData() {
  const res = await fetch(
    `/users/${username}/statics/total?year=${year}&month=${month}`
  );
  const totalData = await res.json();

  const monthlyIncome = totalData["income-total"];
  const monthlyExpense = totalData["expense-total"];

  const monthlyIncomeElement = document.querySelector(".income");
  const monthlyExpenseElement = document.querySelector(".expense");
  const monthlyTotalEmement = document.querySelector(".total");

  monthlyIncomeElement.textContent = `+${monthlyIncome.toLocaleString()}원`;
  monthlyExpenseElement.textContent = `-${monthlyExpense.toLocaleString()}원`;
  monthlyTotalEmement.textContent = `${(
    monthlyIncome - monthlyExpense
  ).toLocaleString()}원`;
}

// --- 특정 날짜의 거래 내역 데이터를 렌더링하여 페이지에 추가 ---
async function displayTransactions(year, month, date) {
  // 특정 날짜의 거래 내역 데이터 불러오기
  const res = await fetch(
    `/users/${username}/transactions?date=${year}-${month}-${date}`
  );
  const datasOfClickedDate = await res.json();

  // !!!!! 임의로 작성한 데이터
  // let datas = [
  //   {
  //     division: "expense",
  //     money: "1000",
  //     date: "2024-02-24",
  //     category: "food",
  //     memo: "편의점",
  //   },
  //   {
  //     division: "expense",
  //     money: "18000",
  //     date: "2024-02-25",
  //     category: "culture",
  //   },
  //   {
  //     division: "income",
  //     money: "10000",
  //     date: "2024-02-25",
  //     category: "additional",
  //     memo: "당근마켓",
  //   },
  //   {
  //     division: "expense",
  //     money: "13000",
  //     date: "2024-02-22",
  //     category: "food",
  //     memo: "배달의 민족",
  //   },
  //   {
  //     division: "expense",
  //     money: "45000",
  //     date: "2024-02-22",
  //     category: "shopping",
  //     memo: "러쉬 샤워젤리",
  //   },
  // ];

  // datasOfClickedDate = datas.filter((data) => {
  //   const dataDate = new Date(data.date);
  //   return (
  //     dataDate.getFullYear() == year &&
  //     dataDate.getMonth() == month &&
  //     dataDate.getDate() == date
  //   );
  // });
  // 임의로 작성한 데이터 !!!!!

  // 선택된 날짜 배경색 바꾸기
  const dateNodes = [...document.querySelectorAll(".date")];
  dateNodes.forEach((dateNode) => {
    dateNode.classList.remove("selected-date");
    if (dateNode.dataset.date == date) {
      dateNode.classList.add("selected-date");
    }
  });

  // 거래 내역 동적 생성
  const transactions = document.querySelector(".transactions");
  transactions.innerHTML = "";

  datasOfClickedDate.forEach((data) => {
    const transactionDiv = document.createElement("div");
    transactionDiv.classList.add("transaction-div");

    const money = document.createElement("div");
    if (data.division === "income") {
      money.textContent = "+" + (+data.money).toLocaleString() + "원";
      money.classList.add("transaction-money");
      money.classList.add("income");
    } else {
      money.textContent = (-data.money).toLocaleString() + "원";
      money.classList.add("transaction-money");
      money.classList.add("expense");
    }
    const div = document.createElement("div");
    const category = document.createElement("div");
    const memo = document.createElement("div");

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

    category.textContent = categoryList[data.category];
    category.classList.add("transaction-category");
    memo.textContent = data.memo;
    memo.classList.add("transaction-memo");

    transactions.append(transactionDiv);
    div.append(category);
    div.append(memo);
    transactionDiv.append(div);
    transactionDiv.append(money);
  });
}

// --- 달력 동적 생성 ---
function renderCalendar() {
  const calendarDates = document.getElementById("calendarDates");
  const monthElement = document.getElementById("month");
  const prevBtn = document.querySelector(".prev.btn");
  const nextBtn = document.querySelector(".next.btn");

  // 특정 연도와 달의 달력 페이지 생성
  function renderDates() {
    const firstDayOfMonth = new Date(year, month, 1);
    const daysInMonth = new Date(year, month + 1, 0).getDate();

    const startDayOfWeek = firstDayOfMonth.getDay();

    monthElement.textContent = `${year}년 ${month + 1}월`;

    calendarDates.innerHTML = "";

    for (let i = 0; i < startDayOfWeek; i++) {
      const emptyDate = document.createElement("div");
      emptyDate.classList.add("empty");
      calendarDates.appendChild(emptyDate);
    }

    for (let i = 1; i <= daysInMonth; i++) {
      const dateElement = document.createElement("div");
      dateElement.classList.add("date");
      dateElement.textContent = i;
      dateElement.dataset.date = i;
      calendarDates.appendChild(dateElement);
    }
  }

  renderDates();

  // 이전 버튼을 누르면 이전달로 이동
  prevBtn.addEventListener("click", () => {
    month--;
    if (month < 0) {
      month = 11;
      year--;
    }
    renderDates();
    updateData();
  });
  // 다음 버튼을 누르면 다음달로 이동
  nextBtn.addEventListener("click", () => {
    month++;
    if (month > 11) {
      month = 0;
      year++;
    }
    renderDates();
    updateData();
  });
}

// --- 현재 날짜의 거래 내역을 보여주는 함수 ---
function displayTransactionsOfToday() {
  const today = new Date();
  const currentYear = today.getFullYear();
  const currentMonth = today.getMonth();
  const currentDate = today.getDate();
  if ((year === currentYear) & (month === currentMonth)) {
    displayTransactions(currentYear, currentMonth, currentDate);
  } else {
    const transactions = document.querySelector(".transactions");
    transactions.innerHTML = "";
  }
}

// --- 일별 지출/수입 금액을 합산하여 달력에 표시 ---
async function renderDailyTotalData() {
  const dateNodes = [...document.querySelectorAll(".date")];
  for (let i = 0; i < dateNodes.length; i++) {
    const res = await fetch(
      `/users/${username}/transactions?date=${year}-${month}-${i + 1}`
    );
    const dailyData = await res.json();

    // !!!!! 임의로 작성한 데이터
    // let datas = [
    //   {
    //     division: "expense",
    //     money: "1000",
    //     date: "2024-02-24",
    //     category: "food",
    //     memo: "편의점",
    //   },
    //   {
    //     division: "expense",
    //     money: "18000",
    //     date: "2024-02-25",
    //     category: "culture",
    //   },
    //   {
    //     division: "income",
    //     money: "10000",
    //     date: "2024-02-25",
    //     category: "additional",
    //     memo: "당근마켓",
    //   },
    //   {
    //     division: "expense",
    //     money: "13000",
    //     date: "2024-02-22",
    //     category: "food",
    //     memo: "배달의 민족",
    //   },
    //   {
    //     division: "expense",
    //     money: "45000",
    //     date: "2024-02-22",
    //     category: "shopping",
    //     memo: "러쉬 샤워젤리",
    //   },
    // ];

    // dailyData = datas.filter((data) => {
    //   const dataDate = new Date(data.date);
    //   return (
    //     dataDate.getFullYear() == year &&
    //     dataDate.getMonth() == month &&
    //     dataDate.getDate() == i + 1
    //   );
    // });
    // 임의로 작성한 데이터 !!!!!

    let totalExpense = 0;
    let totalIncome = 0;
    dailyData.forEach((data) => {
      if (data.division === "expense") {
        totalExpense += +data.money;
      } else {
        totalIncome += +data.money;
      }
    });

    if (totalIncome !== 0) {
      const dailyIncomeElement = document.createElement("div");
      dailyIncomeElement.textContent = "+" + totalIncome.toLocaleString();
      dailyIncomeElement.classList.add("daily-income");
      dateNodes[i].appendChild(dailyIncomeElement);
    }
    if (totalExpense !== 0) {
      const dailyExpenseElement = document.createElement("div");
      dailyExpenseElement.textContent = "-" + totalExpense.toLocaleString();
      dailyExpenseElement.classList.add("daily-expense");
      dateNodes[i].appendChild(dailyExpenseElement);
    }
  }
}

// --- 달력의 날짜 칸마다 이벤트 등록 ---
function registerClickEvent(year, month) {
  const dateNodes = [...document.querySelectorAll(".date")];

  dateNodes.forEach((dateNode) =>
    dateNode.addEventListener("click", (e) => {
      const clickedDate = e.currentTarget.dataset.date;
      displayTransactions(year, month, clickedDate);
    })
  );
}

// 달력 페이지마다 실행할 함수
function updateData() {
  displayTransactionsOfToday();
  renderMonthlyTotalData();
  renderDailyTotalData();
  registerClickEvent(year, month);
}

async function init() {
  await getUsername();
  handlePageBtns();
  updateData();
}

renderCalendar();
init();
