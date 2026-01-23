package csd230.lab1;

import com.github.javafaker.Commerce;
import com.github.javafaker.Faker;
import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class ApplicationTests {
    @Autowired
    private ProductEntityRepository productRepository;
    @Autowired
    private GuitarEntityRepository guitarRepository;
    @Autowired
    private ElectricGuitarEntityRepository electricGuitarRepository;
    @Autowired
    private AcousticGuitarEntityRepository acousticGuitarRepository;
    @Autowired
    private BookEntityRepository bookRepository;
    @Autowired
    private CartEntityRepository cartRepository;
    @Autowired
    private DiscMagEntityRepository discMagRepository;
    @Autowired
    private MagazineEntityRepository magazineRepository;
    @Autowired
    private TicketEntityRepository ticketRepository;

	@Test
    void testCreateAndSaveEntities() {
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

        //DiscMagEntity
        DiscMagEntity discMag= new DiscMagEntity(fakeBook.title(), number.randomDouble(2,10,100), number.numberBetween(1,50), number.randomDigit(), now, true);
        discMagRepository.save(discMag);

        //MagazineEntity
        MagazineEntity magazine = new MagazineEntity(fakeBook.title(), number.randomDouble(2,10,100), number.numberBetween(1,50), number.randomDigit(), now);
        magazineRepository.save(magazine);

        //TicketEntity
        TicketEntity ticket = new TicketEntity(description, number.randomDouble(2,10,100));
        ticketRepository.save(ticket);

        //Niche Entities
        ElectricGuitarEntity electricGtr = new ElectricGuitarEntity("Fender", "Stratocaster", 6, 2, number.randomDouble(2,250,2000));
        electricGuitarRepository.save(electricGtr);

        AcousticGuitarEntity acousticGtr = new AcousticGuitarEntity("Martin", "Grand J-28E DN", 6, true, number.randomDouble(2,300,2500));
        acousticGuitarRepository.save(acousticGtr);

        List<ProductEntity> allProducts = productRepository.findAll();
        assertTrue(allProducts.size() == 6);

        List<CartEntity> allCarts = cartRepository.findAll();
        assertTrue(allCarts.size() == 1);
    }

    @Test
    void testReadFromDatabase() {
        Faker faker = new Faker();
        Commerce cm = faker.commerce();
        com.github.javafaker.Number number = faker.number();
        com.github.javafaker.Book fakeBook = faker.book();
        LocalDateTime now = LocalDateTime.now();
        String description=cm.material();

        //BookEntity
        BookEntity book = new BookEntity(fakeBook.title(), number.randomDouble(2,10,100), number.numberBetween(1,50), fakeBook.author(), "1234567890");
        bookRepository.save(book);

        //CartEntity
        CartEntity cart = new CartEntity();
        cartRepository.save(cart);

        //DiscMagEntity
        DiscMagEntity discMag= new DiscMagEntity(fakeBook.title(), number.randomDouble(2,10,100), number.numberBetween(1,50), number.randomDigit(), now, true);
        discMagRepository.save(discMag);

        //MagazineEntity
        MagazineEntity magazine = new MagazineEntity(fakeBook.title(), number.randomDouble(2,10,100), number.numberBetween(1,50), number.randomDigit(), now);
        magazineRepository.save(magazine);

        //TicketEntity
        TicketEntity ticket = new TicketEntity(description, number.randomDouble(2,10,100));
        ticketRepository.save(ticket);

        //Niche Entities
        ElectricGuitarEntity electricGtr = new ElectricGuitarEntity("Fender", "Stratocaster", 6, 2, number.randomDouble(2,250,2000));
        electricGuitarRepository.save(electricGtr);

        AcousticGuitarEntity acousticGtr = new AcousticGuitarEntity("Martin", "Grand J-28E DN", 6, true, number.randomDouble(2,300,2500));
        acousticGuitarRepository.save(acousticGtr);

        List<BookEntity> readBook = bookRepository.findByIsbn("1234567890");
        assertEquals(readBook.get(0).getTitle(), book.getTitle());

        List<DiscMagEntity> readDiscMags = discMagRepository.findByHasDisc(true);
        assertEquals(readDiscMags.get(0).getTitle(), discMag.getTitle());

        List<MagazineEntity> readMagazines = magazineRepository.findByCurrentIssue(magazine.getCurrentIssue());
        assertEquals(readMagazines.get(1).getTitle(), magazine.getTitle());

        List<TicketEntity> readTickets = ticketRepository.findByPrice(ticket.getPrice());
        assertEquals(readTickets.get(0).getDescription(), ticket.getDescription());

        List<ElectricGuitarEntity> readElectricGtrs = electricGuitarRepository.findByNumberOfPickups(2);
        assertEquals(readElectricGtrs.get(0).getModel(), electricGtr.getModel());
        assertEquals(readElectricGtrs.get(0).getBrand(), electricGtr.getBrand());

        List<AcousticGuitarEntity> readAcousticGtrs = acousticGuitarRepository.findByHasCutaway(true);
        assertEquals(readAcousticGtrs.get(0).getModel(), acousticGtr.getModel());
        assertEquals(readAcousticGtrs.get(0).getBrand(), acousticGtr.getBrand());

    }

    @Test
    void testUpdateEntities() {
        Faker faker = new Faker();
        Commerce cm = faker.commerce();
        com.github.javafaker.Number number = faker.number();
        com.github.javafaker.Book fakeBook = faker.book();
        LocalDateTime now = LocalDateTime.now();
        String description=cm.material();

        //BookEntity
        BookEntity book = new BookEntity(fakeBook.title(), number.randomDouble(2,10,100), number.numberBetween(1,50), fakeBook.author(), "1234567890");
        bookRepository.save(book);

        //CartEntity
        CartEntity cart = new CartEntity();
        cartRepository.save(cart);

        //DiscMagEntity
        DiscMagEntity discMag= new DiscMagEntity(fakeBook.title(), number.randomDouble(2,10,100), number.numberBetween(1,50), number.randomDigit(), now, true);
        discMagRepository.save(discMag);

        //MagazineEntity
        MagazineEntity magazine = new MagazineEntity(fakeBook.title(), number.randomDouble(2,10,100), number.numberBetween(1,50), number.randomDigit(), now);
        magazineRepository.save(magazine);

        //TicketEntity
        TicketEntity ticket = new TicketEntity(description, number.randomDouble(2,10,100));
        ticketRepository.save(ticket);

        //Niche Entities
        ElectricGuitarEntity electricGtr = new ElectricGuitarEntity("Fender", "Stratocaster", 6, 2, number.randomDouble(2,250,2000));
        electricGuitarRepository.save(electricGtr);

        AcousticGuitarEntity acousticGtr = new AcousticGuitarEntity("Martin", "Grand J-28E DN", 6, true, number.randomDouble(2,300,2500));
        acousticGuitarRepository.save(acousticGtr);

        book.setAuthor("Updated Author");
        bookRepository.save(book);
        assertEquals("Updated Author", book.getAuthor());

        discMag.setHasDisc(false);
        discMagRepository.save(discMag);
        assertFalse(discMag.getHasDisc());

        magazine.setTitle("Updated Title");
        magazineRepository.save(magazine);
        assertEquals("Updated Title", magazine.getTitle());

        ticket.setDescription("Updated Description");
        ticketRepository.save(ticket);
        assertEquals("Updated Description", ticket.getDescription());

        electricGtr.setNumberOfPickups(3);
        electricGuitarRepository.save(electricGtr);
        assertEquals(3, electricGtr.getNumberOfPickups());

        acousticGtr.setHasCutaway(false);
        acousticGuitarRepository.save(acousticGtr);
        assertFalse(acousticGtr.getHasCutaway());

    }

    @Test
    void testDeleteEntities() {
        Faker faker = new Faker();
        Commerce cm = faker.commerce();
        com.github.javafaker.Number number = faker.number();
        com.github.javafaker.Book fakeBook = faker.book();
        LocalDateTime now = LocalDateTime.now();
        String description=cm.material();

        //BookEntity
        BookEntity book = new BookEntity(fakeBook.title(), number.randomDouble(2,10,100), number.numberBetween(1,50), fakeBook.author(), "1234567890");
        bookRepository.save(book);

        //CartEntity
        CartEntity cart = new CartEntity();
        cartRepository.save(cart);

        //DiscMagEntity
        DiscMagEntity discMag= new DiscMagEntity(fakeBook.title(), number.randomDouble(2,10,100), number.numberBetween(1,50), number.randomDigit(), now, true);
        discMagRepository.save(discMag);

        //MagazineEntity
        MagazineEntity magazine = new MagazineEntity(fakeBook.title(), number.randomDouble(2,10,100), number.numberBetween(1,50), number.randomDigit(), now);
        magazineRepository.save(magazine);

        //TicketEntity
        TicketEntity ticket = new TicketEntity(description, number.randomDouble(2,10,100));
        ticketRepository.save(ticket);

        //Niche Entities
        ElectricGuitarEntity electricGtr = new ElectricGuitarEntity("Fender", "Stratocaster", 6, 2, number.randomDouble(2,250,2000));
        electricGuitarRepository.save(electricGtr);

        AcousticGuitarEntity acousticGtr = new AcousticGuitarEntity("Martin", "Grand J-28E DN", 6, true, number.randomDouble(2,300,2500));
        acousticGuitarRepository.save(acousticGtr);

        List<ProductEntity> products = productRepository.findAll();
        assertEquals(6, products.size());
        List<CartEntity> carts = cartRepository.findAll();
        assertEquals(1, carts.size());

        productRepository.delete(book);
        productRepository.delete(discMag);
        productRepository.delete(magazine);
        productRepository.delete(ticket);
        productRepository.delete(electricGtr);
        productRepository.delete(acousticGtr);
        cartRepository.delete(cart);

        products = productRepository.findAll();
        carts = cartRepository.findAll();
        assertTrue(products.isEmpty());
        assertTrue(carts.isEmpty());

    }



}
