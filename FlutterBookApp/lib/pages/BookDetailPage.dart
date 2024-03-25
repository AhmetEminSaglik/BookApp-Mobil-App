import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/cubit/BookAddRemoveCubit.dart';
import 'package:flutter_book_app/cubit/MyBookReadScreenCubit.dart';
import 'package:flutter_book_app/cubit/UserBookActionCubit.dart';
import 'package:flutter_book_app/model/Book.dart';
import 'package:flutter_book_app/product/BookDesignDecoration.dart';
import 'package:flutter_book_app/util/ProductColor.dart';
import 'package:flutter_book_app/util/ResponsiveDesign.dart';
import 'package:logger/logger.dart';

import '../cubit/recommendedbook/BookCubit.dart';
import '../httprequest/HttpRequestBook.dart';

class BookDetailPage extends StatefulWidget {
  late Book book;
  double imgWidth = 90;
  double imgHeight = 140;

  BookDetailPage({required this.book});

  @override
  State<BookDetailPage> createState() => _BookDetailPageState();
}

class _BookDetailPageState extends State<BookDetailPage> {
  var log = Logger(printer: PrettyPrinter(colors: false));
  bool isLoading = true;
  bool isBookRead = false;

  _retrieveUserReadThisBook() async {
    if (isLoading == true) {
      await _sendRequestIsBookRead();
      _updateBookAddRemoveCubitValue();
      setState(() {
        isLoading = false;
        log.i("isLoading : $isLoading > isBookRead : $isBookRead");
      });
    }
  }

  _updateBookAddRemoveCubitValue() {
    context.read<BookAddRemoveCubit>().updateBookReadValue(isBookRead);
  }

  _sendRequestIsBookRead() async {
    Book? book = await HttpRequestBook.getIfUserReadBook(widget.book.id);
    if (book != null) {
      isBookRead = true;
    }
    /*List<Book> bookList = await HttpRequestBook.getReadBookList();
    bookList.forEach((element) {
      if (element.id == widget.book.id) {
        isBookRead = true;
        return;
      }
    });*/
  }

  @override
  Widget build(BuildContext context) {
    _retrieveUserReadThisBook();
    return Scaffold(
      appBar: AppBar(title: const Text("Recommended Book Page")),
      backgroundColor: ProductColor.darkWhite,
      body: Padding(
        padding: const EdgeInsets.only(top: 30),
        child: Stack(children: [
          Padding(
            padding: const EdgeInsets.only(top: 90, left: 25, right: 25),
            child: ContainerWithBoxDecoration(
              child: Container(
                height: 550,
                color: ProductColor.white,
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: Column(
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ContainerWithBoxDecoration(
                        child: Padding(
                      padding: const EdgeInsets.all(10),
                      child: Image.network(
                        widget.book.imgUrl,
                        fit: BoxFit.cover,
                        width: widget.imgWidth,
                        height: widget.imgHeight,
                      ),
                    )),
                  ],
                ),
                _BigCardDesign(
                  book: widget.book,
                  isBookRead: isBookRead,
                  isLoading: isLoading,
                ),
              ],
            ),
          )
        ]),
      ),
    );
  }
}

class _BigCardDesign extends StatelessWidget {
  final Book book;
  bool connectionIsNotCreated = true;
  late bool isBookRead;
  late bool isLoading;

  _BigCardDesign(
      {required this.book, required this.isBookRead, required this.isLoading});

  @override
  Widget build(BuildContext context) {
    return Padding(
      // padding: const EdgeInsets.all(30),
      padding: const EdgeInsets.all(30),
      child: Column(
          // crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            getBookHeader(),
            const SizedBox(height: 25),
            getAuthorData(),
            const SizedBox(height: 45),
            SizedBox(width: 250, child: getButton())
          ]),
    );
  }

  SizedBox getBookHeader() {
    return SizedBox(
      height: 100,
      child: SingleChildScrollView(
        child: Column(
          // crossAxisAlignment: CrossAxisAlignment.center,
          // mainAxisAlignment: MainAxisAlignment.center,
          children: [
            _TextForBigCardDesign(
              textAlign: TextAlign.center,
              text: book.name,
              textSize: book.name.length <= 20 ? 25 : 20,
              textColor: ProductColor.black,
              fontWeight: FontWeight.bold,
            ),
            Column(
              children: [
                Row(
                  // crossAxisAlignment: CrossAxisAlignment.center,
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    getBookRatingShape(book.point),
                    // getAveragePointText(widget.book.point)
                  ],
                ),
                getReviewText(book.totalRead),
              ],
            ),
            /*const SizedBox(height: 25),
            getAuthorData()*/
          ],
        ),
      ),
    );
  }

  Column getAuthorData() {
    return Column(
      // crossAxisAlignment: CrossAxisAlignment.stretch,
      children: [
        Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            _TextForBigCardDesign(
                textAlign: TextAlign.center,
                text: "Author: ${book.author.name} ${book.author.lastname}",
                textSize: 17,
                textColor: ProductColor.black,
                fontWeight: FontWeight.bold),
          ],
        ),
        const SizedBox(height: 15),
        getBookDesc(),
        // const SizedBox(height: 20),
        /*     SizedBox(
                width: 200,
                height: 45,
                child: isLoading
                    ? const Center(child: CircularProgressIndicator())
                    : getButton()),*/
      ],
    );
  }

  Container getBookDesc() {
    // if (book.desc.`isNotEmpty`) {
    bool descIsEmpty = book.desc.isEmpty;
    return Container(
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20),
        color: ProductColor.darkWhite,
      ),
      height: 150,
      width: ResponsiveDesign.width(),
      child: Padding(
        padding: const EdgeInsets.all(10.0),
        child: SingleChildScrollView(
          // scrollDirection: Axis.vertical,
          child: Padding(
            padding: EdgeInsets.only(
              top: descIsEmpty ? 50 : 10,
              bottom: 10,
              right: 10,
              left: 10,
            ),
            child: _TextForBigCardDesign(
                text: descIsEmpty ? "- - -" : book.desc,
                textAlign: descIsEmpty ? TextAlign.center : TextAlign.start,
                textSize: 17,
                textColor: ProductColor.darkGrey,
                fontWeight: FontWeight.bold),
          ),
        ),
      ),
    );
    // } else {
    //   return Container(
    //     child: const Text("- - -"),
    //     alignment: Alignment.centerLeft,
    //   );
    // }
  }

  Widget getButton() {
    return BlocBuilder<BookAddRemoveCubit, bool>(builder: (context, state) {
      if (state == true) {
        return _RemoveReadBookButton(bookId: book.id);
      }
      return _AddAsReadButton(bookId: book.id);
    });

    /*return isBookRead
        ? _RemoveReadBookButton(bookId: book.id)
        : _AddAsReadButton(bookId: book.id);*/
  }
}

class _RemoveReadBookButton extends StatefulWidget {
  final int bookId;

  _RemoveReadBookButton({required this.bookId});

  @override
  State<_RemoveReadBookButton> createState() => _RemoveReadBookButtonState();
}

class _RemoveReadBookButtonState extends State<_RemoveReadBookButton> {
  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: () async {
        await context
            .read<UserBookActionCubit>()
            .destroyUserReadBookConnection(widget.bookId);
        context.read<BookAddRemoveCubit>().updateBookReadValue(false);
        context.read<MyReadBookScreenCubit>().updateBookList();
      },
      style: ButtonStyle(
        shape: MaterialStateProperty.all<RoundedRectangleBorder>(
            RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(10.0),
          // side: const BorderSide(color: Colors.red)
        )),
        backgroundColor:
            MaterialStateColor.resolveWith((states) => ProductColor.red),
        foregroundColor:
            MaterialStateColor.resolveWith((states) => ProductColor.white),
      ),
      child: const Text(
        "Remove Book",
        style: TextStyle(fontSize: 18),
      ),
    );
  }
}

class _AddAsReadButton extends StatefulWidget {
  final int bookId;

  _AddAsReadButton({required this.bookId});

  @override
  State<_AddAsReadButton> createState() => _AddAsReadButtonState();
}

class _AddAsReadButtonState extends State<_AddAsReadButton> {
  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: () async {
        await context
            .read<UserBookActionCubit>()
            .createUserReadBookConnection(widget.bookId);
        context.read<BookAddRemoveCubit>().updateBookReadValue(true);
        context.read<MyReadBookScreenCubit>().updateBookList();
      },
      style: ButtonStyle(
        shape: MaterialStateProperty.all<RoundedRectangleBorder>(
            RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(10.0),
          // side: const BorderSide(color: Colors.red)
        )),
        backgroundColor:
            MaterialStateColor.resolveWith((states) => ProductColor.green),
        foregroundColor:
            MaterialStateColor.resolveWith((states) => ProductColor.white),
      ),
      child: const Text(
        "Add Book",
        style: TextStyle(fontSize: 18),
      ),
    );
  }
}

class _TextForBigCardDesign extends StatelessWidget {
  final String text;
  final Color textColor;
  final FontWeight fontWeight;
  final double textSize;
  final TextAlign textAlign;

  const _TextForBigCardDesign(
      {required this.text,
      required this.textColor,
      required this.fontWeight,
      required this.textSize,
      required this.textAlign});

  @override
  Widget build(BuildContext context) {
    return Text(text,
        textAlign: textAlign,
        style: TextStyle(
            fontSize: textSize, fontWeight: fontWeight, color: textColor));
  }
}
