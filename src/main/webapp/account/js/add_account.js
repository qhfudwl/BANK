let account; // 선택된 radio
const balance = document.querySelector("#balance");
const interestRate = document.querySelector("#interest_rate");
const overDraft = document.querySelector("#over_draft");

// sa일 경우 한도액 못적음
$('input[name="accType"]').on("click", function() {
   inputOverDraft();
})
inputOverDraft();
function inputOverDraft() {
   account = document.querySelector('input[type="radio"]:checked');
   if (account.value == "SA"){
      interestRate.setAttribute("value", "0.05");
      interestRate.removeAttribute("disabled");
      overDraft.setAttribute("disabled", "true");
      overDraft.setAttribute("value", "보통계좌는 한도액을 입력할 수 없습니다.");
      overDraft.style.color = "#b50000";
   } else {
      interestRate.setAttribute("value", "0");
      overDraft.removeAttribute("disabled");
      overDraft.setAttribute("value", "");
      overDraft.style.color = "#000";
   }
}