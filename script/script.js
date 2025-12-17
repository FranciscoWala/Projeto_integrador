// script do menu lateral da versão responsiva
const menuButton = document.getElementById("menuButton");
const menu = document.getElementById("menu")
menuButton.addEventListener("click", () => {
    if (menu.style.display === "none") {
        menu.style.display = "block";
    } else {
        menu.style.display = "none";
    }
});

// script do botões da página principal

const botaoVerFuncionalidades = document.querySelector('.botão2');

botaoVerFuncionalidades.addEventListener('click', function () {

    alert('Pague primeiro!');
});

// script do botões de "QUERO ESTE", "FALAR COM CONSULTOR", "COMEÇA AGORA"
const botaoQueroEste = document.querySelector('.quero');

botaoQueroEste.addEventListener('click', function () {

    alert('🎉 Parabéns! Você acabou de adquirir o nosso produto');
});

const botaoFalarComConsultor = document.querySelector('.aperte2');

botaoFalarComConsultor.addEventListener('click', function () {

    alert('🎉 Parabéns! Você acabou de adquirir o nosso produto');
});

const botaoComecaAgora = document.querySelector('.aperte');

botaoComecaAgora.addEventListener('click', function () {

    alert('🎉 Parabéns! Você acabou de adquirir o nosso produto');
});

// script do botão de solicitar contato
const botaoSolicitar = document.querySelector('.solicitar');

botaoSolicitar.addEventListener('click', function () {

    alert('Entraremos em contato em sté duas horas!');
});

// script do formulario 
const buttons = document.querySelectorAll(".clear-btn");

buttons.forEach(btn => {
    btn.addEventListener("click", () => {
        const id = btn.getAttribute("data-input");
        document.getElementById(id).value = "";
        document.getElementById(id).focus();
    });
});

// script do botão anual e mensal
const toggle = document.getElementById('togglePrice');
const duquePriceElement = document.getElementById('duquePrice');
const kingPriceElement = document.getElementById('kingPrice');
const mensalText = document.getElementById('mensalText');
const anualText = document.getElementById('anualText');
const periodoSpan = document.querySelectorAll('.plan-card > span');

const prices = {
    duque: {
        mensal: 299,
        anual: 2870,
    },
    king: {
        mensal: 599,
        anual: 5750,
    }
};

function updatePrices() {
    const isAnnual = toggle.checked;

    duquePriceElement.textContent = `R$${isAnnual ? prices.duque.anual.toFixed(2).replace('.', ',') : prices.duque.mensal.toFixed(0)}`;
    kingPriceElement.textContent = `R$${isAnnual ? prices.king.anual.toFixed(2).replace('.', ',') : prices.king.mensal.toFixed(0)}`;

    periodoSpan.forEach(span => {
        span.textContent = isAnnual ? '/anualmente' : '/mês';
    });

    if (isAnnual) {
        mensalText.classList.remove('active-toggle');
        mensalText.classList.add('inactive-toggle');
        anualText.classList.remove('inactive-toggle');
        anualText.classList.add('active-toggle');
    } else {
        mensalText.classList.remove('inactive-toggle');
        mensalText.classList.add('active-toggle');
        anualText.classList.remove('active-toggle');
        anualText.classList.add('inactive-toggle');
    }
}

toggle.addEventListener('change', updatePrices);

updatePrices();
