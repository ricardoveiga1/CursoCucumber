#language: pt

@ignore
Funcionalidade: Aprender Cucumber
	Como um aluno
	Eu quero aprender a utilizar Cucumber
	Para que eu possa automatizar critérios de aceitação

Cenário: Deve executar especificação
	Dado que criei o arquivo corretamente
	Quando executá-lo
	Então a especificação deve finalizar com sucesso

@contador
Cenário: Deve incrementar contador
	Dado que valor do contador é 15
	Quando eu incrementar em 3
	Então o valor do contador será 18

@contador
Cenário: Deve incrementar contador
	Dado que valor do contador é 123
	Quando eu incrementar em 35
	Então o valor do contador será 158

@tipo2
Cenário: Deve calcular atraso na entrega
	Dado que a entrega é dia 05/04/2021
	Quando a entrega atrasar em 2 dias
	Então a entrega será efetuada em 07/04/2021

@tipo1 @tipo2
Cenário: Deve calcular atraso na entrega
	Dado que a entrega é dia 05/04/2021
	Quando a entrega atrasar em 2 meses
	Então a entrega será efetuada em 05/06/2021




