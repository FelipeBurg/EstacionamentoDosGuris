# Sistema de Estacionamento - Verificação e Validação de Software

## Descrição do Projeto

Este projeto faz parte da disciplina de **Verificação e Validação de Software** e tem como objetivo implementar um sistema de controle de estacionamento pago para centros comerciais, utilizando **Java** e **JUnit**. O foco principal é definir e implementar um conjunto de casos de teste baseados em técnicas de **particionamento de equivalência** e **análise de valor limite**, a fim de verificar a aceitação ou rejeição das classes entregues.

## Regras de Negócio

O sistema de estacionamento funciona com base em cancelas e deve calcular o valor a ser pago conforme as seguintes regras:

- O estacionamento opera das 08:00 às 02:00.
- A tarifa é calculada com base no tempo de permanência e se o cliente é VIP.
- Regras de cálculo:
  - Cliente **VIP** tem 50% de desconto sobre o valor final da tarifa.
  - Todos os clientes têm **15 minutos de cortesia** (valor = 0).
  - Até **1 hora** de permanência, o valor é fixo de **R$5,90**.
  - Acima de **1 hora**, o valor é acrescido de **R$2,50** por hora adicional.
  - Saídas após as 08:00 de dias posteriores são cobradas como **pernoite**, cujo valor é de **R$50,00**.

## Funcionalidades

O sistema inclui as seguintes funcionalidades:

1. **Registrar Entrada**: Insere o automóvel no sistema e inicia a contagem de tempo.
2. **Registrar Saída**: Calcula o valor a ser pago com base no tempo de permanência.
3. **Imprimir Ticket**: Exibe as informações do ticket gerado para o veículo.
4. **Exibir Carros no Estacionamento**: Lista todos os automóveis que estão atualmente no estacionamento.

## Estrutura do Código

O projeto está organizado da seguinte forma:

- **Package `Entity`**:
  - `Automovel`: Representa o automóvel no estacionamento.
  - `Ticket`: Representa o ticket de entrada/saída do automóvel.
  - `Estacionamento`: Gerencia a lista de automóveis e tickets.

- **Package `Service`**:
  - `CalculoTarifa`: Responsável por calcular o valor do ticket com base nas regras de negócio.
  - `Aplicacao`: Classe principal do sistema que interage com o usuário e coordena as operações.

## Tecnologias Utilizadas

- **Java**
- **JUnit**: Para implementação e execução de testes.
- **Maven**: Gerenciamento de dependências.

## Integrantes

- **Felipe Burgdurf Seelend**
- **Victor Machado**
- **Lucas Albuquerque**
- **Pedro Henrique**
- **Cecilia Ricalcati**

