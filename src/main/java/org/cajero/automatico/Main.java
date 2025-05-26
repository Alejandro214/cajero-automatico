package org.cajero.automatico;

import org.cajero.automatico.service.ServiceAccountCardImpl;
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
        ServiceAccountCardImpl serviceAccount = context.getBean(ServiceAccountCardImpl.class);
        Scanner read = new Scanner(System.in);
        System.out.println("CAJERO AUTOMÁTICO");
        System.out.println("====================");
        while (true) {
            System.out.println("Comandos disponibles: login | extraer | depositar | saldo | salir");
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
                    String login = serviceAccount.login(numberCard);
                    System.out.println(login);
                    break;
                case "extraer":
                    numberCard = Integer.parseInt(partes[1]);
                    numberAccount = Integer.parseInt(partes[2]);
                    amount = Double.parseDouble(partes[3]);
                    System.out.println(serviceAccount.extraction(numberCard, numberAccount, amount));
                    break;
                case "depositar":
                    numberCard = Integer.parseInt(partes[1]);
                    cbu = partes[2];
                    amount = Double.parseDouble(partes[3]);
                    System.out.println(serviceAccount.deposit(numberCard, cbu, amount));
                    break;
                case "saldo":
                    numberCard = Integer.parseInt(partes[1]);
                    numberAccount = Integer.parseInt(partes[2]);
                    double saldo = serviceAccount.balance(numberCard, numberAccount);
                    System.out.println("Su saldo es $" + saldo);
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
