package soa.mkuzmik.se.app;

import entities.Role;
import entities.User;
import repos.UserRepository;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.Scanner;

public class RegisterUser {


    private static UserRepository lookupUserRepository() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();

        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");

        final Context ctx = new InitialContext(jndiProperties);

        return (UserRepository) ctx
                .lookup("ejb:/fantasy-beans-1.0-SNAPSHOT/UserRepositoryBean!" + UserRepository.class.getName());
    }

    public static void main(String[] args) {
        try {
          UserRepository userRepository = lookupUserRepository();
          Scanner reader = new Scanner(System.in);

          System.out.print("Enter username: ");
          String username = reader.next();
          System.out.print("Enter password: ");
          String password = reader.next();
          System.out.print("Enter role (USER/ADMIN): ");
          String role = reader.next();
          Role userRole = Role.valueOf(role);

          User user = new User(username, password, userRole);
          userRepository.save(user);

          System.out.println("OK");
        } catch (EJBException e) {
          e.printStackTrace();
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}