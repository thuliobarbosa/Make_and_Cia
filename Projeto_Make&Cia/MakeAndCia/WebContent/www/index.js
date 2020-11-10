let cadastroProduto = new CadastroProduto("form-cadastro-produto","tabela-produtos");
let cadastroUsuario = new CadastroUsuario("form-cadastro-usuario","tabela-usuarios");

document.querySelector("#btnCadastroProduto").addEventListener('click', () => {
    mostraPainelProduto();
})

document.querySelector("#btnCadastroUsuario").addEventListener('click', () => {
    mostraPainelUsuario();
})

document.querySelector("#btnVendas").addEventListener('click', () => {
    mostraPainelVendas();
})

document.querySelector("#show_cadastro-usuario").addEventListener('submit', (e) => {
    e.preventDefault();
})



function mostraPainelVendas() {
    document.querySelector("#show_cadastro-produto").style.display = "none";
    document.querySelector("#show_cadastro-usuario").style.display = "none";
    document.querySelector("#show_vendas").style.display = "block";
}

function mostraPainelUsuario() {
    document.querySelector("#show_cadastro-produto").style.display = "none";
    document.querySelector("#show_vendas").style.display = "none";
    document.querySelector("#show_cadastro-usuario").style.display = "block";
	cadastroUsuario.listaUsuarios();
    
}

function mostraPainelProduto() {
    document.querySelector("#show_vendas").style.display = "none";
    document.querySelector("#show_cadastro-produto").style.display = "block";
    document.querySelector("#show_cadastro-usuario").style.display = "none";
    cadastroProduto.listaProdutos();
}