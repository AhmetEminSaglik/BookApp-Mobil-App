enum EnumRecommendBy {
  BY_FRIEND(name: "BY FRIEND"),
  BESTSELLERS(name: "BESTSELLERS"),
  HIGHEST_RATING(name: "HIGHEST RATING"),
  BESTSELLER(name: "BESTSELLER"),
  BY_RANDOM(name: "BY RANDOM");

  final String name;

  const EnumRecommendBy({required this.name});
}
