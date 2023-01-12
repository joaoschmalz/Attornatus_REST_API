# Attornatus_REST_API

Esta API deve permiti:  
•	Criar uma pessoa
•	Editar uma pessoa
•	Consultar uma pessoa
•	Listar pessoas
•	Criar endereço para pessoa
•	Listar endereços da pessoa
•	Informar qual endereço é o principal da pessoa
•	Deletar uma pessoa

Uma Pessoa possui os seguintes campos:  
•	Nome
•	Data de nascimento
•	Endereço:
  o	Logradouro
  o	CEP
  o	Número
  o	Cidade

-----

ENDPOINT criação de uma nova pessoa -> POST /person
JSON modelo criação de pessoa:
{
    "name": "João",
    "birthdate": "1995-18-15T03:00:00.000+00:00",
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
-----
ENDPOINT editar uma pessoa -> PUT /person/{id}
Obs. Visto que existe um método específico para o cadastro/alteração de endereço, este endpoint não considera alteração de endereço.
JSON modelo alteração de pessoa
{
    "name": "João",
    "birthdate": "1995-18-15T03:00:00.000+00:00",
}
-----
ENDPOINT consultar uma pessoa -> GET /person/{id}
-----
ENDPOINT consultar todos -> GET /person
-----
ENDPOINT cadastrar novo endereço -> PUT /person/{id}/add-new-address
JSON modelo novo endereço:

{
    "street": "Rua das Orquídeas",
    "zipCode": "89204003",
    "number": 304,
    "city": "Joinville",
    "mainAddress": true
}
-----
ENDPOINT listar todos os endereços de uma pessoa -> GET /person/{id}/address
-----
ENDPOINT informar o endereço principal -> PUT /person/{id}/main-address/{addressId}
-----
ENDPOINT deletar uma pessoa -> DELETE /person/{id}
