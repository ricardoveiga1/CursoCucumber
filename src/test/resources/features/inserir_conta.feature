#language: pt

@funcionais
Funcionalidade: Cadastro de contas
  Como um usuário
  Gostaria de cadastrar contas
  Para que eu possa distribuir meu dinheiro de uma forma mais organizada

#Background
Contexto:
  Dado que desejo adicionar uma conta

#no passo está buscando conta com aspas por conta da expressão regular
#MUDANÇA PARA CENÁRIO DECLARATIVO
Esquema do Cenário: Deve validar regras cadastro contas
  Quando adiciono a conta "<conta>"
  Então recebo a mensagem "<mensagem>"

  #se atentar a variável
Exemplos:
  | conta          | mensagem |
  | Conta de Teste | Conta adicionada com sucesso! |
  |                | Informe o nome da conta |
  | Conta de Teste | Já existe uma conta com esse nome! |