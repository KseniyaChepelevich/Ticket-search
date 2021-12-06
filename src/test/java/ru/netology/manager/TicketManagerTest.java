package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.domain.TicketByTravelTimeComparator;
import ru.netology.repository.TicketRepository;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;


class TicketManagerTest {
    private TicketRepository repository = new TicketRepository();
    private TicketManager manager = new TicketManager(repository);

    private Ticket fromMoscowToPiterMorningSVOLED = new Ticket(1, 1200, "SVO", "LED", 75);
    private Ticket fromMoscowToPiterMorningVKOLED = new Ticket(2, 1500, "VKO", "LED", 90);
    private Ticket fromMoscowToPiterMorningDMELED = new Ticket(3, 1750, "DME", "LED", 110);
    private Ticket fromMoscowToPiterEveningDMELED = new Ticket(4,2250, "DME", "LED", 100);
    private Ticket fromMoscowToPiterEveningDMELED2 = new Ticket(14, 2700, "DME", "LED", 120);
    private Ticket fromMoscowToPiterEveningDMELED3 = new Ticket(16, 2700, "DME", "LED", 100);
    private Ticket fromMoscowToPiterEveningDMELED4 = new Ticket(17, 2250, "DME", "LED", 130);
    private Ticket FromMoscowToPiterEveningVKOLED = new Ticket(5, 2000, "VKO", "LED", 90);
    private Ticket FromMoscowToKazanMorningVKOLED = new Ticket(6, 2600, "VKO", "KZN", 95);
    private Ticket FromMoscowToKazanMorningSVOKZN = new Ticket(7, 3700, "SVO", "KZN", 600);
    private Ticket FromMoscowToKazanEveningSVOKZN = new Ticket(8, 2300, "SVO", "KZN", 100);
    private Ticket fromPiterToMoscowMorningLEDVKO = new Ticket(9, 1500, "LED", "VKO", 90);
    private Ticket fromPiterToMoscowEveningLEDSVO = new Ticket(10, 1800, "LED", "SVO", 85);
    private Ticket fromPiterToMoscowEveningLEDVKO = new Ticket(11, 1900, "LED", "VKO", 90);
    private Ticket fromPiterToMoscowEveningLEDDME = new Ticket(12, 2500, "LED", "DME", 90);
    private Ticket fromPiterToMoscowMorningLEDDME = new Ticket(13, 2700, "LED", "DME", 80);
    private Ticket FromMoscowToKazanMorningDMEKZN = new Ticket(15, 2000, "DME", "KZN", 120);
    private Comparator<Ticket> TicketByTravelTimeComparator;


    @Test
    public void shouldSearch3TicketsOutOf5WithTheSameAirportNames() {
        String fromAirport = "DME";
        String toAirport = "LED";

        manager.add(fromPiterToMoscowMorningLEDDME);
        manager.add(fromMoscowToPiterEveningDMELED2);
        manager.add(fromPiterToMoscowEveningLEDDME);
        manager.add(fromMoscowToPiterMorningDMELED);
        manager.add(fromMoscowToPiterEveningDMELED);




        Ticket[] expected = new Ticket[]{fromMoscowToPiterMorningDMELED, fromMoscowToPiterEveningDMELED, fromMoscowToPiterEveningDMELED2};
        Ticket[] actual = manager.search(fromAirport, toAirport);



        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearch3TicketsOutOf7() {
        String fromAirport = "DME";
        String toAirport = "LED";

        manager.add(fromMoscowToPiterMorningSVOLED);
        manager.add(fromMoscowToPiterMorningDMELED);
        manager.add(fromMoscowToPiterEveningDMELED);
        manager.add(fromMoscowToPiterEveningDMELED2);
        manager.add(fromPiterToMoscowEveningLEDDME);
        manager.add(fromPiterToMoscowMorningLEDDME);
        manager.add(FromMoscowToKazanMorningDMEKZN);

        Ticket[] expected = new Ticket[]{fromMoscowToPiterMorningDMELED, fromMoscowToPiterEveningDMELED, fromMoscowToPiterEveningDMELED2};
        Ticket[] actual = manager.search(fromAirport, toAirport);



        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearch1TicketsOutOf7(){
        String fromAirport = "SVO";
        String toAirport = "LED";

        manager.add(fromMoscowToPiterMorningSVOLED);
        manager.add(fromMoscowToPiterMorningDMELED);
        manager.add(fromMoscowToPiterEveningDMELED);
        manager.add(fromMoscowToPiterEveningDMELED2);
        manager.add(fromPiterToMoscowEveningLEDDME);
        manager.add(fromPiterToMoscowMorningLEDDME);
        manager.add(FromMoscowToKazanMorningDMEKZN);

        Ticket[] expected = new Ticket[]{fromMoscowToPiterMorningSVOLED};
        Ticket[] actual = manager.search(fromAirport, toAirport);



        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTicketsThatAreNot() {
        String fromAirport = "SVO";
        String toAirport = "KZN";

        manager.add(fromMoscowToPiterMorningSVOLED);
        manager.add(fromMoscowToPiterMorningDMELED);
        manager.add(fromMoscowToPiterEveningDMELED);
        manager.add(fromMoscowToPiterEveningDMELED2);
        manager.add(fromPiterToMoscowEveningLEDDME);
        manager.add(fromPiterToMoscowMorningLEDDME);
        manager.add(FromMoscowToKazanMorningDMEKZN);

        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.search(fromAirport, toAirport);



        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearch4TicketsOutOf7WithTheSameAirportNamesAndSortByTravelTime() {
        String fromAirport = "DME";
        String toAirport = "LED";

        manager.add(fromPiterToMoscowMorningLEDDME);
        manager.add(fromMoscowToPiterEveningDMELED2);
        manager.add(fromPiterToMoscowEveningLEDDME);
        manager.add(fromMoscowToPiterMorningDMELED);
        manager.add(fromMoscowToPiterEveningDMELED);
        manager.add(fromMoscowToPiterEveningDMELED3);
        manager.add(fromMoscowToPiterEveningDMELED4);


        Ticket[] expected = new Ticket[]{ fromMoscowToPiterEveningDMELED, fromMoscowToPiterEveningDMELED3, fromMoscowToPiterMorningDMELED, fromMoscowToPiterEveningDMELED2, fromMoscowToPiterEveningDMELED4 };
        Ticket[] actual = manager.findAll(fromAirport, toAirport, TicketByTravelTimeComparator);




        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearch2TicketsOutOf3WithTheSameAirportNamesAndSortByTravelTime() {
        String fromAirport = "DME";
        String toAirport = "LED";

        manager.add(fromPiterToMoscowMorningLEDDME);
        manager.add(fromMoscowToPiterEveningDMELED2);
        manager.add(fromMoscowToPiterMorningDMELED);



        Ticket[] expected = new Ticket[]{ fromMoscowToPiterMorningDMELED, fromMoscowToPiterEveningDMELED2};
        Ticket[] actual = manager.findAll(fromAirport, toAirport, TicketByTravelTimeComparator);




        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearch1TicketsOutOf1WithTheSameAirportNamesAndSortByTravelTime() {
        String fromAirport = "DME";
        String toAirport = "LED";


        manager.add(fromMoscowToPiterEveningDMELED2);


        Ticket[] expected = new Ticket[]{ fromMoscowToPiterEveningDMELED2};
        Ticket[] actual = manager.findAll(fromAirport, toAirport, TicketByTravelTimeComparator);




        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearch1TicketsIfEmptyWithTheSameAirportNamesAndSortByTravelTime() {
        String fromAirport = "DME";
        String toAirport = "LED";




        Ticket[] expected = new Ticket[]{ };
        Ticket[] actual = manager.findAll(fromAirport, toAirport, TicketByTravelTimeComparator);




        assertArrayEquals(expected, actual);
    }


}