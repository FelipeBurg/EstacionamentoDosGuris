package Entity;

import java.util.ArrayList;
import java.util.List;

public class Automovel {
    private String placa;
    private boolean isVip;
    private List<Ticket> tickets;

    public Automovel(String placa, boolean isVip) {
        this.placa = placa;
        this.isVip = isVip;
        this.tickets = new ArrayList<Ticket>();
    }
    public String getPlaca() {
        return placa;
    }
    public boolean isVip() {
        return isVip;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
    public Ticket ultimoTicket() {
        return tickets.get(tickets.size() - 1);
    }
}
