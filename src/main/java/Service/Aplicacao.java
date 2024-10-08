package Service;

import Entity.Automovel;
import Entity.Estacionamento;
import Entity.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Aplicacao {
    private Scanner scanner = new Scanner(System.in);
    private Estacionamento estacionamento;
    private CalculoTarifa calculoTarifa = new CalculoTarifa(); // Instância de Service.CalculoTarifa

    public Aplicacao(Estacionamento estacionamento) {
        this.estacionamento = estacionamento; // Certifica-se de inicializar a variável
    }

    public void menu() {
        int opcao = -1;
        do {
            try {
                System.out.println("1: Registrar Entrada");
                System.out.println("2: Registrar Saída");
                System.out.println("3: Imprimir Ticket");
                System.out.println("4: Exibir Carros");
                System.out.println("5: Sair");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        registrarEntrada();
                        break;
                    case 2:
                        registrarSaida();
                        break;
                    case 3:
                        exibirInformacoesCarro();
                        break;
                    case 4:
                        exibirCarros();
                        break;
                    case 5:
                        System.out.println("Saindo do sistema...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, insira um número válido.");
                scanner.nextLine();
            }
        } while (opcao != 5);
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
        LocalDateTime horaSaida = lerDataHoraManual();

        // Definir a hora de saída no último ticket
        ultimoTicket.setHoraSaida(horaSaida);
        Duration duracao = Duration.between(ultimoTicket.getHoraEntrada(), ultimoTicket.getHoraSaida());
        ultimoTicket.setTempoEstadia(duracao);

        // Chama o método de cálculo de tarifa na classe Service.CalculoTarifa
        double valor = calculoTarifa.calcularValor(duracao, ultimoTicket.getHoraEntrada(), horaSaida, automovel.isVIP());
        ultimoTicket.setValorTicket(valor);

        System.out.println("Saída registrada com sucesso");
        try {
            System.out.println("Imprimir Entity.Ticket? (s/n)");
            String option = scanner.nextLine().trim().toLowerCase();

            if (option.equals("s")) {
                exibirInformacoesTicket(placa);
            } else if (option.equals("n")) {
                System.out.println("Volte sempre!!");
            } else {
                throw new IllegalArgumentException("Opção inválida. Por favor, insira 's' para sim ou 'n' para não.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    private void exibirInformacoesTicket(String placa) {
        Automovel automovel = estacionamento.buscarPorPlaca(placa);

        if (automovel == null) {
            System.out.println("Automóvel não encontrado.");
            return;
        }
        Ticket ultimoTicket = automovel.getTickets().get(automovel.getTickets().size() - 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("====== ENTRADA =======");
        System.out.println("Data: " + ultimoTicket.getHoraEntrada().format(formatter));
        System.out.println("Horário: " + ultimoTicket.getHoraEntrada().format(timeFormatter));

        if (ultimoTicket.getHoraSaida() != null) {
            System.out.println("====== SAÍDA ========");
            System.out.println("Data: " + ultimoTicket.getHoraSaida().format(formatter));
            System.out.println("Horário: " + ultimoTicket.getHoraSaida().format(timeFormatter));
            System.out.println("====== VALOR ========");
            System.out.println("Valor a Pagar: R$ " + ultimoTicket.getValorTicket() + "\n");
        } else {
            System.out.println("O automóvel ainda está no estacionamento.");
        }
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

                dataHoraSaida = LocalDateTime.parse(dataHoraString, formatter);
            } catch (Exception e) {
                System.out.println("Formato inválido. Tente novamente.");
            }
        }

        return dataHoraSaida;
    }

    public void exibirInformacoesCarro() {
        System.out.println("Digite a placa do carro:");
        String placa = scanner.nextLine();
        Automovel automovel = estacionamento.buscarPorPlaca(placa);
        if (automovel == null) {
            System.out.println("Não encontrado.");
        } else {
            if (automovel.isVIP()) {
                System.out.println(automovel.getPlaca());
                System.out.println("VIP");
                for (int i = 0; i < automovel.getTickets().size(); i++) {
                    System.out.println(automovel.getTickets().get(i).getCodigo() + " - " + automovel.getTickets().get(i).getHoraSaida());
                }
            }
        }
    }

    public void exibirCarros() {
        ArrayList<Automovel> autos = estacionamento.getAutomoveis();
        for (Automovel automovel : autos) {
            System.out.println(automovel.getPlaca());
        }
    }
}
