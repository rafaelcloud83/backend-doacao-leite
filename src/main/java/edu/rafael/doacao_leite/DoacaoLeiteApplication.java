package edu.rafael.doacao_leite;

import edu.rafael.doacao_leite.entities.Order;
import edu.rafael.doacao_leite.entities.Users;
import edu.rafael.doacao_leite.entities.enums.Role;
import edu.rafael.doacao_leite.repositories.OrderRepository;
import edu.rafael.doacao_leite.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DoacaoLeiteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DoacaoLeiteApplication.class, args);
	}

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	Users user;
	@Autowired
	Order order;

	@Override
	public void run(String... args) throws Exception {

		Users user1 = new Users("Rafael", "rafael@email.com", "$2a$10$Em.2EoqjaYSuWCmw6U/Msuwa3x4.s87R7e5YtgC9qo1W1hdqC/S..", Enum.valueOf(Role.class,"ADMIN"));
		Users user2 = new Users("Mariana", "mariana@email.com", "$2a$10$Em.2EoqjaYSuWCmw6U/Msuwa3x4.s87R7e5YtgC9qo1W1hdqC/S..", Enum.valueOf(Role.class,"RECEBEDOR"));
		Users user3 = new Users("Maria", "maria@email.com", "$2a$10$Em.2EoqjaYSuWCmw6U/Msuwa3x4.s87R7e5YtgC9qo1W1hdqC/S..", Enum.valueOf(Role.class,"RECEBEDOR"));
		Users user4 = new Users("Joao", "joao@email.com", "$2a$10$Em.2EoqjaYSuWCmw6U/Msuwa3x4.s87R7e5YtgC9qo1W1hdqC/S..", Enum.valueOf(Role.class,"RECEBEDOR"));
		Users user5 = new Users("Mario", "mario@email.com", "$2a$10$Em.2EoqjaYSuWCmw6U/Msuwa3x4.s87R7e5YtgC9qo1W1hdqC/S..", Enum.valueOf(Role.class,"DOADOR"));
		Users user6 = new Users("Jose", "jose@email.com", "$2a$10$Em.2EoqjaYSuWCmw6U/Msuwa3x4.s87R7e5YtgC9qo1W1hdqC/S..", Enum.valueOf(Role.class,"DOADOR"));
		Users user7 = new Users("Joana", "joana@email.com", "$2a$10$Em.2EoqjaYSuWCmw6U/Msuwa3x4.s87R7e5YtgC9qo1W1hdqC/S..", Enum.valueOf(Role.class,"DOADOR"));
		usersRepository.save(user1);
		usersRepository.save(user2);
		usersRepository.save(user3);
		usersRepository.save(user4);
		usersRepository.save(user5);
		usersRepository.save(user6);
		usersRepository.save(user7);

		Order order1 = new Order("Leite ninho", user2, user5);
		Order order2 = new Order("Leite italac", user3, user6);
		Order order3 = new Order("Leite nan", user4, user7);
		Order order4 = new Order("Leite ninho po", user2, user7);
		orderRepository.save(order1);
		orderRepository.save(order2);
		orderRepository.save(order3);
		orderRepository.save(order4);
	}
}
