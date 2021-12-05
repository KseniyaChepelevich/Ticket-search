package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class TicketManager {
    private TicketRepository repository = new TicketRepository();


    public TicketManager(TicketRepository repository) { this.repository = repository;}




    public void add(Ticket ticket) {
        repository.save(ticket);
    }

    public Ticket[] getAll() {
        return repository.findAll();
    }


    public Ticket[] search(String from, String to) {
        Ticket[] result = new Ticket[0];
        for(Ticket ticket : repository.findAll()) {
            if(ticket.getDepartureAirport().equals(from) && ticket.getArrivalAirport().equals(to)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
                Arrays.sort(result);
            }

        }
        return result;
    }











}
