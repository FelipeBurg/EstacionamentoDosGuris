package Service;

import Entity.Automovel;
import Entity.Estacionamento;
import Entity.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Aplicacao {
    private Scanner scanner = new Scanner(System.in);
    private Estacionamento estacionamento;

    public Aplicacao(Estacionamento estacionamento) {
        this.estacionamento = estacionamento; // Certifica-se de inicializar a variável
    }


    public void menu() {
        int opcao;
        do {
            System.out.println("1: Registrar Entrada");
            System.out.println("2: Registrar Saída");
            System.out.println("3: Imprimir Ticket");
            System.out.println("4: Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    registrarEntrada();
                    break;
                case 2:
                    registrarSaida();
                    break;
                case 3:
                    exibirInformacoesTicket();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 4);
    }

    // Método para registrar a entrada de um automóvel
    private void registrarEntrada() {
        System.out.println("Digite a placa do automóvel:");
        String placa = scanner.nextLine();
        Automovel automovel = estacionamento.buscarPorPlaca(placa);

        if (automovel == null) {
            System.out.println("O automóvel é VIP? (S/N):");
            boolean isVIP = scanner.nextLine().equalsIgnoreCase("S");

            automovel = new Automovel(placa, isVIP);
            Ticket novoTicket = new Ticket();
            automovel.addTicket(novoTicket);
            estacionamento.adicionarAutomovel(automovel);

            System.out.println("Entrada registrada com sucesso para o automóvel " + placa);
        } else {
            Ticket ultimoTicket = automovel.getTickets().get(automovel.getTickets().size() - 1);
            if (ultimoTicket.getHoraSaida() == null) {
                System.out.println("Este automóvel já está no estacionamento e ainda não saiu.");
            } else {
                Ticket novoTicket = new Ticket();
                automovel.addTicket(novoTicket);
                System.out.println("Nova entrada registrada para o automóvel " + placa);
            }
        }
    }

    // Método para registrar a saída de um automóvel
    private void registrarSaida() {
        System.out.println("Digite a placa do automóvel:");
        String placa = scanner.nextLine();
        Automovel automovel = estacionamento.buscarPorPlaca(placa);

        if (automovel == null) {
            System.out.println("Automóvel não encontrado.");
            return;
        }

        Ticket ultimoTicket = automovel.getTickets().get(automovel.getTickets().size() - 1);
        if (ultimoTicket.getHoraSaida() != null) {
            System.out.println("Este automóvel já saiu do estacionamento.");
            return;
        }

        // Ler o horário de saída
        LocalDateTime horaSaida = lerDataHoraManual();

        // Valida se a hora de saída não é antes da hora de entrada
        if (horaSaida.isBefore(ultimoTicket.getHoraEntrada())) {
            System.out.println("Horário de saída não pode ser antes do horário de entrada.");
            return;
        }

        // Registra a saída
        ultimoTicket.setHoraSaida(horaSaida);
        Duration duracao = Duration.between(ultimoTicket.getHoraEntrada(), ultimoTicket.getHoraSaida());
        ultimoTicket.setTempoEstadia(duracao);

        // Cálculo do valor do ticket (lógica simplificada, ajuste conforme suas regras)
        double valor = calcularValor(duracao, automovel.isVIP());
        ultimoTicket.setValorTicket(valor);

        exibirInformacoesTicket();
    }


    // Método para exibir as informações do ticket de um automóvel
    private void exibirInformacoesTicket() {
        System.out.println("Digite a placa do automóvel:");
        String placa = scanner.nextLine();
        Automovel automovel = estacionamento.buscarPorPlaca(placa);

        if (automovel == null) {
            System.out.println("Automóvel não encontrado.");
            return;
        }

        Ticket ultimoTicket = automovel.getTickets().get(automovel.getTickets().size() - 1);
        System.out.println("Código do Ticket: " + ultimoTicket.getCodigo());
        System.out.println("Hora de Entrada: " + ultimoTicket.getHoraEntrada());
        if (ultimoTicket.getHoraSaida() != null) {
            System.out.println("Hora de Saída: " + ultimoTicket.getHoraSaida());
            System.out.println("Valor a Pagar: R$ " + ultimoTicket.getValorTicket());
        } else {
            System.out.println("O automóvel ainda está no estacionamento.");
        }
    }

    // Método para calcular o valor do ticket
    private double calcularValor(Duration duracao, boolean isVIP) {
        long minutos = duracao.toMinutes();
        double valor;

        if (minutos <= 15) {
            valor = 0.0;  // 15 minutos de cortesia
        } else if (minutos <= 60) {
            valor = 5.90;  // Até 1 hora
        } else {
            long horas = duracao.toHours();
            valor = 5.90 + (horas - 1) * 2.50;  // Após 1 hora, R$ 2,50 por hora adicional
        }

        if (isVIP) {
            valor *= 0.5;  // Desconto VIP de 50%
        }

        return valor;
    }

    public LocalDateTime lerDataHoraManual() {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime dataHoraSaida = null;

        while (dataHoraSaida == null) {
            try {
                System.out.print("Coloque a data de saída (dd/MM/yyyy): ");
                String data = scanner.nextLine();

                System.out.print("Coloque o horário de saída (HH:mm): ");
                String horario = scanner.nextLine();

                String dataHoraString = data + " " + horario;

                // Converte a string em LocalDateTime usando o formatador
                dataHoraSaida = LocalDateTime.parse(dataHoraString, formatter);
            } catch (Exception e) {
                System.out.println("Formato inválido. Tente novamente.");
            }
        }

        return dataHoraSaida;
    }
}
