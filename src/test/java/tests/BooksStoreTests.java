package tests;
import api.books.BooksApi;
import helpers.WithLogin;
import org.junit.jupiter.api.*;
import pages.ProfilePage;
public class BooksStoreTests extends TestBase {
    private final ProfilePage profilePage = new ProfilePage();
    private final BooksApi book = new BooksApi();

    @Test
    @WithLogin
    @DisplayName("Удаление книги из списка на странице профиля пользователя")
    public void DeleteBookFromTheList() {
        String isbn = "9781491904244";

        book.addBookToProfile(isbn);
        profilePage
                .openPage()
                .checkBookIsInProfile(isbn)
                .deleteBook()
                .checkBookIsDeleted();
    }
}
