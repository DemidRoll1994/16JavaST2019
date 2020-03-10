package by.samtsov.controller.command;

public class SearchBookResultAction /*extends LibrarianAction */{
/*

    public ForwardPage exec(HttpServletRequest request,
                            HttpServletResponse response) throws PersistentException {
        // создаем модель следующей страницы:
        ForwardPage forward = new ForwardPage("/search/book/result.jsp", false);
        // атрибут readerId снова записываем в сессию.(ну а точнее готовим к
        // записи в сессию)
        Integer readerIdentity = (Integer)request.getAttribute("readerIdentity");
        if(readerIdentity != null) {
            forward.getRedirectedAttributes().put("readerIdentity", readerIdentity);
        }
        //создали сервис книги, для того чтобы пототм работатьс ним:
        List<Book> books;
        BookService service = factory.getService(BookService.class);
        //получаем параметры поиска со страницы :
        String inventoryNumber = request.getParameter("inventoryNumber");
        String search = request.getParameter("search");
        Integer identity = null;
        try {
            identity = Integer.parseInt(request.getParameter("identity"));
        } catch(NumberFormatException e) {}
        //ищем поочередно по заданным параметрам - по кому первому нахдят -
        // того отдают в рексвесте
        if(inventoryNumber != null) {
            books = new ArrayList<>();
            Book book = service.findByInventoryNumber(inventoryNumber);
            if(book != null) {
                books.add(book);
            }
        } else if(search != null) {
            books = service.findByTitle(search);
        } else {
            books = service.findByAuthor(identity);
        }
        //отдаем ответ в реквесте
        request.setAttribute("books", books);
        return forward;
    }*/
}