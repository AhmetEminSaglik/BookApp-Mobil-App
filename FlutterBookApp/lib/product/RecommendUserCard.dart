import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/model/Recommend.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:flutter_book_app/pages/UserFriendDetailPage.dart';
import 'package:flutter_book_app/product/RecommendUserDesignDecoration.dart';
import 'package:logger/logger.dart';
import '../cubit/recommendedbook/BookCubit.dart';
import '../httprequest/HttpRequestBook.dart';
import '../model/Book.dart';
import '../util/ProductColor.dart';
import '../util/ResponsiveDesign.dart';
import 'BookDesignDecoration.dart';

class RecommendUserCard extends StatefulWidget {
  late RecommendData<UserFriendDTO> recData;
  late int index;

  RecommendUserCard({required this.index, required this.recData});

  @override
  State<RecommendUserCard> createState() => _RecommendUserCardState();
}

class _RecommendUserCardState extends State<RecommendUserCard> {
  var log = Logger(printer: PrettyPrinter(colors: false));

  final double imgWidth = ResponsiveDesign.width() / 5;
  final double imgHeight = ResponsiveDesign.height() / 6.5;
  final double padding = ResponsiveDesign.height() / 65;

  bool isLoading = true;

  // List<Book> bookList = [];

  @override
  void initState() {
    super.initState();
    // _retrieveReadBookData();
    print("UserCard initState calisti");
  }

  _retrieveReadBookData() async {
    await _retrieveUserList();
    setState(() {
      isLoading = false;
    });
  }

  _retrieveUserList() async {
    // bookList = await HttpRequestBook.getReadBookList();
  }

  @override
  Widget build(BuildContext context) {
    print("Gelen User Card ${widget.recData.data}");
    return SizedBox(
      height: imgHeight * 1.8,
      child: Column(
        children: [
          Row(
            children: [
              Stack(
                children: [
                  InkWell(
                    onTap: () {
                      goToDetailPage(context, widget.recData.data);
                    },
                    child: getRecommendCardContent(),
                  ),
                  Padding(
                    padding:
                        EdgeInsets.only(top: ResponsiveDesign.height() / 30),
                    child: Row(
                      children: [
                        getImage(context),
                        getChevron(),
                      ],
                    ),
                  ),
                ],
              ),
            ],
          )
        ],
      ),
    );
  }

  bool isBookReadByUser(Book book) {
    bool isBookRead = false;
/*    bookList.forEach((element) {
      if (element.id == book.id) {
        isBookRead = true;
        return;
      }
    });
    log.i("Book is Read : $isBookRead");*/
    return isBookRead;
  }

  Padding getRecommendCardContent() {
    final double contentWidth = imgWidth / 2 + ResponsiveDesign.width() / 25;
    return Padding(
      padding: EdgeInsets.only(left: contentWidth),
      child: ContainerWithBoxDecoration(
        child: Container(
          // width: 295,
          width: ResponsiveDesign.width() - contentWidth - 5 * padding,
          height: imgHeight + 5.5 * padding,
          color: ProductColor.white,
          child: Padding(
            padding: EdgeInsets.only(left: imgWidth * 0.75, top: 10),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  getShortTitle(
                      "${widget.index}-) ${widget.recData.data.name} ${widget.recData.data.lastname}"),
                  maxLines: 2,
                  style: const TextStyle(
                      fontSize: 18, fontWeight: FontWeight.bold),
                ),
                /*   Padding(
                  padding: const EdgeInsets.only(top: 5),
                  child: Row(
                    children: [
                      getBookRatingShape(widget.recData.data.point),
                    ],
                  ),
                ),*/
                Text(
                  widget.recData.by,
                  style: TextStyle(
                      fontSize: 15,
                      fontWeight: FontWeight.bold,
                      color: widget.recData.color),
                ),
                const SizedBox(
                  height: 15,
                ),
                Text(
                  "Total Read Book : ${widget.recData.data.totalReadBook}",
                  style: const TextStyle(
                      fontSize: 17,
                      fontWeight: FontWeight.bold,
                      color: ProductColor.grey),
                ),
                const SizedBox(
                  height: 5,
                ),
                Text(
                  "Following : ${widget.recData.data.totalFollowing}",
                  style: const TextStyle(
                      fontSize: 17,
                      fontWeight: FontWeight.bold,
                      color: ProductColor.grey),
                ),
                const SizedBox(
                  height: 5,
                ),
                Text(
                  "Followers : ${widget.recData.data.totalFollowers}",
                  style: const TextStyle(
                      fontSize: 17,
                      fontWeight: FontWeight.bold,
                      color: ProductColor.grey),
                ),

                /*    Padding(
                  padding: const EdgeInsets.only(top: 5),
                  child: Text(
                    getShortDesc(widget.recData.data.desc),
                    maxLines: 3,
                    style:
                        const TextStyle(fontSize: 15, color: ProductColor.grey),
                  ),
                ),*/
              ],
            ),
          ),
        ),
      ),
    );
  }

  Padding getChevron() {
    return Padding(
      padding: EdgeInsets.only(
          left: ResponsiveDesign.width() - imgWidth - 7.5 * padding),
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

  Padding getImage(BuildContext context) {
    return Padding(
        padding: EdgeInsets.only(top: padding),
        child: InkWell(
          onTap: () {
            goToDetailPage(context, widget.recData.data);
          },
          child: Padding(
            padding: const EdgeInsets.all(10),
            child: RecommendUserDesignDecoration(
              child: ClipRRect(
                borderRadius: BorderRadius.circular(100),
                child: Image.network(
                  widget.recData.data.imgUrl,
                  fit: BoxFit.fitWidth,
                  // height: imgHeight,
                  width: imgWidth,
                ),
              ),
            ),
          ),
        ));
  }

  void goToDetailPage(BuildContext context, UserFriendDTO userFriendDTO) {
    // context.read<BookCubit>().setBook(book);
    // context.read<BookCubit>().goToDetailPage(context);
    _navigatePage(context, userFriendDTO);
  }

  void _navigatePage(BuildContext context, UserFriendDTO userFriendDTO) {
    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => UserFriendDetailPage(
                  userDTO: userFriendDTO,
                )));
  }

  String getShortDesc(String desc) {
    if (desc.trim().length == 0) {
      return "- - -";
    }
    int index = 60;
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
