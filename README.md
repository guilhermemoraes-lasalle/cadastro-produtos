# Cadastro de Produtos — MVC, JPA, Hibernate e PostgreSQL

Aplicação Java de console construída conforme o roteiro do laboratório. Permite cadastrar, listar, consultar, alterar e excluir produtos.

## Tecnologias
- Java 21
- Maven
- Jakarta Persistence (JPA)
- Hibernate ORM
- PostgreSQL

## Regras implementadas
- ID gerado automaticamente.
- Nome obrigatório, com até 120 caracteres.
- Quantidade inteira maior ou igual a zero.
- Valor unitário positivo, com até duas casas decimais.
- Alteração e exclusão somente para produtos existentes.
- Exclusão exige confirmação na interface.
- Valores monetários usam `BigDecimal`.

## Configuração do banco
No PostgreSQL/pgAdmin, crie o banco:

```sql
CREATE DATABASE db_produtos;
```

Abra `src/main/resources/META-INF/persistence.xml` e substitua `SUA_SENHA` pela senha local do usuário `postgres`. Não publique sua senha no GitHub.

A tabela `produtos` é criada/atualizada pelo Hibernate na primeira execução.

## Importar no Eclipse
1. Extraia o ZIP.
2. Acesse **File > Import > Maven > Existing Maven Projects**.
3. Selecione a pasta `cadastro-produtos`.
4. Finalize e aguarde o Maven baixar as dependências.
5. Se necessário, clique com o botão direito no projeto > **Maven > Update Project**.
6. Confira se o PostgreSQL está ativo e se o banco `db_produtos` existe.
7. Execute `br.edu.exemplo.app.Main` com **Run As > Java Application**.

## Testes do roteiro
- Inclusão: Notebook, quantidade 10, valor 3500.00.
- Listagem: Mouse, quantidade 50, valor 75.00.
- Consulta: ID existente e inexistente.
- Alteração: Mouse para Mouse sem fio, quantidade 40, valor 95.00.
- Exclusão: confirmar com S.
- Validação: nome vazio, quantidade -1 e valor 0.
- Entrada inválida: letras no campo quantidade.

## Conferir no pgAdmin

```sql
SELECT id, nome, quantidade, valor_unitario
FROM produtos
ORDER BY id;

SELECT SUM(quantidade * valor_unitario) AS total_estoque
FROM produtos;
```

## Estrutura
- `model`: entidade Produto.
- `dao`: operações de persistência.
- `controller`: regras de negócio e validações.
- `view`: menu de console e leitura de entradas.
- `util`: fábrica e obtenção dos EntityManagers.
- `app`: classe principal.
