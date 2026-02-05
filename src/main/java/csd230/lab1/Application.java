package csd230.lab1;

import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.github.javafaker.Commerce;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
//import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private final BookEntityRepository bookRepository;
    private final CartEntityRepository cartRepository;
    private final DiscMagEntityRepository discMagRepository;
    private final MagazineEntityRepository magazineRepository;
    private final TicketEntityRepository ticketRepository;
    private final ProductEntityRepository productRepository;
    private final GuitarEntityRepository guitarRepository;
    private final ElectricGuitarEntityRepository electricGuitarRepository;
    private final AcousticGuitarEntityRepository acousticGuitarRepository;

//    public Application(BookEntityRepository bookRepository, CartRepository cartRepository, DiscMagRepository discMagRepository, MagazineRepository magazineRepository, TicketRepository ticketRepository) {
    public Application(BookEntityRepository bookRepository, CartEntityRepository cartRepository, DiscMagEntityRepository discMagRepository, MagazineEntityRepository magazineRepository, TicketEntityRepository ticketRepository, ProductEntityRepository productRepository, GuitarEntityRepository guitarRepository, ElectricGuitarEntityRepository electricGuitarRepository, AcousticGuitarEntityRepository acousticGuitarRepository) {
        this.bookRepository = bookRepository;
        this.cartRepository = cartRepository;
        this.discMagRepository = discMagRepository;
        this.magazineRepository = magazineRepository;
        this.ticketRepository = ticketRepository;
        this.productRepository = productRepository;
        this.guitarRepository = guitarRepository;
        this.electricGuitarRepository = electricGuitarRepository;
        this.acousticGuitarRepository = acousticGuitarRepository;
    }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Override
    @Transactional
    public void run (String... args) throws Exception {

        Faker faker = new Faker();
        Commerce cm = faker.commerce();
        com.github.javafaker.Number number = faker.number();
        com.github.javafaker.Book fakeBook = faker.book();
        LocalDateTime now = LocalDateTime.now();
        String name=cm.productName();
        String description=cm.material();

        //BookEntity
        BookEntity newBook = new BookEntity(fakeBook.title(), number.randomDouble(2,10,100), number.numberBetween(1,50), fakeBook.author(), "1234567890");
        bookRepository.save(newBook);

        BookEntity newBook2 = new BookEntity(fakeBook.title(), number.randomDouble(2,10,100), number.numberBetween(1,50), fakeBook.author(), "0987654321");
        bookRepository.save(newBook2);

        //CartEntity
        CartEntity cart = new CartEntity();
        cartRepository.save(cart);


    }


}
