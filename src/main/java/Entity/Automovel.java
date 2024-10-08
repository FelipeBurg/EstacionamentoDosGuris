package Entity;

import java.util.ArrayList;
import java.util.List;

public class Automovel {
    private String placa;
    private boolean isVIP;
    private List<Ticket> tickets;

    public Automovel(String placa, boolean isVIP) {
        this.placa = placa;
        this.isVIP = isVIP;
        this.tickets = new ArrayList<Ticket>();
    }
    public String getPlaca() {
        return placa;
    }
    public boolean isVIP() {
        return isVIP;
    }
    public List<Ticket> getTickets() {
        return tickets;
    }
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}