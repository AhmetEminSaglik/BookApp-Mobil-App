import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import '../cubit/recommendedbook/BookCubit.dart';
import '../model/Book.dart';
import '../util/ProductColor.dart';
import 'BookDesignDecoration.dart';

class BookCard extends StatefulWidget {
  late Book book;
  late int index;
  late bool isBookRead;

  BookCard({required this.book, required this.index,required this.isBookRead});

  @override
  State<BookCard> createState() => _BookCardState();
}

class _BookCardState extends State<BookCard> {
  final double imgWidth = 90;
  final double imgHeight = 120;
  final double padding = 15;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: imgHeight * 7 / 4,
      child: Column(
        children: [
          Row(
            children: [
              Stack(
                children: [
                  InkWell(
                    onTap: () {
                      goToDetailPageOfBook(context, widget.book, widget.isBookRead);
                    },
                    child: getBookCardContent(),
                  ),
                  Row(
                    children: [
                      getBookImage(context),
                      getChevron(),
                    ],
                  ),
                ],
              ),
            ],
          )
        ],
      ),
    );
  }

  Padding getBookCardContent() {
    return Padding(
      padding: EdgeInsets.only(left: imgWidth / 2),
      child: ContainerWithBoxDecoration(
        child: Container(
          width: 295,
          height: imgHeight + 4.5 * padding,
          color: ProductColor.white,
          child: Padding(
            padding: EdgeInsets.only(left: imgWidth, top: 10),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  getShortTitle("${widget.index}-) ${widget.book.name}"),
                  maxLines: 2,
                  style: const TextStyle(
                      fontSize: 18, fontWeight: FontWeight.bold),
                ),
                Padding(
                  padding: const EdgeInsets.only(top: 10),
                  child: Row(
                    children: [
                      getBookRatingShape(widget.book.point),
                    ],
                  ),
                ),
                const SizedBox(
                  height: 10,
                ),
                Text(
                  "${widget.book.totalRead} Reviews",
                  style: const TextStyle(
                      fontSize: 15,
                      fontWeight: FontWeight.bold,
                      color: ProductColor.grey),
                ),
                Padding(
                  padding: const EdgeInsets.only(top: 10),
                  child: Text(
                    getShortDesc(widget.book.desc),
                    maxLines: 3,
                    style:
                        const TextStyle(fontSize: 15, color: ProductColor.grey),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Padding getChevron() {
    return Padding(
      padding: const EdgeInsets.only(left: 215),
      child: Container(
        decoration: BoxDecoration(
            border: Border.all(width: 2, color: ProductColor.white),
            borderRadius: const BorderRadius.all(Radius.circular(50)),
            color: ProductColor.white,
            boxShadow: [
              BoxShadow(
                color: Colors.deepOrange.withOpacity(0.7),
                spreadRadius: 2,
                blurRadius: 3,
                offset: const Offset(0, 1), // Gölge ofseti (x, y)
              ),
            ]),
        height: 25,
        width: 25,
        // color: ProductColor.red,
        child: const Icon(Icons.chevron_right, color: ProductColor.red),
      ),
    );
  }

  Padding getBookImage(BuildContext context) {
    return Padding(
        padding: EdgeInsets.only(top: padding),
        child: InkWell(
          onTap: () {
            goToDetailPageOfBook(context, widget.book, true);
          },
          child: ContainerWithBoxDecoration(
            child: Padding(
              padding: const EdgeInsets.all(10),
              child: Image.network(
                widget.book.imgUrl,
                fit: BoxFit.cover,
                height: imgHeight,
                width: imgWidth,
              ),
            ),
          ),
        ));
  }

  void goToDetailPageOfBook(BuildContext context, Book book, bool isBookAdded) {
    context.read<BookCubit>().setBook(book);
    context.read<BookCubit>().goToDetailPageOfBook(context, isBookAdded);
  }

  String getShortDesc(String desc) {
    if (desc.trim().length == 0) {
      return "- - -";
    }
    int index = 45;
    String shortDesc = desc;
    if (desc.trim().length > index) {
      shortDesc = shortDesc.replaceAll("\n", " ");
      shortDesc = "${shortDesc.substring(0, index).trim()}...";
    }
    return shortDesc;
  }

  String getShortTitle(String title) {
    int index = 40;
    if (title.trim().length > index) {
      return "${title.substring(0, index).trim()}...";
    }
    return title;
  }
}
