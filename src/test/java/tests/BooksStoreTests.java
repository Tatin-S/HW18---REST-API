package tests;

import api.books.BooksApi;
import config.TestDataConfig;
import helpers.WithLogin;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import pages.ProfilePage;
public class BooksStoreTests extends TestBase {
    private final ProfilePage profilePage = new ProfilePage();
    private final BooksApi book = new BooksApi();
    static final TestDataConfig testDataConfig = ConfigFactory.create(TestDataConfig.class, System.getProperties());

    @Test
    @WithLogin
    @DisplayName("Delete Удаление книги из списка на странице профиля пользователя")
    public void DeleteBookFromTheList() {
        book.addBookToProfile(testDataConfig.isbn());
        profilePage
                .openPage()
                .checkNameProfile(testDataConfig.userLogin())
                .checkBookIsInProfile(testDataConfig.isbn())
                .deleteBook()
                .checkBookIsDeleted();
    }
}
