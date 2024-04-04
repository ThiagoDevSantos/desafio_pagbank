//document.addEventListener('DOMContentLoaded', function () {
//    const veiculoForm = document.getElementById('veiculo-form');
//    const veiculosList = document.getElementById('veiculos-list');
//    const atualizarExcluirForm = document.getElementById('atualizar-excluir-form');
//
//    // Event listener para o envio do formulário de cadastro
//    veiculoForm.addEventListener('submit', function (event) {
//        event.preventDefault();
//
//        const formData = new FormData(veiculoForm);
//
//        const veiculo = {
//            marca: formData.get('marca'),
//            veiculo: formData.get('veiculo'),
//            anoFabricacao: formData.get('ano'),
//            cor: formData.get('cor'),
//            vendido: formData.get('vendido') === 'true'
//        };
//
//        cadastrarVeiculo(veiculo);
//    });
//
//    // Event listener para o envio do formulário de atualizar / excluir
//    atualizarExcluirForm.addEventListener('submit', function (event) {
//        event.preventDefault();
//        // Não precisa fazer nada aqui, pois já tratamos a atualização e exclusão diretamente nos botões
//    });
//
//    // Carregar a lista de veículos ao carregar a página
//    listarVeiculos();
//
//    // Função para cadastrar um veículo
//    function cadastrarVeiculo(veiculo) {
//        fetch('http://localhost:8080/veiculos', {
//            method: 'POST',
//            headers: {
//                'Content-Type': 'application/json'
//            },
//            body: JSON.stringify(veiculo)
//        })
//        .then(response => {
//            if (!response.ok) {
//                throw new Error('Erro ao cadastrar veículo');
//            }
//            return response.json();
//        })
//        .then(data => {
//            // Veículo cadastrado com sucesso, adicionar à lista de veículos
//            adicionarVeiculoNaLista(data);
//            // Limpar o formulário após o cadastro
//            veiculoForm.reset();
//        })
//        .catch(error => {
//            console.error('Erro ao cadastrar veículo:', error);
//        });
//    }
//
//    // Função para listar veículos
//    function listarVeiculos() {
//        fetch('http://localhost:8080/veiculos')
//        .then(response => response.json())
//        .then(veiculos => {
//            veiculos.forEach(veiculo => {
//                adicionarVeiculoNaLista(veiculo);
//            });
//        })
//        .catch(error => {
//            console.error('Erro ao listar veículos:', error);
//        });
//    }
//
//    // Função para adicionar um veículo à lista de veículos na tela
//   function adicionarVeiculoNaLista(veiculo) {
//       const listItem = document.createElement('li');
//       listItem.textContent = `${veiculo.marca} ${veiculo.veiculo} (${veiculo.anoFabricacao}) - ${veiculo.cor}`;
//       veiculosList.appendChild(listItem);
//
//       // Adicionar botões de atualização e exclusão
//       const updateButton = document.createElement('button');
//       updateButton.textContent = 'Atualizar';
//       updateButton.addEventListener('click', function() {
//           atualizarVeiculo(veiculo.id);
//       });
//       listItem.appendChild(updateButton);
//
//       const deleteButton = document.createElement('button');
//       deleteButton.textContent = 'Excluir';
//       deleteButton.addEventListener('click', function() {
//           excluirVeiculo(veiculo.id, listItem);
//       });
//       listItem.appendChild(deleteButton);
//   }
//
//    // Função para atualizar um veículo
//    function atualizarVeiculo(id) {
//        const formData = new FormData(atualizarExcluirForm);
//
//        const veiculoAtualizado = {
//            marca: formData.get('marca-atualizar'),
//            veiculo: formData.get('veiculo-atualizar'),
//            anoFabricacao: formData.get('ano-atualizar'),
//            cor: formData.get('cor-atualizar'),
//            vendido: formData.get('vendido-atualizar') === 'true'
//        };
//
//        // Verificar se é um PATCH ou PUT
//        const method = formData.has('marca-atualizar') ? 'PATCH' : 'PUT';
//
//        fetch(`http://localhost:8080/veiculos/${id}`, {
//            method: method,
//            headers: {
//                'Content-Type': 'application/json'
//            },
//            body: JSON.stringify(veiculoAtualizado)
//        })
//        .then(response => {
//            if (!response.ok) {
//                throw new Error('Erro ao atualizar veículo');
//            }
//            // Veículo atualizado com sucesso, recarregar a lista de veículos
//            veiculosList.innerHTML = '';
//            listarVeiculos();
//        })
//        .catch(error => {
//            console.error('Erro ao atualizar veículo:', error);
//        });
//    }
//
//    // Função para excluir um veículo
//    function excluirVeiculo(id, listItem) {
//        fetch(http://localhost:8080/veiculos/${id}, {
//            method: 'DELETE'
//        })
//        .then(response => {
//            if (!response.ok) {
//                throw new Error('Erro ao excluir veículo');
//            }
//            // Veículo excluído com sucesso, remover da lista de veículos na tela
//            listItem.remove();
//        })
//        .catch(error => {
//            console.error('Erro ao excluir veículo:', error);
//        });
//    }
//});