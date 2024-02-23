# Se guiando

O primeiro tópico abaixo desse é o de Diagrama de Classes deste projeto. Abaixo os tópicos estão divididos por Packages que são subdivididos pelas classes que estão dentro desses Packages e abaixo das classes seus métodos e atributos.


# Diagrama de Classes

<img src="/img/ClassDiagram.jpg" />

# Banco

## VaiNuBank

Esta é uma classe que funciona como um banco de dados para o banco. Aqui é onde fica armazenada a lista estática de todas as contas cadastradas no banco. Existem três métodos nessa classe, são eles:

### * add

Este método recebe como parâmetro uma classe do tipo Conta e verifica se esta conta já está cadastrada no banco de dados. Caso não esteja, ela é adicionada ao banco de dados.

### * remove

Este método recebe como parâmetro uma classe do tipo Conta e verifica se esta conta já está cadastrada no banco de dados. Caso esteja, ela é removida do banco de dados.

### * getContas

Este método não tem nenhum parâmetro, ele apenas retorna a lista de contas ja cadastradas no banco de dados.


# Contas

## IConta

Esta é uma Interface para a classe conta, contendo os métodos: saque, depósito e transferência. Os dois primeiros métodos recebem como parâmetro um double e retornam um double. Ja o método transferência recebe um double, uma String e mais outra String, retornando um double.

## Conta

Classe que implementa a interface IConta. Os parâmetros para criar uma conta são: 

* String agenciaNum -> Número de 3 digitos que irá representar o número de agência da conta
* String nomeTitular -> Nome do titular da conta
* String final cpf -> Número de CPF do titular da conta que não pode ser alterado
* double valorAtual -> Valor atual do saldo em conta

Abaixo seguem os métodos de Conta: 

### saque

Método vindo de IConta. Aqui é verificado se o valor do parâmetro é no mínimo 00.01 e se há saldo suficiente na conta para que possa se sacar a quantidade de dinheiro solicitada pelo parâmetro do método. Caso passe pelas verificações, será subtraído esse valor do salto da conta e será retornado o valor sacado. Caso não passe, será disparada uma mensagem de erro e será retornado -1.

### deposito

Método vindo de IConta. Aqui é verificado se o valor do parâmetro é no mínimo 00.01. Caso seja, o valor passado pelo parâmetro do método será adicionado ao saldo da conta e será retornado o valor depositado. Caso não, será disparada uma mensagem de erro e será retornado -1.

### transferencia

Método vindo de IConta. Aqui existem quatro verificações para que o método possa ser executado corretamente, são elas: 

1. Verificar se o valor do parâmetro transferencia é maior que 00.01
2. Verificação para saber se o usuário não está tentando transferir dinheiro para a própria conta que está transferindo dinheiro
3. Verificar se o usuário para o qual está sendo transferido dinheiro realmente existe no banco de dados
4. Verificar se o valor atual da conta é maior ou igual ao valor sendo transferido

Caso passe por todas essas verificações, o dinheiro será transferido para a conta de transferência e será retornado o valor de transferência. Caso não passe, será disparada uma mensagem de erro e retornado -1.

### toString

Método para mostrar uma representação da conta em formato de String.

## ContaCorrente

Essa classe herda da classe Conta, porém com a adição do atributo limiteCred, do tipo double,  que representa o limite de crédito que a conta tem.

## ContaPoupanca

Essa classe herda da classe Conta, porém com a adição do atributo aniversario, do tipo LocalDate, que representa a data de nascimento do titular da conta. Também há um @Override no método saque, que aqui ele retorna o valor de saque com desconto de %2 do valor solicitado para sacar.


# Erros

Aqui existem 3 classes personalizadas para erros. São elas: 

1. ErrorDeposito -> erros relacionados a depósito
2. ErrorSaque - > erros relacionados à saque
3. ErrorTransferencia -> erros relacionados à transferência

Todas herdam de Exception.


# Validador

Aqui há a classe CpfUtils, classe que tem o principal método isValid, que retorna um boolean. Servindo para identificar se um CPF é válido ou não.