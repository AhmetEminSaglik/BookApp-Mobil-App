import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_rating_bar/flutter_rating_bar.dart';

import '../cubit/recommendedbook/BookCubit.dart';
import '../model/Book.dart';
import '../util/ProductColor.dart';

class BookCard extends StatefulWidget {
  late Book book;

  BookCard({required this.book});

  @override
  State<BookCard> createState() => _BookCardState();
}

class _BookCardState extends State<BookCard> {
  final double imgWidth = 90;
  final double imgHeight = 140;
  final double paddingTop = 20;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 255,
      child: Column(
        children: [
          Row(
            children: [
              Stack(
                children: [
                  InkWell(
                    onTap: () {
                      goToDetailPageOfBook(context, widget.book);
                    },
                    child: Padding(
                      padding:
                          EdgeInsets.only(left: imgWidth * 3 / 5, right: 10),
                      child: ContainerWithBoxDecoration(
                        child: Container(
                          width: 270,
                          height: imgHeight + 4 * paddingTop,
                          color: ProductColor.white,
                          child: Container(
                            child: Padding(
                              padding: EdgeInsets.only(left: imgWidth, top: 15),
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    getShortTitle(widget.book.name),
                                    maxLines: 2,
                                    style: const TextStyle(
                                        fontSize: 20,
                                        fontWeight: FontWeight.bold),
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
                                    width: 10,
                                  ),
                                  Text(
                                    "${widget.book.totalRead} Reviews",
                                    style: const TextStyle(
                                        fontSize: 15,
                                        fontWeight: FontWeight.bold,
                                        color: ProductColor.grey),
                                  ),
                                  Padding(
                                    padding: const EdgeInsets.only(top: 15),
                                    child: Text(
                                      getShortDesc(widget.book.desc),
                                      maxLines: 3,
                                      style: const TextStyle(
                                          fontSize: 15,
                                          color: ProductColor.grey),
                                    ),
                                  ),
                                ],
                              ),
                            ),
                          ),
                        ),
                      ),
                    ),
                  ),
                  Row(
                    children: [
                      Padding(
                          padding: EdgeInsets.only(top: paddingTop),
                          child: InkWell(
                            onTap: () {
                              goToDetailPageOfBook(context, widget.book);
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
                          )),
                      Padding(
                        padding: const EdgeInsets.only(left: 205),
                        child: Container(
                          decoration: BoxDecoration(
                              border: Border.all(
                                  width: 2, color: ProductColor.white),
                              borderRadius:
                                  const BorderRadius.all(Radius.circular(50)),
                              color: ProductColor.white,
                              boxShadow: [
                                BoxShadow(
                                  color: Colors.deepOrange.withOpacity(0.7),
                                  spreadRadius: 2,
                                  blurRadius: 3,
                                  offset:
                                      const Offset(0, 1), // Gölge ofseti (x, y)
                                ),
                              ]),
                          height: 25,
                          width: 25,
                          // color: ProductColor.red,
                          child: Icon(Icons.chevron_right,
                              color: ProductColor.red),
                        ),
                      ),
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

  void goToDetailPageOfBook(BuildContext context, Book book) {
    context.read<BookCubit>().setBook(book);
    context.read<BookCubit>().goToDetailPageOfBook(context);
  }

  String getShortDesc(String desc) {
    if (desc.trim().length == 0) {
      return "- - -";
    }
    int index = 70;
    String shortDesc = desc;
    if (desc.trim().length > index) {
      shortDesc = shortDesc.replaceAll("\n", " ");
      shortDesc = "${shortDesc.substring(0, index).trim()}...";
    }
    return shortDesc;
  }

  String getShortTitle(String title) {
    int index = 35;
    if (title.trim().length > index) {
      return "${title.substring(0, index).trim()}...";
    }
    return title;
  }
}

class ContainerWithBoxDecoration extends StatelessWidget {
  late Widget child;

  ContainerWithBoxDecoration({required this.child});

  @override
  Widget build(BuildContext context) {
    return Container(
      // width: width,
      // height: height,
      decoration: BoxDecoration(
          border: Border.all(width: 5, color: ProductColor.white),
          borderRadius: const BorderRadius.all(Radius.circular(12)),
          color: ProductColor.white,
          boxShadow: [
            BoxShadow(
              color: Colors.grey.withOpacity(0.7),
              spreadRadius: 6,
              blurRadius: 5,
              offset: const Offset(0, 1), // Gölge ofseti (x, y)
            ),
          ]),
      child: child,
    );
  }
}

Widget getBookRatingShape(double rating) {
  // rating /= 2;
  double currentRating = rating;
  return RatingBar.builder(
      initialRating: rating,
      tapOnlyMode: false,
      allowHalfRating: true,
      itemBuilder: (context, _) => const Icon(
            // Icons.star,
            Icons.star,
            color: ProductColor.ratingColor,
          ),
      unratedColor: ProductColor.unRatingColor,
      itemSize: 25,
      onRatingUpdate: (rating) {
        rating = currentRating;
        /*   setState(() {
          rating = currentRating;
        });*/
      });
}

Text getReviewText(int totalReviews) {
  return Text("$totalReviews Reviews",
      style: const TextStyle(
          fontSize: 15, fontWeight: FontWeight.bold, color: ProductColor.grey));
}

Text getAveragePointText(double point) {
  return Text("$point",
      style: const TextStyle(
          fontSize: 15, fontWeight: FontWeight.bold, color: ProductColor.grey));
}
