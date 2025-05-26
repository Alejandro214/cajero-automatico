package org.cajero.automatico;

import org.cajero.automatico.service.impl.ServiceCareAutomaticImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        app.setWebApplicationType(WebApplicationType.NONE); // para consola
        ConfigurableApplicationContext context = app.run(args);
        ServiceCareAutomaticImpl careAutomatic = context.getBean(ServiceCareAutomaticImpl.class);
        Scanner read = new Scanner(System.in);
        System.out.println("CAJERO AUTOMÁTICO");
        System.out.println("====================");
        System.out.println("Comandos disponibles: login | extraer | depositar | saldo | salir");
        while (true) {
            System.out.print("Ingrese un comando: ");
            String linea = read.nextLine().trim().toLowerCase();
            String[] partes = linea.split("\\s+");
            String comando = partes[0];
            Integer numberCard;
            Integer numberAccount;
            String cbu;
            Double amount;
            switch (comando) {
                case "login":
                    numberCard = Integer.parseInt(partes[1]);
                    String login = careAutomatic.login(numberCard);
                    System.out.println(login);
                    break;
                case "extraer":
                    numberCard = Integer.parseInt(partes[1]);
                    numberAccount = Integer.parseInt(partes[2]);
                    amount = Double.parseDouble(partes[3]);
                    System.out.println(careAutomatic.extraction(numberCard, numberAccount, amount));
                    break;
                case "depositar":
                    numberCard = Integer.parseInt(partes[1]);
                    cbu = partes[2];
                    amount = Double.parseDouble(partes[3]);
                    System.out.println(careAutomatic.deposit(numberCard, cbu, amount));
                    break;
                case "saldo":
                    numberCard = Integer.parseInt(partes[1]);
                    numberAccount = Integer.parseInt(partes[2]);
                    System.out.println(careAutomatic.balance(numberCard,numberAccount));
                    break;
                case "salir":
                    System.out.println("Gracias por usar el cajero.");
                    read.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Comando no reconocido.");
            }
        }
    }
}
