package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Objects;

public class TicketManager {
    private TicketRepository repository = new TicketRepository();


    public TicketManager(TicketRepository repository) { this.repository = repository;}

   // public TicketManager() {

   // }

    public void add(Ticket ticket) {
        repository.save(ticket);
    }

    public Ticket[] getAll() {
        return repository.findAll();
    }


    public Ticket[] search(String from, String to) {
        Ticket[] result = new Ticket[0];
        for(Ticket ticket : repository.findAll()) {
            if(ticket.getDepartureAirport() == from && ticket.getArrivalAirport() == to) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }

        }
        return result;
    }


        //Ticket[] result = new Ticket[0];
      //  Ticket[] resultFrom = new Ticket[0];
        //return false;
       // for (Ticket ticket : repository.findAll()) {
        //    if (Objects.equals(ticket.getDepartureAirport(), from)) {

        //        Ticket[] tmp1 = new Ticket[resultFrom.length + 1];
         //       System.arraycopy(resultFrom, 0, tmp1, 0, resultFrom.length);
         //       tmp1[tmp1.length - 1] = ticket;
         //       resultFrom = tmp1;
         //       if (Objects.equals(ticket.getArrivalAirport(), to)) {
          //          Ticket[] tmp2 = new Ticket[resultFrom.length + 1];
          //          System.arraycopy(resultFrom, 0, tmp2, 0, resultFrom.length);
          //          tmp2[tmp2.length - 1] = ticket;
           //         result = tmp2;
          //      }
          //  }
      //  return result;






}
