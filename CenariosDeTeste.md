## Cenários de teste
## Teste E2E Para Locatorio

- CT001 - Deve retornar 201 ao criar um locatário
- CT002 - Deve retornar 200 ao buscar um locatário
- CT003 - Deve retornar 200 ao atualizar um locatário
- CT004 - Deve retornar 200 ao deletar um locatário

## Descrição

CT001 - Deve retornar 201 ao criar um locatário

Descrição: Verifica se a API retorna o status 201 (Created) ao criar um novo locatário.

CT002 - Deve retornar 200 ao buscar um locatário

Descrição: Verifica se a API retorna o status 200 (OK) ao buscar um locatário existente pelo seu ID.

CT003 - Deve retornar 200 ao atualizar um locatário

Descrição: Verifica se a API retorna o status 200 (OK) ao atualizar as informações de um locatário existente.

CT004 - Deve retornar 200 ao deletar um locatário

Descrição: Verifica se a API retorna o status 200 (OK) ao deletar um locatário existente pelo seu ID.



## Teste E2E Para Autor

- CT001 - Deve retornar 201 ao criar um autor
- CT002 - Deve retornar 200 ao buscar um autor pelo nome
- CT003 - Deve retornar 200 ao deletar um autor

## Descrição


CT001 - Deve retornar 201 ao criar um autor

Descrição: Verifica se a API retorna o status 201 (Created) ao criar um novo autor.

CT002 - Deve retornar 200 ao buscar um autor pelo nome

Descrição: Verifica se a API retorna o status 200 (OK) ao buscar um autor pelo nome.

CT003 - Deve retornar 200 ao deletar um autor

Descrição: Verifica se a API retorna o status 200 (OK) ao deletar um autor existente pelo seu ID.

## Teste E2E Para Livro

- CT001 - Deve retornar 201 ao criar um autor
- CT002 - Deve retornar 201 ao criar um livro
- CT003 - Deve retornar 200 ao buscar um livro pelo ID
- CT004 - Deve retornar 200 ao listar livros
- CT005 - Deve retornar 200 ao listar livros alugados
- CT006 - Deve retornar 200 ao deletar um livro

## Descrição

CT001 - Deve retornar 201 ao criar um autor

Descrição: Verifica se a API retorna o status 201 (Created) ao criar um novo autor para associar ao livro.

CT002 - Deve retornar 201 ao criar um livro

Descrição: Verifica se a API retorna o status 201 (Created) ao criar um novo livro no sistema.

CT003 - Deve retornar 200 ao buscar um livro pelo ID

Descrição: Verifica se a API retorna o status 200 (OK) ao buscar um livro pelo seu ID, validando seus detalhes.

CT004 - Deve retornar 200 ao listar livros

Descrição: Verifica se a API retorna o status 200 (OK) ao listar todos os livros cadastrados no sistema.

CT005 - Deve retornar 200 ao listar livros alugados

Descrição: Verifica se a API retorna o status 200 (OK) ao listar todos os livros que estão alugados.

CT006 - Deve retornar 200 ao deletar um livro

Descrição: Verifica se a API retorna o status 200 (OK) ao deletar um livro existente no sistema.

## Teste E2E Para Alugar

- CT001 - Deve retornar 201 ao criar um locatário
- CT002 - Deve retornar 201 ao criar um autor
- CT003 - Deve retornar 201 ao criar um livro
- CT004 - Deve retornar 201 ao criar um aluguel
- CT005 - Deve retornar 200 ao buscar um aluguel pelo ID
- CT006 - Deve retornar 200 ao listar aluguéis
- CT007 - Deve retornar 200 ao deletar um aluguel

## Descrição

CT001 - Deve retornar 201 ao criar um locatário

Descrição: Verifica se a API retorna o status 201 (Created) ao criar um novo locatário.

CT002 - Deve retornar 201 ao criar um autor

Descrição: Verifica se a API retorna o status 201 (Created) ao criar um novo autor.

CT003 - Deve retornar 201 ao criar um livro

Descrição: Verifica se a API retorna o status 201 (Created) ao criar um novo livro.

CT004 - Deve retornar 201 ao criar um aluguel

Descrição: Verifica se a API retorna o status 201 (Created) ao criar um novo aluguel.

CT005 - Deve retornar 200 ao buscar um aluguel pelo ID

Descrição: Verifica se a API retorna o status 200 (OK) ao buscar um aluguel pelo seu ID, validando seus detalhes.

CT006 - Deve retornar 200 ao listar aluguéis

Descrição: Verifica se a API retorna o status 200 (OK) ao listar todos os aluguéis cadastrados no sistema.

CT007 - Deve retornar 200 ao deletar um aluguel

Descrição: Verifica se a API retorna o status 200 (OK) ao deletar um aluguel existente no sistema.