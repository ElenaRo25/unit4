import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    private BookRepository bookRepository; // Mock
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        bookRepository = Mockito.mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    public void testGetBookById_BookExists() {
        // Arrange
        Long bookId = 1L;
        Book expectedBook = new Book();
        expectedBook.setId(bookId);
        expectedBook.setTitle("Mockito in Action");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(expectedBook));

        // Act
        Book actualBook = bookService.getBookById(bookId);

        // Assert
        assertNotNull(actualBook);
        assertEquals(expectedBook.getId(), actualBook.getId());
        assertEquals(expectedBook.getTitle(), actualBook.getTitle());
    }

    @Test
    public void testGetBookById_BookDoesNotExist() {
        // Arrange
        Long bookId = 2L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // Act
        Book actualBook = bookService.getBookById(bookId);

        // Assert
        assertNull(actualBook);
    }
}
