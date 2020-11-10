class CadastroProduto {

	
	
	
	constructor(formularioCadastro, tabelaDeProdutos) {
		this.formularioCad = document.getElementById(formularioCadastro);
        this.tabelaProdutos = document.getElementById(tabelaDeProdutos);

		
		this.formCadProduto();
		this.atualizaContador();

		
		
		
	}

	// Realiza o cadastro de um produto ao bd
	formCadProduto() { 
		
		let btnCadProduto = document.querySelector("#btnCadastraProduto");
	
		this.formularioCad.addEventListener('submit', (event) => {
			event.preventDefault();
		});
		
		btnCadProduto.addEventListener('click', () => {
			
			//btnCadProduto.disabled = false;
			var servico = "";
			let formEdit = document.querySelector("#form-cadastro-produto");
			let id = formEdit.querySelector("#identCad");
			if (id.value==0) {
				servico = "InserirProduto";
			} else {
				servico = "AlterarProduto";
			}
			

			requestAjax(servico, this.formularioCad).then(
				
				(retorno) => {
					
		        	alert(retorno);
					this.formularioCad.reset();
					//btnCadProduto.disabled = true;
					this.atualizaContador();
					this.listaProdutos();
					
					this.mostraFormularioCad();
					
	        	},
	
				(error) => {
					console.log(error);
				}
			);
		});
		



		let formEdit = document.querySelector("#form-cadastro-produto");
		formEdit.querySelector("#btnCancelarProduto").addEventListener('click', () => {
			///xxxxxxx
		});
	
	}

	// Lista produtos já cadastrado na tabela de produto
	listaProdutos() {
		this.tabelaProdutos.innerHTML = "Aguarde... Carregando...";
		requestAjax("ListarProduto", this.formularioCad).then(
			
			(retorno) => {
				this.tabelaProdutos.innerHTML = "";
				retorno.forEach((campo) => {
				
					
					let tr = document.createElement('tr');
				
					tr.innerHTML += `
						<td>${campo.codigo}</td>
						<td>${campo.descricao}</td>
						<td>${"R$ " + campo.preco_custo}</td>
						<td>${"R$ " + campo.preco_venda}</td>
			            <td>${campo.categoria}</td>
			            <td>${campo.cod_fornecedor}</td>
						<td>${campo.quantidade}</td>
			            <td>
			            <button type="button" class="btn btn-primary btn-edit btn-xs btn-flat" onclick="editaCadastro(${campo.id})">Editar</button>
			            <button type="button" class="btn btn-danger btn-xs btn-flat" onclick="excluiCadastro(${campo.id})">Excluir</button>
			            </td> 
						
		        	`;

					tr.id = campo.id;
		
					this.tabelaProdutos.appendChild(tr);
				
				});
	
		   	}, 
		
			(error) => {
				console.log(error)
			}
			
		);
	
	};

	
	
	// Consulta a quantidade de registro no bd para atualizar o info de produtos
	atualizaContador() {

		requestAjax("ListarProduto", this.formularioCad).then(
			
			(retorno) => {
			
				document.querySelector("#quantidade-produtos").innerHTML = retorno.length;
		
		   	}, 
		
			(error) => {
				console.log(error)
			}
			
		);

    };

	mostraFormularioCad() {
        document.querySelector("#box-cadastro-produto-titulo").innerHTML = "Cadastrar Produto";
        let formEdit = document.querySelector("#form-cadastro-produto");
		let id = formEdit.querySelector("#identCad");
		id.value = 0;
    }

    mostraFormularioEdit() {
        document.querySelector("#box-cadastro-produto-titulo").innerHTML = "Alterar Produto";
    }	
}

// Adiciona os dados no formulario de edição e altera os valores
function editaCadastro(idProduto) {
	cadastroProduto.mostraFormularioEdit();
	
	
	let formEdit = document.querySelector("#form-cadastro-produto");
	let id = formEdit.querySelector("#identCad");
	id.value = idProduto;
	
	requestAjax("ConsultarProduto", formEdit).then(
			
		(retorno) => {
			
			formEdit.querySelector("#codigo").value = retorno.codigo
			formEdit.querySelector("#descricao").value = retorno.descricao
			formEdit.querySelector("#preco-custo").value = retorno.preco_custo
			formEdit.querySelector("#preco-venda").value = retorno.preco_venda
			formEdit.querySelector("#categoria").value = retorno.categoria
			formEdit.querySelector("#fornecedor").value = retorno.cod_fornecedor
			formEdit.querySelector("#qtd-estoque").value = retorno.quantidade
			
			
	   	}, 
	
		(error) => {
			console.log(error);
		}
			
	);

}

function excluiCadastro(idUsuario) {
		
	var formCad = document.querySelector("#form-cadastro-produto");
	
	let id = formCad.querySelector("#identCad");

	id.value = idUsuario;
	
	let modal = document.querySelector("#modalConfirm");
	modal.style.display = "block";
	modal.querySelector("#pModal").innerHTML = "Deseja excluir o produto?";
	modal.querySelector("#modalBotoes").innerHTML = '<button type="button" class="btn btn-primary" id="btnModalSim">Sim</button>'
						+ '<button type="button" class="btn btn-secondary" data-dismiss="modal"'
						+ 'id="btnModalNao">Não</button>';
	
	let opSim = modal.querySelector("#btnModalSim");
	opSim.addEventListener('click', () => {
	
		requestAjax("ExcluiProduto", formCad).then(
			
			(retorno) => {
				cadastroProduto.listaProdutos();
				cadastroProduto.atualizaContador();
			}, 
		
			(error) => {
				console.log("=> ERRO");
				console.log(error);
			}
				
		);
		
		
		modal.style.display = "none";	
	});
		
	let opNao = modal.querySelector("#btnModalNao");
	opNao.addEventListener('click', () => {
		modal.style.display = "none"
	});
}


