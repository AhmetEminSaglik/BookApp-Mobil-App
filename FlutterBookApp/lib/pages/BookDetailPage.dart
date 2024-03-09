import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/cubit/UserBookActionCubit.dart';
import 'package:flutter_book_app/model/Book.dart';
import 'package:flutter_book_app/product/BookDesignDecoration.dart';
import 'package:flutter_book_app/util/ProductColor.dart';

class BookDetailPage extends StatefulWidget {
  late Book book;
  double imgWidth = 90;
  double imgHeight = 140;

  BookDetailPage({required this.book});

  @override
  State<BookDetailPage> createState() => _BookDetailPageState();
}

class _BookDetailPageState extends State<BookDetailPage> {
  @override
  Widget build(BuildContext context) {
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
                height: 500,
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
                _BigCardDesign(widget.book),
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

  _BigCardDesign(this.book);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(30),
      child: Column(
        // crossAxisAlignment: CrossAxisAlignment.center,
        // mainAxisAlignment: MainAxisAlignment.center,
        children: [
          _TextForBigCardDesign(
            text: book.name,
            textSize: 25,
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
          const SizedBox(height: 25),
          Column(
            // crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  _TextForBigCardDesign(
                      text:
                          "Author: ${book.author.name} ${book.author.lastname}",
                      textSize: 17,
                      textColor: ProductColor.black,
                      fontWeight: FontWeight.bold),
                ],
              ),
              const SizedBox(height: 15),
              Container(
                color: ProductColor.darkWhite,
                height: 120,
                child: SingleChildScrollView(
                  // scrollDirection: Axis.vertical,
                  child: _TextForBigCardDesign(
                      text: book.desc,
                      textSize: 17,
                      textColor: ProductColor.darkGrey,
                      fontWeight: FontWeight.bold),
                ),
              ),
              const SizedBox(height: 20),
              SizedBox(
                width: 200,
                child: _AddAsReadButton(
                    bookId: book
                        .id)
                ,
              )
            ],
          )
        ],
      ),
    );
  }
}

class _AddAsReadButton extends StatefulWidget {
  final int bookId;

  // bool connectionFound;

  _AddAsReadButton({required this.bookId /*, required this.connectionFound*/
      });

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

      },
      style: ButtonStyle(
        shape: MaterialStateProperty.all<RoundedRectangleBorder>(
            RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(10.0),
          // side: const BorderSide(color: Colors.red)
        )),
        backgroundColor:
            MaterialStateColor.resolveWith((states) => ProductColor.orange),
        foregroundColor:
            MaterialStateColor.resolveWith((states) => ProductColor.white),
      ),
      child: const Text(
        "Add As Read",
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

  _TextForBigCardDesign(
      {required this.text,
      required this.textColor,
      required this.fontWeight,
      required this.textSize});

  @override
  Widget build(BuildContext context) {
    return Text(text,
        style: TextStyle(
            fontSize: textSize, fontWeight: fontWeight, color: textColor));
  }
}
