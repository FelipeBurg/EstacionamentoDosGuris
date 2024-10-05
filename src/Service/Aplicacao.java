package Service;

import Entity.Automovel;
import Entity.Estacionamento;
import Entity.Ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Aplicacao {
    private Scanner scanner = new Scanner(System.in);
    private Estacionamento estacionamento;
    public Aplicacao() {
        estacionamento = new Estacionamento();
    }
    public void menu() {
        System.out.println("Bem-vindo ao Estacionamento dos Guris");
        System.out.println("1. Registrar entrada");
        System.out.println("2. Registrar saída");
        System.out.println("4. Listar entradas");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public void registrarEntrada() {
        System.out.println("Digite a placa: ");
        String placa = scanner.nextLine();
        if (estacionamento.buscarAutomovelPorPlaca(placa) != null) {
            Automovel automovel = estacionamento.buscarAutomovelPorPlaca(placa);
            Ticket ticket = new Ticket();
            automovel.addTicket(ticket);

        }else {
            System.out.println("Sem cadastro ainda\n" +
                    "Deseja ser VIP (true/false)");
            Boolean isVip  = scanner.nextBoolean();
            Automovel automovel = new Automovel(placa, isVip);
            estacionamento.adicionarAutomovel(automovel);
            Ticket ticket = new Ticket();
            automovel.addTicket(ticket);
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

                // Converte a string em LocalDateTime usando o formatador
                dataHoraSaida = LocalDateTime.parse(dataHoraString, formatter);
            } catch (Exception e) {
                System.out.println("Formato inválido. Tente novamente.");
            }
        }

        return dataHoraSaida;
    }

}
