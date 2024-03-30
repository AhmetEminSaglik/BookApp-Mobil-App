import 'package:flutter/material.dart';
import 'package:flutter_rating_bar/flutter_rating_bar.dart';
import '../util/ProductColor.dart';


class RecommendUserDesignDecoration extends StatelessWidget {
  late Widget child;

  RecommendUserDesignDecoration({super.key, required this.child});

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
          border: Border.all(width: 5, color: ProductColor.white),
          borderRadius: const BorderRadius.all(Radius.circular(100)),
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
