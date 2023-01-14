## Attornatus_REST_API

### Esta API permite:

* Criar uma pessoa
* Editar uma pessoa
* Consultar uma pessoa
* Listar todas as pessoas
* Cadastrar novo endereço de uma pessoa
* Listar todos os endereços de uma pessoa
* Editar endereço principal de uma pessoa
* Deletar uma pessoa

### Uma Pessoa possui os seguintes campos:

* Nome
* Data de Nascimento
* Endereço
    * Cidade
    * Logradouro
    * CEP
     * Número
     * Cidade
-----

### Tabela ENDPOINTS

| AÇÃO                                    | METODO | ROTA                                  |
|-----------------------------------------|--------|---------------------------------------|
| Criar uma pessoa                        | POST   | /person                               |
| Editar uma pessoa                       | PUT    | /person/{id}                          |
| Consultar uma pessoa                    | GET    | /person{id}                           |
| Listar todas as pessoas                 | GET    | /person                               |
| Cadastrar novo endereço de uma pessoa   | POST   | /person/{id}/add-new-address          |
| Listar todos os endereços de uma pessoa | GET    | /person/{id}/address                  |
| Editar edereço principal de uma pessoa  | PUT    | /person/{id}/main-address/{addressId} |
| Deletar uma pessoa                      | DELETE | /person/{id}                          |

-----
### Criar uma pessoa

JSON entrada:

````
{
   "name": "João",
    "birthdate": "1995-18-15",
    "address": [
    {
        "street": "Rua das Orquídeas",
        "zipCode": "89204003",
        "number": 304,
        "city": "Joinville",
        "mainAddress": true
        }
    ]
}
````

**SUCESSO** - HTTP STATUS 201

RETORNA a URI de consulta da pessoa criada {baseURL}/person/3

-----
### EDITAR UMA PESSOA
**Obs.** Visto que existe um método específico para o cadastro/alteração de endereço, este endpoint não considera alteração de endereço.
JSON modelo alteração de pessoa

````
{
    "name": "João",
    "birthdate": "1995-18-15",
}
````
**SUCESSO** - HTTP STATUS 200

Retorna o objeto completo da pessoa alterada

````
{
    "id": 2,
    "name": "José",
    "birthdate": "2005-07-02T00:00:00.000+00:00",
    "address": [
        {
            "id": 3,
            "street": "Rua dos Turcos",
            "zipCode": "89205786",
            "number": 567,
            "city": "Joinville",
            "mainAddress": true
        }
    ]
}
````

-----
### CADASTRAR NOVO ENDEREÇO DE UMA PESSOA

Modelo novo endereço:

````
{
    "street": "Rua das Orquídeas",
    "zipCode": "89204003",
    "number": 304,
    "city": "Joinville",
    "mainAddress": true
}
````

**RETORNO** - Objeto do novo endereço cadastrado
````
{
    "id": 5,
    "street": "Teste",
    "zipCode": "89204004",
    "number": 2388,
    "city": "Joinville",
    "mainAddress": false
}
````
