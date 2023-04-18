import axios from "../custom-axios/axios";

const BookService = {
    fetchCategories: () => {
        return axios.get("/books/categories");
    },
    fetchBooks: () => {
        return axios.get("/books/all");
    },
    fetchAuthors: () => {
        return axios.get("/authors/all");
    },
    addBook(name, category, authorId, availableCopies) {
        return axios.post("/books/add", {
            "name": name,
            "category": category,
            "authorId": authorId,
            "availableCopies": availableCopies
        });
    },
    editBook: (id,name, category, authorId, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "category": category,
            "authorId": authorId,
            "availableCopies": availableCopies
        });

    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    markAsTaken: (id) =>{
        return axios.put(`/books/markAsRented/${id}`);
    }
}

export default BookService;