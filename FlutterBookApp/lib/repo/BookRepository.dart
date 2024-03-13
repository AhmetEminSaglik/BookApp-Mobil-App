import 'package:flutter_book_app/model/Author.dart';

import '../model/Book.dart';

class BookRepository {
  static Book parseBook(Map<String, dynamic> json) {
    print("-------JSON : $json");
    return Book.fromJson(json);
  }

  static List<Book> parseBookList(List<dynamic> json) {
    List<Book> bookList = [];
    print("-------JSON : $json");
    for (var tmp in json) {
      Book user = Book(
          id: tmp["id"],
          desc: tmp["description"],
          imgUrl: tmp["imgUrl"],
          isbn: tmp["isbn"],
          name: tmp["name"],
          point: tmp["point"],
          totalRead: tmp["totalRead"],
          webUrl: tmp["webUrl"],
          author: Author.fromJson(tmp['author']));
      bookList.add(user);
    }
    return bookList;
  }
}
