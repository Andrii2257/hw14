package org.example;

import org.example.crud.ClientCrudService;
import org.example.crud.PlanetCrudService;
import org.example.crud.TicketCrudService;
import org.example.migration.FlywayMigration;
import org.example.model.Client;
import org.example.model.Planet;
import org.example.model.Ticket;

import java.sql.SQLException;
import java.sql.Timestamp;

public class App {
    public static void main(String[] args) throws SQLException {
        FlywayMigration flywayMigration = new FlywayMigration();
        flywayMigration.migrate();


        ClientCrudService clientCrudService = new ClientCrudService();
        PlanetCrudService planetCrudService = new PlanetCrudService();
        TicketCrudService ticketCrudService = new TicketCrudService();


        Client client = clientCrudService.findById(3L);
        System.out.println("client = " + client);

        Planet fromPlanet = planetCrudService.findById("VEN");
        System.out.println("planet = " + fromPlanet);
        Planet toPlanet = planetCrudService.findById("SAT");
        System.out.println("planet = " + toPlanet);

        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);
        ticket.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        System.out.println("ticket = " + ticket);
        ticketCrudService.save(ticket);
        Long id = ticket.getId();
        ticket = ticketCrudService.findById(id);
        System.out.println("ticket = " + ticket);
        ticket.setClient(clientCrudService.findById(7L));
        ticketCrudService.update(ticket);
        ticket = ticketCrudService.findById(id);
        System.out.println("ticket = " + ticket);

        ticketCrudService.delete(ticket);
        ticket = ticketCrudService.findById(id);
        System.out.println("ticket = " + ticket);

    }
}
