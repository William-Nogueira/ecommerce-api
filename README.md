## E-Commerce API

API REST para um e-commerce de tecnologia desenvolvida utilizando Spring Boot 3 e MySQL. Permite que o usuário acesse e gerencie informações sobre produtos a venda, incluindo consultas com filtragem e paginação, criação, atualização e exclusão de produtos (CRUD completo). Hospedado em servidores cloud.

Live - https://ecommerce-api-build.up.railway.app/api/produtos

---
# Documentação da API

## Base URL

A base URL para todas as solicitações é:

```assembly
https://ecommerce-api-build.up.railway.app/api/produtos
```

## Endpoints Disponíveis

### Listar Todos os Produtos

#### Endpoint

```http
GET /api/produtos
```

Retorna uma lista de todos os produtos disponíveis.

#### Parâmetros de Solicitação com Paginação

- `page` - Número da página desejada (padrão é 0).
- `size` - Tamanho da página (padrão é 100).

#### Exemplo de Solicitação com Paginação

```http
GET /api/produtos?page=0&size=10
```
#
### Consultar Produto por ID

#### Endpoint

```http
GET /api/produtos/{id}
```

Retorna informações sobre um produto específico com base no ID fornecido.

#### Exemplo de Solicitação

```http
GET /api/produtos/8
```
#
### Consultar Produtos por Categoria

#### Endpoint

```http
GET /api/produtos/categoria/{categoria}
```

Retorna uma lista de produtos com base na categoria especificada.

#### Categorias Disponíveis

- COMPUTADOR
- NOTEBOOK
- SMARTPHONE
- MONITOR
- TECLADO
- MOUSE
- HEADSET
- PLACA_DE_VIDEO
- PROCESSADOR
- MEMORIA_RAM
- ARMAZENAMENTO
- OUTROS

#### Exemplo de Solicitação

```http
GET /api/produtos/categoria/outros
```
#
### Consultar Produtos por Marca

#### Endpoint

```http
GET /api/produtos/marca/{marca}
```

Retorna uma lista de produtos com base na marca especificada.

#### Exemplo de Solicitação

```http
GET /api/produtos/marca/samsung
```
#
### Criar um Novo Produto

#### Endpoint

```http
POST /api/produtos
```

Cria um novo produto com base nos dados fornecidos no corpo da solicitação.

#### Exemplo de Corpo da Solicitação

```json
{
  "categoria": "SMARTPHONE",
  "nome": "Samsung Galaxy S21",
  "marcaProduto": "Samsung",
  "preco": 899.99,
  "temDesconto": false,
  "precoComDesconto": null,
  "parcelas": 10,
  "freteGratis": true,
  "imgUrl": "https://images.pexels.com/photos/4549408/pexels-photo-4549408.jpeg"
}
```
#
### Atualizar um Produto Existente

#### Endpoint

```http
PUT /api/produtos/{id}
```

Atualiza um produto existente com base no ID fornecido e nos dados fornecidos no corpo da solicitação.

#### Exemplo de Corpo da Solicitação

```json
{
  "id": 6,
  "categoria": "PLACA_DE_VIDEO",
  "nome": "NVIDIA GeForce RTX 3080",
  "marcaProduto": "NVIDIA",
  "preco": 2799.99,
  "temDesconto": false,
  "precoComDesconto": null,
  "parcelas": 12,
  "freteGratis": false,
  "imgUrl": "https://images.pexels.com/photos/6716692/pexels-photo-6716692.jpeg"
}
```
#
### Excluir um Produto

#### Endpoint

```http
DELETE /api/produtos/{id}
```

Exclui um produto existente com base no ID fornecido.

#
## Respostas da API

A API retornará respostas com os seguintes códigos de status:

- `200 OK`: A solicitação foi bem-sucedida.
- `201 Created`: Um novo recurso foi criado com sucesso (usado para POST).
- `204 No Content`: A solicitação de exclusão foi bem-sucedida (usado para DELETE).
- `400 Bad Request`: A solicitação foi malformada ou contém dados inválidos.
- `404 Not Found`: O recurso solicitado não foi encontrado.
- `500 Internal Server Error`: O servidor encontrou um erro interno.
---
## Contato

Se você tiver alguma dúvida, sugestão ou encontrar algum problema com esta API, sinta-se à vontade para entrar em contato via LinkedIn - <a href="https://www.linkedin.com/in/william-nogueira-dev" target="_blank">Clique Aqui