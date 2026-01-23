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

        //CartEntity
        CartEntity cart = new CartEntity();
        cartRepository.save(cart);

        CartEntity cart2 = new CartEntity();
        cartRepository.save(cart2);

        //DiscMagEntity
        DiscMagEntity discMag= new DiscMagEntity(fakeBook.title(), number.randomDouble(2,10,100), number.numberBetween(1,50), number.randomDigit(), now, true);
        discMagRepository.save(discMag);
//        //MagazineEntity
        MagazineEntity magazine = new MagazineEntity(fakeBook.title(), number.randomDouble(2,10,100), number.numberBetween(1,50), number.randomDigit(), now);
        magazineRepository.save(magazine);
//        //TicketEntity
        TicketEntity ticket = new TicketEntity(description, number.randomDouble(2,10,100));
        ticketRepository.save(ticket);

        //Niche Entities
        ElectricGuitarEntity electricGtr = new ElectricGuitarEntity("Fender", "Stratocaster", 6, 2, number.randomDouble(2,250,2000));
        electricGuitarRepository.save(electricGtr);

        AcousticGuitarEntity acousticGtr = new AcousticGuitarEntity("Martin", "Grand J-28E DN", 6, true, number.randomDouble(2,300,2500));
        acousticGuitarRepository.save(acousticGtr);

        cart.addProduct(newBook);
        cart.addProduct(discMag);
        cart.addProduct(magazine);
        cart.addProduct(ticket);
        cart.addProduct(electricGtr);
        cart.addProduct(acousticGtr);
        cartRepository.save(cart);

        cart2.addProduct(newBook);
        cart2.addProduct(discMag);
        cart2.addProduct(magazine);
        cart2.addProduct(ticket);
        cart2.addProduct(electricGtr);
        cart2.addProduct(acousticGtr);
        cartRepository.save(cart2);

        //Read each entity type from database
        List<BookEntity> readBook = bookRepository.findByIsbn("1234567890");
        readBook.forEach(System.out::println);

        List<DiscMagEntity> readDiscMags = discMagRepository.findByHasDisc(true);
        readDiscMags.forEach(System.out::println);

        List<MagazineEntity> readMagazines = magazineRepository.findByCurrentIssue(magazine.getCurrentIssue());
        readMagazines.forEach(System.out::println);

        List<TicketEntity> readTickets = ticketRepository.findByPrice(ticket.getPrice());
        readTickets.forEach(System.out::println);

        List<ElectricGuitarEntity> readElectricGtrs = electricGuitarRepository.findByNumberOfPickups(2);
        readElectricGtrs.forEach(System.out::println);

        List<AcousticGuitarEntity> readAcousticGtrs = acousticGuitarRepository.findByHasCutaway(true);
        readAcousticGtrs.forEach(System.out::println);

        //Update each entity type
        newBook.setAuthor("Updated Author");
        bookRepository.save(newBook);
        discMag.setHasDisc(false);
        discMagRepository.save(discMag);
        magazine.setTitle("Cool Magazine");
        magazineRepository.save(magazine);
        ticket.setDescription("for a cool concert");
        ticketRepository.save(ticket);
        electricGtr.setBrand("Gibson");
        electricGuitarRepository.save(electricGtr);
        acousticGtr.setHasCutaway(false);
        acousticGuitarRepository.save(acousticGtr);

        System.out.println("All Products:");
        List<ProductEntity> allProducts = productRepository.findAll();
        allProducts.forEach(System.out::println);

        //Cart Contents
        System.out.println("Cart 1 Contents:");
        Set<ProductEntity> cartContents = cart.getProducts();
        cartContents.forEach(System.out::println);

        System.out.println("Cart 2 Contents:");
        Set<ProductEntity> cart2Contents = cart2.getProducts();
        cart2Contents.forEach(System.out::println);


        //Delete carts
        cartRepository.deleteAll();

        //Delete Products
        System.out.println("Products before deletion:");
        allProducts.forEach(System.out::println);
        productRepository.deleteAll();
        System.out.println("Products after deletion:");
        allProducts = productRepository.findAll();
        allProducts.forEach(System.out::println);


    }


}
