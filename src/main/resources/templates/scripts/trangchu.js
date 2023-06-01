const allSideMenu = document.querySelectorAll("#sidebar .side-menu.top li a");

// TOGGLE SIDEBAR
const menuBar = document.querySelector("#content nav .bx.bx-menu");
const sidebar = document.getElementById("sidebar");

menuBar.addEventListener("click", function () {
  sidebar.classList.toggle("hide");
});

const searchButton = document.querySelector(
  "#content nav form .form-input button"
);
const searchButtonIcon = document.querySelector(
  "#content nav form .form-input button .bx"
);
const searchForm = document.querySelector("#content nav form");

searchButton.addEventListener("click", function (e) {
  if (window.innerWidth < 576) {
    e.preventDefault();
    searchForm.classList.toggle("show");
    if (searchForm.classList.contains("show")) {
      searchButtonIcon.classList.replace("bx-search", "bx-x");
    } else {
      searchButtonIcon.classList.replace("bx-x", "bx-search");
    }
  }
});

if (window.innerWidth < 768) {
  sidebar.classList.add("hide");
} else if (window.innerWidth > 576) {
  searchButtonIcon.classList.replace("bx-x", "bx-search");
  searchForm.classList.remove("show");
}

window.addEventListener("resize", function () {
  if (this.innerWidth > 576) {
    searchButtonIcon.classList.replace("bx-x", "bx-search");
    searchForm.classList.remove("show");
  }
});

const switchMode = document.getElementById("switch-mode");

switchMode.addEventListener("change", function () {
  if (this.checked) {
    document.body.classList.add("dark");
  } else {
    document.body.classList.remove("dark");
  }
});

//BEGIN
const LIST_ITEMS = {
  sinhVien: {
    id: "sinhVien",
    tabName: "Sinh Viên",
  },
  giangVien: {
    id: "giangVien",
    tabName: "Giảng Viên",
  },
  monHoc: {
    id: "monHoc",
    tabName: "Môn Học",
  },
  hocPhan: {
    id: "hocPhan",
    tabName: "Học Phần",
  },
  phongHoc: {
    id: "phongHoc",
    tabName: "Phòng Học",
  },
  ctdt: {
    id: "ctdt",
    tabName: "Chương Trình Đào Tạo",
  },
  tkb: {
    id: "tkb",
    tabName: "Thời Khóa Biểu",
  },
  email: {
    id: "email",
    tabName: "Gửi Email",
  },
  dkOnl: {
    id: "dkOnl",
    tabName: "Đăng Ký Phòng Học Online",
  },
};

$("#sinhVien").load("sinhvien.html #sinhVien");

let currentTabValue = LIST_ITEMS.sinhVien.tabName;
let currentRenderId = LIST_ITEMS.sinhVien.id;

function setCurrentRenderId(willChangeThisCurrentRenderId, newId) {
  document.getElementById(willChangeThisCurrentRenderId).id = newId;
  currentRenderId = newId;
}

function setRenderTab(id) {
  setCurrentRenderId(currentRenderId, id);
  $(`#${id}`).load(`${id.toLowerCase()}.html #${id}`);
}

allSideMenu.forEach((item) => {
  const li = item.parentElement;

  item.addEventListener("click", function () {
    allSideMenu.forEach((i) => {
      i.parentElement.classList.remove("active");
    });
    li.classList.add("active");
    const element = document.querySelector(".active .text");
    currentTabValue = element.textContent;
    switch (currentTabValue) {
      case LIST_ITEMS.sinhVien.tabName:
        setRenderTab(LIST_ITEMS.sinhVien.id);
        break;
      case LIST_ITEMS.giangVien.tabName:
        setRenderTab(LIST_ITEMS.giangVien.id);
        break;
      case LIST_ITEMS.monHoc.tabName:
        setRenderTab(LIST_ITEMS.monHoc.id);
        break;
      case LIST_ITEMS.hocPhan.tabName:
        setRenderTab(LIST_ITEMS.hocPhan.id);
        break;
      case LIST_ITEMS.phongHoc.tabName:
        setRenderTab(LIST_ITEMS.phongHoc.id);
        break;
      case LIST_ITEMS.ctdt.tabName:
        setRenderTab(LIST_ITEMS.ctdt.id);
        break;
      case LIST_ITEMS.tkb.tabName:
        setRenderTab(LIST_ITEMS.tkb.id);
        break;
      case LIST_ITEMS.email.tabName:
        setRenderTab(LIST_ITEMS.email.id);
        break;
      case LIST_ITEMS.dkOnl.tabName:
        setRenderTab(LIST_ITEMS.dkOnl.id);
        break;
      default:
        $("#sinhVien").load("sinhvien.html #sinhVien");
    }
  });
});
