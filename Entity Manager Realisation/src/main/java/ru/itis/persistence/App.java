package ru.itis.persistence;

import ru.itis.persistence.core.Configuration;
import ru.itis.persistence.core.Dialect;
import ru.itis.persistence.core.EntityManager;
import ru.itis.persistence.core.EntityManagerFactory;
import ru.itis.persistence.criteria.CriteriaBuilder;
import ru.itis.persistence.criteria.SignsResolver;

import java.util.List;
import java.util.Scanner;

public class App {

    protected EntityManagerFactory factory;
    protected EntityManager entityManager;
    protected SignsResolver signsResolver;
    protected CriteriaBuilder builder;

    public static void main(String[] args){
          App app = new App();
          app.init();
          app.start();
    }

    public void init(){
        Configuration configuration = Configuration.builder()
                .url("jdbc:postgresql://localhost:5432/Minion_Valuation")
                .username("postgres")
                .password("Ki27082001rill")
                .driverClass(org.postgresql.Driver.class)
                .dialect(Dialect.PostgreSQL9Dialect)
                .maximumPoolSize(10)
                .showSql(true)
                .build()
                .registry()
                .registerEntity(Account.class)
                .close();
        factory = new EntityManagerFactory(configuration);
        entityManager = factory.createEntityManager();
        signsResolver = factory.getSignsResolver();
        builder = factory.getCriteriaBuilder();
    }

    public void start(){
        boolean isWork = true;
        Scanner sc = new Scanner(System.in);
        String command;
        try{
            while (isWork){
                System.out.print("Entity Manager command => ");
                command = sc.nextLine().trim().toLowerCase();
                switch (command.split(" ")[0]){
                    case "/save":
                        System.out.print("Enter account id: ");
                        Long id = sc.nextLong();
                        System.out.print("Enter account name: ");
                        sc.nextLine();
                        String name = sc.nextLine();
                        System.out.print("Enter account money amount: ");
                        Integer money = sc.nextInt();
                        sc.nextLine();
                        entityManager.save(Account.builder()
                                .id(id)
                                .name(name)
                                .money(money)
                                .build());
                        break;
                    case "/find":
                        if (command.split(" ").length == 1){
                            System.out.println("There is no flags");
                        } else {
                            switch (command.split(" ")[1]){
                                case "all":
                                    List<Account> accounts = entityManager.findAll(Account.class);
                                    for (Account account : accounts){
                                        System.out.println("{Id = " + account.getId() + "; Name = " + account.getName() +
                                                "; Money = " + account.getMoney() + "}");
                                    }
                                    System.out.println(accounts.size() + " accounts summary");
                                    break;
                                case "byid":
                                    try{
                                        Account account = entityManager.findById(Account.class, Integer.valueOf(command.split(" ")[2]));
                                        System.out.println("{Id = " + account.getId() + "; Name = " + account.getName() +
                                                "; Money = " + account.getMoney() + "}");
                                    } catch (ArrayIndexOutOfBoundsException ex){
                                        System.out.println("There is no ID value");
                                    }
                                    break;
                                default:
                                    System.out.println("Incorrect Flag.");
                            }
                        }
                        break;
                    case "/delete":
                        if (command.split(" ").length == 1){
                            System.out.println("There is no flags");
                        } else {
                            switch (command.split(" ")[1]){
                                case "byid":
                                    try{
                                        entityManager.deleteById(Account.class, Integer.valueOf(command.split(" ")[2]));
                                    } catch (ArrayIndexOutOfBoundsException ex){
                                        System.out.println("There is no ID value");
                                    }
                                    break;
                                default:
                                    System.out.println("Incorrect Flag.");
                            }
                        }
                        break;
                    case "/update":
                        System.out.print("Enter current id: ");
                        Long currentId = sc.nextLong();
                        sc.nextLine();
                        System.out.println("Enter new name: ");
                        String newName = sc.nextLine();
                        System.out.println("Enter new money: ");
                        Integer newMoney = sc.nextInt();
                        sc.nextLine();
                        entityManager.update(Account.builder()
                            .id(currentId)
                            .name(newName)
                            .money(newMoney)
                            .build()
                        );
                    case "/quit":
                        isWork = false;
                        break;
                    case "/entity":
                        System.out.println("Account entity info:");
                        System.out.println("\t*Id - account identify (unique value) [Long]");
                        System.out.println("\t*Name - account name (unique value) [String]");
                        System.out.println("\t*Money - account money [Integer]");
                        System.out.println("\t*transientField - account transientField (transient field) [Short]");
                        break;
                    case "/help":
                        System.out.println("Command list:");
                        System.out.println("\t/entity - command to get information about entity fields");
                        System.out.println("\t/quit - command to close program");
                        System.out.println("\t/save - command to save new account");
                        System.out.println("\t/find [all | byId ${ID} | ${custom}] - command to save new account");
                        System.out.println("\t\tall - get all accounts");
                        System.out.println("\t\tbyId ${ID} - find account by ${ID} value");
                        System.out.println("\t/delete [byId ${ID}] - command to delete entity");
                        System.out.println("\t\tbyId ${ID} - delete account by ${ID} value");
                        System.out.println("\t/update - command to update account entry");
                        break;
                    default:
                        System.out.println("Unknown command. Use '/help' to get commands list");
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
            start();
        }
    }
}
