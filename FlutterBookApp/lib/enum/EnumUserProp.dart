enum EnumUserProp {
  ID(name: "id"),
  NAME(name: "name"),
  LASTNAME(name: "lastname"),
  USERNAME(name: "username"),
  PASSWORD(name: "password"),
  TOTAL_FOLLOWERS(name: "followers"),
  TOTAL_Following(name: "following"),
  GENDER(name: "gender"),
  IMG_URL(name: "imgUrl");

  final String name;

  const EnumUserProp({required this.name});
}
