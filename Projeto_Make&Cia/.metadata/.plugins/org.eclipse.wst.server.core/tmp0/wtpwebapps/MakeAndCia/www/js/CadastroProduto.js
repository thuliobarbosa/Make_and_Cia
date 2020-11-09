class CadastroProduto {

	constructor(formularioCadastro, tabelaDeProdutos) {
		
		this.formularioCad = document.getElementById(formularioCadastro);
        this.tabelaProdutos = document.getElementById(tabelaDeProdutos);

		this.listaProdutos();
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
			
			requestAjax("InserirProduto", this.formularioCad).then(
				
				(retorno) => {
					
		        	alert(retorno);
					this.formularioCad.reset();
					//btnCadProduto.disabled = true;
					this.atualizaContador();
					
					requestAjax("ListarProduto", this.formularioCad).then(
						
						(retorno) => {
				
							this.addLine(retorno[retorno.length - 1]);
							
	   					},
		 
						(error) => {
							console.log(error);
						}	
					);
	        	},
	
				(error) => {
					console.log(error);
				}
			);
		});
	
	}

	// Lista produtos já cadastrado na tabela de produto
	listaProdutos() {
		
		requestAjax("ListarProduto", this.formularioCad).then(
			
			(retorno) => {
			
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

					tr.id = campo.id

					tr.querySelector(".btn-edit").addEventListener('click', (event) => {
						this.mostraFormularioEdit();
					});
							
					this.tabelaProdutos.appendChild(tr);
				
				});
	
		   	}, 
		
			(error) => {
				console.log(error)
			}
			
		);
	
	};

	// Adiciona uma nova linha com o novo produto cadastrado
	addLine(dados) {
		
		let tr = document.createElement("tr");
		
		tr.innerHTML = `
					<td>${dados.codigo}</td>
					<td>${dados.descricao}</td>
					<td>${"R$ " + dados.preco_custo}</td>
					<td>${"R$ " + dados.preco_venda}</td>
		            <td>${dados.categoria}</td>
		            <td>${dados.cod_fornecedor}</td>
					<td>${dados.quantidade}</td>
		            <td>
		            <button type="button" class="btn btn-primary btn-edit btn-xs btn-flat" onclick="editaCadastro(${dados.id})">Editar</button>
	            	<button type="button" class="btn btn-danger btn-xs btn-flat" onclick="excluiCadastro(${dados.id})">Excluir</button>
		            </td> 
		`;
		
		
		tr.id = dados.id;
		
		tr.querySelector(".btn-edit").addEventListener('click', (event) => {
			this.mostraFormularioEdit();
		});
		
		this.tabelaProdutos.appendChild(tr);
		
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
        document.querySelector("#box-cadastro-produto").style.display = "block";
        document.querySelector("#box-edit-produto").style.display = "none";
    }

    mostraFormularioEdit() {
        document.querySelector("#box-cadastro-produto").style.display = "none";
        document.querySelector("#box-edit-produto").style.display = "block";
    }	
}

// Adiciona os dados no formulario de edição e altera os valores
function editaCadastro(idUsuario) {
	
	let formEdit = document.querySelector("#form-edit-produto");
	
	let id = formEdit.querySelector("#ident");

	id.value = idUsuario;
	
	formEdit.querySelector("#btnEditCancelar").addEventListener('click', () => {
		document.querySelector("#box-cadastro-produto").style.display = "block";
        document.querySelector("#box-edit-produto").style.display = "none";
	});
	
	requestAjax("ConsultarProduto", formEdit).then(
			
		(retorno) => {
			
			formEdit.querySelector("#EdCodigo").value = retorno.codigo
			formEdit.querySelector("#EdDescricao").value = retorno.descricao
			formEdit.querySelector("#EdPreco-custo").value = retorno.preco_custo
			formEdit.querySelector("#EdPreco-venda").value = retorno.preco_venda
			formEdit.querySelector("#EdCategoria").value = retorno.categoria
			formEdit.querySelector("#EdFornecedor").value = retorno.cod_fornecedor
			formEdit.querySelector("#EdQtd-estoque").value = retorno.quantidade
			
			let btn = formEdit.querySelector("#btnEditProduto");
			
			btn.addEventListener('click', (event) => {
				
				let modal = document.querySelector("#modalConfirm");
				modal.style.display = "block";
				modal.querySelector("#pModal").innerHTML = "Deseja alterar o produto?";
				
				let opSim = modal.querySelector("#btnModalSim");
				opSim.addEventListener('click', () => {
					
					var tabela = document.querySelector("#tabela-produtos");
					
					requestAjax("AlterarProduto", formEdit).then(
			
					(retorno) => {
						
						//adasdsadasd
						var linha = "";
						[...tabela.children].forEach( (tr) => {
							if (tr.id == idUsuario) {
								linha = tr;
								
								requestAjax("ConsultarProduto", formEdit).then(
			
									(retorno) => {
									
										[...linha.cells][0].innerHTML = retorno.codigo;
										[...linha.cells][1].innerHTML = retorno.descricao;
										[...linha.cells][2].innerHTML = retorno.preco_custo;
										[...linha.cells][3].innerHTML = retorno.preco_venda;
										[...linha.cells][4].innerHTML = retorno.categoria;
										[...linha.cells][5].innerHTML = retorno.cod_fornecedor;
										[...linha.cells][6].innerHTML = retorno.quantidade;
								
								   	}, 
								
									(error) => {
										console.log(error)
									}
			
								);
								
							}
						});
						
						
						formEdit.reset();
			   		}, 
			
					(error) => {
						console.log(error);
					}
			
				);
					
					modal.style.display = "none";
				});
				
				let opNao = modal.querySelector("#btnModalNao");
				opNao.addEventListener('click', () => {
					modal.style.display = "none";
				});
		
			});
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
	
	let opSim = modal.querySelector("#btnModalSim");
	opSim.addEventListener('click', () => {
	
		requestAjax("ExcluiProduto", formCad).then(
			
		(retorno) => {
			var tabela = document.querySelector("#tabela-produtos");
			var linha = "";
			[...tabela.children].forEach( (tr) => {
				if (tr.id == idUsuario) {
					linha = tr;
					linha.remove();
				}
			});
	   	}, 
	
		(error) => {
			console.log("=> ERRO");
			console.log(error);
		}
			
	);
		
		modal.style.display = "none"
		
	});
	
	let opNao = modal.querySelector("#btnModalNao");
	opNao.addEventListener('click', () => {
		modal.style.display = "none"
	});
	
}


